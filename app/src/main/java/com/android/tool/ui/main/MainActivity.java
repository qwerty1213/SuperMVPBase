package com.android.tool.ui.main;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.main.fragment.Fragment0;
import com.android.tool.ui.main.fragment.Fragment1;
import com.android.tool.ui.main.fragment.Fragment2;
import com.android.tool.ui.main.fragment.Fragment3;
import com.ycl.tabview.library.TabView;
import com.ycl.tabview.library.TabViewChild;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class MainActivity extends BaseActivitys implements TabView.OnTabChildClickListener {
    @BindView(R.id.tabView)
    TabView tabView;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent();
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        //start add data
        List<TabViewChild> tabViewChildList = new ArrayList<>();
        TabViewChild tabViewChild00 = new TabViewChild(R.mipmap.tab01_sel, R.mipmap.tab01_unsel, "首页", Fragment0.newInstance("首页"));
        TabViewChild tabViewChild01 = new TabViewChild(R.mipmap.tab02_sel, R.mipmap.tab02_unsel, "分类", Fragment1.newInstance("分类"));
        TabViewChild tabViewChild02 = new TabViewChild(R.mipmap.tab03_sel, R.mipmap.tab03_unsel, "资讯", Fragment2.newInstance("资讯"));
        TabViewChild tabViewChild03 = new TabViewChild(R.mipmap.tab05_sel, R.mipmap.tab05_unsel, "我的", Fragment3.newInstance("我的"));
        tabViewChildList.add(tabViewChild00);
        tabViewChildList.add(tabViewChild01);
        tabViewChildList.add(tabViewChild02);
        tabViewChildList.add(tabViewChild03);
        //end add data
        tabView.setTabViewDefaultPosition(0);
        tabView.setTabViewChild(tabViewChildList, getSupportFragmentManager());
    }

    @Override
    public void initListener() {
        tabView.setOnTabChildClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    /**
     * tab切换监听
     * @param position
     * @param imageView
     * @param textView
     */
    @Override
    public void onTabChildClick(int position, ImageView imageView, TextView textView) {

    }
}
