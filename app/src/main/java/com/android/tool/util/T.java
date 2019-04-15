package com.android.tool.util;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.android.tool.R;

public class T {

    public static void showToast(Activity mActivity, final String message) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View layout = inflater.inflate(R.layout.toast_layout, null);
        Toast toast = new Toast(mActivity);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setText(message);
        toast.getView().setAlpha(0.6f);
        toast.show();

    }

    public static void showToast(Activity mActivity, final int message) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View layout = inflater.inflate(R.layout.toast_layout, null);
        Toast toast = new Toast(mActivity);
        toast.setGravity(Gravity.CENTER | Gravity.CENTER_HORIZONTAL, 0, 0);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(layout);
        toast.setText(message);
        toast.getView().setAlpha(0.6f);
        toast.show();
    }

}
