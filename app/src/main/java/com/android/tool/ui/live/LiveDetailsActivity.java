package com.android.tool.ui.live;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.LiveDetailsBean;
import com.android.tool.util.share.bean.ShareBean;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.live.fragment.IntroduceLiveFragment;
import com.android.tool.ui.web.WebURLUtil;
import com.android.tool.util.GUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.ResultUtil;
import com.android.tool.util.share.ShareDialogFragment;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.flyco.tablayout.SlidingTabLayout;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.umeng.socialize.UMShareAPI;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：直播详情
 * author：York(wuchunyuan)
 * time  : 2018/6/6 11:08
 */
public class LiveDetailsActivity extends BaseActivitys /*implements VodSite.OnVodListener*/ {

    private final static String TAG = "LiveDetailsActivity";

    @BindView(R.id.slidingTabLayout)
    SlidingTabLayout mSlidingTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;
    @BindView(R.id.txt_price)
    TextView txtPrice;
    @BindView(R.id.txt_listen_now)
    TextView txtListenNow;
    @BindView(R.id.txt_buy_now)
    TextView txtBuyNow;
    @BindView(R.id.iv_collection)
    ImageView ivCollection;
    @BindView(R.id.txt_collection)
    TextView txtCollection;
    @BindView(R.id.iv_cover)
    ImageView ivCover;
    private ArrayList<Fragment> mFragments = new ArrayList<>();
    private String[] mTitle = {"介绍", "课程表", "回放"};
    private MyPagerAdapter mAdapter;
    public static LiveDetailsBean liveDetailsBean;
    public static String liveId;
//    private GSFastConfig mGSFastConfig = new GSFastConfig();
//    private PlayBackLiveFragment mPlayBackLiveFragment;
//    private CourseTimeTableFragment mCourseTimeTableFragment;
    private IntroduceLiveFragment mIntroduceLiveFragment;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, false);
        liveId = mBundle.getString(KeyUtil.LIVE_ID, "");
        PUtil.putPreferences(KeyUtil.UNIONKEY, mBundle.getString(KeyUtil.UNIONKEY, ""));
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_live_details;
    }

    @Override
    public void initView() {
        EventBus.getDefault().register(this);//订阅
//        mPlayBackLiveFragment = new PlayBackLiveFragment();
//        mCourseTimeTableFragment = new CourseTimeTableFragment();
        mIntroduceLiveFragment = new IntroduceLiveFragment();
        mFragments.add(mIntroduceLiveFragment);
//        mFragments.add(mCourseTimeTableFragment);
//        mFragments.add(mPlayBackLiveFragment);

    }

    @Override
    public void initListener() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<>();
            if (checkSelfPermission(Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.RECORD_AUDIO);
            }
            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 127);
            }
        }
    }

    /**
     * buttonStatus 0 立即购买 解释：未购买情况下和没有回放情况下
     * buttonStatus 1 立即试听 立即购买 未购买情况下和有回放情况下
     * buttonStatus 2 看直播 已经购买没有回放情况下
     * buttonStatus 3 有效期剩余多长时间 已经购买和有回放情况下
     */
    @Override
    public void doBusiness() {
        OkGo.<ObjectResponse<LiveDetailsBean>>get(PathUtil.getNewLiveDetails())
                .params(AppConfig.LiveList.ID, liveId)
                .execute(new ObjectCallback<ObjectResponse<LiveDetailsBean>>(mActivity, "") {
                    @Override
                    public void onSuccess(Response<ObjectResponse<LiveDetailsBean>> response) {
                        liveDetailsBean = response.body().data;
                        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
                        mViewPager.setAdapter(mAdapter);
                        mSlidingTabLayout.setViewPager(mViewPager);
                        mViewPager.setOffscreenPageLimit(mFragments.size());
                        //        mSlidingTabLayout.setCurrentTab(1);
                        GUtils.loadActivityBanner(mActivity, liveDetailsBean.getCoverImgUrl(), ivCover);
                        txtPrice.setText(liveDetailsBean.getPriceStr());
                        if (liveDetailsBean.getIsCollection().equals("1")) {//1 已收藏
                            ivCollection.setImageResource(R.mipmap.collection_icon_true);
                            txtCollection.setText(R.string.have_to_collection);
                        } else {//2 未收藏
                            ivCollection.setImageResource(R.mipmap.collection_icon3x);
                            txtCollection.setText(R.string.collection);
                        }
                        if (liveDetailsBean.getButtonStatus().equals("0")) {
                            txtBuyNow.setText(liveDetailsBean.getButtonStr());
                            txtListenNow.setVisibility(View.GONE);
                        } else if (liveDetailsBean.getButtonStatus().equals("1")) {
                            txtBuyNow.setText(liveDetailsBean.getButtonStr());
                            if (liveDetailsBean.getVideos() != null && liveDetailsBean.getVideos().size() > 0) {
                                txtListenNow.setVisibility(View.VISIBLE);
                            } else {
                                txtListenNow.setVisibility(View.GONE);
                            }
                        } else if (liveDetailsBean.getButtonStatus().equals("2")) {
                            txtBuyNow.setText(liveDetailsBean.getButtonStr());
                            txtListenNow.setVisibility(View.GONE);
                        } else if (liveDetailsBean.getButtonStatus().equals("3")) {
                            txtBuyNow.setText(liveDetailsBean.getButtonStr());
                            txtListenNow.setVisibility(View.GONE);
                        } else if (liveDetailsBean.getButtonStatus().equals("4")) {
                            txtBuyNow.setText(liveDetailsBean.getButtonStr());
                            txtListenNow.setVisibility(View.GONE);
                        }
                    }
                });
    }

    @OnClick({R.id.img_back, R.id.iv_cache, R.id.iv_share, R.id.ll_consulting, R.id.ll_collection, R.id.txt_listen_now, R.id.txt_buy_now})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.iv_cache:
