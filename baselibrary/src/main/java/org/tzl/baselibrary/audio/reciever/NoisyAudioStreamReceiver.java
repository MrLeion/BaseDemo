package org.tzl.baselibrary.audio.reciever;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import org.tzl.baselibrary.audio.constant.Actions;
import org.tzl.baselibrary.audio.service.PlayService;


/**
 * 来电/耳机拔出时暂停播放
 * Created by wcy on 2016/1/23.
 */
public class NoisyAudioStreamReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        PlayService.startCommand(context, Actions.ACTION_MEDIA_PLAY_PAUSE);
    }
}
