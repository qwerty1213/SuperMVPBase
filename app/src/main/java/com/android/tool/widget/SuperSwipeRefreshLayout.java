package com.android.tool.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.AttributeSet;

import com.android.tool.R;


/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/3/13 11:22
 */
public class SuperSwipeRefreshLayout extends SwipeRefreshLayout {

    public SuperSwipeRefreshLayout(Context context) {
        super(context);
        initLayout();
    }

    public SuperSwipeRefreshLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initLayout();
    }

    private void initLayout() {
        this.setProgressViewOffset(true, -20, 100);
        this.setColorSchemeResources(R.color.c_zhuti);
    }

    public void setRefreshingPost(final boolean refreshing) {

        this.post(new Runnable() {
            @Override
            public void run() {
                try {
                    setRefreshing(refreshing);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }
}
