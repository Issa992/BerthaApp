package com.example.berthaapp;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Data implements Serializable {
  //  deviceId	1616384779
   // pm25	6.4
   // pm10	40.7
    //co2	454
    //o3	273012
    //pressure	1029.77
   // temperature	37.6
   // humidity	52
   // utc	1551789113484
   // latitude	55.6312758
   // longitude	12.078147
    //noise	0
    //userId	"anbo"
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
    @SerializedName("utc")
    private double utc;
    @SerializedName("latitude")
    private double latitude;
    @SerializedName("longitude")
    private double longitude;
    @SerializedName("noise")
    private double noise;
    @SerializedName("userId")
    private String userId;

    public Data(){
    }

    public Data(int deviceId,double pm25,double pm10,double co2,double o3,double pressure,
                double temperature,double humidity,double utc,double latitude,
                double longitude,double noise, String UserId){
        this.deviceId=deviceId;
        this.pm25=pm25;
        this.pm10=pm10;
        this.co2=co2;
        this.o3=o3;
        this.pressure=pressure;
        this.temperature=temperature;
        this.humidity=humidity;
        this.utc=utc;
        this.latitude=latitude;
        this.longitude=longitude;
        this.noise=noise;
        this.userId=UserId;
    }
    public void setDeviceId(int deviceId){this.deviceId=deviceId;}
    public void setPm25(double pm25){this.pm25=pm25;}
    public void setPm10(double pm10){this.pm10=pm10;}
    public void setCo2(double co2){this.co2=co2;}
    public void setO3(double o3){this.o3=o3;}
    public void setPressure(double pressure){this.pressure=pressure;}
    public void setTemperature(double temperature){this.temperature=temperature;}
    public void setHumidity(double humidity){this.humidity=humidity;}
    public void setUtc(double utc){this.utc=utc;}
    public void setLatitude(double latitude){this.latitude=latitude;}
    public void setLongitude(double longitude){this.longitude=longitude;}
    public void setNoise(double noise){this.noise=noise;}
    public void setUserId(String userId){this.userId=userId;}

    public int getDeviceId(){return deviceId;}
    public double getPm25 (){return pm25;}
    public double getPm10(){return pm10;}
    public double getCo2(){return co2;}
    public double getO3(){return o3;}
    public double getPressure(){return pressure;}
    public double getTemperature(){return temperature;}
    public double getHumidity(){return humidity;}
    public double getUtc(){return utc;}
    public double getLatitude(){return latitude;}
    public double getLongitude(){return longitude;}
    public double getNoise(){return noise;}
    public String getUserId(){
      return userId;}

    @Override
    public String toString(){return userId;}



}
