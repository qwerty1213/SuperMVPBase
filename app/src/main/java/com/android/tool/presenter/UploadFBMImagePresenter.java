package com.android.tool.presenter;

import android.app.Activity;

import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

public interface UploadFBMImagePresenter {
    void getRequested(Activity mActivity, List<LocalMedia> imageList);

}
