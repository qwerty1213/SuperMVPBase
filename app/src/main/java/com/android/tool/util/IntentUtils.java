package com.android.tool.util;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.android.tool.BaseApplication;
import com.android.tool.ui.login.LoginFragmentActivity;
import com.android.tool.ui.main.MainActivity;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/5 09:10
 */
public class IntentUtils {
    public static boolean isLogin(Activity mActivity, Bundle bundle) {
        if (!PUtil.isTokenNull()) {
            bundle.putInt(KeyUtil.REFRESH_LOGIN, ResultUtil.LOGING_RESULT);
            startLoginActivity(mActivity, bundle);
            return false;
        } else {
            return true;
        }
    }
    /**
     * 判断未登录 跳转登录页
     *
     * @param mActivity
     * @return
     */
    public static void startLoginActivity(Activity mActivity, Bundle bundle) {
        if (!ActivityManagementUtil.isFastDoubleClick()) {
            Intent mIntent = new Intent(mActivity, LoginFragmentActivity.class);
            int requestCode = bundle.getInt(KeyUtil.REFRESH_LOGIN, 0);
            mActivity.startActivityForResult(mIntent, requestCode, bundle);
//            mActivity.startActivityForResult(mIntent, requestCode);
        } else {
            return;
        }
    }
    /**
     * 跳转
     *
     * @param mActivity
     * @return
     */
    public static void startIntent(Activity mActivity, Class<?> cls, Bundle bundle) {
        Intent mIntent = new Intent(mActivity, cls);
        mIntent.putExtras(bundle);
        mActivity.startActivity(mIntent);
    }

    /**
     * 跳转
     *
     * @param mContext
     * @return
     */
    public static void startIntent(Context mContext, Class<?> cls, Bundle bundle) {
        Intent mIntent = new Intent(mContext, cls);
        mIntent.putExtras(bundle);
        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        mContext.startActivity(mIntent);
    }
    /**
     * 判断MainActivity是否存在  true不存在    false存在
     *
     * @param mActivity
     * @return
     */
    public static void isCurrentMainActivity(Activity mActivity) {
        if (!BaseApplication.getInstance().currentActivity(MainActivity.class)) {
            mActivity.startActivity(new Intent(mActivity, MainActivity.class));
            mActivity.finish();
        } else {
            mActivity.finish();
        }
    }
}
