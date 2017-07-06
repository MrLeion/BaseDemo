package org.tzl.baselibrary.net.json;

import android.app.Activity;
import android.app.Application;
import android.os.Build;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.cookie.store.PersistentCookieStore;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;

import org.tzl.baselibrary.R;
import org.tzl.baselibrary.base.BaseRequest;
import org.tzl.baselibrary.base.BaseResponse;
import org.tzl.baselibrary.bean.DeviceInfo;
import org.tzl.baselibrary.inter.Loading;
import org.tzl.baselibrary.net.callback.Callback;
import org.tzl.baselibrary.utils.Convert;
import org.tzl.baselibrary.utils.NetworkUtils;
import org.tzl.baselibrary.utils.T;
import org.tzl.baselibrary.utils.constant.Constant;
import org.tzl.baselibrary.widget.CustomProgressDialog;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.Response;


/**
 * author: tangzenglei
 * created on: 2017/4/9 上午10:55
 * description: http请求调度类
 */
public class HttpManager {

    private volatile static HttpManager mHttpManager;
    private volatile static OkGo        client;
    private                 Loading     dialog;

    /**
     * 设置开发连接的服务器地址，默认连接正式服务器
     */
    public static Config CONNECT_SERVER = Config.OFFLINE_BASE_URL;

    /**
     * 避免多线程调用问题
     */
    private HttpManager(){
    }


    public static String APPNAME = "wangcai";
    public static String APPVERSION = "", APPS = "ANDROID_C_APP";
    public static String APPDTN = "deviceToken_C";
    public static String IDTYPE = "customId";

    /**
     * ==========================================================================================
     * 单例实现：调度类对象
     * =========================================================================================
     */
    public static HttpManager getInstance(){

        if(mHttpManager==null){
            synchronized (HttpManager.class) {
                if (mHttpManager == null) {
                    mHttpManager = new HttpManager();
                }
            }
        }

        if(client==null){
            synchronized (OkGo.class) {
                if (client == null) {
                    client = OkGo.getInstance();
                }
            }
        }

        return mHttpManager;
    }


