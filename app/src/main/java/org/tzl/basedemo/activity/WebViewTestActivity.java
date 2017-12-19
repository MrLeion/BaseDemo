package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import org.tzl.basedemo.R;
import org.tzl.baselibrary.utils.UIManager;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class WebViewTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view_test);
        ButterKnife.bind(this);
    }



    @OnClick({R.id.btn_js,R.id.btn_cookie,R.id.btn_audio,R.id.btn_video})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_js:

                UIManager.turnToAct(this,WebviewActivity.class);

                break;
            case R.id.btn_cookie:


                break;
            case R.id.btn_audio:


                break;
            case R.id.btn_video:


                break;
        }

    }









}


