package com.android.tool.utility;


import com.android.tool.BaseApplication;

public class AppConfig {
    public static final int RECYCLERVIEW_ANIM = 1;//全局RecyclerView动画控制
    public static final int SPLASH_TIME = 1000;//启动页倒计时时间
    public static final int SPLASH_MILLISINFUTURE_TIME = 5000;//启动广告倒计时时间
    public static final int CODE_MILLISINFUTURE_TIME = 60000;//发送验证码倒计时时间

    public static class Main {
        public final static int contentTextSize = 18;
        public final static int titleSize = 16;
        public final static int subCalSize = 14;
        public final static String CODE = "code";
        public final static String FIRST_PAGE = "firstPage";
        public final static String LIVE = "live";
        public final static String VIDEO = "video";
        public final static String PACKAGE = "package";
    }

    /**
     * 收藏、已收藏
     */
    public static class IsCollection {
        public static final String OBJ_TYPE = "objType";
        public static final String BOJ_ID = "id";
        public static final String IS_COLLECTION = "isCollection";
        public static final String TYPE_ID = "typeId";
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String VIDEO = "video";//video点播 live直播
        public static final String LIVE = "live";//video点播 live直播
        public static final String PACKAGE = "package";//video点播 live直播
    }

    /**
     * 支付
     */
    public static class Pay {
        public static final String ORDER_ID = "orderId";
        public static final String ADDRESS_ID = "addressId";
        public static final String CURRENCY = "currency";
        public static final String COUPONID = "couponId";
        public static final String UNIONKEY = "unionkey";
        public static final String PAY_WAY = "paymentWay";
        // APP_ID 替换为你的应用从官方网站申请到的合法appId
        public static final String APP_ID = BaseApplication.WX_APPID;
    }

    /**
     * 登录、注册、重置密码、设置密码
     */
    public static class LRAll {
        public static final String MOBILE = "mobile";
        public static final String OBJ_TYPE = "objType";
        public static final String VERIFY_CODE = "verifyCode";
        public static final String PASS_WORD = "password";
        public static final String USER_PWD = "userPwd";
        public static final String PASSWORD_T = "passwordT";

        public static final String REGISTER = "register";
        public static final String LOGIN = "login";
        public static final String FINDPWD = "findpwd";//找回密码
        public static final String REVISEPWD = "revisepwd";//重置密码

        public static final String NICKNAME = "nickName";
        public static final String ROLE = "role";
        public static final String CAPTCHA = "captcha";
    }

    /**
     * pictureId	是	string	头像id
     * nickName	是	string	昵称
     * birthday	否	string	生日
     * realName	否	string	真实姓名
     * role	否	string	考试类型
     * province	否	string	报考地区
     * subject	否	string	报考专业
     * 修改和获取个人信息
     */
    public static class GetUserInfo {
        public static final String NICK_NAME = "nickName";
        public static final String PICTURE_ID = "pictureId";
        public static final String BIRTHDAY = "birthday";
        public static final String REAL_NAME = "realName";
        public static final String SEX = "sex";
        public static final String ROLE = "role";
        public static final String SUBJECT = "subject";

        public static final String PROVICE_ID = "provinceId";
        public static final String CITY_ID = "cityId";
        public static final String DISTRICT_ID = "districtId";
        public static final String GRADE = "grade";

    }

    /**
     * 版本更新
     */
    public static class Version {
        public static final String CURENT_VERSION_CODE = "curentVersionCode";
    }

