package com.android.tool.ui.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.webkit.WebView;

import com.android.tool.util.IntentUtils;
import com.android.tool.util.L;
import com.android.tool.util.StringUtil;
import com.android.tool.util.SystemUtil;
import java.util.HashMap;
import java.util.Map;

public class WebURLUtil {
    private final static String OBJ_TYPE = "objtype";
    private final static String TYPE = "type";
    private final static String EXAM = "exam";
    private final static String LIVE = "live";
    private final static String VIDEO = "video";
    private final static String ID = "id";
    public final static String UNIONKEY = "unionkey";
    private final static String TITLE = "title";
    private final static String URL = "url";
    private final static String SXAPP = "sxapp";
    private final static String TBOPEN = "tbopen";
    private final static String TEL = "tel";
    private final static String SMS = "sms";
    private final static String MAILTO = "mailto";
    private final static String ALIPAYS = "alipays";
    private final static String HTTP = "http";
    private final static String TAOBAO = "taobao";
    private final static String MQQWPA = "mqqwpa";
    private final static String MQQOPENSDKAPI = "mqqopensdkapi";
    private final static String TENCENTEDU = "tencentedu";
    private final static String WTLOGINMQQ = "wtloginmqq";
    public static final String PRODUCT_TYPE = "productType";
    public static final String GRADE = "grade";
    public static final String KNOWLEDGE = "knowledge";
    public static final String AREA_ID = "areaId";
    public static final String TOPIC_ID = "topicId";

    public static final String COURSE_SPU_ID = "courseSpuId";


    //跳转点播、直播列表(objtype = {live，video}切换菜单)
    private final static String GO_PRODUCTTABLIST = "goProductTabList";
    //跳转大礼包商品列表
    private final static String GO_PRODUCTPACKAGELIST = "getProductPackageList";
    //跳转大礼包商品详情
    private final static String GO_PRODUCTPACKAGEDETAIL = "goProductPackageDetail";
    //跳转课程详情
    private final static String GO_PRODUCT_DETAIL = "goProductDetail";
    //跳转网页
    private final static String GO_WEBVIEW = "goWebView";
    //跳转登录界面
    private final static String GO_LOGIN = "goLogin";
    //提交预订单
    private final static String GO_PRODUCT_BUY = "goProductBuy";
    //历年真题
    private final static String GO_OLD_EXAM = "goOldExam";
    //智能组卷
    private final static String GO_AUTO_EXAM = "goAutoExam";
    //章节练习
    private final static String GO_PRACTICE_EXAM = "goPracticeExam";
    //题库
    private final static String GO_EXAM = "goExam";
    //社区
    private final static String GO_WEIBO = "goWeibo";
    //社区原生详情
    private final static String GO_SYSTEM_WEIBO_DETAIL = "goSystemWeiboDetail";
    //社区包含webview详情
    private final static String GO_WEBWEIBO_DETAIL = "goWebWeiboDetail";
    //社区
    private final static String GO_WEIBO_LIST = "goWeiboList";
    //预约
    private final static String GO_REVIEW_ORDER = "goRevieworder";
    //预约
    private final static String GET_COURSE_LIST = "getCourseList";


    public static void openUrl(String url, Activity mActivity) {
        openUrl(url, mActivity, null);
    }
    public static void openUrl(String url, Activity mActivity, WebView view) {
        L.i("url----->" + url);
        if (!StringUtil.isNotBlankAndEmpty(url))
            return;
        if (url.startsWith(SXAPP)) {
            WebURLUtil.openSXUrl(url, mActivity);
        } else if (url.startsWith(TEL)) {//打电话
            SystemUtil.callPhone(mActivity, url);
        } else if (url.startsWith(SMS)) {//短信
            SystemUtil.sendSmsWithNumber(mActivity, url);
        } else if (url.startsWith(MAILTO)) {//发邮件
            SystemUtil.sendEmail(mActivity, url);
        } else if (url.startsWith(TAOBAO)) {//淘宝
            SystemUtil.checkAliPayInstalled(mActivity, url);
        } else if (url.startsWith(MQQWPA)) {//QQ聊天
            SystemUtil.goQq(mActivity, url);
        } else if (url.startsWith(MQQOPENSDKAPI)) {//QQ群
            SystemUtil.goJoinQqGroup(mActivity, url);
        } else if (url.startsWith(TENCENTEDU)) {//腾讯课堂
//            SystemUtil.checkTencentEduInstalled(mActivity, url);
        } else if (url.startsWith(WTLOGINMQQ)) {//qq一键登录
//            SystemUtil.checkQQLoginInstalled(mActivity, url);
        } else if (url.startsWith(TBOPEN)) {
        } else {
            if (view != null && url.toLowerCase().startsWith(HTTP)) {
                L.i("url----->" + url);
                view.loadUrl(url);
            }
        }
    }
    /**
     * 获取 协议方法名
     **
     * @param url
     * @return
     */
    @Nullable
    private static Map<String, String> getStringStringMap(String url) {
        Map<String, String> params = getParamsFromUri(url);//参数
        return params;
    }

    private static String getTypeFromUri(String uri) {
        String result = StringUtil.substringAfter(uri, "://");
        if (result.contains("?")) {
            result = StringUtil.substringBefore(result, "?");
        }
        return result;
    }
    /****
     * 打开url
     * @param url
     * @param mActivity
     */
    public static void openSXUrl(String url, Activity mActivity) {
        Map<String, String> params = getStringStringMap(url);
        switch (getTypeFromUri(url)) {
            case GO_WEBVIEW://跳转网页
                WebViewActivity.startWebViewActivity(mActivity, params.get(URL),
                        params.get(TITLE), params.get(TYPE));
                break;
            case GO_LOGIN://跳转登录界面
                IntentUtils.startLoginActivity(mActivity, new Bundle());
                break;
            default:
//                mActivity.startActivity(new Intent(mActivity, SplashActivity.class));
                break;
        }
    }

    private static Map<String, String> getParamsFromUri(String uri) {
        String result = StringUtil.substringAfter(uri, "://");
        result = StringUtil.substringAfter(result, "?");
        String[] paramArr = result.split("&");
        Map<String, String> params = new HashMap<>();
        for (String one : paramArr) {
            if (StringUtil.isBlank(one)) {
                continue;
            }
            String[] temp = one.split("=");
            if (temp.length >= 2) {
                params.put(temp[0], temp[1]);
            }
        }
        return params;
    }
}
