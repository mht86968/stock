package com.mht.stock.storage.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.content.SharedPreferencesCompat;

import com.mht.stock.util.JSONUtils;

public class PreferencesUtils {

    private SharedPreferences mPreferences;

    public PreferencesUtils(Context context, String name) {
        mPreferences = context.getSharedPreferences(name, Context.MODE_PRIVATE);
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
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }

    public void clear() {
        SharedPreferences.Editor editor = mPreferences.edit();
        editor.clear();
        SharedPreferencesCompat.EditorCompat.getInstance().apply(editor);
    }
}
