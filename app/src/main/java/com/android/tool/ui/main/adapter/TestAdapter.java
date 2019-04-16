package com.android.tool.ui.main.adapter;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import com.android.tool.R;
import com.android.tool.model.TestModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;
import java.util.Random;


/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/9/26 16:44
 */
public class TestAdapter extends BaseQuickAdapter<TestModel.RowsBean, BaseViewHolder> {
    private Activity mActivity;
    int[] mArray = {R.mipmap.test1, R.mipmap.test2, R.mipmap.test3, R.mipmap.test4, R.mipmap.test5, R.mipmap.test6};

    public TestAdapter(Activity mActivity, int layoutResId, List<TestModel.RowsBean> data) {
        super(layoutResId, data);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final TestModel.RowsBean item) {
        helper.setText(R.id.textView, item.getName() + "\n所有人都以为霍阑厌恶她，连她自己也这么认为，却没人知道霍阑想了她三年，念了她三年，终于失控了……");

        int index = new Random().nextInt(6);
        ((ImageView) helper.getView(R.id.iv_bg)).setImageResource(mArray[index]);

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