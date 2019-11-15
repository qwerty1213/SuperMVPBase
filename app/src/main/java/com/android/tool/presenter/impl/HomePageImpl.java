package com.android.tool.presenter.impl;

import android.app.Activity;

import com.android.tool.R;
import com.android.tool.model.HomePageBean;
import com.android.tool.presenter.HomePagePresenter;
import com.android.tool.ui.view.HomePageView;
import com.android.tool.util.NetUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.T;
import com.android.tool.utility.AppConfig;
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
public class HomePageImpl implements HomePagePresenter {
    private HomePageView view;

    public HomePageImpl(HomePageView view) {
        this.view = view;
    }

    @Override
    public void getRequested(final Activity mActivity, String areaId) {
        OkGo.<ObjectResponse<HomePageBean>>get(PathUtil.getHomeInfo())
                .params(AppConfig.getHomePage.AREAID, areaId)
                .execute(new ObjectNoDialogCallback<ObjectResponse<HomePageBean>>(mActivity, "") {
                    @Override
                    public void onStart(Request<ObjectResponse<HomePageBean>, ? extends Request> request) {
                        super.onStart(request);
//                        view.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<ObjectResponse<HomePageBean>> response) {
                        view.doHomePageResponse(response.body().data);
                        view.skeletonScreen().hide();
                    }

                    @Override
                    public void onError(Response<ObjectResponse<HomePageBean>> response) {
                        super.onError(response);
                        if (!NetUtil.isConnected(mActivity)) {
                            T.customToastCenterShort(mActivity, R.string.loading_network_error);
                        }
                        view.onHide();
                    }
                });
    }

}
