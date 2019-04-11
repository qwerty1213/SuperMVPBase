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
import android.app.Dialog;
import android.os.Looper;
import android.os.Message;

import com.android.tool.ui.base.BaseHandler;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONObject;

public abstract class StringDialogCallback extends StringCallback {
    private BaseHandler mainHandler;

    private Activity mActivity;
    private String message;
    private Dialog mDialogLoading;//加载提示框

    public StringDialogCallback(Activity mActivity, String message) {
        this.mActivity = mActivity;
        this.message = message;
    }

    public StringDialogCallback(Activity mActivity) {
        this.mActivity = mActivity;
        this.message = message;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        HttpPublicParametersUtil.httpRequestParameters(mActivity, request);
        getDialogLoading(mActivity);
    }

    @Override
    public void onFinish() {
        dismissDialogLoading();
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
        try {
            dismissDialogLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 这里的数据解析是根据 http://gank.io/api/data/Android/10/1 返回的数据来写的
     * 实际使用中,自己服务器返回的数据格式和上面网站肯定不一样,所以以下是参考代码,根据实际情况自己改写
     */
    @Override
    public String convertResponse(okhttp3.Response response) throws Throwable {
        String responseStr = new StringConvert().convertResponse(response);
        JSONObject jsonObject = new JSONObject(responseStr);
        String success = jsonObject.getString("success");
        String code = jsonObject.getString("code");
        String message = jsonObject.getString("message");
        if (code.equals("0")) {
            if (success.equals("1")) {
                return responseStr;
            } else {
                handler(mActivity).sendMessage(handler(mActivity).obtainMessage(HttpPublicParametersUtil.TOAST_MESSAGE, message));
                throw new IllegalStateException("success==0");
            }
        } else if (code.equals("-0")) {
            handler(mActivity).sendMessage(handler(mActivity).obtainMessage(HttpPublicParametersUtil.TOAST_MESSAGE, message));
            throw new IllegalStateException("code==-0");
        } else if (code.equals("40000")) {
            handler(mActivity).sendEmptyMessage(HttpPublicParametersUtil.IS_LOGIN);
            handler(mActivity).sendMessage(handler(mActivity).obtainMessage(HttpPublicParametersUtil.TOAST_MESSAGE, message));
            throw new IllegalStateException("code==40000");
        } else {
            handler(mActivity).sendMessage(handler(mActivity).obtainMessage(HttpPublicParametersUtil.TOAST_MESSAGE, message));
            response.close();
            //直接将服务端的错误信息抛出，onError中可以获取
            throw new IllegalStateException("错误代码：" + code + ", 错误信息：" + message);
        }
    }

    /**
     * 获取通用句柄，自动释放
     */
    public BaseHandler handler(final Activity activity) {
        if (null == mainHandler) {
            mainHandler = new BaseHandler(new BaseHandler.CallBack() {
                @Override
                public void handleMessage(Message msg) {
                    switch (msg.what) {
                        case HttpPublicParametersUtil.TOAST_MESSAGE:
//                            T.customToastShort(activity, String.valueOf(msg.obj));
                            break;
                        case HttpPublicParametersUtil.IS_LOGIN:
//                            PUtil.clearPreferences();
//                            IntentUtils.startLoginActivity(mActivity, new Bundle());
                            break;
                        default:
                            break;
                    }
                }
            }, Looper.getMainLooper());
        }
        return mainHandler;
    }

    /**
     * [dialog弹窗]
     */
    protected Dialog getDialogLoading(Activity activity) {
//        mDialogLoading = DialogUtil.createLoadingDialog(activity, "加载中...");
        return mDialogLoading;
    }

    /**
     * [关闭dialog]
     */
    protected void dismissDialogLoading() {
        if (mDialogLoading != null && mDialogLoading.isShowing()) {
            mDialogLoading.dismiss();
        }
    }
}