    /**
     * http post:baseRequest -> baseResponse+不包含显示网络加载动画选择+cookie
     * @param activity
     * @param reqUrl
     * @param request
     * @param responseClass
     * @param callback
     * @param <C>
     */
    public <C> void post(Activity activity, String reqUrl, Object request, final Class<C> responseClass, final Callback callback){
        //判断网络状态
        if(!NetworkUtils.isConnected()){
            T.showShortToast(Constant.CONNECT_NETWORK_FAILED);
            return;
        }
        //处理请求参数 bean->params
        HttpParams params = new HttpParams();
        String requestJson = Convert.toJson(request);
        HashMap<String,String> requestMap = Convert.fromJson(requestJson, HashMap.class);
        params.put(requestMap,true);

        //TODO:添加header
        HttpHeaders headers = new HttpHeaders();
        //添加cookie处理:公用配置.setCookieStore(new PersistentCookieStore())



        //发送请求
        OkGo.post(reqUrl)
                .tag(activity)//便于取消网络请求
                .headers(headers)//公用header
                .params(params)//请求参数
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            BaseResponse baseResponse = (BaseResponse)Convert.fromJson(s, responseClass);
                            callback.onSuccess(response.code(),baseResponse);

                        } catch (Exception e) {
                            callback.onError(response.code(),response.message());
                            e.printStackTrace();

                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        try {
                            if (response.code()==401) {
                                //TODO:提示重新登陆并跳转到登录界面


                                //TODO:清楚客户缓存信息，并且关闭通知

                            }

                        } catch (Exception exception) {
                            //todo nothing...
                            exception.printStackTrace();

                        }
                        if (callback!=null&&response!=null&&e!=null) {
                            callback.onError(response.code(),e.getMessage());
                        }

                    }

                });
    }


    //TODO:http post:baseRequest -> baseResponse 包含是否显示加载进度条选择+cookie
    public <C> void postWithLoading(final Activity activity, String reqUrl, BaseRequest request, final Class<C> responseClass, final Callback callback){
        //判断网络状态
        if(!NetworkUtils.isConnected()){
            T.showShortToast(Constant.CONNECT_NETWORK_FAILED);
            return;
        }
        //处理请求参数 bean->params
        String json = "";
        try {
            json = Convert.toJson(request);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String customId = "customId = 0";

        //TODO:添加header
        HttpHeaders headers = new HttpHeaders();
        headers.put("User-Agent",String.format("%s/%s (Linux; Android %s; %s Build/%s  ",
                APPNAME, APPVERSION, Build.VERSION.RELEASE, Build.MANUFACTURER, Build.ID) + ");" + APPS + ";" + APPDTN + "=" + DeviceInfo.getInstance().getDeviceToken() + ";" + customId);
        //添加cookie处理:公用配置.setCookieStore(new PersistentCookieStore())
        if (dialog==null) {
            dialog = new CustomProgressDialog.Builder(activity)
                    .cancelTouchOut(false)
                    .style(R.style.customDialog)
                    .view(R.layout.customprogressdialog)
                    .aniRes(R.id.loadingImageView)
                    .widthFixed(false)
                    .heigthFixed(false)
                    .build();
        }
        //发送请求
        OkGo.post(reqUrl)
                .tag(activity)//便于取消网络请求
                .headers(headers)//公用header
                .requestBody(RequestBody.create(MediaType.parse("application/json"),json))
                .execute(new StringCallback() {

                    @Override
                    public void onBefore(com.lzy.okgo.request.BaseRequest request) {
                        super.onBefore(request);
                        //网络请求前显示对话框
                        if (dialog != null && !dialog.isShowing()) {
                            dialog.showLoading();
                        }
                    }


                    @Override
                    public void onSuccess(String s, Call call, Response response) {
                        try {
                            BaseResponse baseResponse = (BaseResponse)Convert.fromJson(s, responseClass);
                            callback.onSuccess(response.code(),baseResponse);

                        } catch (Exception e) {
                            callback.onError(response.code(),response.message());
                            e.printStackTrace();

                        }

                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        try {
                            if (response.code()==401) {
                                //TODO:提示重新登陆并跳转到登录界面


                                //TODO:清楚客户缓存信息，并且关闭通知

                            }

                        } catch (Exception exception) {
                            //todo nothing...
                            exception.printStackTrace();

                        }
                        if (callback!=null&&response!=null&&e!=null) {
                            callback.onError(response.code(),e.getMessage());
                        }

                    }



                    @Override
                    public void onAfter(String s, Exception e) {
                        //网络请求结束后关闭对话框
                        if (dialog != null && dialog.isShowing()) {
                            dialog.hideLoading();
                        }
                    }


                });
    }






    /**
     * https post:baseRequest -> baseResponse+不包含显示网络加载动画选择+cookie
     * @param activity
     * @param reqUrl
     * @param request
     * @param response
     * @param callback
     * @param <C>
     */
    public <C> void postHttps(Activity activity, String reqUrl, BaseRequest request, Class<? extends BaseResponse> response, Callback callback){

    }


    //TODO:https post:baseRequest -> baseResponse 包含是否显示加载进度条选择+cookie
    public <C> void postHttpsWithLoading(Activity activity, String reqUrl, BaseRequest request, Class<? extends BaseResponse> response,Callback callback){

    }


    /**
     * 取消当前请求
     * @param activity
     */
    public void cancelHttp(Activity activity){
        if(client==null){
            return;
        }
        if (activity!=null) {
            client.cancelTag(activity);
        }
    }


    /**
     * 取消所有网络请求
     */
    public void cancelAll(){
        if(client==null){
            return;
        }
//        client.cancelAll();
    }







    /**
     * 网络请求相关配置
     */
    public static class NETWORK_CONFIG{

        /**
         * 请求最长时间
         */
        public static final int MAX_REQ_TIME_OUT = 60*000;//单位ms

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
     * ==========================================================================================
     * 服务器地址
     * ==========================================================================================
     */
    public enum Config{


        /**
         * 正式服
         **/
        OFFLINE_BASE_URL("https://www.wanttreasure.com/mobile-web",0),
        /**
         * 测试服1
         **/
        TEST_BASE_URL1("http://test.wanttreasure.com/mobile-web",1),
        /**
         * 测试服2
         **/
        TEST_BASE_URL2("http://test2.wanttreasure.com/mobile-web",2),
        /**
         * 测试服3
         **/
        TEST_BASE_URL3("http://test3.wanttreasure.com/mobile-web",3);


        public String base_url;
        public int mode;

        Config(String base_url, int mode) {
            this.base_url = base_url;
            this.mode = mode;
        }

    }


    /**
     * ==========================================================================================
     * 设置连接服务器地址
     * ==========================================================================================
     */
    public void setConnectServer(int mode){

        switch (mode) {
            case 0:
                CONNECT_SERVER = Config.OFFLINE_BASE_URL;
                break;
            case 1:
                CONNECT_SERVER = Config.TEST_BASE_URL1;
                break;
            case 2:
                CONNECT_SERVER = Config.TEST_BASE_URL2;
                break;
            case 3:
                CONNECT_SERVER = Config.TEST_BASE_URL3;
                break;

        }

    }


    /**
     * 获取连接服务器类型
     * @return 0 正式服
     *         1 测试服1
     *         2 测试服2
     *         3 测试服3
     */
    public int getConnectServer(){
        return CONNECT_SERVER.mode;
    }


    /**
     * 配置全局参数，需要在Application中初始化
     * @param app
     */
    public  void init( Application app) {

        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文
//        headers.put("commonHeaderKey2", "commonHeaderValue2");
//        HttpParams params = new HttpParams();
//        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
//        params.put("commonParamsKey2", "这里支持中文参数");
        //-----------------------------------------------------------------------------------//

        //必须调用初始化
        OkGo.init(app);

        //以下设置的所有参数是全局参数,同样的参数可以在请求的时候再设置一遍,那么对于该请求来讲,请求中的参数会覆盖全局参数
        //好处是全局参数统一,特定请求可以特别定制参数
        try {
            //以下都不是必须的，根据需要自行选择,一般来说只需要 debug,缓存相关,cookie相关的 就可以了
                    client
                    // 打开该调试开关,打印级别INFO,并不是异常,是为了显眼,不需要就不要加入该行
                    // 最后的true表示是否打印okgo的内部异常，一般打开方便调试错误
                    .debug("OkGo")

                    //如果使用默认的 60秒,以下三行也不需要传
                    .setConnectTimeout(NETWORK_CONFIG.MAX_REQ_TIME_OUT)  //全局的连接超时时间
                    .setReadTimeOut(NETWORK_CONFIG.MAX_REQ_TIME_OUT)     //全局的读取超时时间
                    .setWriteTimeOut(NETWORK_CONFIG.MAX_REQ_TIME_OUT)    //全局的写入超时时间

                    //可以全局统一设置缓存模式,默认是不使用缓存,可以不传,具体其他模式看 github 介绍 https://github.com/jeasonlzy/
                    .setCacheMode(CacheMode.NO_CACHE)

                    //可以全局统一设置缓存时间,默认永不过期,具体使用方法看 github 介绍
                    .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)

                    //可以全局统一设置超时重连次数,默认为三次,那么最差的情况会请求4次(一次原始请求,三次重连请求),不需要可以设置为0
//                    .setRetryCount(3)

                    //如果不想让框架管理cookie（或者叫session的保持）,以下不需要
                    //              .setCookieStore(new MemoryCookieStore())            //cookie使用内存缓存（app退出后，cookie消失）
                    .setCookieStore(new PersistentCookieStore())        //cookie持久化存储，如果cookie不过期，则一直有效

                    //可以设置https的证书,以下几种方案根据需要自己设置
                    .setCertificates() ;                                //方法一：信任所有证书,不安全有风险
                    //              .setCertificates(new SafeTrustManager())            //方法二：自定义信任规则，校验服务端证书
                    //              .setCertificates(getAssets().open("srca.cer"))      //方法三：使用预埋证书，校验服务端证书（自签名证书）
                    //              //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
                    //               .setCertificates(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"))//

                    //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
                    //               .setHostnameVerifier(new SafeHostnameVerifier())

                    //可以添加全局拦截器，不需要就不要加入，错误写法直接导致任何回调不执行
                    //                .addInterceptor(new Interceptor() {
                    //                    @Override
                    //                    public Response intercept(Chain chain) throws IOException {
                    //                        return chain.proceed(chain.request());
                    //                    }
                    //                })

                    //这两行同上，不需要就不要加入
//                    .addCommonHeaders(headers)  //设置全局公共头
//                    .addCommonParams(params);   //设置全局公共参数

        } catch (Exception e) {
            e.printStackTrace();
        }


    }




}