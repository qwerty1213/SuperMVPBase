package com.android.tool.ui.main.adapter;

import android.app.Activity;
import android.view.View;

import com.android.tool.R;
import com.android.tool.model.TestModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;


/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/9/26 16:44
 */
public class TestAdapter extends BaseQuickAdapter<TestModel.RowsBean, BaseViewHolder> {
    private Activity mActivity;

    public TestAdapter(Activity mActivity, int layoutResId, List<TestModel.RowsBean> data) {
        super(layoutResId, data);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final TestModel.RowsBean item) {
        helper.setText(R.id.textView, item.getName());
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
        void onItemClick(TestModel.RowsBean bean, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}