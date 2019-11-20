package com.android.tool.presenter.impl;

import android.app.Activity;

import com.android.tool.R;
import com.android.tool.model.AddressManagementModel;
import com.android.tool.presenter.AddressManagementListPresenter;
import com.android.tool.ui.view.AddressManagementListView;
import com.android.tool.util.NetUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.T;
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
public class AddressManagementListImpl implements AddressManagementListPresenter {
    private AddressManagementListView view;

    public AddressManagementListImpl(AddressManagementListView view) {
        this.view = view;
    }

    @Override
    public void getRequested(final Activity mActivity) {
        OkGo.<ObjectResponse<List<AddressManagementModel>>>get(PathUtil.getAddressList())
                .execute(new ObjectNoDialogCallback<ObjectResponse<List<AddressManagementModel>>>(mActivity, "") {
                    @Override
                    public void onStart(Request<ObjectResponse<List<AddressManagementModel>>, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(Response<ObjectResponse<List<AddressManagementModel>>> response) {
                        view.skeletonScreen().hide();
                        view.doAddressManagementListResponse(response.body().data);
                    }

                    @Override
                    public void onError(Response<ObjectResponse<List<AddressManagementModel>>> response) {
                        super.onError(response);
                        if (!NetUtil.isConnected(mActivity)) {
                            T.customToastCenterShort(mActivity, R.string.loading_network_error);
                        }
                        view.onHide();
                    }
                });
    }
}
