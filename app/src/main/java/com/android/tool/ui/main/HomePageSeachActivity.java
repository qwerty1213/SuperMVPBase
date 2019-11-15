package com.android.tool.ui.main;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.HomePageSeachBean;
import com.android.tool.presenter.HomePageSeachListPresenter;
import com.android.tool.presenter.impl.HomePageSeachListImpl;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.main.adapter.HomePageSeachAdapter;
import com.android.tool.ui.view.HomePageSeachListView;
import com.android.tool.ui.web.WebURLUtil;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.widget.CustomLoadMoreView;
import com.chad.library.adapter.base.BaseQuickAdapter;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：招教搜索
 * author：York(wuchunyuan)
 * time  : 2018/3/19 17:00
 */
public class HomePageSeachActivity extends BaseActivitys implements HomePageSeachListView, HomePageSeachAdapter.OnItemCouserClickListener, BaseQuickAdapter.RequestLoadMoreListener {
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    private HomePageSeachAdapter mAdapter;
    private List<HomePageSeachBean.RowsBean> mList = new ArrayList<>();
    private String page = "1";
    private String couserType;
    protected View viewNull;
    protected TextView txtNothingData;
    private HomePageSeachListPresenter mHomePageSeachListPresenter;

    @Override
    public void initParms(Bundle mBundle) {
        couserType = mBundle.getString(KeyUtil.COUSER_TYPE);
        steepSetStatusBarTranslucent(true, true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_for_teaching_seach;
    }

    @Override
    public void initView() {
        mHomePageSeachListPresenter = new HomePageSeachListImpl(this);
        viewNull = LayoutInflater.from(mActivity).inflate(R.layout.layout_null, null);
        txtNothingData = viewNull.findViewById(R.id.txt_nothing_data);
        txtNothingData.setText(R.string.nothing_content_error);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new HomePageSeachAdapter(mActivity, R.layout.home_seach_item, mList);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mAdapter.setOnLoadMoreListener(this, mRecyclerView);
        mAdapter.setOnItemCouserClickListener(this);
        etName.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
        etName.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                mList.clear();
                seachText();
                return true;
            }

        });
    }

    private void seachText() {
        String name = etName.getText().toString().trim();
        if (StringUtil.isNotBlankAndEmpty(name)) {
            mHomePageSeachListPresenter.getRequested(mActivity, 1, name);
        } else {
            showToast("搜索内容不能为空");
        }
    }

    @Override
    public void doBusiness() {

    }

    @OnClick(R.id.txt_cancel)
    public void onViewClicked() {
        onBackPressed();
    }

    @Override
    public void onItemCouserClick(HomePageSeachBean.RowsBean bean, int position) {
        if (bean != null)
            WebURLUtil.openUrl(bean.getUrl(), mActivity);
    }

    @Override
    public void onLoadMoreRequested() {
        mHomePageSeachListPresenter.getRequested(mActivity, mHomePageSeachListPresenter.getPage(), etName.getText().toString().trim());
    }

    @Override
    public void doHomePageSeachListResponse(HomePageSeachBean bean, int page) {
        mList.clear();
        mAdapter.addData(bean.getRows());
        mAdapter.notifyDataSetChanged();
        mAdapter.setEnableLoadMore(true);
        if (page == -1) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
        try {
            if (bean.getRows() != null && bean.getRows().size() <= 0) {
                mAdapter.addFooterView(viewNull);
            } else {
                mAdapter.removeFooterView(viewNull);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(HomePageSeachActivity.this.getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void LoadMoreResponse(HomePageSeachBean bean, int page) {
        mAdapter.addData(bean.getRows());
        if (page == -1) {
            mAdapter.loadMoreEnd();
        } else {
            mAdapter.loadMoreComplete();
        }
    }

    @Override
    public void onHide() {

    }
}
