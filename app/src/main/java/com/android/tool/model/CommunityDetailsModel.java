package com.android.tool.model;

import com.android.tool.ui.community.browse.helper.ImageType;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author York(wuchunyuan)
 * @Created 2018/11/28.
 */
public class CommunityDetailsModel {


    /**
     * weiboId : 49688553
     * isMyself : 0
     * userId : 49432251
     * diyName : 1景天
     * userImageUrl : http://wanglei.new.images.sx1211.ea-crm.com/af735798f026f4afd2b4a1c8a8621474.40-40.gif
     * isAttention : 0
     * contents : [{"type":"text","value":"现在央行就算发动引擎，全开水龙头，水也放不出去，近段时间（降准、投放MLF）的动作使得银行体系内积蓄了大量流动性，基本形成了堰塞湖。现在，货币之堵塞在商业银行流不到实体经济。所有钱都在银行里面贷不出去，拆借利率暴跌，余额宝不跌才怪。","thumbValue":"现在央行就算发动引擎，全开水龙头，水也放不出去，近段时间（降准、投放MLF）的动作使得银行体系内积蓄了大量流动性，基本形成了堰塞湖。现在，货币之堵塞在商业银行流不到实体经济。所有钱都在银行里面贷不出去，拆借利率暴跌，余额宝不跌才怪。","ext":{"width":"0","height":"0"}},{"type":"image","value":"http://wanglei.new.images.sx1211.ea-crm.com/af3454854f714fa320c9fa7c4e22d84e.png","thumbValue":"http://wanglei.new.images.sx1211.ea-crm.com/af3454854f714fa320c9fa7c4e22d84e.png","ext":{"width":"641","height":"489"}},{"type":"text","value":"但是市场上的钱荒还是依然钱荒，所有的钱都在银行堵着，银行钱多的花不完，导致MLF的利率比拆借的还高，放的水直接倒灌央行了。这个水放的把央行自己呛着了。","thumbValue":"但是市场上的钱荒还是依然钱荒，所有的钱都在银行堵着，银行钱多的花不完，导致MLF的利率比拆借的还高，放的水直接倒灌央行了。这个水放的把央行自己呛着了。","ext":{"width":"0","height":"0"}},{"type":"image","value":"http://wanglei.new.images.sx1211.ea-crm.com/82629beebd11eb8281188b63887ed12c.png","thumbValue":"http://wanglei.new.images.sx1211.ea-crm.com/82629beebd11eb8281188b63887ed12c.png","ext":{"width":"610","height":"972"}},{"type":"text","value":"大家对放水肯定些误解，其实放水不是央行想放就能放的，他还需要有人借款这个钱才能放出去，也就是说需要银行和借款人共同努力才能算把这个水放出去了。银行不是做慈善的，水放出去是要收回来的！啥？放给p2p？小微企业？炒房的？炒股的？买次级债券？濒危倒闭的企业。银行是还嫌自己坏账不够多么？银行傻么，固定资产收益率明显下降，自己信贷也要收缩，毕竟坏账了，是要去追的。所以说印钱不是万能的，中国能投的都投了，投啥产能都过剩，有啥收益率？出国又出不去，只能投基建才能维持生活这样子。愿意借钱的p2p跪着求银行也不会给，老老实实干事业的发现没有机会赚钱也就不借钱扩大规模了。这个时候放水还有用么？","thumbValue":"大家对放水肯定些误解，其实放水不是央行想放就能放的，他还需要有人借款这个钱才能放出去，也就是说需要银行和借款人共同努力才能算把这个水放出去了。银行不是做慈善的，水放出去是要收回来的！啥？放给p2p？小微企业？炒房的？炒股的？买次级债券？濒危倒闭的企业。银行是还嫌自己坏账不够多么？银行傻么，固定资产收益率明显下降，自己信贷也要收缩，毕竟坏账了，是要去追的。所以说印钱不是万能的，中国能投的都投了，投啥产能都过剩，有啥收益率？出国又出不去，只能投基建才能维持生活这样子。愿意借钱的p2p跪着求银行也不会给，老老实实干事业的发现没有机会赚钱也就不借钱扩大规模了。这个时候放水还有用么？","ext":{"width":"0","height":"0"}},{"type":"video","value":"http://wanglei.new.images.sx1211.ea-crm.com/file/f174e6a2993dc3d96f3919c6d45640ce.mp4","thumbValue":"http://wanglei.new.images.sx1211.ea-crm.com/296c22da65606ab60859e6584101da26.300-200.jpg","ext":{"width":"300","height":"200"}}]
     * isPraise : 0
     * viewCount : 0
     * commentCount : 0
     * praiseCount : 0
     * platForm : web
     * createTimeStr : 2018.11.23 15:31
     * advertisementInfo : {"imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/296c22da65606ab60859e6584101da26.500-100.jpg","url":"sxapp://goWebView?url=http://www.sx1211.cn&title=北京园丁园&type=1"}
     */

