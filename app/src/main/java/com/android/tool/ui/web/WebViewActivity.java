//package com.android.tool.ui.web;
//
//import android.Manifest;
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.os.Handler;
//import android.os.Message;
//import android.support.annotation.NonNull;
//import android.support.v4.content.ContextCompat;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.webkit.JavascriptInterface;
//import android.webkit.WebChromeClient;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//import com.android.tool.R;
//import com.android.tool.ui.base.BaseActivitys;
//import com.android.tool.util.IntentUtils;
//import com.android.tool.util.L;
//import com.android.tool.util.StringUtil;
//import com.android.tool.util.T;
//import com.android.tool.util.permissions.PermissionListener;
//import com.android.tool.util.permissions.PermissionsUtil;
//import com.android.tool.widget.dialog.ActionSheetDialog;
//
//import java.io.UnsupportedEncodingException;
//
//import butterknife.BindView;
//import butterknife.OnClick;
//import com.android.tool.ui.web.bean.WebShareBean;
//import com.android.tool.ui.web.util.AndroidBug5497WorkaroundUtils;
//
//import static com.shanxiang.online.sxclass.widget.dialog.ActionSheetDialog.SheetItemColor.Red;
//
//
///**
// * class ：webview
// * author：York(wuchunyuan)
// * time  : 2017/12/5 11:23
// */
//public class WebViewActivity extends BaseActivitys {
//    private final static String TAG = "WebViewActivity";
//    protected AgentWeb mAgentWeb;
//    @BindView(R.id.txt_title)
//    TextView txtTitle;
//    @BindView(R.id.img_right)
//    ImageView imgRight;
//    @BindView(R.id.iv_go_close)
//    ImageView ivGoClose;
//    @BindView(R.id.v_lines)
//    View vLines;
//    @BindView(R.id.container)
//    LinearLayout mLinearLayout;
//    private WebShareBean webShareBean;
//    public final static String JUMP_VALUE = "jumpValue";
//    public final static String TITLE = "title";
//    public final static String TYPE = "type";
//    private String title, url, type;
//    private WebView webView;
//
//    /**
//     * 跳转web页面
//     */
//    public static void startWebViewActivity(Activity mActivity, String url, String title, String type) {
//        Intent mIntent = new Intent(mActivity, WebViewActivity.class);
//        if (!StringUtil.isNotBlankAndEmpty(title)) {
//            title = "山香教育";
//        }
//        if (!StringUtil.isNotBlankAndEmpty(type)) {
//            type = "2";
//        }
//        try {
//            //url：后台网址编码    java.net.URLDecoder.decode(url, "utf-8")解码
//            mIntent.putExtra(JUMP_VALUE, java.net.URLDecoder.decode(url, "utf-8"));
//            L.i("解码------>"+java.net.URLDecoder.decode(url, "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        mIntent.putExtra(TITLE, title);
//        mIntent.putExtra(TYPE, type);
//        mActivity.startActivity(mIntent);
//    }
//
//    /**
//     * 跳转web页面
//     */
//    public static void startWebViewActivity(Context mContext, String url, String title, String type) {
//        Intent mIntent = new Intent(mContext, WebViewActivity.class);
//        if (!StringUtil.isNotBlankAndEmpty(title)) {
//            title = "山香教育";
//        }
//        if (!StringUtil.isNotBlankAndEmpty(type)) {
//            type = "2";
//        }
//        try {
//            //url：后台网址编码    java.net.URLDecoder.decode(url, "utf-8")解码
//            mIntent.putExtra(JUMP_VALUE, java.net.URLDecoder.decode(url, "utf-8"));
//            L.i("解码------>"+java.net.URLDecoder.decode(url, "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        mIntent.putExtra(TITLE, title);
//        mIntent.putExtra(TYPE, type);
//        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        mContext.startActivity(mIntent);
//    }
//
//    @Override
//    public void initParms(Bundle mBundle) {
//        steepSetStatusBarTranslucent(true, true);
//        title = getIntent().getStringExtra(TITLE);
//        url = getIntent().getStringExtra(JUMP_VALUE);
//        type = getIntent().getStringExtra(TYPE);
//        L.i("webUrl-----" + url);
//
//    }
//
//    @Override
//    protected void onNewIntent(Intent intent) {
//        super.onNewIntent(intent);
////        setIntent(intent);
//        finish();
//        Intent mIntent = new Intent(this, WebViewActivity.class);
//        title = intent.getStringExtra(TITLE);
//        url = intent.getStringExtra(JUMP_VALUE);
//        type = intent.getStringExtra(TYPE);
//        if (!StringUtil.isNotBlankAndEmpty(title)) {
//            title = "山香教育";
//        }
//        if (!StringUtil.isNotBlankAndEmpty(type)) {
//            type = "2";
//        }
//        try {
//            //url：后台网址编码    java.net.URLDecoder.decode(url, "utf-8")解码
//            mIntent.putExtra(JUMP_VALUE, java.net.URLDecoder.decode(url, "utf-8"));
//            L.i("解码1------>"+java.net.URLDecoder.decode(url, "utf-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        mIntent.putExtra(TITLE, title);
//        mIntent.putExtra(TYPE, type);
//        mIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        startActivity(mIntent);
//    }
//
//    @Override
//    public int bindLayout() {
//        return R.layout.activity_web;
//    }
//
//    @Override
//    public void initView() {
//        AndroidBug5497WorkaroundUtils.assistActivity(this);
//        txtTitle.setText(title);
//        try {
//            if (type.equals("1")) {
//                ivGoClose.setVisibility(View.INVISIBLE);
//                vLines.setVisibility(View.INVISIBLE);
//            } else {
//                ivGoClose.setVisibility(View.VISIBLE);
//                vLines.setVisibility(View.VISIBLE);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        imgRight.setImageResource(R.mipmap.share_icon3x);
//        mAgentWeb = AgentWeb.with(this)
//                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator()
//                .setIndicatorColorWithHeight(ContextCompat.getColor(mActivity, R.color.c_ff26), 2)
//                .setReceivedTitleCallback(mCallback)
//                .addJavascriptInterface("androidShare", new JsToJava())
//                .setWebChromeClient(mWebChromeClient)
//                .setWebViewClient(mWebViewClient)
//                .setSecurityType(AgentWeb.SecurityType.strict)
//                .setWebLayout(new WebLayout(this))
//                .createAgentWeb()
//                .go(url);
//        webView = mAgentWeb.getWebCreator().get();
//        webView.setLayerType(View.LAYER_TYPE_NONE, null);
//        // 长按点击事件
//        webView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                final WebView.HitTestResult hitTestResult = webView.getHitTestResult();
//                // 如果是图片类型或者是带有图片链接的类型
//                if (hitTestResult.getType() == WebView.HitTestResult.IMAGE_TYPE || hitTestResult.getType() == WebView.HitTestResult.SRC_IMAGE_ANCHOR_TYPE) {
//                    new ActionSheetDialog(mActivity).builder()
//                            .addSheetItem("保存图片", Red, new ActionSheetDialog.OnSheetItemClickListener() {
//                                @Override
//                                public void onClick(int which) {
//                                    picUrl = hitTestResult.getExtra();//获取图片链接
//                                    L.i("获取图片链接picUrl----" + picUrl);
//                                    //保存图片到相册
//                                    PermissionsUtil.requestPermission(mActivity, new PermissionListener() {
//                                        @Override
//                                        public void permissionGranted(@NonNull String[] permissions) {
//                                            getDialogLoading("正在保存图片");
//                                            AlbumManager.download(picUrl, new DownloadCallback() {
//                                                public void onDownloadCompleted(boolean downloadResult) {
//                                                    dismissDialogLoading();
//                                                    T.customToastCenterShort(mActivity, downloadResult ? "图片保存成功" : "图片保存失败");
//                                                }
//                                            });
//                                        }
//
//                                        @Override
//                                        public void permissionDenied(@NonNull String[] permissions) {
//                                            T.customToastCenterShort(mActivity, "用户拒绝了文件读写权限");
//                                        }
//                                    }, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
//                                }
//                            }).show();
//                    return true;
//                }
//                return false;//保持长按可以复制文字
//            }
//        });
////        mAgentWeb.getLoader().loadUrl(url);
////        addBGChild((FrameLayout) mAgentWeb.getWebCreator().getGroup()); // 得到 AgentWeb 最底层的控件
//    }
//
//    private String picUrl;
//
//    @Override
//    public void initListener() {
//
//    }
//
//    @Override
//    public void doBusiness() {
//    }
//
//    private class JsToJava {
//        //这里需要加@JavascriptInterface，
//        // 4.2之后提供给javascript调用的函数必须带有@JavascriptInterface
//        @JavascriptInterface
//        public void jsMethod(final String paramFromJS) {
//            new Thread(new Runnable() {
//                @Override
//                public void run() {
//                    handler.sendMessage(handler.obtainMessage(1, paramFromJS));
//                }
//            }).start();
//        }
//    }
//
//    @SuppressLint("HandlerLeak")
//    Handler handler = new Handler() {
//        @Override
//        public void handleMessage(Message msg) {
//            super.handleMessage(msg);
//            String paramFromJS = (String) msg.obj;
//            if (StringUtil.isNotBlankAndEmpty(paramFromJS)) {
//                imgRight.setVisibility(View.VISIBLE);
//                webShareBean = WebLayout.getWebShare(paramFromJS);
//                L.i("js返回结果------:" + paramFromJS);
//            }
//        }
//    };
//
//    private WebViewClient mWebViewClient = new WebViewClient() {
//        @Override
//        public void onPageFinished(WebView view, String url) {
//            super.onPageFinished(view, url);
//            imgRight.setVisibility(View.INVISIBLE);
//            //网页加载完之后，java调用js方法setLayerType
//            view.loadUrl("javascript:shareToFriends()");
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, String url) {
//            L.i("url-------------" + url);
//            WebURLUtil.openUrl(url, mActivity, view);
//            return true;
//        }
//
//        @Override
//        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
//            return super.shouldOverrideUrlLoading(view, request);
//        }
//
//        @Override
//        public void onPageStarted(WebView view, String url, Bitmap favicon) {
//        }
//    };
//
//    private WebChromeClient mWebChromeClient = new WebChromeClient() {
//        @Override
//        public void onProgressChanged(WebView view, int newProgress) {
//        }
//    };
//
//    private ChromeClientCallbackManager.ReceivedTitleCallback mCallback = new ChromeClientCallbackManager.ReceivedTitleCallback() {
//        @Override
//        public void onReceivedTitle(WebView view, String title) {
////            if (txtTitle != null)
////                txtTitle.setText(getWTitle());
//        }
//    };
//
//    protected void addBGChild(FrameLayout frameLayout) {
//        TextView mTextView = new TextView(frameLayout.getContext());
//        mTextView.setText("园丁教师");
//        mTextView.setTextSize(16);
//        mTextView.setTextColor(Color.parseColor("#727779"));
//        frameLayout.setBackgroundColor(Color.parseColor("#272b2d"));
//        FrameLayout.LayoutParams mFlp = new FrameLayout.LayoutParams(-2, -2);
//        mFlp.gravity = Gravity.CENTER_HORIZONTAL;
//        final float scale = frameLayout.getContext().getResources().getDisplayMetrics().density;
//        mFlp.topMargin = (int) (15 * scale + 0.5f);
//        frameLayout.addView(mTextView, 0, mFlp);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        Log.i("Info", "result:" + requestCode + " result:" + resultCode);
//        mAgentWeb.uploadFileResult(requestCode, resultCode, data);
//        super.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @OnClick({R.id.img_back, R.id.img_right, R.id.iv_go_close})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.img_back:
//                onBackPressed();
//                break;
//            case R.id.iv_go_close:
//                IntentUtils.isCurrentMainActivity(mActivity);
//                break;
//            case R.id.img_right:
//                if (webShareBean != null) {
//                    ShareDialogFragment shareDialog = ShareDialogFragment.newInstance(mActivity,
//                            ShareBean.getWebShareBeanInstance(mActivity, webShareBean));
//                    shareDialog.show(getSupportFragmentManager(), TAG);
//                }
//                break;
//        }
//    }
//
//
//    @Override
//    protected void onResume() {
//        mAgentWeb.getWebLifeCycle().onResume();
//        super.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        mAgentWeb.getWebLifeCycle().onPause();
//        super.onPause();
//    }
//
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        mAgentWeb.getWebLifeCycle().onDestroy();
//    }
//
//    @Override
//    public void onBackPressed() {
//        if (mAgentWeb.back()) {
//            return;
//        }
//        IntentUtils.isCurrentMainActivity(mActivity);
//    }
//}
