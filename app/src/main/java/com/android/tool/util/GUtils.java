package com.android.tool.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.android.tool.R;
import com.android.tool.photoview.PhotoView;
import com.android.tool.util.glide.GlideBlurformation;
import com.android.tool.widget.longimage.ImageSource;
import com.android.tool.widget.longimage.ImageViewState;
import com.android.tool.widget.longimage.SubsamplingScaleImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.util.Util;
/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2017/12/8 14:46
 */
public class GUtils {

    private String TAG = "ImageLoader";

    /**
     * 借助内部类 实现线程安全的单例模式
     * 属于懒汉式单例，因为Java机制规定，内部类SingletonHolder只有在getInstance()
     * 方法第一次调用的时候才会被加载（实现了lazy），而且其加载过程是线程安全的。
     * 内部类加载的时候实例化一次instance。
     */
    public GUtils() {
    }

    private static class GlideLoadUtilsHolder {
        private final static GUtils INSTANCE = new GUtils();
    }

    public static GUtils getInstance() {
        return GlideLoadUtilsHolder.INSTANCE;
    }

    /**
     * @param context
     */
    public static void resumeRequests(Context context) {
        if (context != null) {
            try {
                Glide.with(context).resumeRequests();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * @param context
     */
    public static void pauseRequests(Context context) {
        if (context != null) {
            try {
                Glide.with(context).pauseRequests();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     *
     * @param context
     * @param url       加载图片的url地址  String
     * @param imageView 加载图片的ImageView 控件
     */
    public static void glideStringLoad(Context context, String url, ImageView imageView) {
        if (context != null) {
            try {
                RequestOptions options = new RequestOptions();
                Glide.with(context).load(url).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     *
     * @param context
     * @param url       加载图片的url地址  String
     * @param imageView 加载图片的ImageView 控件
     */
    public static void glideNineStringLoad(Context context, String url, ImageView imageView) {
        if (context != null) {
            try {
                RequestOptions options = new RequestOptions().placeholder(R.mipmap.nine_zhanweitu_icon).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).load(url).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * Glide 加载 高斯模糊
     *
     * @param context
     * @param url       加载图片的url地址  String
     * @param imageView 加载图片的ImageView 控件
     */
    public static void loadGlideBlurformation(Context context, String url, ImageView imageView) {
        if (context != null) {
            try {
                Glide.with(context).load(url).apply(RequestOptions.bitmapTransform(new GlideBlurformation(context)))
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }


    /**
     * Glide 加载 简单判空封装 防止异步加载数据时调用Glide 抛出异常
     *
     * @param context
     * @param url       加载图片的url地址  String
     * @param imageView 加载图片的ImageView 控件
     */
    public static void glideIntLoad(Context context, int url, ImageView imageView) {
        if (context != null) {
            try {
                RequestOptions options = new RequestOptions().centerCrop();
                Glide.with(context).load(url).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 图片淡出效果
     *
     * @param activity
     * @param url
     * @param imageView
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadActivityReadyImageView(Activity activity, String url, final ImageView imageView) {
        if (!activity.isDestroyed()) {
            try {
                Glide.with(activity).asBitmap().load(url).into(new SimpleTarget<Bitmap>() {
                    @Override
                    public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                        AlphaAnimation alpha = new AlphaAnimation(0.0f, 1.0f);
                        alpha.setDuration(500);
                        alpha.setFillAfter(true);
                        imageView.setImageBitmap(resource);
                        imageView.setAnimation(alpha);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * 加载Bitmap  设置比例
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    @SuppressLint("CheckResult")
    public static void loadSimpleTargetBitmap(final Context context, final String imgUrl, final ImageView imageView) {
        if (context != null) {
            try {
//                Glide.with(context).asBitmap().load(imgUrl).into(new SimpleTargetBitmap(context, imageView));
                RequestOptions options = new RequestOptions().placeholder(R.color.c_f0f0).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).asBitmap().load(imgUrl).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 加载Bitmap  设置比例
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    @SuppressLint("CheckResult")
    public static void loadStringBanner(final Context context, final String imgUrl, final ImageView imageView) {
        if (context != null) {
            try {
                Glide.with(context).load(imgUrl).apply(new RequestOptions().placeholder(R.drawable.zhanwei_banner2x).error(R.drawable.zhanwei_banner2x).dontAnimate().centerCrop())
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 加载Bitmap
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    @SuppressLint("CheckResult")
    public static void loadBitmap(final Context context, final String imgUrl, final ImageView imageView) {
        if (context != null) {
            try {
                RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).asBitmap().load(imgUrl).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 加载Bitmap
     *
     * @param context
     * @param imgUrl
     * @param imageView
     */
    @SuppressLint("CheckResult")
    public static void loadBitmapWH(final Context context, final String imgUrl, final ImageView imageView, int w, int h) {
        if (context != null) {
            try {
                RequestOptions options = new RequestOptions().placeholder(R.color.c_f0f0).override(w, h).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).asBitmap().load(imgUrl).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }


    /**
     * 加载GIF  设置比例
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadSimpleTargetGif(final Context context, String url, final ImageView imageView) {
        if (context != null) {
            try {
//                Glide.with(context).asGif().load(url).into(new SimpleTargetGif(context, imageView));
                RequestOptions options = new RequestOptions().placeholder(R.color.c_f0f0).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).asGif().load(url).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 加载GIF
     *
     * @param context
     * @param url
     * @param imageView
     */
    public static void loadGif(final Context context, String url, final ImageView imageView) {
        if (context != null) {
            try {
                RequestOptions options = new RequestOptions().placeholder(R.color.c_f0f0).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(context).asGif().load(url).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 加载GIF
     *
     * @param mActivity
     * @param url
     * @param imageView
     */
    public static void loadBrowsesBitmap(final Activity mActivity, String url, final PhotoView imageView) {
        if (mActivity != null) {
            try {
                RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(mActivity).asBitmap().load(url).apply(options)
                        .into(new SimpleTarget<Bitmap>(480, 800) {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                imageView.setImageBitmap(resource);
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 加载GIF
     *
     * @param mActivity
     * @param url
     * @param LongImage
     */
    public static void loadBrowsesLong(final Activity mActivity, String url, final SubsamplingScaleImageView LongImage) {
        if (mActivity != null) {
            try {
                RequestOptions options = new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL);
                Glide.with(mActivity).asBitmap().load(url).apply(options)
                        .into(new SimpleTarget<Bitmap>(480, 800) {
                            @Override
                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                LongImage.setQuickScaleEnabled(true);
                                LongImage.setZoomEnabled(true);
                                LongImage.setPanEnabled(true);
                                LongImage.setDoubleTapZoomDuration(100);
                                LongImage.setMinimumScaleType(SubsamplingScaleImageView.SCALE_TYPE_CENTER_CROP);
                                LongImage.setDoubleTapZoomDpi(SubsamplingScaleImageView.ZOOM_FOCUS_CENTER);
                                LongImage.setImage(ImageSource.cachedBitmap(resource), new ImageViewState(0, new PointF(0, 0), 0));
                            }
                        });
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 加载GIF
     *
     * @param mActivity
     * @param url
     * @param imageview
     */
    public static void loadBrowsesGif(final Activity mActivity, String url, final PhotoView imageview) {
        if (mActivity != null) {
            try {
                RequestOptions gifOptions = new RequestOptions().override(480, 800).priority(Priority.HIGH)
                        .diskCacheStrategy(DiskCacheStrategy.NONE);
                Glide.with(mActivity).asGif().load(url).apply(gifOptions).into(imageview);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }


    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void glideLoadSkipMemoryCache(Activity activity, String url, ImageView imageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE);
                Glide.with(activity).load(url).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void glideLoad(Activity activity, String url, ImageView imageView, int default_image) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().error(default_image);
                Glide.with(activity).load(url).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * Banner String类型
     *
     * @param fragment
     * @param url
     * @param imageView
     */
    public static void loadFragmentBanner(Fragment fragment, String url, ImageView imageView) {
        if (fragment != null && fragment.getActivity() != null) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei_banner2x)
                        .error(R.drawable.zhanwei_banner2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(fragment).load(url).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,fragment is null");
        }
    }

    /**
     * Banner String类型
     *
     * @param activity
     * @param url
     * @param imageView
     */
    public static void loadActivityBanner(Activity activity, String url, ImageView imageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei_banner2x)
                        .error(R.drawable.zhanwei_banner2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(url).thumbnail(0.1f).apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,fragment is null");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVStringHeadYuan(Activity activity, String url, ImageView imageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.default_head_icon)
                        .error(R.mipmap.default_head_icon).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(url).thumbnail(0.1f)
                        .apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVStringCourseHeadYuan(Activity activity, String url, ImageView imageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.course_teacher_head_icon)
                        .error(R.mipmap.course_teacher_head_icon).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(url).thumbnail(0.1f)
                        .apply(options).into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * 头像圆 int类型
     *
     * @param activity
     * @param url
     * @param imageView
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVStringHeadYuan(Activity activity, int url, ImageView imageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.mipmap.default_head_icon)
                        .error(R.mipmap.default_head_icon).diskCacheStrategy(DiskCacheStrategy.RESOURCE).dontAnimate();
                Glide.with(activity).load(url).thumbnail(0.1f).apply(options)
                        .into(imageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * 正方形  直角 string类型
     *
     * @param activity
     * @param url
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVStringFang(Activity activity, String url, ImageView mImageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei_fang2x)
                        .error(R.drawable.zhanwei_fang2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(url).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * 正方形  直角 string类型
     *
     * @param activity
     * @param url
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVIntFang(Activity activity, int url, ImageView mImageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei_fang2x)
                        .error(R.drawable.zhanwei_fang2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(url).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * 列表长方形  直角 string类型
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVStringListChang(Activity activity, String path, ImageView mImageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei)
                        .error(R.drawable.zhanwei).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(path).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * 列表长方形  直角 string类型
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVIntListChang(Activity activity, int path, ImageView mImageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei)
                        .error(R.drawable.zhanwei).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(path).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * Banner String类型
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVStringGuangGao(Activity activity, String path, ImageView mImageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei_guanggao2x)
                        .error(R.drawable.zhanwei_guanggao2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(path).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * Banner String类型
     *
     * @param activity
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static void loadIVStringGuangGaoHome(Activity activity, String path, ImageView mImageView) {
        if (!activity.isDestroyed()) {
            try {
                RequestOptions options = new RequestOptions().placeholder(R.drawable.zhanwei_guanggao2x)
                        .error(R.drawable.zhanwei_guanggao2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(activity).load(path).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,activity is Destroyed");
        }
    }

    /**
     * Banner Object类型
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    public static void loadIVObjectBanner(Context mContext, Object path, ImageView mImageView) {
        if (mContext != null) {
            try {
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei_banner2x)
                        .error(R.drawable.zhanwei_banner2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(mContext).load(path).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * Banner Object类型
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    public static void loadIVObjectBannerTransform(Context mContext, Object path, ImageView mImageView) {
        if (mContext != null) {
            try {
                //设置图片圆角角度
                RoundedCorners roundedCorners = new RoundedCorners(15);
                RequestOptions options = new RequestOptions().centerCrop().placeholder(R.drawable.zhanwei_banner2x)
                        .error(R.drawable.zhanwei_banner2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE).bitmapTransform(roundedCorners);
                Glide.with(mContext).load(path).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    /**
     * 缓存所有 Uri
     *
     * @param mContext
     * @param path
     * @param mImageView
     */
    public static void loadIVUriCacheAll(Context mContext, Uri path, ImageView mImageView) {
        if (mContext != null) {
            try {
                RequestOptions options = new RequestOptions().placeholder(R.drawable.zhanwei_fang2x)
                        .error(R.drawable.zhanwei_fang2x).diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                Glide.with(mContext).load(path).thumbnail(0.1f).apply(options).into(mImageView);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            L.i("Picture loading failed,context is null");
        }
    }

    public static boolean isOnMainThread() {
        if (Util.isOnMainThread()) {
            return true;
        } else {
            return false;
        }
    }

}