    private String weiboId;
    private String isMyself;
    private String userId;
    private String diyName;
    private String userImageUrl;
    private String isAttention;
    private String isCollection;
    private String isPraise;
    private String viewCount;
    private String commentCount;
    private String praiseCount;
    private String platForm;
    private String createTimeStr;
    private String contentsUrl;
    private AdvertisementInfoBean advertisementInfo;

    private List<ContentsBean> contents;
    /**
     * shareInfo : {"title":"山香园丁园","contents":"\u201c山香老师\u201dAPP是山香教育为考生精心打造的一款适用于参加教师资格证、教师招聘考试的掌中备考学习平台。提供教师资格证笔试面试、教师招聘考试笔试面试、题库练习、直播辅导课程 、点播辅导课程、错题纠错，帮助考生实现掌中备考，随时随地学习，助您成师！乘山香翅膀 圆教师梦想！","imgUrl":"http://wanglei.new.images.sx1211.ea-crm.com/7ef1f080ccecc24907868802a557bf22.50-50.png","url":"http://wanglei.new.weixin.sx1211.ea-crm.com","specialContents":"#山香教育#山香园丁园 http://wanglei.new.weixin.sx1211.ea-crm.com"}
     */

    private ShareInfoBean shareInfo;
    private String isAuth;

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getContentsUrl() {
        return contentsUrl;
    }

    public void setContentsUrl(String contentsUrl) {
        this.contentsUrl = contentsUrl;
    }

    public String getIsCollection() {
        return isCollection;
    }

    public void setIsCollection(String isCollection) {
        this.isCollection = isCollection;
    }

    public String getWeiboId() {
        return weiboId;
    }

    public void setWeiboId(String weiboId) {
        this.weiboId = weiboId;
    }

    public String getIsMyself() {
        return isMyself;
    }

