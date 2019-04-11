package com.android.tool.util;

import android.annotation.SuppressLint;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/3/2 11:27
 */
public class PathUtil {

    public static final String VERSION_TIME = "20190225";//版本时间

    public static final boolean DEBUG_LOG = true;

    public static final boolean DEBUG = true;

    //接口域名
    public static final String url = DEBUG ? "https://api1.sx1211.cn"
            : "http://wanglei.new.api.sx1211.ea-crm.com";
    //图片域名
    public static final String urlimg = DEBUG ? "https://images1.sx1211.cn"
            : "http://wanglei.new.images.sx1211.ea-crm.com";

    /**
     * 路径
     */
    public static class Path {
        //第三方缓存路径
        @SuppressLint("SdCardPath")
        public static String DOWNLOADING_URL = "/sdcard/teacher1211/genseevideo/";
        //图片缓存路径
        public static String IMAGE_URL = "/teacher1211/image/";
        //MP4缓存路径
        public static String MP4_URL = "/teacher1211/mp4/";
        //下载apk路径
        public final static String DOWNLOADING_APK_URL = "/storage/emulated/0/teacher1211/apk/";
        //注册协议
        public final static String AGREEMENT_LINK_URL = "sxapp://goWebView?url=" + url + "/index/registerAgreement&title=注册协议";
        //隐私政策
        public final static String POLIC_LINK_URL = "https://wanglei.apih5.yuanding.ea-crm.com/PageContents/detail/7178892";
        //微博回调网址
        public static String SINA_URL = "https://www.sx1211.cn";
    }

    public static String getTestList() {
        return url + "/Product/getVideoList";
    }

}
