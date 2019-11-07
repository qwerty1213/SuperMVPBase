package com.android.tool.ui.pay;


import android.annotation.SuppressLint;
import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.android.tool.R;
import com.android.tool.model.AddressManagementModel;
import com.android.tool.model.UseCouponsModel;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.pay.adapter.PayCouserInfomatiomAdapter;
import com.android.tool.ui.pay.adapter.PayWayAdapter;
import com.android.tool.ui.pay.bean.PayBean;
import com.android.tool.ui.pay.bean.SubmitAlipayOrdersBean;
import com.android.tool.ui.pay.bean.SubmitWeiChatOrdersBean;
import com.android.tool.ui.pay.util.PayResult;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.L;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.ResultUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.util.SystemUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.widget.CustomLoadMoreView;
import com.android.tool.widget.TextViewDel;
import com.android.tool.wxapi.WXPayEntryActivity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.tencent.mm.opensdk.constants.Build;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：支付页面
 * author：York(wuchunyuan)
 * time  : 2018/3/21 17:09
 */
public class PayActivity extends BaseActivitys implements PayCouserInfomatiomAdapter.OnItemPayCouserClickListener, PayWayAdapter.OnItemClickListener {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.rv_pay_inform)
    RecyclerView rvPayInform;
    @BindView(R.id.txt_recruit_teach_currency_num)
    TextView txtRecruitTeachCurrencyNum;
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @BindView(R.id.txt_original_price)
    TextViewDel txtOriginalPrice;
    @BindView(R.id.txt_total_price)
    TextView txtTotalPrice;
    @BindView(R.id.rl_recruit_teach_currency)
    RelativeLayout rlRecruitTeachCurrency;
    @BindView(R.id.ll_address_management)
    LinearLayout llAddressManagement;
    @BindView(R.id.ll_setting_address)
    LinearLayout llSettingAddress;
    @BindView(R.id.txt_name)
    TextView txtName;
    @BindView(R.id.txt_mobile)
    TextView txtMobile;
    @BindView(R.id.txt_address)
    TextView txtAddress;
    @BindView(R.id.txt_coupons_num)
    TextView txtCouponsNum;
    private PayWayAdapter mAdapter;
    private PayCouserInfomatiomAdapter mPayCouserInfomatiomAdapter;
    private String orderId, currency;
    private boolean isOrder = false;
    private List<PayBean.PayWayListBean> mList = new ArrayList<>();
    private List<PayBean.ProductsBean> mProductsBeanList = new ArrayList<>();
    private static final int SDK_PAY_FLAG = 1;//支付宝
    private IWXAPI wxApi;
    private static final String ALIPAY = "alipay";
    private static final String WECHAT = "wechat";
    public static final String ALIPAY_RESULT_STATUS = "alipayResultStatus";
    public static final String ALIPAY_SUCCESS_STATUS = "9000";
    private PayBean mPayBean;
    private String addressId, couponsId, couponsPrice;

    @Override
    public void initParms(Bundle mBundle) {
        isAddActivity = false;
        steepSetStatusBarTranslucent(true, true);
        orderId = mBundle.getString(KeyUtil.ORDER_ID);
        isOrder = mBundle.getBoolean(KeyUtil.IS_ORDER);
        ActivityManagementUtil.getInstance().addPayActivity(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_pay;
    }

    @Override
    public void initView() {
        wxApi = WXAPIFactory.createWXAPI(this, AppConfig.Pay.APP_ID, false);
        wxApi.registerApp(AppConfig.Pay.APP_ID);
        txtTitle.setText(R.string.title_order_pay);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new PayWayAdapter(mActivity, R.layout.pay_way_item, mList);
        mAdapter.setLoadMoreView(new CustomLoadMoreView());
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setAdapter(mAdapter);

        rvPayInform.setLayoutManager(new LinearLayoutManager(this));
        mPayCouserInfomatiomAdapter = new PayCouserInfomatiomAdapter(R.layout.pay_data_item, mProductsBeanList);
        mPayCouserInfomatiomAdapter.setLoadMoreView(new CustomLoadMoreView());
        mPayCouserInfomatiomAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        mPayCouserInfomatiomAdapter.setOnItemPayCouserClickListener(this);
        rvPayInform.setNestedScrollingEnabled(false);
        rvPayInform.setAdapter(mPayCouserInfomatiomAdapter);
        if (isOrder) {
            rlRecruitTeachCurrency.setEnabled(false);
            llSettingAddress.setEnabled(false);
            llAddressManagement.setEnabled(false);
        } else {
            rlRecruitTeachCurrency.setEnabled(true);
            llSettingAddress.setEnabled(true);
            llAddressManagement.setEnabled(true);
        }
    }

    @Override
    public void initListener() {
    }

    @Override
    public void doBusiness() {
        getDataBalance("0");
    }

    /**
     * 获取数据
     *
     * @param currency
     */
    public void getDataBalance(String currency) {
        OkGo.<ObjectResponse<PayBean>>get(PathUtil.getPaymentInfo()).tag(this)
                .params(AppConfig.Pay.ORDER_ID, orderId)
                .params(AppConfig.Pay.ADDRESS_ID, addressId)
                .params(AppConfig.Pay.CURRENCY, currency)
                .params(AppConfig.Pay.COUPONID, couponsId)
                .execute(new ObjectCallback<ObjectResponse<PayBean>>(mActivity, "正在加载...") {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(Response<ObjectResponse<PayBean>> response) {
                        mPayBean = response.body().data;
                        if (mPayBean.getIsShowAddress().equals("1")) {
                            if (mPayBean.getDefaultAddress() != null) {
                                addressId = mPayBean.getDefaultAddress().getId();
                                llSettingAddress.setVisibility(View.GONE);
                                llAddressManagement.setVisibility(View.VISIBLE);
                                try {
                                    txtName.setText("收货人：" + mPayBean.getDefaultAddress().getRealName());
                                    txtMobile.setText(mPayBean.getDefaultAddress().getMobile());
                                    txtAddress.setText("收货地址：" + mPayBean.getDefaultAddress().getAddress());
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            } else {
                                addressId = "0";
                                llSettingAddress.setVisibility(View.VISIBLE);
                                llAddressManagement.setVisibility(View.GONE);
                            }
                        } else {
                            addressId = "0";
                            llSettingAddress.setVisibility(View.GONE);
                            llAddressManagement.setVisibility(View.GONE);
                        }
                        if (isOrder) {//未支付订单 显示学习金金額
                            txtRecruitTeachCurrencyNum.setText(mPayBean.getUsedCurrency());
                        }
                        if (isOrder) {//未支付订单 显示优惠券数量
                            txtCouponsNum.setText(mPayBean.getCouponAmountStr());
                        }
                        txtOriginalPrice.setText("原价: " + mPayBean.getOriginalPriceStr());
                        txtTotalPrice.setText("实付价: " + mPayBean.getCurrentPriceStr());
                        mList.clear();
                        mAdapter.addData(mPayBean.getPayWayList());
                        mProductsBeanList.clear();
                        mPayCouserInfomatiomAdapter.addData(mPayBean.getProducts());
                    }
                });
    }

    @OnClick({R.id.img_back, R.id.txt_pay,
            R.id.rl_recruit_teach_currency,
            R.id.ll_address_management,
            R.id.ll_setting_address,
            R.id.rl_coupons})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.txt_pay:
                if (mPayBean != null) {
                    if (mPayBean.getIsShowAddress().equals("1")) {
                        if (addressId.equals("") || addressId.equals("0")) {
                            //弹出请选择地址
                            showToast("请选择地址");
                            return;
                        }
                    }
                }
                List<PayBean.PayWayListBean> payList = mAdapter.getData();
                for (final PayBean.PayWayListBean payWayListBean : payList) {
                    if (payWayListBean.getIsSelect().equals("1")) {
                        if (payWayListBean.getPayWay().equals(ALIPAY)) {//支付宝
                            alipayShow(payWayListBean);
                        } else if (payWayListBean.getPayWay().equals(WECHAT)) {//微信
                            weChatShow(payWayListBean);
                        }
                        break;
                    }
                }
                break;
            case R.id.rl_recruit_teach_currency:
                if (!isOrder) {
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(KeyUtil.TEACHING_CURRENCY_BEAN, mPayBean);
//                    IntentUtils.startIntentForResult(mActivity, TeachCurrencyActivity.class, bundle,
//                            ResultUtil.PAY_TEACHING_CURRENCY);
                }
                break;
            case R.id.ll_address_management:
                if (!isOrder) {
                    Bundle bundle1 = new Bundle();
                    bundle1.putBoolean(KeyUtil.IS_ADDRESSMANAGEMENT_PAGE, true);
//                    IntentUtils.startIntentForResult(mActivity, AddressManagementActivity.class,
//                            bundle1, ResultUtil.ADDRESSMANAGEMENT_CODE);
                }
                break;
            case R.id.ll_setting_address:
                if (!isOrder) {
                    Bundle bundle2 = new Bundle();
                    bundle2.putBoolean(KeyUtil.IS_ADDRESSMANAGEMENT_PAGE, true);
//                    IntentUtils.startIntentForResult(mActivity, AddressManagementActivity.class,
//                            bundle2, ResultUtil.ADDRESSMANAGEMENT_CODE);
                }
                break;
            case R.id.rl_coupons:
                if (!isOrder) {
//                    IntentUtils.startIntentForResult(mActivity, CouponsUseListActivity.class, new Bundle(), ResultUtil.COUPONS_CODE);
                }
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ResultUtil.PAY_TEACHING_CURRENCY) {
            if (resultCode == ResultUtil.PAY_TEACHING_CURRENCY) {
                currency = data.getStringExtra(KeyUtil.RECRUIT_TEACH_CURRENCY);
                if (!StringUtil.isNotBlankAndEmpty(currency) || currency.equals("0")) {
                    txtRecruitTeachCurrencyNum.setHint("0");
                } else {
                    txtRecruitTeachCurrencyNum.setText(currency);
                }
                getDataBalance(currency);
            }
        } else if (requestCode == ResultUtil.ADDRESSMANAGEMENT_CODE) {
            if (resultCode == ResultUtil.ADDRESSMANAGEMENT_CODE) {
                AddressManagementModel bean = (AddressManagementModel) data.getSerializableExtra(KeyUtil.ADDRESS_MANAGEMENT_BEAN);
                if (bean != null) {
                    addressId = bean.getId();
                    doBusiness();
                }
            }
        } else if (requestCode == ResultUtil.COUPONS_CODE) {
            if (resultCode == ResultUtil.COUPONS_CODE) {
                UseCouponsModel.AvailableCouponBean availableCouponBean = (UseCouponsModel.AvailableCouponBean) data.getSerializableExtra(KeyUtil.COUPONS_BEAN);
                if (availableCouponBean != null) {
                    couponsId = availableCouponBean.getId();
                    txtCouponsNum.setText("使用优惠券" + availableCouponBean.getAmountStr() + "元");
                    doBusiness();
                }
            } else if (resultCode == ResultUtil.COUPONS_NO_CODE) {
                couponsId = "0";
                txtCouponsNum.setText("不使用优惠券");
                doBusiness();
            }
        }
    }


    /**
     * 支付
     *
     * @param payWayListBean
     */
    private void weChatShow(final PayBean.PayWayListBean payWayListBean) {
        //提交微信订单
        OkGo.<ObjectResponse<SubmitWeiChatOrdersBean>>get(PathUtil.getSubmitBookOrder()).tag(this)
                .params(AppConfig.Pay.ORDER_ID, orderId)
                .params(AppConfig.Pay.ADDRESS_ID, addressId)
                .params(AppConfig.Pay.CURRENCY, currency)
                .params(AppConfig.Pay.COUPONID, couponsId)
                .params(AppConfig.Pay.UNIONKEY, PUtil.getPreferences(KeyUtil.UNIONKEY, ""))
                .params(AppConfig.Pay.PAY_WAY, payWayListBean.getPayWay())
                .execute(new ObjectCallback<ObjectResponse<SubmitWeiChatOrdersBean>>(mActivity, "正在加载...") {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(Response<ObjectResponse<SubmitWeiChatOrdersBean>> response) {
                        SubmitWeiChatOrdersBean weiBean = response.body().data;
                        boolean isPaySupported = wxApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
                        if (isPaySupported) {
                            PayReq req = new PayReq();
                            req.appId = weiBean.getResult().getAppid();
                            req.partnerId = weiBean.getResult().getPartnerid();
                            req.prepayId = weiBean.getResult().getPrepayid();
                            req.nonceStr = weiBean.getResult().getNoncestr();
                            req.timeStamp = weiBean.getResult().getTimestamp();
                            req.packageValue = weiBean.getResult().getPackageX();
                            req.sign = weiBean.getResult().getSign();
                            req.extData = "app data"; // optional
                            // 在支付之前,如果应用没有注册到微信,应该先调用IWXMsg.registerApp将应用注册到微信
                            boolean s = wxApi.sendReq(req);
                        } else {
                            showToast(R.string.wechat_current_version_does_not_support_payment_functions);
                        }
                    }
                });
    }

    /**
     * 支付宝支付
     *
     * @param payWayListBean
     */
    private void alipayShow(final PayBean.PayWayListBean payWayListBean) {
        if (SystemUtil.checkAliPayInstalled(mActivity)) {
            //提交支付宝订单
            OkGo.<ObjectResponse<SubmitAlipayOrdersBean>>post(PathUtil.getSubmitBookOrder()).tag(this)
                    .params(AppConfig.Pay.ORDER_ID, orderId)
                    .params(AppConfig.Pay.CURRENCY, currency)
                    .params(AppConfig.Pay.COUPONID, couponsId)
                    .params(AppConfig.Pay.ADDRESS_ID, addressId)
                    .params(AppConfig.Pay.UNIONKEY, PUtil.getPreferences(KeyUtil.UNIONKEY, ""))
                    .params(AppConfig.Pay.PAY_WAY, payWayListBean.getPayWay())
                    .execute(new ObjectCallback<ObjectResponse<SubmitAlipayOrdersBean>>(mActivity, "正在加载...") {
                        @SuppressLint("SetTextI18n")
                        @Override
                        public void onSuccess(Response<ObjectResponse<SubmitAlipayOrdersBean>> response) {
                            final SubmitAlipayOrdersBean submitOrdersBean = response.body().data;
                            new Thread(new Runnable() {
                                @Override
                                public void run() {
                                    Map<String, String> result = new PayTask(mActivity).payV2(submitOrdersBean.getPayStr(), true);
                                    L.i("支付宝===================" + result.toString());
                                    mHandler.sendMessage(mHandler.obtainMessage(SDK_PAY_FLAG, result));
                                }
                            }).start();
                        }
                    });
        } else {
            showToast("您还未安装支付宝");
        }
    }

    @Override
    public void onItemClick(PayBean.PayWayListBean bean, int position) {
        List<PayBean.PayWayListBean> payList = mAdapter.getData();
        for (PayBean.PayWayListBean data : payList) {
            data.setIsSelect("0");
        }
        payList.get(position).setIsSelect("1");
        mAdapter.notifyDataSetChanged();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    Intent mIntent = new Intent(mActivity, WXPayEntryActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString(ALIPAY_RESULT_STATUS, resultStatus);
                    mIntent.putExtras(bundle);
                    startActivity(mIntent);
//                    if (TextUtils.equals(resultStatus, "9000")) {
//                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
//                        showToast("支付成功");
//                    } else {
//                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                    //                        showToast("支付失败");
//                    }
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onItemPayCouserClick(PayBean.ProductsBean bean, int position) {

    }
}
