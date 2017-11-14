package com.joehaivo.hweather.View;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.desmond.citypicker.bean.BaseCity;
import com.desmond.citypicker.bin.CityPicker;
import com.desmond.citypicker.callback.IOnCityPickerCheckedCallBack;
import com.joehaivo.hweather.Presenter.MyFragmentPagerAdapter;
import com.joehaivo.hweather.R;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity implements TencentLocationListener,
        WeatherFragmentLocation.OnFragmentInteractionListener {
    String TAG = "TAG";
    TextView cityText;
    ImageButton addBtn;
    Handler handler;
    TencentLocationRequest tencentLocationRequest;
    TencentLocationManager locationManager;
    ViewPager viewPager;
    MyFragmentPagerAdapter myFragmentPagerAdapter;
    private static final int FLAG_CITY_NAME = 8,FLAG_FRAGMENT_SELF = 9,FLAG_LOCATION_CITY = 10,FLAG_ADD_NEW_CITY = 11;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTranslucentStatus(true);
        bindView();
        tencentLocationRequest = TencentLocationRequest.create();
        tencentLocationRequest.setRequestLevel(3);
        locationManager = TencentLocationManager.getInstance(this);
        int errorCode = locationManager.requestLocationUpdates(tencentLocationRequest, this);
        final FragmentManager fragmentManager = getSupportFragmentManager();

        final List<Fragment> fragmentList = new ArrayList<>();
        handler = new Handler() {
            @SuppressLint("RestrictedApi")
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case FLAG_LOCATION_CITY:
                        cityText.setText(msg.obj.toString());
                        fragmentList.add(WeatherFragmentLocation.newInstance(msg.obj.toString()));
                        fragmentList.add(WeatherFragmentLocation.newInstance("北京市"));
                        myFragmentPagerAdapter = new MyFragmentPagerAdapter(fragmentManager,fragmentList);
                        viewPager.setAdapter(myFragmentPagerAdapter);
                        Toast.makeText(getApplicationContext(), "定位成功",Toast.LENGTH_SHORT).show();
                        break;
                    case FLAG_ADD_NEW_CITY:
                        fragmentList.add(WeatherFragmentLocation.newInstance(msg.obj.toString()));
                        myFragmentPagerAdapter.notifyDataSetChanged();
                        viewPager.setCurrentItem(fragmentList.size()-1);
                        Toast.makeText(getApplicationContext(), "添加城市成功",Toast.LENGTH_SHORT).show();
                }
            }
        };
    }

    private void bindView() {
        cityText = (TextView) findViewById(R.id.cityText);
        addBtn = (ImageButton)findViewById(R.id.btn_setting);
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
        CityPicker.with(this)
                .setUseGpsCity(false)
                .setMaxHistory(0)
                .setSearchViewTextSize(14)
                .setOnCityPickerCallBack(new IOnCityPickerCheckedCallBack() {
                    @Override
                    public void onCityPickerChecked(BaseCity baseCity) {
                        handler.obtainMessage(FLAG_ADD_NEW_CITY,baseCity.getCityName()).sendToTarget();
                    }
                })
                .open();
    }
    @Override
    public void onLocationChanged(TencentLocation tencentLocation, int i, String s) {
        if (TencentLocation.ERROR_OK == i) {
            String city = tencentLocation.getCity();
            Log.d(TAG, "onLocationChanged: "+city);
            handler.obtainMessage(FLAG_LOCATION_CITY, city).sendToTarget();
            locationManager.removeUpdates(this);
        }
    }
    @Override
    public void onStatusUpdate(String s, int i, String s1) {

    }
    @Override
    public void onFragmentInteraction(String s) {
        cityText.setText(s);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler = null;
    }

}
