package com.mht.stock.storage;

import android.content.Context;
import android.content.SharedPreferences;

import com.mht.stock.util.JSONUtils;

public class Preferences {

    private static Preferences sPreferences;
    private SharedPreferences mPreferences;

    public static Preferences instance() {
        return sPreferences;
    }

    public static void init(Context context) {
        sPreferences = new Preferences();
        sPreferences.mPreferences = context.getSharedPreferences("preferences", Context.MODE_PRIVATE);
    }




    public String getString(String key) {
        return mPreferences.getString(key, null);
    }

    public String getString(String key, String defValue) {
        return mPreferences.getString(key, defValue);
    }

    public int getInt(String key) {
        return mPreferences.getInt(key, 0);
    }

    public int getInt(String key, int defValue) {
        return mPreferences.getInt(key, defValue);
    }

    public float getFloat(String key, float defValue) {
        return mPreferences.getFloat(key, defValue);
    }

    public long getLong(String key, long defValue) {
        return mPreferences.getLong(key, defValue);
    }

    public boolean getBoolean(String key) {
        return mPreferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defValue) {
        return mPreferences.getBoolean(key, defValue);
    }

    public <T> T getObject(String key, Class<T> classOfT) {
        String value = getString(key);
        return JSONUtils.fromJSON(value, classOfT);
    }




    public void set(String key, String value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    public void set(String key, int value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
    }

    public void set(String key, float value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putFloat(key, value);
        editor.commit();
    }

    public void set(String key, long value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    public void set(String key, boolean value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    public void set(String key, Object value) {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.putString(key, JSONUtils.toJSON(value));
        editor.commit();
    }


    public void remove(String... key) {
        SharedPreferences.Editor editor = mPreferences.edit();
        for (String k : key)
            editor.remove(k);
        editor.commit();
    }

    public void clear() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
