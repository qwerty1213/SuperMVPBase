package com.android.tool.model;

import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/3/1 15:37
 */
public class HomePageBean {

        /**
         * topBanner : [{"name":"河南","url":"sxapp://goWebView?url=https://ke.qq.com/course/356651&title=河南&type=2","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/8fcfc4830bdc52726dea124ca050d929.png"},{"name":"山东","url":"sxapp://goProductDetail?objtype=live&id=135795022","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/fa231b6f7e6060bdb480316d45930508.png"}]
         * functionButton : [{"name":"点播","imgUrl":"http://wanglei.new.statics.sx1211.ea-crm.com/public/api/images/functionbutton/video.png?timestamp=e1469006018800","url":"sxapp://goProductTabList?objtype=video"},{"name":"直播","imgUrl":"http://wanglei.new.statics.sx1211.ea-crm.com/public/api/images/functionbutton/live.png?timestamp=e1469006018800","url":"sxapp://goProductTabList?objtype=live"},{"name":"大礼包","imgUrl":"http://wanglei.new.statics.sx1211.ea-crm.com/public/api/images/functionbutton/package.png?timestamp=e1469006018800","url":"sxapp://getProductPackageList"},{"name":"天猫图书","imgUrl":"http://wanglei.new.statics.sx1211.ea-crm.com/public/api/images/functionbutton/book.png?timestamp=e1469006018800","url":"taobao://sxjyts.tmall.com/shop/view_shop.htm?user_number_id=2777143824&ali_trackid=2%3Amm_24585955_16766104_61984342%3A1489398099_2k3_928105592"}]
         * notice : [{"id":"140955603","tip":"提示","name":"抢鸭！安徽招教政策解读+速效通关礼包，0元助你成功上岸！","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/140955603&title=公告&type=1"},{"id":"123415797","tip":"提示","name":"2019云南省大理州事业单位招聘公告（教师178名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123415797&title=公告&type=1"},{"id":"123415716","tip":"提示","name":"2019浙江宁波慈城镇招聘非事业编制幼儿教师公告（15名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123415716&title=公告&type=1"},{"id":"123415584","tip":"提示","name":"2019浙江舟山市教育局直属高中面向社会招聘教师公告（11名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123415584&title=公告&type=1"},{"id":"123415562","tip":"提示","name":"2019山东淄博市事业单位招聘教师公告（1050名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123415562&title=公告&type=1"},{"id":"123415429","tip":"提示","name":"2019云南怒江州各级事业单位招聘人员公告（教师105名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123415429&title=公告&type=1"},{"id":"123415347","tip":"提示","name":"2019湖北施州州招聘事业单位人员公告（教师31名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123415347&title=公告&type=1"},{"id":"123415280","tip":"提示","name":"2019湖北宜昌远安县事业单位 （公办幼儿园）招聘工作人员公告（10名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123415280&title=公告&type=1"},{"id":"123415140","tip":"提示","name":"2019江苏省淮安生态文旅区建华观园幼儿园公开招聘工作人员公告（教师20名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123415140&title=公告&type=1"},{"id":"123172032","tip":"提示","name":"2019湖北天门市事业单位招聘工作人员公告（教师160名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123172032&title=公告&type=1"},{"id":"123171762","tip":"提示","name":"2019山东缤智苏滨城区引进\u201c双一流\u201d建设高校师范类毕业生公告（40名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123171762&title=公告&type=1"},{"id":"123171442","tip":"提示","name":"2019云南临沧市事业单位公开招聘新进工作人员考试的公告（教师230名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123171442&title=公告&type=1"},{"id":"123171380","tip":"提示","name":"2019湖北襄阳市保康县事业单位专项招聘急需紧缺人才公告（教师12名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123171380&title=公告&type=1"},{"id":"123171308","tip":"提示","name":"2019辽宁省沈阳市招聘中等职业学校专业课教师公告（70名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123171308&title=公告&type=1"},{"id":"123140425","tip":"提示","name":"2019辽宁省沈阳市招聘中小学（幼儿园）教师公告（745名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123140425&title=公告&type=1"},{"id":"123140415","tip":"提示","name":"2019湖北随州市事业单位统一组织招聘工作人员公告（幼师32名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123140415&title=公告&type=1"},{"id":"123140396","tip":"提示","name":"2019湖北宜昌市点军区事业单位集中招聘工作人员公告（教师3名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123140396&title=公告&type=1"},{"id":"123140282","tip":"提示","name":"2019湖北宜昌市市直事业单位集中公开招聘工作人员公告（教师13名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123140282&title=公告&type=1"},{"id":"123121420","tip":"提示","name":"2019湖北咸宁市直事业单位招聘工作人员公告（教师39名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123121420&title=公告&type=1"},{"id":"123121415","tip":"提示","name":"2019湖北省省直事业单位统一公开招聘工作人员公告（教师82名）","url":"sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/123121415&title=公告&type=1"}]
         * middleBanner : [{"name":"预约点评","url":"sxapp://goRevieworder","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/7a9f84b095cae965683416ac8ed5e28b.jpg"},{"name":"每日一练","url":"sxapp://goWebView?url=http://apih5.sx1211.cn/news/index/type/2&title=每日一练&type=2","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/a2431c24f22dda8e03a950d64383e6ab.png"}]
         * recommend : [{"id":"88584368","name":"19招教笔试协议班","price":"价格:¥7980.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/0758b3e75aa3ca6ed6cd9f512d979bba.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"7980.00","url":"sxapp://goProductDetail?objtype=live&id=88584368"},{"id":"139513663","name":"19小学资格证笔试单科+面试协议班","price":"价格:¥2480.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/191de4bd99f8693382a8931977cf9973.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"2480.00","url":"sxapp://goProductDetail?objtype=live&id=139513663"},{"id":"140806023","name":"2019教资面试协议班（英语）","price":"价格:¥1980.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/37c43d0a9022dd2fdcff561b2edd7a13.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"1980.00","url":"sxapp://goProductDetail?objtype=live&id=140806023"},{"id":"94060810","name":"19状元考编协议班","price":"价格:¥29800.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/8fa2b25538315349dee5c31bdfdf256b.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"29800.00","url":"sxapp://goProductDetail?objtype=live&id=94060810"},{"id":"88584755","name":"19招教笔试+面试协议班","price":"价格:¥16800.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/03c1c483b055ee5f8b7e02cc3c365e25.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"16800.00","url":"sxapp://goProductDetail?objtype=live&id=88584755"},{"id":"101071510","name":"招教/特岗笔试直播协议班定金","price":"价格:¥1000.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/3b9d217320a9f5586b373b7047d993f6.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"1000.00","url":"sxapp://goProductDetail?objtype=live&id=101071510"},{"id":"147074218","name":"2019教资面试协议班（幼儿园）","price":"价格:¥1980.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/5cbf045a880940696f3f7cbf1af1b3c4.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"1980.00","url":"sxapp://goProductDetail?objtype=live&id=147074218"},{"id":"145864748","name":"19幼儿资格证两科循环班","price":"价格:¥980.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/24a2095dd8e74e0fc31246386ebd62d6.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"980.00","url":"sxapp://goProductDetail?objtype=live&id=145864748"},{"id":"139514020","name":"19中学资格证笔试单科+面试协议班","price":"价格:¥2480.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/66aaa89e2ac0a98ab94bbc854c4ae5d3.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"2480.00","url":"sxapp://goProductDetail?objtype=live&id=139514020"},{"id":"141120208","name":"19教资笔试循环班（单科）","price":"价格:¥490.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/8ae795a4c6a022f43ba196c18e078f2a.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"490.00","url":"sxapp://goProductDetail?objtype=live&id=141120208"},{"id":"158941341","name":"2019教资面试协议班（美术）","price":"价格:¥1980.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/d6d87f90e4e688f2132342f758992abe.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"1980.00","url":"sxapp://goProductDetail?objtype=live&id=158941341"},{"id":"158933205","name":"7.31 河北笔面补费8520","price":"价格:¥8520.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/019d4f49755306dff63fa3b92295f88d.300_150.jpg","type":"video","tip":"教师之路山香起步","tag":"可试听","priceStr":"8520.00","url":"sxapp://goProductDetail?objtype=video&id=158933205"},{"id":"160421810","name":"2019教资面试协议班（数学）","price":"价格:¥1980.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/4bda0ff0d4e94b313d87ae975c7bbe6d.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"1980.00","url":"sxapp://goProductDetail?objtype=live&id=160421810"},{"id":"160885284","name":"1000定金（限部分省份使用）","price":"价格:¥1000.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/e2be2306e1952f123e9cb476f6128362.300_150.jpg","type":"video","tip":"教师之路山香起步","tag":"可试听","priceStr":"1000.00","url":"sxapp://goProductDetail?objtype=video&id=160885284"},{"id":"160884947","name":"500定金（限部分省份使用）","price":"价格:¥500.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/4eb0ab67c2fd8d584807857eac8ccdde.300_150.jpg","type":"video","tip":"教师之路山香起步","tag":"可试听","priceStr":"500.00","url":"sxapp://goProductDetail?objtype=video&id=160884947"},{"id":"158877771","name":"7.31 河北大礼包+公基补费","price":"价格:¥992.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/53fd3a029e7ea52ec63ca31ae21141c6.300_150.jpg","type":"video","tip":"教师之路山香起步","tag":"可试听","priceStr":"992.00","url":"sxapp://goProductDetail?objtype=video&id=158877771"},{"id":"147878345","name":"特岗笔面协议班","price":"价格:¥19800.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/55008815e134c47901342dcd3b74d878.300_150.png","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"19800.00","url":"sxapp://goProductDetail?objtype=live&id=147878345"},{"id":"139514230","name":"19幼儿资格证笔试单科+面试协议班","price":"价格:¥2480.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/0d7abeacac56133aea9e727d5200ff32.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"2480.00","url":"sxapp://goProductDetail?objtype=live&id=139514230"},{"id":"163174040","name":"20广东笔面协议班补费7000元","price":"价格:¥7000.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/66ba4b636e580c225305ec204f0858de.300_150.jpg","type":"video","tip":"教师之路山香起步","tag":"可试听","priceStr":"7000.00","url":"sxapp://goProductDetail?objtype=video&id=163174040"},{"id":"101071592","name":"招教/特岗笔面直播协议班定金","price":"价格:¥1000.00","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/aa3b40404cbd6af218bfac918ef19361.300_150.jpg","type":"live","tip":"教师之路山香起步","tag":"提供回放","priceStr":"1000.00","url":"sxapp://goProductDetail?objtype=live&id=101071592"}]
         * contact : {"qq":"sxapp://goWebView?url=http://wanglei.new.api.sx1211.ea-crm.com/Index/kefu&title=客服消息&type=1","tel":"tel:4006003363"}
         * noticeListUrl : sxapp://goWebView?url=http://wanglei.new.api.sx1211.ea-crm.com/news/wapNewsList/categoryId/0/secCategoryId/0/areaId/1&title=简章&type=2
         * isShowVideo : 0
         * isRevieworder : 0
         */

