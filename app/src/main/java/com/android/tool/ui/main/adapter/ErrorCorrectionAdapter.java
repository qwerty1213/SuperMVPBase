package com.android.tool.ui.main.adapter;


import android.view.View;
import android.widget.CheckBox;

import com.android.tool.R;
import com.android.tool.model.ErrorCorrectionBean;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;


import java.util.List;

/**
 * class ：地区适配器
 * author：York(wuchunyuan)
 * time  : 2018/4/4 17:09
 */
public class ErrorCorrectionAdapter extends BaseQuickAdapter<ErrorCorrectionBean, BaseViewHolder> {
    public ErrorCorrectionAdapter(int layoutResId, List<ErrorCorrectionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final ErrorCorrectionBean item) {
        helper.setText(R.id.txt_error_name, item.getErrorName());
        CheckBox checkBox = helper.getView(R.id.checkBox);
        if (item.isChecked()) {
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
        void onItemClick(ErrorCorrectionBean bean, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}