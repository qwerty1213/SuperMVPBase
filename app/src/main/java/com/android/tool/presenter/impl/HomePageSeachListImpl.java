package com.android.tool.presenter.impl;

import android.app.Activity;

import com.android.tool.R;
import com.android.tool.model.HomePageSeachBean;
import com.android.tool.presenter.HomePageSeachListPresenter;
import com.android.tool.ui.view.HomePageSeachListView;
import com.android.tool.util.NetUtil;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.util.T;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;


/**
 * class ：获取数据实现类
 * author：York(wuchunyuan)
 * time  : 2018/9/27 15:48
 */
public class HomePageSeachListImpl implements HomePageSeachListPresenter {
    private HomePageSeachListView view;
    private String nextPageIndex;

    public HomePageSeachListImpl(HomePageSeachListView view) {
        this.view = view;
    }

    @Override
    public void getRequested(final Activity mActivity, final int page, String seachContent) {
        OkGo.<ObjectResponse<HomePageSeachBean>>get(PathUtil.getSearch())
                .params(AppConfig.getSearch.TYPE, PUtil.productType(mActivity))
//                .params(AppConfig.getSearch.AREAID, PUtil.areaId(mActivity))
//                .params(AppConfig.getSearch.EXAMINEWAY, PUtil.examineWay(mActivity))
//                .params(AppConfig.getSearch.GRADE, PUtil.grade(mActivity))
//                .params(AppConfig.getSearch.SUBJECT, PUtil.subject(mActivity))
                .params(AppConfig.getSearch.KEYWORD, seachContent)//搜索内容
                .params(AppConfig.getSearch.PAGEINDEX, page)
                .execute(new ObjectCallback<ObjectResponse<HomePageSeachBean>>(mActivity, "") {
                    @Override
                    public void onStart(Request<ObjectResponse<HomePageSeachBean>, ? extends Request> request) {
                        super.onStart(request);
//                        if (isFirstPage(page))
//                            view.showLoading();
                    }

                    @Override
                    public void onSuccess(Response<ObjectResponse<HomePageSeachBean>> response) {
                        HomePageSeachBean bean = response.body().data;
                        nextPageIndex = bean.getNextPageIndex();
                        if (isFirstPage(page)) {
                            view.doHomePageSeachListResponse(bean, Integer.valueOf(nextPageIndex));
                        } else {
                            view.LoadMoreResponse(bean, Integer.valueOf(nextPageIndex));
                        }
                    }

                    @Override
                    public void onError(Response<ObjectResponse<HomePageSeachBean>> response) {
                        super.onError(response);
                        view.onHide();
                        if (!NetUtil.isConnected(mActivity)) {
                            T.customToastCenterShort(mActivity, R.string.loading_network_error);
                        }
                    }

                });
    }


    /**
     * 获取当前返回的页码
     *
     * @return
     */
    @Override
    public int getPage() {
        if (StringUtil.isNotBlankAndEmpty(nextPageIndex)) {
            return Integer.valueOf(nextPageIndex);
        } else {
            return -1;
        }
    }

    private boolean isFirstPage(int page) {
        return page == 1;
    }
}
