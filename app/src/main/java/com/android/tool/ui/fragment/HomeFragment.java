package com.android.tool.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.HomePageBean;
import com.android.tool.presenter.HomePagePresenter;
import com.android.tool.presenter.impl.HomePageImpl;
import com.android.tool.ui.base.BaseFragments;
import com.android.tool.ui.fragment.adapter.CenterViewPagerAdapter;
import com.android.tool.ui.fragment.adapter.HomePageAdapter224;
import com.android.tool.ui.fragment.adapter.HomePageCenterAdapter;
import com.android.tool.ui.main.HomePageSeachActivity;
import com.android.tool.ui.main.LocationCityListActivity;
import com.android.tool.ui.main.MainActivity;
import com.android.tool.ui.view.HomePageView;
import com.android.tool.ui.web.WebURLUtil;
import com.android.tool.util.DataUtils;
import com.android.tool.util.GUtils;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.L;
import com.android.tool.util.PUtil;
import com.android.tool.util.ResultUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.util.T;
import com.android.tool.util.permissions.PermissionListener;
import com.android.tool.util.permissions.PermissionsUtil;
import com.android.tool.widget.ButtonView;
import com.android.tool.widget.CustomLoadMoreView;
import com.android.tool.widget.SuperRecyclerView;
import com.android.tool.widget.SuperSwipeRefreshLayout;
import com.android.tool.widget.UPMarqueeView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ethanhua.skeleton.SkeletonScreen;
import com.google.gson.Gson;

import com.makeramen.roundedimageview.RoundedImageView;

import com.zhy.autolayout.AutoRelativeLayout;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * class ：首页
 * author：York(wuchunyuan)
 * time  : 2018/3/6 10:05
 */
public class HomeFragment extends BaseFragments implements HomePageView, ButtonView.OnTabClickListener, MainActivity.OnActivityResult, HomePageAdapter224.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, BGABanner.Delegate<ImageView, String>, BGABanner.Adapter<ImageView, String> {
    @BindView(R.id.recyclerView)
    SuperRecyclerView mRecyclerView;
    @BindView(R.id.swipeRefreshLayout)
    SuperSwipeRefreshLayout mSwipeRefreshLayout;
    @BindView(R.id.txt_city)
    TextView txtCity;
    @BindView(R.id.iv_msg)
    ImageView ivMsg;
    @BindView(R.id.iv_msg_red)
    ImageView ivMsgRed;
    @BindView(R.id.iv_consulting)
    ButtonView ivConsulting;
    @BindView(R.id.title_bar_layout)
    LinearLayout title_bar_layout;
    @BindView(R.id.rootView)
    AutoRelativeLayout rootView;
    @BindView(R.id.statusBarHeight)
    View statusBarHeight;
    private UPMarqueeView mUpMarqueeView;
    private BGABanner mBanner;
    private TextView numIndicator;
    private ImageView ivAll;
    private HomePageAdapter224 mAdapter;
    private List<HomePageBean.RecommendBean> mList = new ArrayList<>();
    private List<HomePageBean.FunctionButtonBean> functionButtonBeanList = new ArrayList<>();
    private List<HomePageBean.MiddleBannerBean> middleBannerBeanList;
    private View viewHeader;
    private List<HomePageBean.TopBannerBean> adSlideBeanList;
    protected View viewNull;
    protected TextView txtNothingData;
    private HomePageCenterAdapter homePageCenterAdapter;
    private MainActivity fragmentActivity;
    private HomePageBean homePageBean;
    private ViewPager viewPager;
    private CenterViewPagerAdapter mCenterViewPagerAdapter;
    private HomePagePresenter mHomePagePresenter;

    @Override
    public void initParms(Bundle mBundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_home_page224;
    }

