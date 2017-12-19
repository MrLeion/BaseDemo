package org.tzl.baselibrary.bitmap;

import android.content.Context;

/**
 * author: tangzenglei
 * created on: 2017/4/20 上午11:07
 * description:图片加载策略
 */
public interface ImageLoaderStrategy {

    /**
     * 加载静态图片
     * @param context
     * @param imageLoader
     */
    void loadStaticImage(Context context,ImageLoader imageLoader);

    /**
     * 加载动态gif
     * @param context
     * @param imageLoader
     */
    void loadDynamicImage(Context context,ImageLoader imageLoader);



}