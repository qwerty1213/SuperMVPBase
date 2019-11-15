package com.android.tool.ui.main.adapter;

import android.app.Activity;
import android.graphics.Color;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.HomePageSeachBean;
import com.android.tool.util.GUtils;
import com.android.tool.util.StringUtil;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedImageView;


import java.util.List;

/**
 * class ：首页搜索
 * author：York(wuchunyuan)
 * time  : 2018/3/19 10:35
 */
public class HomePageSeachAdapter extends BaseQuickAdapter<HomePageSeachBean.RowsBean, BaseViewHolder> {
    private Activity mActivity;

    public HomePageSeachAdapter(Activity mActivity, int layoutResId, List<HomePageSeachBean.RowsBean> data) {
        super(layoutResId, data);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HomePageSeachBean.RowsBean item) {
        RoundedImageView ivCourse = helper.getView(R.id.iv_course);
        GUtils.loadIVStringListChang(mActivity, item.getCoverImgUrl(), ivCourse);
        TextView txName = helper.getView(R.id.txt_course_name);
        TextView txtTips = helper.getView(R.id.txt_tips);
        txtTips.setText(item.getObjType());

        //标签
        String tag = item.getObjType();
        if (StringUtil.isNotBlankAndEmpty(tag)) {
            txtTips.setVisibility(View.VISIBLE);
            //tips + "占" 实现首行缩进占位效果
            SpannableStringBuilder span = new SpannableStringBuilder(tag + "占" + item.getName());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, tag.length() + 1, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            txName.setText(span);
        } else {
            txtTips.setVisibility(View.GONE);
            SpannableStringBuilder span = new SpannableStringBuilder(item.getName());
            span.setSpan(new ForegroundColorSpan(Color.TRANSPARENT), 0, 0, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
            txName.setText(span);
        }


        helper.setText(R.id.txt_study_num, item.getSalesStr());
        helper.setText(R.id.txt_course_price, item.getPriceStr());
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemCouserClickListener != null) {
                    mOnItemCouserClickListener.onItemCouserClick(item, helper.getAdapterPosition());
                }
            }
        });
    }

    public interface OnItemCouserClickListener {
        void onItemCouserClick(HomePageSeachBean.RowsBean bean, int position);
    }

    private OnItemCouserClickListener mOnItemCouserClickListener;

    public void setOnItemCouserClickListener(OnItemCouserClickListener mOnItemCouserClickListener) {
        this.mOnItemCouserClickListener = mOnItemCouserClickListener;
    }
}