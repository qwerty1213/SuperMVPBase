package com.android.tool.util;

import android.content.Context;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class ScreenUtil {

    /**
     * 获得屏幕宽度
     *
     * @param context
     * @return
     */
    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获取屏幕高度
     */
    public static int getScreenHight(Context context) {

        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    /**
     * 获取屏幕高度(px)
     */
    public static int getScreenHeights(Context context) {
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    /**
     * 获取屏幕宽度(px)
     */
    public static int getScreenWidths(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static float dp2px(Context context, float dp) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }

    public static float px2dp(Context context, float px) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_PX, px, context.getResources().getDisplayMetrics());
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * 设置图片比例
     *
     * @param context
     * @param imageView
     */
    public static void setImageView16_9(Context context, ImageView imageView) {
        //计算图片左右间距之和
        int padding = 15;
        int spacePx = (int) (ScreenUtil.dp2px(context, padding) * 2);
        //计算图片宽度
        int imageWidth = ScreenUtil.getScreenWidths(context) - spacePx;
        //计算宽高比，注意数字后面要加上f表示浮点型数字
        float scale = 16f / 9f;
        //根据图片宽度和比例计算图片高度
        int imageHeight = (int) (imageWidth / scale);
        L.i("width16_9------>" + imageWidth + "<------height16_9------>" + imageHeight);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(imageWidth, imageHeight);
//                //设置左右边距
//                params.leftMargin = (int) ScreenUtil.dp2px(context, padding);
//                params.rightMargin = (int) ScreenUtil.dp2px(context, padding);
        imageView.setLayoutParams(params);
    }

    /**
     * 获取图片比例16:9高度
     *
     * @param context
     */
    public static int getImage16_9Height(Context context) {
        //15 计算图片左右间距之和
        //计算图片宽度
        int imageWidth = ScreenUtil.getScreenWidths(context) - (int) (ScreenUtil.dp2px(context, 15) * 2);
        //16f / 9f 计算宽高比，注意数字后面要加上f表示浮点型数字
        //根据图片宽度和比例计算图片高度
        return (int) (imageWidth / (16f / 9f));
    }

}
