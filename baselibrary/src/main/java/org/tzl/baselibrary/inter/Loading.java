package org.tzl.baselibrary.inter;

/**
 * author: tangzenglei
 * created on: 2017/4/10 上午11:21
 * description: 网络加载的过程中我们只关心两个动作：
 * 1.显示加载动画
 * 2.隐藏加载动画
 * 无须关注加载的实体是什么。
 * 设计为接口的形式，可以方便的实现加载动画的更改
 */

public interface Loading {

    /**
     * 显示加载
     */
   void  showLoading();

    /**
     * 隐藏加载
     */
    void hideLoading();


    boolean isShowing();

}
