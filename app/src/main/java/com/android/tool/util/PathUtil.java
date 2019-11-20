package com.android.tool.util;

import android.annotation.SuppressLint;
import android.os.Environment;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/3/2 11:27
 */
public class PathUtil {

    //public static final String VERSION_TIME = "20190422";//版本时间
    //public static final String VERSION_TIME = "20190508";//版本时间
    //public static final String VERSION_TIME = "20190520";//版本时间
    //public static final String VERSION_TIME = "20190529";//版本时间
    //public static final String VERSION_TIME = "20190730";//版本时间
    //public static final String VERSION_TIME = "20190816";//版本时间
    //public static final String VERSION_TIME = "20190910";//版本时间
      public static final String VERSION_TIME = "20191030";//版本时间


    public static final boolean DEBUG_LOG = true;

    public static final boolean DEBUG = false;

    //接口域名
    public static final String url = DEBUG ? "https://api1.sx1211.cn"
            : "http://wanglei.new.api.sx1211.ea-crm.com";
    //图片域名
    public static final String urlimg = DEBUG ? "https://images1.sx1211.cn"
            : "http://wanglei.new.images.sx1211.ea-crm.com";


    /**
     * 路径
     */
    public static class Path {
        //第三方缓存路径
        @SuppressLint("SdCardPath")
        public static String DOWNLOADING_URL = "/sdcard/teacher1211/genseevideo/";
        //图片缓存路径
        public static String IMAGE_URL = "/teacher1211/image/";
        //MP4缓存路径
        public static String MP4_URL = "/teacher1211/mp4/";
        //cc视频缓冲路径
        public static String DOWNLOAD_DIR = Environment.getExternalStorageDirectory().getPath() + "/CCDownload";
        //下载apk路径
        public final static String DOWNLOADING_APK_URL = "/storage/emulated/0/teacher1211/apk/";
        //注册协议
        public final static String AGREEMENT_LINK_URL = "sxapp://goWebView?url=" + url + "/index/registerAgreement&title=注册协议";
        //隐私政策
        public final static String POLIC_LINK_URL = "https://wanglei.apih5.yuanding.ea-crm.com/PageContents/detail/7178892";
        //微博回调网址
        public static String SINA_URL = "https://www.sx1211.cn";
    }

    /**
     * 首頁 教育资讯列表
     *
     * @return
     */
    public static String getHomeInfo() {
        return url + "/index/getHomeInfo";
    }

    /**
     * 基类
     *
     * @return
     */
    public static String getBaseList() {
        return url + "/getBaseList";
    }

    /**
     * 收藏、取消收藏
     *
     * @return
     */
    public static String getIsCollection() {
        return url + "/Product/collection";
    }

    /**
     * 登录 注册发送短信
     *
     * @return
     */
    public static String getSendSms() {
//        return url + "/sms/sendSms";
        return url + "/sms/sendSmsSpecial";
    }

    /**
     * 短信验证码注册
     *
     * @return
     */
    public static String getSmsRegister() {
        return url + "/account/smsRegister";
    }

    /**
     * 找回密码
     *
     * @return
     */
    public static String getRevisePwd() {
        return url + "/account/revisePwd";
    }

    /**
     * 验证短信验证码
     *
     * @return
     */
    public static String getCheckMobileVerify() {
        return url + "/sms/checkMobileVerify";
    }

    /**
     * 密碼登录
     *
     * @return
     */
    public static String getPswLogin() {
        return url + "/account/login";
    }

    /**
     * 注册、登录
     *
     * @return
     */
    public static String getSmsLogin() {
        return url + "/account/smsLogin";
    }

    /**
     * 修改个人信息
     *
     * @return
     */
    public static String getChangeUserInfo() {
        return url + "/user/updateUserInfo";
    }

    /**
     * 获取个人信息
     *
     * @return
     */
    public static String getUserInfo() {
        return url + "/user/getUserInfo";
    }

    /**
     * 支付
     *
     * @return
     */
    public static String getPaymentInfo() {
        return url + "/bookOrder/getPreBookOrderIndex";
    }

    /**
     * 提交订单
     *
     * @return
     */
    public static String getSubmitBookOrder() {
        return url + "/bookOrder/submitBookOrder";
    }

    /**
     * 单图片上传
     *
     * @return
     */
    public static String getUploadImage() {
        return urlimg + "/Index/uploadImage";
    }

