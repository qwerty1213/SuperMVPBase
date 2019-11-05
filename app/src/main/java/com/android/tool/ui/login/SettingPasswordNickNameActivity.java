package com.android.tool.ui.login;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.tool.R;
import com.android.tool.model.ProductTypeBean;
import com.android.tool.model.RigisterLoginBean;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.login.util.LoginUtil;
import com.android.tool.util.ActivityManagementUtil;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.widget.wheelview.ClearEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import butterknife.BindView;
import butterknife.OnClick;


/**
 * class ：设置昵称  密码
 * author：York(wuchunyuan)
 * time  : 2018/5/23 15:49
 */
public class SettingPasswordNickNameActivity extends BaseActivitys {

    @BindView(R.id.edit_set_password)
    EditText editSetPassword;
    @BindView(R.id.iv_eye)
    ImageView ivEye;
    @BindView(R.id.edit_set_nickname)
    ClearEditText editSetNickname;
    @BindView(R.id.bt_ok)
    TextView btOk;
    @BindView(R.id.txt_text_type)
    TextView txtTextType;
    private int eye = 0;
    private String mobile, code;
    private ArrayList<String> productTypeList = new ArrayList<>();
    private String productTypeId;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
        mobile = mBundle.getString(KeyUtil.MOBLIE, "");
        code = mBundle.getString(KeyUtil.CODE, "");
        ActivityManagementUtil.getInstance().addLoginActivity(this);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_setting_nickname_password;
    }

    @Override
    public void initView() {
        LoginUtil.setPasswordNickNameStatus(editSetPassword, editSetNickname, btOk);
    }

    @Override
    public void initListener() {
        editSetNickname.addTextChangedListener(new EditNickNameText());
        editSetPassword.addTextChangedListener(new EditPasswordText());
    }

    @Override
    public void doBusiness() {

    }

    @OnClick({R.id.iv_eye, R.id.bt_ok, R.id.img_back, R.id.rl_type})
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
                String nickName = editSetNickname.getText().toString();
                String textType = txtTextType.getText().toString();
                if (isNull(mobile, password, nickName, textType)) {
                    OkGo.<ObjectResponse<RigisterLoginBean>>get(PathUtil.getSmsRegister()).tag(this)
                            .params(AppConfig.LRAll.MOBILE, mobile)
                            .params(AppConfig.LRAll.PASS_WORD, password)
                            .params(AppConfig.LRAll.NICKNAME, nickName)
                            .params(AppConfig.LRAll.ROLE, productTypeId)
                            .params(AppConfig.LRAll.VERIFY_CODE, code)
                            .execute(new ObjectCallback<ObjectResponse<RigisterLoginBean>>(mActivity, "") {
                                @Override
                                public void onSuccess(Response<ObjectResponse<RigisterLoginBean>> response) {
                                    RigisterLoginBean bean = response.body().data;
                                    PUtil.putPreferences(bean.getToken(),
                                            bean.getUserId(),
                                            bean.getNickName(),
                                            bean.getMobile(),
                                            bean.getAvatar(),
                                            bean.getSex(),
                                            bean.getRole(),
                                            bean.getRoleText(),
                                            bean.getIsAuth());
//                                    JPushInterface.setAlias(mActivity, PUtil.getPreferences(PUtil.MOBILE, ""), mAliasCallback);
                                    ActivityManagementUtil.getInstance().exitLoginActivity();
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
            LoginUtil.setPasswordNickNameStatus(editSetPassword, editSetNickname, btOk);
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
    class EditNickNameText implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {
            LoginUtil.setPasswordNickNameStatus(editSetPassword, editSetNickname, btOk);
        }
    }

    private boolean isNull(String moblie, String password, String nickName, String textType) {
        if (!StringUtil.isPhoneNumber(moblie)) {
            showToast(R.string.the_phone_number_is_null);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(password)) {
            showToast(R.string.text_setting_6_20_password);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(nickName)) {
            showToast(R.string.please_enter_your_10_nickname);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(textType)
                && !StringUtil.isNotBlankAndEmpty(productTypeId)) {
            showToast(R.string.please_select_type);
            return false;
        }
        return true;
    }


}
