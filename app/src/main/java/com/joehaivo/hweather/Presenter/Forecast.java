package com.joehaivo.hweather.Presenter;

import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.ArrayMap;

import com.google.gson.Gson;
import com.joehaivo.hweather.Model.HefengAQIBean;
import com.joehaivo.hweather.Model.HefengWeatherBean;
import com.joehaivo.hweather.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Forecast {
    private static final int
            FLAG_NOW_CONDITION = 1, TAG_NOW_TMP = 2, FLAG_FORE_HOURLY = 3,
            FLAG_HEFENG_AQI = 4, FLAG_FORE_DAILY = 5, FLAG_LOCATION_CITY = 6,
            FLAG_LIFE_INDEX = 7;
    private Fragment fragment;
    private Handler handler;
    private OkHttpClient client = new OkHttpClient();

    public Forecast(Fragment fragment, Handler handler) {
        this.handler = handler;
        this.fragment = fragment;
    }

    public void getHefengAQI(String city) {
        Request okRequest = new Request.Builder()
                .url("https://free-api.heweather.com/s6/air/now?key=bbeef92e1fe94133b1c298024e7629f5&location=" + city)
                .build();
        client.newCall(okRequest).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                HefengAQIBean hefengAQIBean = gson.fromJson(response.body().string(), HefengAQIBean.class);
                List<HefengAQIBean.HeWeather6Bean> heWeather6BeanList = hefengAQIBean.getHeWeather6();
                HefengAQIBean.HeWeather6Bean heWeather6Bean = heWeather6BeanList.get(0);
                if (heWeather6Bean.getStatus().equals("ok")) {
                    HefengAQIBean.HeWeather6Bean.AirNowCityBean airNowCityBean = heWeather6Bean.getAir_now_city();
                    handler.obtainMessage(FLAG_HEFENG_AQI, airNowCityBean.getAqi() + " " + airNowCityBean.getQlty()).sendToTarget();
                }
            }
        });
    }

    public void getHefengWeather(String city) {
        Request okRequest = new Request.Builder()
                .url("https://free-api.heweather.com/s6/weather?key=bbeef92e1fe94133b1c298024e7629f5&location=" + city)
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
                if (heWeather6Bean.getStatus().equals("ok")) {
                    //定位地点
                    HefengWeatherBean.HeWeather6Bean.BasicBean basicBean = heWeather6Bean.getBasic();
                    String city = basicBean.getLocation();
                    handler.obtainMessage(FLAG_LOCATION_CITY, city).sendToTarget();
                    //实时
                    HefengWeatherBean.HeWeather6Bean.NowBean nowBean = heWeather6Bean.getNow();
                    String nowTxt = nowBean.getCond_txt(); //实时天气状况
                    handler.obtainMessage(FLAG_NOW_CONDITION, nowTxt).sendToTarget();
                    String nowTmp = nowBean.getTmp(); //实时温度
                    handler.obtainMessage(TAG_NOW_TMP, nowTmp).sendToTarget();
                    //7天
                    List<HefengWeatherBean.HeWeather6Bean.DailyForecastBean> dailyForecastBeanList = heWeather6Bean.getDaily_forecast();
                    List<Map<String, Object>> dailyList = new ArrayList<>();  //7天数据集合
                    int dailyCount = 0;
                    while (dailyCount < 7) {
                        Map<String, Object> dailyMap = new ArrayMap<>();
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
                    handler.obtainMessage(FLAG_FORE_DAILY, dailyList).sendToTarget();
                    //每小时
                    List<HefengWeatherBean.HeWeather6Bean.HourlyBean> hourlyBeanList = heWeather6Bean.getHourly();
                    List<Map<String, Object>> hourlyList = new ArrayList<>(); //小时数据集合
                    int hourCount = 0;
                    while (hourCount < 8) {
                        HefengWeatherBean.HeWeather6Bean.HourlyBean hourlyBean = hourlyBeanList.get(hourCount);
                        Map<String, Object> hourlyMap = new ArrayMap<>();
                        String time = hourlyBean.getTime();  //时间
                        hourlyMap.put("HOUR_TIME", time.substring(11));
                        String hourTxt = hourlyBean.getCond_txt();  //小时状况
                        if (hourTxt.equals("晴间多云")) {
                            hourlyMap.put("HOUR_TXT", "多云");
                        } else {
                            hourlyMap.put("HOUR_TXT", hourTxt);
                        }
                        Drawable drawable = getConditionDraw(hourTxt); //图标
                        hourlyMap.put("HOUR_DRAW", drawable);
                        String hourTmp = hourlyBean.getTmp();  //小时温度
                        hourlyMap.put("HOUR_TMP", hourTmp);
                        hourlyList.add(hourlyMap);
                        hourCount++;
                    }
                    handler.obtainMessage(FLAG_FORE_HOURLY, hourlyList).sendToTarget();
                    //生活指数
                    List<HefengWeatherBean.HeWeather6Bean.LifestyleBean> lifestyleBeanList = heWeather6Bean.getLifestyle();
                    int lifeCount = 0;
                    List<String> list = new ArrayList<>(); //指数数据集合
                    while (lifeCount < 8) {
                        HefengWeatherBean.HeWeather6Bean.LifestyleBean lifestyleBean = lifestyleBeanList.get(lifeCount);
                        String lifeType = lifestyleBean.getType();   //生活指数类型
                        switch (lifeType) {
                            case "comf":
                                list.add(lifestyleBean.getBrf());
                                list.add(lifestyleBean.getTxt());
                                break;
                            case "air":
                                list.add(lifestyleBean.getBrf());
                                list.add(lifestyleBean.getTxt());
                                break;
                            case "drsg":
                                list.add(lifestyleBean.getBrf());
                                list.add(lifestyleBean.getTxt());
                                break;
                        }
                        lifeCount++;
                    }
                    handler.obtainMessage(FLAG_LIFE_INDEX, list).sendToTarget();
                }
            }
        });
    }

    private Drawable getConditionDraw(String condition) {
        switch (condition) {
            case "晴":
                return fragment.getActivity().getDrawable(R.drawable.sunny);
            case "多云":
            case "少云":
            case "晴间多云":
                return fragment.getActivity().getDrawable(R.drawable.cloudy);
            case "阴":
                return fragment.getActivity().getDrawable(R.drawable.over_cast);
            case "阵雨":
                return fragment.getActivity().getDrawable(R.drawable.shower);
            case "雷阵雨":
                return fragment.getActivity().getDrawable(R.drawable.thunder_shower);
            case "小雨":
                return fragment.getActivity().getDrawable(R.drawable.light_rain);
            case "中雨":
                return fragment.getActivity().getDrawable(R.drawable.mid_rain);
            case "大雨":
                return fragment.getActivity().getDrawable(R.drawable.heavy_rain);
            case "小雪":
                return fragment.getActivity().getDrawable(R.drawable.light_snow);
            case "中雪":
            case "大雪":
                return fragment.getActivity().getDrawable(R.drawable.heavy_snow);
            case "雾":
                return fragment.getActivity().getDrawable(R.drawable.fog);
            case "浮尘":
                return fragment.getActivity().getDrawable(R.drawable.dusty);
            case "强沙尘暴":
            case "沙尘暴":
                return fragment.getActivity().getDrawable(R.drawable.sandstorms);
            case "风暴":
                return fragment.getActivity().getDrawable(R.drawable.strom);
            case "大风":
            case "烈风":
            case "疾风":
                return fragment.getActivity().getDrawable(R.drawable.strong_wind);
            case "微风":
            case "和风":
            case "清风":
                return fragment.getActivity().getDrawable(R.drawable.wind);
        }
        return fragment.getActivity().getDrawable(R.drawable.sunny);
    }
}
