package com.android.tool.ui.main.version;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.support.v4.content.FileProvider;

import java.io.File;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/4/23 17:29
 */
public class VersionUtil {
    public final static String DELETE_DOWNLOADING_APK_URL = "/sdcard/shanxianglaoshi/apk/";
    public final static String DOWNLOADING_TAG = "downloadingTag";
    public final static String APK_NAME = "shanxianglaoshi.apk";

    /**
     * Don't let anyone instantiate this class.
     */
    private VersionUtil() {
        throw new Error("Do not need instantiate!");
    }

    /**
     * @param file
     * @return
     * @Description 安装apk
     */
    public static void installApk(Activity mActivity, File file) {
        try{
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) { // 7.0+以上版本
                Uri apkUri = FileProvider.getUriForFile(mActivity, mActivity.getPackageName() + ".versionProvider", file);
                //与manifest中定义的provider中的authorities="com.shanxiang.online.sxclass.versionProvider"保持一致
                intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setDataAndType(apkUri, "application/vnd.android.package-archive");
            } else {
                intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
            }
            mActivity.startActivity(intent);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static File getPathFile(String path) {
        String apkName = path.substring(path.lastIndexOf("/"));
        File outputFile = new File(Environment.getExternalStoragePublicDirectory
                (Environment.DIRECTORY_DOWNLOADS), apkName);
        return outputFile;
    }

    public static void rmoveFile(String path) {
        File file = new File(path);
        if (file != null && file.exists()) {
            file.delete();
        }
    }

    /**
     * 得到当前软件版本
     */
    public static int getVersion(Context context) {
        int versionCode = 0;
        try {
            // 获取软件版本号，对应AndroidManifest.xml下android:versionCode
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            versionCode = pi.versionCode;
            return versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            // 设置本地版本号
            return versionCode;
        }
    }

    /**
     * 获取版本号名称
     *
     * @param context 上下文
     * @return
     */
    public static String getVerName(Context context) {
        String verName = "";
        try {
            verName = context.getPackageManager().
                    getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return verName;
    }
}
