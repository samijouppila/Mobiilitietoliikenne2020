package com.example.asynchttpapp;

import android.os.AsyncTask;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHTTPFetcher extends AsyncTask<String, Integer, String> {

    public interface ReporterInterface{
        void networkFetchDone(String data);
    }

    ReporterInterface callbackInterface;

    public void setCallbackInterface(ReporterInterface callbackInterface) {
        this.callbackInterface = callbackInterface;
    }

    @Override
    protected String doInBackground(String... addressStrings) {
        String data = loadFromWeb(addressStrings[0]);
        return data;
    }

    protected String loadFromWeb(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(connection.getInputStream());
            String htmlText = Utilities.fromStream(in);
            return htmlText;
        }
        catch (Exception e) {e.printStackTrace();}
        return null;
    }

    protected void onPostExecute(String data) {
        if (callbackInterface != null) {
            callbackInterface.networkFetchDone(data);
        }
    }
}
