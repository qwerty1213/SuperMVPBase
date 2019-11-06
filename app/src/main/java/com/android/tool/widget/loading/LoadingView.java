package com.android.tool.widget.loading;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.widget.ButtonState;
import com.cunoraz.gifview.library.GifView;

/**
 * class ：加载Loading效果，效果包括：加载中、无网络、空数据、加载失败等
 * author：York(wuchunyuan)
 * time  : 2017/6/29 14:49
 */
public class LoadingView extends FrameLayout {

    private Context mContext;
    // 加载中的布局
    private LinearLayout mLinearLoad;
    //其他加载的布局
    private LinearLayout mLinearLoading;
    private RelativeLayout rlLoading;

    private TextView mTvLoading;

    private TextView mTvLoad;

    private ImageView mIvLoad;

    private ButtonState mBtnLoad;

    private LoadingState mState;
    private GifView gifview;
    private AnimationDrawable animation;


    public LoadingView(Context context) {
        super(context);
        mContext = context;
    }

    public LoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public LoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void build() {
        LayoutInflater.from(mContext).inflate(R.layout.view_loading_state, this, true);

        mLinearLoading = (LinearLayout) findViewById(R.id.lin_loading);
        rlLoading = (RelativeLayout) findViewById(R.id.rl_loading);
        rlLoading.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLinearLoad = (LinearLayout) findViewById(R.id.lin_load);

        gifview = (GifView) findViewById(R.id.gifview);
        gifview.setGifResource(R.drawable.loading_gif);
        gifview.play();

        mIvLoad = (ImageView) findViewById(R.id.iv_load);

        mTvLoading = (TextView) findViewById(R.id.tv_loading);

        mTvLoad = (TextView) findViewById(R.id.tv_load);

        mBtnLoad = (ButtonState) findViewById(R.id.btn_load);
        mBtnLoad.setRadius(new float[]{20, 20, 20, 20, 20, 20, 20, 20});
        mBtnLoad.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                setState(LoadingState.STATE_LOADING);
                mOnRetryListener.onRetry();
            }
        });
    }

    @Override
    public void setVisibility(int visibility) {
        super.setVisibility(visibility);

        if (View.GONE == visibility && mState == LoadingState.STATE_LOADING && animation != null && animation.isRunning()) {
            animation.stop();
        }
    }

    /**
     * 加载中提示文字
     */
    private String mLoadingText;
    private int mLoadingIcon;

    public LoadingView withLoadingIcon(int resId) {
        mLoadingIcon = resId;
        return this;
    }

    /**
     * 加载数据为空提示文字
     */
    private String mLoadEmptyText;
    private int mLoadEmptyIcon;

    public LoadingView withEmptyIcon(int resId) {
        mLoadEmptyIcon = resId;
        return this;
    }

    /**
     * 无网络提示
     */
    private String mLoadNoNetworkText;
    private int mNoNetworkIcon;

    public LoadingView withNoNetIcon(int resId) {
        mNoNetworkIcon = resId;
        return this;
    }

    private OnRetryListener mOnRetryListener;

    /**
     * 定义重试的的接口
     */
    public interface OnRetryListener {
        void onRetry();
    }

    public LoadingView withOnRetryListener(OnRetryListener mOnRetryListener) {
        this.mOnRetryListener = mOnRetryListener;
        return this;
    }

    /**
     * 设置加载的状态
     *
     * @param state
     */
    public void setState(LoadingState state) {
        if (mState == state) {
            return;
        } else if (state == LoadingState.STATE_LOADING) {
            mLinearLoading.setVisibility(VISIBLE);
            mLinearLoad.setVisibility(GONE);
        } else if (state != LoadingState.STATE_LOADING) {
            mLinearLoading.setVisibility(GONE);
            mLinearLoad.setVisibility(VISIBLE);
            if (animation != null && mState == LoadingState.STATE_LOADING)
                animation.stop();
        }
        changeState(state);
    }


    public boolean btnEmptyEnable = true;
    public boolean btnErrorEnable = true;
    public boolean btnNoNetworkEnable = true;

    public LoadingView withBtnNoNetEnnable(boolean ennable) {
        btnNoNetworkEnable = ennable;
        return this;
    }

    public LoadingView withBtnErrorEnnable(boolean ennable) {
        btnErrorEnable = ennable;
        return this;
    }


    public LoadingView withBtnEmptyEnnable(boolean ennable) {
        btnEmptyEnable = ennable;
        return this;
    }

    /**
     * 改变状态
     *
     * @param state
     */
    private void changeState(LoadingState state) {
        switch (state) {
            //加载中
            case STATE_LOADING:
                mState = LoadingState.STATE_LOADING;
//                mTvLoading.setText(mLoadingText);
                mBtnLoad.setVisibility(GONE);
                break;
            //数据为空
            case STATE_EMPTY:
                mState = LoadingState.STATE_EMPTY;
                mIvLoad.setImageResource(mLoadEmptyIcon);
                mTvLoad.setText(mLoadEmptyText);
                if (btnEmptyEnable) {
                    mBtnLoad.setVisibility(VISIBLE);
                    mBtnLoad.setText(btn_empty_text);
                } else {
                    mBtnLoad.setVisibility(GONE);
                }
                break;
            //加载失败
            case STATE_ERROR:
                mState = LoadingState.STATE_ERROR;
                mIvLoad.setImageResource(mErrorIco);
                mTvLoad.setText(mLoadErrorText);
                if (btnErrorEnable) {
                    mBtnLoad.setVisibility(VISIBLE);
                    mBtnLoad.setText(btn_error_text);
                } else {
                    mBtnLoad.setVisibility(GONE);
                }
                break;
            //无网络
            case STATE_NO_NET:
                mState = LoadingState.STATE_NO_NET;
                mIvLoad.setImageResource(mNoNetworkIcon);
                mTvLoad.setText(mLoadNoNetworkText);
                if (btnNoNetworkEnable) {
                    mBtnLoad.setVisibility(VISIBLE);
                    mBtnLoad.setText(btn_nonet_text);
                } else {
                    mBtnLoad.setVisibility(GONE);
                }
                break;
        }

    }


    /**
     * 后台或者本地出现错误提示
     */
    private String mLoadErrorText;
    private int mErrorIco;

    public LoadingView withErrorIco(int resId) {
        mErrorIco = resId;
        return this;
    }

    /**
     * 加载空数据
     *
     * @param resId
     * @return
     */
    public LoadingView withLoadEmptyText(int resId) {
        mLoadEmptyText = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadEmptyText(String mLoadEmptyText) {
        this.mLoadEmptyText = mLoadEmptyText;
        return this;
    }

    /**
     * 无网络时候加载文字
     *
     * @param resId
     * @return
     */
    public LoadingView withLoadNoNetworkText(int resId) {
        mLoadNoNetworkText = getResources().getString(resId);
        return this;
    }

    public String btn_empty_text = "重试";
    public String btn_error_text = "重试";
    public String btn_nonet_text = "重试";

    /**
     * 数据为空的Button的文字提示
     *
     * @param text
     * @return
     */
    public LoadingView withBtnEmptyText(int text) {
        this.btn_empty_text = getResources().getString(text);
        return this;
    }

    /**
     * 加载错误的Button的文字提示
     *
     * @param resId
     * @return
     */
    public LoadingView withBtnErrorText(int resId) {
        this.btn_error_text = getResources().getString(resId);
        return this;
    }

    /**
     * 加载错误的Button的文字提示
     *
     * @param text
     * @return
     */
    public LoadingView withBtnErrorText(String text) {
        this.btn_error_text = text;
        return this;
    }

    /**
     * 加载错误的文字提示
     *
     * @param resId
     * @return
     */
    public LoadingView withLoadErrorText(int resId) {
        this.mLoadErrorText = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadErrorText(String mLoadedErrorText) {
        this.mLoadErrorText = mLoadedErrorText;
        return this;
    }

    /**
     * 加载无网络的Button的文字提示
     *
     * @param resId
     * @return
     */
    public LoadingView withBtnNoNetText(int resId) {
        this.btn_nonet_text = getResources().getString(resId);
        return this;
    }

    /**
     * 加载无网络的Button的文字提示
     *
     * @param text
     * @return
     */
    public LoadingView withBtnNoNetText(String text) {
        this.btn_nonet_text = text;
        return this;
    }

    /**
     * 加载没有网路的文字提示
     *
     * @param mLoadedNoNetText
     * @return
     */
    public LoadingView withLoadNoNetworkText(String mLoadedNoNetText) {
        this.mLoadNoNetworkText = mLoadedNoNetText;
        return this;
    }

    public LoadingView withLoadingText(int resId) {
        this.mLoadingText = getResources().getString(resId);
        return this;
    }

    public LoadingView withLoadingText(String mLoadingText) {
        this.mLoadingText = mLoadingText;
        return this;
    }

}
