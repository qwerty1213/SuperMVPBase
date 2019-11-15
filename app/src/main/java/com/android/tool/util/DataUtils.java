package com.android.tool.util;


import com.android.tool.model.HomePageBean;

import java.util.ArrayList;
import java.util.List;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/3/15 10:13
 */
public class DataUtils {


    /**
     * 首页轮播图数据转存
     *
     * @param adSlideBeanList
     * @return
     */
    public static List<String> getAdSlideBeanList(List<HomePageBean.TopBannerBean> adSlideBeanList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < adSlideBeanList.size(); i++) {
            list.add(adSlideBeanList.get(i).getImgUrl());
        }
        return list;
    }

    /**
     * 首页广告轮播图数据转存
     *
     * @param middleBannerBeanList
     * @return
     */
    public static List<String> getMiddleBannerBeanList(List<HomePageBean.MiddleBannerBean> middleBannerBeanList) {
        List<String> list = new ArrayList<>();
        for (int i = 0; i < middleBannerBeanList.size(); i++) {
            list.add(middleBannerBeanList.get(i).getImgUrl());
        }
        return list;
    }
}
