package com.xun.smalltoutiao.appmain.sp;

import android.content.Context;

import com.xun.smalltoutiao.libcomm.manager.PluginPrefsManager;

/**
 * Created by xunwang on 2017/11/20.
 */

public class SpMainManager extends PluginPrefsManager {
    private static final String PLUGIN_BUNDLES = "plugin_bundles";
    private static final String MANIFEST_CODE = "manifest_code";
    private static final String UPDATES_CODE = "updates_code";
    private static final String ADDITION_CODE = "additions_code";
    private static final String SMALL_UPDATE = "small_update";
    private static final String SMALL_ADD = "small_add";

    public static String getPluginBundles(Context context) {
        return getString(context, PLUGIN_BUNDLES, null);
    }

    public static void savePluginBundles(Context context, String b) {
        putString(context, PLUGIN_BUNDLES, b);
    }

    public static int getManifestCode(Context context) {
        return getInt(context, MANIFEST_CODE, 0);
    }

    public static void saveManifestCode(Context context, int b) {
        putInt(context, MANIFEST_CODE, b);
    }

    public static int getUpdatesCode(Context context) {
        return getInt(context, UPDATES_CODE, 0);
    }

    public static void saveUpdatesCode(Context context, int b) {
        putInt(context, UPDATES_CODE, b);
    }

    public static int getAdditionCode(Context context) {
        return getInt(context, ADDITION_CODE, 0);
    }

    public static void saveAdditionCode(Context context, int b) {
        putInt(context, ADDITION_CODE, b);
    }

    public static int getSmallUpdate(Context context) {
        return getInt(context, SMALL_UPDATE, 0);
    }

    public static void saveSmallUpdate(Context context, int b) {
        putInt(context, SMALL_UPDATE, b);
    }

    public static int getSmallAdd(Context context) {
        return getInt(context, SMALL_ADD, 0);
    }

    public static void saveSmallAdd(Context context, int b) {
        putInt(context, SMALL_ADD, b);
    }

}
