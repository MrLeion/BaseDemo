package org.tzl.basedemo.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import org.tzl.basedemo.R;
import org.tzl.basedemo.app.constant.Api;
import org.tzl.baselibrary.base.BaseRequest;
import org.tzl.baselibrary.base.BaseResponse;
import org.tzl.baselibrary.net.callback.Callback;
import org.tzl.baselibrary.net.json.HttpManager;
import org.tzl.baselibrary.utils.T;
import org.tzl.baselibrary.widget.CustomDialog;
import org.tzl.baselibrary.widget.CustomProgressDialog;
import org.tzl.baselibrary.widget.CustomeWebView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class WebviewActivity extends AppCompatActivity {


    @Bind(R.id.btn)
    Button         mBtn;
    @Bind(R.id.webview)
    CustomeWebView webview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);
        ButterKnife.bind(this);
        webview.loadUrl("file:///android_asset/about_me.html");


        mBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                postConfined();


            }
        });


    }

    private void postConfined() {

        BaseRequest request = new BaseRequest();
        HttpManager.getInstance().postWithLoading(this, Api.Login.GET_AVATAR_TOKEN, request, BaseResponse.class, new Callback() {
            @Override
            public <C> void onSuccess(int statusCode, C response) {
                T.showShortToast(response.toString());
            }

            @Override
            public void onError(int statusCode, String errorMsg) {

            }
        });


    }


    public void loading(View view) {

        new CustomProgressDialog.Builder(this)
                .cancelTouchOut(false)
                .style(R.style.customDialog)
                .view(R.layout.customprogressdialog)
                .aniRes(R.id.loadingImageView)
                .widthFixed(false)
                .heigthFixed(false)
                .build()
                .show();

    }


    public void login(View view) {

        new CustomDialog.Builder(this)
                .cancelTouchOut(false)
                .style(R.style.customDialog)
                .view(R.layout.dialog_side_by_side)
                .widthFixed(false)
                .heigthFixed(false)
                .addViewOnclick(R.id.btn_assure, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShortToast("success");
                    }
                })
                .addViewOnclick(R.id.btn_cancel, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        T.showShortToast("false");
                    }
                })
                .build()
                .show();


    }


}
