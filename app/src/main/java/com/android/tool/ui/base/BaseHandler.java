package com.android.tool.ui.base;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

/**
 * 通用句柄
 */
public class BaseHandler extends Handler {

    private static CallBack callBack;

    public interface CallBack {
        void handleMessage(Message msg);
    }

    public BaseHandler(CallBack callBack, Looper looper) {
        super(looper);
        this.callBack = callBack;
    }

    @Override
    public void handleMessage(Message msg) {
        if (null != callBack) {
            callBack.handleMessage(msg);
        }
    }

}
