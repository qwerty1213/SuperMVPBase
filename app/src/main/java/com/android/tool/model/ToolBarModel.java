package com.android.tool.model;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.tool.R;

/**
 * @author York(wuchunyuan)
 * @Created 2019/4/11.
 */
public class ToolBarModel {
    private TextView txtTitle, txtRight;
    private ImageView ivToolbarBack, ivRight;
    private Activity activity;
    private Toolbar toolbar;

    public ToolBarModel(Activity activity, Toolbar toolbar) {
        this.activity = activity;
        this.toolbar = toolbar;
        ((AppCompatActivity) activity).setSupportActionBar(toolbar);
        ((AppCompatActivity) activity).getSupportActionBar().setDisplayShowTitleEnabled(false);
        ivToolbarBack = (ImageView) toolbar.findViewById(R.id.iv_back);
        ivRight = (ImageView) toolbar.findViewById(R.id.iv_right);
        txtTitle = (TextView) toolbar.findViewById(R.id.txt_title);
        txtRight = (TextView) toolbar.findViewById(R.id.txt_right);
    }


    /**
     * 进行回退
     */
    public ToolBarModel setBack() {
        ivToolbarBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        return this;
    }

    /**
     * 回退按钮资源更改
     */
    public ToolBarModel setBackImageResource(int resource) {
        ivToolbarBack.setImageResource(resource);
        return this;
    }

    /**
     * 回退按钮资源更改
     */
    public ToolBarModel setRightImageResource(int resource) {
        ivRight.setImageResource(resource);
        return this;
    }

    /**
     * 设置标题
     */
    public ToolBarModel setTitle(String title) {
        txtTitle.setText(title);
        return this;
    }

    /**
     * 设置标题
     */
    public ToolBarModel setRight(String right) {
        txtRight.setText(right);
        return this;
    }

    /**
     * 自定义的回退
     */
    public ToolBarModel setBackClickListener(View.OnClickListener onClickListener) {
        ivToolbarBack.setOnClickListener(onClickListener);
        return this;
    }

    /**
     * 自定义的回退
     */
    public ToolBarModel setRightClickListener(View.OnClickListener onClickListener) {
        ivRight.setOnClickListener(onClickListener);
        return this;
    }
}
