package com.example.automobile.constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TinyDB {
    private Context context;
    private SharedPreferences preferences;

    public TinyDB(Context appContext) {
        preferences = PreferenceManager.getDefaultSharedPreferences(appContext);
        context = appContext;
    }

    public int getInt(String key) {
        return preferences.getInt(key, 0);
    }

    public double getDouble(String key) {
        String number = getString(key);

        try {
            return Double.parseDouble(number);

        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public String getString(String key) {
        return preferences.getString(key, "");
    }

    public void putInt(String key, int value) {
        checkForNullKey(key);
        preferences.edit().putInt(key, value).apply();
    }

    public void putDouble(String key, double value) {
        checkForNullKey(key);
        putString(key, String.valueOf(value));
    }

    public void putString(String key, String value) {
        checkForNullKey(key);
        checkForNullValue(value);
        preferences.edit().putString(key, value).apply();
    }

    public void clear() {
        preferences.edit().clear().apply();
    }

    private void checkForNullKey(String key) {
        if (key == null) {
            throw new NullPointerException();
        }
    }

    private void checkForNullValue(String value) {
        if (value == null) {
            throw new NullPointerException();
        }
    }
}
