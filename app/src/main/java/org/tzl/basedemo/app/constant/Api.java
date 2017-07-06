package org.tzl.basedemo.app.constant;

import org.tzl.baselibrary.net.json.HttpManager;

/**
 * author: tangzenglei
 * created on: 2017/4/6 上午10:27
 * description: url
 */
public class Api {


    /**
     * 整个app的基础访问站点
     */
     public static final String BASE_URL = HttpManager.CONNECT_SERVER.base_url;



    public class NETWORK_CONFIG{

        /**
         * 请求最长时间
         */
         public static final int MAX_REQ_TIME_OUT = 5;

        /**
         * 有网情况下cookie保存时间，默认为60s；
         */
         public static final int COOKIE_NETWORK_TIME = 60;



        /**
         * 无网情况下cookie保存时间，默认为30天；
         */
        public static final int COOKIE_NO_NETWORK_TIME = 24*60*60;


    }


    /**
     * 登录
     */
    public static  class Login{

        public static final String LOGIN =BASE_URL+"/open/login";


        public static final String GET_AVATAR_TOKEN = BASE_URL+"/confined/qiniuImg/getAvatarToken";


    }

    /**
     * 电影模块
     * 豆瓣开放平台
     */

    public static  class MOVIE{


        /**
         * 电影模块
         */
         public static final String BASE_MOVIE_URL = BASE_URL+"/movie/";


         public static final String GET_LIST = BASE_MOVIE_URL+"subject/1764796";



    }


    /**
     * 音乐模块
     */
    public  static  class Music {
        /**
         * 音乐模块
         */
        public static final String BASE_MOVIE_URL = BASE_URL+"/music";
    }


}