    /**
     * 消息列表
     */
    public static class MessageList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String KEYWORD = "keyword";//当前页码
    }
    /**
     * 收藏列表
     */
    public static class CollectionList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
    }

    /**
     * 课程历史列表
     */
    public static class CourseHistoryList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
    }

    /**
     * 消息列表
     */
    public static class OrderList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String ORDER_ID = "orderId";
        public static final String STATUS = "status";
    }

    /**
     * 所有类型列表
     */
    public static class TypeList {
        public static final String PRODUCT_TYPE = "productType";
        public static final String TYPE = "type";
        public static final String GRADE = "grade";
    }

    /**
     * 所有类型列表
     */
    public static class OndemandList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String TYPE = "type";//商品类型
        public static final String AREAID = "areaId";//地区id
        public static final String EXAMINEWAY = "examineWay";//考试渠道 1面试 2笔试
        public static final String SUBJECTID = "subjectid";//科目
        public static final String GRADE = "grade";//学段
        public static final String KEYWORD = "keyword";//关键字
        public static final String ID = "id";

    }

    /**
     * 所有类型列表
     */
    public static class LiveList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String TYPE = "type";//商品类型
        public static final String AREAID = "areaId";//地区id
        public static final String EXAMINEWAY = "examineWay";//考试渠道 1面试 2笔试
        public static final String SUBJECTID = "subjectid";//科目
        public static final String GRADE = "grade";//学段
        public static final String ID = "id";

    }

    /**
     * 所有类型列表
     */
    public static class BigPackageList {
        public static final String PACKAGE_ID = "packageId";

    }

    /**
     * 首页
     * productType	否	int	商品类型编号
     * examineWay	否	int	考试类型编号
     * grade	否	int	学段编号
     * subject	否	int	考试科目编号
     * areaId	否	int	地区编号
     */
    public static class getHomePage {
        public static final String PRODUCT_TYPE = "productType";//商品类型
        public static final String AREAID = "areaId";//地区id
        public static final String EXAMINEWAY = "examineWay";//考试渠道 1面试 2笔试
        public static final String SUBJECT = "subject";//科目
        public static final String GRADE = "grade";//学段

    }

    /**
     * 所有类型列表
     * objType	是	string	商品类型 (product 商品 package大礼包 )
     * objId	是	string	商品id
     */
    public static class getAddCar {
        public static final String OBJ_TYPE = "objType";
        public static final String OBJ_ID = "objId";
        public static final String PRODUCT = "product";
        public static final String PACKAGE = "package";

    }

    /**
     * 搜索
     * type	否	int	商品类型编号 教师资格 教师招聘 特岗 这些
     * examineWay	否	int	考试类型编号 笔试 面试
     * grade	否	int	学段编号
     * subject	否	int	考试科目编号
     * areaId	否	int	地区编号
     * keyword	否	int	关键字
     * pageIndex	否	int	页码
     */
    public static class getSearch {
        public static final String TYPE = "type";//商品类型
        public static final String AREAID = "areaId";//地区id
        public static final String EXAMINEWAY = "examineWay";//考试渠道 1面试 2笔试
        public static final String SUBJECT = "subject";//科目
        public static final String GRADE = "grade";//学段
        public static final String KEYWORD = "keyword";
        public static final String PAGEINDEX = "pageIndex";

    }

    public static class getCouserComments {
        public static final String CONTENTS = "contents";
        public static final String IS_ANONYMOUS = "isAnonymous";

    }

    /**
     * 啓動广告
     */
    public static class getAdvertising {
        public static final String TYPE = "type";

    }

    /**
     * 学习金使用记录
     */
    public static class getCurrencyList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
    }

    /**
     * 兑换学习金
     */
    public static class getCashCurrency {
        public static final String CARD_NUMBER = "cardNumber";
        public static final String CARD_PASSWORD = "cardPassword";
    }

    /**
     * 兑换优惠券
     */
    public static class getCashCoupons {
        public static final String CARD_NUMBER = "number";
    }

    /**
     * 消息列表
     */
    public static class CashProductList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
    }

    /**
     * 地址列表 詳情
     * realName	是	string	收货人姓名
     * provinceId	是	string	省id
     * cityId	是	string	市id
     * districtId	是	string	县id
     * mobile	是	string	手机号 联系方式
     * address	是	string	详细地址
     */
    public static class AddressList {
        public static final String ADDRESS_ID = "addressId";

        public static final String ID = "id";
        public static final String REAL_NAME = "realName";
        public static final String PROVINCE_ID = "provinceId";
        public static final String CITY_ID = "cityId";
        public static final String DISTRICT_ID = "districtId";
        public static final String MOBILE = "mobile";
        public static final String ADDRESS = "address";

    }


    public static class ChapterTestList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String PRODUCT_TYPE = "productType";
        public static final String GRADE = "grade";
        public static final String SUBJECTS = "subjectId";
        public static final String CHAPTER_ID = "chapterId";
        public static final String KNOW_LEDGE = "knowledge";
        public static final String AREAID = "areaId";
        public static final String SUBJECT_ID = "subjectId";
        public static final String EXAMINE_TYPE = "examineType";

        public static final String EXAMINE_ID = "examineId";
        public static final String ERROR_TYPE = "errorType";
        public static final String CONTENTS = "contents";

        public static final String IS_COLLECTION = "isCollection";


    }

    /**
     * id	是	string	练习试卷id
     * totalCount	是	string	总题数
     * totalScore	是	string	总分
     * name	是	string	练习试卷名称
     * productType	是	string	试卷分类
     * grade	是	string	学段
     * subjects	是	string	科目
     * difficultys	是	string	困难等级
     * singleScore	是	string	单选题分数
     * mutipleScore	是	string	多选题分数
     * judgeScore	是	string	判断题分数
     * fillingScore	是	string	填空题分数
     * singleContents	是	string	单选题内容
     * multipleContents	是	string	多选题内容
     * judgeContents	是	string	判断题内容
     * fillingContents	是	string	填空题内容
     */
    public static class SubmitResults {
        public static final String ID = "id";
        public static final String TOTAL_COUNT = "totalCount";
        public static final String TOTAL_SCORE = "totalScore";
        public static final String NAME = "name";
        public static final String PRODUCT_TYPE = "productType";
        public static final String GRADE = "grade";
        public static final String SUBJECTS = "subjects";
        public static final String AREA_ID = "areaId";
        public static final String CHAPTER_ID = "chapterId";

        public static final String SINGLE_SCORE = "singleScore";
        public static final String MUTIPLE_SCORE = "mutipleScore";
        public static final String JUDGE_SCORE = "judgeScore";
        public static final String FILLING_SCORE = "fillingScore";

        public static final String JIANDA_SCORE = "jiandaScore";
        public static final String CAILIAO_SCORE = "cailiaoScore";
        public static final String ZUOWEN_SCORE = "zuowenScore";
        public static final String HUODONG_SCORE = "huodongScore";
        public static final String MINGCI_SCORE = "mingciScore";
        public static final String LUNSHU_SCORE = "lunshuScore";
        public static final String JIAOXUE_SCORE = "jiaoxueScore";

        public static final String SINGLE_CONTENTS = "singleContents";
        public static final String MUTIPLE_CONTENTS = "multipleContents";
        public static final String JUDGE_CONTENTS = "judgeContents";
        public static final String FILLING_CONTENTS = "fillingContents";
        public static final String JIANDA_CONTENTS = "jiandaContents";//
        public static final String CAILIAO_CONTENTS = "cailiaoContents";//
        public static final String ZUOWEN_CONTENTS = "zuowenContents";//
        public static final String HUODONG_CONTENTS = "huodongContents";//
        public static final String MINGCI_CONTENTS = "mingciContents";//
        public static final String LUNSHU_CONTENTS = "lunshuContents";//
        public static final String JIAOXUE_CONTENTS = "jiaoxueContents";//

    }

    /**
     * 社区
     */
    public static class Community {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String TOPIC_ID = "topicId";//话题id
        public static final String TYPE = "type";//话题id
    }

    /**
     * 评论列表
     */
    public static class WeiBoList {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String WEIBO_ID = "weiboId";
        public static final String COMMENT_ID = "commentId";
    }

    /**
     * 社区
     */
    public static class InvitationPH {
        public static final String PAGE_INDEX = "pageIndex";//当前页码
        public static final String USER_ID = "userId";
    }

    /**
     * id	是	string	微博id/评论id
     * type	是	string	点赞类型 1微博 2评论
     * status	否	string	0添加点赞 1取消点赞
     * 微博/评论点赞
     */
    public static class WeiBoPraise {
        public static final String ID = "id";
        public static final String TYPE = "type";
        public static final String STATUS = "status";
    }

    /**
     * 关注
     */
    public static class AddCollection {
        public static final String ID = "weiboId";
        public static final String STATUS = "status";
    }

    /**
     * 关注
     */
    public static class AddAttention {
        public static final String USERID = "userId";
        public static final String STATUS = "status";
    }

    /**
     * 微博详情
     */
    public static class WeiBoDetails {
        public static final String ID = "weiboId";
    }

    /**
     * 微博详情
     */
    public static class FileVideoImage {
        public static final String PIC = "pic";
        public static final String FILE = "file";
    }

    /**
     * 发布微博
     */
    public static class UploadWeiBo {
        public static final String TOPIC_ID = "topicId";
        public static final String CONTENTS = "contents";
    }

    /**
     * 刪除微博
     */
    public static class DelWeiBo {
        public static final String ID = "weiboId";
    }

    /**
     * 刪除评论
     */
    public static class DelComments {
        public static final String ID = "commentId";
    }

    /**
     * 个人主页
     */
    public static class HomePage {
        public static final String USER_ID = "userId";
        public static final String PAGE_INDEX = "pageIndex";//当前页码
    }

    /**
     * weiboId	是	string	微博id
     * commentType	是	string	1评论 2回复
     * parentId	否	string	父级id(第一级的评论id) PS:一级评论 父级id为0
     * toUserId	否	string	回复用户id
     * $toCommentId	否	string	对谁回复 二级回复才有(一级回复的id)
     * contents	否	string	内容
     * 评论
     */
    public static class AddComment {
        public static final String WEIBO_ID = "weiboId";
        public static final String PARENT_ID = "parentId";
        public static final String TO_USER_ID = "toUserId";
        public static final String TO_COMMENT_ID = "toCommentId";
        public static final String CONTENTS = "content";

    }

    public static class Signature {
        public static final String SIGNATURE = "signature";
    }

    /**
     * 举报
     */
    public final static class Report {
        public static final String IS_GET_DATA = "isGetData";
        public static final String WEIBO_ID = "objId";
        public static final String OBJ_TYPE = "objType";
        public static final String TYPE = "type";
        public static final String CONTENTS = "contents";
    }

    /**
     * 意见反馈
     */
    public final static class FeekBack {
        public static final String FEEDBACKTYPE = "feedBackType";
        public static final String CONTENTS = "contents";
        public static final String PICTUREIDS = "pictureIds";
        public static final String MOBILE = "mobile";

    }

    /**
     * name	是	string	实名
     * idNumber	是	string	身份证号码
     * cardFrontPictureId	否	string	身份证国徽面
     * cardBackPictureId	是	string	身份证头像面
     * videoPictureId	是	string	视频封面
     * videoId	是	string	视频
     */
    public final static class ApplyWeiBo {
        public static final String NAME = "name";
        public static final String IDNUMBER = "idNumber";
        public static final String CARDFRONTPICTUREID = "cardFrontPictureId";
        public static final String CARDBACKPICTUREID = "cardBackPictureId";
        public static final String VIDEOPICTUREID = "videoPictureId";
        public static final String VIDEOID = "videoId";
    }

    /**
     * realName	是	string	真实姓名
     * mobile	是	string	手机号
     * bookOrderId	是	string	订单号
     * grade	是	string	学段
     * beginDate	是	string	开始日期
     * endDate	是	string	结束日期
     * subject	是	string	学科
     * area	是	string	地区
     * type	是	string	分类
     */
    public final static class AppintmentAdd {
        public static final String NAME = "realName";
        public static final String PHONE = "mobile";
        public static final String ORDER = "bookOrderId";
        public static final String GRAND = "grade";
        public static final String SUBJECT = "subject";
        public static final String AREA = "area";
        public static final String BDATE = "beginDate";
        public static final String BEGIN_AM_OR_PM = "beginAmOrPm";
        public static final String ENDDATE = "endDate";
        public static final String END_AM_OR_PM = "endAmOrPm";
        public static final String TYPE = "type";
        public static final String PICTURE_ID = "pictureId";
    }

    /**
     * 关注
     */
    public static class setVideoProgress {
        public static final String PRODUCT_ID = "productId";
        public static final String VIDEO_ID = "videoId";
        public static final String PROGRESS = "progress";
        public static final String INITPROGRESS = "initProgress";
    }

    /**
     * 课程分组列表
     * provinceId	是	string	省份编号
     * type	是	string	类型 1.教师招聘考试2.教师资格证3.特岗
     * pageIndex	否	string	页码
     */
    public static class CourseSpuList {
        public static final String PROVINCE_ID = "provinceId";
        public static final String TYPE = "type";
        public static final String PAGEINDEX = "pageIndex";
    }

    /**
     * 课程分组列表
     * courseSpuId	是	string	分组编号
     * pageIndex	否	string	页码
     */
    public static class SpuProductList {
        public static final String COURSESPU_ID = "courseSpuId";
        public static final String PAGEINDEX = "pageIndex";
        public static final String OBJTYPE = "objType";
    }

}
