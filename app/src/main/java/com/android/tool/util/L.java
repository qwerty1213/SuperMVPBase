package com.android.tool.util;

import android.util.Log;

/**
 * class ：Log信息(xxx)
 * author：York(wuchunyuan)
 * time  : 2019/04/11 09:13
 */

public class L {
    /**
     * 信息
     */
    public static void i(String log) {
        if (PathUtil.DEBUG_LOG) {
            Log.i("xxx", log);
        }
    }

    /**
     * 警告
     */
    public static void w(String log) {
        if (PathUtil.DEBUG_LOG) {
            Log.w("xxx", log);
        }
    }

    /**
     * 错误
     */
    public static void e(String log) {
        if (PathUtil.DEBUG_LOG) {
            Log.e("xxx", log);
        }
    }

    /**
     * 测试
     */
    public static void d(String log) {
        if (PathUtil.DEBUG_LOG) {
            Log.d("xxx", log);
        }
    }

    public static void v(String log) {
        if (PathUtil.DEBUG_LOG) {
            Log.v("xxx", log);
        }
    }
}
