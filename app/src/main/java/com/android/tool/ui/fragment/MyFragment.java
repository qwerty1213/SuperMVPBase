package com.android.tool.ui.fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.tool.R;
import com.android.tool.model.CurrencyBalanceBean;
import com.android.tool.ui.base.BaseFragments;
import com.android.tool.ui.main.MyOrderActivity;
import com.android.tool.ui.web.WebURLUtil;
import com.android.tool.util.GUtils;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.utility.ObjectNoDialogCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.widget.ButtonView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author York(wuchunyuan)
 * @Created 2019/4/16.
 */
public class MyFragment extends BaseFragments  implements ButtonView.OnTabClickListener {
    private final static String TAG = "MyFragment";
    @BindView(R.id.iv_rounded)
    RoundedImageView ivRounded;
    @BindView(R.id.txt_nick_name)
    TextView txtNickName;
    @BindView(R.id.txt_signature)
    TextView txtSignature;
    @BindView(R.id.txt_teach_currency_num)
    TextView txtTeachCurrency;
    @BindView(R.id.txt_coupons_num)
    TextView txtCouponsNum;
    @BindView(R.id.iv_consulting)
    ButtonView ivConsulting;
    @BindView(R.id.ll_appointment)
    LinearLayout llAppointment;
    @BindView(R.id.ll_appointment_bottom_line)
    View llAppointmentBottomLine;
    private CurrencyBalanceBean balanceBean;
    @Override
    public void initParms(Bundle mBundle) {
//        setStatusBarTransparent(true);
//        XStatusBar.newColorBuilder()
//                .statusBarTextColor(true)
//                .statusColor(color(R.color.c_FC5D))  // 状态栏颜色
//                .statusDepth(0)                          // 状态栏颜色深度
//                .applyNav(false)                           // 是否应用到导航栏
//                .build(getActivity())
//                .apply();
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_my224;
    }

    @Override
    public void onResume() {
        super.onResume();
        //获取学习金余额
        OkGo.<ObjectResponse<CurrencyBalanceBean>>get(PathUtil.getMyInfo())
                .execute(new ObjectNoDialogCallback<ObjectResponse<CurrencyBalanceBean>>(mActivity, "") {
                    @Override
                    public void onSuccess(Response<ObjectResponse<CurrencyBalanceBean>> response) {
                        balanceBean = response.body().data;
                        if (balanceBean != null) {
                            txtTeachCurrency.setText(balanceBean.getAmount());
                            txtSignature.setText(balanceBean.getSignature());
                            txtCouponsNum.setText(balanceBean.getCouponCountStr());
                        }
                    }
                });
        if (PUtil.isTokenNull()) {
            GUtils.loadIVStringHeadYuan(mActivity, PUtil.getPreferences(PUtil.AVATAR, ""), ivRounded);
            String nickName = PUtil.getPreferences(PUtil.NICK_NAME, "").trim();
            if (StringUtil.isNotBlankAndEmpty(nickName)) {
                txtNickName.setText(nickName);
            } else {
                txtNickName.setText(R.string.text_nick_name);
            }
        } else {
            GUtils.loadIVStringHeadYuan(mActivity, R.mipmap.default_head_icon, ivRounded);
            txtNickName.setText("登录/注册");
        }

    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {
        ivConsulting.setOnTabClickListener(this);
    }

    @Override
    public void doBusiness() {

    }

    @OnClick({R.id.ll_login, R.id.ll_my_order, R.id.ll_commodity_exchange, R.id.ll_my_course, R.id.ll_course_history,
            R.id.ll_my_collection, R.id.ll_cache_record, R.id.ll_use_helper,
            R.id.iv_setting, R.id.ll_address_management, R.id.ll_feekback,
            R.id.ll_recommend_our, R.id.ll_teach_currency, R.id.ll_teach_currency_top, R.id.ll_appointment, R.id.ll_coupons_top})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_login://选择头像
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, UserSettingActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_commodity_exchange:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, ExchangeProductActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_my_course:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, CouserActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_course_history:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, CourseHistoryActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_my_order:
                if (IntentUtils.isLogin(mActivity, new Bundle())) {
                    IntentUtils.startIntent(mActivity, MyOrderActivity.class, new Bundle());
                }
                break;
            case R.id.ll_my_collection:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, CollectionActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_cache_record:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, CacheListActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_use_helper:
//                if (balanceBean != null) {
//                    if (StringUtil.isNotBlankAndEmpty(balanceBean.getHelpUrl())) {
//                        WebURLUtil.openUrl(balanceBean.getHelpUrl(), mActivity);
//                    }
//                }
                break;
            case R.id.ll_address_management:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, AddressManagementActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_feekback:
//                IntentUtils.startIntent(mActivity, FeedBackActivity.class, new Bundle());
                break;
            case R.id.ll_recommend_our:
//                if (balanceBean != null) {
//                    ShareDialogFragment shareDialog = ShareDialogFragment.newInstance(mActivity,
//                            ShareBean.getRecommendOurBeanInstance(mActivity,
//                                    balanceBean.getRecommendShareInfo()));
//                    shareDialog.show(getActivity().getSupportFragmentManager(), TAG);
//                }
                break;
            case R.id.iv_setting:
//                IntentUtils.startIntent(mActivity, SystemSettingActivity.class, new Bundle());
                break;
            case R.id.ll_teach_currency:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, ExchangeRecordActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_teach_currency_top:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, ExchangeRecordActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_appointment:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, AppintmentActivity.class, new Bundle());
//                }
                break;
            case R.id.ll_coupons_top:
//                if (IntentUtils.isLogin(mActivity, new Bundle())) {
//                    IntentUtils.startIntent(mActivity, CouponsListActivity.class, new Bundle());
//                }
                break;
        }
    }

    @Override
    public void onTabClick(int currentTab) {
        if (balanceBean == null) {
            return;
        }
        switch (currentTab) {
            case 1://拨打电话
                WebURLUtil.openUrl(balanceBean.getTel(), mActivity);
                break;
            case 2://qq聊天
                WebURLUtil.openUrl(balanceBean.getQq(), mActivity);
                break;
            default:
                break;
        }
    }
}
