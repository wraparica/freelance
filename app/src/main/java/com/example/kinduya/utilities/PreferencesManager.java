package com.example.kinduya.utilities;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.preference.PreferenceManager;


public class PreferencesManager {

    private static PreferencesManager instance;

    private SharedPreferences preferences;

    private PreferencesManager(Context context) {
        preferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static PreferencesManager getInstance(Context context) {
        if (instance == null) {
            instance = new PreferencesManager(context);
        }
        return instance;
    }

    public void putBoolean(String key, boolean value) {
        this.preferences.edit().putBoolean(key, value).apply();
    }

    public void putInt(String key, int value) {
        this.preferences.edit().putInt(key, value).apply();
    }

    public void putInt(String key, Integer value) {
        if (value == null) {
            remove(key);
        } else {
            this.preferences.edit().putInt(key, value).apply();
        }
    }

    public void putLong(String key, long value) {
        this.preferences.edit().putLong(key, value).apply();
    }

    public void putLong(String key, Long value) {
        if (value == null) {
            remove(key);
        } else {
            this.preferences.edit().putLong(key, value).apply();
        }
    }

    public void putFloat(String key, float value) {
        this.preferences.edit().putFloat(key, value).apply();
    }

    public void putString(String key, String value) {
        this.preferences.edit().putString(key, value).apply();
    }

    public void remove(String key) {
        this.preferences.edit().remove(key).apply();
    }

    public void clearPreferences() {
        this.preferences.edit().clear().apply();
    }

    public boolean getBoolean(String key) {
        return this.preferences.getBoolean(key, false);
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return this.preferences.getBoolean(key, defaultValue);
    }

    public int getInt(String key) {
        return this.preferences.getInt(key, -1);
    }

    public int getInt(String key, int defaultValue) {
        return this.preferences.getInt(key, defaultValue);
    }

    public long getLong(String key) {
        return this.preferences.getLong(key, -1);
    }

    public long getLong(String key, long defaultValue) {
        return this.preferences.getLong(key, defaultValue);
    }

    public float getFloat(String key) {
        return this.preferences.getFloat(key, -1);
    }

    public String getString(String key) {
        return getString(key, null);
    }

    public String getString(String key, String defaultValue) {
        return this.preferences.getString(key, defaultValue);
    }
}