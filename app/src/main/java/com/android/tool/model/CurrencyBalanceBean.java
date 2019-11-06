package com.android.tool.model;

import java.io.Serializable;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/25 10:34
 */
public class CurrencyBalanceBean implements Serializable {


    /**
     * amount : 40
     * introductionUrl : sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/card/introduction.html&title=学习金使用帮助&type=1
     * qq : mqqwpa://im/chat?chat_type=wpa&uin=513782198
     * tel : tel:4006003363
     * helpUrl : http://wanglei.new.apih5.sx1211.ea-crm.com/PageContents/detail/id/7166791.html
     * recommendShareInfo : {"title":"欢迎使用山香老师APP","contents":"\u201c山香老师\u201dAPP是山香教育为考生精心打造的一款适用于参加教师资格证、教师招聘考试的掌中备考学习平台。提供教师资格证笔试面试、教师招聘考试笔试面试、题库练习、直播辅导课程 、点播辅导课程、错题纠错，帮助考生实现掌中备考，随时随地学习，助您成师！乘山香翅膀 圆教师梦想！","imgUrl":"https://1211cn.oss-cn-hangzhou.aliyuncs.com/images/iteacher_logo.png","url":"http://wanglei.new.apih5.sx1211.ea-crm.com/Download","specialContents":"#山香教育#欢迎使用山香老师APP http://wanglei.new.apih5.sx1211.ea-crm.com/Download"}
     */

    private String amount;
    private String introductionUrl;
    private String qq;
    private String tel;
    private String helpUrl;
    private String couponCountStr;
    private String signature;

    public String getCouponCountStr() {
        return couponCountStr;
    }

    public void setCouponCountStr(String couponCountStr) {
        this.couponCountStr = couponCountStr;
    }

    private RecommendShareInfoBean recommendShareInfo;

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getIntroductionUrl() {
        return introductionUrl;
    }

    public void setIntroductionUrl(String introductionUrl) {
        this.introductionUrl = introductionUrl;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getHelpUrl() {
        return helpUrl;
    }

    public void setHelpUrl(String helpUrl) {
        this.helpUrl = helpUrl;
    }

    public RecommendShareInfoBean getRecommendShareInfo() {
        return recommendShareInfo;
    }

    public void setRecommendShareInfo(RecommendShareInfoBean recommendShareInfo) {
        this.recommendShareInfo = recommendShareInfo;
    }

    public static class RecommendShareInfoBean {
        /**
         * title : 欢迎使用山香老师APP
         * contents : “山香老师”APP是山香教育为考生精心打造的一款适用于参加教师资格证、教师招聘考试的掌中备考学习平台。提供教师资格证笔试面试、教师招聘考试笔试面试、题库练习、直播辅导课程 、点播辅导课程、错题纠错，帮助考生实现掌中备考，随时随地学习，助您成师！乘山香翅膀 圆教师梦想！
         * imgUrl : https://1211cn.oss-cn-hangzhou.aliyuncs.com/images/iteacher_logo.png
         * url : http://wanglei.new.apih5.sx1211.ea-crm.com/Download
         * specialContents : #山香教育#欢迎使用山香老师APP http://wanglei.new.apih5.sx1211.ea-crm.com/Download
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
}
