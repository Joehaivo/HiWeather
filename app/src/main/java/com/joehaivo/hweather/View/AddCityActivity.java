package com.joehaivo.hweather.View;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;

import com.desmond.citypicker.bin.CityPicker;
import com.joehaivo.hweather.R;

public class AddCityActivity extends AppCompatActivity {
    SearchView searchCityView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_city);

        CityPicker.with(this)
                .setUseGpsCity(true)
        .setMaxHistory(6)
        .open();
    }
}
