package org.tzl.basedemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.tzl.basedemo.R;
import org.tzl.baselibrary.webview.CustomWebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {


    @Bind(R.id.webview)
    CustomWebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        webview.loadUrl("file:///android_asset/about_me.html");
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == CustomWebView.FILECHOOSER_RESULTCODE) {
            webview.mUploadMessage(intent, resultCode);
        } else if (requestCode == CustomWebView.FILECHOOSER_RESULTCODE_FOR_ANDROID_5) {
            webview.mUploadMessageForAndroid5(intent, resultCode);
        }
    }
}
