/*
 * Copyright 2016 jeasonlzy(廖子尧)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.android.tool.utility;

import android.app.Activity;

import com.android.tool.ui.base.BaseJsonCallback;
import com.lzy.okgo.request.base.Request;

/**
 * 全屏占位图网络请求回调
 *
 * @param <T>
 */
public abstract class LoadingCallback<T> extends BaseJsonCallback<T> {

//    private LoadingView mLoadingView;
    private Activity mActivity;

    public LoadingCallback(Activity mActivity/*, LoadingView mLoadingView*/) {
        super(mActivity);
//        this.mLoadingView = mLoadingView;
        this.mActivity = mActivity;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
//        loadingFailure(mActivity, mLoadingView);
    }

    @Override
    public void onFinish() {
        super.onFinish();
//        mLoadingView.setVisibility(View.GONE);
    }

//    /**
//     * 加载数据失败
//     *
//     * @param activity
//     * @param mLoadingView
//     */
//    private static void loadingFailure(Activity activity, LoadingView mLoadingView) {
//        mLoadingView.setVisibility(View.VISIBLE);
//        if (!NetUtil.isConnected(activity)) {
//            loadingState(mLoadingView, LoadingState.STATE_NO_NET);
//            return;
//        }
//        loadingState(mLoadingView, LoadingState.STATE_ERROR);
//    }
//
//    public static void loadingState(LoadingView mLoadingView, LoadingState stateEmpty) {
//        mLoadingView.setVisibility(View.VISIBLE);
//        mLoadingView.setState(stateEmpty);
//    }
}
