package com.android.tool.presenter.impl;

import android.app.Activity;

import com.android.tool.model.UploadImageModel;
import com.android.tool.presenter.UploadFBMImagePresenter;
import com.android.tool.ui.view.UploadFBMImageView;
import com.android.tool.util.PathUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.NetErrorHandler;
import com.android.tool.utility.ObjectNoDialogCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.PostRequest;
import com.lzy.okgo.request.base.Request;

import java.io.File;
import java.util.List;

/**
 * class ：获取数据实现类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 15:48
 */
public class UploadFBMImageImpl implements UploadFBMImagePresenter {
    private UploadFBMImageView view;

    public UploadFBMImageImpl(UploadFBMImageView view) {
        this.view = view;
    }

    @Override
    public void getRequested(final Activity mActivity, List<LocalMedia> imageList) {
        PostRequest<ObjectResponse<List<UploadImageModel>>> postRequest = OkGo.post(PathUtil.multUploadImage());
        for (int i = 0; i < imageList.size(); i++) {
            postRequest.params(AppConfig.FileVideoImage.PIC + "[" + i + "]", new File(imageList.get(i).getPath()));
        }
        postRequest.execute(new ObjectNoDialogCallback<ObjectResponse<List<UploadImageModel>>>(mActivity, "") {
            @Override
            public void onStart(Request<ObjectResponse<List<UploadImageModel>>, ? extends Request> request) {
                super.onStart(request);
                view.showLoading();
            }

            @Override
            public void onSuccess(Response<ObjectResponse<List<UploadImageModel>>> response) {
                view.hideLoading();
                view.doUploadFBMImageResponse(response.body().data);
            }

            @Override
            public void onError(Response<ObjectResponse<List<UploadImageModel>>> response) {
                super.onError(response);
                NetErrorHandler.process(response.getException(), view);
//                if (!NetUtil.isConnected(mActivity)) {
//                    T.customToastCenterShort(mActivity, R.string.loading_network_error);
//                }
            }

            @Override
            public void uploadProgress(Progress progress) {
                super.uploadProgress(progress);
                view.uploadImageModelProgress(progress);
            }
        });

    }

}
