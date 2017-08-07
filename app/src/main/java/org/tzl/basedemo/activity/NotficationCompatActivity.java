package org.tzl.basedemo.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.widget.RemoteViews;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.NotificationTarget;

import org.tzl.basedemo.MainActivity;
import org.tzl.basedemo.R;
import org.tzl.baselibrary.base.BaseActivity;

public class NotficationCompatActivity extends BaseActivity {

    private NotificationManager mNotificationManager;
    private MyBroadCastReciever mMyBr;
    public static final String ACTION_PLAY_PAUSE  = "PressPauseOrPlayButton";
    public static final String ACTION_CANCEL_BUTTON  = "cancelButton";
    private NotificationTarget notificationTarget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notfication_compat);
        initBroadReciever();
        showCustomNotification();
    }

    /**
     * 注册广播
     */
    private void initBroadReciever() {
        mMyBr = new MyBroadCastReciever();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(ACTION_PLAY_PAUSE);
        intentFilter.addAction(ACTION_CANCEL_BUTTON);
        registerReceiver(mMyBr, intentFilter);
    }

    /**
     * 解绑广播
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mMyBr);
      }

    private void showCustomNotification() {

                //设置点击跳转
            Intent resultIntent = new Intent(this, MainActivity.class);
            PendingIntent resultPendingIntent = PendingIntent.getActivity(
                    this, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            RemoteViews mRemoteViews = new RemoteViews(getPackageName(), R.layout.view_custom_button);
        //                if (!Strings.isNullOrEmpty(imgUrl)) {
        //                    mRemoteViews.setImageViewUri(R.id.iv, Uri.parse(imgUrl));
        //                }

        //
        //                if (!Strings.isNullOrEmpty(title)) {
        //                    mRemoteViews.setTextViewText(R.id.tv_title,title);
        //                }

        //                if(mPlaybackState.getState() == PlaybackStateCompat.STATE_PLAYING ){
        //                    mRemoteViews.setImageViewResource(R.id.ib_play_pause, R.mipmap.notification_pause);
        //                }else{
        //                    mRemoteViews.setImageViewResource(R.id.ib_play_pause, R.mipmap.notification_play);
        //                }

         mRemoteViews.setImageViewUri(R.id.iv, Uri.parse("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501763135586&di=793b268b744a15b47b0540ec7b04f8b7&imgtype=0&src=http%3A%2F%2Fimg-sg.tgbusdata.cn%2Fol%2F2017%2F07%2F09%2F1%2F55cd0c8956730cc1a3006f1cd1708eb3.jpg"));




        //绑定按钮点击事件

        Intent playPauseIntent = new Intent(ACTION_PLAY_PAUSE);
        PendingIntent playPausePIntent = PendingIntent.getBroadcast(this, 0, playPauseIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.ib_play_pause, playPausePIntent);

        Intent cancelIntent = new Intent(ACTION_CANCEL_BUTTON);
        PendingIntent cancelPIntent = PendingIntent.getBroadcast(this, 1, cancelIntent, 0);
        mRemoteViews.setOnClickPendingIntent(R.id.ib_close, cancelPIntent);


        //自定义通知布局
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setContent(mRemoteViews)
                .setContentIntent(resultPendingIntent)
                .setSmallIcon(R.mipmap.wc_logo)
                ;

        Notification notification = mBuilder.build();
        mRemoteViews.setTextViewText(R.id.tv_title,"MrLeion的成名曲");
        mRemoteViews.setImageViewResource(R.id.ib_play_pause,R.mipmap.notification_play);

        //加载图片
        notificationTarget = new NotificationTarget(
                this,
                mRemoteViews,
                R.id.iv,
                notification,
                1);
        Glide
                .with(this) // safer!
                .load("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1501763135586&di=793b268b744a15b47b0540ec7b04f8b7&imgtype=0&src=http%3A%2F%2Fimg-sg.tgbusdata.cn%2Fol%2F2017%2F07%2F09%2F1%2F55cd0c8956730cc1a3006f1cd1708eb3.jpg")
                .asBitmap()
                .into(notificationTarget);



        // set big content view for newer androids
        if (android.os.Build.VERSION.SDK_INT >= 16) {
            notification.bigContentView = mRemoteViews;
        }

        mNotificationManager = ((NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE));
        mNotificationManager.notify(1, notification);//显示通知

    }

    private class MyBroadCastReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

            switch (intent.getAction()) {
                case ACTION_CANCEL_BUTTON:
                    showToast("cancel");
                    mNotificationManager.cancel(1);
                    break;
                case ACTION_PLAY_PAUSE:

                    showToast("play");
                    //判断当前状态
                    // 1.播放状态,暂停音乐

                    // 2.暂停状态,继续播放


                    break;
            }
        }
    }
}
