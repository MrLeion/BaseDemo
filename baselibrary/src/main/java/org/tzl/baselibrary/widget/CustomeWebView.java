package org.tzl.baselibrary.widget;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import org.tzl.baselibrary.R;
import org.tzl.baselibrary.inter.Loading;
import org.tzl.baselibrary.utils.NetworkUtils;


/**
 * author: tangzenglei
 * created on: 2017/4/17 上午10:50
 * description: 自定义webview
 */
public class CustomeWebView extends WebView {

    public static final String JSOBJECT = "android";
    private Activity mContext;
    private Loading  mCustomProgressDialog;

    public CustomeWebView(Context context) {
        this(context,null);
    }

    public CustomeWebView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = (Activity) context;
        mCustomProgressDialog = new CustomProgressDialog.Builder(context)
                .cancelTouchOut(false)
                .style(R.style.customDialog)
                .view(R.layout.customprogressdialog)
                .aniRes(R.id.loadingImageView)
                .widthFixed(false)
                .heigthFixed(false)
                .build();

        /**
         * 初始化操作
         */
        init();
    }


    /**
     * 初始化webview
     */
    private void init() {

        /**
         * 基础属性设置
         */

//        支持JS
        WebSettings settings = getSettings();
        settings.setJavaScriptEnabled(true);

        //提升渲染等级
        settings.setRenderPriority(WebSettings.RenderPriority.HIGH);


        /**
         * 缓存设置
         */
        if (NetworkUtils.isConnected()) {
          settings.setCacheMode(WebSettings.LOAD_DEFAULT);
        }else{
            settings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }

        settings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        settings.setDatabaseEnabled(true);   //开启 database storage API 功能
        settings.setAppCacheEnabled(true);//开启 Application Caches 功能

        String cacheDirPath = mContext.getFilesDir().getAbsolutePath() ;
        settings.setAppCachePath(cacheDirPath); //设置  Application Caches 缓存目录

        /**
         * 开启http与https混合调用
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            settings.setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);
        }



        /**
         * 点击连接在当前browser中响应
         */
        setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return super.shouldOverrideUrlLoading(view, url);
            }
        });


        /**
         * 辅助功能：添加加载进度条
         */
        setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress==100) {
                    mCustomProgressDialog.hideLoading();
                }else{
                    mCustomProgressDialog.showLoading();

                }

            }


            @Override
            public void onReceivedTitle(WebView view, String title) {

            }
        });


        /**
         * JS调用本地方法
         */
        addJavascriptInterface(new JsObject(), JSOBJECT);





    }


    /**
     * 在webView中处理返回事件
     * @param keyCode
     * @param event
     * @return
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                this.goBack();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }


    /**
     * Js与java互相调用
     *
     */

     class JsObject {
        /**
         * ==========================================================================================
         * html调用Java       js中调用方法：window.jsInterfaceName.methodName(parameterValues)
         * ==========================================================================================
         */

        //无参
        @JavascriptInterface
        public void htmlCallJava() {
            Toast.makeText(mContext, "helloAndroid", Toast.LENGTH_SHORT).show();
        }

        //有参
        @JavascriptInterface
        public void htmlDataCallJava(String json) {
            Toast.makeText(mContext, json, Toast.LENGTH_SHORT).show();
        }


        /**
         * ==========================================================================================
         * java调用html       webView.loadUrl(“javascript:methodName(parameterValues)”)
         * ==========================================================================================
         */
        //无参
        @JavascriptInterface
        public void javaCallHtml() {

            post(new Runnable() {
                @Override
                public void run() {
                    loadUrl("javascript: showFromHtml()");
                }
            });



        }

        //有参
        @JavascriptInterface
        public void javaDataCallHtml(final String json) {


            post(new Runnable() {
                @Override
                public void run() {
                    loadUrl("javascript: showFromHtmlData(\""+json+"\")");
                }
            });
        }

    }


    /**
     * ==========================================================================================
     * cookie操作
     * ==========================================================================================
     */
    public void syncCookie() {
        
    }





    
    
    




}