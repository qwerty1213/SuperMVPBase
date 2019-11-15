package com.android.tool.ui.view;

import com.android.tool.model.HomePageBean;
import com.android.tool.ui.base.BaseHideView;
import com.ethanhua.skeleton.SkeletonScreen;


/**
 * class ：展示层数据传输基类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 14:49
 */
public interface HomePageView extends BaseHideView {
    void doHomePageResponse(HomePageBean model);

    SkeletonScreen skeletonScreen();
}
