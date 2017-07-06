package org.tzl.basedemo;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.tzl.basedemo.activity.Sound2WordActivity;
import org.tzl.basedemo.app.constant.Api;
import org.tzl.basedemo.request.LoginRequest;
import org.tzl.baselibrary.base.BaseActivity;
import org.tzl.baselibrary.base.BaseResponse;
import org.tzl.baselibrary.inter.Loading;
import org.tzl.baselibrary.net.callback.Callback;
import org.tzl.baselibrary.net.json.HttpManager;
import org.tzl.baselibrary.utils.T;
import org.tzl.baselibrary.widget.CustomProgressDialog;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 */
public class LoginActivity extends BaseActivity {

    /*密码输入框*/
    @Bind(R.id.etLoginUserPassWord)
    EditText etLoginUserPassWord;

    /*输入用户名*/
    @Bind(R.id.etLoginUserPhone)
    EditText etLoginUserPhone;

    /*隐藏密码*/
    @Bind(R.id.ivLoginCloseEye)
    ImageView ivLoginCloseEye;

    /*打开密码*/
    @Bind(R.id.ivLoginOpenEye)
    ImageView      ivLoginOpenEye;
    /*登录*/
    @Bind(R.id.tvLoginUserLogin)
    TextView       tvLoginUserLogin;
    /*删除输入框信息*/
    @Bind(R.id.ivLoginInputDelete)
    ImageView      ivLoginInputDelete;
    @Bind(R.id.tv_title)
    TextView       tv_title;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.rl_bb)
    RelativeLayout rl_bb;




    String            phone      = "15000439131";
    String            pwd        = "abc123";

    String            login_type = "0";
    private String open_type = "0";
    Loading customprogressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);  //无效
//        getSupportActionBar().hide();//有效
        setContentView(R.layout.activity_login);
//        getActionBar().hide();
//        setStatusBarTextColor(R.color.white);
//        ShareSDK.initSDK(this, ShareConfig.APPKEY);
        init();
        if (!login_type.equals("0")) {
            tv_title.setText("关联账号");
            rl.setVisibility(View.INVISIBLE);
            rl_bb.setVisibility(View.INVISIBLE);
            tvLoginUserLogin.setText("关联并登录");
        }
        customprogressDialog =new CustomProgressDialog.Builder(this)
                .cancelTouchOut(false)
                .style(org.tzl.baselibrary.R.style.customDialog)
                .view(org.tzl.baselibrary.R.layout.customprogressdialog)
                .aniRes(org.tzl.baselibrary.R.id.loadingImageView)
                .widthFixed(false)
                .heigthFixed(false)
                .build();
    }

    public void init() {
        ButterKnife.bind(this);

        etLoginUserPhone.setText(phone);
        etLoginUserPassWord.setText(pwd);

        etLoginUserPassWord.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyCode == KeyEvent.KEYCODE_ENTER) {

                }
                return false;
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (customprogressDialog != null && customprogressDialog.isShowing()) {
            customprogressDialog.hideLoading();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }




    @OnClick({R.id.tvLoginUserLogin, R.id.ivLoginBack, R.id.ivLoginCloseEye, R.id.ivLoginOpenEye, R.id.tvLoginForgetPassword, R.id.ivLoginInputDelete, R.id.tv_register, R.id.ib_weixin, R.id.ib_weibo, R.id.ib_qq})
    public void viewClick(View view) {
        switch (view.getId()) {
            case R.id.ivLoginBack:
                finish();
                break;
            //查看密码
            case R.id.ivLoginCloseEye:
                ivLoginOpenEye.setVisibility(View.VISIBLE);
                ivLoginCloseEye.setVisibility(View.INVISIBLE);
                etLoginUserPassWord.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                break;
            //隐藏密码
            case R.id.ivLoginOpenEye:
                ivLoginCloseEye.setVisibility(View.VISIBLE);
                ivLoginOpenEye.setVisibility(View.INVISIBLE);
                etLoginUserPassWord.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case R.id.tvLoginForgetPassword:

                break;
            case R.id.tvLoginUserLogin:
                login();
                break;
            case R.id.ivLoginInputDelete:
//                etLoginUserPhone.setText("");
                break;
            case R.id.tv_register://注册
                
                break;
            case R.id.ib_weixin://微信登陆

                break;
            case R.id.ib_weibo://微博登陆

                break;
            case R.id.ib_qq://QQ登陆

                break;
        }

    }


    /**
     * 登录
     */
    private void login() {
        String phone = etLoginUserPhone.getText().toString().trim();
        String password = etLoginUserPassWord.getText().toString().trim();
        LoginRequest request = new LoginRequest(phone,password,"0");
        HttpManager.getInstance().postWithLoading(this, Api.Login.LOGIN, request, BaseResponse.class, new Callback() {
            @Override
            public <C> void onSuccess(int statusCode, C response) {
                T.showShortToast("状态码"+statusCode);
                Intent intent = new Intent(LoginActivity.this, Sound2WordActivity.class);
                startActivity(intent);
                finish();

            }

            @Override
            public void onError(int statusCode, String errorMsg) {
                T.showShortToast("登录失败");
            }
        });



    }




    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }


}
