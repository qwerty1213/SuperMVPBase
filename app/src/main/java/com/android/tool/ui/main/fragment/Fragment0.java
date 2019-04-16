package com.android.tool.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.android.tool.R;
import com.android.tool.model.TestModel;
import com.android.tool.presenter.TestPresenter;
import com.android.tool.presenter.impl.TestImpl;
import com.android.tool.ui.base.BaseFragments;
import com.android.tool.ui.main.adapter.TestAdapter;
import com.android.tool.ui.view.TestBaseView;
import com.android.tool.widget.CustomLoadMoreView;
import com.android.tool.widget.dialog.ActionSheetDialog;
import com.android.tool.widget.dialog.AlertDialogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

import static com.android.tool.widget.dialog.ActionSheetDialog.SheetItemColor.Black;


/**
 * @author York(wuchunyuan)
 * @Created 2019/4/16.
 */
public class Fragment0 extends BaseFragments implements TestAdapter.OnItemClickListener, TestBaseView, SwipeRefreshLayout.OnRefreshListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout mSwipeRefreshLayout;
    private List<TestModel.RowsBean> mList = new ArrayList<>();
    private TestAdapter mAdapter;
    private TestPresenter testPresenter;

    public static Fragment0 newInstance(String text) {
        Fragment0 fragmentCommon = new Fragment0();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Override
    public void initParms(Bundle mBundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_layout0;
    }

    @Override
    public void initView() {
        testPresenter = new TestImpl(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new TestAdapter(mActivity, R.layout.test_item, mList);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
    }


    @Override
    public void doBusiness() {
        onRefresh();
    }

    /**
     * 下拉触发回调
     */
    @Override
    public void onRefresh() {
        testPresenter.getRequested(mActivity, 1);
    }

    /**
     * 上拉触发回调
     */
    @Override
    public void onLoadMoreRequested() {
        testPresenter.getRequested(mActivity, testPresenter.getPage());
    }

    /**
     * 下拉加载返回数据
     *
     * @param bean
     * @param page
     */
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

    /**
     * 上拉加载返回数据
     *
     * @param bean
     * @param page
     */
    @Override
    public void loadMoreResponse(TestModel bean, int page) {
        mAdapter.addData(bean.getRows());
        if (page == -1) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void showLoading() {
        showDialogLoading();
    }


    @Override
    public void hideLoading() {
        dismissDialogLoading();
    }

    @Override
    public void showError(String errMsg) {
        dismissDialogLoading();
    }

    @Override
    public void onItemClick(TestModel.RowsBean bean, int position) {
        if (position == 0) {
            new ActionSheetDialog(mActivity).builder()
                    .addSheetItem("Item0", Black, new ActionSheetDialog.OnSheetItemClickListener() {
                        @Override
                        public void onClick(int which) {

                        }
                    }).addSheetItem("Item1", Black, new ActionSheetDialog.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {

                }
            }).addSheetItem("Item2", Black, new ActionSheetDialog.OnSheetItemClickListener() {
                @Override
                public void onClick(int which) {

                }
            }).show();
        } else {
            new AlertDialogUtil(mActivity).builder().setTitle("提示").setMsg("是否确定？")
                    .setPositiveButton("是", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                        }
                    }).setNegativeButton("否", new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                }
            }).show();
        }
    }
}