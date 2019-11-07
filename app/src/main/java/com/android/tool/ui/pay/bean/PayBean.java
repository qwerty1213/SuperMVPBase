package com.android.tool.ui.pay.bean;

import java.io.Serializable;
import java.util.List;

/**
 * class ：支付页面数据
 * author：York(wuchunyuan)
 * time  : 2018/4/11 10:35
 */
public class PayBean implements Serializable {

    /**
     * isShowAddress : 1
     * defaultAddress : {"id":"17967293","realName":"孙艺琪","mobile":"18435159769","address":"北京市 县 密云县北京后台测试"}
     * maxCurrency : 0
     * totalCurrency : 0
     * currencyTip : 温馨提示：抵值上限为课程价格的15%
     * usedCurrency : 0
     * originalPriceStr : ¥48
     * currentPriceStr : ¥48
     * products : [{"id":"10137924","name":"2016教师资格题库幼儿综合素质 教材（暂不出售）","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/8a5636b4bcefc55363cce4cc6d8c5288.200-200.jpg","number":"1","priceStr":"¥48.00"}]
     * payWayList : [{"imageUrl":"http://wanglei.new.statics.iteacher.ea-crm.com/public/api/images/pay/alipay.png","payWay":"alipay","payName":"支付宝支付","isSelect":"1"},{"imageUrl":"http://wanglei.new.statics.iteacher.ea-crm.com/public/api/images/pay/wechat.png","payWay":"wechat","payName":"微信支付","isSelect":"0"}]
     */

    private String isShowAddress;
    private DefaultAddressBean defaultAddress;
    private String maxCurrency;
    private String totalCurrency;
    private String currencyTip;
    private String usedCurrency;
    private String couponAmountStr;
    private String originalPriceStr;
    private String currentPriceStr;
    private List<ProductsBean> products;
    private List<PayWayListBean> payWayList;

    public String getCouponAmountStr() {
        return couponAmountStr;
    }

    public void setCouponAmountStr(String couponAmountStr) {
        this.couponAmountStr = couponAmountStr;
    }

    public String getIsShowAddress() {
        return isShowAddress;
    }

    public void setIsShowAddress(String isShowAddress) {
        this.isShowAddress = isShowAddress;
    }

    public DefaultAddressBean getDefaultAddress() {
        return defaultAddress;
    }

    public void setDefaultAddress(DefaultAddressBean defaultAddress) {
        this.defaultAddress = defaultAddress;
    }

    public String getMaxCurrency() {
        return maxCurrency;
    }

    public void setMaxCurrency(String maxCurrency) {
        this.maxCurrency = maxCurrency;
    }

    public String getTotalCurrency() {
        return totalCurrency;
    }

    public void setTotalCurrency(String totalCurrency) {
        this.totalCurrency = totalCurrency;
    }

    public String getCurrencyTip() {
        return currencyTip;
    }

    public void setCurrencyTip(String currencyTip) {
        this.currencyTip = currencyTip;
    }

    public String getUsedCurrency() {
        return usedCurrency;
    }

    public void setUsedCurrency(String usedCurrency) {
        this.usedCurrency = usedCurrency;
    }

    public String getOriginalPriceStr() {
        return originalPriceStr;
    }

    public void setOriginalPriceStr(String originalPriceStr) {
        this.originalPriceStr = originalPriceStr;
    }

    public String getCurrentPriceStr() {
        return currentPriceStr;
    }

    public void setCurrentPriceStr(String currentPriceStr) {
        this.currentPriceStr = currentPriceStr;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public List<PayWayListBean> getPayWayList() {
        return payWayList;
    }

    public void setPayWayList(List<PayWayListBean> payWayList) {
        this.payWayList = payWayList;
    }

    public static class DefaultAddressBean implements Serializable {
        /**
         * id : 17967293
         * realName : 孙艺琪
         * mobile : 18435159769
         * address : 北京市 县 密云县北京后台测试
         */

        private String id;
        private String realName;
        private String mobile;
        private String address;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public static class ProductsBean implements Serializable {
        /**
         * id : 10137924
         * name : 2016教师资格题库幼儿综合素质 教材（暂不出售）
         * imgUrl : http://wanglei.new.images.sx1211.ea-crm.com/8a5636b4bcefc55363cce4cc6d8c5288.200-200.jpg
         * number : 1
         * priceStr : ¥48.00
         */

        private String id;
        private String name;
        private String imgUrl;
        private String number;
        private String priceStr;

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

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }

        public String getNumber() {
            return number;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public String getPriceStr() {
            return priceStr;
        }

        public void setPriceStr(String priceStr) {
            this.priceStr = priceStr;
        }
    }

    public static class PayWayListBean implements Serializable {
        /**
         * imageUrl : http://wanglei.new.statics.iteacher.ea-crm.com/public/api/images/pay/alipay.png
         * payWay : alipay
         * payName : 支付宝支付
         * isSelect : 1
         */

        private String imageUrl;
        private String payWay;
        private String payName;
        private String isSelect;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }

        public String getPayWay() {
            return payWay;
        }

        public void setPayWay(String payWay) {
            this.payWay = payWay;
        }

        public String getPayName() {
            return payName;
        }

        public void setPayName(String payName) {
            this.payName = payName;
        }

        public String getIsSelect() {
            return isSelect;
        }

        public void setIsSelect(String isSelect) {
            this.isSelect = isSelect;
        }
    }
}
