package com.android.tool.widget.loading;

import android.content.Context;
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

/**
 * class ：加载Loading效果，效果包括：加载中、无网络、空数据、加载失败等
 * author：York(wuchunyuan)
 * time  : 2017/6/29 14:49
 */
public class LoadingSsView extends FrameLayout {

    private Context mContext;
    // 加载中的布局
    private LinearLayout mLinearLoad;
    private RelativeLayout rlLoading;

    private TextView mTvLoad;

    private ImageView mIvLoad;

    private ButtonState mBtnLoad;

    private LoadingState mState;


    public LoadingSsView(Context context) {
        super(context);
        mContext = context;
    }

    public LoadingSsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public LoadingSsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    public void build() {
        LayoutInflater.from(mContext).inflate(R.layout.view_loading_ss_state, this, true);

        rlLoading = (RelativeLayout) findViewById(R.id.rl_loading);
        rlLoading.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        mLinearLoad = (LinearLayout) findViewById(R.id.lin_load);

        mIvLoad = (ImageView) findViewById(R.id.iv_load);

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
    }

    /**
     * 加载中提示文字
     */
    private String mLoadingText;
    private int mLoadingIcon;

    public LoadingSsView withLoadingIcon(int resId) {
        mLoadingIcon = resId;
        return this;
    }

    /**
     * 加载数据为空提示文字
     */
    private String mLoadEmptyText;
    private int mLoadEmptyIcon;

    public LoadingSsView withEmptyIcon(int resId) {
        mLoadEmptyIcon = resId;
        return this;
    }

    /**
     * 无网络提示
     */
    private String mLoadNoNetworkText;
    private int mNoNetworkIcon;

    public LoadingSsView withNoNetIcon(int resId) {
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

    public LoadingSsView withOnRetryListener(OnRetryListener mOnRetryListener) {
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
            mLinearLoad.setVisibility(GONE);
        } else if (state != LoadingState.STATE_LOADING) {
            mLinearLoad.setVisibility(VISIBLE);
        }
        changeState(state);
    }


    public boolean btnEmptyEnable = true;
    public boolean btnErrorEnable = true;
    public boolean btnNoNetworkEnable = true;

    public LoadingSsView withBtnNoNetEnnable(boolean ennable) {
        btnNoNetworkEnable = ennable;
        return this;
    }

    public LoadingSsView withBtnErrorEnnable(boolean ennable) {
        btnErrorEnable = ennable;
        return this;
    }


    public LoadingSsView withBtnEmptyEnnable(boolean ennable) {
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

    public LoadingSsView withErrorIco(int resId) {
        mErrorIco = resId;
        return this;
    }

    /**
     * 加载空数据
     *
     * @param resId
     * @return
     */
    public LoadingSsView withLoadEmptyText(int resId) {
        mLoadEmptyText = getResources().getString(resId);
        return this;
    }

    public LoadingSsView withLoadEmptyText(String mLoadEmptyText) {
        this.mLoadEmptyText = mLoadEmptyText;
        return this;
    }

    /**
     * 无网络时候加载文字
     *
     * @param resId
     * @return
     */
    public LoadingSsView withLoadNoNetworkText(int resId) {
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
    public LoadingSsView withBtnEmptyText(int text) {
        this.btn_empty_text = getResources().getString(text);
        return this;
    }

    /**
     * 加载错误的Button的文字提示
     *
     * @param resId
     * @return
     */
    public LoadingSsView withBtnErrorText(int resId) {
        this.btn_error_text = getResources().getString(resId);
        return this;
    }

    /**
     * 加载错误的Button的文字提示
     *
     * @param text
     * @return
     */
    public LoadingSsView withBtnErrorText(String text) {
        this.btn_error_text = text;
        return this;
    }

    /**
     * 加载错误的文字提示
     *
     * @param resId
     * @return
     */
    public LoadingSsView withLoadErrorText(int resId) {
        this.mLoadErrorText = getResources().getString(resId);
        return this;
    }

    public LoadingSsView withLoadErrorText(String mLoadedErrorText) {
        this.mLoadErrorText = mLoadedErrorText;
        return this;
    }

    /**
     * 加载无网络的Button的文字提示
     *
     * @param resId
     * @return
     */
    public LoadingSsView withBtnNoNetText(int resId) {
        this.btn_nonet_text = getResources().getString(resId);
        return this;
    }

    /**
     * 加载无网络的Button的文字提示
     *
     * @param text
     * @return
     */
    public LoadingSsView withBtnNoNetText(String text) {
        this.btn_nonet_text = text;
        return this;
    }

    /**
     * 加载没有网路的文字提示
     *
     * @param mLoadedNoNetText
     * @return
     */
    public LoadingSsView withLoadNoNetworkText(String mLoadedNoNetText) {
        this.mLoadNoNetworkText = mLoadedNoNetText;
        return this;
    }

    public LoadingSsView withLoadingText(int resId) {
        this.mLoadingText = getResources().getString(resId);
        return this;
    }

    public LoadingSsView withLoadingText(String mLoadingText) {
        this.mLoadingText = mLoadingText;
        return this;
    }

}
