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
//    /**
//     * 跳转支付页面
//     *
//     * @param mActivity 上下文
//     * @param couserId  商品id
//     * @param orderId   订单id
//     * @param type      加入购物车需要
//     * @param isOrder   true订单頁面支付     false商品列表支付
//     */
//    public static void startPayPageActivity(final Activity mActivity, final String couserId,
//                                            final String orderId, String type, final boolean isOrder) {
//        if (isLogin(mActivity, new Bundle())) {
//            if (isOrder) {//订单页面
//                Bundle bundle = new Bundle();
//                bundle.putString(KeyUtil.ORDER_ID, orderId);
//                bundle.putBoolean(KeyUtil.IS_ORDER, isOrder);
//                startIntentForResult(mActivity, PayActivity.class, bundle, ResultUtil.R2);
//            } else {//未加入购物车
//                OkGo.<String>get(PathUtil.getAdd()).tag(mActivity)
//                        .params(AppConfig.getAddCar.OBJ_ID, couserId)
//                        .params(AppConfig.getAddCar.OBJ_TYPE, type)
//                        .execute(new StringDialogCallback(mActivity) {
//                            @Override
//                            public void onSuccess(Response<String> response) {
//                                Bundle bundle = new Bundle();
//                                bundle.putString(KeyUtil.ORDER_ID, orderId);
//                                bundle.putBoolean(KeyUtil.IS_ORDER, isOrder);
//                                startIntentForResult(mActivity, PayActivity.class, bundle, ResultUtil.R2);
//                            }
//                        });
//            }
//        }
//    }
    /**
     * 跳转
     *
     * @param mActivity
     * @return
     */
    public static void startIntentForResult(Activity mActivity, Class<?> cls, Bundle bundle, int requestCode) {
        Intent mIntent = new Intent(mActivity, cls);
        mIntent.putExtras(bundle);
        mActivity.startActivityForResult(mIntent, requestCode);
    }
}
