package com.android.tool.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import com.android.tool.R;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.main.fragment.AllOrderFragment;
import com.android.tool.ui.main.fragment.CompleteFragment;
import com.android.tool.ui.main.fragment.WaitingCommentsFragment;
import com.android.tool.ui.main.fragment.WaitingPayFragment;
import com.android.tool.util.L;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：我的订单
 * author：York(wuchunyuan)
 * time  : 2018/4/12 11:11
 */
public class MyOrderActivity extends BaseActivitys implements OnTabSelectListener {

    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    private final String[] mTitles = {"全部订单", "待支付", "待评价", "已完成"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_my_order;
    }

    @Override
    public void initView() {
        mFragments.add(new AllOrderFragment());
        mFragments.add(new WaitingPayFragment());
        mFragments.add(new WaitingCommentsFragment());
        mFragments.add(new CompleteFragment());
        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mSlidingTabLayout.setViewPager(mViewPager);
        mSlidingTabLayout.setOnTabSelectListener(this);
        mViewPager.setOffscreenPageLimit(mFragments.size());
//        mSlidingTabLayout.showDot(2);
//        mSlidingTabLayout.hideMsg(2);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {
    }

    @Override
    public void onTabSelect(int position) {
        L.i("onTabSelect&position--->" + position);
    }

    @Override
    public void onTabReselect(int position) {
        L.i("onTabReselect&position--->" + position);
    }


    @OnClick(R.id.img_back)
    public void onViewClicked() {
        onBackPressed();
    }

    private class MyPagerAdapter extends FragmentStatePagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    public interface OnActivityResult {
        void onActivityResultComments(int requestCode, int resultCode, Intent data);

        void onActivityResultPay(int requestCode, int resultCode, Intent data);
    }

    private OnActivityResult onActivityResult;

    public void setOnActivityResult(OnActivityResult onActivityResult) {
        this.onActivityResult = onActivityResult;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        L.i("onDestroy");
        EventBus.getDefault().unregister(this);//解除订阅
    }
}
