package org.tzl.basedemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import org.tzl.baselibrary.utils.L;

public class MyService extends Service {


    @Override
    public void onCreate() {
        super.onCreate();
        L.e("MyService----onCreate()");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        L.e("MyService----onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        L.e("MyService----onBind()");
        return new MyBinder();
    }


    @Override
    public boolean onUnbind(Intent intent) {
        L.e("MyService----onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        L.e("MyService----onDestroy()");
        super.onDestroy();
    }

    public class MyBinder extends Binder {

        public void startDownload() {
            L.e("MyBinder----startDownload()");
        }
    }

}
