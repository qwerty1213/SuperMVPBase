package com.android.tool.utility;

import android.app.Activity;

import java.util.LinkedList;
import java.util.List;

/**
 * class ：Activity管理类
 * author：York(wuchunyuan)
 * time  : 2018/4/12 10:23
 */
public class ActivityManagement {
    private static ActivityManagement mInstance = null;
    private List<Activity> mAllActivityList = new LinkedList<>();

    public synchronized static ActivityManagement getInstance() {
        if (null == mInstance) {
            mInstance = new ActivityManagement();
        }
        return mInstance;
    }

    public void addAllActivity(Activity activity) {
        mAllActivityList.add(activity);
    }

    public void exitAllActivity() {
        for (Activity activity : mAllActivityList) {
            if (activity != null)
                activity.finish();
        }
    }

    /**
     * 销毁单个Activity
     */
    public void removeActivity(Activity activity) {
        if (mAllActivityList.contains(activity)) {//判断当前集合中存在该Activity
            mAllActivityList.remove(activity);//从集合中移除
            activity.finish();//销毁当前Activity
        }
    }
}
