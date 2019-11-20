package com.android.tool.presenter.impl;

import android.app.Activity;

import com.android.tool.presenter.AddFeedBackPresenter;
import com.android.tool.ui.view.AddFeedBackView;
import com.android.tool.util.PathUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.NetErrorHandler;
import com.android.tool.utility.StringDialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;


/**
 * class ：获取数据实现类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 15:48
 */
public class AddFeedBackImpl implements AddFeedBackPresenter {
    private AddFeedBackView view;

    public AddFeedBackImpl(AddFeedBackView view) {
        this.view = view;
    }

    @Override
    public void getRequested(final Activity mActivity, String feedBackType, String contents, String pictureIds, String mobile) {
        OkGo.<String>get(PathUtil.addFeedBack())
                .params(AppConfig.FeekBack.FEEDBACKTYPE, feedBackType)
                .params(AppConfig.FeekBack.CONTENTS, contents)
                .params(AppConfig.FeekBack.PICTUREIDS, pictureIds)
                .params(AppConfig.FeekBack.MOBILE, mobile)
                .execute(new StringDialogCallback(mActivity) {
                    @Override
                    public void onStart(Request<String, ? extends Request> request) {
                        super.onStart(request);
                        view.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<String> response) {
                        view.hideLoading();
                        view.doAddFeedBackResponse();
                    }

                    @Override
                    public void onError(Response<String> response) {
                        super.onError(response);
//                        view.hideLoading();
                        NetErrorHandler.process(response.getException(), view);
//                        if (!NetUtil.isConnected(mActivity)) {
//                            T.customToastCenterShort(mActivity, R.string.loading_network_error);
//                        }
                    }
                });
    }
}
