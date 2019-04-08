package com.example.berthaapp;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DataWristBand implements Serializable {
    @SerializedName("deviceId")
    private int deviceId;
    @SerializedName("pm25")
    private double pm25;
    @SerializedName("pm10")
    private double pm10;
    @SerializedName("co2")
    private double co2;
    @SerializedName("o3")
    private double o3;
    @SerializedName("pressure")
    private double pressure;
    @SerializedName("temperature")
    private double temperature;
    @SerializedName("humidity")
    private double humidity;


    public DataWristBand(){
    }
    public DataWristBand(int deviceId,double pm25,double pm10,double co2,double o3,double pressure,
                double temperature,double humidity){
        this.deviceId=deviceId;
        this.pm25=pm25;
        this.pm10=pm10;
        this.co2=co2;
        this.o3=o3;
        this.pressure=pressure;
        this.temperature=temperature;
        this.humidity=humidity;

    }

    public int setDeviceId(int deviceId){this.deviceId=deviceId;
    return deviceId;
    }
    public void setPm25(double pm25){this.pm25=pm25;}
    public void setPm10(double pm10){this.pm10=pm10;}
    public void setCo2(double co2){this.co2=co2;}
    public void setO3(double o3){this.o3=o3;}
    public void setPressure(double pressure){this.pressure=pressure;}
    public void setTemperature(double temperature){this.temperature=temperature;}
    public void setHumidity(double humidity){this.humidity=humidity;}


    public int getDeviceId(){return deviceId;}

    public double getPm25 (){return pm25;}
    public double getPm10(){return pm10;}
    public double getCo2(){return co2;}
    public double getO3(){return o3;}
    public double getPressure(){return pressure;}
    public double getTemperature(){return temperature;}
    public double getHumidity(){return humidity;}


}

