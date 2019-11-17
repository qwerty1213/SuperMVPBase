package com.android.tool.widget.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
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
import com.android.tool.model.ErrorCorrectionBean;
import com.android.tool.ui.main.adapter.ErrorCorrectionAdapter;
import com.android.tool.util.GUtils;
import com.android.tool.util.StringUtil;
import com.android.tool.util.T;
import com.android.tool.widget.ButtonState;
import com.android.tool.widget.wheelview.ClearEditText;
import com.chad.library.adapter.base.BaseQuickAdapter;

import com.zhy.autolayout.AutoRelativeLayout;

import java.util.ArrayList;
import java.util.List;

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


    /**
     * 学习金兑换
     *
     * @param mContext
     * @return
     */
    public static Dialog showExchangeRecordDialog(int imageBg, final Activity mContext) {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mDialog = new Dialog(mContext, R.style.AlertDialogStyle);
        View layout = inflater.inflate(R.layout.dialog_exchage_record_layout, null);
        AutoRelativeLayout rlLayout = layout.findViewById(R.id.lLayout_bg);
        ImageView ivDissmiss = layout.findViewById(R.id.iv_dissmiss);
        ivDissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        ImageView ivTitle = layout.findViewById(R.id.iv_title);
        ivTitle.setImageResource(imageBg);
        final ClearEditText etAccount = layout.findViewById(R.id.et_account);
        final ClearEditText etPassword = layout.findViewById(R.id.et_password);
        ImageView ivOk = layout.findViewById(R.id.iv_ok);
        ivOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etAccounts = etAccount.getText().toString().trim();
                String etPasswords = etPassword.getText().toString().trim();
                if (!StringUtil.isNotBlankAndEmpty(etAccounts)) {
                    T.customToastShort(mContext, "请输入兑换码");
                    return;
                }
                if (!StringUtil.isNotBlankAndEmpty(etPasswords)) {
                    T.customToastShort(mContext, "请输入兑换密码");
                    return;
                }
                if (mOnOkExchangeClickListener != null) {
                    mOnOkExchangeClickListener.onOkExchangeClick(etAccounts, etPasswords);
                }
            }
        });
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.addContentView(layout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(true);
        mDialog.setContentView(layout);
        rlLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85), RelativeLayout.LayoutParams.WRAP_CONTENT));
        mDialog.show();
        return mDialog;
    }

    public interface OnOkExchangeClickListener {
        void onOkExchangeClick(String etAccounts, String etPasswords);
    }

    private static OnOkExchangeClickListener mOnOkExchangeClickListener;

    public void setOnOkExchangeClickListener(OnOkExchangeClickListener mOnOkExchangeClickListener) {
        this.mOnOkExchangeClickListener = mOnOkExchangeClickListener;
    }

    /**
     * 优惠券兑换
     *
     * @param mContext
     * @return
     */
    public static Dialog showExchangeCouponsDialog(final Activity mContext) {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mDialog = new Dialog(mContext, R.style.AlertDialogStyle);
        View layout = inflater.inflate(R.layout.dialog_exchage_coupons_layout, null);
        AutoRelativeLayout rlLayout = layout.findViewById(R.id.lLayout_bg);
        ImageView ivDissmiss = layout.findViewById(R.id.iv_dissmiss);
        ivDissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
            }
        });
        final ClearEditText etAccount = layout.findViewById(R.id.et_account);
        ImageView ivOk = layout.findViewById(R.id.iv_ok);
        ivOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String etAccounts = etAccount.getText().toString().trim();
                if (!StringUtil.isNotBlankAndEmpty(etAccounts)) {
                    T.customToastShort(mContext, "请输入兑换码");
                    return;
                }
                if (mOnOkExchangeCouponsClickListener != null) {
                    mOnOkExchangeCouponsClickListener.onOkExchangeCouponsClick(etAccounts);
                }
            }
        });
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.addContentView(layout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(true);
        mDialog.setContentView(layout);
        rlLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.85), RelativeLayout.LayoutParams.WRAP_CONTENT));
        mDialog.show();
        return mDialog;
    }

    public interface OnOkExchangeCouponsClickListener {
        void onOkExchangeCouponsClick(String etAccounts);
    }

    private static OnOkExchangeCouponsClickListener mOnOkExchangeCouponsClickListener;

    public void setOnOkExchangeCouponsClickListener(OnOkExchangeCouponsClickListener mOnOkExchangeCouponsClickListener) {
        this.mOnOkExchangeCouponsClickListener = mOnOkExchangeCouponsClickListener;
    }

    private static List<ErrorCorrectionBean> mErrorCorrectionDefultList = new ArrayList<>();

    /**
     * 纠错弹窗
     *
     * @param mList
     * @param mActivity
     * @return
     */
    public static Dialog showErrorCorrectionDialog(final List<ErrorCorrectionBean> mList, final Activity mActivity) {
        WindowManager windowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        mDialog = new Dialog(mActivity, R.style.ActionSheetDialogStyle);
        View layout = inflater.inflate(R.layout.dialog_error_correction_layout, null);
        RelativeLayout rlLayout = layout.findViewById(R.id.rl_layout);
        RecyclerView recyclerView = layout.findViewById(R.id.recyclerView);
        ImageView imgClose = layout.findViewById(R.id.img_close);
        final TextView txtAllSelect = layout.findViewById(R.id.txt_all_select);
        final TextView etFeedback = layout.findViewById(R.id.et_feedback);
        ButtonState btnSubmit = layout.findViewById(R.id.btn_submit);
        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        final ErrorCorrectionAdapter mAdapter = new ErrorCorrectionAdapter(R.layout.error_correction_item, mErrorCorrectionDefultList);
        mAdapter.openLoadAnimation(BaseQuickAdapter.ALPHAIN);
        recyclerView.setAdapter(mAdapter);
        mErrorCorrectionDefultList.clear();
        mAdapter.addData(mList);
        mAdapter.setOnItemClickListener(new ErrorCorrectionAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ErrorCorrectionBean bean, int position) {
                if (mList != null && mList.size() > 0) {
                    if (mList.get(position).isChecked()) {
                        mList.get(position).setChecked(false);
                    } else {
                        mList.get(position).setChecked(true);
                    }
                    mAdapter.notifyItemChanged(position);
                }
            }
        });
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        txtAllSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mList != null && mList.size() > 0) {
                    if (txtAllSelect.getText().toString().equals("全选")) {
                        for (int i = 0; i < mList.size(); i++) {
                            mList.get(i).setChecked(true);
                        }
                        mAdapter.notifyDataSetChanged();
                        txtAllSelect.setText("取消");
                    } else {
                        for (int i = 0; i < mList.size(); i++) {
                            mList.get(i).setChecked(false);
                        }
                        mAdapter.notifyDataSetChanged();
                        txtAllSelect.setText("全选");
                    }
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isSelecte = false;
                if (mOnSubmitClickListener != null) {
                    String str = "";
                    for (int i = 0; i < mList.size(); i++) {
                        if (mList.get(i).isChecked()) {
                            isSelecte = true;
                            str = str + mList.get(i).getErrorName() + ",";
                        }
                    }
                    if (!TextUtils.isEmpty(str)) {//去掉拼接的最后一个字段
                        str = str.substring(0, str.length() - 1);
                    }
                    if (isSelecte) {
                        mOnSubmitClickListener.onSubmitClick(etFeedback.getText().toString(), str);
                        mDialog.dismiss();
                    } else {
                        T.customToastCenterShort(mActivity, "请选择错误类型");
                    }
                }
            }
        });
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.getWindow().setGravity(Gravity.BOTTOM);
        mDialog.addContentView(layout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(true);
        mDialog.setContentView(layout);
        rlLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth()),
                (int) (display.getHeight() * 0.5)));
        mDialog.show();
        return mDialog;
    }

    public interface OnSubmitClickListener {
        void onSubmitClick(String feedback, String str);
    }

    private static OnSubmitClickListener mOnSubmitClickListener;

    public void setOnSubmitClickListener(OnSubmitClickListener mOnSubmitClickListener) {
        this.mOnSubmitClickListener = mOnSubmitClickListener;
    }


    /**
     * 上传文本图片视频
     *
     * @param mContext
     * @return
     */
    public static Dialog showApplyRulesDialog(String title, final Activity mContext) {
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mDialog = new Dialog(mContext, R.style.AlertDialogStyle);
        View layout = inflater.inflate(R.layout.dialog_apply_rules_layout, null);
        LinearLayout rlLayout = layout.findViewById(R.id.lLayout_bg);
        TextView txtTitle = layout.findViewById(R.id.txt_title);
        txtTitle.setText(title);
        ImageView ivDissmiss = layout.findViewById(R.id.iv_dissmiss);
        ivDissmiss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.addContentView(layout, new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setCancelable(true);
        mDialog.setContentView(layout);
        rlLayout.setLayoutParams(new FrameLayout.LayoutParams((int) (display.getWidth() * 0.9), LinearLayout.LayoutParams.WRAP_CONTENT));
        mDialog.show();
        return mDialog;
    }
}
