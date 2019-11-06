package com.android.tool.model;

import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/4 10:28
 */
public class OndemandBean {


    /**
     * total : 119
     * rows : [{"id":"10138866","name":"16教育教学知识与能力（小学）","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/81867be2c39767954447fad669bdd694.png","courseCountStr":"【38课时】","salesStr":"8483人已学习","priceStr":"¥288.00"},{"id":"10138933","name":"16中学 教育知识与能力","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/8c79b4fcc947583f7bbeb4ab020e2384.png","courseCountStr":"【42课时】","salesStr":"9721人已学习","priceStr":"¥288.00"},{"id":"10139263","name":"【教师资格证】 （循环班）","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/684e02361225b821c313a5fa96845402.jpg","courseCountStr":"","salesStr":"6254人已学习","priceStr":"¥780.00"}]
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
         * id : 10138866
         * name : 16教育教学知识与能力（小学）
         * coverImgUrl : http://wanglei.new.images.sx1211.ea-crm.com/81867be2c39767954447fad669bdd694.png
         * courseCountStr : 【38课时】
         * salesStr : 8483人已学习
         * priceStr : ¥288.00
         */

        private String id;
        private String name;
        private String coverImgUrl;
        private String courseCountStr;
        private String salesStr;
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

        public String getCourseCountStr() {
            return courseCountStr;
        }

        public void setCourseCountStr(String courseCountStr) {
            this.courseCountStr = courseCountStr;
        }

        public String getSalesStr() {
            return salesStr;
        }

        public void setSalesStr(String salesStr) {
            this.salesStr = salesStr;
        }

        public String getPriceStr() {
            return priceStr;
        }

        public void setPriceStr(String priceStr) {
            this.priceStr = priceStr;
        }
    }
}
