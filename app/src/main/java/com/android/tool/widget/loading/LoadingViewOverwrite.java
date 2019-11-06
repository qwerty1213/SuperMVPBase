package com.android.tool.widget.loading;


import android.content.Context;
import android.view.View;

import com.android.tool.R;

/**
 * class ：LoadingView二次封装版
 * author：York(wuchunyuan)
 * time  : 2017/7/7 13:39
 */
public class LoadingViewOverwrite {
    private Context mContext;

    public LoadingViewOverwrite(Context mContext) {
        this.mContext = mContext;
    }

    /**
     * @param mLoadingView
     * @param retryListener
     * @return
     */
    public static LoadingView loadingGlobal(LoadingView mLoadingView, LoadingView.OnRetryListener retryListener) {
        mLoadingView
                //空数据...
                .withEmptyIcon(R.mipmap.default_loading_icon)
                .withLoadEmptyText(R.string.nothing_error)
                .withBtnEmptyEnnable(false)
                //加载失败...
                .withErrorIco(R.mipmap.default_loading_icon).withLoadErrorText(R.string.load_failed)
                .withBtnErrorText(R.string.reload_the)
                //网络错误...
                .withLoadNoNetworkText(R.string.network_error)
                .withNoNetIcon(R.mipmap.default_loading_icon)
                .withBtnNoNetText(R.string.reload_the)
                //加载中...
                .withLoadingText(R.string.loading)
                .withOnRetryListener(retryListener).build();
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.setState(LoadingState.STATE_LOADING);
        return mLoadingView;
    }

    /**
     * 招教首页
     *
     * @param mLoadingView
     * @param retryListener
     * @return
     */
    public static LoadingView loadingTeachingGlobal(LoadingView mLoadingView, LoadingView.OnRetryListener retryListener) {
        mLoadingView
                //空数据...
                .withEmptyIcon(R.mipmap.default_loading_icon)
                .withLoadEmptyText(R.string.nothing_want_to_couser_error)
                .withBtnEmptyText(R.string.reload_the)
                //加载失败...
                .withErrorIco(R.mipmap.default_loading_icon).withLoadErrorText(R.string.load_failed)
                .withBtnErrorText(R.string.reload_the)
                //网络错误...
                .withLoadNoNetworkText(R.string.network_error)
                .withNoNetIcon(R.mipmap.default_loading_icon)
                .withBtnNoNetText(R.string.reload_the)
                //加载中...
                .withLoadingText(R.string.loading)
                .withOnRetryListener(retryListener).build();
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.setState(LoadingState.STATE_LOADING);
        return mLoadingView;
    }

    /**
     * 收藏列表
     *
     * @param mLoadingView
     * @param retryListener
     * @return
     */
    public static LoadingView loadingCollectionGlobal(LoadingView mLoadingView, LoadingView.OnRetryListener retryListener) {
        mLoadingView
                //空数据...
                .withEmptyIcon(R.mipmap.default_loading_icon)
                .withLoadEmptyText(R.string.nothing_want_to_collection_error)
                .withBtnEmptyEnnable(false)
                //加载失败...
                .withErrorIco(R.mipmap.default_loading_icon).withLoadErrorText(R.string.load_failed)
                .withBtnErrorText(R.string.reload_the)
                //网络错误...
                .withLoadNoNetworkText(R.string.network_error)
                .withNoNetIcon(R.mipmap.default_loading_icon)
                .withBtnNoNetText(R.string.reload_the)
                //加载中...
                .withLoadingText(R.string.loading)
                .withOnRetryListener(retryListener).build();
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.setState(LoadingState.STATE_LOADING);
        return mLoadingView;
    }

    /**
     * 收藏列表
     *
     * @param mLoadingView
     * @param retryListener
     * @return
     */
    public static LoadingView loadingGlobal(LoadingView mLoadingView, int nothing, LoadingView.OnRetryListener retryListener) {
        mLoadingView
                //空数据...
                .withEmptyIcon(R.mipmap.default_loading_icon)
                .withLoadEmptyText(nothing)
                .withBtnEmptyEnnable(false)
                //加载失败...
                .withErrorIco(R.mipmap.default_loading_icon).withLoadErrorText(R.string.load_failed)
                .withBtnErrorText(R.string.reload_the)
                //网络错误...
                .withLoadNoNetworkText(R.string.network_error)
                .withNoNetIcon(R.mipmap.default_loading_icon)
                .withBtnNoNetText(R.string.reload_the)
                //加载中...
                .withLoadingText(R.string.loading)
                .withOnRetryListener(retryListener).build();
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.setState(LoadingState.STATE_LOADING);
        return mLoadingView;
    }

    /**
     * @param mLoadingView
     * @param retryListener
     * @return
     */
    public static LoadingView loadingAddressManagementGlobal(LoadingView mLoadingView, LoadingView.OnRetryListener retryListener) {
        mLoadingView
                //空数据...
                .withEmptyIcon(R.mipmap.default_loading_icon)
                .withLoadEmptyText(R.string.add_address)
                .withBtnEmptyEnnable(false)
                //加载失败...
                .withErrorIco(R.mipmap.default_loading_icon).withLoadErrorText(R.string.load_failed)
                .withBtnErrorText(R.string.reload_the)
                //网络错误...
                .withLoadNoNetworkText(R.string.network_error)
                .withNoNetIcon(R.mipmap.default_loading_icon)
                .withBtnNoNetText(R.string.reload_the)
                //加载中...
                .withLoadingText(R.string.loading)
                .withOnRetryListener(retryListener).build();
        mLoadingView.setVisibility(View.VISIBLE);
        mLoadingView.setState(LoadingState.STATE_LOADING);
        return mLoadingView;
    }

    /**
     * @param mLoadingView
     * @param retryListener
     * @return
     */
    public static LoadingSsView loading(LoadingSsView mLoadingView, LoadingSsView.OnRetryListener retryListener) {
        mLoadingView
                //空数据...
                .withEmptyIcon(R.mipmap.default_loading_icon)
                .withLoadEmptyText(R.string.add_address)
                .withBtnEmptyEnnable(false)
                //加载失败...
                .withErrorIco(R.mipmap.default_loading_icon).withLoadErrorText(R.string.load_failed)
                .withBtnErrorText(R.string.reload_the)
                //网络错误...
                .withLoadNoNetworkText(R.string.network_error)
                .withNoNetIcon(R.mipmap.default_loading_icon)
                .withBtnNoNetText(R.string.reload_the)
                //加载中...
                .withLoadingText(R.string.loading)
                .withOnRetryListener(retryListener).build();
        mLoadingView.setVisibility(View.GONE);
//        mLoadingView.setState(LoadingState.STATE_LOADING);
        return mLoadingView;
    }


}
