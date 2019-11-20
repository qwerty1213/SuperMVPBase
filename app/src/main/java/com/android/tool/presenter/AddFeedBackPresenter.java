package com.android.tool.presenter;

import android.app.Activity;

public interface AddFeedBackPresenter {
    /**
     * feedBackType	是	string	反馈类型
     * contents	是	string	反馈内容
     * pictureIds	否	string	图片id 多个’,’隔开
     * mobile	是	string	联系方式
     *
     * @param mActivity
     */
    void getRequested(Activity mActivity, String feedBackType, String contents, String pictureIds, String mobile);

}