    @Override
    public void initView() {
        //设置状态栏高度
        setStatusBarHeight(statusBarHeight);
        mHomePagePresenter = new HomePageImpl(this);
        viewNull = LayoutInflater.from(mActivity).inflate(R.layout.layout_null, null);
        txtNothingData = viewNull.findViewById(R.id.txt_nothing_data);
        txtNothingData.setText(R.string.nothing_couser_vod_error);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));    /*热门推荐*/
        mAdapter = new HomePageAdapter224(mActivity, R.layout.home_page_list_item, mList);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        mRecyclerView.setAdapter(mAdapter);

        fragmentActivity = (MainActivity) getActivity();
        fragmentActivity.setOnActivityResult(this);
        setRecyclerViewHeader();   /*热门推荐头部*/


        if (StringUtil.isNotBlankAndEmpty(PUtil.getPreferences(PUtil.CITY_NAME, "")) && StringUtil.isNotBlankAndEmpty(PUtil.getPreferences(PUtil.CITY_ID, ""))) {
            txtCity.setText(PUtil.getPreferences(PUtil.CITY_NAME, ""));
        } else {
            PUtil.putPreferencesCity("全国", "1");
            txtCity.setText(PUtil.getPreferences(PUtil.CITY_NAME, ""));
        }
    }

    private void setRecyclerViewHeader() {
        //顶部、下部轮播
        viewHeader = LayoutInflater.from(mActivity).inflate(R.layout.home_page_header_layout224, null);
        mBanner = viewHeader.findViewById(R.id.banner);
        numIndicator = viewHeader.findViewById(R.id.numIndicator);

        mUpMarqueeView = viewHeader.findViewById(R.id.upMarqueeView);

        ivAll = viewHeader.findViewById(R.id.iv_all);/*更多推送*/
        ivAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (homePageBean != null)
                    WebURLUtil.openUrl(homePageBean.getNoticeListUrl(), mActivity);
            }
        });


        viewPager = viewHeader.findViewById(R.id.centerViewPager);
        mCenterViewPagerAdapter = new CenterViewPagerAdapter(mActivity);
        viewPager.setAdapter(mCenterViewPagerAdapter);
        viewPager.setPageMargin(15);


        RecyclerView recyclerViewF = viewHeader.findViewById(R.id.recyclerView_four);  /*高端课堂，报班咨询*/
        recyclerViewF.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        homePageCenterAdapter = new HomePageCenterAdapter(mActivity, R.layout.include_home_page_four_bt224, functionButtonBeanList);
        homePageCenterAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerViewF.setAdapter(homePageCenterAdapter);
        homePageCenterAdapter.setOnItemClickListener(new HomePageCenterAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(HomePageBean.FunctionButtonBean bean, int position) {
                if (bean != null)
                    WebURLUtil.openUrl(bean.getUrl(), mActivity);

            }
        });

        mAdapter.addHeaderView(viewHeader);
        showViewSkeletonScreen(R.layout.ss_home_page_header_layout224, viewHeader);
    }

    @Override
    public void initListener() {
        mAdapter.setOnItemClickListener(this);
        ivConsulting.setOnTabClickListener(this);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mBanner.setDelegate(this);
    }

    @Override
    public void doBusiness() {
        onRefresh();
    }


    @Override
    public void onRefresh() {
        mHomePagePresenter.getRequested(mActivity, PUtil.getPreferences(PUtil.CITY_ID, ""));
    }

    @Override
    public void doHomePageResponse(HomePageBean homePageBean) {
        this.homePageBean = homePageBean;
        mList.clear();
        mAdapter.addData(homePageBean.getRecommend());
        setView(homePageBean.getNotice());

        functionButtonBeanList.clear();
        PUtil.putPreferences(PUtil.IS_VIDEO, homePageBean.getIsShowVideo());
        //	是否显示我的中点评预约功能0否1是
        PUtil.putPreferences(PUtil.IS_HIDE_APPOINTMENT, homePageBean.getIsRevieworder());
        homePageCenterAdapter.addData(homePageBean.getFunctionButton());

        adSlideBeanList = homePageBean.getTopBanner();
        L.i(new Gson().toJson(adSlideBeanList));
        //设置 开始顶部轮播
        final List<String> bannerList = DataUtils.getAdSlideBeanList(adSlideBeanList);
        L.i(new Gson().toJson(bannerList));
        /**
         * 设置是否开启自动轮播，需要在 setData 方法之前调用，并且调了该方法后必须再调用一次 setData 方法
         * 例如根据图片当图片数量大于 1 时开启自动轮播，等于 1 时不开启自动轮播
         */
        numIndicator.setVisibility(View.VISIBLE);
        mBanner.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int position) {
                numIndicator.setText((position + 1) + "/" + bannerList.size());
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        mBanner.setAutoPlayAble(bannerList.size() > 1);
        mBanner.setAdapter(this);
        mBanner.setData(bannerList, bannerList);

        middleBannerBeanList = homePageBean.getMiddleBanner();
        setMiddleBannerList(middleBannerBeanList);

        try {
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onBannerItemClick(BGABanner banner, ImageView itemView, String model, int position) {
        if (adSlideBeanList != null && adSlideBeanList.size() > 0)
            WebURLUtil.openUrl(adSlideBeanList.get(position).getUrl(), mActivity);
    }

    @Override
    public void fillBannerItem(BGABanner banner, ImageView itemView, String model, int position) {
        GUtils.loadStringBanner(mActivity, model, itemView);
    }


    @Override
    public SkeletonScreen skeletonScreen() {
        return skeletonScreen;
    }

    @Override
    public void onHide() {
        if (mSwipeRefreshLayout != null)
            mSwipeRefreshLayout.setRefreshingPost(false);
    }


    /**
     * 列表点击事件
     *
     * @param bean
     * @param position
     */
    @Override
    public void onItemClick(HomePageBean.RecommendBean bean, int position) {
        if (bean != null) {
            WebURLUtil.openUrl(bean.getUrl(), mActivity);
        }
    }

    @OnClick({R.id.ll_selector_city, R.id.iv_seach, R.id.iv_msg, R.id.iv_flicking_a})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_selector_city:
                Bundle bundle = new Bundle();
                bundle.putString("city", txtCity.getText().toString());
                IntentUtils.startIntentForResult(mActivity, LocationCityListActivity.class, bundle, ResultUtil.HOME_PAGE_LOCATION);
                break;
            case R.id.iv_seach:
                IntentUtils.startIntent(mActivity, HomePageSeachActivity.class, new Bundle());
                break;
            case R.id.iv_msg:
                ivMsgRed.setVisibility(View.GONE);
//                IntentUtils.startIntent(mActivity, MessageActivity.class, new Bundle());
                break;
            case R.id.iv_flicking_a:
//                PermissionsUtil.requestPermission(mActivity, new PermissionListener() {
//                    @Override
//                    public void permissionGranted(@NonNull String[] permissions) {
//                        IntentUtils.startIntent(mActivity, ZXingBarActivity.class, new Bundle());
//                    }
//
//                    @Override
//                    public void permissionDenied(@NonNull String[] permissions) {
//                        T.customToastCenterShort(mActivity, "用户拒绝了相机权限");
//                    }
//                }, Manifest.permission.CAMERA);
                break;
        }
    }


    @Override
    public void onTabClick(int currentTab) {
        if (homePageBean == null)
            return;
        switch (currentTab) {
            case 1://拨打电话
                WebURLUtil.openUrl(homePageBean.getContact().getTel(), mActivity);
                break;
            case 2://qq聊天
                WebURLUtil.openUrl(homePageBean.getContact().getQq(), mActivity);
                break;
            default:
                break;
        }
    }

    @Override
    public void onActivityResultHomePage(int requestCode, int resultCode, Intent data) {
        if (resultCode == ResultUtil.HOME_PAGE_TYPE) {
            if (requestCode == ResultUtil.HOME_PAGE_TYPE) {
                doBusiness();
            }
        } else if (resultCode == ResultUtil.HOME_PAGE_LOCATION) {
            if (requestCode == ResultUtil.HOME_PAGE_LOCATION) {
                String cityName = data.getStringExtra(KeyUtil.CITY_NAME);
                String cityCode = data.getStringExtra(KeyUtil.CITY_CODE);
                PUtil.putPreferencesCity(cityName, cityCode);
                txtCity.setText(cityName);
                doBusiness();
            }
        }
    }

    /**
     * 设置左右漏 展示Viewpager
     *
     * @param middleBannerBeanList
     */
    private void setMiddleBannerList(final List<HomePageBean.MiddleBannerBean> middleBannerBeanList) {
        List<RoundedImageView> views = new ArrayList<>();
        for (int i = 0; i < middleBannerBeanList.size(); i++) {
            RoundedImageView mRoundedImageView = new RoundedImageView(mActivity);
            final int finalI = i;
            mRoundedImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (middleBannerBeanList != null && middleBannerBeanList.size() > 0)
                        WebURLUtil.openUrl(middleBannerBeanList.get(finalI).getUrl(), mActivity);
                }
            });
            mRoundedImageView.setCornerRadius(10);
            mRoundedImageView.setScaleType(ImageView.ScaleType.FIT_XY);
            GUtils.loadIVStringGuangGaoHome(mActivity, middleBannerBeanList.get(i).getImgUrl(), mRoundedImageView);
            views.add(mRoundedImageView);
        }
        mCenterViewPagerAdapter.setMiddleBannerList(views);
    }


    /**
     * 初始化需要循环的View
     * 为了灵活的使用滚动的View，所以把滚动的内容让用户自定义
     * 假如滚动的是三条或者一条，或者是其他，只需要把对应的布局，和这个方法稍微改改就可以了，
     *
     * @param upList
     */
    private void setView(final List<HomePageBean.NoticeBean> upList) {
        List<View> views = new ArrayList<>();
        for (int i = 0; i < upList.size(); i = i + 2) {
            final int position = i;
            LinearLayout moreView = (LinearLayout) LayoutInflater.from(mActivity).inflate(R.layout.home_page_up_item_view224, null);
            TextView tv1 = moreView.findViewById(R.id.tv1);
            moreView.findViewById(R.id.rl).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (upList != null && upList.size() > 0)
                        WebURLUtil.openUrl(upList.get(position).getUrl(), mActivity);
                }
            });
            //进行对控件赋值
            tv1.setText(upList.get(i).getName());
            //添加到循环滚动数组里面去
            views.add(moreView);
        }
        mUpMarqueeView.setViews(views);
    }

    /**
     * 处理推送通知
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(String event) {
        switch (event) {
            case KeyUtil.JPUSH_RECEIVER:
                ivMsgRed.setVisibility(View.VISIBLE);
                break;
        }
    }

    public void setCity(String city) {
        if (txtCity != null)
            txtCity.setText(city);
    }

    @Override
    public void onStop() {
        super.onStop();
        //结束轮播
    }


}
