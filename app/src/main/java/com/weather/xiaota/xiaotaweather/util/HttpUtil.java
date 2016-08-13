package com.weather.xiaota.xiaotaweather.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by gaohuang on 2016/8/13.
 */
public class HttpUtil {
    public static void sendHttpRequest(final String address, final HttpCallbackListener listener){
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;

                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection)url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

                    StringBuilder response = new StringBuilder();
                    String line;
                    while(null != (line = reader.readLine())){
                        response.append(line);
                    }

                    if(null != listener){
                        listener.onFinish(response.toString());
                    }
                }catch (Exception e){
                    if(null != listener){
                        listener.onError(e);
                    }
                }finally {
                    if(null != connection){
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
