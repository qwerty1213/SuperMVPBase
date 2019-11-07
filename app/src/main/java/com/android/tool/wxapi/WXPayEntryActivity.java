package com.android.tool.wxapi;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.pay.PayActivity;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.widget.ButtonState;
import com.tencent.mm.opensdk.constants.ConstantsAPI;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.OnClick;

public class WXPayEntryActivity extends BaseActivitys implements IWXAPIEventHandler {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.img)
    ImageView img;
    @BindView(R.id.bt_ok)
    ButtonState btOk;
    private IWXAPI api;
    private String alipayStatus;

    @Override
    public void initParms(Bundle mBundle) {
        isAddActivity = false;
        steepSetStatusBarTranslucent(true, false);
        ActivityManagementUtil.getInstance().addPayActivity(this);
        //支付宝状态
        alipayStatus = mBundle.getString(PayActivity.ALIPAY_RESULT_STATUS);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_pay_result;
    }

    @Override
    public void initView() {
        api = WXAPIFactory.createWXAPI(this, AppConfig.Pay.APP_ID);
        api.handleIntent(getIntent(), this);
    }

    @Override
    public void initListener() {
        txtTitle.setText(R.string.pay_for_results);
        //支付宝
        if (StringUtil.isNotBlankAndEmpty(alipayStatus)) {
            if (alipayStatus.equals(PayActivity.ALIPAY_SUCCESS_STATUS)) {
                img.setImageResource(R.mipmap.pay_success3x);
                btOk.setText("完成");
            } else {
                img.setImageResource(R.mipmap.pay_failure3x);
                btOk.setText("重新支付");
            }
        }
    }

    @Override
    public void doBusiness() {
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq req) {
    }

    @Override
    public void onResp(BaseResp resp) {
        if (resp.getType() == ConstantsAPI.COMMAND_PAY_BY_WX) {
            if (resp.errCode == 0) {//支付成功
                img.setImageResource(R.mipmap.pay_success3x);
                btOk.setText("完成");
            } else if (resp.errCode == -1) {//错误
                img.setImageResource(R.mipmap.pay_failure3x);
                btOk.setText("重新支付");
            } else if (resp.errCode == -2) {//用户取消
                img.setImageResource(R.mipmap.pay_failure3x);
                btOk.setText("重新支付");
            }
        }
    }

    @OnClick({R.id.img_back, R.id.bt_ok})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.bt_ok:
                onBackPressed();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        ActivityManagementUtil.getInstance().exitPayActivity();
        EventBus.getDefault().post(KeyUtil.UPDATE_PAY);
        super.onBackPressed();
    }
}