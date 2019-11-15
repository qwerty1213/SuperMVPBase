package com.android.tool.presenter;

import android.app.Activity;

public interface HomePageSeachListPresenter {
    void getRequested(Activity mActivity, int page, String seachContent);

    int getPage();
}
