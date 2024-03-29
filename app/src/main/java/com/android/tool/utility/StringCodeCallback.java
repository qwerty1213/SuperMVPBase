package com.android.tool.utility;

import android.app.Activity;
import android.os.Bundle;
import android.os.Looper;
import android.os.Message;

import com.android.tool.ui.base.BaseHandler;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.PUtil;
import com.android.tool.util.T;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.convert.StringConvert;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import org.json.JSONObject;

/**
 * 图形验证码回调
 */
public abstract class StringCodeCallback extends StringCallback {
    private BaseHandler mainHandler;

    private Activity mActivity;


    public StringCodeCallback(Activity mActivity) {
        this.mActivity = mActivity;
    }

    @Override
    public void onStart(Request<String, ? extends Request> request) {
        HttpPublicParametersUtil.httpRequestParameters(mActivity, request);
    }

    @Override
    public void onFinish() {
    }

    @Override
    public void onError(Response<String> response) {
        super.onError(response);
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
                            T.customToastCenterShort(activity, String.valueOf(msg.obj));
                            break;
                        case HttpPublicParametersUtil.IS_LOGIN:
                            PUtil.clearPreferences();
                            IntentUtils.startLoginActivity(mActivity, new Bundle());
                            break;
                        default:
                            break;
                    }
                }
            }, Looper.getMainLooper());
        }
        return mainHandler;
    }

    @Override
    public void onSuccess(Response<String> response) {

    }
}
