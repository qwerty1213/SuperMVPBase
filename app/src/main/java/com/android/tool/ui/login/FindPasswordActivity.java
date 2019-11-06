package com.android.tool.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.android.tool.R;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.login.util.LoginUtil;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.StringCodeCallback;
import com.android.tool.utility.StringDialogCallback;
import com.android.tool.widget.TimeCountUtil;
import com.android.tool.widget.dialog.DialogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：找回密码
 * author：York(wuchunyuan)
 * time  : 2018/5/24 10:09
 */
public class FindPasswordActivity extends BaseActivitys {

    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.edit_mobile)
    EditText editMobile;
    @BindView(R.id.edit_verification_code)
    EditText editVerificationCode;
    @BindView(R.id.txt_get_verification_code)
    TextView txtGetVerificationCode;
    @BindView(R.id.next_ok)
    TextView nextOk;
    private TimeCountUtil mTimeCountUtil;
    private DialogUtil dialogUtil;
    private boolean isFindChangePassword;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
        ActivityManagementUtil.getInstance().addFindPsdActivity(this);
        isFindChangePassword = mBundle.getBoolean(KeyUtil.IS_FIND_CHANGE_PASSWORD, false);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_find_password;
    }

    @Override
    public void initView() {
        if (isFindChangePassword) {
            txtTitle.setText(R.string.text_change_password);
            editMobile.setText(PUtil.getPreferences(PUtil.MOBILE, ""));
            editMobile.setEnabled(false);
        } else {
            txtTitle.setText(R.string.come_back_password);
            editMobile.setText("");
        }
        LoginUtil.setVerificationCodeLoginStatus(editVerificationCode, editMobile, nextOk);
        dialogUtil = new DialogUtil();
        mTimeCountUtil = new TimeCountUtil(mActivity, txtGetVerificationCode, TimeCountUtil.MILLISINFUTURE, TimeCountUtil.COUNTDOWNINTERVAL);
    }

    @Override
    public void initListener() {
        editMobile.addTextChangedListener(new EditMoblieText());
        editVerificationCode.addTextChangedListener(new EditPasswordText());
    }

    @Override
    public void doBusiness() {

    }

    @OnClick({R.id.txt_get_verification_code, R.id.next_ok, R.id.img_back})
    public void onViewClicked(View view) {
        final String moblie = editMobile.getText().toString();
        final String code = editVerificationCode.getText().toString();
        switch (view.getId()) {
            case R.id.txt_get_verification_code:
                if (isNull(moblie)) {
                    OkGo.<String>get(PathUtil.getSendSms()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, moblie)
                            .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.FINDPWD)
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
//                                                        .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.FINDPWD)
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
            case R.id.next_ok:
                if (isNull(moblie, code)) {//验证短信验证码
                    OkGo.<String>get(PathUtil.getCheckMobileVerify()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, moblie)
                            .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.FINDPWD)
                            .params(AppConfig.LRAll.VERIFY_CODE, code)
                            .execute(new StringDialogCallback(this) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Bundle bundle = new Bundle();
                                    bundle.putString(KeyUtil.MOBLIE, moblie);
                                    bundle.putString(KeyUtil.CODE, code);
                                    IntentUtils.startIntent(mActivity, SettingPasswordActivity.class, bundle);
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
            LoginUtil.setVerificationCodeLoginStatus(editVerificationCode, editMobile, nextOk);
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
            LoginUtil.setVerificationCodeLoginStatus(editVerificationCode, editMobile, nextOk);
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

}
