package com.android.tool.ui.main.version;


import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;

import com.android.tool.util.L;


/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/4/24 14:21
 */
public class InitApkBroadCastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        if (Intent.ACTION_PACKAGE_ADDED.equals(intent.getAction())) {
            handler.sendEmptyMessage(0);
            L.i("监听到系统广播添加---" + VersionUtil.DELETE_DOWNLOADING_APK_URL);
        }

        if (Intent.ACTION_PACKAGE_REMOVED.equals(intent.getAction())) {
            handler.sendEmptyMessage(0);
            L.i("监听到系统广播移除---" + VersionUtil.DELETE_DOWNLOADING_APK_URL);
        }

        if (Intent.ACTION_PACKAGE_REPLACED.equals(intent.getAction())) {
            handler.sendEmptyMessage(0);
            L.i("监听到系统广播替换---" + VersionUtil.DELETE_DOWNLOADING_APK_URL);
        }
    }


    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
//            VersionUtil.rmoveFile(VersionUtil.DELETE_DOWNLOADING_APK_URL);
        }
    };
}