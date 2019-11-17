package com.android.tool.ui.community.browse.helper.fragment;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Gravity;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;

import com.android.tool.R;
import com.android.tool.presenter.impl.CommentDialogFragmentCallback;


public class CommentDialogFragment extends DialogFragment implements View.OnClickListener {

    private EditText commentEditText;
    private ImageView sendButton;
    private InputMethodManager inputMethodManager;
    private CommentDialogFragmentCallback dataCallback;

    @Override
    public void onAttach(Context context) {
        if (!(getActivity() instanceof CommentDialogFragmentCallback)) {
            throw new IllegalStateException("DialogFragment 所在的 activity 必须实现 DialogFragmentDataCallback 接口");
        }
        super.onAttach(context);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog mDialog = new Dialog(getActivity(), R.style.BottomDialog);
        mDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        mDialog.setContentView(R.layout.dialog_fragment_comment_layout);
        mDialog.setCanceledOnTouchOutside(true);
        Window window = mDialog.getWindow();
        window.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        WindowManager.LayoutParams layoutParams;
        if (window != null) {
            layoutParams = window.getAttributes();
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
            window.setAttributes(layoutParams);
        }

        commentEditText = (EditText) mDialog.findViewById(R.id.edit_comment);
        sendButton = (ImageView) mDialog.findViewById(R.id.image_btn_comment_send);

        fillEditText();
        setSoftKeyboard();

        commentEditText.addTextChangedListener(mTextWatcher);

        sendButton.setOnClickListener(this);

        return mDialog;
    }

    @Override
    public void onStart() {
        super.onStart();
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
    }

    private void fillEditText() {
        dataCallback = (CommentDialogFragmentCallback) getActivity();
        commentEditText.setHint(dataCallback.getCommentText());
//        commentEditText.setSelection(dataCallback.getCommentText().length());
        if (commentEditText.length() == 0) {
            sendButton.setEnabled(false);
            sendButton.setColorFilter(ContextCompat.getColor(getActivity(), R.color.iconCover));
        }
    }

    private void setSoftKeyboard() {
        commentEditText.setFocusable(true);
        commentEditText.setFocusableInTouchMode(true);
        commentEditText.requestFocus();

        //为 commentEditText 设置监听器，在 DialogFragment 绘制完后立即呼出软键盘，呼出成功后即注销
        commentEditText.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputMethodManager != null) {
                    if (inputMethodManager.showSoftInput(commentEditText, 0)) {
                        commentEditText.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    }
                }
            }
        });
    }

    private TextWatcher mTextWatcher = new TextWatcher() {

        private CharSequence temp;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            temp = s;
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
        }

        @Override
        public void afterTextChanged(Editable s) {
            if (temp.length() > 0) {
                sendButton.setEnabled(true);
                sendButton.setClickable(true);
                sendButton.setColorFilter(ContextCompat.getColor(getActivity(), R.color.colorAccent));
            } else {
                sendButton.setEnabled(false);
                sendButton.setColorFilter(ContextCompat.getColor(getActivity(), R.color.iconCover));
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_btn_comment_send:
//                Toast.makeText(getActivity(), commentEditText.getText().toString(), Toast.LENGTH_SHORT).show();
                dataCallback.setCommentText(commentEditText.getText().toString());
                commentEditText.setText("");
                dismiss();
                break;
            default:
                break;
        }
    }

//    @Override
//    public void onDismiss(DialogInterface dialog) {
//        dataCallback.setCommentText(commentEditText.getText().toString());
//        super.onDismiss(dialog);
//    }

//    @Override
//    public void onCancel(DialogInterface dialog) {
//        dataCallback.setCommentText(commentEditText.getText().toString());
//        super.onCancel(dialog);
//    }
}