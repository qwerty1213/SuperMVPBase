package com.android.tool.ui.pay.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.android.tool.R;
import com.android.tool.ui.pay.bean.PayBean;
import com.android.tool.util.GUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/4/11 10:56
 */
public class PayWayAdapter extends BaseQuickAdapter<PayBean.PayWayListBean, BaseViewHolder> {
    private Activity activity;

    public PayWayAdapter(Activity activity, int layoutResId, List<PayBean.PayWayListBean> data) {
        super(layoutResId, data);
        this.activity = activity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final PayBean.PayWayListBean item) {
        GUtils.loadIVStringFang(activity, item.getImageUrl(), (ImageView) helper.getView(R.id.iv_pay_away));
        helper.setText(R.id.txt_pay_away, item.getPayName());
        CheckBox checkBox = helper.getView(R.id.checkBox);
        if (item.getIsSelect().equals("1")) {
            checkBox.setChecked(true);
        } else {
            checkBox.setChecked(false);
        }
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
        void onItemClick(PayBean.PayWayListBean bean, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
