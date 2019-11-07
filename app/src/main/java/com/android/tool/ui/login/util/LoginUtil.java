package com.android.tool.ui.login.util;


import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.tool.R;


/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/3/27 16:49
 */
public class LoginUtil {
    public final static String MOBILE = "4006003363";
    public final static String C_SERVICE = "注册登录相关问题,请联系客服" + MOBILE;

    /**
     * 设置登陆、注册、短信页面按钮状态
     *
     * @param data
     * @param btOk
     * @param size
     */
    public static void setButtonStatus(String data, Button btOk, int size) {
        if (data.length() == size) {
            btOk.setClickable(true);
            btOk.setBackgroundResource(R.drawable.ok_red_true_selector);
        } else {
            btOk.setClickable(false);
            btOk.setBackgroundResource(R.drawable.ok_gray_false_selector);
        }
    }

    /**
     * 设置登陆、注册、短信页面按钮状态
     *
     * @param editPassword
     * @param editMoblie
     * @param btOk
     */
    public static void setPasswordLoginStatus(EditText editPassword, EditText editMoblie, TextView btOk) {
        if (editPassword.getText().toString().length() > 0 && editMoblie.getText().toString().length() >= 6) {
            btOk.setClickable(true);
            btOk.setBackgroundResource(R.drawable.ok_red_true_selector);
        } else {
            btOk.setClickable(false);
            btOk.setBackgroundResource(R.drawable.ok_gray_false_selector);
        }
    }

    /**
     * 设置密码和昵称
     *
     * @param editPassword
     * @param editNickName
     * @param btOk
     */
    public static void setPasswordNickNameStatus(EditText editPassword, EditText editNickName, TextView btOk) {
        if (editPassword.getText().toString().length() >= 6 && editNickName.getText().toString().length() > 0) {
            btOk.setClickable(true);
            btOk.setBackgroundResource(R.drawable.ok_red_true_selector);
        } else {
            btOk.setClickable(false);
            btOk.setBackgroundResource(R.drawable.ok_gray_false_selector);
        }
    }

    /**
     * 设置找回密码
     *
     * @param editPassword
     * @param btOk
     */
    public static void setFindPasswordStatus(EditText editPassword, TextView btOk) {
        if (editPassword.getText().toString().length() >= 6) {
            btOk.setClickable(true);
            btOk.setBackgroundResource(R.drawable.ok_red_true_selector);
        } else {
            btOk.setClickable(false);
            btOk.setBackgroundResource(R.drawable.ok_gray_false_selector);
        }
    }

    /**
     * 设置短信登陆、注册按钮状态
     *
     * @param editVerificationCode
     * @param editMoblie
     * @param btOk
     */
    public static void setVerificationCodeLoginStatus(EditText editVerificationCode, EditText editMoblie, TextView btOk) {
        if (editVerificationCode.getText().toString().length() == 6 && editMoblie.getText().toString().length() == 11) {
            btOk.setClickable(true);
            btOk.setBackgroundResource(R.drawable.ok_red_true_selector);
        } else {
            btOk.setClickable(false);
            btOk.setBackgroundResource(R.drawable.ok_gray_false_selector);
        }
    }

}
