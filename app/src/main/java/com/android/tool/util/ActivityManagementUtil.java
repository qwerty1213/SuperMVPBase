package com.android.tool.util;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * class ：Activity管理类
 * author：York(wuchunyuan)
 * time  : 2018/4/12 10:23
 */
public class ActivityManagementUtil {
    private static ActivityManagementUtil mInstance = null;
    private List<Activity> mPayActivityList = new LinkedList<>();
    private List<Activity> mLoginActivityList = new LinkedList<>();
    private List<Activity> mFindPsdActivityList = new LinkedList<>();
    private List<Activity> mUploadReleaseList = new LinkedList<>();

    public synchronized static ActivityManagementUtil getInstance() {
        if (null == mInstance) {
            mInstance = new ActivityManagementUtil();
        }
        return mInstance;
    }

    public void addUploadReleaseActivity(Activity activity) {
        mUploadReleaseList.add(activity);
    }

    public void exitUploadReleaseActivity() {
        for (Activity activity : mUploadReleaseList) {
            if (activity != null)
                activity.finish();
        }
    }

    public void addPayActivity(Activity activity) {
        mPayActivityList.add(activity);
    }

    public void exitPayActivity() {
        for (Activity activity : mPayActivityList) {
            if (activity != null)
                activity.finish();
        }
    }

    public void addLoginActivity(Activity activity) {
        mLoginActivityList.add(activity);
    }

    public void exitLoginActivity() {
        for (Activity activity : mLoginActivityList) {
            if (activity != null)
                activity.finish();
        }
    }

    public void addFindPsdActivity(Activity activity) {
        mFindPsdActivityList.add(activity);
    }

    public void exitFindPsdActivity() {
        for (Activity activity : mFindPsdActivityList) {
            if (activity != null)
                activity.finish();
        }
    }

    /**
     * 防止控件被重复点击
     */
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {

        long time = System.currentTimeMillis();

        long timeD = time - lastClickTime;

        if (0 < timeD && timeD < 800) {

            return true;

        }

        lastClickTime = time;

        return false;

    }
}
