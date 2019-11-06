package com.android.tool.ui.login;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.tool.R;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.login.util.LoginUtil;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.StringDialogCallback;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：设置昵称  密码
 * author：York(wuchunyuan)
 * time  : 2018/5/23 15:49
 */
public class SettingPasswordActivity extends BaseActivitys {

    @BindView(R.id.edit_set_password)
    EditText editSetPassword;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.bt_ok)
    TextView btOk;
    private int eye = 0;
    private String mobile, code;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
        mobile = mBundle.getString(KeyUtil.MOBLIE, "");
        code = mBundle.getString(KeyUtil.CODE, "");
        ActivityManagementUtil.getInstance().addFindPsdActivity(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting_password;
    }

    @Override
    public void initView() {
        LoginUtil.setFindPasswordStatus(editSetPassword, btOk);
    }

    @Override
    public void initListener() {
        editSetPassword.addTextChangedListener(new EditPasswordText());
    }

    @Override
    public void doBusiness() {

    }

    @OnClick({R.id.iv_eye, R.id.bt_ok, R.id.img_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_eye:
                if (eye == 0) {
                    editSetPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivEye.setImageResource(R.mipmap.eye_closed_icon);
                    eye = 1;
                } else {
                    editSetPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivEye.setImageResource(R.mipmap.eye_open_icon);
                    eye = 0;
                }
                break;
            case R.id.bt_ok:
                String password = editSetPassword.getText().toString();
                if (isNull(mobile, password)) {
                    OkGo.<String>post(PathUtil.getRevisePwd()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, mobile)
                            .params(AppConfig.LRAll.PASS_WORD, password)
                            .params(AppConfig.LRAll.VERIFY_CODE, code)
                            .execute(new StringDialogCallback(mActivity) {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    ActivityManagementUtil.getInstance().exitFindPsdActivity();
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
     * 监听输入密码 显示眼睛
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
            LoginUtil.setFindPasswordStatus(editSetPassword, btOk);
            if (editable.length() > 0) {
                ivEye.setVisibility(View.VISIBLE);
            } else {
                ivEye.setVisibility(View.GONE);
            }
        }
    }

    private boolean isNull(String moblie, String password) {
        if (!StringUtil.isPhoneNumber(moblie)) {
            showToast(R.string.the_phone_number_is_null);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(password)) {
            showToast(R.string.text_setting_6_20_password);
            return false;
        }
        return true;
    }
}
