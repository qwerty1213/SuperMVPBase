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
package com.android.tool.ui.base;

import android.app.Activity;
import android.os.Looper;
import android.os.Message;

import com.android.tool.utility.HttpPublicParametersUtil;
import com.android.tool.utility.model.ObjectResponse;
import com.android.tool.utility.util.Convert;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import okhttp3.Response;


/**
 * ================================================
 * 描    述：默认将返回的数据解析成需要的Bean,可以是 BaseBean，String，List，Map
 * ================================================
 */
public abstract class BaseJsonCallback<T> extends AbsCallback<T> {

    private Type type;
    private Class<T> clazz;
    private Activity mActivity;

    public BaseJsonCallback(Activity mActivity) {
        this.mActivity = mActivity;
    }

    public BaseJsonCallback(Type type) {
        this.type = type;
    }

    public BaseJsonCallback(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        HttpPublicParametersUtil.httpRequestParameters(mActivity, request);
    }

    /**
     * 这里的数据解析是根据 http://gank.io/api/data/Android/10/1 返回的数据来写的
     * 实际使用中,自己服务器返回的数据格式和上面网站肯定不一样,所以以下是参考代码,根据实际情况自己改写
     */
    @Override
    public T convertResponse(Response response) throws Throwable {
        //以下代码是通过泛型解析实际参数,泛型必须传
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        Type type = params[0];
        if (!(type instanceof ParameterizedType)) throw new IllegalStateException("没有填写泛型参数");

        JsonReader jsonReader = new JsonReader(response.body().charStream());
        Type rawType = ((ParameterizedType) type).getRawType();
        if (rawType == ObjectResponse.class) {
            ObjectResponse objectResponse = Convert.fromJson(jsonReader, type);
            String code = objectResponse.code;
            String success = objectResponse.success;
            if (code.equals("0")) {
                if (success.equals("1")) {
                    return (T) objectResponse;
                } else {
                    handler(mActivity).sendMessage(handler(mActivity).obtainMessage(HttpPublicParametersUtil.TOAST_MESSAGE, objectResponse.message));
                    throw new IllegalStateException("success==0");
                }
            } else if (code.equals("-0")) {
                handler(mActivity).sendMessage(handler(mActivity).obtainMessage(HttpPublicParametersUtil.TOAST_MESSAGE, objectResponse.message));
                throw new IllegalStateException("code==-0");
            } else if (code.equals("40000")) {
                handler(mActivity).sendEmptyMessage(HttpPublicParametersUtil.IS_LOGIN);
                handler(mActivity).sendMessage(handler(mActivity).obtainMessage(HttpPublicParametersUtil.TOAST_MESSAGE, objectResponse.message));
                throw new IllegalStateException("code==40000");
            } else {
                handler(mActivity).sendMessage(handler(mActivity).obtainMessage(HttpPublicParametersUtil.TOAST_MESSAGE, objectResponse.message));
                //直接将服务端的错误信息抛出，onError中可以获取
                throw new IllegalStateException("错误代码：" + code + ", 错误信息：" + objectResponse.message);
            }
        } else {
            response.close();
            throw new IllegalStateException("基类错误无法解析!");
        }
    }

    private BaseHandler mainHandler;

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
//                            com.shanxiang.online.sxclass.util.T.customToastShort(activity, String.valueOf(msg.obj));
                            break;
                        case HttpPublicParametersUtil.IS_LOGIN:
//                            P.clearPreferences();
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
}
