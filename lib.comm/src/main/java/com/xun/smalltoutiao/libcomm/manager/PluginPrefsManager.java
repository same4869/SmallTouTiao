package com.xun.smalltoutiao.libcomm.manager;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by xunwang on 2017/11/20.
 */

public class PluginPrefsManager {
    private static SharedPreferences getSharedPreferences(Context context) {
        if (context != null) {
            return context.getSharedPreferences("local", Context.MODE_PRIVATE);
        }
        return null;
    }

    protected static int getInt(Context context, String key, int def) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs != null ? prefs.getInt(key, def) : -1;
    }

    protected static long getLong(Context context, String key, long def) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs != null ? prefs.getLong(key, def) : -1;
    }

    protected static String getString(Context context, String key, String def) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs != null ? prefs.getString(key, def) : null;
    }

    protected static boolean getBoolean(Context context, String key, boolean def) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs != null ? prefs.getBoolean(key, def) : false;
    }

    protected static float getFloat(Context context, String key, float def) {
        SharedPreferences prefs = getSharedPreferences(context);
        return prefs != null ? prefs.getFloat(key, def) : -1;
    }

    protected static void putInt(Context context, String key, int value) {
        SharedPreferences prefs = getSharedPreferences(context);
        if (prefs != null) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putInt(key, value);
            editor.apply();
        }
    }

    protected static void putLong(Context context, String key, long value) {
        SharedPreferences prefs = getSharedPreferences(context);
        if (prefs != null) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putLong(key, value);
            editor.apply();
        }
    }

    protected static void putString(Context context, String key, String value) {
        SharedPreferences prefs = getSharedPreferences(context);
        if (prefs != null) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putString(key, value);
            editor.apply();
        }
    }

    protected static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences prefs = getSharedPreferences(context);
        if (prefs != null) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }
    }

    protected static void putFloat(Context context, String key, float value) {
        SharedPreferences prefs = getSharedPreferences(context);
        if (prefs != null) {
            SharedPreferences.Editor editor = prefs.edit();
            editor.putFloat(key, value);
            editor.apply();
        }
    }
}
