package com.wudoumi.batter.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;

import com.wudoumi.batter.R;

import java.util.Map;
import java.util.Set;

/**
 * Created by Administrator on 2015/6/18 0018.
 */
public class PreferenceHelper {

    private SharedPreferences sp;

    private SharedPreferences.Editor editor;

    private static String errorMessage;


    private PreferenceHelper(Context context) {
        errorMessage = context.getString(R.string.none_init_in_application);
        sp = context.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
        editor = sp.edit();
    }


    private static PreferenceHelper instance;


    public static void init(Context context) {
        if (instance == null) {
            synchronized (PreferenceHelper.class) {
                if (instance == null) {
                    instance = new PreferenceHelper(context);
                }
            }
        }
    }

    public static PreferenceHelper getInstance() {
        if (instance == null) {
            throw new RuntimeException(errorMessage);
        }
        return instance;
    }


    public void put(String key, String value) {
        editor.putString(key, value);
        editor.commit();
    }

    public void put(String key, boolean value) {
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void put(String key, int value) {
        editor.putInt(key, value);
        editor.commit();
    }

    public void put(String key, float value) {
        editor.putFloat(key, value);
        editor.commit();
    }

    public void put(String key, long value) {
        editor.putLong(key, value);
        editor.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public void put(String key, Set<String> value) {
        editor.putStringSet(key, value);
        editor.commit();
    }


    public String getString(String key, String defaultValue) {
        return sp.getString(key, defaultValue);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return sp.getBoolean(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return sp.getFloat(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return sp.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return sp.getLong(key, defaultValue);
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    public Set<String> getStringSet(String key, Set<String> defaultValue) {
        return sp.getStringSet(key, defaultValue);
    }


    public Map<String, ?> getAll() {
        return sp.getAll();
    }


}
