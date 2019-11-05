package com.android.tool.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.tool.R;
import com.android.tool.model.CodeBean;
import com.android.tool.util.GUtils;
import com.android.tool.util.StringUtil;
import com.android.tool.util.T;


/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/4/4 16:54
 */
public class DialogUtil {
    private static Dialog mDialog;

    /**
     * 图片验证码
     *
     * @param mContext
     * @return
     */
    public static Dialog showVerificationCodeDialog(final CodeBean bean, final Activity mContext) {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mDialog = new Dialog(mContext, R.style.AlertDialogStyle);
        View layout = inflater.inflate(R.layout.dialog_verification_code_layout, null);
        LinearLayout rlLayout = layout.findViewById(R.id.lLayout_bg);
        final ImageView ivVerifyCode = layout.findViewById(R.id.iv_verify_code);
        GUtils.glideLoadSkipMemoryCache(mContext, bean.getUrl(), ivVerifyCode);
        final EditText editText = layout.findViewById(R.id.editText);
        TextView txtCancel = layout.findViewById(R.id.txt_cancel);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        TextView txtOk = layout.findViewById(R.id.txt_ok);
        txtOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String code = editText.getText().toString().trim();
                if (!StringUtil.isNotBlankAndEmpty(code)) {
                    T.customToastCenterShort(mContext, "请输入验证码");
                    return;
                }
                if (mOnOkClickListener != null) {
                    mOnOkClickListener.onOkClick(code);
                }
            }
        });
        ivVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GUtils.glideLoadSkipMemoryCache(mContext, bean.getUrl(), ivVerifyCode);
            }
        });
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.addContentView(layout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(true);
        mDialog.setContentView(layout);
        rlLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));
        mDialog.show();
        return mDialog;
    }

    public interface OnOkClickListener {
        void onOkClick(String code);
    }

    private static OnOkClickListener mOnOkClickListener;

    public void setOnOkClickListener(OnOkClickListener mOnOkClickListener) {
        this.mOnOkClickListener = mOnOkClickListener;
    }




    public interface OnOkExchangeClickListener {
        void onOkExchangeClick(String etAccounts, String etPasswords);
    }

    private static OnOkExchangeClickListener mOnOkExchangeClickListener;

    public void setOnOkExchangeClickListener(OnOkExchangeClickListener mOnOkExchangeClickListener) {
        this.mOnOkExchangeClickListener = mOnOkExchangeClickListener;
    }


    public interface OnOkExchangeCouponsClickListener {
        void onOkExchangeCouponsClick(String etAccounts);
    }

    private static OnOkExchangeCouponsClickListener mOnOkExchangeCouponsClickListener;

    public void setOnOkExchangeCouponsClickListener(OnOkExchangeCouponsClickListener mOnOkExchangeCouponsClickListener) {
        this.mOnOkExchangeCouponsClickListener = mOnOkExchangeCouponsClickListener;
    }






    public interface OnSubmitClickListener {
        void onSubmitClick(String feedback, String str);
    }

    private static OnSubmitClickListener mOnSubmitClickListener;

    public void setOnSubmitClickListener(OnSubmitClickListener mOnSubmitClickListener) {
        this.mOnSubmitClickListener = mOnSubmitClickListener;
    }



}