//                String liveType0 = PlayerIntentUtils.LivePlayerType(mActivity, liveDetailsBean, liveDetailsBean.getIsBuy());
//                if (!StringUtil.isNotBlankAndEmpty(liveType0))
//                    return;
//                if (liveDetailsBean.getVideos() != null && liveDetailsBean.getVideos().size() > 0) {
//                    Bundle bundle = new Bundle();
//                    switch (liveType0) {
//                        case PlayerIntentUtils.LIVE_GENSEE://1 展视互动
//                            bundle.putString(KeyUtil.COUSER_ID, liveDetailsBean.getId());
//                            bundle.putString(KeyUtil.COUSER_NAME, liveDetailsBean.getName());
//                            IntentUtils.startIntent(mActivity, SelectLiveCacheActivity.class, bundle);
//                            break;
//                        case PlayerIntentUtils.LIVE_CC_PLAYBACK://5 cc视频回放
//                            bundle.putString(KeyUtil.COUSER_ID, liveDetailsBean.getId());
//                            bundle.putString(KeyUtil.COUSER_NAME, liveDetailsBean.getName());
//                            IntentUtils.startIntent(mActivity, SelectCCDBPlayBackCacheActivity.class, bundle);
//                            break;
//                    }
//                } else {
//                    showToast("暂无回放视频");
//                }
                break;
            case R.id.iv_share:
                if (liveDetailsBean != null) {
                    ShareDialogFragment shareDialog = ShareDialogFragment.newInstance(mActivity,
                            ShareBean.getLiveDetailsBeanInstance(mActivity, liveDetailsBean.getShareInfo()));
                    shareDialog.show(getSupportFragmentManager(), TAG);
                }
                break;
            case R.id.ll_consulting:
                if (liveDetailsBean != null)
                    WebURLUtil.openUrl(liveDetailsBean.getContactUrl(), mActivity);
                break;
            case R.id.ll_collection:
//                if (liveDetailsBean != null)
//                    if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                        OkGo.<ObjectResponse<CollectionBean>>get(PathUtil.getIsCollection()).tag(this)
//                                .params(AppConfig.IsCollection.OBJ_TYPE, AppConfig.IsCollection.LIVE)//1 点播 2 直播
//                                .params(AppConfig.IsCollection.BOJ_ID, liveDetailsBean.getId())
//                                .params(AppConfig.IsCollection.IS_COLLECTION, liveDetailsBean.getIsCollection())
//                                .execute(new ObjectCallback<ObjectResponse<CollectionBean>>(mActivity, "正在加载...") {
//                                    @Override
//                                    public void onSuccess(Response<ObjectResponse<CollectionBean>> response) {
//                                        CollectionBean bean = response.body().data;
//                                        liveDetailsBean.setIsCollection(bean.getIsCollection());
//                                        if (liveDetailsBean.getIsCollection().equals("1")) {//1 已收藏
//                                            ivCollection.setImageResource(R.mipmap.collection_icon_true);
//                                            txtCollection.setText(R.string.have_to_collection);
//                                        } else {//2 未收藏
//                                            ivCollection.setImageResource(R.mipmap.collection_icon3x);
//                                            txtCollection.setText(R.string.collection);
//                                        }
//                                    }
//                                });
//                    }
                break;
            case R.id.txt_listen_now://立即试听