    public void setIsMyself(String isMyself) {
        this.isMyself = isMyself;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDiyName() {
        return diyName;
    }

    public void setDiyName(String diyName) {
        this.diyName = diyName;
    }

    public String getUserImageUrl() {
        return userImageUrl;
    }

    public void setUserImageUrl(String userImageUrl) {
        this.userImageUrl = userImageUrl;
    }

    public String getIsAttention() {
        return isAttention;
    }

    public void setIsAttention(String isAttention) {
        this.isAttention = isAttention;
    }

    public String getIsPraise() {
        return isPraise;
    }

    public void setIsPraise(String isPraise) {
        this.isPraise = isPraise;
    }

    public String getViewCount() {
        return viewCount;
    }

    public void setViewCount(String viewCount) {
        this.viewCount = viewCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }

    public String getPraiseCount() {
        return praiseCount;
    }

    public void setPraiseCount(String praiseCount) {
        this.praiseCount = praiseCount;
    }

    public String getPlatForm() {
        return platForm;
    }

    public void setPlatForm(String platForm) {
        this.platForm = platForm;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }

    public AdvertisementInfoBean getAdvertisementInfo() {
        return advertisementInfo;
    }

    public void setAdvertisementInfo(AdvertisementInfoBean advertisementInfo) {
        this.advertisementInfo = advertisementInfo;
    }

    public List<ContentsBean> getContents() {
        return contents;
    }

    public void setContents(List<ContentsBean> contents) {
        this.contents = contents;
    }

    public ShareInfoBean getShareInfo() {
        return shareInfo;
    }

    public void setShareInfo(ShareInfoBean shareInfo) {
        this.shareInfo = shareInfo;
    }

    public static class AdvertisementInfoBean {
        /**
         * imgUrl : http://wanglei.new.images.sx1211.ea-crm.com/296c22da65606ab60859e6584101da26.500-100.jpg
         * url : sxapp://goWebView?url=http://www.sx1211.cn&title=北京园丁园&type=1
         */

        private String imgUrl;
        private String url;

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

    public static class ContentsBean implements MultiItemEntity {
        /**
         * type : text
         * value : 现在央行就算发动引擎，全开水龙头，水也放不出去，近段时间（降准、投放MLF）的动作使得银行体系内积蓄了大量流动性，基本形成了堰塞湖。现在，货币之堵塞在商业银行流不到实体经济。所有钱都在银行里面贷不出去，拆借利率暴跌，余额宝不跌才怪。
         * thumbValue : 现在央行就算发动引擎，全开水龙头，水也放不出去，近段时间（降准、投放MLF）的动作使得银行体系内积蓄了大量流动性，基本形成了堰塞湖。现在，货币之堵塞在商业银行流不到实体经济。所有钱都在银行里面贷不出去，拆借利率暴跌，余额宝不跌才怪。
         * ext : {"width":"0","height":"0"}
         */

        private String type;
        private String value;
        private String thumbValue;
        private int pos;
        private ExtBean ext;

        public int getPos() {
            return pos;
        }

        public void setPos(int pos) {
            this.pos = pos;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getThumbValue() {
            return thumbValue;
        }

        public void setThumbValue(String thumbValue) {
            this.thumbValue = thumbValue;
        }

        public ExtBean getExt() {
            return ext;
        }

        public void setExt(ExtBean ext) {
            this.ext = ext;
        }

        @Override
        public int getItemType() {
            if (getType().equals(ImageType.TEXT)) {
                return ImageType.TEXTS;
            } else if (getType().equals(ImageType.GIF)) {
                return ImageType.GIFS;
            } else if (getType().equals(ImageType.LONG)) {
                return ImageType.LONGS;
            } else if (getType().equals(ImageType.VIDEO)) {
                return ImageType.VIDEOS;
            } else {
                return ImageType.IMAGES;
            }
        }

        public static class ExtBean {
            /**
             * width : 0
             * height : 0
             */

            private String width;
            private String height;

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }
        }
    }

    public static class ShareInfoBean {
        /**
         * title : 山香园丁园
         * contents : “山香老师”APP是山香教育为考生精心打造的一款适用于参加教师资格证、教师招聘考试的掌中备考学习平台。提供教师资格证笔试面试、教师招聘考试笔试面试、题库练习、直播辅导课程 、点播辅导课程、错题纠错，帮助考生实现掌中备考，随时随地学习，助您成师！乘山香翅膀 圆教师梦想！
         * imgUrl : http://wanglei.new.images.sx1211.ea-crm.com/7ef1f080ccecc24907868802a557bf22.50-50.png
         * url : http://wanglei.new.weixin.sx1211.ea-crm.com
         * specialContents : #山香教育#山香园丁园 http://wanglei.new.weixin.sx1211.ea-crm.com
         */

        private String title;
        @SerializedName("contents")
        private String contentsX;
        private String imgUrl;
        private String url;
        private String specialContents;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContentsX() {
            return contentsX;
        }

        public void setContentsX(String contentsX) {
            this.contentsX = contentsX;
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
