package com.weather.xiaota.xiaotaweather.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by gaohuang on 2016/8/13.
 */
public class WeatherOpenHelper extends SQLiteOpenHelper {

    // 表名
    public static final String PROVINCE_TABLE       = "Province";
    public static final String CITY_TABLE           = "City";
    public static final String COUNTY_TABLE         = "County";

    // 字段名
    public static final String ID                   = "id";
    public static final String PROVINCE_NAME        = "province_name";
    public static final String PROVINCE_CODE        = "province_code";
    public static final String CITY_NAME            = "city_name";
    public static final String CITY_CODE            = "city_code";
    public static final String CITY_PROVINCE_ID     = "province_id";
    public static final String COUNTY_NAME          = "county_name";
    public static final String COUNTY_CODE          = "county_code";
    public static final String COUNTY_CITY_ID       = "city_id";

    // 建表语句
    private static final String CREATE_PROVICE = "create table " + WeatherOpenHelper.PROVINCE_TABLE + "(" +
            WeatherOpenHelper.ID + " integer primary key autoincrement," +
            WeatherOpenHelper.PROVINCE_NAME + " text," +
            WeatherOpenHelper.PROVINCE_CODE + " text)";

    private static final String CREATE_CITY = "crate table " + WeatherOpenHelper.CITY_TABLE + "(" +
            WeatherOpenHelper.ID + " integer primary key autoincrement," +
            WeatherOpenHelper.CITY_NAME + " text," +
            WeatherOpenHelper.CITY_CODE + " text," +
            WeatherOpenHelper.CITY_PROVINCE_ID + " integer)";

    private static final String CREATE_COUNTY = "create table " + WeatherOpenHelper.COUNTY_TABLE + "(" +
            WeatherOpenHelper.ID + " integer primary key autoincrement," +
            WeatherOpenHelper.COUNTY_NAME + " text," +
            WeatherOpenHelper.COUNTY_CODE + " text," +
            WeatherOpenHelper.COUNTY_CITY_ID + " integer)";

    public WeatherOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_PROVICE);
        db.execSQL(CREATE_CITY);
        db.execSQL(CREATE_COUNTY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