        private ContactBean contact;
        private String noticeListUrl;
        private String isShowVideo;
        private String isRevieworder;
        private List<TopBannerBean> topBanner;
        private List<FunctionButtonBean> functionButton;
        private List<NoticeBean> notice;
        private List<MiddleBannerBean> middleBanner;
        private List<RecommendBean> recommend;

        public ContactBean getContact() {
            return contact;
        }

        public void setContact(ContactBean contact) {
            this.contact = contact;
        }

        public String getNoticeListUrl() {
            return noticeListUrl;
        }

        public void setNoticeListUrl(String noticeListUrl) {
            this.noticeListUrl = noticeListUrl;
        }

        public String getIsShowVideo() {
            return isShowVideo;
        }

        public void setIsShowVideo(String isShowVideo) {
            this.isShowVideo = isShowVideo;
        }

        public String getIsRevieworder() {
            return isRevieworder;
        }

        public void setIsRevieworder(String isRevieworder) {
            this.isRevieworder = isRevieworder;
        }

        public List<TopBannerBean> getTopBanner() {
            return topBanner;
        }

        public void setTopBanner(List<TopBannerBean> topBanner) {
            this.topBanner = topBanner;
        }

        public List<FunctionButtonBean> getFunctionButton() {
            return functionButton;
        }

