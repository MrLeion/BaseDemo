package org.tzl.basedemo.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;

import org.tzl.basedemo.R;
import org.tzl.baselibrary.bean.DeviceInfo;
import org.tzl.baselibrary.net.json.HttpManager;
import org.tzl.baselibrary.utils.Utils;

/**
 * author: tangzenglei
 * created on: 2017/4/13 下午4:50
 * description:
 */
public class BaseApplication extends Application {

    @Override
    public void onCreate() {
        //初始化全局上下文
        Utils.init(this);
        //初始化网络框架
        HttpManager.getInstance().init(this);
        //选择服务器
        HttpManager.getInstance().setConnectServer(0);
        DeviceInfo.MyVersion = getSystemVersionName(this);
        DeviceInfo.childSources = "";

        // 将“12345678”替换成您申请的APPID，申请地址：http://open.voicecloud.cn
        SpeechUtility.createUtility(this, SpeechConstant.APPID +"="+getString(R.string.app_id));

    }



    /**
     * 获取版本号
     * @param context
     * @return
     */
    public static String getSystemVersionName(Context context) {
        String versionName = "1.0";
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            //            versionCode = pi.versionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return versionName;
    }
}