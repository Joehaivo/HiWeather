package com.joehaivo.hweather.View;


import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.desmond.citypicker.bean.BaseCity;
import com.desmond.citypicker.bin.CityPicker;
import com.desmond.citypicker.callback.IOnCityPickerCheckedCallBack;
import com.joehaivo.hweather.Presenter.Forecast;
import com.joehaivo.hweather.Presenter.MyFragmentPagerAdapter;
import com.joehaivo.hweather.R;
import com.joehaivo.hweather.Presenter.Recycler7DayAdapter;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements TencentLocationListener, WeatherFragment.OnFragmentInteractionListener, WeatherFragmentLocation.OnFragmentInteractionListener {
    String TAG = "TAG";
    TextView tmpText, sunText, tvAirNum, cityText, tv24hrDescription;
    ImageButton addBtn;
    Handler handler;
    SwipeRefreshLayout swipeRefreshLayout;
    SharedPreferences sharedPreferences;
    double localLongitude = 113.817, localLatitude = 27.6;
    RecyclerView forecastRecyclerView, rv24hrFore, rvSuggestion;
    Recycler7DayAdapter recycler7DayAdapter;
    ImageView imageAnime;
    ViewPager viewPager;
    MyFragmentPagerAdapter mViewMyFragmentPagerAdapter;

    public void os() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTranslucentStatus(true);
        bindView();
//        重力感应动画
//        SensorManager mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
//        Sensor mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
//        mSensorManager.registerListener(new SensorEventListener() {
//            @Override
//            public void onSensorChanged(SensorEvent sensorEvent) {
//                ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(imageAnime,"translationX",0,sensorEvent.values[1]*230);
//                objectAnimator.setDuration(600);
//                objectAnimator.start();
//            }
//
//            @Override
//            public void onAccuracyChanged(Sensor sensor, int i) {
//            }
//        }, mSensor,2000000,2000000);
//        swipeRefreshLayout.setOnRefreshListener(this);
//        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary);

        //和风 https://free-api.heweather.com/v5/forecast?city=萍乡市&key=bbeef92e1fe94133b1c298024e7629f5
        //彩云 https://api.caiyunapp.com/v2/7xJkeOW5-lexk1l5/113.817,27.6/forecast.json
        //腾讯定位SDK


//        sharedPreferences = this.getSharedPreferences("realtimeInfo", MODE_PRIVATE);
//        final SharedPreferences.Editor editor = sharedPreferences.edit();
//        setTextfromlocal(sharedPreferences);

//        FragmentManager fragmentManager = getSupportFragmentManager();
//        fragmentManager.beginTransaction().add(weatherInfoFragment,"s").commit();
//        handler = new Handler() {
//            private static final int TAG_SUCCESS_FLAG = 0;
//            private static final int TAG_DATE = 1;
//            private static final int TAG_FORE_CONDITION = 2;
//            private static final int TAG_NOW_CONDITION = 3;
//            private static final int TAG_HIGHTMP = 4;
//            private static final int TAG_LOWTMP = 5;
//            private static final int TAG_NOW_TMP = 6;
//            private static final int TAG_IMAGECODE = 7;
//            private static final int TAG_HOURLYDESCRIPTION = 8;
//            private static final int TAG_LIST = 9;
//            private static final int TAG_FORE_CODE = 10;
//            private static final int TAG_FORE_HOURLY = 11;
//            private static final int TAG_SUGGESTION = 12;
//            private static final int TAG_HEFENG_AQI = 13;
//
//            @Override
//            public void handleMessage(Message msg) {
//                super.handleMessage(msg);
//                switch (msg.what) {
//
//                }
//            }
//        };
//        List<Fragment> fragmentList = new ArrayList<>();
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.constraintLayout_fragment,WeatherFragment.newInstance(0));
//            fragmentTransaction.add(R.id.constraintLayout_fragment,WeatherFragment.newInstance(1));
//            fragmentTransaction.commit();
//            fragmentList.add(new WeatherFragmentLocation().getFragment("萍乡"));
//        fragmentList.add(new WeatherFragment().getFragment("北京市"));
        mViewMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewMyFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        handler = new Handler() {
            private static final int FLAG_CITY_NAME = 16;

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case FLAG_CITY_NAME:
                        cityText.setText(msg.obj.toString());
                        break;
                    case 18:
                        cityText.setText(msg.obj.toString());
                        WeatherFragmentLocation weatherFragmentLocation
                                = (WeatherFragmentLocation) getSupportFragmentManager().findFragmentByTag("android:switcher:2131230951:0");
                        Log.d(TAG, "handleMessage: "+weatherFragmentLocation);
                        weatherFragmentLocation.onRefresh(msg.obj.toString());
                        break;
                }
            }
        };
    }

    private void bindView() {
        sunText = (TextView) findViewById(R.id.sunnyText);
        tmpText = (TextView) findViewById(R.id.tmpText);
        tvAirNum = (TextView) findViewById(R.id.air_num);
        cityText = (TextView) findViewById(R.id.cityText);
        addBtn = (ImageButton) findViewById(R.id.btn_setting);
        tv24hrDescription = (TextView) findViewById(R.id.text_24hr_description);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);
        imageAnime = (ImageView) findViewById(R.id.imageAnime);
        viewPager = (ViewPager) findViewById(R.id.view_pager_fragment);
    }

    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public void btnAddCityClick(View view) {
//        Intent intent = new Intent(this, AddCityActivity.class);
//        startActivityForResult(intent, 1);
        CityPicker.with(this)
                .setUseGpsCity(false)
                .setMaxHistory(0)
                .setSearchViewTextSize(14)
                .setOnCityPickerCallBack(new IOnCityPickerCheckedCallBack() {
                    @Override
                    public void onCityPickerChecked(BaseCity baseCity) {
                        handler.obtainMessage(18,baseCity.getCityName()).sendToTarget();
                    }
                })
                .open();
    }

    public void showCityName(String city) {
        cityText.setText(city);
    }

    //获得来自AddActivity返回的城市名
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String result = data.getExtras().getString("result");
        Forecast forecast;
        if (result.equals("北京")) {
            cityText.setText(result);

        }
    }

    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {

    }

    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
//     class MyFragmentPagerAdapter extends FragmentPagerAdapter {
//        private List<Fragment> fragmentList;
//        int nowPosition = 0,count;
//        String city,location;
//        String TAG = "MyFragmentPagerAdapter";
//        public MyFragmentPagerAdapter(FragmentManager fm) {
//            super(fm);
////        this.city = city;
////        this.count = count;
//////        this.location = locationCity;
//////        this.fragmentList = fragmentList;
//            notifyDataSetChanged();
//        }
//
//        @Override
//        public Fragment getItem(int position) {
//            switch (position){
//                case 0: return WeatherFragmentLocation.newInstance(position,cityName);
//                case 1: return WeatherFragment.newInstance(position+1,"北京");
//                case 2: return WeatherFragment.newInstance(position+1,"上海");
//                default: return null;
//            }
////        return fragmentList.get(position);
//        }
//
//        @Override
//        public int getCount() {
////        return fragmentList.size();
//            return 3;
//        }
//    }
}
