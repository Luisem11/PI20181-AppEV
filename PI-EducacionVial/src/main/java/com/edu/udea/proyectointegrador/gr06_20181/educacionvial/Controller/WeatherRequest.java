package com.edu.udea.proyectointegrador.gr06_20181.educacionvial.Controller;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class WeatherRequest {

    public static String API_KEY = "6da9d4a06292f5506cfa67f8e9b6a7b3";
    public static String API_LINK = "http://api.openweathermap.org/data/2.5/weather";
    private final String TAG = "WeatherRequest";
    private String host;
    private String request;
    private String params;
    private String url;

    // Get your own user name at http://www.geonames.org/login
    //private final String USER_NAME = "aporter";
    //private final String URL = "http://api.geonames.org/earthquakesJSON?north=20&south=-20&east=-60&west=-80&username=" + USER_NAME;

    public WeatherRequest(String host, String request, String params){
        this.host = host;
        this.request = request;
        this.params = params;
        this.url = host + request + params;
    }

    public WeatherRequest(String lat, String lng){
        StringBuilder sb = new StringBuilder(API_LINK);
        sb.append(String.format("?lat=%s&lon=%s&APPID=%s&units=metric",lat,lng,API_KEY));
        this.url = sb.toString();
    }



    public WeatherRequest(String url){
        this.url = url;
    }

    public String httpGet() {

        String data = "";
        HttpURLConnection httpUrlConnection = null;

        try {
            httpUrlConnection = (HttpURLConnection) new URL(url).openConnection();

            InputStream in = new BufferedInputStream(
                    httpUrlConnection.getInputStream());

            data = readStream(in);

        } catch (MalformedURLException exception) {
            Log.e(TAG, "MalformedURLException");
        } catch (IOException exception) {
            Log.e(TAG, "IOException");
        } finally {
            if (null != httpUrlConnection)
                httpUrlConnection.disconnect();
        }
        return data;

    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer data = new StringBuffer("");
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                data.append(line);
            }
        } catch (IOException e) {
            Log.e(TAG, "IOException");
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return data.toString();
    }
}