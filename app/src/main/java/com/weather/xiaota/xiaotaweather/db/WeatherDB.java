package com.weather.xiaota.xiaotaweather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.weather.xiaota.xiaotaweather.model.City;
import com.weather.xiaota.xiaotaweather.model.County;
import com.weather.xiaota.xiaotaweather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by gaohuang on 2016/8/13.
 * 封装数据库操作类
 */
public class WeatherDB {
    private static final String DB_NAME = "xiaota_weather";
    private static final int DB_VERSION = 1;

    private static WeatherDB instance;
    private SQLiteDatabase database;

    private WeatherDB(Context context){
        WeatherOpenHelper weatherOpenHelper = new WeatherOpenHelper(context, DB_NAME, null, DB_VERSION);
        database = weatherOpenHelper.getWritableDatabase();
    }

    // 单例模式
    public synchronized static WeatherDB getInstance(Context context){
        if(null == instance){
            instance = new WeatherDB(context);
        }

        return instance;
    }

    // 保存和读取省份信息
    public void saveProvince(Province province){
        if(null != province){
            ContentValues contentValue = new ContentValues();
            contentValue.put(WeatherOpenHelper.PROVINCE_NAME, province.getProvinceName());
            contentValue.put(WeatherOpenHelper.PROVINCE_CODE, province.getProvinceCode());
            database.insert(WeatherOpenHelper.PROVINCE_TABLE, null, contentValue);
        }
    }

    public List<Province> loadProvinces(){
        List<Province> provinces = new ArrayList<Province>();

        Cursor cursor = database.query(WeatherOpenHelper.PROVINCE_TABLE, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            Province province = new Province();
            province.setId(cursor.getInt(cursor.getColumnIndex(WeatherOpenHelper.ID)));
            province.setProvinceName(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.PROVINCE_NAME)));
            province.setProvinceCode(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.PROVINCE_CODE)));
            provinces.add(province);
        }

        return provinces;
    }

    // 保存和读取城市信息
    public void saveCity(City city){
        if(null != city){
            ContentValues contentValue = new ContentValues();
            contentValue.put(WeatherOpenHelper.CITY_NAME, city.getCityName());
            contentValue.put(WeatherOpenHelper.CITY_CODE, city.getCityCode());
            contentValue.put(WeatherOpenHelper.CITY_PROVINCE_ID, city.getProvinceId());
            database.insert(WeatherOpenHelper.CITY_TABLE, null, contentValue);
        }
    }

    public List<City> loadCitys(){
        List<City> list = new ArrayList<City>();

        Cursor cursor = database.query(WeatherOpenHelper.CITY_TABLE, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            City city = new City();
            city.setId(cursor.getInt(cursor.getColumnIndex(WeatherOpenHelper.ID)));
            city.setCityName(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.CITY_NAME)));
            city.setCityCode(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.CITY_CODE)));
            city.setProvinceId(cursor.getInt(cursor.getColumnIndex(WeatherOpenHelper.CITY_PROVINCE_ID)));
            list.add(city);
        }

        return list;
    }

    // 保存和读取县信息
    public void saveCounty(County county){
        if(null != county){
            ContentValues contentValue = new ContentValues();
            contentValue.put(WeatherOpenHelper.COUNTY_NAME, county.getCountyName());
            contentValue.put(WeatherOpenHelper.COUNTY_CODE, county.getCountyCode());
            contentValue.put(WeatherOpenHelper.COUNTY_CITY_ID, county.getCityId());
            database.insert(WeatherOpenHelper.COUNTY_TABLE, null, contentValue);
        }
    }

    public List<County> loadCountys(){
        List<County> list = new ArrayList<County>();

        Cursor cursor = database.query(WeatherOpenHelper.COUNTY_TABLE, null, null, null, null, null, null);

        while(cursor.moveToNext()){
            County county = new County();
            county.setId(cursor.getInt(cursor.getColumnIndex(WeatherOpenHelper.ID)));
            county.setCountyName(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.COUNTY_NAME)));
            county.setCountyCode(cursor.getString(cursor.getColumnIndex(WeatherOpenHelper.COUNTY_CODE)));
            county.setCityId(cursor.getInt(cursor.getColumnIndex(WeatherOpenHelper.COUNTY_CITY_ID)));
            list.add(county);
        }

        return list;
    }
}
