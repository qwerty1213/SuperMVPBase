package com.android.tool.model;

import java.io.Serializable;
import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/6/19 11:25
 */
public class OrderBean implements Serializable {
    public final static String INIT = "init";//取消订单和去支付
    public final static String PAY = "pay";//显示 待发货 不能点击
    public final static String SEND = "send";//显示待收货 不能点击
    public final static String RECEIVE = "receive";//显示待评价 点击去评价
    public final static String COMPLETE = "complete";//已完成 不能点击
    public final static String CANCEL = "cancel";//已取消 不能点击
    public final static String CLOSE = "close";//已关闭 不能点击

    /**
     * total : 29
     * rows : [{"orderId":"42022469","status":"close","statusName":"已关闭","name":"18案例分析 预售","currentTotal":"200.00","originalTotal":"200.00","createTime":"2017-12-13 16:42:10","details":[{"name":"18案例分析 预售","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/adc96b4d9c0c8beb84338345476f89dc.png","priceStr":"¥200.00","objId":"41856870","objType":"video"}]},{"orderId":"42022405","status":"close","statusName":"已关闭","name":"18案例分析 预售","currentTotal":"200.00","originalTotal":"200.00","createTime":"2017-12-13 16:41:57","details":[{"name":"18案例分析 预售","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/adc96b4d9c0c8beb84338345476f89dc.png","priceStr":"¥200.00","objId":"41856870","objType":"video"}]},{"orderId":"39738244","status":"receive","statusName":"待评价","name":"后台添加订单- 17教师资格笔试+面试协议班","currentTotal":"0.10","originalTotal":"0.10","createTime":"2017-11-25 09:16:43","details":[{"name":"17教师资格笔试+面试协议班","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/ffaeb801b1fb1ad86634d73e319c4bba.jpg","priceStr":"¥2980.00","objId":"25430774","objType":"live"}]},{"orderId":"38324042","status":"receive","statusName":"待评价","name":"后台添加订单- 18年教师招聘协议班一期（回放）","currentTotal":"0.10","originalTotal":"0.10","createTime":"2017-11-10 16:41:42","details":[{"name":"18年教师招聘协议班一期（回放）","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/3ef24637a48f6b2b03251428e0af232f.jpg","priceStr":"¥6980.00","objId":"32558222","objType":"video"}]},{"orderId":"31150422","status":"close","statusName":"已关闭","name":"教师资格证中学真题班（回放）","currentTotal":"99.00","originalTotal":"99.00","createTime":"2017-09-10 10:45:01","details":[{"name":"教师资格证中学真题班（回放）","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/2098d6d0178bfc12d00bc2850008a30f.jpg","priceStr":"¥99.00","objId":"30418998","objType":"video"}]},{"orderId":"31149045","status":"close","statusName":"已关闭","name":"小学综合素质（回放）","currentTotal":"699.00","originalTotal":"699.00","createTime":"2017-09-10 10:29:31","details":[{"name":"小学综合素质（回放）","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/38c2cea4f1129802ef161d455b73a7ea.jpg","priceStr":"¥699.00","objId":"30430509","objType":"video"}]},{"orderId":"31148953","status":"close","statusName":"已关闭","name":"招教协议班四期冲刺课（回放）","currentTotal":"580.00","originalTotal":"580.00","createTime":"2017-09-10 10:29:13","details":[{"name":"招教协议班四期冲刺课（回放）","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/bf8465806e803284882e1d680319b1f3.jpg","priceStr":"¥580.00","objId":"30547008","objType":"video"}]},{"orderId":"31147751","status":"close","statusName":"已关闭","name":"教师资格证幼儿真题班（回放）","currentTotal":"99.00","originalTotal":"99.00","createTime":"2017-09-10 10:19:24","details":[{"name":"教师资格证幼儿真题班（回放）","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/0d0210db634f4c98f3bf7245fdac6763.jpg","priceStr":"¥99.00","objId":"30420260","objType":"video"}]},{"orderId":"31147256","status":"close","statusName":"已关闭","name":"招教协议班四期冲刺课（回放）","currentTotal":"580.00","originalTotal":"580.00","createTime":"2017-09-10 10:14:47","details":[{"name":"招教协议班四期冲刺课（回放）","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/bf8465806e803284882e1d680319b1f3.jpg","priceStr":"¥580.00","objId":"30547008","objType":"video"}]},{"orderId":"31146855","status":"close","statusName":"已关闭","name":"16案例分析与教学设计等","currentTotal":"598.00","originalTotal":"598.00","createTime":"2017-09-10 10:10:38","details":[{"name":"16案例分析与教学设计","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/3745298635889c8d051bdf0d2374b44f.png","priceStr":"¥200.00","objId":"10139935","objType":"video"},{"name":"2017小学教育教学知识与能力","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/26733b4fc6a0e0c6f04bed4b92e922fa.jpg","priceStr":"¥288.00","objId":"16211531","objType":"video"},{"name":"17小学综合素质","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/4328756cce448e39cd1368e88391b987.jpg","priceStr":"¥288.00","objId":"17829224","objType":"video"}]}]
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

    public static class RowsBean implements Serializable {
        /**
         * orderId : 42022469
         * status : close
         * statusName : 已关闭
         * name : 18案例分析 预售
         * currentTotal : 200.00
         * originalTotal : 200.00
         * createTime : 2017-12-13 16:42:10
         * details : [{"name":"18案例分析 预售","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/adc96b4d9c0c8beb84338345476f89dc.png","priceStr":"¥200.00","objId":"41856870","objType":"video"}]
         */