        public void setFunctionButton(List<FunctionButtonBean> functionButton) {
            this.functionButton = functionButton;
        }

        public List<NoticeBean> getNotice() {
            return notice;
        }

        public void setNotice(List<NoticeBean> notice) {
            this.notice = notice;
        }

        public List<MiddleBannerBean> getMiddleBanner() {
            return middleBanner;
        }

        public void setMiddleBanner(List<MiddleBannerBean> middleBanner) {
            this.middleBanner = middleBanner;
        }

        public List<RecommendBean> getRecommend() {
            return recommend;
        }

        public void setRecommend(List<RecommendBean> recommend) {
            this.recommend = recommend;
        }

        public static class ContactBean {
            /**
             * qq : sxapp://goWebView?url=http://wanglei.new.api.sx1211.ea-crm.com/Index/kefu&title=客服消息&type=1
             * tel : tel:4006003363
             */

            private String qq;
            private String tel;

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
        }

        public static class TopBannerBean {
            /**
             * name : 河南
             * url : sxapp://goWebView?url=https://ke.qq.com/course/356651&title=河南&type=2
             * imgUrl : http://wanglei.new.images.sx1211.ea-crm.com/8fcfc4830bdc52726dea124ca050d929.png
             */

            private String name;
            private String url;
            private String imgUrl;

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

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }

