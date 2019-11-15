package com.android.tool.ui.fragment.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;

import com.android.tool.R;
import com.android.tool.model.HomePageBean;
import com.android.tool.util.GUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/5/30 17:17
 */
public class HomePageCenterAdapter extends BaseQuickAdapter<HomePageBean.FunctionButtonBean, BaseViewHolder> {
    private Activity activity;

    public HomePageCenterAdapter(Activity activity, int layoutResId, List<HomePageBean.FunctionButtonBean> data) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HomePageBean.FunctionButtonBean item) {
        helper.setText(R.id.txt_name, item.getName());
        GUtils.loadIVStringFang(activity, item.getImgUrl(), (ImageView) helper.getView(R.id.iv_name));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(item, helper.getAdapterPosition());
                }
            }
        });
    }

    public interface OnItemClickListener {
        void onItemClick(HomePageBean.FunctionButtonBean bean, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}