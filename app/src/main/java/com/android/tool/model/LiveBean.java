package com.android.tool.model;

import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/6 10:57
 */
public class LiveBean {


    /**
     * total : 433
     * rows : [{"id":"49164626","name":"汾阳2018招教面试礼仪直播课","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/5e10040a2e76daa98b8bf3e484df01d6.png","salesStr":"","courseCountStr":"","priceStr":"¥0.00"},{"id":"49110462","name":"综合素质：教育法律法规概述","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/fcfe38a1927875d6945aa55a9d8179bb.jpg","salesStr":"","courseCountStr":"","priceStr":"¥0.00"}]
     * nextPageIndex : 2
     */

    private int total;
    private String nextPageIndex;
    private List<RowsBean> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getNextPageIndex() {
        return nextPageIndex;
    }

    public void setNextPageIndex(String nextPageIndex) {
        this.nextPageIndex = nextPageIndex;
    }

    public List<RowsBean> getRows() {
        return rows;
    }

    public void setRows(List<RowsBean> rows) {
        this.rows = rows;
    }

    public static class RowsBean {
        /**
         * id : 49164626
         * name : 汾阳2018招教面试礼仪直播课
         * coverImgUrl : http://wanglei.new.images.sx1211.ea-crm.com/5e10040a2e76daa98b8bf3e484df01d6.png
         * salesStr :
         * courseCountStr :
         * priceStr : ¥0.00
         */

        private String id;
        private String name;
        private String coverImgUrl;
        private String salesStr;
        private String courseCountStr;
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

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public String getSalesStr() {
            return salesStr;
        }

        public void setSalesStr(String salesStr) {
            this.salesStr = salesStr;
        }

        public String getCourseCountStr() {
            return courseCountStr;
        }

        public void setCourseCountStr(String courseCountStr) {
            this.courseCountStr = courseCountStr;
        }

        public String getPriceStr() {
            return priceStr;
        }

        public void setPriceStr(String priceStr) {
            this.priceStr = priceStr;
        }
    }
}
