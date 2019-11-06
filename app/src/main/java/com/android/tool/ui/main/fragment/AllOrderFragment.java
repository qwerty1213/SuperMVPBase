package com.android.tool.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.tool.R;
import com.android.tool.model.OrderBean;
import com.android.tool.ui.base.BaseFragments;
import com.android.tool.ui.main.adapter.OrderAdapter;
import com.android.tool.ui.web.WebURLUtil;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.L;
import com.android.tool.util.PathUtil;
import com.android.tool.util.ResultUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.LoadingCallback;
import com.android.tool.utility.StringDialogCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.widget.CustomLoadMoreView;
import com.android.tool.widget.SuperSwipeRefreshLayout;
import com.android.tool.widget.callback.util.LoadingCallbackUtil;
import com.android.tool.widget.dialog.AlertDialogUtil;
import com.android.tool.widget.loading.LoadingView;
import com.android.tool.widget.loading.LoadingViewOverwrite;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * class ：全部订单
 * author：York(wuchunyuan)
 * time  : 2018/6/19 10:05
 */
public class AllOrderFragment extends BaseFragments implements OrderAdapter.OnItemClickListener, SuperSwipeRefreshLayout.OnRefreshListener, LoadingView.OnRetryListener, BaseQuickAdapter.RequestLoadMoreListener {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SuperSwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.loadingView)
    LoadingView mLoadingView;
    private OrderAdapter mAdapter;
    private List<OrderBean.RowsBean> mList = new ArrayList<>();
    private String page = "1";
    private int refrashType = 1;

    @Override
    public void initParms(Bundle mBundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_all_order;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);//订阅
        mLoadingView = LoadingViewOverwrite.loadingGlobal(mLoadingView, this);
        //课程列表
        mSwipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new OrderAdapter(mActivity, R.layout.order_item, mList);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mAdapter.setOnLoadMoreListener(this, recyclerView);
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void doBusiness() {
        OkGo.<ObjectResponse<OrderBean>>get(PathUtil.getMyBookOrderList())
                .params(AppConfig.OrderList.PAGE_INDEX, page)
                .execute(new LoadingCallback<ObjectResponse<OrderBean>>(mActivity, mLoadingView) {
                    @Override
                    public void onSuccess(Response<ObjectResponse<OrderBean>> response) {
                        try {
                            OrderBean bean = response.body().data;
                            page = bean.getNextPageIndex();
                            if (refrashType == 0) {
                                mList.clear();
                                mAdapter.addData(bean.getRows());
                                mAdapter.notifyDataSetChanged();
                                mAdapter.setEnableLoadMore(true);
                                mSwipeRefreshLayout.setRefreshing(false);
                            } else {
                                mAdapter.addData(bean.getRows());
                            }
                            if (page.equals("-1")) {
                                mAdapter.loadMoreEnd();
                            } else {
                                mAdapter.loadMoreComplete();
                            }
                            LoadingCallbackUtil.isMyBookOrderListNullList(bean.getRows(), mLoadingView);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
    }

    @Override
    public void onRefresh() {
        if (refrashType != 0) {
            refrashType = 0;
        }
        mAdapter.setEnableLoadMore(false);
        page = "1";
        doBusiness();
    }

    @Override
    public void onLoadMoreRequested() {
        if (refrashType != 1) {
            refrashType = 1;
        }
        doBusiness();
    }

    /**
     * 取消支付点击事件
     *
     * @param bean
     * @param position
     */
    @Override
    public void onItemCancelPayClick(final OrderBean.RowsBean bean, final int position) {
        new AlertDialogUtil(mActivity).builder().setTitle("提示").setMsg("是否取消订单?")
                .setPositiveButton("是", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        OkGo.<String>get(PathUtil.getCancelOrder()).tag(this)
                                .params(AppConfig.OrderList.ORDER_ID, bean.getOrderId())
                                .execute(new StringDialogCallback(mActivity) {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        if (mAdapter != null) {
                                            mAdapter.remove(position);
                                            mAdapter.notifyItemChanged(position);
                                        }
                                    }
                                });
                    }
                }).setNegativeButton("否", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        }).show();
    }

    /**
     * 去支付点击事件
     *
     * @param bean
     * @param position
     */
    @Override
    public void onItemGoPayClick(OrderBean.RowsBean bean, int position) {
        IntentUtils.startPayPageActivity(mActivity, "",
                bean.getOrderId(), "", true);
    }

    /**
     * 去评价点击事件
     *
     * @param bean
     * @param position
     */
    @Override
    public void onItemCommentsClick(OrderBean.RowsBean bean, int position) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KeyUtil.COMMENTS_DETAILS_BEAN, bean);
//        IntentUtils.startIntentForResult(mActivity, CouserCommentsActivity.class, bundle, ResultUtil.COMMENTS_DETAILS_CODE);
    }

    /**
     * 二级列表点击事件
     *
     * @param bean
     * @param position
     */
    @Override
    public void onChildItemClick(OrderBean.RowsBean.DetailsBean bean, int position) {
        WebURLUtil.openUrl(bean.getUrl(), mActivity);
    }

    @Override
    public void onRetry() {
        onRefresh();
    }

    /**
     * 评价完成、支付完成 刷新
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(String event) {
        if (event == KeyUtil.UPDATE_COMMENTS) {
            onRefresh();
        } else if (event == KeyUtil.UPDATE_PAY) {
            onRefresh();
            L.i("全部订单-----pay");
        }
    }
}
