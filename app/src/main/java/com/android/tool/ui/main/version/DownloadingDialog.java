package com.android.tool.ui.main.version;


import android.app.Activity;
import android.app.ProgressDialog;

import com.android.tool.R;
import com.android.tool.model.VersionBean;


/**
 * class ：下载进度弹窗
 * author：York(wuchunyuan)
 * time  : 2018/4/24 11:20
 */
public class DownloadingDialog {
    private Activity mActivity;
    private final int MAX_PROGRESS = 100;
    private ProgressDialog progressDialog;

    public DownloadingDialog(Activity mActivity) {
        progressDialog = new ProgressDialog(mActivity);
        this.mActivity = mActivity;
    }

    public void progressDialog(VersionBean versionBean) {
        if (versionBean.getIsForced().equals("1")) {//强制更新
            progressDialog.setCancelable(false);
            progressDialog.setCanceledOnTouchOutside(false);
        } else {
            progressDialog.setCancelable(true);
            progressDialog.setCanceledOnTouchOutside(false);
        }
        progressDialog.setProgress(0);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        progressDialog.setProgressDrawable(mActivity.getResources().getDrawable(R.drawable.version_downloading_style));
        progressDialog.setMax(MAX_PROGRESS);
    }

    public void show() {
        if (progressDialog != null) {
            progressDialog.show();
        }
    }

    public void setProgressInt(int progress) {
        if (progressDialog != null) {
            progressDialog.setProgress(progress);
        }
    }

    public void dismiss() {
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

}
