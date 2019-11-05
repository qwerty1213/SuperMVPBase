package com.android.tool.ui.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.android.tool.BaseApplication;
import com.android.tool.R;
import com.android.tool.ui.main.MainActivity;
import com.android.tool.util.T;
import com.android.tool.util.statusbar.StatusUtil;
import com.android.tool.util.statusbar.XStatusBar;
import com.android.tool.widget.dialog.LoadingDialogUtil;
import com.android.tool.widget.swipeback.SwipeWindowHelper;
import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;

import java.lang.reflect.Field;
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
    private SwipeWindowHelper mSwipeWindowHelper;//侧滑返回注册
    protected View viewNull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //用于判断这个Activity的启动标志，看它所在的应用是不是从后台跑到前台的。如果是，则直接把它finish（）掉，
        // 然后系统会去Activity启动历史栈查询上一个activity，然后再新建它，所以还原到了按home键出去的那个界面。
        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }
        if (isAddActivity) {
            BaseApplication.getInstance().addActivity(this);
        }
        initParms(getIntent().getExtras());
        setContentView(bindLayout());
        ButterKnife.bind(this);
        viewNull = LayoutInflater.from(mActivity).inflate(R.layout.layout_null, null);
        initView();
        initListener();
        doBusiness();
    }

    /**
     * 设置状态栏字体颜色
     * 设置状态栏透明情况下 有一层遮罩問題
     */
    protected void setStatusBarShadeTransparentOrTextColor() {
        StatusUtil.setStatusTextColor(true, mActivity);//设置字体颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS); //透明导航栏
//            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
//        遮罩問題
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            try {
                Class decorViewClazz = Class.forName("com.android.internal.policy.DecorView");
                Field field = decorViewClazz.getDeclaredField("mSemiTransparentStatusBarColor");
                field.setAccessible(true);
                field.setInt(getWindow().getDecorView(), Color.TRANSPARENT);  //改为透明
            } catch (Exception e) {
            }
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

    // 请求全屏特性
    public void requestFullScreenFeature() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        hideActionBar();
    }

    // 隐藏ActionBar
    public void hideActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
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

    protected SkeletonScreen skeletonScreen;

    protected void showSkeletonScreen(int layout, RecyclerView mRecyclerView, RecyclerView.Adapter baseQuickAdapter) {
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
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    // 判断当前屏幕朝向是否为竖屏
    public boolean isPortrait() {
        int mOrientation = getApplicationContext().getResources().getConfiguration().orientation;
        return mOrientation != Configuration.ORIENTATION_LANDSCAPE;
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
    protected Dialog getDialogLoading() {
        mDialogLoading = LoadingDialogUtil.createLoadingDialog(this, "加载中...");
        return mDialogLoading;
    }

    /**
     * [dialog弹窗]
     */
    protected Dialog getDialogLoading(String msg) {
        mDialogLoading = LoadingDialogUtil.createLoadingDialog(this, msg);
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
        return ContextCompat.getColor(this, colorRes);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (!supportSlideBack) {
            return super.dispatchTouchEvent(ev);
        }

        if (mSwipeWindowHelper == null) {
            mSwipeWindowHelper = new SwipeWindowHelper(getWindow());
        }
        try {
            return mSwipeWindowHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onBackPressed() {
//        IntentUtils.isCurrentMainActivity(mActivity);
        if (!BaseApplication.getInstance().currentActivity(MainActivity.class)) {
            mActivity.startActivity(new Intent(mActivity, MainActivity.class));
            mActivity.finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
    }
}