    /**
     * 获取版本信息
     *
     * @return
     */
    public static String getVersionData() {
        return url + "/UpdateAppInfo/getVersionAndroid";
    }

    /**
     * 获取图形验证码
     *
     * @return
     */
    public static String getCaptcha() {
        return url + "/tools/getCaptcha";
    }

    /**
     * 消息列表
     *
     * @return
     */
    public static String getMessageList() {
        return url + "/message/getMessageList";
    }

    /**
     * 课程历史列表
     *
     * @return
     */
    public static String getCourseHistoryList() {
        return url + "/user/getMyCourseHistoryList";
    }

    /**
     * 课程
     *
     * @return
     */
    public static String getMyCourseList() {
        return url + "/user/getMyCourseList";
    }

    /**
     * 课程收藏
     *
     * @return
     */
    public static String getMyCollectionList() {
        return url + "/user/getMyCollectionList";
    }

    /**
     * 获取所有考试类型
     *
     * @return
     */
    public static String getProductType() {
        return url + "/index/getProductType";
    }

    /**
     * 获取所有考试类型
     *
     * @return
     */
    public static String getExamineBaseList() {
        return url + "/examine/getBaseList";
    }

    /**
     * 获取所有試卷类型
     *
     * @return
     */
    public static String getExamineTypeBySubjectId() {
        return url + "/Examine/getExamineTypeBySubjectId";
    }

    /**
     * 获取所有类型列表
     *
     * @return
     */
    public static String getTypeList() {
        return url + "/index/getTypeList";
    }

    /**
     * 获取学科列表
     *
     * @return
     */
    public static String getCategorySub() {
        return url + "/examine/getCategorySub";
    }

    /**
     * 获取点播列表数据
     *
     * @return
     */
    public static String getVideoList() {
        return url + "/Product/getVideoList";
    }

    /**
     * 获取直播列表数据
     *
     * @return
     */
    public static String getLiveList() {
        return url + "/Product/getLiveList";
    }

    /**
     * 获取点播详情
     *
     * @return
     */
    public static String getVideoDetail() {
        return url + "/Product/getVideoDetail";
    }

    /**
     * 获取点播详情
     *
     * @return
     */
    public static String getNewVideoDetail() {
        return url + "/Product/getNewVideoDetail";
    }

    /**
     * 获取直播详情
     *
     * @return
     */
    public static String getLiveDetails() {
        return url + "/Product/getLiveDetail";
    }

    /**
     * 获取新直播详情
     *
     * @return
     */
    public static String getNewLiveDetails() {
        return url + "/Product/getNewLiveDetail";
    }

    /**
     * 获取大礼包列表数据
     *
     * @return
     */
    public static String getPackageList() {
        return url + "/ProductPackage/getList";
    }

    /**
     * 获取大礼包詳情数据
     *
     * @return
     */
    public static String getPackageDetail() {
        return url + "/ProductPackage/getDetail";
    }

    /**
     * 加入购物车
     *
     * @return
     */
    public static String getAdd() {
        return url + "/cart/add";
    }

    /**
     * 搜索
     *
     * @return
     */
    public static String getSearch() {
        return url + "/index/search";
    }

    /**
     * 订单
     *
     * @return
     */
    public static String getMyBookOrderList() {
        return url + "/user/getMyBookOrderList";
    }

    /**
     * 取消订单
     *
     * @return
     */
    public static String getCancelOrder() {
        return url + "/bookorder/cancel";
    }

    /**
     * 提交评价
     *
     * @return
     */
    public static String getEvaluate() {
        return url + "/bookOrder/evaluate";
    }

    /**
     * 提交评价
     *
     * @return
     */
    public static String getAppInitData() {
        return url + "/index/appInitData";
    }

    /**
     * 学习金使用记录
     *
     * @return
     */
    public static String getCurrencyList() {
        return url + "/user/getCurrencyList";
    }

    /**
     * 学习金余额
     *
     * @return
     */
    public static String getMyInfo() {
        return url + "/user/getMyInfo";
    }

    /**
     * 学习金
     *
     * @return
     */
    public static String getCashCurrency() {
        return url + "/user/cashCurrency";
    }

    /**
     * 兑换商品
     *
     * @return
     */
    public static String getCashProduct() {
        return url + "/user/cashProduct";
    }

    /**
     * 优惠券商品
     *
     * @return
     */
    public static String getCashCoupons() {
        return url + "/UserCoupon/addUserCouponCard";
    }

