package com.android.tool.ui.pay.adapter;

import android.view.View;

import com.android.tool.R;
import com.android.tool.ui.pay.bean.PayBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/14 10:21
 */
public class PayCouserInfomatiomAdapter extends BaseQuickAdapter<PayBean.ProductsBean, BaseViewHolder> {
    public PayCouserInfomatiomAdapter(int layoutResId, List<PayBean.ProductsBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PayBean.ProductsBean item) {
        helper.setText(R.id.txt_couser_name, item.getName());
        helper.setText(R.id.txt_price, item.getPriceStr());

        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemPayCouserClickListener != null) {
                    mOnItemPayCouserClickListener.onItemPayCouserClick(item, helper.getAdapterPosition());
                }
            }
        });
    }

    public interface OnItemPayCouserClickListener {
        void onItemPayCouserClick(PayBean.ProductsBean bean, int position);
    }

    private OnItemPayCouserClickListener mOnItemPayCouserClickListener;

    public void setOnItemPayCouserClickListener(OnItemPayCouserClickListener mOnItemPayCouserClickListener) {
        this.mOnItemPayCouserClickListener = mOnItemPayCouserClickListener;
    }
}