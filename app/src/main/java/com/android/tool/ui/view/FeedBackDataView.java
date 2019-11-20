package com.android.tool.ui.view;


import com.android.tool.model.FeedBackDataModel;
import com.android.tool.ui.base.BaseView;

import java.util.List;

/**
 * class ：展示层数据传输基类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 14:49
 */
public interface FeedBackDataView extends BaseView {
    void doFeedBackDataResponse(List<FeedBackDataModel> list);
}
