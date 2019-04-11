package com.android.tool.presenter.impl;

import android.app.Activity;

import com.android.tool.model.TestModel;
import com.android.tool.presenter.TestPresenter;
import com.android.tool.ui.view.TestBaseView;
import com.android.tool.util.PathUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.NetErrorHandler;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.util.StringUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

/**
 * class ：获取数据实现类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 15:48
 */
public class TestImpl  implements TestPresenter {
    private TestBaseView view;
    private String nextPageIndex;

    public TestImpl(TestBaseView view) {
        this.view = view;
    }

    @Override
    public void getRequested(Activity mActivity, final int page) {
        OkGo.<ObjectResponse<TestModel>>get(PathUtil.getTestList())
                .params(AppConfig.Main.PAGE_INDEX, page)
                .params(AppConfig.Test.TYPE, "2")
                .execute(new ObjectCallback<ObjectResponse<TestModel>>(mActivity, "") {

                    @Override
                    public void onStart(Request<ObjectResponse<TestModel>, ? extends Request> request) {
                        super.onStart(request);
                        if (isFirstPage(page))
                            view.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<ObjectResponse<TestModel>> response) {
                        view.hideLoading();
                        TestModel bean = response.body().data;
                        nextPageIndex = bean.getNextPageIndex();
                        if (isFirstPage(page)) {
                            view.testResponse(bean, Integer.parseInt(nextPageIndex));
                        } else {
                            view.LoadMoreResponse(bean, Integer.parseInt(nextPageIndex));
                        }
                    }

                    @Override
                    public void onError(Response<ObjectResponse<TestModel>> response) {
                        super.onError(response);
                        view.hideLoading();
                        NetErrorHandler.process(response.getException(), view);
                    }

                    @Override
                    public void onFinish() {
                        super.onFinish();
                        view.hideLoading();
                    }
                });
    }

    @Override
    public int getPage() {
        if (StringUtil.isNotBlankAndEmpty(nextPageIndex)) {
            return Integer.valueOf(nextPageIndex);
        } else {
            return -1;
        }
    }

    public boolean isFirstPage(int page) {
        return page == 1;
    }
}
