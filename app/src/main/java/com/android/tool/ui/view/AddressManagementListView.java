package com.android.tool.ui.view;

import com.android.tool.model.AddressManagementModel;
import com.android.tool.ui.base.BaseHideView;
import com.ethanhua.skeleton.SkeletonScreen;

import java.util.List;

/**
 * class ：展示层数据传输基类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 14:49
 */
public interface AddressManagementListView extends BaseHideView {
    void doAddressManagementListResponse(List<AddressManagementModel> model);

    SkeletonScreen skeletonScreen();

}