    /**
     * 兑换商品列表
     *
     * @return
     */
    public static String getCashProductList() {
        return url + "/user/getCashProductList";
    }

    /**
     * 地址；列表 詳情
     *
     * @return
     */
    public static String getAddressList() {
        return url + "/Address/addressList";
    }

    /**
     * 添加地址
     *
     * @return
     */
    public static String getAddressAdd() {
        return url + "/Address/addressAdd";
    }

    /**
     * 编辑地址
     *
     * @return
     */
    public static String getAddressUpdate() {
        return url + "/Address/addressUpdate";
    }

    /**
     * 默认地址
     *
     * @return
     */
    public static String setDefaultAddress() {
        return url + "/Address/setDefaultAddress";
    }

    /**
     * 删除地址
     *
     * @return
     */
    public static String setDeleteAddress() {
        return url + "/Address/deleteAddress";
    }

    /**
     * 问答
     *
     * @return
     */
    public static String getRandomExamine() {
        return url + "/examine/getPracticePaper";
    }

    /**
     * 智能组卷
     *
     * @return
     */
    public static String getModelPaper() {
        return url + "/examine/getModelPaper";
    }

    /**
     * 章节列表
     *
     * @return
     */
    public static String getPracticeSubjectList() {
        return url + "/Examine/getPracticeSubjectList";
    }

    /**
     * 章节列表
     *
     * @return
     */
    public static String getPracticeChapterBySubjectId() {
        return url + "/Examine/getPracticeChapterBySubjectId";
    }

    /**
     * 练习提交试卷
     *
     * @return
     */
    public static String getSubmitPracticePaper() {
        return url + "/Examine/submitPracticePaper";
    }

    /**
     * 模考提交试卷
     *
     * @return
     */
    public static String getSubmitModelPaper() {
        return url + "/Examine/submitModelPaper";
    }

    /**
     * 我的錯題
     *
     * @return
     */
    public static String getExamineWrongBookSubs() {
        return url + "/Examine/getExamineWrongBookSubs";
    }

    /**
     * 收藏科目列表
     *
     * @return
     */
    public static String getCollectionSubsList() {
        return url + "/Examine/getCollectionSubsList";
    }

    /**
     * 錯題列表
     *
     * @return
     */
    public static String getWrongBookListBySubId() {
        return url + "/Examine/getWrongBookListBySubId";
    }

    /**
     * 收藏列表
     *
     * @return
     */
    public static String getCollectionSubsListBySubId() {
        return url + "/Examine/getCollectionSubsListBySubId";
    }

    /**
     * 错题本删除错题接口
     *
     * @return
     */
    public static String getSubmitWrongBookByExamineId() {
        return url + "/examine/submitWrongBookByExamineId";
    }

    /**
     * 题库纠错
     *
     * @return
     */
    public static String getDoExamineCorrect() {
        return url + "/Examine/doExamineCorrect";
    }

    /**
     * 题库收藏
     *
     * @return
     */
    public static String getDoCollection() {
        return url + "/Examine/doCollection";
    }

    /**
     * 历年真题
     *
     * @return
     */
    public static String getModelPaperList() {
        return url + "/Examine/getModelPaperList";
    }

    /**
     * 历年真题
     *
     * @return
     */
    public static String getNewModelPaperList() {
        return url + "/Examine/getNewModelPaperList";
    }

    /**
     * 历年真题
     *
     * @return
     */
    public static String getModelPaperAreaList() {
        return url + "/Examine/getModelPaperAreaList";
    }

    /**
     * 获取真题试卷信息
     *
     * @return
     */
    public static String getModelPaperDetailById() {
        return url + "/Examine/getModelPaperDetailById";
    }

    /**
     * 社区列表
     *
     * @return
     */
    public static String getWeiboList() {
        return url + "/weibo/getWeiboList";
    }

    /**
     * 用户收藏微博列表接口
     *
     * @return
     */
    public static String getUserCollectionList() {
        return url + "/weibo/getUserCollectionList";
    }

    /**
     * 用户发布微博列表
     *
     * @return
     */
    public static String getuserWeiboList() {
        return url + "/weibo/getuserWeiboList";
    }

    /**
     * 获取微博话题列表
     *
     * @return
     */
    public static String getWeiboTopicList() {
        return url + "/weibo/getWeiboTopicList";
    }

    /**
     * 微博/评论点赞
     *
     * @return
     */
    public static String addPraise() {
        return url + "/weibo/addPraise";
    }

