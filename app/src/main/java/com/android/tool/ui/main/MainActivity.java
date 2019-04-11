package com.android.tool.ui.main;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.tool.R;
import com.android.tool.model.TestModel;
import com.android.tool.presenter.TestPresenter;
import com.android.tool.presenter.impl.TestImpl;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.main.adapter.TestAdapter;
import com.android.tool.ui.view.TestBaseView;
import com.android.tool.widget.CustomLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivitys implements TestBaseView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<TestModel.RowsBean> mList = new ArrayList<>();
    private TestAdapter mAdapter;
    private TestPresenter testPresenter;

    @Override
    public void initParms(Bundle mBundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        testPresenter = new TestImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new TestAdapter(mActivity, R.layout.test_item, mList);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecyclerView.setAdapter(mAdapter);
        showToolbar().setBack().setTitle("UTools");
    }

    @Override
    public void initListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }

    @Override
    public void doBusiness() {
        onRefresh();
    }

    @Override
    public void onRefresh() {
        testPresenter.getRequested(mActivity, 1);
    }

    @Override
    public void onLoadMoreRequested() {
        testPresenter.getRequested(mActivity, testPresenter.getPage());
    }

    @Override
    public void testResponse(TestModel bean, int page) {
        mList.clear();
        mAdapter.addData(bean.getRows());
        mAdapter.notifyDataSetChanged();
        mAdapter.setEnableLoadMore(true);
        mSwipeRefreshLayout.setRefreshing(false);
        if (page == -1) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void LoadMoreResponse(TestModel bean, int page) {
        mAdapter.addData(bean.getRows());
        if (page == -1) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errMsg) {

    }
}
