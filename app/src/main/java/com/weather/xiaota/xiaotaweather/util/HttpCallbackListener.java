package com.weather.xiaota.xiaotaweather.util;

/**
 * Created by gaohuang on 2016/8/13.
 */
public interface HttpCallbackListener {
    public void onFinish(String content);
    public void onError(Exception e);
}
