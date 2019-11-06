package com.android.tool.widget.callback.util;


import android.content.Context;
import com.lzy.okgo.request.base.Request;
import com.android.tool.BaseApplication;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.SystemUtil;

/**
 * class ：请求公共参数
 * author：York(wuchunyuan)
 * time  : 2018/4/4 09:12
 */
public class HttpPublicParametersUtil {

    public final static int TOAST_MESSAGE = 0x005;
    public final static int IS_LOGIN = 0x006;
    private final static String TOKEN = "token";
    private final static String IWIDTH = "iWidth";
    private final static String IHEIGHT = "iHeight";
    private final static String __DEVICE = "__device";
    private final static String __USERID = "__userid";
    private final static String __VERSION = "__version";
    private final static String DEVICE_ID = "deviceid";

    public static void httpRequestParameters(Context mActivity, Request request) {
        request.params(TOKEN, PUtil.getPreferences(PUtil.TOKEN_KEY, ""))
                .params(IWIDTH, mActivity.getResources().getDisplayMetrics().widthPixels)
                .params(IHEIGHT, mActivity.getResources().getDisplayMetrics().heightPixels)
                .params(__DEVICE, SystemUtil.getdata(BaseApplication.getInstance()))
                .params(DEVICE_ID, SystemUtil.getDeviceId(BaseApplication.getInstance()))
                .params(__USERID, PUtil.getPreferences(PUtil.USER_ID, ""))
                .params(__VERSION, PathUtil.VERSION_TIME);
//        L.i("获取手机唯一标识44444---" + SystemUtil.getDeviceId(BaseApplication.getInstance()));

    }
}