//                String liveType = PlayerIntentUtils.LivePlayerType(mActivity, liveDetailsBean, liveDetailsBean.getFreeVideo().getIsshow());
//                if (!StringUtil.isNotBlankAndEmpty(liveType))
//                    return;
//                switch (liveType) {
//                    case PlayerIntentUtils.LIVE_GENSEE://1 展视互动
//                        startPlayer(liveDetailsBean.getFreeVideo());
//                        break;
//                    case PlayerIntentUtils.LIVE_CC_PLAYBACK://5 cc视频回放
//                        startCCPlayer(liveDetailsBean.getFreeVideo());
//                        break;
//                }
                break;
            case R.id.txt_buy_now:
          /*      if (liveDetailsBean != null)
                    if (IntentUtils.isLogin(mActivity, new Bundle())) {
                        if (liveDetailsBean.getButtonStatus().equals("0")) {//立即购买 解释：未购买情况下和没有回放情况下
                            IntentUtils.startPayPageActivity(mActivity, liveDetailsBean.getId(),
                                    "", AppConfig.getAddCar.PRODUCT, false);
                        } else if (liveDetailsBean.getButtonStatus().equals("1")) {//立即购买 未购买情况下和有回放情况下
                            IntentUtils.startPayPageActivity(mActivity, liveDetailsBean.getId(),
                                    "", AppConfig.getAddCar.PRODUCT, false);
                        } else if (liveDetailsBean.getButtonStatus().equals("2")) {//看直播 已经购买没有回放情况下
                            PermissionsUtil.requestPermission(mActivity, new PermissionListener() {
                                @Override
                                public void permissionGranted(@NonNull String[] permissions) {
                                    if (liveDetailsBean.getLiveType().equals("1")) {
                                        joinLive(liveDetailsBean.getLiveInfo());
                                    } else if (liveDetailsBean.getLiveType().equals("5")) {
                                        joinCCLive(liveDetailsBean.getLiveInfo());
                                    }
                                }

                                @Override
                                public void permissionDenied(@NonNull String[] permissions) {
                                    T.customToastCenterShort(mActivity, "用户拒绝了摄像头或麦克风权限");
                                }
                            }, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO);
                        }
                    }*/
//                    joinCCLive();
                break;
        }
    }

    /**
     * cc直播
     */
//    private void joinCCLive(LiveDetailsBean.LiveInfoBean bean) {
//        getDialogLoading();
//        // 创建登录信息
//        LoginInfo loginInfo = new LoginInfo();
//        loginInfo.setUserId(bean.getUserid());
//        loginInfo.setRoomId(bean.getRoomid());
//        loginInfo.setViewerName(bean.getViewername());
//        loginInfo.setViewerToken(bean.getViewertoken());
//        // 设置登录参数
//        DWLive.getInstance().setDWLiveLoginParams(new DWLiveLoginListener() {
//            @Override
//            public void onLogin(TemplateInfo templateInfo, Viewer viewer, final RoomInfo roomInfo, PublishInfo publishInfo) {
//                Bundle mBundle = new Bundle();
//                mBundle.putString("liveTitle", liveDetailsBean.getName());
//                IntentUtils.startIntent(mActivity, CCLivePlayActivity.class, mBundle);// 直播默认Demo页
//                dismissDialogLoading();
//            }
//
//            @Override
//            public void onException(final DWLiveException e) {
//                toastOnUiThread("登录失败");
//                dismissDialogLoading();
//            }
//        }, loginInfo);
//        // 执行登录操作
//        DWLive.getInstance().startLogin();
//    }

    /**
     * 播放cc回访视频
     *
     * @param bean
     */
