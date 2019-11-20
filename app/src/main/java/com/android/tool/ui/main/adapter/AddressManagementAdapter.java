package com.android.tool.ui.main.adapter;

import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.AddressManagementModel;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/7/2 15:13
 */
public class AddressManagementAdapter extends BaseQuickAdapter<AddressManagementModel, BaseViewHolder> {
    public AddressManagementAdapter(int layoutResId, List<AddressManagementModel> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final AddressManagementModel item) {
        helper.setText(R.id.txt_name, "收货人：" + item.getRealname());
        helper.setText(R.id.txt_mobile, item.getMobile());
        RelativeLayout llDefult = helper.getView(R.id.ll_defult);
        CheckBox checkBox = helper.getView(R.id.checkBox);
        TextView txtEditor = helper.getView(R.id.txt_editor);
        TextView txtDelete = helper.getView(R.id.txt_delete);
        //标签
        TextView txtDefaultLabel = helper.getView(R.id.txt_default_label);
        String txtDefaultTag = txtDefaultLabel.getText().toString();
        TextView txtAddress = helper.getView(R.id.txt_address);
        if (item.getIsdefault().equals("0")) {
            checkBox.setChecked(false);
            txtDefaultLabel.setVisibility(View.GONE);
            SpannableStringBuilder span = new SpannableStringBuilder(item.getDetailArea());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 0, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            txtAddress.setText(span);
        } else {
            checkBox.setChecked(true);
            txtDefaultLabel.setVisibility(View.VISIBLE);
            //tips + "占" 实现首行缩进占位效果
            SpannableStringBuilder span = new SpannableStringBuilder(txtDefaultTag + "占占" + item.getDetailArea());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, txtDefaultTag.length() + 2, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            txtAddress.setText(span);
        }

        txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onDeleteClick(item, helper.getAdapterPosition());
                }
            }
        });
        txtEditor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onEditeClick(item, helper.getAdapterPosition());
                }
            }
        });
        llDefult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onDefultClick(item, helper.getAdapterPosition());
                }
            }
        });
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
        void onItemClick(AddressManagementModel bean, int position);

        void onEditeClick(AddressManagementModel bean, int position);

        void onDeleteClick(AddressManagementModel bean, int position);

        void onDefultClick(AddressManagementModel bean, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}