package com.android.tool.model;

/**
 * @author York(wuchunyuan)
 * @Created 2019/3/7.
 */
public class FeedBackDataModel {

    /**
     * value : 1
     * text : 改进建议
     */

    private int value;
    private String text;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
