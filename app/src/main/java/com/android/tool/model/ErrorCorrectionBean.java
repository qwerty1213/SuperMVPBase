package com.android.tool.model;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/9/10 16:31
 */
public class ErrorCorrectionBean {
    private String errorName;
    private boolean isChecked;

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
