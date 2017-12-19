package org.tzl.baselibrary.audio.activity;

import android.os.Bundle;

import org.tzl.baselibrary.R;
import org.tzl.baselibrary.base.BaseActivity;

import butterknife.ButterKnife;

public class PonyMusicActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pony_music);
        ButterKnife.bind(this);
    }


}
