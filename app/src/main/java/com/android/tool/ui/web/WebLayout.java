package com.android.tool.ui.web;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.webkit.WebView;
import com.android.tool.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.android.tool.ui.web.bean.WebShareBean;
import com.just.agentweb.IWebLayout;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

public class WebLayout implements IWebLayout {

    private Activity mActivity;
    private final TwinklingRefreshLayout mTwinklingRefreshLayout;
    private WebView mWebView = null;

    public WebLayout(Activity activity) {
        this.mActivity = activity;
        mTwinklingRefreshLayout = (TwinklingRefreshLayout) LayoutInflater.from(activity).inflate(R.layout.fragment_twk_web, null);
        mTwinklingRefreshLayout.setPureScrollModeOn();
        mWebView = (WebView) mTwinklingRefreshLayout.findViewById(R.id.webView);
    }

    @NonNull
    @Override
    public ViewGroup getLayout() {
        return mTwinklingRefreshLayout;
    }

    @Nullable
    @Override
    public WebView getWeb() {
        return mWebView;
    }

    public static WebShareBean getWebShare(String jsonStr) {
        return parserWebShare(jsonStr);
    }


    public static WebShareBean parserWebShare(String jsonStr) {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<WebShareBean>() {
        }.getType();
        return gson.fromJson(jsonStr, type);
    }

}
