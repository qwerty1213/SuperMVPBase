package com.android.tool.ui.web;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.android.tool.util.StringUtil;


/**
 * class ：JumpActivity中做打开后的处理，用来接收外部的跳转
 * author：York(wuchunyuan)
 * time  : 2018/6/28 09:06
 */
public class JumpActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();//在这个Activity里，我们可以通过getIntent()，来获取外部跳转传过来的信息。
        String data = intent.getDataString();//接收到网页传过来的数据
        if (StringUtil.isNotBlankAndEmpty(data)) {
//            startActivity(new Intent(JumpActivity.this, MainFragmentActivity.class));
            WebURLUtil.openUrl(data, JumpActivity.this);
            finish();
        }
//		然后我们再通过网页打开app的同时就可以用获得的url数据做一些我们需要做的处理
//        比如你在微信里浏览网页时打开自己的安卓app应用的同时，加载一个app内的网页
    }

}
