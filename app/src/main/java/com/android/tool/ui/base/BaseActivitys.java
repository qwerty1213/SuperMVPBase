package com.android.tool.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.WindowManager;

import com.android.tool.R;
import com.android.tool.model.ToolBarModel;
import com.android.tool.util.LoadingUtil;
import com.android.tool.util.T;
import com.android.tool.util.statusbar.XStatusBar;
import com.android.tool.utility.ActivityManagement;

import butterknife.ButterKnife;

/**
 * class ：Activity基类
 * author：York(wuchunyuan)
 * time  : 2018/2/28 17:12
 */
public abstract class BaseActivitys extends AppCompatActivity {
    protected boolean supportSlideBack = true, isAddActivity = true;//是否支持滑动返回/是否沉浸状态栏
    protected Dialog mDialogLoading;
    protected Activity mActivity = this;
    private ToolBarModel toolBarModel;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isAddActivity) {
            ActivityManagement.getInstance().addAllActivity(this);
        }
        initParms(getIntent().getExtras());
        setContentView(bindLayout());
        ButterKnife.bind(this);
        initView();
        initListener();
        doBusiness();
    }


    /**
     * 全屏显示
     */
    protected void steepSetStatusBarFullscreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    /**
     * 透明状态栏
     */
    protected void setStatusBarTransparent(boolean isSetStatusBarTransparent) {
        if (isSetStatusBarTransparent) {
            XStatusBar.newImmersionBuilder()
                    .applyNav(false)
                    .build(this)
                    .apply();
        }
    }

    /**
     * 透明状态栏  字体颜色   跳转动画
     */
    protected void steepSetStatusBarTranslucent(boolean isSetStatusBar, boolean isIntentAnim) {
//        if (isIntentAnim) {//跳转动画
//            AnimUtil.enterTransition(mActivity, 300);
//        }
        XStatusBar.newColorBuilder()
                .statusBarTextColor(true)
                .statusColor(color(R.color.white))  // 状态栏颜色
                .statusDepth(0)                          // 状态栏颜色深度
                .applyNav(false)                           // 是否应用到导航栏
                .navColor(color(R.color.black))       // 导航栏颜色
                .navDepth(50)                             // 导航栏颜色深度
                .build(this)
                .apply();
    }



    protected ToolBarModel showToolbar() {
        if (toolBarModel == null) {
            toolbar = (Toolbar) findViewById(R.id.toolbar);
            toolBarModel = new ToolBarModel(this, toolbar);
        }
        return toolBarModel;
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
     * @return int
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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(String msg) {
        T.showToast(mActivity, msg);
    }

    /**
     * [简化Toast]
     *
     * @param msg
     */
    protected void showToast(int msg) {
        T.showToast(mActivity, msg);
    }

    /**
     * [dialog弹窗]
     */
    protected Dialog showDialogLoading() {
        mDialogLoading = LoadingUtil.createLoadingDialog(this, "加载中...");
        return mDialogLoading;
    }

    /**
     * [dialog弹窗]
     */
    protected Dialog showDialogLoading(String msg) {
        mDialogLoading = LoadingUtil.createLoadingDialog(this, msg);
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
        return ContextCompat.getColor(mActivity, colorRes);
    }

}