    /**
     * 微博详情
     *
     * @return
     */
    public static String getWeiboDetail() {
        return url + "/weibo/getweiboDetail";
    }

    /**
     * 视频 和视频图片上传
     *
     * @return
     */
    public static String uploadImageVideo() {
        return urlimg + "/Index/uploadImageVideo";
    }

    /**
     * 多圖上傳
     *
     * @return
     */
    public static String multUploadImage() {
        return urlimg + "/Index/multUploadImage";
    }

    /**
     * 微博上传
     *
     * @return
     */
    public static String AddWeibo() {
        return url + "/weibo/addWeibo";
    }

    /**
     * 删除微博
     *
     * @return
     */
    public static String delWeibo() {
        return url + "/weibo/delWeibo";
    }

    /**
     * 评论
     *
     * @return
     */
    public static String addComment() {
        return url + "/weibo/addComment";
    }

    /**
     * 评论列表
     *
     * @return
     */
    public static String getCommentList() {
        return url + "/weibo/getCommentList";
    }

    /**
     * 评论详情列表
     *
     * @return
     */
    public static String getCommentReplyList() {
        return url + "/weibo/getCommentReplyList";
    }

    /**
     * 评论详情
     *
     * @return
     */
    public static String getCommentDetail() {
        return url + "/weibo/getCommentDetail";
    }

    /**
     * 删除评论
     *
     * @return
     */
    public static String delComment() {
        return url + "/weibo/delComment";
    }

    /**
     * 个人主页
     *
     * @return
     */
    public static String getUserHomePageInfo() {
        return url + "/weibo/getUserHomePageInfo";
    }

    /**
     * 关注
     *
     * @return
     */
    public static String addAttention() {
        return url + "/weibo/addAttention";
    }

    /**
     * 收藏
     *
     * @return
     */
    public static String addCollection() {
        return url + "/weibo/addCollection";
    }

    /**
     * 关注列表
     *
     * @return
     */
    public static String getAttentionList() {
        return url + "/weibo/getUserAttentionList";
    }

    /**
     * 粉丝列表
     *
     * @return
     */
    public static String getFansList() {
        return url + "/weibo/getUserFansList";
    }

    /**
     * 更新个人签名
     *
     * @return
     */
    public static String updateSignature() {
        return url + "/weibo/updateSignature";
    }

    /**
     * 举报
     *
     * @return
     */
    public static String report() {
        return url + "/weibo/report";
    }

    /**
     * 反馈类型列表接口
     *
     * @return
     */
    public static String getFeedBackData() {
        return url + "/feedback/getFeedBackData";
    }

    /**
     * 上传反馈意见
     *
     * @return
     */
    public static String addFeedBack() {
        return url + "/feedback/addFeedBack";
    }

    /**
     * 我要入住
     *
     * @return
     */
    public static String applyWeiBo() {
        return url + "/Certification/applyWeiBo";
    }

    /**
     * 检测微博认证状态
     *
     * @return
     */
    public static String checkWeibo() {
        return url + "/Certification/checkWeibo";
    }

    /**
     * 预约类型
     *
     * @return
     */
    public static String aType() {
        return url + "/revieworder/getTypeInfo";
    }

    /**
     * 立即预约
     *
     * @return
     */
    public static String appintmentAdd() {
        return url + "/revieworder/addrevieworder";
    }

    /**
     * 预约状态
     *
     * @return
     */
    public static String appintmentState() {
        return url + "/revieworder/readrevieworder";
    }

    /**
     * 可用/不可使用的优惠券列表
     *
     * @return
     */
    public static String getAllTypeCouponList() {
        return url + "/UserCoupon/getAllTypeCouponList";
    }

    /**
     * 我的优惠券列表
     *
     * @return
     */
    public static String getCouponList() {
        return url + "/UserCoupon/getCouponList";
    }

    /**
     * 设置点播和回放当前播放进度
     *
     * @return
     */
    public static String getsetVideoProgress() {
        return url + "/tongji/setVideoProgress";
    }

    /**
     * 课程分组列表
     *
     * @return
     */
    public static String getCourseSpuList() {
        return url + "/CourseSpu/getCourseSpuList";
    }

    /**
     * 获取每个分组里的课程
     *
     * @return
     */
    public static String getSpuProductList() {
        return url + "/CourseSpu/getSpuProductList";
    }

    /**
     * 城市定位列表
     *
     * @return
     */
    public static String getLocationCityList() {
        return url + "/Index/getLocationAreaList";
    }

}
