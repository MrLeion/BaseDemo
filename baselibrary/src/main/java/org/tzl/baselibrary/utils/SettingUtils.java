package org.tzl.baselibrary.utils;

import android.content.Context;

/**
 * author: tangzenglei
 * created on: 2017/4/20 下午5:26
 * description:
 */
public class SettingUtils {

    public static final String SP_ONLY_WIFI_LOAD_IMG = "OnlyWifiLoadImg";




    private SettingUtils() {
        throw new UnsupportedOperationException("u can't instantiate me...");
    }

    /**
     * WIFI下加载大图
     */
    public static boolean getOnlyWifiLoadImg(Context ctx) {
        return SpUtils.getInstance().getBoolean(SP_ONLY_WIFI_LOAD_IMG, false);
    }

    public static void setOnlyWifiLoadImg(Context ctx, boolean isOn) {
        SpUtils.getInstance().put(SP_ONLY_WIFI_LOAD_IMG, isOn);
    }




}