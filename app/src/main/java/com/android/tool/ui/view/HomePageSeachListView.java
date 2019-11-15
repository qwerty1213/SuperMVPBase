package com.android.tool.ui.view;


import com.android.tool.model.HomePageSeachBean;
import com.android.tool.ui.base.BaseHideView;

/**
 * class ：展示层数据传输基类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 14:49
 */
public interface HomePageSeachListView extends BaseHideView {
    void doHomePageSeachListResponse(HomePageSeachBean bean, int page);

    void LoadMoreResponse(HomePageSeachBean bean, int page);

}
