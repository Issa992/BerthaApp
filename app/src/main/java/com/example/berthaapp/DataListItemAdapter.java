package com.example.berthaapp;


import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class DataListItemAdapter extends ArrayAdapter<Data> {
    private final int resource;

    public DataListItemAdapter(Context context, int resource, List<Data> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    public DataListItemAdapter(Context context, int resource, Data[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override

    public View getView(int positon, View convertView, @NonNull ViewGroup parent) {
        Data data=getItem(positon);
        int deviceId=data.getDeviceId();
        String userId=data.getUserId();

        double pm25=data.getPm25();
        //double pm10=data.getPm10();
       // double co2=data.getCo2();
        //double o3=data.getO3();
        //double humidity=data.getHumidity();
     //   double latitude= data.getLatitude();
       // double longitude= data.getLongitude();
       // double noise=data.getNoise();
        //double pressure=data.getPressure();
        //double temperature=data.getTemperature();
        //double utc=data.getUtc();

        LinearLayout dataView;
        if (convertView==null){
            dataView=new LinearLayout(getContext());
            String inflater=Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li=(LayoutInflater)getContext().getSystemService(inflater);
            li.inflate(resource,dataView,true);
        }
        else {
            dataView=(LinearLayout)convertView;
        }
        TextView userView=dataView.findViewById(R.id.datalist_item_userId);
        TextView deviceIdView=dataView.findViewById(R.id.datalist_item_deviceId);
        userView.setText("  By  "+userId);
        deviceIdView.setText("Device id:  "+String.valueOf(deviceId)+".");
        return dataView;

    }


}
