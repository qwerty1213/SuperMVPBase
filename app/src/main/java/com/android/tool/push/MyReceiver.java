package com.android.tool.push;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Base64;

import com.android.tool.model.NotificationBean;
import com.android.tool.ui.web.WebURLUtil;
import com.android.tool.ui.web.WebViewActivity;
import com.android.tool.util.IntentUtils;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.L;
import com.android.tool.util.StringUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import cn.jpush.android.api.JPushInterface;


/**
 * 自定义接收器
 * <p>
 * 如果不定义这个 Receiver，则：
 * 1) 默认用户会打开主界面
 * 2) 接收不到自定义消息
 */
public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
        String json = null;
        try {
            json = printBundle(bundle);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (JPushInterface.ACTION_REGISTRATION_ID.equals(intent.getAction())) {
            String regId = bundle.getString(JPushInterface.EXTRA_REGISTRATION_ID);
            L.i("[MyReceiver] 接收Registration Id : " + regId);
        } else if (JPushInterface.ACTION_MESSAGE_RECEIVED.equals(intent.getAction())) {
            L.i("[MyReceiver] 接收到推送下来的自定义消息: " + bundle.getString(JPushInterface.EXTRA_MESSAGE));
        } else if (JPushInterface.ACTION_NOTIFICATION_RECEIVED.equals(intent.getAction())) {
            try {
                EventBus.getDefault().post(KeyUtil.JPUSH_RECEIVER);
            } catch (Exception e) {
                e.printStackTrace();
            }
            L.i("[MyReceiver] 接收到推送下来的通知");
        } else if (JPushInterface.ACTION_NOTIFICATION_OPENED.equals(intent.getAction())) {
            L.i("[MyReceiver] 用户点击打开了通知");
            if (StringUtil.isNotBlankAndEmpty(json)) {
                L.i("json.toString()-------" + json);
                NotificationBean mBean = getNotification(json);
                if (mBean != null) {
                    if (mBean.getObjType().equals("0")) {//0不跳转
                        return;
                    } else if (mBean.getObjType().equals("1")) {//1协议地址 objValue 对应的是网址
                        String replaceStr = mBean.getObjValue().replace(" ", "+");
                        try {
                            //Base64解密处理
                            WebURLUtil.openSXUrl(new String(Base64.decode(replaceStr, Base64.DEFAULT)), (Activity) context);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else if (mBean.getObjType().equals("2")) {//2网址 objValue 对应的是网址
                        L.i("网址-------" + mBean.getObjValue());
                        WebViewActivity.startWebViewActivity((Activity) context, mBean.getObjValue(),
                                "", "");
                    } else if (mBean.getObjType().equals("3")) {//3点播详情 objValue 对应的是id
                        L.i("点播详情id-------" + mBean.getObjValue());
//                        IntentUtils.startOndemandDetails(context, mBean.getObjValue(), "0");
                    } else if (mBean.getObjType().equals("4")) {//4直播详情 objValue 对应的是id
                        L.i("直播详情id-------" + mBean.getObjValue());
                        IntentUtils.startLiveDetails((Activity) context, mBean.getObjValue(), "0");
                    } else if (mBean.getObjType().equals("5")) {//5图书详情 objValue 对应的是id
                        L.i("图书详情id-------" + mBean.getObjValue());
//                        IntentUtils.startBigPackageDetails(context, mBean.getObjValue());
                    } else if (mBean.getObjType().equals("6")) {//6大礼包详情 objValue 对应的是id
                        L.i("大礼包详情id-------" + mBean.getObjValue());
//                        IntentUtils.startBigPackageDetails(context, mBean.getObjValue());
                    }
                }
            }
        } else if (JPushInterface.ACTION_RICHPUSH_CALLBACK.equals(intent.getAction())) {
            L.i("[MyReceiver] 用户收到到RICH PUSH CALLBACK: " + bundle.getString(JPushInterface.EXTRA_EXTRA));
            //在这里根据 JPushInterface.EXTRA_EXTRA 的内容处理代码，比如打开新的Activity， 打开一个网页等..
        } else if (JPushInterface.ACTION_CONNECTION_CHANGE.equals(intent.getAction())) {
            boolean connected = intent.getBooleanExtra(JPushInterface.EXTRA_CONNECTION_CHANGE, false);
            L.i("[MyReceiver]" + intent.getAction() + " connected state change to " + connected);
        } else {
            L.i("[MyReceiver] Unhandled intent - " + intent.getAction());
        }
    }

    // 打印所有的 intent extra 数据
    private static String printBundle(Bundle bundle) {
        JSONObject json = null;
        for (String key : bundle.keySet()) {
            if (key.toString().equals(JPushInterface.EXTRA_EXTRA.toString())) {
                if (TextUtils.isEmpty(bundle.getString(JPushInterface.EXTRA_EXTRA))) {
                    return "";
                }
                try {
                    json = new JSONObject(bundle.getString(JPushInterface.EXTRA_EXTRA));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        if (!StringUtil.isNotBlankAndEmpty(json.toString())) {
            return "";
        }
        return json.toString();
    }

    public NotificationBean getNotification(String jsonStr) {
        return parserNotification(jsonStr);
    }


    public NotificationBean parserNotification(String jsonStr) {
        Gson gson = new Gson();
        java.lang.reflect.Type type = new TypeToken<NotificationBean>() {
        }.getType();
        return gson.fromJson(jsonStr, type);
    }

//    //send msg to MainActivity
//	private void processCustomMessage(Context context, Bundle bundle) {
//		if (MainActivity.isForeground) {
//			String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
//			String extras = bundle.getString(JPushInterface.EXTRA_EXTRA);
//			Intent msgIntent = new Intent(MainActivity.MESSAGE_RECEIVED_ACTION);
//			msgIntent.putExtra(MainActivity.KEY_MESSAGE, message);
//			if (!ExampleUtil.isEmpty(extras)) {
//				try {
//					JSONObject extraJson = new JSONObject(extras);
//					if (extraJson.length() > 0) {
//						msgIntent.putExtra(MainActivity.KEY_EXTRAS, extras);
//					}
//				} catch (JSONException e) {
//
//				}
//
//			}
//			context.sendBroadcast(msgIntent);
//		}
//	}
}
