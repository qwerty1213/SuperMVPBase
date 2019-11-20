package com.android.tool.ui.view;

import com.android.tool.model.UploadImageModel;
import com.android.tool.ui.base.BaseView;
import com.lzy.okgo.model.Progress;

import java.util.List;

/**
 * class ：展示层数据传输基类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 14:49
 */
public interface UploadFBMImageView extends BaseView {
    void doUploadFBMImageResponse(List<UploadImageModel> modelList);

    void uploadImageModelProgress(Progress progress);
}
