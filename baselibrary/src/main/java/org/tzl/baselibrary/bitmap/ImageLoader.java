package org.tzl.baselibrary.bitmap;

import android.widget.ImageView;

/**
 * author: tangzenglei
 * created on: 2017/4/20 上午10:17
 * description:构建器模式创建图片加载信息封装类
 */
public class ImageLoader {


    public ImageLoaderManager.PIC_SIZE size;
    public String url;
    public int placeHolder;
    public ImageView imgView;
    public ImageLoaderManager.LOAD_STRATEGY strategy;
    public int errorPlaceHolder;//请求错误的情况下的占位符



    public ImageLoader(Builder builder) {
        this.size = builder.size;
        this.url = builder.url;
        this.placeHolder = builder.placeHolder;
        this.imgView = builder.imgView;
        this.strategy = builder.strategy;

    }


    public static class Builder{

        private ImageLoaderManager.PIC_SIZE size;
        private String url;
        private int placeHolder;
        private ImageView imgView;
        private ImageLoaderManager.LOAD_STRATEGY strategy;


        public Builder size(ImageLoaderManager.PIC_SIZE size) {
            this.size = size;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder placeHolder(int placeHolder) {

            this.placeHolder = placeHolder;
            return this;
        }


        public Builder imgView(ImageView imageView) {
            this.imgView = imgView;
            return this;
        }


        public Builder strategy(ImageLoaderManager.LOAD_STRATEGY strategy) {
            this.strategy = strategy;
            return this;
        }


        public ImageLoader build() {
            return new ImageLoader(this);
        }


    }




}