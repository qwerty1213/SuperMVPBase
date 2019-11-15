package com.android.tool.util.share;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.tool.R;
import com.android.tool.util.T;
import com.android.tool.util.share.bean.ShareBean;
import com.github.lany192.blurdialog.BlurDialogFragment;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMWeb;

public class ShareDialogFragment extends BlurDialogFragment {
    private static UMWeb web;
    private static Activity mActivity;
    private static ShareBean shareBean;
    private static Dialog mDialog;
    private static RelativeLayout lLayoutBg;
    private static LinearLayout layoutWeixin, layoutWeixinFriend, layoutWeibo, layoutQq/*, layoutShouchang, layoutjubao*/;
    private static Handler handler;

    public static ShareDialogFragment newInstance(Activity mActivity, ShareBean shareBean) {
        ShareDialogFragment.mActivity = mActivity;
        ShareDialogFragment.shareBean = shareBean;
        web = new UMWeb(shareBean.url);
        web.setTitle(shareBean.title);
        web.setThumb(shareBean.image);
        web.setDescription(shareBean.content);
        handler = new Handler();
        mDialog = new Dialog(mActivity);
        return new ShareDialogFragment();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        LayoutInflater inflater = LayoutInflater.from(mActivity);
        View layout = inflater.inflate(R.layout.dialog_share, null);
        lLayoutBg = layout.findViewById(R.id.lLayoutBg);
        layoutWeixin = layout.findViewById(R.id.layout_weixin);
        layoutWeixinFriend = layout.findViewById(R.id.layout_weixin_friend);
        layoutWeibo = layout.findViewById(R.id.layout_weibo);
        layoutQq = layout.findViewById(R.id.layout_qq);
        lLayoutBg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mDialog != null) {
                    mDialog.dismiss();
                }
            }
        });

        layoutWeixin.setOnClickListener(new layoutWeixin());
        layoutWeixinFriend.setOnClickListener(new layoutWeixinFriend());
        layoutWeibo.setOnClickListener(new layoutWeibo());
        layoutQq.setOnClickListener(new layoutQq());
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        mDialog.addContentView(layout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        mDialog.setCanceledOnTouchOutside(true);
        mDialog.setContentView(layout);
        lLayoutBg.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        openPublishVew(mActivity);
//        mDialog.show();
        return mDialog;
    }

    public static void openPublishVew(final Context context) {
        layoutWeixin.setVisibility(View.INVISIBLE);
        layoutWeixinFriend.setVisibility(View.INVISIBLE);
        layoutWeibo.setVisibility(View.INVISIBLE);
        layoutQq.setVisibility(View.INVISIBLE);
        lLayoutBg.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_fade_in));
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutWeixin.setVisibility(View.VISIBLE);
                layoutWeixin.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_translate_up));
            }
        }, 100);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutWeixinFriend.setVisibility(View.VISIBLE);
                layoutWeixinFriend.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_translate_up));
            }
        }, 300);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutWeibo.setVisibility(View.VISIBLE);
                layoutWeibo.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_translate_up));
            }
        }, 500);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layoutQq.setVisibility(View.VISIBLE);
                layoutQq.startAnimation(AnimationUtils.loadAnimation(context, R.anim.anim_translate_up));
            }
        }, 700);
    }


    static class layoutWeixin implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN).withMedia(web).setCallback(umShareListener).share();
            dismissDialog();
        }
    }

    static class layoutWeixinFriend implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            new ShareAction(mActivity).setPlatform(SHARE_MEDIA.WEIXIN_CIRCLE).withMedia(web).setCallback(umShareListener).share();
            dismissDialog();
        }
    }

    static class layoutWeibo implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            UMWeb web = new UMWeb(shareBean.url);
            web.setTitle(shareBean.title);
            web.setThumb(shareBean.image);
            web.setDescription(shareBean.specialContents);
            new ShareAction(mActivity).setPlatform(SHARE_MEDIA.SINA).withMedia(web).setCallback(umShareListener).share();
            dismissDialog();
        }
    }

    static class layoutQq implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            new ShareAction(mActivity).setPlatform(SHARE_MEDIA.QQ).withMedia(web).setCallback(umShareListener).share();
            dismissDialog();
        }
    }

    public static void dismissDialog() {
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public static UMShareListener umShareListener = new UMShareListener() {

        @Override
        public void onStart(SHARE_MEDIA share_media) {
        }

        @Override
        public void onResult(SHARE_MEDIA platform) {
            T.customToastShort(mActivity, mActivity.getResources().getString(R.string.share_success));
        }

        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            T.customToastShort(mActivity, mActivity.getResources().getString(R.string.share_fail));
        }

        @Override
        public void onCancel(SHARE_MEDIA platform) {
            T.customToastShort(mActivity, mActivity.getResources().getString(R.string.cancel_share));
        }
    };


    @Override
    protected boolean isDimmingEnable() {
        return true;
    }

    @Override
    protected boolean isActionBarBlurred() {
        return true;
    }

    @Override
    protected float getDownScaleFactor() {
        return 9;
    }

    @Override
    protected int getBlurRadius() {
        return 5;
    }
}
