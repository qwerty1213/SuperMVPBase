package com.android.tool.ui.pay.bean;

import com.google.gson.annotations.SerializedName;

/**
 * class ：提交微信订单
 * author：York(wuchunyuan)
 * time  : 2018/4/11 13:56
 */
public class SubmitWeiChatOrdersBean {


    /**
     * result : {"appid":"wxbb6f053a1ca76cfb","package":"Sign=WXPay","partnerid":"F5EB3A6B112E85B2B8C2A781562C30BF","prepayid":"wx20170210170510984479fb4c0286373146","noncestr":"yzpbwcvkgz0a4yjy1jk1k6i1910wel6k","timestamp":"1486717510","sign":"FC33F2866B4EA6BF1096B62A5EE7A172"}
     * orderId : 470304
     */

    private ResultBean result;
    private String orderId;

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public static class ResultBean {
        /**
         * appid : wxbb6f053a1ca76cfb
         * package : Sign=WXPay
         * partnerid : F5EB3A6B112E85B2B8C2A781562C30BF
         * prepayid : wx20170210170510984479fb4c0286373146
         * noncestr : yzpbwcvkgz0a4yjy1jk1k6i1910wel6k
         * timestamp : 1486717510
         * sign : FC33F2866B4EA6BF1096B62A5EE7A172
         */

        private String appid;
        @SerializedName("package")
        private String packageX;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
