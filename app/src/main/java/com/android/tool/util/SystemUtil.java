package com.android.tool.util;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;

/**
 * 系统工具类
 */
public class SystemUtil {


    /**
     * 获取当前手机系统版本号
     *
     * @return 系统版本号
     */
    public static String getSystemVersion() {
        return android.os.Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return 手机型号
     */
    public static String getSystemModel() {
        return android.os.Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return 手机厂商
     */
    public static String getDeviceBrand() {
        return android.os.Build.BRAND;
    }

    public static String getdata(Context context) {
        return "android--手机型号" + getSystemModel() + "--系统版本" + getSystemVersion() + "--app版本" + getlocalVersion(context);
    }

    /**
     * 得到当前软件版本
     */
    public static int getlocalVersion(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
            L.i("--versionCode-->" + versionCode + "");
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // 设置本地版本号
            return versionCode;
        }
    }

//    /**
//     * 拨打电话（跳转到拨号界面，用户手动点击拨打）
//     *
//     * @param phoneNum 电话号码
//     */
//    public static void callPhone(final Activity activity, final String phoneNum) {
//        PermissionsUtil.requestPermission(activity, new PermissionListener() {
//            @Override
//            public void permissionGranted(@NonNull String[] permissions) {
//                Intent intent = new Intent(Intent.ACTION_DIAL);
//                Uri data = Uri.parse("tel:" + phoneNum);
//                intent.setData(data);
//                activity.startActivity(intent);
//            }
//
//            @Override
//            public void permissionDenied(@NonNull String[] permissions) {
//                T.customToastCenterShort(activity, "用户拒绝了拨打电话权限");
//            }
//        }, Manifest.permission.READ_PHONE_STATE);
//    }

    /**
     * 调用系统界面，给指定的号码发送短信
     *
     * @param
     * @param url
     */
    public static void sendSmsWithNumber(Activity activity, String url) {
        Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse(url));
        activity.startActivity(sendIntent);
    }

    /**
     * 发送邮件
     *
     * @param activity
     * @param url
     */
    public static void sendEmail(Activity activity, String url) {
        Intent data = new Intent(Intent.ACTION_SENDTO);
        data.setData(Uri.parse(url));
        activity.startActivity(data);
    }


    /**
     * 跳转到指定QQ临时聊天界面
     *
     * @param activity
     * @param url
     */
    public static void goQq(Activity activity, String url) {
        try {
            activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        } catch (Exception e) {
            // 未安装手Q或安装的版本不支持    showToast("未安装手Q或安装的版本不支持");
//            T.customToastCenterShort(activity, "未安装手Q或安装的版本不支持");
        }
    }

    /**
     * 跳转到指定QQ群临时聊天界面
     *
     * @param activity
     * @param url
     */
    public static void goJoinQqGroup(Activity activity, String url) {
        Intent intent = new Intent();
        intent.setData(Uri.parse(url));
        try {
            activity.startActivity(intent);
        } catch (Exception e) {
//            T.customToastCenterShort(activity, "未安装手Q或安装的版本不支持");
        }
    }

    /**
     * Andorid检测支付宝客户端是否安装
     */
    public static boolean checkAliPayInstalled(Activity activity) {
        Uri uri = Uri.parse("alipays://platformapi/startApp");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        ComponentName componentName = intent.resolveActivity(activity.getPackageManager());
        return componentName != null;
    }

    /**
     * Andorid检测支付宝客户端是否安装
     */
    public static void checkAliPayInstalled(Activity activity, String url) {
        if (checkPackage(activity, "com.taobao.taobao")) {
            Intent intent = new Intent();
            intent.setAction("android.intent.action.VIEW");
            Uri uri = Uri.parse(url);
            intent.setData(uri);
            activity.startActivity(intent);
        } else {
//            WebViewActivity.startWebViewActivity(activity, url.replace("taobao", "http"),
//                    "", "");
        }
    }

    public static boolean checkPackage(Context context, String packageName) {
        if (packageName == null || "".equals(packageName))
            return false;
        try {
            context.getPackageManager().getApplicationInfo(packageName, PackageManager.GET_UNINSTALLED_PACKAGES);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }

    }

    /**
     * 震动
     *
     * @param mActivity
     * @return
     */
    public static Vibrator showVibrator(Activity mActivity) {
        Vibrator vibrator = (Vibrator) mActivity.getSystemService(mActivity.VIBRATOR_SERVICE);
        vibrator.vibrate(200);
        return vibrator;
    }


}
