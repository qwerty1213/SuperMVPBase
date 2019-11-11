package com.android.tool.ui.main.version;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tool.BaseApplication;
import com.android.tool.R;
import com.android.tool.model.VersionBean;
import com.android.tool.util.T;


/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/4/23 17:51
 */
public class VersionDialog {
    private static Dialog mDialog;
    private static Activity mActivity;
    private static Display display;

    public VersionDialog(Activity mActivity) {
        WindowManager windowManager = (WindowManager) mActivity.getSystemService(Context.WINDOW_SERVICE);
        display = windowManager.getDefaultDisplay();
        this.mActivity = mActivity;
    }

    @SuppressLint("SetTextI18n")
    public static Dialog show(final VersionBean versionBean, View.OnClickListener updateOnClickListener) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        mDialog = new Dialog(mActivity, R.style.AlertDialogStyle);
        View view = inflater.inflate(R.layout.dialog_version_info, null);
        TextView txtVersion = view.findViewById(R.id.txt_version);
        txtVersion.setText("Version : " + versionBean.getVersionName());
        TextView txtMsg = view.findViewById(R.id.txt_msg);
        txtMsg.setText(versionBean.getContents().replace("\\n", "\n"));
        TextView txtUpdate = view.findViewById(R.id.txt_update);
        txtUpdate.setOnClickListener(updateOnClickListener);
        TextView txtCancel = view.findViewById(R.id.txt_cancel);
        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (versionBean.getIsForced().equals("1")) {//强制更新
                    BaseApplication.getInstance().exit();
                    T.customToastShort(mActivity, R.string.program_has_dropped_out_please_download_latest_version);
                }
                mDialog.dismiss();
            }
        });
        if (versionBean.getIsForced().equals("1")) {//强制更新
            setIsForcedStatus(false);
        } else {
            setIsForcedStatus(true);
        }
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.addContentView(view, new LinearLayout.LayoutParams(android.view.ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT));
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
     * @param cancel
     */
    private static void setIsForcedStatus(boolean cancel) {
        mDialog.setCanceledOnTouchOutside(cancel);
        mDialog.setCancelable(cancel);
    }
}
