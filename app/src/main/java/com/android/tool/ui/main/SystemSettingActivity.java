package com.android.tool.ui.main;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.VersionBean;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.login.FindPasswordActivity;
import com.android.tool.ui.main.version.DownloadingDialog;
import com.android.tool.ui.main.version.VersionDialog;
import com.android.tool.ui.main.version.VersionUtil;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.L;
import com.android.tool.util.PUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.glide.GlideCatchUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.widget.dialog.AlertDialogUtil;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;


import java.io.File;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：系统设置
 * author：York(wuchunyuan)
 * time  : 2018/4/3 09:56
 */
public class SystemSettingActivity extends BaseActivitys implements Runnable {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_logout)
    TextView txtLogout;
    @BindView(R.id.txt_cache_size)
    TextView txtCacheSize;
    @BindView(R.id.txt_version)
    TextView txtVersion;
    @BindView(R.id.ll_clear_cache)
    LinearLayout llClearCache;
    @BindView(R.id.ll_change_password)
    LinearLayout llChangePassword;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_system_setting;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (PUtil.isTokenNull()) {
            txtLogout.setVisibility(View.VISIBLE);
            llChangePassword.setVisibility(View.VISIBLE);
        } else {
            txtLogout.setVisibility(View.GONE);
            llChangePassword.setVisibility(View.GONE);
        }
    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {
        txtTitle.setText(R.string.text_my_settings);
        txtCacheSize.setText(GlideCatchUtil.getInstance().getCacheSize());
        txtVersion.setText("V" + VersionUtil.getVerName(mActivity));
    }

    @OnClick({R.id.img_back, R.id.ll_clear_cache, R.id.ll_update_app, R.id.txt_logout, R.id.ll_change_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.ll_clear_cache:
                GlideCatchUtil.getInstance().clearall();
                getDialogLoading();
                mHandler.postDelayed(this, 1000);
                break;
            case R.id.ll_update_app://更新App版本
                updateVersion();
                break;
            case R.id.ll_change_password:
                if (IntentUtils.isLogin(mActivity, new Bundle())) {
                    Bundle bundle = new Bundle();
                    bundle.putBoolean(KeyUtil.IS_FIND_CHANGE_PASSWORD, true);
                    IntentUtils.startIntent(mActivity, FindPasswordActivity.class, bundle);
                }
                break;
            case R.id.txt_logout:
                new AlertDialogUtil(mActivity).builder().setTitle("提示").setMsg("是否退出当前账号?")
                        .setPositiveButton("是", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                PUtil.clearPreferences();
                                onBackPressed();
                            }
                        }).setNegativeButton("否", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                }).show();
                break;
        }
    }

    /**
     * 新版本信息弹窗
     */
    private void updateVersion() {
        OkGo.<ObjectResponse<VersionBean>>get(PathUtil.getVersionData()).tag(this)
                .params(AppConfig.Version.CURENT_VERSION_CODE, VersionUtil.getVersion(mActivity))
                .execute(new ObjectCallback<ObjectResponse<VersionBean>>(this, "") {
                    @Override
                    public void onSuccess(Response<ObjectResponse<VersionBean>> response) {
                        final VersionBean versionBean = response.body().data;
//                        versionBean.setIsForced("0");
                        if (versionBean != null) {
                            if (versionBean.getResult().equals("0")) {//没有新版本
                                showToast(R.string.text_new_version);
                            } else {
                                final VersionDialog mVersionDialog = new VersionDialog(mActivity);
                                mVersionDialog.show(versionBean, new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        downloadingProgressDialog(versionBean);
                                        mVersionDialog.dismiss();
                                    }
                                });
                            }
                        }
                    }
                });
    }

    /**
     * 下载进度
     *
     * @param versionBean
     */
    private void downloadingProgressDialog(VersionBean versionBean) {
        //下载进度弹窗
        final DownloadingDialog downloadingDialog = new DownloadingDialog(mActivity);
        downloadingDialog.progressDialog(versionBean);
        OkGo.<File>get(versionBean.getUrl()).tag(this)
                .execute(new FileCallback(PathUtil.Path.DOWNLOADING_APK_URL, VersionUtil.APK_NAME) {
                    @Override
                    public void onStart(Request<File, ? extends Request> request) {
                        downloadingDialog.show();
                    }

                    @Override
                    public void onSuccess(Response<File> response) {
                        File file = response.body();
                        VersionUtil.installApk(mActivity, file);
                        downloadingDialog.dismiss();
                    }

                    @Override
                    public void onError(Response<File> response) {
                        downloadingDialog.dismiss();
                    }

                    @Override
                    public void downloadProgress(Progress progress) {
                        downloadingDialog.setProgressInt((int) (progress.fraction * 100));
                        L.i("downloadProgress--------" + (int) (progress.fraction * 100));
                    }
                });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkGo.getInstance().cancelTag(this);
    }


    @Override
    public void run() {
        mHandler.sendEmptyMessage(0);
    }

    @SuppressLint("HandlerLeak")
    Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            txtCacheSize.setText(GlideCatchUtil.getInstance().getCacheSize());
            dismissDialogLoading();
            llClearCache.setClickable(false);
        }
    };
}
