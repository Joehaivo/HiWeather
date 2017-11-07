package com.joehaivo.hweather.View;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.desmond.citypicker.bean.BaseCity;
import com.desmond.citypicker.bin.CityPicker;
import com.desmond.citypicker.callback.IOnCityPickerCheckedCallBack;
import com.joehaivo.hweather.Presenter.MyFragmentPagerAdapter;
import com.joehaivo.hweather.R;

public class MainActivity extends FragmentActivity implements WeatherFragment.OnFragmentInteractionListener, WeatherFragmentLocation.OnFragmentInteractionListener {
    TextView tmpText, sunText, tvAirNum, cityText, tv24hrDescription;
    ImageButton addBtn;
    Handler handler;
    ViewPager viewPager;
    MyFragmentPagerAdapter mViewMyFragmentPagerAdapter;
    private static final int FLAG_CITY_NAME = 8,FLAG_FRAGMENT_SELF = 9;
    WeatherFragmentLocation weatherFragmentLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTranslucentStatus(true);
        bindView();
        mViewMyFragmentPagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(mViewMyFragmentPagerAdapter);
        viewPager.setOffscreenPageLimit(2);

        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case FLAG_CITY_NAME:
                        cityText.setText(msg.obj.toString());
                        weatherFragmentLocation = (WeatherFragmentLocation) getSupportFragmentManager().findFragmentById(msg.arg1);
                        Log.d("T", "handleMessage: "+msg.obj.toString());
                        weatherFragmentLocation.refresh(msg.obj.toString());
                        break;
                    case FLAG_FRAGMENT_SELF:
                        weatherFragmentLocation = (WeatherFragmentLocation) getSupportFragmentManager().findFragmentById(msg.arg1);
                        Log.d("T", "handleMessage: "+msg.obj.toString());
                        weatherFragmentLocation.refresh(msg.obj.toString());
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
                        handler.obtainMessage(FLAG_CITY_NAME,baseCity.getCityName()).sendToTarget();
                    }
                })
                .open();
    }
    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
