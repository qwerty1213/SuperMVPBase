package com.android.tool.ui.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/2/28 17:12
 */
@SuppressLint("ObsoleteSdkInt")
public abstract class BaseFragments extends Fragment {
    protected Activity mActivity;
//    protected Dialog mDialogLoading;
    protected Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        Bundle bundle = getActivity().getIntent().getExtras();
        initParms(bundle);
        View view = inflater.inflate(bindLayout(), null);
        unbinder = ButterKnife.bind(this, view);
        initView();
        initListener();
        doBusiness();
        return view;
    }

    /**
     * [初始化参数]
     *
     * @param mBundle
     */
    public abstract void initParms(Bundle mBundle);

    /**
     * [绑定布局]
     *
     * @return
     */
    public abstract int bindLayout();

    /**
     * [初始化控件]
     */
    public abstract void initView();


    /**
     * [设置监听]
     */
    public abstract void initListener();

    /**
     * [业务操作]
     */
    public abstract void doBusiness();

    /**
     * [页面跳转]
     *
     * @param clz
     */
    public void startActivity(Class<?> clz) {
        startActivity(new Intent(mActivity, clz));
    }

    /**
     * [携带数据的页面跳转]
     *
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(mActivity, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * [含有Bundle通过Class打开编辑界面]
     *
     * @param cls
     * @param bundle
     * @param requestCode
     */
    public void startActivityForResult(Class<?> cls, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        intent.setClass(mActivity, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
//        T.customToastShort(mActivity, msg);
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(int msg) {
//        T.customToastShort(mActivity, msg);
    }

//    /**
//     * [dialog弹窗]
//     */
//    protected Dialog getDialogLoading(String msg) {
//        if (mDialogLoading == null) {
//            mDialogLoading = LoadingDialogUtil.createLoadingDialog(mActivity, msg);
//        }
//        return mDialogLoading;
//    }
//
//    /**
//     * [dialog弹窗]
//     */
//    protected Dialog getDialogLoading() {
//        if (mDialogLoading == null) {
//            mDialogLoading = LoadingDialogUtil.createLoadingDialog(mActivity, "加载中...");
//        }
//        return mDialogLoading;
//    }
//
//    /**
//     * [关闭dialog]
//     */
//    protected void dismissDialogLoading() {
//        if (mDialogLoading != null && mDialogLoading.isShowing()) {
//            mDialogLoading.dismiss();
//        }
//    }

    @ColorInt
    protected int color(@ColorRes int colorRes) {
        return ContextCompat.getColor(getActivity(), colorRes);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
