package com.android.tool.ui.main.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.ui.base.BaseFragments;

/**
 * @author York(wuchunyuan)
 * @Created 2019/4/16.
 */
public class Fragment3 extends BaseFragments {
    TextView textView;

    public static Fragment3 newInstance(String text) {
        Fragment3 fragmentCommon = new Fragment3();
        Bundle bundle = new Bundle();
        bundle.putString("text", text);
        fragmentCommon.setArguments(bundle);
        return fragmentCommon;
    }

    @Override
    public void initParms(Bundle mBundle) {

    }

    @Override
    public int bindLayout() {
        return R.layout.fragment_layout3;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {

    }
}