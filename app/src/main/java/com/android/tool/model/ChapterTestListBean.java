package com.android.tool.model;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/9/3 17:04
 */
public class ChapterTestListBean {


    /**
     * id : 24088996
     * name : 法律法规
     * completCount : 0
     * totalCount : 480
     */

    private String id;
    private String name;
    private int completCount;
    private int totalCount;
    private String pictureUrl;

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCompletCount() {
        return completCount;
    }

    public void setCompletCount(int completCount) {
        this.completCount = completCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }
}
