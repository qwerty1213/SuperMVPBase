package com.android.tool.model;

import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/1 14:51
 */
public class HomePageSeachBean {


    /**
     * total : 1652
     * rows : [{"id":"166041796","name":"20190920添加直播回放","objType":"点播","url":"sxapp://goProductDetail?objtype=video&id=166041796","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/57c3a44fa7ba8261b7e6191b03273326.300_150.jpg","courseCountStr":"2课时","salesStr":"","priceStr":"¥0.00","liveStatus":"0","liveStatusStr":""},{"id":"88584755","name":"19招教笔试+面试协议班","objType":"直播","url":"sxapp://goProductDetail?objtype=live&id=88584755","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/03c1c483b055ee5f8b7e02cc3c365e25.300_150.jpg","courseCountStr":"","salesStr":"660人已学习","priceStr":"¥16800.00","liveStatus":"0","liveStatusStr":""},{"id":"94060810","name":"19状元考编协议班","objType":"直播","url":"sxapp://goProductDetail?objtype=live&id=94060810","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/8fa2b25538315349dee5c31bdfdf256b.300_150.jpg","courseCountStr":"","salesStr":"258人已学习","priceStr":"¥29800.00","liveStatus":"0","liveStatusStr":""},{"id":"88584368","name":"19招教笔试协议班","objType":"直播","url":"sxapp://goProductDetail?objtype=live&id=88584368","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/0758b3e75aa3ca6ed6cd9f512d979bba.300_150.jpg","courseCountStr":"","salesStr":"963人已学习","priceStr":"¥7980.00","liveStatus":"0","liveStatusStr":""},{"id":"163792609","name":"公告来啦\u201419教资下半年公告解读","objType":"直播","url":"sxapp://goProductDetail?objtype=live&id=163792609","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/3023aeb8ffd99c356a127cfb33580247.300_150.jpg","courseCountStr":"","salesStr":"178人已学习","priceStr":"¥0.00","liveStatus":"0","liveStatusStr":""},{"id":"143428708","name":"19山西教师招聘备考冲刺","objType":"直播","url":"sxapp://goProductDetail?objtype=live&id=143428708","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/3ac34edc90a54f2bedab09b2fb93a34c.300_150.jpg","courseCountStr":"","salesStr":"648人已学习","priceStr":"¥0.00","liveStatus":"0","liveStatusStr":""},{"id":"105467339","name":"19年河北招教公开课\u2014教育学","objType":"点播","url":"sxapp://goProductDetail?objtype=video&id=105467339","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/3390524f85a1e7f7882396eea1a15a41.300_150.jpg","courseCountStr":"1课时","salesStr":"699人已学习","priceStr":"¥0.00","liveStatus":"0","liveStatusStr":""},{"id":"94458513","name":"19年全国招教公开课\u2014教育学","objType":"直播","url":"sxapp://goProductDetail?objtype=live&id=94458513","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/665653e3b5fd0fb2515db10d3476ae12.300_150.jpg","courseCountStr":"1课时","salesStr":"6352人已学习","priceStr":"¥0.00","liveStatus":"0","liveStatusStr":""},{"id":"94458023","name":"19年全国招教公开课\u2014心理学","objType":"直播","url":"sxapp://goProductDetail?objtype=live&id=94458023","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/b23a737ec112fafa9e427e01e4613194.300_150.jpg","courseCountStr":"1课时","salesStr":"5240人已学习","priceStr":"¥0.00","liveStatus":"0","liveStatusStr":""},{"id":"166108632","name":"2019年06月18日上午添加的直播 copy2->3","objType":"直播","url":"sxapp://goProductDetail?objtype=live&id=166108632","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/65c69378aa2496028c24daa88cbd41a2.300_150.jpg","courseCountStr":"10课时","salesStr":"","priceStr":"¥0.00","liveStatus":"0","liveStatusStr":""}]
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
         * id : 166041796
         * name : 20190920添加直播回放
         * objType : 点播
         * url : sxapp://goProductDetail?objtype=video&id=166041796
         * coverImgUrl : http://wanglei.new.images.sx1211.ea-crm.com/57c3a44fa7ba8261b7e6191b03273326.300_150.jpg
         * courseCountStr : 2课时
         * salesStr :
         * priceStr : ¥0.00
         * liveStatus : 0
         * liveStatusStr :
         */

        private String id;
        private String name;
        private String objType;
        private String url;
        private String coverImgUrl;
        private String courseCountStr;
        private String salesStr;
        private String priceStr;
        private String liveStatus;
        private String liveStatusStr;

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

        public String getObjType() {
            return objType;
        }

        public void setObjType(String objType) {
            this.objType = objType;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
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

        public String getLiveStatus() {
            return liveStatus;
        }

        public void setLiveStatus(String liveStatus) {
            this.liveStatus = liveStatus;
        }

        public String getLiveStatusStr() {
            return liveStatusStr;
        }

        public void setLiveStatusStr(String liveStatusStr) {
            this.liveStatusStr = liveStatusStr;
        }
    }
}
