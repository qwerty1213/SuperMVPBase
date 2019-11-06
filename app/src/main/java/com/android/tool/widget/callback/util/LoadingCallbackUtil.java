package com.android.tool.widget.callback.util;


import android.view.View;

import com.android.tool.model.ChapterTestListBean;
import com.android.tool.model.LiveBean;
import com.android.tool.model.MyWrongTopicSubjectBean;
import com.android.tool.model.OndemandBean;
import com.android.tool.model.OrderBean;
import com.android.tool.widget.loading.LoadingState;
import com.android.tool.widget.loading.LoadingView;

import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/4/2 10:50
 */
public class LoadingCallbackUtil {

    public static void isOndemandsNullList(List<OndemandBean.RowsBean> mlist, LoadingView mLoadingView) {
        try {
            if (mlist != null && mlist.size() <= 0) {
                mLoadingView.setVisibility(View.VISIBLE);
                mLoadingView.setState(LoadingState.STATE_EMPTY);
            } else {
                mLoadingView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void isLiveNullList(List<LiveBean.RowsBean> mlist, LoadingView mLoadingView) {
        try {
            if (mlist != null && mlist.size() <= 0) {
                mLoadingView.setVisibility(View.VISIBLE);
                mLoadingView.setState(LoadingState.STATE_EMPTY);
            } else {
                mLoadingView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void isChapterTestListNullList(List<ChapterTestListBean> mlist, LoadingView mLoadingView) {
        try {
            if (mlist != null && mlist.size() <= 0) {
                mLoadingView.setVisibility(View.VISIBLE);
                mLoadingView.setState(LoadingState.STATE_EMPTY);
            } else {
                mLoadingView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void isMyErrorTopicBeanNullList(List<MyWrongTopicSubjectBean> mlist, LoadingView mLoadingView) {
        try {
            if (mlist != null && mlist.size() <= 0) {
                mLoadingView.setVisibility(View.VISIBLE);
                mLoadingView.setState(LoadingState.STATE_EMPTY);
            } else {
                mLoadingView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    public static void isMyBookOrderListNullList(List<OrderBean.RowsBean> mlist, LoadingView mLoadingView) {
        try {
            if (mlist != null && mlist.size() <= 0) {
                mLoadingView.setVisibility(View.VISIBLE);
                mLoadingView.setState(LoadingState.STATE_EMPTY);
            } else {
                mLoadingView.setVisibility(View.GONE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
