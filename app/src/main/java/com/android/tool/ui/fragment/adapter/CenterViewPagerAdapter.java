package com.android.tool.ui.fragment.adapter;


import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.makeramen.roundedimageview.RoundedImageView;

import java.util.List;

public class CenterViewPagerAdapter extends PagerAdapter {
//    @Override
//    public float getPageWidth(int position) {
//       return 0.9f;
//    }

    private List<RoundedImageView> pagerList;
    private Context mContext;

    public CenterViewPagerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setMiddleBannerList(List<RoundedImageView> pagerList) {
        this.pagerList = pagerList;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return pagerList == null ? 0 : pagerList.size();
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ((ViewPager) container).addView(pagerList.get(position));
        return pagerList.get(position);
    }

    @Override
    public void destroyItem(View container, int position, Object view) {
        ((ViewPager) container).removeView(pagerList.get(position));
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Parcelable saveState() {
        return null;
    }

    @Override
    public void restoreState(Parcelable arg0, ClassLoader arg1) {
    }

    @Override
    public void startUpdate(View arg0) {
    }

    @Override
    public void finishUpdate(View arg0) {
    }
}

