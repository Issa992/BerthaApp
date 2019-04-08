package com.example.berthaapp;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class AddData extends AppCompatActivity {
//https://berthawristbandrestprovider.azurewebsites.net/api/wristbanddata
public static final String DATA="DATA";


    private Data data;
    public DataWristBand dataWristBand;
    public ReadTaskWristBand readTaskWristBand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_data);
    }
    @Override
    protected void onStart(){
        super.onStart();
        ReadTaskWristBand task=new ReadTaskWristBand();
        task.execute("https://berthawristbandrestprovider.azurewebsites.net/api/wristbanddata");
    }

    public void addata(View view) {
        Intent intent =getIntent();
        data=(Data)intent.getSerializableExtra(DATA);
      //  Log.d("datae",data.toString());
       //  String deviceIdString = ((EditText) findViewById(R.id.data_deviceId_edittext)).getText().toString();
        /*
       int deviceId = dataWristBand.getDeviceId();

        String pm25String = ((EditText) findViewById(R.id.data_pm25_edittext)).getText().toString();
        double pm25 = Double.parseDouble(pm25String);

        String pm10String = ((EditText) findViewById(R.id.data_pm10_edittext)).getText().toString();
        double pm10 = Double.parseDouble(pm10String);
        String co2String = ((EditText) findViewById(R.id.data_co2_edittext)).getText().toString();
        double co2 = Double.parseDouble(co2String);
        String o3String = ((EditText) findViewById(R.id.data_o3_edittext)).getText().toString();
        double o3 = Double.parseDouble(o3String);
        String pressureString = ((EditText) findViewById(R.id.data_pressure_edittext)).getText().toString();
        double pressure = Double.parseDouble(pressureString);
        String temperatureString = ((EditText) findViewById(R.id.data_temperature_edittext)).getText().toString();
        double temperature = Double.parseDouble(temperatureString);
        String humidityString = ((EditText) findViewById(R.id.data_humidity_edittext)).getText().toString();
        double humidity = Double.parseDouble(humidityString);
        String utcString = ((EditText) findViewById(R.id.data_utc_edittext)).getText().toString();
        double utc = Double.parseDouble(utcString);
        String latitudeString = ((EditText) findViewById(R.id.data_latitude_edittext)).getText().toString();
        double latitude = Double.parseDouble(latitudeString);
        String longitudeString = ((EditText) findViewById(R.id.data_longitude_edittext)).getText().toString();
        double longitude = Double.parseDouble(longitudeString);
        String noiseString = ((EditText) findViewById(R.id.data_noise_edittext)).getText().toString();
        double noise = Double.parseDouble(noiseString);
        String userId = ((EditText) findViewById(R.id.Data_userId_edittext)).getText().toString();
        TextView messageView = findViewById(R.id.main_message_textview);
        try {
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("deviceId",  deviceId);
//            Log.d("Di",String.valueOf(dataWristBand.getDeviceId()));
            jsonObject.put("pm25", pm25);
            jsonObject.put("pm10", pm10);
            jsonObject.put("co2", co2);
            jsonObject.put("o3", o3);
            jsonObject.put("temperature", temperature);
            jsonObject.put("humidity", humidity);
            jsonObject.put("pressure", pressure);
            jsonObject.put("utc", utc);
            jsonObject.put("latitude", latitude);
            jsonObject.put("longitude", longitude);
            jsonObject.put("noise", noise);
            jsonObject.put("userId", userId);


            String jsonDocument = jsonObject.toString();
            Log.d("json", jsonDocument.toString());
            //  messageView.setText(jsonDocument);
            PostDataTask task = new PostDataTask();
            task.execute("https://berthabackendrestprovider.azurewebsites.net/api/data", jsonDocument);
            finish();


        } catch (JSONException ex) {
            messageView.setText(ex.getMessage());
        }
    */
    }

    private class PostDataTask extends AsyncTask<String, Void, CharSequence> {
        protected CharSequence doInBackground(String... params) {
            String urlString = params[0];
            String jsonDocument = params[1];
            Log.d("para", params.toString());
            try {
                URL url = new URL(urlString);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setRequestProperty("Accept", "application/json");
                OutputStreamWriter osw = new OutputStreamWriter(connection.getOutputStream());
                osw.write(jsonDocument);
                Log.d("js", jsonDocument);
                osw.flush();
                osw.close();
                int responseCode = connection.getResponseCode();
                Log.d("res", String.valueOf(responseCode));
                if (responseCode / 100 != 2) {
                    String responseMessage = connection.getResponseMessage();
                    throw new IOException("HTTP response code: " + responseCode + " " + responseMessage);
                }
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String line = reader.readLine();
                return line;
            } catch (MalformedURLException ex) {
                cancel(true);
                String message = ex.getMessage() + " " + urlString;
                Log.e("DATA", message);
                return message;
            } catch (IOException ex) {
                cancel(true);
                Log.e("DATA", ex.getMessage());
                return ex.getMessage();
            }
        }

        @Override
        protected void onPostExecute(CharSequence charSequence) {
            super.onPostExecute(charSequence);
            TextView messageView = findViewById(R.id.add_data_message_textview);
            messageView.setText(charSequence);
            Log.d("MINE", charSequence.toString());
            finish();
        }

        @Override
        protected void onCancelled(CharSequence charSequence) {
            super.onCancelled(charSequence);
            TextView messageView = findViewById(R.id.add_data_message_textview);
            messageView.setText(charSequence);
            finish();
        }
    }

    private class ReadTaskWristBand extends ReadHttpTask {
        @Override
        public void onPostExecute(CharSequence jsonString) {
            Log.d("ReadWrist", jsonString.toString());
            GsonBuilder gsonBuilder=new GsonBuilder();
            Gson  gson=gsonBuilder.create();
            final DataWristBand datas = gson.fromJson(jsonString.toString(), DataWristBand.class);
            JSONObject jsonObject= null;
            try {
                jsonObject = new JSONObject(jsonString.toString());
                jsonObject.put("utc",new Date().getTime());
                jsonObject.put("latitude", "33");
                jsonObject.put("longitude", "33");
                jsonObject.put("noise", "0");
                jsonObject.put("userId", "anbo");
                Log.d("JO",jsonObject.toString());

                String jsonDocument = jsonObject.toString();
                Log.d("jrson", jsonDocument.toString());
                //  messageView.setText(jsonDocument);
                PostDataTask task = new PostDataTask();
                task.execute("https://berthabackendrestprovider.azurewebsites.net/api/data", jsonDocument);
                finish();


            } catch (JSONException e) {
                e.printStackTrace();
            }


            /*    jsonObject.put("utc", utc);
                jsonObject.put("latitude", latitude);
                jsonObject.put("longitude", longitude);
                jsonObject.put("noise", noise);
                jsonObject.put("userId", userId);
                Log.d("jO",jsonObject.toString());
                */

            //GsonBuilder gsonBuilder=new GsonBuilder();
            //Gson  gson=gsonBuilder.create();
            //final DataWristBand datas = gson.fromJson(jsonString.toString(), DataWristBand.class);
            //Log.d("dtr",String.valueOf(datas.getDeviceId()));
          //  String json=gson.toJson(datas);
          //  DataWristBand dModel=new DataWristBand();
            //dModel.setDeviceId(datas.getDeviceId());
        }

    }
}

