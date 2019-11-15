package com.android.tool.ui.fragment.adapter;


import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.android.tool.R;
import com.android.tool.model.HomePageBean;
import com.android.tool.util.GUtils;
import com.android.tool.util.ScreenUtil;
import com.android.tool.widget.MTextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import com.zhy.autolayout.AutoLinearLayout;

import java.util.List;

/**
 * class ：首页数据适配器
 * author：York(wuchunyuan)
 * time  : 2018/3/6 11:44
 */
public class HomePageAdapter224 extends BaseQuickAdapter<HomePageBean.RecommendBean, BaseViewHolder> {
    private Activity mActivity;

    public HomePageAdapter224(Activity mActivity, int layoutResId, List<HomePageBean.RecommendBean> data) {
        super(layoutResId, data);
        this.mActivity = mActivity;
    }

    @Override
    protected void convert(final BaseViewHolder helper, final HomePageBean.RecommendBean item) {
//        helper.setText(R.id.txt_course_name, item.getName());
        MTextView txtCourseName = helper.getView(R.id.txt_course_name);
        txtCourseName.setMText(item.getName());
        txtCourseName.setTextColor(ContextCompat.getColor(mContext, R.color.text_black));
        txtCourseName.setTextSize(14);
        txtCourseName.setMaxLines(2);
        txtCourseName.setEllipsize(TextUtils.TruncateAt.END);
        helper.setText(R.id.txt_course_price, "¥ " + item.getPriceStr());
        helper.setText(R.id.txt_tag, item.getTag());
        GUtils.loadIVStringListChang(mActivity, item.getImgUrl(), (ImageView) helper.getView(R.id.iv_course));
        helper.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(item, helper.getAdapterPosition());
                }
            }
        });
        //设置列间距
        AutoLinearLayout llItem = helper.getView(R.id.ll_item);
        if (helper.getAdapterPosition() % 2 == 0) {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(llItem.getLayoutParams());
            lp.setMargins(ScreenUtil.dip2px(mActivity, 8), 0, ScreenUtil.dip2px(mActivity, 15), 0);
            llItem.setLayoutParams(lp);
        } else {
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(llItem.getLayoutParams());
            lp.setMargins(ScreenUtil.dip2px(mActivity, 15), 0, ScreenUtil.dip2px(mActivity, 8), 0);
            llItem.setLayoutParams(lp);
        }

    }

    public interface OnItemClickListener {
        void onItemClick(HomePageBean.RecommendBean bean, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}