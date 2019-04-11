package com.android.tool.utility;
import android.app.Activity;
import com.android.tool.ui.base.BaseJsonCallback;
import com.lzy.okgo.request.base.Request;

public abstract class ObjectCallback<T> extends BaseJsonCallback<T> {
    private Activity mActivity;
    private String msg;

    public ObjectCallback(Activity mActivity, String msg) {
        super(mActivity);
        this.mActivity = mActivity;
        this.msg = msg;
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        super.onStart(request);
        try {
//            getDialogLoading(mActivity);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onError(com.lzy.okgo.model.Response<T> response) {
        super.onError(response);
        try {
//            dismissDialogLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onFinish() {
        super.onFinish();
        try {
//            dismissDialogLoading();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
