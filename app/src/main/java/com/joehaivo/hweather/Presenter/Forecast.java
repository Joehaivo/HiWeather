package com.joehaivo.hweather.Presenter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.ArrayMap;
import android.util.Log;

import com.google.gson.Gson;
import com.joehaivo.hweather.Model.HefengAQIBean;
import com.joehaivo.hweather.Model.HefengWeatherBean;
import com.joehaivo.hweather.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Forecast {
    private static final int TAG_SUCCESS_FLAG = 0, TAG_DATE = 1, TAG_FORE_CONDITION = 2,
            TAG_NOW_CONDITION = 3, TAG_HIGHTMP = 4, TAG_LOWTMP = 5, TAG_NOW_TMP = 6,
            TAG_IMAGE_DRAW = 7, TAG_HOURLYDESCRIPTION = 8, TAG_LIST = 9, TAG_FORE_CODE = 10,
            TAG_FORE_HOURLY = 11, TAG_SUGGESTION = 12, TAG_HEFENG_AQI = 13, FLAG_FORE_DAILY = 14,
            FLAG_LOCATION_CITY = 15;
    private Context context;
    private Handler mHandler;
    String TAG = "TAGForecast";
    private final OkHttpClient client = new OkHttpClient();

    public Forecast(Context context, Handler handler) {
        mHandler = handler;
        this.context = context;
    }

    public void getHefengAQI(String city) {
        final String basicUrl = "https://free-api.heweather.com/v5/aqi?city=";
        final String keyUrl = "&key=bbeef92e1fe94133b1c298024e7629f5";
        Request okRequest = new Request.Builder()
                .url(basicUrl + city + keyUrl)
                .build();
        client.newCall(okRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                HefengAQIBean bean = gson.fromJson(response.body().string(), HefengAQIBean.class);
                List<HefengAQIBean.HeWeather5Bean> heWeather5BeanList = bean.getHeWeather5();
                HefengAQIBean.HeWeather5Bean heWeather5Bean = heWeather5BeanList.get(0);
                HefengAQIBean.HeWeather5Bean.AqiBean aqiBean = heWeather5Bean.getAqi();
                HefengAQIBean.HeWeather5Bean.AqiBean.CityBean cityBean = aqiBean.getCity();
                mHandler.obtainMessage(TAG_HEFENG_AQI, cityBean.getAqi() + " " + cityBean.getQlty()).sendToTarget();
            }
        });
    }

    public void getHefengWeather(String city) {
        Request okRequest = new Request.Builder()
                .url("https://free-api.heweather.com/s6/weather?key=bbeef92e1fe94133b1c298024e7629f5&location="
                        + city)
                .build();
        client.newCall(okRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                HefengWeatherBean bean = gson.fromJson(response.body().string(), HefengWeatherBean.class);
                List<HefengWeatherBean.HeWeather6Bean> heWeather6BeanList = bean.getHeWeather6();
                HefengWeatherBean.HeWeather6Bean heWeather6Bean = heWeather6BeanList.get(0);
                Log.d(TAG, "onResponse: "+heWeather6Bean.getStatus());
                if (heWeather6Bean.getStatus().equals("ok")) {
                    //定位地点
                    HefengWeatherBean.HeWeather6Bean.BasicBean basicBean = heWeather6Bean.getBasic();
                    String city = basicBean.getLocation();
                    mHandler.obtainMessage(FLAG_LOCATION_CITY,city).sendToTarget();
                    //实时
                    HefengWeatherBean.HeWeather6Bean.NowBean nowBean = heWeather6Bean.getNow();
                    String nowTxt = nowBean.getCond_txt(); //实时天气状况
                    Log.d(TAG, "onResponse: "+nowTxt);
                    mHandler.obtainMessage(TAG_NOW_CONDITION, nowTxt).sendToTarget();
                    String nowTmp = nowBean.getTmp(); //实时温度
                    mHandler.obtainMessage(TAG_NOW_TMP, nowTmp).sendToTarget();
                    //7天
                    List<HefengWeatherBean.HeWeather6Bean.DailyForecastBean> dailyForecastBeanList = heWeather6Bean.getDaily_forecast();
                    List<Map<String,Object>> dailyList = new ArrayList<>();  //7天数据集合
                    int dailyCount = 0;
                    while (dailyCount < 7) {
                        Map<String,Object> dailyMap = new ArrayMap<>();
                        HefengWeatherBean.HeWeather6Bean.DailyForecastBean dailyForecastBean = dailyForecastBeanList.get(dailyCount);
                        String date = dailyForecastBean.getDate();  //日期
                        if (dailyCount == 0) {
                            dailyMap.put("DATE", "今天");
                        } else if (dailyCount == 1) {
                            dailyMap.put("DATE", "明天");
                        } else {
                            dailyMap.put("DATE", date.substring(5));
                        }
                        String dayTxt = dailyForecastBean.getCond_txt_d(); //白天状况
                        dailyMap.put("DAY_TXT", dayTxt);
                        Drawable drawable = getConditionDraw(dayTxt); //图标
                        dailyMap.put("DAY_DRAW", drawable);
                        String maxTmp = dailyForecastBean.getTmp_max(); //最高温
                        dailyMap.put("DAY_MAX_TMP", maxTmp);
                        String minTmp = dailyForecastBean.getTmp_min();  //最低温
                        dailyMap.put("DAY_MIN_TMP", minTmp);
                        dailyList.add(dailyMap);
                        dailyCount++;
                    }
                    mHandler.obtainMessage(FLAG_FORE_DAILY, dailyList).sendToTarget();
                    //每小时
                    List<HefengWeatherBean.HeWeather6Bean.HourlyBean> hourlyBeanList = heWeather6Bean.getHourly();
                    List<Map<String,Object>> hourlyList = new ArrayList<>(); //小时数据集合
                    int hourCount = 0;
                    while (hourCount < 8) {
                        HefengWeatherBean.HeWeather6Bean.HourlyBean hourlyBean = hourlyBeanList.get(hourCount);
                        Map<String,Object> hourlyMap = new ArrayMap<>();
                        String time = hourlyBean.getTime();  //时间
                        hourlyMap.put("HOUR_TIME",time.substring(11));
                        String hourTxt = hourlyBean.getCond_txt();  //小时状况
                        if (hourTxt.equals("晴间多云")){
                            hourlyMap.put("HOUR_TXT","多云");
                        }else{
                            hourlyMap.put("HOUR_TXT",hourTxt);
                        }
                        Drawable drawable = getConditionDraw(hourTxt); //图标
                        hourlyMap.put("HOUR_DRAW",drawable);
                        String hourTmp = hourlyBean.getTmp();  //小时温度
                        hourlyMap.put("HOUR_TMP",hourTmp);
                        hourlyList.add(hourlyMap);
                        hourCount++;
                    }
                    mHandler.obtainMessage(TAG_FORE_HOURLY, hourlyList).sendToTarget();
                    //生活指数
                    List<HefengWeatherBean.HeWeather6Bean.LifestyleBean> lifestyleBeanList = heWeather6Bean.getLifestyle();
                    List<Map<String,Object>> lifeList = new ArrayList<>(); //指数数据集合
                    int lifeCount = 0;
                    List<String> list = new ArrayList<>();
                    while (lifeCount < 8) {
//                        Map<String,Object> lifeMap = new ArrayMap<>();
                        HefengWeatherBean.HeWeather6Bean.LifestyleBean lifestyleBean = lifestyleBeanList.get(lifeCount);
                        String lifeType = lifestyleBean.getType();   //生活指数类型
                        switch (lifeType){
                            case "comf":
                                list.add(lifestyleBean.getBrf());
                                list.add(lifestyleBean.getTxt());
                                break;
                            case "air":
                                list.add(lifestyleBean.getBrf());
                                list.add(lifestyleBean.getTxt());break;
                            case "drsg":
                                list.add(lifestyleBean.getBrf());
                                list.add(lifestyleBean.getTxt());break;
                        }
//                        Drawable drawable = getLifeTypeDraw(lifeType); //图标
//                        lifeMap.put("LIFE_DRAW",drawable);
//                        String lifeBrf = lifestyleBean.getBrf();  //指数概述
//                        lifeMap.put("LIFE_BRF",liftTypeToChinese(lifeType)+"  "+lifeBrf);
//                        String lifeTxt = lifestyleBean.getTxt();  //指数描述
//                        lifeMap.put("LIFE_TXT",lifeTxt);
//                        lifeList.add(lifeMap);
                        lifeCount++;
                    }
                    mHandler.obtainMessage(17, list).sendToTarget();
//                    mHandler.obtainMessage(TAG_SUGGESTION, lifeList).sendToTarget();
                }
            }
        });
    }

    private Drawable getConditionDraw(String conditon) {
        switch (conditon) {
            case "晴":
                return context.getDrawable(R.drawable.sunny);
            case "多云":
            case "少云":
            case "晴间多云":
                return context.getDrawable(R.drawable.cloudy);
            case "阴":
                return context.getDrawable(R.drawable.over_cast);
            case "阵雨":
                return context.getDrawable(R.drawable.shower);
            case "雷阵雨":
                return context.getDrawable(R.drawable.thunder_shower);
            case "小雨":
                return context.getDrawable(R.drawable.light_rain);
            case "中雨":
                return context.getDrawable(R.drawable.mid_rain);
            case "大雨":
                return context.getDrawable(R.drawable.heavy_rain);
            case "小雪":
                return context.getDrawable(R.drawable.light_snow);
            case "中雪":
            case "大雪":
                return context.getDrawable(R.drawable.heavy_snow);
            case "雾":
                return context.getDrawable(R.drawable.fog);
            case "浮尘":
                return context.getDrawable(R.drawable.dusty);
            case "强沙尘暴":
            case "沙尘暴":
                return context.getDrawable(R.drawable.sandstorms);
            case "风暴":
                return context.getDrawable(R.drawable.strom);
            case "大风":
            case "烈风":
            case "疾风":
                return context.getDrawable(R.drawable.strong_wind);
            case "微风":
            case "和风":
            case "清风":
                return context.getDrawable(R.drawable.wind);
        }
        return context.getDrawable(R.drawable.sunny);
    }
    private Drawable getLifeTypeDraw(String type){
        switch (type){
            case "comf":return context.getDrawable(R.drawable.comfort);
            case "drsg":return context.getDrawable(R.drawable.clothes);
            case "flu":return context.getDrawable(R.drawable.risk);
            case "sport": return context.getDrawable(R.drawable.sport);
            case "trav": return context.getDrawable(R.drawable.travel);
            case "uv":return context.getDrawable(R.drawable.uv);
            case "cw":return context.getDrawable(R.drawable.wash_car);
            case "air":return context.getDrawable(R.drawable.air);
        }
        return context.getDrawable(R.drawable.comfort);
    }
    private String liftTypeToChinese(String lifeType){
        switch (lifeType){
            case "comf":return "舒适度";
            case "drsg":return "穿衣指数";
            case "flu":return "感冒指数";
            case "sport": return "运动指数";
            case "trav": return "旅游指数";
            case "uv":return "紫外线强度";
            case "cw":return "洗车指数";
            case "air":return "空气质量";
        }
        return "N/A";
    }
}
