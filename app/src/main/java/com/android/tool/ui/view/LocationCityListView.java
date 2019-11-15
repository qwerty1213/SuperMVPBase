package com.android.tool.ui.view;

import com.android.tool.model.LocationCityListModel;
import com.android.tool.ui.base.BaseHideView;
import com.ethanhua.skeleton.SkeletonScreen;


/**
 * class ：展示层数据传输基类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 14:49
 */
public interface LocationCityListView extends BaseHideView {
    void doLocationCityListResponse(LocationCityListModel model);

    SkeletonScreen skeletonScreen();

}
