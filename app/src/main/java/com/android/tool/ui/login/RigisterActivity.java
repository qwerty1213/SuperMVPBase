package com.android.tool.ui.login;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.android.tool.R;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.login.util.LoginUtil;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.StringCodeCallback;
import com.android.tool.utility.StringDialogCallback;
import com.android.tool.widget.TimeCountUtil;
import com.android.tool.widget.dialog.DialogUtil;
import com.android.tool.widget.wheelview.ClearEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * class ：注册
 * author：York(wuchunyuan)
 * time  : 2018/3/23 15:16
 */
public class RigisterActivity extends BaseActivitys {

    @BindView(R.id.edit_mobile)
    ClearEditText editMobile;
    @BindView(R.id.edit_verification_code)
    EditText editVerificationCode;
    @BindView(R.id.txt_get_verification_code)
    TextView txtGetVerificationCode;
    @BindView(R.id.bt_ok)
    TextView btOk;
    @BindView(R.id.txt_privacy_policy)
    TextView txtPrivacyPolicy;
    private TimeCountUtil mTimeCountUtil;
    private DialogUtil dialogUtil;
    private String privacyPolicy = "注册代表您已同意注册协议";

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
        ActivityManagementUtil.getInstance().addLoginActivity(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_rigister;
    }

    @Override
    public void initView() {
        LoginUtil.setVerificationCodeLoginStatus(editVerificationCode, editMobile, btOk);
        dialogUtil = new DialogUtil();
        mTimeCountUtil = new TimeCountUtil(mActivity, txtGetVerificationCode, TimeCountUtil.MILLISINFUTURE, TimeCountUtil.COUNTDOWNINTERVAL);
        SpannableString link = new SpannableString(privacyPolicy);
        link.setSpan(new TxtPrivacyPolicy(), privacyPolicy.length() - 4, privacyPolicy.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtPrivacyPolicy.setText(link);
        txtPrivacyPolicy.setMovementMethod(LinkMovementMethod.getInstance());
    }

    @Override
    public void initListener() {
        editMobile.addTextChangedListener(new EditMoblieText());
        editVerificationCode.addTextChangedListener(new EditPasswordText());
    }

    @Override
    public void doBusiness() {

    }

    @OnClick({R.id.txt_get_verification_code, R.id.bt_ok, R.id.img_back})
    public void onViewClicked(View view) {
        final String moblie = editMobile.getText().toString();
        final String code = editVerificationCode.getText().toString();
        switch (view.getId()) {
            case R.id.txt_get_verification_code:
                if (isNull(moblie)) {
                    OkGo.<String>get(PathUtil.getSendSms()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, moblie)
                            .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.REGISTER)
                            .params(AppConfig.LRAll.CAPTCHA, "0")
                            .execute(new StringCodeCallback(mActivity) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    mTimeCountUtil.start();
                                }
                            });
//                    OkGo.<ObjectResponse<CodeBean>>get(PathUtil.getCaptcha()).tag(this)
//                            .execute(new ObjectNoDialogCallback<ObjectResponse<CodeBean>>(mActivity, "") {
//                                @Override
//                                public void onSuccess(Response<ObjectResponse<CodeBean>> response) {
//                                    CodeBean bean = response.body().data;
//                                    final Dialog dialog = dialogUtil.showVerificationCodeDialog(bean, mActivity);
//                                    dialogUtil.setOnOkClickListener(new DialogUtil.OnOkClickListener() {
//                                        @Override
//                                        public void onOkClick(String code) {
//                                            if (isCodeNull(code)) {
//                                                OkGo.<String>get(PathUtil.getSendSms()).tag(this)
//                                                        .params(AppConfig.LRAll.MOBILE, moblie)
//                                                        .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.REGISTER)
//                                                        .params(AppConfig.LRAll.CAPTCHA, code)
//                                                        .execute(new StringCodeCallback(mActivity) {
//                                                            @Override
//                                                            public void onSuccess(Response<String> response) {
//                                                                dialog.dismiss();
//                                                                mTimeCountUtil.start();
//                                                            }
//                                                        });
//                                            }
//                                        }
//                                    });
//                                }
//                            });
                }
                break;
            case R.id.bt_ok:
                if (isNull(moblie, code)) {//验证短信验证码
                    OkGo.<String>get(PathUtil.getCheckMobileVerify()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, moblie)
                            .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.REGISTER)
                            .params(AppConfig.LRAll.VERIFY_CODE, code)
                            .execute(new StringDialogCallback(this) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString(KeyUtil.MOBLIE, moblie);
                                    bundle.putString(KeyUtil.CODE, code);
                                    IntentUtils.startIntent(mActivity, SettingPasswordNickNameActivity.class, bundle);

                                }
                            });
                }
                break;
            case R.id.img_back:
                onBackPressed();
                break;
        }
    }

    /**
     * 监听输入密码
     */
    class EditPasswordText implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            LoginUtil.setVerificationCodeLoginStatus(editVerificationCode, editMobile, btOk);
        }
    }

    /**
     * 监听输入手机号码
     */
    class EditMoblieText implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            LoginUtil.setVerificationCodeLoginStatus(editVerificationCode, editMobile, btOk);
        }
    }

    private boolean isNull(String moblie) {
        if (!StringUtil.isNotBlankAndEmpty(moblie)) {
            showToast(R.string.the_phone_number_is_null);
            return false;
        }
        if (!StringUtil.isPhoneNumber(moblie)) {
            showToast(R.string.the_phone_number_format_is_not_correct);
            return false;
        }
        return true;
    }

    private boolean isNull(String moblie, String code) {
        if (!StringUtil.isPhoneNumber(moblie)) {
            showToast(R.string.the_phone_number_is_null);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(code)) {
            showToast(R.string.verify_that_the_code_cannot_be_empty);
            return false;
        }
        return true;
    }


    private boolean isCodeNull(String code) {
        if (!StringUtil.isNotBlankAndEmpty(code)) {
            showToast(R.string.img_verification_code_null);
            return false;
        }
        return true;
    }

    class TxtPrivacyPolicy extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(ContextCompat.getColor(mActivity, R.color.c_7786));//设置超链接的颜色
            ds.setUnderlineText(true);
        }

        @Override
        public void onClick(View widget) {

//            WebURLUtil.openUrl(PathUtil.Path.AGREEMENT_LINK_URL, mActivity);
        }
    }


}
