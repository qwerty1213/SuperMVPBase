package com.android.tool.util;

import android.util.Log;

/**
 * class ：Log信息(xxx)
 * author：York(wuchunyuan)
 * time  : 2017/12/27 09:13
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

    /**
     * Json格式化输出
     */
    public static void toJson(String message) {
        Log.i("xxx", JsonUtils.format(JsonUtils.convertUnicode(message)));
    }


    public static void it(String tag, String msg) {  //信息太长,分段打印
        //因为String的length是字符数量不是字节数量所以为了防止中文字符过多，
        //  把4*1024的MAX字节打印长度改为2001字符数
        int max_str_length = 2001 - tag.length();
        //大于4000时
        while (msg.length() > max_str_length) {
            Log.i(tag, msg.substring(0, max_str_length));
            msg = msg.substring(max_str_length);
        }
        //剩余部分
        Log.i(tag, msg);
    }
}
