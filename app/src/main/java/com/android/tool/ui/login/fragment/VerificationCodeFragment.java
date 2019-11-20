package com.android.tool.ui.login.fragment;

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
import com.android.tool.model.RigisterLoginBean;
import com.android.tool.ui.base.BaseFragments;
import com.android.tool.ui.login.LoginFragmentActivity;
import com.android.tool.ui.login.util.LoginUtil;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.NetUtil;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.util.SystemUtil;
import com.android.tool.util.T;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.StringCodeCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.widget.TimeCountUtil;
import com.android.tool.widget.dialog.DialogUtil;
import com.android.tool.widget.wheelview.ClearEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.Set;

import butterknife.BindView;
import butterknife.OnClick;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.api.TagAliasCallback;


/**
 * class ：验证码登录
 * author：York(wuchunyuan)
 * time  : 2018/5/22 10:11
 */
public class VerificationCodeFragment extends BaseFragments {

    @BindView(R.id.edit_mobile)
    ClearEditText editMobile;
    @BindView(R.id.edit_verification_code)
    EditText editVerificationCode;
    @BindView(R.id.txt_get_verification_code)
    TextView txtGetVerificationCode;
    @BindView(R.id.bt_ok)
    TextView btOk;
    @BindView(R.id.txt_customer_service)
    TextView txtCustomerService;
    private TimeCountUtil mTimeCountUtil;
    private DialogUtil dialogUtil;
    private LoginFragmentActivity loginFragmentActivity;

    @Override
    public void initParms(Bundle mBundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_verification_code_login;
    }

    @Override
    public void initView() {
        LoginUtil.setVerificationCodeLoginStatus(editVerificationCode, editMobile, btOk);
        dialogUtil = new DialogUtil();
        mTimeCountUtil = new TimeCountUtil(mActivity, txtGetVerificationCode, TimeCountUtil.MILLISINFUTURE, TimeCountUtil.COUNTDOWNINTERVAL);
        SpannableString link = new SpannableString(LoginUtil.C_SERVICE);
        link.setSpan(new TxtCustomerService(), LoginUtil.C_SERVICE.length() - LoginUtil.MOBILE.length(), LoginUtil.C_SERVICE.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txtCustomerService.setText(link);
        txtCustomerService.setMovementMethod(LinkMovementMethod.getInstance());
        loginFragmentActivity = (LoginFragmentActivity) getActivity();
    }

    @Override
    public void initListener() {
        editMobile.addTextChangedListener(new EditMoblieText());
        editVerificationCode.addTextChangedListener(new EditPasswordText());
    }

    @Override
    public void doBusiness() {
    }

    @OnClick({R.id.txt_get_verification_code, R.id.bt_ok})
    public void onViewClicked(View view) {
        final String moblie = editMobile.getText().toString();
        switch (view.getId()) {
            case R.id.txt_get_verification_code:
                if (isNull(moblie)) {
                    OkGo.<String>get(PathUtil.getSendSms()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, moblie)
                            .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.LOGIN)
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
//                                                        .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.LOGIN)
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
                String code = editVerificationCode.getText().toString();
                if (isNull(moblie, code)) {
                    OkGo.<ObjectResponse<RigisterLoginBean>>post(PathUtil.getSmsLogin()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, moblie)
                            .params(AppConfig.LRAll.VERIFY_CODE, code)
                            .params(AppConfig.LRAll.OBJ_TYPE, AppConfig.LRAll.LOGIN)
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
                                        JPushInterface.setAlias(mActivity, PUtil.getPreferences(PUtil.MOBILE, ""), mAliasCallback);
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

    class TxtCustomerService extends ClickableSpan {
        @Override
        public void updateDrawState(TextPaint ds) {
            super.updateDrawState(ds);
            ds.setColor(ContextCompat.getColor(mActivity, R.color.c_7786));//设置超链接的颜色
            ds.setUnderlineText(true);
        }

        @Override
        public void onClick(View widget) {
            //拨打电话
            SystemUtil.callPhone(mActivity, LoginUtil.MOBILE);
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

    private boolean isCodeNull(String code) {
        if (!StringUtil.isNotBlankAndEmpty(code)) {
            showToast(R.string.img_verification_code_null);
            return false;
        }
        return true;
    }

    /**
     * 设置推送别名回调
     */
    private final TagAliasCallback mAliasCallback = new TagAliasCallback() {
        public void gotResult(int code, String alias, Set<String> tags) {
            switch (code) {
                case 0:
                    //别名设置正确
                    break;
                case 6002:
                    //别名设置错误码6002
                    break;
                default:
            }
        }
    };
}
