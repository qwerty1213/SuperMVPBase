package com.android.tool.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.AddressManagementModel;
import com.android.tool.presenter.AddressManagementListPresenter;
import com.android.tool.presenter.impl.AddressManagementListImpl;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.main.adapter.AddressManagementAdapter;
import com.android.tool.ui.view.AddressManagementListView;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.ResultUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.StringDialogCallback;
import com.android.tool.widget.CustomLoadMoreView;
import com.android.tool.widget.dialog.AlertDialogUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ethanhua.skeleton.SkeletonScreen;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：地址管理
 * author：York(wuchunyuan)
 * time  : 2018/7/2 09:38
 */
public class AddressManagementActivity extends BaseActivitys implements AddressManagementListView, AddressManagementAdapter.OnItemClickListener {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private AddressManagementAdapter mAdapter;
    private List<AddressManagementModel> mList = new ArrayList<>();
    private boolean isPage;
    private AddressManagementListPresenter mAddressManagementListPresenter;
    private View viewNull;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
        isPage = mBundle.getBoolean(KeyUtil.IS_ADDRESSMANAGEMENT_PAGE);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_address_management;
    }

    @Override
    public void initView() {
        txtTitle.setText(R.string.text_address_management);
        viewNull = LayoutInflater.from(mActivity).inflate(R.layout.layout_nothing_data, null);
        ((ImageView) viewNull.findViewById(R.id.iv_nothing)).setImageResource(R.drawable.address_list_notning_icon);
        ((TextView) viewNull.findViewById(R.id.txt_nothing)).setText(getResources().getText(R.string.there_is_no_address_please_add_new_address));
        mAddressManagementListPresenter = new AddressManagementListImpl(this);
        //课程列表
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        mAdapter = new AddressManagementAdapter(R.layout.address_management_item, mList);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        recyclerView.setAdapter(mAdapter);

        showSkeletonScreen(R.layout.ss_collection_list_item, recyclerView, mAdapter);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(this);
    }

    @Override
    public void doBusiness() {
        mAddressManagementListPresenter.getRequested(mActivity);
    }

    @OnClick({R.id.img_back, R.id.txt_add_address})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.txt_add_address://添加地址
                Bundle bundle = new Bundle();
                bundle.putString(KeyUtil.ADDRESS_ID, "");
                IntentUtils.startIntentForResult(mActivity, EditorOrAddAddressActivity.class,
                        bundle, ResultUtil.ADDRESSMANAGEMENT_CODE);
                break;
        }
    }


    /**
     * 支付选择地址
     *
     * @param bean
     * @param position
     */
    @Override
    public void onItemClick(AddressManagementModel bean, int position) {
        if (isPage) {
            Intent intent = new Intent();
            intent.putExtra(KeyUtil.ADDRESS_MANAGEMENT_BEAN, bean);
            setResult(ResultUtil.ADDRESSMANAGEMENT_CODE, intent);
            finish();
        }
    }

    /**
     * 编辑地址
     *
     * @param bean
     * @param position
     */
    @Override
    public void onEditeClick(AddressManagementModel bean, int position) {
        Bundle bundle = new Bundle();
        bundle.putString(KeyUtil.ADDRESS_ID, bean.getId());
        IntentUtils.startIntentForResult(mActivity, EditorOrAddAddressActivity.class, bundle, ResultUtil.ADDRESSMANAGEMENT_CODE);
    }

    /**
     * 删除地址
     *
     * @param bean
     * @param position
     */
    @Override
    public void onDeleteClick(final AddressManagementModel bean, final int position) {
        new AlertDialogUtil(mActivity).builder().setTitle("提示").setMsg("是否删除地址?").setPositiveButton("是", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OkGo.<String>get(PathUtil.setDeleteAddress()).tag(this).params(AppConfig.AddressList.ADDRESS_ID, bean.getId())
                        .execute(new StringDialogCallback(mActivity) {
                            @Override
                            public void onSuccess(Response<String> response) {
                                mAdapter.remove(position);
                                if (mList != null && mList.size() == 0) {
                                    doBusiness();
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
     * 设置默认地址
     *
     * @param bean
     * @param position
     */
    @Override
    public void onDefultClick(AddressManagementModel bean, final int position) {
        OkGo.<String>get(PathUtil.setDefaultAddress()).tag(this)
                .params(AppConfig.AddressList.ADDRESS_ID, bean.getId())
                .execute(new StringDialogCallback(mActivity) {
                    @Override
                    public void onSuccess(Response<String> response) {
                        doBusiness();
                        mAdapter.notifyItemChanged(position);
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ResultUtil.ADDRESSMANAGEMENT_CODE) {
            if (resultCode == ResultUtil.ADDRESSMANAGEMENT_CODE) {
                doBusiness();
            }
        }
    }

    @Override
    public void doAddressManagementListResponse(List<AddressManagementModel> model) {
        mList.clear();
        mAdapter.addData(model);
        try {
            if (model.size() <= 0) {
                mAdapter.addFooterView(viewNull);
            } else {
                mAdapter.removeFooterView(viewNull);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public SkeletonScreen skeletonScreen() {
        return skeletonScreen;
    }

    @Override
    public void onHide() {

    }
}