//    private void startCCPlayer(final LiveDetailsBean.FreeVideoBean bean) {
//        getDialogLoading();
//        // 创建登录信息
//        ReplayLoginInfo replayLoginInfo = new ReplayLoginInfo();
//        replayLoginInfo.setUserId(bean.getUserid());
//        replayLoginInfo.setRoomId(bean.getRoomid());
//        replayLoginInfo.setLiveId(bean.getLiveid());
//        replayLoginInfo.setRecordId(bean.getRecordid());
//        replayLoginInfo.setViewerName(bean.getViewername());
//        replayLoginInfo.setViewerToken(bean.getViewertoken());
//        // 设置登录参数
//        DWLiveReplay.getInstance().setLoginParams(new DWLiveReplayLoginListener() {
//
//            @Override
//            public void onException(final DWLiveException exception) {
//                toastOnUiThread("播放失败");
//                dismissDialogLoading();
//            }
//
//            @Override
//            public void onLogin(TemplateInfo templateInfo) {
//                CCReplayPlayBean mCCReplayPlayBean = new CCReplayPlayBean();
//                mCCReplayPlayBean.setCourseId(liveDetailsBean.getId());
//                mCCReplayPlayBean.setChapterId(bean.getId());
//                mCCReplayPlayBean.setChapterName(bean.getName());
//                mCCReplayPlayBean.setDuration(Integer.parseInt(bean.getDuration()));
//                mCCReplayPlayBean.setProgress(Integer.parseInt(bean.getProgress()));
//                mCCReplayPlayBean.setPosition(0);
//                CCReplayPlayActivity.startIntent(mActivity, mCCReplayPlayBean);
//                dismissDialogLoading();
//            }
//        }, replayLoginInfo);
//        // 执行登录操作
//        DWLiveReplay.getInstance().startLogin();
//    }

    /**
     * 播放回访视频
     *
     * @param bean
     */
//    private void startPlayer(LiveDetailsBean.FreeVideoBean bean) {
//        getDialogLoading();
//        InitParam initParam = new InitParam();
//        // 设置域名
//        initParam.setDomain(bean.getDomain());
//        // 设置编号,8位数字字符串，
//        initParam.setNumber(bean.getRoomid());
//        // 设置站点登录帐号（根据配置可选）
//        initParam.setLoginAccount("");
//        // 设置站点登录密码（根据配置可选）
//        initParam.setLoginPwd("");
//        // 设置显示昵称 不能为空,请传入正确的昵称，有显示和统计的作用
//        // 设置显示昵称，如果设置为空，请确保
//        initParam.setNickName(bean.getNickname());
//        // 设置加入口令（根据配置可选）
//        initParam.setJoinPwd(bean.getPwd());
//        // 设置服务类型，如果站点是webcast类型则设置为ServiceType.ST_CASTLINE，
//        // training类型则设置为ServiceType.ST_TRAINING
//        initParam.setServiceType(ServiceType.TRAINING);
//        //如果启用第三方认证，必填项，且要正确有效
//        //initParam.setUserId(1000000001);//大于1000000000有效
//        //站点 系统设置 的 第三方集成 中直播模块 “认证“  启用时请确保”第三方K值“（你们的k值）的正确性 ；如果没有启用则忽略这个参数
//        initParam.setK("");
////        InitParam initParam = ConfigApp.getIns().getInitParam();
//        VodSite vodSite = new VodSite(this);
//        vodSite.setVodListener(this);
//        vodSite.getVodObject(initParam);
//        //initParam.setUserData("vip=1&city=上海");
//    }

    /**
     * 加入直播
     *
     * @param bean
     */
