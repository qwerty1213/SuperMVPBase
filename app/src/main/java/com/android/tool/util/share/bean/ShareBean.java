package com.android.tool.util.share.bean;

import android.content.Context;


import com.android.tool.R;
import com.android.tool.model.CommunityDetailsModel;
import com.android.tool.model.CurrencyBalanceBean;
import com.android.tool.model.LiveDetailsBean;
import com.android.tool.model.OndemandDetailsBean;
import com.android.tool.ui.web.bean.WebShareBean;
import com.android.tool.ui.web.bean.ZQYEBean;
import com.android.tool.util.StringUtil;
import com.umeng.socialize.media.UMImage;

import java.io.Serializable;

/**
 * Created by dell on 2016/12/21.
 */

public class ShareBean implements Serializable {
    public String title;
    public String id;
    public String url;
    public String content;
    public String specialContents;
    public UMImage image;

    /**
     * 点播分享
     *
     * @param title
     * @return
     */
    public static ShareBean getInstance(Context mContext, String title, int icon, String content, String url) {
        ShareBean sb = new ShareBean();
        sb.title = title;
        sb.content = content;
        sb.url = url;
        sb.image = new UMImage(mContext, icon);
        return sb;
    }

    /**
     * 分享
     *
     * @param mContext
     * @param shareInfoBean
     * @return
     */
    public static ShareBean getOndemandDetailsBeanInstance(Context mContext,
                                                           OndemandDetailsBean.ShareInfoBean shareInfoBean) {
        ShareBean sb = new ShareBean();
        sb.title = shareInfoBean.getTitle();
        sb.content = shareInfoBean.getContents();
        sb.url = shareInfoBean.getUrl();
        sb.specialContents = shareInfoBean.getSpecialContents();
        String imgUrl = shareInfoBean.getImgUrl();
        if (StringUtil.isNotBlankAndEmpty(imgUrl)) {
            sb.image = new UMImage(mContext, imgUrl);
        } else {
            sb.image = new UMImage(mContext, R.mipmap.default_head_icon);
        }
        return sb;
    }








    /**
     * 分享
     *
     * @param mContext
     * @param shareInfoBean
     * @return
     */
    public static ShareBean getWebShareBeanInstance(Context mContext, WebShareBean shareInfoBean) {
        ShareBean sb = new ShareBean();
        sb.title = shareInfoBean.getTitle();
        sb.content = shareInfoBean.getContents();
        sb.url = shareInfoBean.getUrl();
        sb.specialContents = shareInfoBean.getSpecialContents();
        String imgUrl = shareInfoBean.getImgUrl();
        if (StringUtil.isNotBlankAndEmpty(imgUrl)) {
            sb.image = new UMImage(mContext, imgUrl);
        } else {
            sb.image = new UMImage(mContext, R.mipmap.default_head_icon);
        }
        return sb;
    }

    /**
     * 分享
     *
     * @param mContext
     * @param shareInfoBean
     * @return
     */
    public static ShareBean getRecommendOurBeanInstance(Context mContext,
                                                        CurrencyBalanceBean.RecommendShareInfoBean shareInfoBean) {
        ShareBean sb = new ShareBean();
        sb.title = shareInfoBean.getTitle();
        sb.content = shareInfoBean.getContents();
        sb.url = shareInfoBean.getUrl();
        sb.specialContents = shareInfoBean.getSpecialContents();
        String imgUrl = shareInfoBean.getImgUrl();
        if (StringUtil.isNotBlankAndEmpty(imgUrl)) {
            sb.image = new UMImage(mContext, imgUrl);
        } else {
            sb.image = new UMImage(mContext, R.mipmap.default_head_icon);
        }
        return sb;
    }

    /**
     * 分享
     *
     * @param mContext
     * @param shareInfoBean
     * @return
     */
    public static ShareBean getLiveDetailsBeanInstance(Context mContext,
                                                       LiveDetailsBean.ShareInfoBean shareInfoBean) {
        ShareBean sb = new ShareBean();
        sb.title = shareInfoBean.getTitle();
        sb.content = shareInfoBean.getContents();
        sb.url = shareInfoBean.getUrl();
        sb.specialContents = shareInfoBean.getSpecialContents();
        String imgUrl = shareInfoBean.getImgUrl();
        if (StringUtil.isNotBlankAndEmpty(imgUrl)) {
            sb.image = new UMImage(mContext, imgUrl);
        } else {
            sb.image = new UMImage(mContext, R.mipmap.default_head_icon);
        }
        return sb;
    }

    /**
     * 分享
     *
     * @param mContext
     * @param shareInfoBean
     * @return
     */
    public static ShareBean getLiveDetailsBeanInstance(Context mContext,
                                                       CommunityDetailsModel.ShareInfoBean shareInfoBean) {
        ShareBean sb = new ShareBean();
        sb.title = shareInfoBean.getTitle();
        sb.content = shareInfoBean.getContentsX();
        sb.url = shareInfoBean.getUrl();
        sb.specialContents = shareInfoBean.getSpecialContents();
        String imgUrl = shareInfoBean.getImgUrl();
        if (StringUtil.isNotBlankAndEmpty(imgUrl)) {
            sb.image = new UMImage(mContext, imgUrl);
        } else {
            sb.image = new UMImage(mContext, R.mipmap.default_head_icon);
        }
        return sb;
    }





    /**
     * 赚取余额分享
     *
     * @param mContext
     * @param bean
     * @return
     */
    public static ShareBean getZQYEShareInstance(Context mContext, ZQYEBean bean) {
        ShareBean sb = new ShareBean();
        sb.title = bean.getTitle();
        sb.content = bean.getDescription();
        sb.url = bean.getLinkUrl();
        sb.specialContents = bean.getDescription();
        String imgUrl = bean.getImageUrl();
        if (StringUtil.isNotBlankAndEmpty(imgUrl)) {
            sb.image = new UMImage(mContext, imgUrl);
        } else {
            sb.image = new UMImage(mContext, R.mipmap.default_head_icon);
        }
        return sb;
    }

}
