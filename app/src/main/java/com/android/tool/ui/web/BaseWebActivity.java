//package com.android.tool.ui.web;
//
//import android.content.Intent;
//import android.graphics.Bitmap;
//import android.graphics.Color;
//import android.os.Bundle;
//import android.support.v4.content.ContextCompat;
//import android.util.Log;
//import android.view.Gravity;
//import android.view.View;
//import android.webkit.WebChromeClient;
//import android.webkit.WebResourceRequest;
//import android.webkit.WebView;
//import android.webkit.WebViewClient;
//import android.widget.FrameLayout;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.TextView;
//
//import com.android.tool.R;
//import com.android.tool.ui.base.BaseActivitys;
//import com.android.tool.util.PathUtil;
//import com.android.tool.util.ResultUtil;
//import com.android.tool.utility.AppConfig;
//import com.just.agentweb.AgentWeb;
//import com.just.agentweb.ChromeClientCallbackManager;
//import com.lzy.okgo.OkGo;
//import butterknife.BindView;
//import butterknife.OnClick;
//
//public class BaseWebActivity extends BaseActivitys {
//
//    protected AgentWeb mAgentWeb;
//    @BindView(R.id.txt_title)
//    TextView txtTitle;
//    @BindView(R.id.img_right)
//    ImageView imgRight;
//    @BindView(R.id.container)
//    LinearLayout mLinearLayout;
//
//
//    @Override
//    public void initParms(Bundle mBundle) {
//        steepSetStatusBarTranslucent(true, true);
//
//    }
//
//    @Override
//    public int bindLayout() {
//        return R.layout.activity_web;
//    }
//
//    @Override
//    public void initView() {
//        txtTitle.setText(getWTitle());
//        mAgentWeb = AgentWeb.with(this)
//                .setAgentWebParent(mLinearLayout, new LinearLayout.LayoutParams(-1, -1))
//                .useDefaultIndicator()
////                .defaultProgressBarColor()
//                .setIndicatorColorWithHeight(ContextCompat.getColor(mActivity, R.color.c_ff26), 2)
//                .setReceivedTitleCallback(mCallback)
//                .setWebChromeClient(mWebChromeClient)
//                .setWebViewClient(mWebViewClient)
////                .setSecutityType(AgentWeb.SecurityType.strict)
//                .setWebLayout(new WebLayout(this))
//                .createAgentWeb()
//                .ready()
//                .go(getUrl());
//        mAgentWeb.getLoader().loadUrl(getUrl());
////        addBGChild((FrameLayout) mAgentWeb.getWebCreator().getGroup()); // 得到 AgentWeb 最底层的控件
//    }
//
//    @Override
//    public void initListener() {
//
//    }
//
//    @Override
//    public void doBusiness() {
//        if (getIsCollectionPageZX()) {
//            if (getCollectionListBean() != null) {
//                if (getCollectionListBean().getIsCollection().equals("1")) {
//                    imgRight.setImageResource(R.mipmap.study_zan_true3x);
//                } else {
//                    imgRight.setImageResource(R.mipmap.study_zan_false3x);
//                }
//            }
//        } else {
//            if (getHomeListBean() != null) {
//                if (getHomeListBean().getIsCollection().equals("1")) {
//                    imgRight.setImageResource(R.mipmap.study_zan_true3x);
//                } else {
//                    imgRight.setImageResource(R.mipmap.study_zan_false3x);
//                }
//            }
//        }
//    }
//
//    private WebViewClient mWebViewClient = new WebViewClient() {
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
//    public String getUrl() {
//        return "";
//    }
//
//    public String getWTitle() {
//        return "";
//    }
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
//    @OnClick({R.id.img_back, R.id.img_right, R.id.img_go_back})
//    public void onViewClicked(View view) {
//        switch (view.getId()) {
//            case R.id.img_back:
//                onBackPressed();
//                break;
//            case R.id.img_go_back:
//                finish();
//                break;
//            case R.id.img_right:
//                if (getIsCollectionPageZX()) {
//                    if (getCollectionListBean() == null) {
//                        return;
//                    }
//                    OkGo.<ObjectResponse<CollectionBean>>get(PathUtil.getIsCollection()).tag(this)
//                            .params(AppConfig.IsCollection.OBJ_TYPE, "3")//1招教 2教资 3教育资讯
//                            .params(AppConfig.IsCollection.BOJ_ID, getCollectionListBean().getId())
//                            .params(AppConfig.IsCollection.IS_COLLECTION, getCollectionListBean().getIsCollection())
//                            .execute(new ObjectCallback<ObjectResponse<CollectionBean>>(mActivity, "正在加载...") {
//                                @Override
//                                public void onSuccess(Response<ObjectResponse<CollectionBean>> response) {
//                                    CollectionBean bean = response.body().data;
//                                    getCollectionListBean().setIsCollection(bean.getIsCollection());
//                                    if (getCollectionListBean().getIsCollection().equals("1")) {
//                                        imgRight.setImageResource(R.mipmap.study_zan_true3x);
//                                    } else {
//                                        imgRight.setImageResource(R.mipmap.study_zan_false3x);
//                                    }
//                                }
//                            });
//                } else {
//                    if (getHomeListBean() == null) {
//                        return;
//                    }
//                    OkGo.<ObjectResponse<CollectionBean>>get(PathUtil.getIsCollection()).tag(this)
//                            .params(AppConfig.IsCollection.OBJ_TYPE, "3")//1招教 2教资 3教育资讯
//                            .params(AppConfig.IsCollection.BOJ_ID, getHomeListBean().getId())
//                            .params(AppConfig.IsCollection.IS_COLLECTION, getHomeListBean().getIsCollection())
//                            .execute(new ObjectCallback<ObjectResponse<CollectionBean>>(mActivity, "正在加载...") {
//                                @Override
//                                public void onSuccess(Response<ObjectResponse<CollectionBean>> response) {
//                                    CollectionBean bean = response.body().data;
//                                    getHomeListBean().setIsCollection(bean.getIsCollection());
//                                    if (getHomeListBean().getIsCollection().equals("1")) {
//                                        imgRight.setImageResource(R.mipmap.study_zan_true3x);
//                                    } else {
//                                        imgRight.setImageResource(R.mipmap.study_zan_false3x);
//                                    }
//                                }
//                            });
//                }
//                break;
//        }
//    }
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
////    @Override
////    public boolean onKeyDown(int keyCode, KeyEvent event) {
////        if (mAgentWeb.handleKeyEvent(keyCode, event)) {
////            return true;
////        }
////        return super.onKeyDown(keyCode, event);
////    }
//
//
//    @Override
//    public void onBackPressed() {
//        if (mAgentWeb.back()) {
//            return;
//        }
//        if (getIsCollectionPageZX()) {
//            if (getCollectionListBean() != null) {
//                Intent intent = new Intent();
//                intent.putExtra(WebViewActivity.ISCOLLECTION, getCollectionListBean().getIsCollection());
//                intent.putExtra(WebViewActivity.POSITION_ZX, getPositionZX());
//                setResult(ResultUtil.WEB_RESULT_ZX, intent);
//            }
//        } else {
//            if (getHomeListBean() != null) {
//                Intent intent = new Intent();
//                intent.putExtra(WebViewActivity.ISCOLLECTION, getHomeListBean().getIsCollection());
//                intent.putExtra(WebViewActivity.POSITION_ZX, getPositionZX());
//                setResult(ResultUtil.WEB_RESULT_ZX, intent);
//            }
//        }
//        super.onBackPressed();
//    }
//
//    public int getPositionZX() {
//        return 0;
//    }
//
//    public boolean getIsCollectionPageZX() {
//        return false;
//    }
//
//    public HomePageBean.RowsBean getHomeListBean() {
//        return null;
//    }
//
//    public CollectionListBean.RowsBean getCollectionListBean() {
//        return null;
//    }
//}