//    private void joinLive(LiveDetailsBean.LiveInfoBean bean) {
//        InitParam initParam = new InitParam();
//        //若一个url为http://test.gensee.com/site/webcast   域名是“test.gensee.com”
//        initParam.setDomain(bean.getDomain().trim());
//        //设置对应编号，如果是点播则是点播编号，是直播便是直播编号。
//        //请注意不要将id理解为编号。
//        //作用等价于id，但不是id。有id可以不用编号，有编号可以不用id
//        initParam.setNumber(bean.getRoomid().trim());
//        //设置站点认证账号 即登录站点的账号
//        initParam.setLoginAccount("");
//        //设置站点认证密码 即登录站点的密码,如果后台设置直播需要登录或点播需要登录，那么登录密码要正确  且帐号同时也必须填写正确
//        initParam.setLoginPwd("");
//        //设置昵称  用于直播间显示或统计   一定要填写
//        initParam.setNickName(bean.getNickname());
//        //可选 如果后台设置了保护密码 请填写对应的口令
//        initParam.setJoinPwd(bean.getPwd().trim());
//        //第三方认证K值，如果启用第三方集成的时候必须传入有效的K值
//        initParam.setK("");
//        //若一个url为http://test.gensee.com/site/webcast ,serviceType是 ServiceType.WEBCAST,
//        //url为http://test.gensee.com/site/training,serviceTypeserviceType是 ServiceType.TRAINING
//        initParam.setServiceType(ServiceType.TRAINING);
//        //是否是主播端，false和默认观看端
//        mGSFastConfig.setPublish(false);
//        mGSFastConfig.setPublishScreenMode(GSFastConfig.PUB_SCREEN_MODE_PORTRAIT);
//        mGSFastConfig.setWatchScreenMode(GSFastConfig.WATCH_SCREEN_MODE_VIDEO_DOC);
//        mGSFastConfig.setHardEncode(false);
//        mGSFastConfig.setPubQuality(GSFastConfig.PUB_QUALITY_HD);
//        //配置观看端打赏固定金额面板,最多支持6个固定金额,固定金额最高不能超过200000(即2000.00元)
//        mGSFastConfig.setFixedMoneyArray(null);
//        //分屏观看端,界面配置
//        mGSFastConfig.setShowDoc(true);//是否显示《文档》
//        mGSFastConfig.setShowChat(true);//是否显示《聊天》
//        mGSFastConfig.setShowQa(true);//是否显示《问答》
//        mGSFastConfig.setShowIntro(true);
//        mGSFastConfig.setShowPIP(true);//是否显示《全屏模式画中画》
//        mGSFastConfig.setShowHand(true);//是否显示《举手》
//        mGSFastConfig.setShowRateSwitch(false);//是否显示《码流切换》
//        mGSFastConfig.setShowDanmuBtn(true);
//        mGSFastConfig.setShownetSwitch(true);//是否显示《优先网络》
//        mGSFastConfig.setShowDanmuBtn(true);//是否显示  《弹幕开关》
//        mGSFastConfig.setShowCloseVideo(true);
//        mGSFastConfig.setSkinType(0);//换肤 0黑夜模式  1白天模式
//        GenseeLive.startLive(mActivity, mGSFastConfig, initParam);
//    }

  /*  @Override
    public void onChatHistory(String s, List<ChatMsg> list, int i, boolean b) {
        dismissDialogLoading();
    }

    @Override
    public void onQaHistory(String s, List<QAMsg> list, int i, boolean b) {
        dismissDialogLoading();
    }

    @Override
    public void onVodErr(final int errCode) {
        dismissDialogLoading();
        if (mActivity == null) {
            return;
        }
        try {
            mActivity.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String msg = VodCodeUtil.getErrMsg(errCode);
                    if (StringUtil.isNotBlankAndEmpty(msg)) {
                        showToast(msg);
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onVodDetail(VodObject vodObject) {
        dismissDialogLoading();
    }

    *//**
     * getVodObject的响应，vodId 接下来可以下载后播放
     *//*
    @Override
    public void onVodObject(String vodId) {
        dismissDialogLoading();
        L.i("onVodObject vodId = " + vodId);
        mHandler.sendMessage(mHandler.obtainMessage(ResultUtil.VOD_RESULT.ON_GET_VODOBJ, vodId));
    }
*/
  /*  @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case ResultUtil.VOD_RESULT.ON_GET_VODOBJ:
                    Intent i = new Intent(mActivity, PlayActivity.class);
                    i.putExtra(ResultUtil.VOD_RESULT.VOD_ID, (String) msg.obj);
                    startActivity(i);
                    break;
                default:
                    break;
            }
        }
    };*/

    /**
     * 支付完成返回刷新
     *
     * @param event
     */
    @Subscribe(threadMode = ThreadMode.MAIN) //在ui线程执行
    public void onDataSynEvent(String event) {
        if (event == KeyUtil.UPDATE_PAY) {
            doBusiness();
//            mPlayBackLiveFragment.doBusiness();
//            mIntroduceLiveFragment.doBusiness();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
        if (requestCode == ResultUtil.LOGING_RESULT) {
            if (resultCode == ResultUtil.LOGING_RESULT) {
                doBusiness();
//                mPlayBackLiveFragment.doBusiness();
//                mIntroduceLiveFragment.doBusiness();
            }
        } else if (resultCode == ResultUtil.CC_POSITION_CODE) {
            if (requestCode == ResultUtil.CC_POSITION_CODE) {
                if (onActivityResult != null) {
                    onActivityResult.onActivityResultPlayLiveBack(requestCode, resultCode, data);
                }
            }
        }
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
            return mTitle[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//解除订阅
    }


    public interface OnActivityResult {
        void onActivityResultPlayLiveBack(int requestCode, int resultCode, Intent data);
    }

    private OnActivityResult onActivityResult;

    public void setOnActivityResult(OnActivityResult onActivityResult) {
        this.onActivityResult = onActivityResult;
    }

}