        public static class FunctionButtonBean {
            /**
             * name : 点播
             * imgUrl : http://wanglei.new.statics.sx1211.ea-crm.com/public/api/images/functionbutton/video.png?timestamp=e1469006018800
             * url : sxapp://goProductTabList?objtype=video
             */

            private String name;
            private String imgUrl;
            private String url;

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

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class NoticeBean {
            /**
             * id : 140955603
             * tip : 提示
             * name : 抢鸭！安徽招教政策解读+速效通关礼包，0元助你成功上岸！
             * url : sxapp://goWebView?url=http://wanglei.new.apih5.sx1211.ea-crm.com/news/detail/id/140955603&title=公告&type=1
             */

            private String id;
            private String tip;
            private String name;
            private String url;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
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
        }

        public static class MiddleBannerBean {
            /**
             * name : 预约点评
             * url : sxapp://goRevieworder
             * imgUrl : http://wanglei.new.images.sx1211.ea-crm.com/7a9f84b095cae965683416ac8ed5e28b.jpg
             */

            private String name;
            private String url;
            private String imgUrl;

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

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }
        }

        public static class RecommendBean {
            /**
             * id : 88584368
             * name : 19招教笔试协议班
             * price : 价格:¥7980.00
             * imgUrl : http://wanglei.new.images.sx1211.ea-crm.com/0758b3e75aa3ca6ed6cd9f512d979bba.300_150.jpg
             * type : live
             * tip : 教师之路山香起步
             * tag : 提供回放
             * priceStr : 7980.00
             * url : sxapp://goProductDetail?objtype=live&id=88584368
             */

            private String id;
            private String name;
            private String price;
            private String imgUrl;
            private String type;
            private String tip;
            private String tag;
            private String priceStr;
            private String url;

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

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getTip() {
                return tip;
            }

            public void setTip(String tip) {
                this.tip = tip;
            }

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getPriceStr() {
                return priceStr;
            }

            public void setPriceStr(String priceStr) {
                this.priceStr = priceStr;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

}