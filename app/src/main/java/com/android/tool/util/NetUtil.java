package com.android.tool.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetUtil {
    public static final int TYPE_DISCONECT = -1;
    public static final int TYPE_WIFI = 0;
    public static final int TYPE_NO_WIFI = 1;

    public static boolean isConnected(Context context) {
        boolean isNetConnected;
        // 获得网络连接服务
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        if (info != null && info.isAvailable()) {
            isNetConnected = true;
        } else {
            isNetConnected = false;
        }
        return isNetConnected;
    }

    /**
     * 返回网络状态
     *
     * @param context
     * @return
     */
    public static int netType(Context context) {
        if (context == null) {
            return TYPE_DISCONECT;
        }
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        if (networkInfo == null) {
            return TYPE_DISCONECT;
        }
        if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
            return TYPE_WIFI;
        } else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
            return TYPE_NO_WIFI;
        }
        return TYPE_DISCONECT;

    }
}
