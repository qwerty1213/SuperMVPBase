package com.android.tool.ui.main.version;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tool.R;


/**
 * class ：网络提示弹窗
 * author：York(wuchunyuan)
 * time  : 2018/4/23 17:51
 */
public class NetWorkDialog {
    private static Dialog mDialog;
    private static Activity mActivity;
    private static Display display;

    public NetWorkDialog(Activity mActivity) {
        WindowManager windowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        this.mActivity = mActivity;
    }

    public static Dialog show(View.OnClickListener updateOnClickListener) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        mDialog = new Dialog(mActivity, R.style.AlertDialogStyle);
        View view = inflater.inflate(R.layout.dialog_network_info, null);
        TextView txtContinue = view.findViewById(R.id.txt_continue);
        txtContinue.setOnClickListener(updateOnClickListener);
        TextView txtCancel = view.findViewById(R.id.txt_cancel);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.addContentView(view, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mDialog.setContentView(view);
        LinearLayout lLayoutBg = view.findViewById(R.id.lLayout_bg);
        lLayoutBg.setLayoutParams(new FrameLayout.LayoutParams((int) (display
                .getWidth() * 0.85), LinearLayout.LayoutParams.WRAP_CONTENT));
        mDialog.show();
        return mDialog;
    }

    public void dismiss() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    /**
     * 设置取消按钮是否显示  dialog是否可以取消
     *
     * @param txtCancel
     * @param invisible
     * @param cancel
     */
    private static void setIsForcedStatus(TextView txtCancel, int invisible, boolean cancel) {
        txtCancel.setVisibility(invisible);
        mDialog.setCanceledOnTouchOutside(cancel);
        mDialog.setCancelable(cancel);
    }
}
