package org.tzl.baselibrary.net.callback;

/**
 * author: tangzenglei
 * created on: 2017/4/13 下午3:57
 * description:网络请求回调
 */
public interface Callback{

    /**
     * 请求成功回调
     * @param statusCode 状态码
     * @param response 响应体
     * @paam <C> 响应bean的类型
     */
    <C> void onSuccess(int statusCode,C response);


    /**
     * 请求失败回调
     * @param statusCode 状态码
     * @param errorMsg 错误信息
     */
    void onError( int statusCode,String errorMsg);



}