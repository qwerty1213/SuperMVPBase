package com.android.tool.widget;

import android.app.Activity;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;
import com.android.tool.R;
import com.android.tool.utility.AppConfig;

/**
 * class ：发送验证码倒计时
 * author：York(wuchunyuan)
 * time  : 2018/4/2 14:57
 */
public class TimeCountUtil extends CountDownTimer {
    public static final long MILLISINFUTURE = AppConfig.CODE_MILLISINFUTURE_TIME;//总倒计时
    public static final long COUNTDOWNINTERVAL = 1000;//每间隔1秒

    private TextView mTextView;
    private Activity mActivity;

    public TimeCountUtil(Activity mActivity, TextView mTextView, long millisInFuture, long countDownInterval) {
        super(millisInFuture, countDownInterval);
        this.mActivity = mActivity;
        this.mTextView = mTextView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        // 按钮不可用
        mTextView.setEnabled(false);
        String showText = "重新发送" + millisUntilFinished / 1000;
        mTextView.setText(showText);
        mTextView.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        mTextView.setBackgroundResource(R.drawable.login_code_bt_true);
    }

    @Override
    public void onFinish() {
        // 按钮设置可用
        mTextView.setEnabled(true);
        mTextView.setText("重新发送");
        mTextView.setTextColor(ContextCompat.getColor(mActivity, R.color.white));
        mTextView.setBackgroundResource(R.drawable.login_code_bt_false);
    }
}
