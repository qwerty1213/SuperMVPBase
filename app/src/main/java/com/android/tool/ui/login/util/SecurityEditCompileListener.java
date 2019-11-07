package com.android.tool.ui.login.util;

import android.text.Editable;

public interface SecurityEditCompileListener {
    void onNumCompleted(String password);

    void afterTextChanged(Editable s);

}
