package com.android.tool.ui.login.util;


import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.util.StringUtil;


/**
 * class ：密码输入框
 * author：York(wuchunyuan)
 * time  : 2017/11/9 11:03
 */
public class SecurityPasswordKLEditText extends LinearLayout {
    private EditText mEditText;
    private TextView oneTextView;
    private TextView twoTextView;
    private TextView threeTextView;
    private TextView fourTextView;
    private TextView fiveTextView;
    private TextView sixTextView;
    private TextView[] mTextViews;

    private StringBuilder builder = new StringBuilder();
    private SecurityEditCompileListener mListener;

    public SecurityPasswordKLEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater inflater = LayoutInflater.from(context);
        initWidget(inflater);
    }

    private void initWidget(LayoutInflater inflater) {
        View contentView = inflater.inflate(R.layout.auth_code_edittext_kl_widget, this, false);
        mEditText = (EditText) contentView.findViewById(R.id.sdk2_pwd_edit_simple);
        oneTextView = (TextView) contentView.findViewById(R.id.pwd_one_tv);
        twoTextView = (TextView) contentView.findViewById(R.id.pwd_two_tv);
        threeTextView = (TextView) contentView.findViewById(R.id.pwd_three_tv);
        fourTextView = (TextView) contentView.findViewById(R.id.pwd_four_tv);
        fiveTextView = (TextView) contentView.findViewById(R.id.pwd_five_tv);
        sixTextView = (TextView) contentView.findViewById(R.id.pwd_six_tv);
        LayoutParams lParams = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        mEditText.addTextChangedListener(mTextWatcher);
        mTextViews = new TextView[]{oneTextView, twoTextView, threeTextView, fourTextView, fiveTextView, sixTextView};
        this.addView(contentView, lParams);
    }

    public String getText() {
        if (mEditText == null) {
            return "";
        } else {
            return mEditText.getText().toString();
        }
    }

    public void setText(String text) {
        if (StringUtil.isNotBlankAndEmpty(text)) {
            try {
                mEditText.setText(text);
                String[] arr = text.split("");
                for (int i = 1; i < arr.length; i++) {
                    mTextViews[i - 1].setText(arr[i]);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    TextWatcher mTextWatcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (mListener != null) {
                mListener.afterTextChanged(s);
            }
            setBuilderValue(s.toString());
        }
    };

    private void setBuilderValue(String password) {
        int strLen = password.length();
        int builderLen = builder.length();
        if (strLen == builderLen) {
            return;
        }
        if (strLen < builderLen) {
            for (int i = strLen; i <= builderLen - 1; i++) {
                mTextViews[i].setText("");
            }
            builder = new StringBuilder(password);
        } else {
            builder = new StringBuilder(password);
            for (int i = builderLen; i <= strLen - 1; i++) {
//                if (SendVerificationCodeActivity.showType == ShowType.hideNumber) {
//                    mTextViews[i].setText("●");
//                } else {
//                    mTextViews[i].setText(builder.toString().substring(i, i + 1));
//                }
            }
        }
        if (mTextViews.length == builder.length() && this.mListener != null) {
            mListener.onNumCompleted(password);
        }
    }

    public void setSecurityEditCompileListener(SecurityEditCompileListener mListener) {
        this.mListener = mListener;
    }

    public void getAfterTextChangedListener(SecurityEditCompileListener mListener) {
        this.mListener = mListener;
    }
}