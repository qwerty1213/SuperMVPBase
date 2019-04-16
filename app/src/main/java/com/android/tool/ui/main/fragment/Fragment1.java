package com.android.tool.ui.main.fragment;

import android.os.Bundle;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.ui.base.BaseFragments;

/**
 * @author York(wuchunyuan)
 * @Created 2019/4/16.
 */
public class Fragment1 extends BaseFragments {
    TextView textView;

    public static Fragment1 newInstance(String text) {
        Fragment1 fragmentCommon = new Fragment1();
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
        return R.layout.fragment_layout1;
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