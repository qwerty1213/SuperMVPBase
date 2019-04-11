package com.android.tool.utility;

import com.android.tool.BaseApplication;
import com.android.tool.R;
import com.android.tool.ui.base.BaseView;
import com.google.gson.JsonSyntaxException;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class NetErrorHandler {
    public static String getNetErrorMessage(Throwable throwable) {
        throwable.printStackTrace();
        if (throwable instanceof UnknownHostException) {
            return BaseApplication.getInstance().getResources().getString(R.string.network_error);
        } else if (throwable instanceof SocketTimeoutException) {
            return BaseApplication.getInstance().getResources().getString(R.string.network_timeout);
        } else if (throwable instanceof JsonSyntaxException) {
            return BaseApplication.getInstance().getResources().getString(R.string.json_syntax_error);
        } else {
            return BaseApplication.getInstance().getResources().getString(R.string.unknown_error);
        }
    }

    public static void process(Throwable throwable, BaseView view) {
        view.showError(getNetErrorMessage(throwable));
    }
//
//
//    public static void processLogicError(ResponseBase responseBase) {
//        if (null != responseBase) {
//            switch (responseBase.getErrorCode()) {
//                case -1: // session 过期
//                    if (ElkenApp.getInstance() != null) {
//                        EasySharedPreferenceInstance.saveUserInfo(ElkenApp.getInstance(), null);
//                        ElkenApp.getInstance().startActivity(new Intent(ElkenApp.getInstance(), LoginActivity.class)
//                                .putExtra("loginOnly", true) // just login
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                    }
//                    break;
//            }
//        }
//    }
//
//    public static void processSeachLogicError(ResponseSeachListList response) {
//        if (null != response) {
//            switch (response.getErrorCode()) {
//                case -1: // session 过期
//                    if (ElkenApp.getInstance() != null) {
//                        ElkenApp.getInstance().startActivity(new Intent(ElkenApp.getInstance(), LoginActivity.class)
//                                .putExtra("loginOnly", true) // just login
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                    }
//                    break;
//            }
//        }
//    }
//
//    public static void processBookLogicError(ResponseVisitorAddressBookList response) {
//        if (null != response) {
//            switch (response.getErrorCode()) {
//                case -1: // session 过期
//                    if (ElkenApp.getInstance() != null) {
//                        ElkenApp.getInstance().startActivity(new Intent(ElkenApp.getInstance(), LoginActivity.class)
//                                .putExtra("loginOnly", true) // just login
//                                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
//                    }
//                    break;
//            }
//        }
//    }
}
