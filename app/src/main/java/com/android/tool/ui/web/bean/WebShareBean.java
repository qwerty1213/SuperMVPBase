package com.android.tool.ui.web.bean;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/28 16:29
 */
public class WebShareBean {

    /**
     * title : 面试简讯，十分重要，进面学员必看
     * contents : “山香老师”APP是山香教育为考生精心打造的一款适用于参加教师资格证、教师招聘考试的掌中备考学习平台。提供教师资格证笔试面试、教师招聘考试笔试面试、题库练习、直播辅导课程 、点播辅导课程、错题纠错，帮助考生实现掌中备考，随时随地学习，助您成师！乘山香翅膀 圆教师梦想！
     * imgUrl : https://1211cn.oss-cn-hangzhou.aliyuncs.com/images/iteacher_logo.png
     * url : http://wanglei.new.apih5.sx1211.ea-crm.com/Message/detail/id/1.html
     * specialContents : #山香教育#面试简讯，十分重要，进面学员必看 http://wanglei.new.apih5.sx1211.ea-crm.com/Message/detail/id/1.html
     */

    private String title;
    private String contents;
    private String imgUrl;
    private String url;
    private String specialContents;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSpecialContents() {
        return specialContents;
    }

    public void setSpecialContents(String specialContents) {
        this.specialContents = specialContents;
    }
}
