package org.tzl.baselibrary.webview;

/**
 * author: tangzenglei
 * created on: 2017/8/22 上午11:57
 * description:
 */

public interface WebViewCallback {



    /**
     * 进度条变化时调用
     */
    void onProgressChanged(int newProgress);


    /**
     * 设置标题
     */
    void onLoadTitle(String title);
//
//    /**
//     * 添加js监听
//     */
//    void addImageClickListener();
//
//    /**
//     * 播放网络视频全屏调用
//     */
//    void fullViewAddView(View view);
//
//    void showVideoFullView();
//
//    void hindVideoFullView();









}
