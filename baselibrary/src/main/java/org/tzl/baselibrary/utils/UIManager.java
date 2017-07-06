package org.tzl.baselibrary.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.google.common.base.Strings;

import java.util.List;

public class UIManager {
    public static void turnToAct(Context ct, Class<?> z) {
        Intent it = new Intent(ct, z);
        if (!isFastDoubleClick()) {
            ct.startActivity(it);
        }
    }

    public static void turnToAct(Context ct, Class<?> z, Bundle bundle) {
        Intent it = new Intent(ct, z);
        it.putExtras(bundle);
        ct.startActivity(it);
    }

    public static void turnToActForResult(Activity ct, Class<?> z, int requestCode) {
        Intent it = new Intent(ct, z);
        ct.startActivityForResult(it, requestCode);
    }
    public static void turnToActForResultBundle(Activity ct, Class<?> z, int requestCode,Bundle bundle) {
        Intent it = new Intent(ct, z);
        it.putExtras(bundle);
        ct.startActivityForResult(it, requestCode);
    }
    private static long lastClickTime = 0;

    /**
     * 防止按钮快速点击
     * @return
     */
    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

    /**
     * 判断某个界面是否在前台
     *
     * @param context
     * @param className 某个界面名称
     */
    public static boolean isForeground(Context context, String className) {
        if (context == null || Strings.isNullOrEmpty(className)) {
            return false;
        }
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> list = am.getRunningTasks(1);
        if (list != null && list.size() > 0) {
            ComponentName cpn = list.get(0).topActivity;
            if (className.equals(cpn.getClassName())) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断应用是否已经启动
     *
     * @param context     一个context
     * @return boolean
     */
    public static boolean isAppAlive(Context context, Class cls) {
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcesses = activityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo appProcess : appProcesses) {
            if (appProcess.processName.equals(context.getPackageName())) {
               return isExsitMianActivity(context,cls);
            }
        }
        return false;
    }

    private static boolean isExsitMianActivity(Context context,Class cls){
        Intent intent = new Intent(context, cls);
        ComponentName cmpName = intent.resolveActivity(context.getPackageManager());
        boolean flag = false;
        if (cmpName != null) { // 说明系统中存在这个activity
            ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            List<ActivityManager.RunningTaskInfo> taskInfoList = am.getRunningTasks(10);
            for (ActivityManager.RunningTaskInfo taskInfo : taskInfoList) {
                if (taskInfo.baseActivity.equals(cmpName)) { // 说明它已经启动了
                    flag = true;
                    break;  //跳出循环，优化效率
                }
            }
        }
        return flag;
    }
}
