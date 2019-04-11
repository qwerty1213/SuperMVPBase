package com.android.tool.presenter;

import android.app.Activity;

public interface TestPresenter {
    void getRequested(Activity mActivity, int page);

    int getPage();
}
