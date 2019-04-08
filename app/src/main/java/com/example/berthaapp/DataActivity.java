package com.example.berthaapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.view.GestureDetector;
import android.view.MotionEvent;

import android.widget.Toast;
public class DataActivity extends AppCompatActivity implements GestureDetector.OnGestureListener {
    public static final String DATA="DATA";
    private Data data;
    private TextView deviceIdView,pm25View,pm10View,co2View
            ,o3View,pressureView
            ,temperatureView,humidityView,utcView
            ,latitudeView,longitudeView,noiseView,userIdView;
    private static final String TAG="Gestures";
    private GestureDetector gestureDetector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
//        ActionBar actionBar=getSupportActionBar();
//        actionBar.setDisplayHomeAsUpEnabled(true);

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
        gestureDetector=new GestureDetector(this,this);
    }
    public void back(View view) {
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent motionEvent){
        Log.d(TAG, "onTouch: " + motionEvent);
        boolean eventHandling=true;
        return gestureDetector.onTouchEvent(motionEvent);
    }
    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return true;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return true;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        Toast.makeText(this, "onFling", Toast.LENGTH_SHORT).show();
        Log.d(TAG, "onFling " + motionEvent.toString() + "::::" + motionEvent1.toString());
        boolean leftSwip = motionEvent.getX() < motionEvent1.getX();
        Log.d(TAG, "onFling left: " + leftSwip);
        boolean rightSwip = motionEvent.getX() > motionEvent1.getX();
        Log.d(TAG, "onFling right: " + rightSwip);
        if (leftSwip) {



        } else if (rightSwip) {
            Intent intent = new Intent(this, DataListView.class);
            startActivity(intent);
        }

        return true;
    }
}
