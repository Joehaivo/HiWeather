package com.joehaivo.hweather.View;


import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.joehaivo.hweather.Presenter.Forecast;
import com.joehaivo.hweather.Presenter.MyFragmentPagerAdapter;
import com.joehaivo.hweather.Presenter.Recycler24hrAdapter;
import com.joehaivo.hweather.Presenter.Recycler7DayAdapter;
import com.joehaivo.hweather.R;
import com.tencent.map.geolocation.TencentLocation;
import com.tencent.map.geolocation.TencentLocationListener;
import com.tencent.map.geolocation.TencentLocationManager;
import com.tencent.map.geolocation.TencentLocationRequest;

import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;


public class WeatherFragmentLocation extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    String TAG = "TAG";
    Handler mHandler;
    ImageView imageComfort, imageAir, imageCloth;
    TextView tmpText, sunText, tvAirNum, tvComfortTitle, tvComfort, tvAirTitle, tvAir, tvClothTitle, tvCloth;
    RecyclerView forecastRecyclerView, rv24hrFore;
    SwipeRefreshLayout swipeRefreshLayout;
    Recycler7DayAdapter recycler7DayAdapter;
    Recycler24hrAdapter recycler24hrAdapter;
    Forecast forecast;
    private String mParam1;
    private String mParam2;
    private String mCity;
    private OnFragmentInteractionListener mListener;
    boolean isCreated = false,isCreatedView = false;
    public WeatherFragmentLocation() {
    }

    public static WeatherFragmentLocation newInstance(String city) {
        WeatherFragmentLocation fragment = new WeatherFragmentLocation();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM2, city);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isCreatedView&&isCreated&&isVisibleToUser){
            mListener.onFragmentInteraction(getArguments().getString(ARG_PARAM2));
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
        isCreated = true;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View layout = inflater.inflate(R.layout.fragment_weather_location, container, false);
        tmpText = (TextView) layout.findViewById(R.id.tmpText);
        tvAirNum = (TextView) layout.findViewById(R.id.air_num);
        sunText = (TextView) layout.findViewById(R.id.sunnyText);
        imageComfort = (ImageView) layout.findViewById(R.id.imageVComfort);
        tvComfortTitle = (TextView) layout.findViewById(R.id.textComfortTitle);
        tvComfort = (TextView) layout.findViewById(R.id.textComfort);
        imageAir = (ImageView) layout.findViewById(R.id.imageAir);
        tvAirTitle = (TextView) layout.findViewById(R.id.textAirTitle);
        tvAir = (TextView) layout.findViewById(R.id.textAir);
        imageCloth = (ImageView) layout.findViewById(R.id.imageCloth);
        tvClothTitle = (TextView) layout.findViewById(R.id.textClothTitle);
        tvCloth = (TextView) layout.findViewById(R.id.textCloth);
        forecastRecyclerView = (RecyclerView) layout.findViewById(R.id.recyclerView);
        rv24hrFore = (RecyclerView) layout.findViewById(R.id.recyclerView24hr);
        swipeRefreshLayout = (SwipeRefreshLayout) layout.findViewById(R.id.swiperefresh);
        isCreatedView = true;
        return layout;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        mHandler = new Handler() {
            private static final int
                    FLAG_NOW_CONDITION = 1,  TAG_NOW_TMP = 2, FLAG_FORE_HOURLY = 3,
                    FLAG_HEFENG_AQI = 4, FLAG_FORE_DAILY = 5, FLAG_LOCATION_CITY = 10,
                    FLAG_LIFE_INDEX = 7;
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                switch (msg.what) {
                    case TAG_NOW_TMP:
                        tmpText.setText(msg.obj + "°");
                        break;
                    case FLAG_NOW_CONDITION:
                        sunText.setText(msg.obj.toString());
                        break;
                    case FLAG_FORE_DAILY:
                        init7DayRecycler((List<Map<String, Object>>) msg.obj);
                        break;
                    case FLAG_FORE_HOURLY:
                        init24hrRecycler((List<Map<String, Object>>) msg.obj);
                        break;
                    case FLAG_LIFE_INDEX:
                        List<String> lifeList = (List<String>) msg.obj;
                        tvComfortTitle.setText("舒适度 " + lifeList.get(0).toString());
                        tvComfort.setText(lifeList.get(1).toString());
                        tvAirTitle.setText("空气质量 " + lifeList.get(2).toString());
                        tvAir.setText(lifeList.get(3).toString());
                        tvClothTitle.setText("穿衣指数 " + lifeList.get(4).toString());
                        tvCloth.setText(lifeList.get(5).toString());
                        break;
                    case FLAG_HEFENG_AQI:
                        tvAirNum.setText("空气 " + msg.obj.toString());
                        break;
                }
            }
        };
        forecast = new Forecast(this, mHandler);
        forecast.getHefengWeather(getArguments().getString(ARG_PARAM2));
        forecast.getHefengAQI(getArguments().getString(ARG_PARAM2));
    }

    @Override
    public void onResume() {
        super.onResume();


    }
    
    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onRefresh() {
        if (swipeRefreshLayout.isRefreshing()){
            forecast.getHefengWeather(((MainActivity)getActivity()).cityText.getText().toString());
            forecast.getHefengAQI(((MainActivity)getActivity()).cityText.getText().toString());
            swipeRefreshLayout.setRefreshing(false);
        }
    }
    public void refresh(String city){
        forecast.getHefengWeather(city);
        forecast.getHefengAQI(city);
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(String s);
    }

    private void init7DayRecycler(List<Map<String, Object>> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        forecastRecyclerView.setLayoutManager(layoutManager);
        recycler7DayAdapter = new Recycler7DayAdapter(getContext());
        recycler7DayAdapter.initData(dataList);
        forecastRecyclerView.setAdapter(recycler7DayAdapter);
    }

    private void init24hrRecycler(List<Map<String, Object>> dataList) {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        rv24hrFore.setLayoutManager(layoutManager);
        recycler24hrAdapter = new Recycler24hrAdapter(getActivity(), dataList);
        rv24hrFore.setAdapter(recycler24hrAdapter);
    }
}
