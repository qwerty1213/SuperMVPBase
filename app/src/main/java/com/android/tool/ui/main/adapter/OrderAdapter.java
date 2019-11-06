package com.android.tool.ui.main.adapter;


import android.app.Activity;
import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.OrderBean;
import com.android.tool.util.T;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/19 11:24
 */
public class OrderAdapter extends BaseQuickAdapter<OrderBean.RowsBean, BaseViewHolder> implements ChildOrderAdapter.OnChildItemClickListener {
    private List<OrderBean.RowsBean.DetailsBean> mChildList = new ArrayList<>();
    private Activity mActivity;

    public OrderAdapter(Activity mActivity, int layoutResId, List<OrderBean.RowsBean> data) {
        super(layoutResId, data);
        this.mActivity = mActivity;
    }

    /**
     * 逻辑判断
     * init 显示 取消订单和去支付 ke
     * pay 显示 待发货 不能点击
     * send 显示待收货 不能点击
     * receive 显示待评价 点击去评价
     * complete 已完成 不能点击
     * cancel 已取消 不能点击
     * close 已关闭 不能点击
     *
     * @param helper
     * @param item
     */
    @Override
    protected void convert(final BaseViewHolder helper, final OrderBean.RowsBean item) {
        helper.setText(R.id.txt_time, item.getCreateTime());
        final TextView txtOrderNumber = helper.getView(R.id.txt_order_number);
        txtOrderNumber.setText("订单号: " + item.getOrderId());
        helper.setText(R.id.txt_order_name, "订单名称: " + item.getName());
        helper.setText(R.id.txt_pay_num, item.getCurrentTotal());
        ButtonState btCopy = helper.getView(R.id.bt_copy);
        RecyclerView recyclerView = helper.getView(R.id.recyclerView);
        addChildItem(item, recyclerView);
        TextView txtCancelPay = helper.getView(R.id.txt_cancel_pay);
        TextView txtGoPay = helper.getView(R.id.txt_go_pay);
        switch (item.getStatus()) {// 显示 取消订单和去支付
            case OrderBean.INIT:
                txtCancelPay.setVisibility(View.VISIBLE);
                txtGoPay.setVisibility(View.VISIBLE);
                txtGoPay.setEnabled(true);
                txtGoPay.setText(item.getStatusName());
                txtGoPay.setBackgroundResource(R.drawable.go_pay_yuanjiaokuang);
                txtGoPay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case OrderBean.PAY://显示 待发货 不能点击
                txtCancelPay.setVisibility(View.INVISIBLE);
                txtGoPay.setVisibility(View.VISIBLE);
                txtGoPay.setEnabled(false);
                txtGoPay.setText(item.getStatusName());
                txtGoPay.setBackgroundResource(R.drawable.cancel_pay_complete_yuanjiaokuang);
                txtGoPay.setTextColor(ContextCompat.getColor(mContext, R.color.c_bcbc));
                break;
            case OrderBean.SEND://显示 待收货 不能点击
                txtCancelPay.setVisibility(View.INVISIBLE);
                txtGoPay.setVisibility(View.VISIBLE);
                txtGoPay.setEnabled(false);
                txtGoPay.setText(item.getStatusName());
                txtGoPay.setBackgroundResource(R.drawable.cancel_pay_complete_yuanjiaokuang);
                txtGoPay.setTextColor(ContextCompat.getColor(mContext, R.color.c_bcbc));
                break;
            case OrderBean.RECEIVE://显示 待评价 点击去评价
                txtCancelPay.setVisibility(View.INVISIBLE);
                txtGoPay.setVisibility(View.VISIBLE);
                txtGoPay.setEnabled(true);
                txtGoPay.setText(item.getStatusName());
                txtGoPay.setBackgroundResource(R.drawable.go_pay_yuanjiaokuang);
                txtGoPay.setTextColor(ContextCompat.getColor(mContext, R.color.white));
                break;
            case OrderBean.COMPLETE://显示 已完成 不能点击
                txtCancelPay.setVisibility(View.INVISIBLE);
                txtGoPay.setVisibility(View.VISIBLE);
                txtGoPay.setEnabled(false);
                txtGoPay.setText(item.getStatusName());
                txtGoPay.setBackgroundResource(R.drawable.cancel_pay_complete_yuanjiaokuang);
                txtGoPay.setTextColor(ContextCompat.getColor(mContext, R.color.c_bcbc));
                break;
            case OrderBean.CANCEL://显示 已取消 不能点击
                txtCancelPay.setVisibility(View.INVISIBLE);
                txtGoPay.setVisibility(View.VISIBLE);
                txtGoPay.setEnabled(false);
                txtGoPay.setText(item.getStatusName());
                txtGoPay.setBackgroundResource(R.drawable.cancel_pay_complete_yuanjiaokuang);
                txtGoPay.setTextColor(ContextCompat.getColor(mContext, R.color.c_bcbc));
                break;
            case OrderBean.CLOSE://显示 已关闭 不能点击
                txtCancelPay.setVisibility(View.INVISIBLE);
                txtGoPay.setVisibility(View.VISIBLE);
                txtGoPay.setEnabled(false);
                txtGoPay.setText(item.getStatusName());
                txtGoPay.setBackgroundResource(R.drawable.cancel_pay_complete_yuanjiaokuang);
                txtGoPay.setTextColor(ContextCompat.getColor(mContext, R.color.c_bcbc));
                break;
            default:
                break;
        }
        btCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager copy = (ClipboardManager) mActivity.getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(item.getOrderId());
                T.customToastCenterShort(mActivity,"复制成功");
            }
        });
        txtCancelPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemCancelPayClick(item, helper.getAdapterPosition());
                }
            }
        });
        txtGoPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (item.getStatus().equals(OrderBean.INIT)) {//去支付
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemGoPayClick(item, helper.getAdapterPosition());
                    }
                } else if (item.getStatus().equals(OrderBean.RECEIVE)) {//待评价
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemCommentsClick(item, helper.getAdapterPosition());
                    }
                }
            }
        });
    }

    /**
     * 二级
     *
     * @param item
     * @param recyclerView
     */
    private void addChildItem(OrderBean.RowsBean item, RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        ChildOrderAdapter mAdapter = new ChildOrderAdapter(mActivity, R.layout.child_order_item, mChildList);
        mAdapter.openLoadAnimation(ALPHAIN);
        recyclerView.setAdapter(mAdapter);
        mChildList.clear();
        mAdapter.addData(item.getDetails());
        mAdapter.setOnChildItemClickListener(this);
    }

    /**
     * 二级列表点击
     *
     * @param bean
     * @param position
     */
    @Override
    public void onChildItemClick(OrderBean.RowsBean.DetailsBean bean, int position) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onChildItemClick(bean, position);
        }
    }

    public interface OnItemClickListener {

        void onItemCancelPayClick(OrderBean.RowsBean bean, int position);

        void onItemGoPayClick(OrderBean.RowsBean bean, int position);

        void onItemCommentsClick(OrderBean.RowsBean bean, int position);

        void onChildItemClick(OrderBean.RowsBean.DetailsBean bean, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}