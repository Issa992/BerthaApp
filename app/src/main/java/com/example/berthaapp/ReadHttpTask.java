package com.example.berthaapp;
import android.util.Log;

import java.io.IOException;
import android.os.AsyncTask;

public class ReadHttpTask extends AsyncTask<String,Void,CharSequence> {
    @Override
    protected CharSequence doInBackground(String... urls){
        String urlString = urls[0];
        try {
            CharSequence result=HttpHelper.GetHttpResponse(urlString);
            return result;
        }catch (IOException ex){
            cancel(true);
            String errorMessage = ex.getMessage() + "\n" + urlString;
            Log.e("DATA", errorMessage);
            return errorMessage;
        }
    }

}
