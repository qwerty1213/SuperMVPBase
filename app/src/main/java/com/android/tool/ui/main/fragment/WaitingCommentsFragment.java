package com.android.tool.ui.main.fragment;



import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.android.tool.R;
import com.android.tool.ui.base.BaseFragments;
import com.android.tool.ui.web.WebURLUtil;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.ResultUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.StringDialogCallback;
import com.android.tool.widget.CustomLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.footer.LoadingView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import java.util.ArrayList;
import butterknife.BindView;

/**
 * class ：待支付
 * author：York(wuchunyuan)
 * time  : 2018/6/19 09:32
 */
public class WaitingCommentsFragment extends BaseFragments implements OrderAdapter.OnItemClickListener, SuperSwipeRefreshLayout.OnRefreshListener, LoadingView.OnRetryListener, BaseQuickAdapter.RequestLoadMoreListener {

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
        mLoadingView = LoadingViewOverwrite.loadingGlobal(mLoadingView, this);
        //课程列表
        mSwipeRefreshLayout.setOnRefreshListener(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new OrderAdapter(mActivity, R.layout.order_item, mList);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        recyclerView.setAdapter(mAdapter);
        EventBus.getDefault().register(this);//订阅
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
                .params(AppConfig.OrderList.STATUS, OrderBean.RECEIVE)//receive 待评价
                .execute(new LoadingCallback<ObjectResponse<OrderBean>>(mActivity, mLoadingView) {
                    @Override
                    public void onSuccess(Response<ObjectResponse<OrderBean>> response) {
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
    public void onItemCancelPayClick(OrderBean.RowsBean bean, final int position) {
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
        IntentUtils.startIntentForResult(mActivity, CouserCommentsActivity.class, bundle,
                ResultUtil.COMMENTS_DETAILS_CODE);
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
     * 评价完成 刷新
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(String event) {
        if (event == KeyUtil.UPDATE_COMMENTS) {
            onRefresh();
        }
    }

}
