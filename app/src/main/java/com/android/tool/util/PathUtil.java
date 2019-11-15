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
    public static final String url = DEBUG ? "https://api1.sx1211.cn"
            : "http://wanglei.new.api.sx1211.ea-crm.com";
    //图片域名
    public static final String urlimg = DEBUG ? "https://images1.sx1211.cn"
            : "http://wanglei.new.images.sx1211.ea-crm.com";


    /**
     * 路径
     */
    public static class Path {
        //下载apk路径
        public final static String DOWNLOADING_APK_URL = "/storage/emulated/0/teacher1211/apk/";
        //注册协议
        public final static String AGREEMENT_LINK_URL = "sxapp://goWebView?url=" + url + "/index/registerAgreement&title=注册协议";
    }
    /**
     * 登录 注册发送短信
     */
    public static String getSendSms() {
//        return url + "/sms/sendSms";
        return url + "/sms/sendSmsSpecial";
    }
    /**
     * 注册、登录
     */
    public static String getSmsLogin() {
        return url + "/account/smsLogin";
    }

    public static String getTestList() {
        return url + "/Product/getVideoList";
    }
    /**
     * 密碼登录
     */
    public static String getPswLogin() {
        return url + "/account/login";
    }
    /**
     * 验证短信验证码
     */
    public static String getCheckMobileVerify() {
        return url + "/sms/checkMobileVerify";
    }
    /**
     * 短信验证码注册
     *
     * @return
     */
    public static String getSmsRegister() {
        return url + "/account/smsRegister";
    }
    /**
     * 找回密码
     *
     * @return
     */
    public static String getRevisePwd() {
        return url + "/account/revisePwd";
    }
    /**
     * 个人信息
     *
     * @return
     */
    public static String getMyInfo() {
        return url + "/user/getMyInfo";
    }
    /**
     * 订单
     *
     * @return
     */
    public static String getMyBookOrderList() {
        return url + "/user/getMyBookOrderList";
    }

    /**
     * 取消订单
     *
     * @return
     */
    public static String getCancelOrder() {
        return url + "/bookorder/cancel";
    }

    /**
     * 提交评价
     *
     * @return
     */
    public static String getEvaluate() {
        return url + "/bookOrder/evaluate";
    }

    /**
     * 提交评价
     *
     * @return
     */
    public static String getAppInitData() {
        return url + "/index/appInitData";
    }
    /**
     * 支付
     *
     * @return
     */
    public static String getPaymentInfo() {
        return url + "/bookOrder/getPreBookOrderIndex";
    } /**
     * 提交订单
     *
     * @return
     */
    public static String getSubmitBookOrder() {
        return url + "/bookOrder/submitBookOrder";
    }
    /**
     * 加入购物车
     *
     * @return
     */
    public static String getAdd() {
        return url + "/cart/add";
    }
    /**
     * 获取版本信息
     *
     * @return
     */
    public static String getVersionData() {
        return url + "/UpdateAppInfo/getVersionAndroid";
    }
    /**
     * 首頁 教育资讯列表
     *
     * @return
     */
    public static String getHomeInfo() {
        return url + "/index/getHomeInfo";
    }
    /**
     * 搜索
     *
     * @return
     */
    public static String getSearch() {
        return url + "/index/search";
    }
    /**
     * 获取新直播详情
     *
     * @return
     */
    public static String getNewLiveDetails() {
        return url + "/Product/getNewLiveDetail";
    }
    /**
     * 城市定位列表
     *
     * @return
     */
    public static String getLocationCityList() {
        return url + "/Index/getLocationAreaList";
    }
}
