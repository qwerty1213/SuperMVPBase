package com.android.tool.ui.login;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.login.fragment.PasswordLoginFragment;
import com.android.tool.ui.login.fragment.VerificationCodeFragment;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.ResultUtil;
import com.flyco.tablayout.SlidingTabLayout;
import com.flyco.tablayout.listener.OnTabSelectListener;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/5/22 09:45
 */
public class LoginFragmentActivity extends BaseActivitys implements OnTabSelectListener {
    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.txt_title)
    TextView txtTitle;
    private final String[] mTitles = {"密码登录", "验证码登录"};
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private MyPagerAdapter mAdapter;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
        ActivityManagementUtil.getInstance().addLoginActivity(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_fragment_login;
    }

    @Override
    public void initView() {
//        txtTitle.setText(R.string.login);
        mFragments.add(new PasswordLoginFragment());
        mFragments.add(new VerificationCodeFragment());
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

    public void setRefreshResult() {
        setResult(ResultUtil.LOGING_RESULT);
    }

    @Override
    public void onTabSelect(int position) {
    }

    @Override
    public void onTabReselect(int position) {
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

    @OnClick(R.id.img_back)
    public void onViewClicked() {
        onBackPressed();
    }

}
