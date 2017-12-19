package org.tzl.baselibrary.bitmap;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.IOException;
import java.io.InputStream;

/**
 * author: tangzenglei
 * created on: 2017/4/20 上午11:10
 * description:Glide加载图片
 *
 * 立足点：尽量减少用户访问图片的流量
 * 判断网络是否连接：
 * 1.已连接
 *     判断是否为wifi
 *     1.是
 *          正常加载，并将图片缓存下来
 *     2.否
 *          提示用户当前在非wifi环境下，并且加载模式为wifi的情况下
 *              a.是
 *              跳入系统设置网络界面
 *              b.否
 *              正常加载，将图片缓存下来,将加载模式修改成普通加载
 * 2.未连接
 *      加载内存中的图片
 *
 */
public class GlideImageLoaderStrategy implements ImageLoaderStrategy {
    @Override
    public void loadStaticImage(Context context, ImageLoader imageLoader) {


//        //判断网络状态
//        if (NetworkUtils.isConnected()) {
//            //        case 1：连接上
//
//            //判断是否设置wifi下加载图片
//            if (!SettingUtils.getOnlyWifiLoadImg(context)) {
//                loadNormal(context, imageLoader);
//            }
//
//
//            if (NetworkUtils.getWifiEnabled()) {
//                //如果是wifi状态，正常加载
//                loadNormal(context, imageLoader);
//            }else{
//                //如果是非wifi状态，并且加载模式为wifi的情况下,弹框提示用户是否要切换网络状态
//
//                ImageLoaderManager.LOAD_STRATEGY strategy = ImageLoaderManager.mStrategy;
//                if (strategy == ImageLoaderManager.LOAD_STRATEGY.ONLY_WIFI ) {
//                    //TODO:弹框提示用户是否要切换网络状态
//                    SettingUtils.setOnlyWifiLoadImg(context,false);
//
//                }else{
//                    //正常加载
//                    loadNormal(context,imageLoader);
//
//                }
//
//            }
//
//
//        }else{
//            //case 2 :未连接上 读取缓存，并且提示用户网络未连接
//            loadCache(context,imageLoader);
//        }





            //具体方法内容自己去选择，次方法是为了减少banner过多的依赖第三方包，所以将这个权限开放给使用者去选择
        try {
            Glide.with(context)
                    .load(imageLoader.url)
                    .crossFade()
                    .into(imageLoader.imgView);

        } catch (Exception e) {

        }




    }



    @Override
    public void loadDynamicImage(Context context, ImageLoader imageLoader) {

    }


    /**
     * 正常加载图片，并且将图片缓存下来
     * @param context
     * @param imageLoader
     */
    public void loadNormal(Context context,ImageLoader imageLoader) {
        Glide.with(context)
                .load(imageLoader.url)
                .placeholder(imageLoader.placeHolder)
//                .skipMemoryCache(true)//不用内存缓存
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//不用磁盘缓存
                .into(imageLoader.imgView);
    }

    /**
     * 加载缓存中的图片
     * @param context
     * @param img
     */
    private void loadCache(Context context, ImageLoader img) {
        Glide.with(context).using(new StreamModelLoader<String>() {
            @Override
            public DataFetcher<InputStream> getResourceFetcher(final String model, int i, int i1) {
                return new DataFetcher<InputStream>() {
                    @Override
                    public InputStream loadData(Priority priority) throws Exception {
                        throw new IOException();
                    }

                    @Override
                    public void cleanup() {

                    }

                    @Override
                    public String getId() {
                        return model;
                    }

                    @Override
                    public void cancel() {

                    }
                };
            }
        }).load(img.url).placeholder(img.placeHolder).diskCacheStrategy(DiskCacheStrategy.ALL).into(img.imgView);
    }









}