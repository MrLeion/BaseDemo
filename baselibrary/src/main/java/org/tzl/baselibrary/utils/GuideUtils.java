package org.tzl.baselibrary.utils;

import android.content.Context;

/**
 * author: tangzenglei
 * created on: 2017/4/25 下午9:19
 * description:
 */
public class GuideUtils {

    private static final  String KEY_GUIDE_ACTIVITY="key_guide_activity";
    /**
     * 根据版本code值 判断是否已经引导过了
     * @param context  上下文
     * @param versionCode  版本值
     * @return  引导过返回true,否则返回false
     */
    public static boolean activityIsGuided(Context context, int versionCode){
        if(SpUtils.getInstance().getInt(KEY_GUIDE_ACTIVITY)==versionCode){
            return true;
        }
        return false;
    }
    /**
     * 设置code值，表明已经引导过
     * @param context
     * @param versionCode
     */
    public static void setIsGuided(Context context,int versionCode){
        SpUtils.getInstance().put(KEY_GUIDE_ACTIVITY, versionCode);
    }
}