package com.android.tool.util;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/3/2 11:27
 */
public class PathUtil {

    public static final String VERSION_TIME = "20190225";//版本时间

    public static final boolean DEBUG_LOG = true;

    public static final boolean DEBUG = false;

    //接口域名
    public static final String url = DEBUG ? "https://baidu.com"
            : "http://wanglei.new.api.sx1211.ea-crm.com";
    //图片域名
    public static final String urlimg = DEBUG ? "https://baidu.com"
            : "http://wanglei.new.images.sx1211.ea-crm.com";

    /**
     * 路径
     */
    public static class Path {
        //下载apk路径
        public final static String DOWNLOADING_APK_URL = "/storage/emulated/0/teacher1211/apk/";
    }

    public static String getTestList() {
        return url + "/Product/getVideoList";
    }

}
