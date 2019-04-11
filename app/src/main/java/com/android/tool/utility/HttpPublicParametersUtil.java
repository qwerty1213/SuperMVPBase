package com.android.tool.utility;

import android.content.Context;

import com.android.tool.BaseApplication;
import com.android.tool.util.P;
import com.android.tool.util.PathUtil;
import com.android.tool.util.SystemUtil;
import com.lzy.okgo.request.base.Request;

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

    public static void httpRequestParameters(Context mActivity, Request request) {
        request.params(TOKEN, P.getPreferences(P.TOKEN_KEY, ""))
                .params(IWIDTH, mActivity.getResources().getDisplayMetrics().widthPixels)
                .params(IHEIGHT, mActivity.getResources().getDisplayMetrics().heightPixels)
                .params(__DEVICE, SystemUtil.getdata(BaseApplication.getInstance()))
                .params(__USERID, P.getPreferences(P.USER_ID, ""))
                .params(__VERSION, PathUtil.VERSION_TIME);
    }
}
