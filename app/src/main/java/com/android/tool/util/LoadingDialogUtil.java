package com.android.tool.util;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.tool.R;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.style.Circle;


/***
 * 得到自定义的progressDialog
 */
public class LoadingDialogUtil {
    public static Dialog createLoadingDialog(Context context, String msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_loading_view);// 加载布局
        SpinKitView spinKit = (SpinKitView) v.findViewById(R.id.spin_kit);
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        spinKit.setIndeterminateDrawable( new Circle());
//        spinKit.setIndeterminateDrawable( new FadingCircle());
//        spinKit.setIndeterminateDrawable( new ThreeBounce());
        tipTextView.setText(msg);// 设置加载信息
        Dialog loadingDialog = new Dialog(context, R.style.loadingStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));// 设置布局
        loadingDialog.show();
        return loadingDialog;
    }

    public static Dialog createLoadingDialog(Context context, int msg) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.dialog_loading, null);// 得到加载view
        LinearLayout layout = (LinearLayout) v.findViewById(R.id.dialog_loading_view);// 加载布局
        TextView tipTextView = (TextView) v.findViewById(R.id.tipTextView);// 提示文字
        tipTextView.setText(msg);// 设置加载信息
        SpinKitView spinKit = (SpinKitView) v.findViewById(R.id.spin_kit);
        spinKit.setIndeterminateDrawable( new Circle());
//        spinKit.setIndeterminateDrawable( new FadingCircle());
//        spinKit.setIndeterminateDrawable( new ThreeBounce());
        Dialog loadingDialog = new Dialog(context, R.style.loadingStyle);// 创建自定义样式dialog
        loadingDialog.setCancelable(true); // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false); // 点击加载框以外的区域
        loadingDialog.setContentView(layout, new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));// 设置布局
        loadingDialog.show();
        return loadingDialog;
    }

}
