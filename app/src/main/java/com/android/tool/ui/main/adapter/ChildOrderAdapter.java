package com.android.tool.ui.main.adapter;

import android.app.Activity;
import android.view.View;

import com.android.tool.R;
import com.android.tool.model.OrderBean;
import com.android.tool.util.GUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/19 11:24
 */
public class ChildOrderAdapter extends BaseQuickAdapter<OrderBean.RowsBean.DetailsBean, BaseViewHolder> {
    private Activity mActivity;

    public ChildOrderAdapter(Activity mActivity, int layoutResId, List<OrderBean.RowsBean.DetailsBean> data) {
        super(layoutResId, data);
        this.mActivity = mActivity;
    }

    /**
     * @param helper
     * @param item
     */
    @Override
    protected void convert(final BaseViewHolder helper, final OrderBean.RowsBean.DetailsBean item) {
        helper.setText(R.id.txt_course_name, item.getName());
        helper.setText(R.id.txt_course_price, item.getPriceStr());
        RoundedImageView ivCourse = helper.getView(R.id.iv_course);
        GUtils.loadIVStringListChang(mActivity, item.getImgUrl(), ivCourse);

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnChildItemClickListener != null) {
                    mOnChildItemClickListener.onChildItemClick(item, helper.getAdapterPosition());
                }
            }
        });
    }

    public interface OnChildItemClickListener {
        void onChildItemClick(OrderBean.RowsBean.DetailsBean bean, int position);
    }

    private OnChildItemClickListener mOnChildItemClickListener;

    public void setOnChildItemClickListener(OnChildItemClickListener mOnChildItemClickListener) {
        this.mOnChildItemClickListener = mOnChildItemClickListener;
    }
}