package com.android.tool.ui.live.fragment;

import android.Manifest;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.text.Html;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.LiveDetailsBean;
import com.android.tool.ui.base.BaseFragments;
import com.android.tool.ui.live.LiveDetailsActivity;
import com.android.tool.ui.web.WebURLUtil;
import com.android.tool.util.L;
import com.android.tool.util.StringUtil;
import com.android.tool.util.T;
import com.android.tool.util.permissions.PermissionListener;
import com.android.tool.util.permissions.PermissionsUtil;
import com.android.tool.widget.album.AlbumManager;
import com.android.tool.widget.album.DownloadCallback;
import com.android.tool.widget.dialog.ActionSheetDialog;
import com.just.agentweb.AgentWeb;


import butterknife.BindView;

import static com.android.tool.widget.dialog.ActionSheetDialog.SheetItemColor.Red;


/**
 * class ：介绍
 * author：York(wuchunyuan)
 * time  : 2018/6/5 09:32
 */
public class IntroduceLiveFragment extends BaseFragments {


    @BindView(R.id.txt_headliness)
    TextView txtHeadline;
    @BindView(R.id.txt_period_validity)
    TextView txtPeriodValidity;
    @BindView(R.id.txt_note)
    TextView txtNote;
    @BindView(R.id.container)
    LinearLayout mLinearLayout;
    protected AgentWeb mAgentWeb;
    private WebView webView;
    private LiveDetailsBean liveBean;

    @Override
    public void initParms(Bundle mBundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_live_introduce;
    }

    @Override
    public void initView() {
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {
        liveBean = LiveDetailsActivity.liveDetailsBean;
        if (liveBean != null) {
            try {
                txtHeadline.setText(liveBean.getName());
                if (StringUtil.isNotBlankAndEmpty(liveBean.getEndTimeStr())) {
                    txtPeriodValidity.setVisibility(View.VISIBLE);
                    txtPeriodValidity.setText(liveBean.getEndTimeStr());
                } else {
                    txtPeriodValidity.setVisibility(View.GONE);
                }
                if (StringUtil.isNotBlankAndEmpty(liveBean.getRemark())) {
                    txtNote.setVisibility(View.VISIBLE);
                    txtNote.setText(Html.fromHtml(liveBean.getRemark()));
                } else {
                    txtNote.setVisibility(View.GONE);
                }
                //设置WebView
                mAgentWeb = AgentWeb.with(mActivity)
                        .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
                        .useDefaultIndicator()
                        .setIndicatorColorWithHeight(ContextCompat.getColor(mActivity, R.color.c_ff26), 2)
                        .setWebViewClient(mWebViewClient)
                        .setSecurityType(AgentWeb.SecurityType.strict)
                        .createAgentWeb()
                        .go(liveBean.getIntroductionUrl());
                webView = mAgentWeb.getWebCreator().get();
                webView.setLayerType(View.LAYER_TYPE_NONE, null);
                OnLong();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private WebViewClient mWebViewClient = new WebViewClient() {
        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            WebURLUtil.openUrl(url, mActivity, view);
            return true;
        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            return super.shouldOverrideUrlLoading(view, request);
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
        }
    };

    private void OnLong() {
        final WebView webView = mAgentWeb.getWebCreator().get();
        // 长按点击事件
        webView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final WebView.HitTestResult hitTestResult = webView.getHitTestResult();
                // 如果是图片类型或者是带有图片链接的类型
                if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE || hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
                    new ActionSheetDialog(mActivity).builder()
                            .addSheetItem("保存图片", Red, new ActionSheetDialog.OnSheetItemClickListener() {
                                @Override
                                public void onClick(int which) {
                                    final String picUrl = hitTestResult.getExtra();//获取图片链接
                                    L.i("获取图片链接picUrl----" + picUrl);
                                    //保存图片到相册
                                    PermissionsUtil.requestPermission(mActivity, new PermissionListener() {
                                        @Override
                                        public void permissionGranted(@NonNull String[] permissions) {
                                            getDialogLoading("正在保存图片");
                                            AlbumManager.download(picUrl, new DownloadCallback() {
                                                public void onDownloadCompleted(boolean downloadResult) {
                                                    dismissDialogLoading();
                                                    T.customToastCenterShort(mActivity, downloadResult ? "图片保存成功" : "图片保存失败");
                                                }
                                            });
                                        }

                                        @Override
                                        public void permissionDenied(@NonNull String[] permissions) {
                                            T.customToastCenterShort(mActivity, "用户拒绝了文件读写权限");
                                        }
                                    }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
                                }
                            }).show();
                    return true;
                }
                return false;//保持长按可以复制文字
            }
        });
    }

}