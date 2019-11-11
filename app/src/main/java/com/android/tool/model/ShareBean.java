package com.android.tool.model;

import android.content.Context;


import com.android.tool.R;
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





}
