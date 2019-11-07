package com.android.tool.model;

import java.io.Serializable;
import java.util.List;

/**
 * @author York(wuchunyuan)
 * @Created 2019/4/24.
 */
public class UseCouponsModel {

    private List<AvailableCouponBean> availableCoupon;
    private List<UnavailableCouponBean> unavailableCoupon;

    public List<AvailableCouponBean> getAvailableCoupon() {
        return availableCoupon;
    }

    public void setAvailableCoupon(List<AvailableCouponBean> availableCoupon) {
        this.availableCoupon = availableCoupon;
    }

    public List<UnavailableCouponBean> getUnavailableCoupon() {
        return unavailableCoupon;
    }

    public void setUnavailableCoupon(List<UnavailableCouponBean> unavailableCoupon) {
        this.unavailableCoupon = unavailableCoupon;
    }

    public static class AvailableCouponBean implements Serializable {
        /**
         * id : 112481993
         * amountStr : ￥5
         * endTimeStr : 有效期至2019.05.16
         * name : 五一大放送
         * limitAmountStr : 满10.00使用
         * useStr : 仅限中小学教育知识与能力（回放）等使用
         */

        private String id;
        private String amountStr;
        private String endTimeStr;
        private String name;
        private String limitAmountStr;
        private String useStr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAmountStr() {
            return amountStr;
        }

        public void setAmountStr(String amountStr) {
            this.amountStr = amountStr;
        }

        public String getEndTimeStr() {
            return endTimeStr;
        }

        public void setEndTimeStr(String endTimeStr) {
            this.endTimeStr = endTimeStr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLimitAmountStr() {
            return limitAmountStr;
        }

        public void setLimitAmountStr(String limitAmountStr) {
            this.limitAmountStr = limitAmountStr;
        }

        public String getUseStr() {
            return useStr;
        }

        public void setUseStr(String useStr) {
            this.useStr = useStr;
        }
    }

    public static class UnavailableCouponBean implements Serializable {
        /**
         * id : 112480491
         * amountStr : ￥5
         * endTimeStr : 有效期至2019.05.12
         * name : 五一大放送2
         * limitAmountStr : 满10.00使用
         * useStr : 仅限大礼包使用
         */

        private String id;
        private String amountStr;
        private String endTimeStr;
        private String name;
        private String limitAmountStr;
        private String useStr;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAmountStr() {
            return amountStr;
        }

        public void setAmountStr(String amountStr) {
            this.amountStr = amountStr;
        }

        public String getEndTimeStr() {
            return endTimeStr;
        }

        public void setEndTimeStr(String endTimeStr) {
            this.endTimeStr = endTimeStr;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getLimitAmountStr() {
            return limitAmountStr;
        }

        public void setLimitAmountStr(String limitAmountStr) {
            this.limitAmountStr = limitAmountStr;
        }

        public String getUseStr() {
            return useStr;
        }

        public void setUseStr(String useStr) {
            this.useStr = useStr;
        }
    }
}
