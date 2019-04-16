package com.android.tool.ui.view;


import com.android.tool.model.TestModel;
import com.android.tool.ui.base.BaseView;

/**
 * class ：展示层数据传输基类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 14:49
 */
public interface TestBaseView extends BaseView {
    void testResponse(TestModel bean, int page);

    void loadMoreResponse(TestModel bean, int page);

}
