package com.android.tool.ui.login.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.LinkMovementMethod;
import android.text.method.PasswordTransformationMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.tool.R;
import com.android.tool.model.RigisterLoginBean;
import com.android.tool.ui.base.BaseFragments;
import com.android.tool.ui.login.FindPasswordActivity;
import com.android.tool.ui.login.LoginFragmentActivity;
import com.android.tool.ui.login.RigisterActivity;
import com.android.tool.ui.login.util.LoginUtil;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.NetUtil;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.util.SystemUtil;
import com.android.tool.util.T;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.widget.wheelview.ClearEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import butterknife.BindView;
import butterknife.OnClick;


/**.
 * class ：密码登录
 * author：York(wuchunyuan)
 * time  : 2018/5/22 10:10
 */
public class PasswordLoginFragment extends BaseFragments {

    @BindView(R.id.edit_mobile)
    ClearEditText editMobile;
    @BindView(R.id.edit_password)
    EditText editPassword;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.bt_ok)
    TextView btOk;
    @BindView(R.id.txt_customer_service)
    TextView txtCustomerService;
    private int eye = 0;
    private LoginFragmentActivity loginFragmentActivity;

    @Override
    public void initParms(Bundle mBundle) {
    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_password_login;
    }

    @Override
    public void initView() {
        LoginUtil.setPasswordLoginStatus(editPassword, editMobile, btOk);
        SpannableString link = new SpannableString(LoginUtil.C_SERVICE);
        link.setSpan(new TxtCustomerService(), LoginUtil.C_SERVICE.length() - LoginUtil.MOBILE.length(), LoginUtil.C_SERVICE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtCustomerService.setText(link);
        txtCustomerService.setMovementMethod(LinkMovementMethod.getInstance());
        loginFragmentActivity = (LoginFragmentActivity) getActivity();
        if (!PathUtil.DEBUG) {
            editMobile.setText("15201506339");
            editPassword.setText("123456");
            LoginUtil.setPasswordLoginStatus(editPassword, editMobile, btOk);
        }
    }

    @Override
    public void initListener() {
        editMobile.addTextChangedListener(new EditMoblieText());
        editPassword.addTextChangedListener(new EditPasswordText());
    }

    @Override
    public void doBusiness() {

    }

    @OnClick({R.id.iv_eye, R.id.bt_ok, R.id.txt_moblie_rigister, R.id.txt_forget_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_eye:
                if (eye == 0) {
                    editPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    ivEye.setImageResource(R.mipmap.eye_closed_icon);
                    eye = 1;
                } else {
                    editPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    ivEye.setImageResource(R.mipmap.eye_open_icon);
                    eye = 0;
                }
                break;
            case R.id.bt_ok:
                String moblie = editMobile.getText().toString();
                String password = editPassword.getText().toString();
                if (isNull(moblie, password)) {
                    OkGo.<ObjectResponse<RigisterLoginBean>>post(PathUtil.getPswLogin()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, moblie)
                            .params(AppConfig.LRAll.PASS_WORD, password)
                            .execute(new ObjectCallback<ObjectResponse<RigisterLoginBean>>(mActivity, "") {
                                @Override
                                public void onSuccess(Response<ObjectResponse<RigisterLoginBean>> response) {
                                    RigisterLoginBean bean = response.body().data;
                                    if (bean != null) {
                                        PUtil.putPreferences(bean.getToken(),
                                                bean.getUserId(),
                                                bean.getNickName(),
                                                bean.getMobile(),
                                                bean.getAvatar(),
                                                bean.getSex(),
                                                bean.getRole(),
                                                bean.getRoleText(),
                                                bean.getIsAuth());
                                        if (loginFragmentActivity != null) {
                                            loginFragmentActivity.setRefreshResult();
                                        }

                                        ActivityManagementUtil.getInstance().exitLoginActivity();
                                    }
                                }

                                @Override
                                public void onError(Response<ObjectResponse<RigisterLoginBean>> response) {
                                    super.onError(response);
                                    if (!NetUtil.isConnected(mActivity)) {
                                        T.customToastCenterShort(mActivity, R.string.loading_network_error);
                                    }
                                }
                            });
                }
                break;
            case R.id.txt_moblie_rigister:
                IntentUtils.startIntent(mActivity, RigisterActivity.class, new Bundle());
                break;
            case R.id.txt_forget_password:
                IntentUtils.startIntent(mActivity, FindPasswordActivity.class, new Bundle());
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
            LoginUtil.setPasswordLoginStatus(editPassword, editMobile, btOk);
            if (editable.length() > 0) {
                ivEye.setVisibility(View.VISIBLE);
            } else {
                ivEye.setVisibility(View.GONE);
            }
        }
    }

    /**
     * 监听输入手机号码 显示删除按钮
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
            LoginUtil.setPasswordLoginStatus(editPassword, editMobile, btOk);
        }
    }

    private boolean isNull(String moblie, String password) {
        if (!StringUtil.isNotBlankAndEmpty(moblie)) {
            showToast(R.string.this_user_not_null);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(password)) {
            showToast(R.string.please_enter_your_password);
            return false;
        }
        return true;
    }

    class TxtCustomerService extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(ContextCompat.getColor(mActivity, R.color.c_7786));//设置超链接的颜色
            ds.setUnderlineText(true);
        }

        @Override
        public void onClick(View widget) {
            SystemUtil.callPhone(mActivity, LoginUtil.MOBILE);
        }
    }


}
