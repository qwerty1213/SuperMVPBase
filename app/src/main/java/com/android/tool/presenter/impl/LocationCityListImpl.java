package com.android.tool.presenter.impl;

import android.app.Activity;

import com.android.tool.R;
import com.android.tool.model.LocationCityListModel;
import com.android.tool.presenter.LocationCityListPresenter;
import com.android.tool.ui.view.LocationCityListView;
import com.android.tool.util.NetUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.T;
import com.android.tool.utility.ObjectNoDialogCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;


/**
 * class ：获取数据实现类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 15:48
 */
public class LocationCityListImpl implements LocationCityListPresenter {
    private LocationCityListView view;

    public LocationCityListImpl(LocationCityListView view) {
        this.view = view;
    }

    @Override
    public void getRequested(final Activity mActivity) {
        OkGo.<ObjectResponse<LocationCityListModel>>get(PathUtil.getLocationCityList())
                .execute(new ObjectNoDialogCallback<ObjectResponse<LocationCityListModel>>(mActivity, "") {
                    @Override
                    public void onStart(Request<ObjectResponse<LocationCityListModel>, ? extends Request> request) {
                        super.onStart(request);
                    }

                    @Override
                    public void onSuccess(Response<ObjectResponse<LocationCityListModel>> response) {
                        view.skeletonScreen().hide();
                        view.doLocationCityListResponse(response.body().data);
                    }

                    @Override
                    public void onError(Response<ObjectResponse<LocationCityListModel>> response) {
                        super.onError(response);
                        if (!NetUtil.isConnected(mActivity)) {
                            T.customToastCenterShort(mActivity, R.string.loading_network_error);
                        }
                        view.onHide();
                    }
                });
    }
}
