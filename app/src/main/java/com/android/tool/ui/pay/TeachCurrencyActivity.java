package com.android.tool.ui.pay;//package com.android.tool.ui.pay;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.text.Editable;
//import android.text.TextWatcher;
//import android.view.View;
//import android.widget.EditText;
//import android.widget.TextView;
//
//import com.android.tool.R;
//import com.android.tool.ui.base.BaseActivitys;
//import com.android.tool.ui.pay.bean.PayBean;
//import com.android.tool.util.KeyUtil;
//import com.android.tool.util.ResultUtil;
//import com.android.tool.util.StringUtil;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//
///**
// * class ：学习金使用
// * author：York(wuchunyuan)
// * time  : 2018/6/14 14:26
// */
//public class TeachCurrencyActivity extends BaseActivitys {
//
//    @BindView(R.id.txt_title)
//    TextView txtTitle;
//    @BindView(R.id.txt_make_available_credit)
//    TextView txtMakeAvailableCredit;
//    @BindView(R.id.txt_tips)
//    TextView txtTips;
//    @BindView(R.id.et_teaching_currency_num)
//    EditText etTeachingCurrencyNum;
//    private PayBean payBean;
//
//    @Override
//    public void initParms(Bundle mBundle) {
//        steepSetStatusBarTranslucent(true, true);
//        payBean = (PayBean) mBundle.getSerializable(KeyUtil.TEACHING_CURRENCY_BEAN);
//    }
//
//    @Override
//    public int bindLayout() {
//        return R.layout.activity_teach_currency;
//    }
//
//    @Override
//    public void initView() {
//        txtTitle.setText(R.string.use_available_credits);
//    }
//
//    @Override
//    public void initListener() {
//        etTeachingCurrencyNum.addTextChangedListener(new EtTeachingCurrencyNum());
//    }
//
//    @Override
//    public void doBusiness() {
//        if (payBean != null) {
//            txtMakeAvailableCredit.setText(payBean.getMaxCurrency());
//            txtTips.setText(payBean.getCurrencyTip());
//            if (!StringUtil.isNotBlankAndEmpty(payBean.getMaxCurrency()) ||
//                    payBean.getMaxCurrency().equals("0")) {
//                etTeachingCurrencyNum.setHint(R.string.enter_amount_teaching_currency);
//            } else {
//                etTeachingCurrencyNum.setText(payBean.getMaxCurrency());
//            }
//        }
//    }
//
//    class EtTeachingCurrencyNum implements TextWatcher {
//
//        @Override
//        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//        }
//
//        @Override
//        public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//        }
//
//        @Override
//        public void afterTextChanged(Editable s) {
//            if (payBean != null) {
//                if (StringUtil.isNotBlankAndEmpty(s.toString()) && StringUtil.isNotBlankAndEmpty(payBean.getMaxCurrency())) {
//                    if (Integer.parseInt(s.toString()) > Integer.parseInt(payBean.getMaxCurrency())) {
//                        etTeachingCurrencyNum.setText(payBean.getMaxCurrency());
//                    }
//                }
//            }
//        }
//    }
//
//    @OnClick({R.id.img_back, R.id.bt_exchange})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.img_back:
//                onBackPressed();
//                break;
//            case R.id.bt_exchange:
//                String currency = etTeachingCurrencyNum.getText().toString();
//                if (StringUtil.isNotBlankAndEmpty(currency)) {
//                    String currencyStr = String.valueOf(Integer.parseInt(currency));
//                    Intent mIntent = new Intent();
//                    mIntent.putExtra(KeyUtil.RECRUIT_TEACH_CURRENCY, currencyStr);
//                    setResult(ResultUtil.PAY_TEACHING_CURRENCY, mIntent);
//                    finish();
//                } else {
//                    showToast("请输入学习金");
//                }
//                break;
//        }
//    }
//}
