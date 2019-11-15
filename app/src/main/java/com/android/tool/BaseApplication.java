package com.android.tool;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.DisplayMetrics;

import com.android.tool.widget.swipeback.ActivityLifecycleHelper;
import com.bumptech.glide.request.target.ViewTarget;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpParams;
import com.readystatesoftware.chuck.ChuckInterceptor;
import com.umeng.commonsdk.UMConfigure;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;
import okhttp3.OkHttpClient;

public class BaseApplication extends MultiDexApplication {
    private ActivityLifecycleHelper mActivityLifecycleHelper;
    private List<Activity> mActivityList;
    /**
     * 获取全局的上下文
     */
    public static Context mContext;
    private static BaseApplication mInstance = null;
    public static SharedPreferences preferences;
    public static int screenWidth, screenHeight;
    public static DisplayMetrics displayMetrics;
    final String avPlayDownloadFileName = "account_sp";

    private NotificationManager mNotificationManager;
    private SharedPreferences accountSp;
    private String TAG = "BaseApplication";

    // 友盟AppKey
    public static final String UM_APPID = "5864e5fea32511679e001545";
    // 友盟Channel
    public static final String UM_CHANNEL = "sx1211";
    // 微信AppID
    public static final String WX_APPID = "wxbb6f053a1ca76cfb";
    // 微信AppSecret
    public static final String WX_APP_SECRET = "23dd4b6a16fd3f900fd4a581a63fe0c4";
    // 微博AppID
    public static final String SINA_APPID = "218606685";
    // 微博AppSecret
    public static final String SINA_APP_SECRET = "e0964dd9874faa17be86ffeeafc21473";
    // QQAppID
    public static final String QQ_APPID = "1105832221";
    // QQAppSecret
    public static final String QQ_APP_SECRET = "V8mA9mAntkF3HvCG";

    /**
     * 友盟分享初始化
     */
    private void UMInit() {
        /**
         * 设置日志加密
         * 参数：boolean 默认为false（不加密）
         */
        UMConfigure.setEncryptEnabled(true);
        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(false);
        /**
         * 初始化common库
         * 参数1:上下文，不能为空
         * 参数2:【友盟+】 AppKey
         * 参数3:【友盟+】 Channel
         * 参数4:设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * 参数5:Push推送业务的secret
         */
        UMConfigure.init(mContext, UM_APPID, UM_CHANNEL, UMConfigure.DEVICE_TYPE_PHONE, "");
    }

    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mActivityLifecycleHelper;
    }
    public synchronized static BaseApplication getInstance() {
        if (null == mInstance) {
            mInstance = new BaseApplication();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {

        super.onCreate();
        ViewTarget.setTagId(R.id.tag_glide);
        mContext = getApplicationContext();
        mInstance = this;
        mActivityList = new LinkedList<>();
UMInit();
        preferences = PreferenceManager.getDefaultSharedPreferences(this);

        //侧滑返回
        registerActivityLifecycleCallbacks(mActivityLifecycleHelper = new ActivityLifecycleHelper());
        initDisplayMetrics();
        //优先调用进行组件加载，有条件的情况下可以放到application启动时候的恰当时机调用
        UnceHandlerInit();
        initOkGo();

    }






    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }


    /**
     * 程序奔溃重启操作
     */
    private void UnceHandlerInit() {
        //设置该CrashHandler为程序的默认处理器（程序奔溃重启操作）
//        UnCeHandler catchExcep = new UnCeHandler(this);
//        Thread.setDefaultUncaughtExceptionHandler(catchExcep);
    }



    /**
     * 获取屏幕宽高
     */
    private void initDisplayMetrics() {
        displayMetrics = getResources().getDisplayMetrics();
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
    }







    /**
     * okGo网络请求初始化设置
     */
    private void initOkGo() {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
//        HttpHeaders headers = new HttpHeaders();
//        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
        //param支持中文,直接传,不要自己编码
        HttpParams params = new HttpParams();
        //----------------------------------------------------------------------------------------//

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("xxx");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);                                 //添加OkGo默认debug日志
        //第三方的开源库，使用通知显示当前请求的log，不过在做文件下载的时候，这个库好像有问题，对文件判断不准确
        builder.addInterceptor(new ChuckInterceptor(this));

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
        builder.hostnameVerifier(new SafeHostnameVerifier());

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //建议设置OkHttpClient，不设置会使用默认的
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
//                .addCommonHeaders(headers)                    //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }

    /**
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     */
    private class SafeTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            try {
                for (X509Certificate certificate : chain) {
                    certificate.checkValidity(); //检查证书是否过期，签名是否通过等
                }
            } catch (Exception e) {
                throw new CertificateException(e);
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     */
    private class SafeHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            //验证主机名是否匹配
            //return hostname.equals("server.jeasonlzy.com");
            return true;
        }
    }

    public void addActivity(Activity activity) {
        // 判断当前集合中不存在该Activity
        if (!mActivityList.contains(activity)) {
            mActivityList.add(activity);//把当前Activity添加到集合中
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity(Activity activity) {
        if (mActivityList.contains(activity)) {//判断当前集合中存在该Activity
            mActivityList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }

    public void exit() {
        try {
            for (Activity activity : mActivityList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    /**
     * 获取activity是否存在
     */
    public boolean currentActivity(Class<?> cls) {
        for (Activity activity : mActivityList) {
            if (activity.getClass().equals(cls)) {
                return true;
            }
        }
        return false;
    }

    public void exitkillProcess() {
        for (Activity activity : mActivityList) {
            if (activity != null)
                activity.finish();
        }
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }






    private int drmServerPort;

    public int getDrmServerPort() {
        return drmServerPort;
    }

    public void setDrmServerPort(int drmServerPort) {
        this.drmServerPort = drmServerPort;
    }



}
