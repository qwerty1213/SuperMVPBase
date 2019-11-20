/**
 * @ProjectName:CYFrameAndroid
 * @Title: CYouEditText.java
 * @Package com.cyou.cyframeandroid.widget
 * @Description: TODO(重写editText可以在右下角显示字数)
 * @author liuqi qiliu_17173@cyou-inc.com
 * @date 2013-8-6 下午5:05:30
 * @version V1.0
 * Copyright (c) 2013搜狐公司-版权所有
 */
package com.android.tool.widget.wheelview;

import android.content.Context;
import android.content.res.TypedArray;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tool.R;


public class EditTextWithCount extends RelativeLayout {
    private RelativeLayout layout = null;
    private EditText contentEt = null;
    private TextView countTv = null;
    private int num = 200;

    public EditTextWithCount(Context context) {
        super(context);
        initWidget(context);
    }

    public EditTextWithCount(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs,
                R.styleable.EditTextWithCount);
        num = array.getInteger(R.styleable.EditTextWithCount_count, 200);
        array.recycle();
        initWidget(context);
    }

    public EditTextWithCount(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initWidget(context);
    }

    public String getInputContent() {
        return contentEt.getText().toString().trim();
    }

    public void setInputContent(String content) {
        contentEt.setText(content);
    }

    public void setFocusable() {

        contentEt.setFocusable(true);// 在EditView 设置焦点
        contentEt.requestFocus();
    }


    public void initWidget(Context context) {
        layout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.edittext_count, this);
        contentEt = layout.findViewById(R.id.conEt);
        contentEt.setFocusable(true);
        countTv = layout.findViewById(R.id.countTv);
        countTv.setText(0 + "/" + num);

        contentEt.addTextChangedListener(new TextWatcher() {
            private CharSequence temp;
            private int selectionStart;
            private int selectionEnd;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                temp = s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                countTv.setText(s.length() + "/" + num);
                selectionStart = contentEt.getSelectionStart();
                selectionEnd = contentEt.getSelectionEnd();
                if (temp.length() > num) {
                    s.delete(selectionStart - 1, selectionEnd);
                    int tempSelection = selectionEnd;
                    contentEt.setText(s);
                    contentEt.setSelection(tempSelection);
                }
                if (mAfterTextChangedListener != null) {
                    mAfterTextChangedListener.afterTextChangeds(s);
                }
            }
        });
    }

    public interface AfterTextChangedListener {
        void afterTextChangeds(Editable s);
    }

    private AfterTextChangedListener mAfterTextChangedListener;

    public void setAfterTextChangedListener(AfterTextChangedListener mAfterTextChangedListener) {
        this.mAfterTextChangedListener = mAfterTextChangedListener;
    }

}
