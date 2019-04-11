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
 * 没有dialog回调
 *
 * @param <T>
 */
public abstract class ObjectNoDialogCallback<T> extends BaseJsonCallback<T> {
    private Activity mActivity;
    private String msg;

    public ObjectNoDialogCallback(Activity mActivity, String msg) {
        super(mActivity);
        this.mActivity = mActivity;
        this.msg = msg;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
    }

    @Override
    public void onFinish() {
        super.onFinish();
    }


}
