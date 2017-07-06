package org.tzl.baselibrary.bitmap;

import android.content.Context;

/**
 * author: tangzenglei
 * created on: 2017/4/20 上午10:54
 * description:单例模式
 * 图片加载调度类
 * 需要关注的功能：
 * 1.加载静态图片
 * 2.加载动态gif
 *
 * 需要关注的点：
 * 1.缓存策略
 * 2.图片大小
 *
 */
public class ImageLoaderManager {


    private ImageLoaderManager mImageLoaderManager;

    private ImageLoaderStrategy mImageLoaderStrategy;

    public static LOAD_STRATEGY mStrategy = LOAD_STRATEGY.ONLY_WIFI ;


    private ImageLoaderManager() {
    }


    /**
     * 根据选择的框架生成调度类和策略类
     * @param frame
     * @return
     */
    public ImageLoaderManager frame(Frame frame) {

        if(mImageLoaderManager==null){
            synchronized (ImageLoaderManager.class) {
                if (mImageLoaderManager == null) {
                    mImageLoaderManager = new ImageLoaderManager();
                }
            }
        }

        if(mImageLoaderStrategy==null){
            synchronized (ImageLoaderStrategy.class) {
                if (mImageLoaderStrategy == null) {
                    switch (frame) {
                        case GLIDE:
                            mImageLoaderStrategy = new GlideImageLoaderStrategy();
                            break;
                        case PICASSO:
                            mImageLoaderStrategy = new PicassoImageLoaderStrategy();
                            break;
                        case UNIVERSALIMAGELOADER:
                            mImageLoaderStrategy = new UniversalImageLoaderStrategy();
                            break;
                        case FRESCO:
                            mImageLoaderStrategy = new FrescoImageLoaderStategy();
                            break;
                    }

                }
            }
        }
        return mImageLoaderManager;
    }


    /**
     * 加载静态图片
     * @param context
     */
    public void loadStaticImage(Context context,ImageLoader imageLoader) {
        if (mImageLoaderStrategy!=null) {
            mImageLoaderStrategy.loadStaticImage(context,imageLoader);
        }
    }



    /**
     * 加载gif图片
     */
    public void loadDynamicImage(Context context,ImageLoader imageLoader) {
        if (mImageLoaderStrategy!=null) {
            mImageLoaderStrategy.loadDynamicImage(context,imageLoader);
        }
    }



    /**
     * 加载策略
     */
    public enum LOAD_STRATEGY{
        NORMAL,//普通加载
        ONLY_WIFI//仅在wifi条件下加载
    }


    /**
     *  加载框架
     */
    public enum Frame{
        GLIDE,
        PICASSO,
        UNIVERSALIMAGELOADER,
        FRESCO
    }

    /**
     * 图片大小
     */
    public enum PIC_SIZE{
        PIC_LARGE,
        PIC_MEDIUM,
        PIC_SMALL
    }


    /**
     * 缓存策略
     */
    public enum CACHE_MODE{
        NETWORK,
        MEMORY_CACHE,
        DISK_CACHE
    }



}