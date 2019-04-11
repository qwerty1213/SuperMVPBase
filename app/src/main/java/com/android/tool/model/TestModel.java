package com.android.tool.model;

import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/9/26 15:56
 */
public class TestModel {
    /**
     * total : 103
     * rows : [{"id":"49477998","name":"2018下教资干货集中营（中学 包邮仅需九块九）","url":"sxapp://goProductDetail?objtype=video&id=49477998","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/4653f99a4016410417e7df7d8a18fc1f.300_150.jpg","courseCountStr":"","salesStr":"6人已学习","priceStr":"¥9.90","liveStatus":"0","liveStatusStr":""},{"id":"49339429","name":"18中学综合素质","url":"sxapp://goProductDetail?objtype=video&id=49339429","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/103aa7888f0d04ffaab6fcd257c93d55.300_150.png","courseCountStr":"","salesStr":"173人已学习","priceStr":"¥0.01","liveStatus":"0","liveStatusStr":""},{"id":"49339354","name":"18中学教育知识与能力","url":"sxapp://goProductDetail?objtype=video&id=49339354","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/ead5522de6a878e870b2d9814f9db3bc.300_150.png","courseCountStr":"","salesStr":"177人已学习","priceStr":"¥0.01","liveStatus":"0","liveStatusStr":""},{"id":"49338960","name":"18小学材料分析、教学设计与写作","url":"sxapp://goProductDetail?objtype=video&id=49338960","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/ad34f75bf0898f7b7a2591c911c9b3e5.300_150.png","courseCountStr":"","salesStr":"170人已学习","priceStr":"¥200.00","liveStatus":"0","liveStatusStr":""},{"id":"49338731","name":"18小学综合素质","url":"sxapp://goProductDetail?objtype=video&id=49338731","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/9987ebe423669a34cea325ee7cc76d5e.300_150.png","courseCountStr":"","salesStr":"140人已学习","priceStr":"¥288.00","liveStatus":"0","liveStatusStr":""},{"id":"49338358","name":"18小学教育教学知识与能力","url":"sxapp://goProductDetail?objtype=video&id=49338358","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/d39da44f83687d7acb713d5ff4f78651.300_150.png","courseCountStr":"","salesStr":"135人已学习","priceStr":"¥288.00","liveStatus":"0","liveStatusStr":""},{"id":"49337544","name":"18幼儿案例、活动设计与写作","url":"sxapp://goProductDetail?objtype=video&id=49337544","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/b7dd4221f1a83d0b67360f5d927eea0e.300_150.png","courseCountStr":"","salesStr":"136人已学习","priceStr":"¥200.00","liveStatus":"0","liveStatusStr":""},{"id":"49336223","name":"18幼儿综合素质","url":"sxapp://goProductDetail?objtype=video&id=49336223","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/393764bb2f7de962e82310b130a427c9.300_150.png","courseCountStr":"","salesStr":"118人已学习","priceStr":"¥288.00","liveStatus":"0","liveStatusStr":""},{"id":"49335061","name":"18幼儿保教知识与能力","url":"sxapp://goProductDetail?objtype=video&id=49335061","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/cd42aec766fb71a2bd3d71c82d5c428d.300_150.png","courseCountStr":"","salesStr":"124人已学习","priceStr":"¥288.00","liveStatus":"0","liveStatusStr":""},{"id":"47985430","name":"2018教师资格面试公开课","url":"sxapp://goProductDetail?objtype=video&id=47985430","coverImgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/9b88463c41603cd54aca3a2dc0439b94.300_150.png","courseCountStr":"2课时","salesStr":"5人已学习","priceStr":"¥50.00","liveStatus":"0","liveStatusStr":""}]
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
         * id : 49477998
         * name : 2018下教资干货集中营（中学 包邮仅需九块九）
         * url : sxapp://goProductDetail?objtype=video&id=49477998
         * coverImgUrl : http://wanglei.new.images.sx1211.ea-crm.com/4653f99a4016410417e7df7d8a18fc1f.300_150.jpg
         * courseCountStr :
         * salesStr : 6人已学习
         * priceStr : ¥9.90
         * liveStatus : 0
         * liveStatusStr :
         */

        private String id;
        private String name;
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
