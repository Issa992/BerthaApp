package com.example.berthaapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DataActivity extends AppCompatActivity {
    public static final String DATA="DATA";
    private Data data;
    private TextView deviceIdView,pm25View,pm10View,co2View
            ,o3View,pressureView
            ,temperatureView,humidityView,utcView
            ,latitudeView,longitudeView,noiseView,userIdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent=getIntent();
        data=(Data)intent.getSerializableExtra(DATA);

        TextView headingView=findViewById(R.id.data_heading_textview);
        //humidityView.setText("Device Id"+String.valueOf(data.getDeviceId()));

        deviceIdView=findViewById(R.id.data_deviceId_edittext);
        deviceIdView.setText(String.valueOf(data.getDeviceId()));

        pm25View=findViewById(R.id.data_pm25_edittext);
        pm25View.setText(String.valueOf(data.getPm25()));

        pm10View=findViewById(R.id.data_pm10_edittext);
        pm10View.setText(String.valueOf(data.getPm10()));

        co2View=findViewById(R.id.data_co2_edittext);
        co2View.setText(String.valueOf(data.getCo2()));

        o3View=findViewById(R.id.data_o3_edittext);
        o3View.setText(String.valueOf(data.getO3()));

        pressureView=findViewById(R.id.data_pressure_edittext);
        pressureView.setText(String.valueOf(data.getPressure()));

        temperatureView=findViewById(R.id.data_temperature_edittext);
        temperatureView.setText(String.valueOf(data.getTemperature()));


        humidityView=findViewById(R.id.data_humidity_edittext);
        humidityView.setText(String.valueOf(data.getHumidity()));

        utcView=findViewById(R.id.data_utc_edittext);
        utcView.setText(String.valueOf(data.getUtc()));

        latitudeView=findViewById(R.id.data_latitude_edittext);
        latitudeView.setText(String.valueOf(data.getLatitude()));

        longitudeView=findViewById(R.id.data_longitude_edittext);
        longitudeView.setText(String.valueOf(data.getLongitude()));

        noiseView=findViewById(R.id.data_noise_edittext);
        noiseView.setText(String.valueOf(data.getNoise()));



    }
    public void back(View view) {
        finish();
    }
}
