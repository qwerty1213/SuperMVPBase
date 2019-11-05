package com.android.tool.ui.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.android.tool.R;
import com.android.tool.util.T;
import com.android.tool.util.statusbar.StatusUtil;
import com.android.tool.util.statusbar.XStatusBar;
import com.android.tool.widget.dialog.LoadingDialogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
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
    protected Dialog mDialogLoading;
    protected Unbinder unbinder;
    protected SkeletonScreen skeletonScreen;
    protected View viewNull;
    protected View baseView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mActivity = getActivity();
        Bundle bundle = getActivity().getIntent().getExtras();
        initParms(bundle);
        baseView = inflater.inflate(bindLayout(), null);
        unbinder = ButterKnife.bind(this, baseView);
        viewNull = LayoutInflater.from(mActivity).inflate(R.layout.layout_null, null);
        initView();
        initListener();
        doBusiness();
        return baseView;
    }

    /**
     * 透明状态栏  字体颜色   跳转动画
     */
    protected void setStatusBar(boolean isSetStatusBar) {
        if (isSetStatusBar) {
            XStatusBar.newColorBuilder()
                    .statusBarTextColor(true)
                    .statusColor(color(R.color.white))  // 状态栏颜色
                    .statusDepth(0)                          // 状态栏颜色深度
                    .applyNav(false)                           // 是否应用到导航栏
                    .build(getActivity())
                    .apply();
        }
    }

    protected void setStatusBarTransparent(boolean isSetStatusBarTransparent) {
        if (isSetStatusBarTransparent) {
            XStatusBar.newImmersionBuilder()
                    .applyNav(false)
                    .build(getActivity())
                    .apply();
        }
    }

    protected void showSkeletonScreen(int layout, RecyclerView mRecyclerView, BaseQuickAdapter baseQuickAdapter) {
        skeletonScreen = Skeleton.bind(mRecyclerView)
                .adapter(baseQuickAdapter)
                .load(layout)
                .shimmer(true)//是否显示shimmer动画。默认显示
                .color(R.color.c_f9f9)//动画闪光的颜色。默认是# a2878787
                .angle(20)//动画微光角度。默认值是20
                .duration(1000)//闪烁动画持续时间。默认是1000;
                .count(10)//回收者查看项目计数。默认的是10
                .frozen(false) //在骨骼显示默认的情况下，frozen recycleview是否为真;
                .show();
    }

    protected void showViewSkeletonScreen(int layout, View view) {
        skeletonScreen = Skeleton.bind(view)
                .load(layout)
                .shimmer(true)//是否显示shimmer动画。默认显示
                .color(R.color.c_f9f9)//动画闪光的颜色。默认是# a2878787
                .angle(20)//动画微光角度。默认值是20
                .duration(1000)//闪烁动画持续时间。默认是1000;
                .show();
    }

    /**
     * 全屏显示
     */
    protected void steepSetStatusBarFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

//    /**
//     * 透明状态栏  字体颜色   跳转动画
//     */
//    protected void steepSetStatusBarTranslucent(boolean isSetStatusBar, boolean isIntentAnim) {
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            // 透明状态栏
//            getActivity().getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            StatusBarUtil.StatusBarLightMode(mActivity, isSetStatusBar);
//            if (isIntentAnim) {
//                AnimUtil.enterTransition(mActivity, 300);
//            }
//        }
//    }

    protected void setStatusBarHeight(View view) {
        int height = StatusUtil.getStatusBarHeight(mActivity);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, height);
        view.setLayoutParams(params);
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

    // 在UI线程上进行吐司提示
    public void toastOnUiThread(final String msg) {
        // 判断是否处在UI线程
        if (!checkOnMainThread()) {
            new Handler(Looper.getMainLooper()).post(new Runnable() {
                @Override
                public void run() {
                    showToast(msg);
                }
            });
        } else {
            showToast(msg);
        }
    }

    // 判断当前的线程是否是UI线程
    protected boolean checkOnMainThread() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        T.customToastShort(mActivity, msg);
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(int msg) {
        T.customToastShort(mActivity, msg);
    }

    /**
     * [dialog弹窗]
     */
    protected Dialog getDialogLoading(String msg) {
        if (mDialogLoading == null) {
            mDialogLoading = LoadingDialogUtil.createLoadingDialog(mActivity, msg);
        }
        return mDialogLoading;
    }

    /**
     * [dialog弹窗]
     */
    protected Dialog getDialogLoading() {
        if (mDialogLoading == null) {
            mDialogLoading = LoadingDialogUtil.createLoadingDialog(mActivity, "加载中...");
        }
        return mDialogLoading;
    }

    /**
     * [关闭dialog]
     */
    protected void dismissDialogLoading() {
        if (mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
    }

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
