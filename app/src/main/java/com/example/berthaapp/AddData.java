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
                jsonObject.put("userId", "issa");
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


        }

    }
}

