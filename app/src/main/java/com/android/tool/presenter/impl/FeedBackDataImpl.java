package com.android.tool.presenter.impl;

import android.app.Activity;

import com.android.tool.model.FeedBackDataModel;
import com.android.tool.presenter.FeedBackDataPresenter;
import com.android.tool.ui.view.FeedBackDataView;
import com.android.tool.util.PathUtil;
import com.android.tool.utility.NetErrorHandler;
import com.android.tool.utility.ObjectNoDialogCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.util.List;

/**
 * class ：获取数据实现类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 15:48
 */
public class FeedBackDataImpl implements FeedBackDataPresenter {
    private FeedBackDataView view;

    public FeedBackDataImpl(FeedBackDataView view) {
        this.view = view;
    }

    @Override
    public void getRequested(final Activity mActivity) {
        OkGo.<ObjectResponse<List<FeedBackDataModel>>>get(PathUtil.getFeedBackData ())
                .execute(new ObjectNoDialogCallback<ObjectResponse<List<FeedBackDataModel>>>(mActivity, "") {
                    @Override
                    public void onStart(Request<ObjectResponse<List<FeedBackDataModel>>, ? extends Request> request) {
                        super.onStart(request);
                        view.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<ObjectResponse<List<FeedBackDataModel>>> response) {
                        view.hideLoading();
                        view.doFeedBackDataResponse(response.body().data);
                    }

                    @Override
                    public void onError(Response<ObjectResponse<List<FeedBackDataModel>>> response) {
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
