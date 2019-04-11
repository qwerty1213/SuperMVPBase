package com.android.tool.util;

import android.app.Activity;
import android.view.Gravity;

import com.android.tool.R;
import com.android.tool.util.toast.ToastUtils;

public class T {

    public static void showToast(Activity mActivity, final String message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.setView(R.layout.toast_layout);
                ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                ToastUtils.show(message);
//                ToastUtils.show(message);
            }
        }).start();

    }

    public static void showToast(Activity mActivity, final int message) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                ToastUtils.setView(R.layout.toast_layout);
                ToastUtils.setGravity(Gravity.CENTER, 0, 0);
                ToastUtils.show(message);
//                ToastUtils.show(message);
            }
        }).start();
    }

}