        private String orderId;
        private String status;
        private String statusName;
        private String name;
        private String currentTotal;
        private String originalTotal;
        private String createTime;
        private List<DetailsBean> details;

        public String getOrderId() {
            return orderId;
        }

        public void setOrderId(String orderId) {
            this.orderId = orderId;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getStatusName() {
            return statusName;
        }

        public void setStatusName(String statusName) {
            this.statusName = statusName;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCurrentTotal() {
            return currentTotal;
        }

        public void setCurrentTotal(String currentTotal) {
            this.currentTotal = currentTotal;
        }

        public String getOriginalTotal() {
            return originalTotal;
        }

        public void setOriginalTotal(String originalTotal) {
            this.originalTotal = originalTotal;
        }

        public String getCreateTime() {
            return createTime;
        }

        public void setCreateTime(String createTime) {
            this.createTime = createTime;
        }

        public List<DetailsBean> getDetails() {
            return details;
        }

        public void setDetails(List<DetailsBean> details) {
            this.details = details;
        }

        public static class DetailsBean implements Serializable {
            /**
             * name : 18案例分析 预售
             * imgUrl : http://wanglei.new.images.sx1211.ea-crm.com/adc96b4d9c0c8beb84338345476f89dc.png
             * priceStr : ¥200.00
             * objId : 41856870
             * objType : video
             */

            private String bookorderDetailId;
            private String name;
            private String imgUrl;
            private String priceStr;
            private String objId;
            private String objType;
            private String url;
            private String contents;
            private String score;

            public String getContents() {
                return contents;
            }

            public void setContents(String contents) {
                this.contents = contents;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getBookorderDetailId() {
                return bookorderDetailId;
            }

            public void setBookorderDetailId(String bookorderDetailId) {
                this.bookorderDetailId = bookorderDetailId;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
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

            public String getPriceStr() {
                return priceStr;
            }

            public void setPriceStr(String priceStr) {
                this.priceStr = priceStr;
            }

            public String getObjId() {
                return objId;
            }

            public void setObjId(String objId) {
                this.objId = objId;
            }

            public String getObjType() {
                return objType;
            }

            public void setObjType(String objType) {
                this.objType = objType;
            }
        }
    }
}
