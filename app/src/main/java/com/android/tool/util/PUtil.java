package com.android.tool.util;


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

import com.android.tool.BaseApplication;
import com.android.tool.model.ProductTypeBean;
import com.android.tool.model.UserInfoBean;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import static android.content.Context.MODE_PRIVATE;


/**
 * @类说明： 小数据存储工具类
 */
public class PUtil {

    public static final String TOKEN_KEY = "token";//
    public static final String USER_ID = "userId";//用户id
    public static final String REAL_NAME = "realName";//真实名字
    public static final String NICK_NAME = "nickName";//昵稱
    public static final String MOBILE = "mobile";//手机号码
    public static final String AVATAR = "avatar";//头像链接
    public static final String PROVINCE_ID = "provinceId";//省Id
    public static final String PROVINCE_NAME = "provinceName";//省名称
    public static final String AUTH_ROLE = "authRole";//身份
    public static final String SEX = "sex";//性别
    public static final String BIRTHDAY = "birthday";//生日
    public static final String ROLE = "role";//考试考试类型id
    public static final String ROLETEXT = "roleText";//考试考试类型名稱
    public static final String ISAUTH = "isAuth";//认证
    public static final String ADDRESS = "address";//考试考试类型名稱
    public static final String IS_ERROR_DELETE = "isErrorDelete";//记录错题是否删除

    //个人信息保存本地
    public static final String LOCAL_KEY = "localKey";//启动广告页面本地key
    public static final String LOCAL_IMAGE_PAHT = "localImagePath";//启动广告页面保存圖片

    public static final String COUSER_NAME_PLAYER_TITLE = "couserNamePlayerTitle";//课程名称+章节名称拼接  播放title使用

    public static final String PRODUCT_TYPE = "ProductType";//所有考试类型
    public static final String SUBJECT_TYPE = "SubjectType";//学科
    public static final String TEST_AREA = "TestArea";//地区
    public static final String GRADE = "Grade";//学段
    public static final String EXAMINEWAY = "ExamineWay";//面试 笔试
    public static final String USERINFO = "UserInfo";//面试 笔试
    public static final String IS_VIDEO = "isVideoVisOrGone";//社区发布是否上传视频

    public static final String CITY_NAME = "cityName";//首页省份名称
    public static final String CITY_ID = "cityId";//首页省份id

    public static final String IS_HIDE_APPOINTMENT = "is_hide_appointment";//是否显示我的中点评预约功能0否1是


    //TODO 把所有的key值 都作为常量定义在这里  并写一个clearPreferences 函数

    public static <T> void putPreferences(String key, T value) {
        SharedPreferences.Editor editor = BaseApplication.preferences.edit();
        if (value instanceof String) {
            editor.putString(key, value.toString());
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof Integer) {
            editor.putInt(key, ((Integer) value).intValue());
        } else if (value instanceof Float) {
            editor.putFloat(key, ((Float) value).floatValue());
        } else if (value instanceof Long) {
            editor.putLong(key, ((Long) value).longValue());
        }
        editor.commit();

    }

    public static <T> T getPreferences(String key, T value) {
        Object o = null;
        if (value instanceof String) {
            o = BaseApplication.preferences.getString(key, value.toString());
        } else if (value instanceof Boolean) {
            o = BaseApplication.preferences.getBoolean(key, ((Boolean) value).booleanValue());
        } else if (value instanceof Integer) {
            o = BaseApplication.preferences.getInt(key, ((Integer) value).intValue());
        } else if (value instanceof Float) {
            o = BaseApplication.preferences.getFloat(key, ((Float) value).floatValue());
        } else if (value instanceof Long) {
            o = BaseApplication.preferences.getLong(key, ((Long) value).longValue());
        }
        T t = (T) o;

        return t;
    }

    /**
     * 用户信息登錄 注冊保存本地
     *
     * @param token
     * @param userId
     * @param nickName
     * @param mobile
     * @param avatar
     * @param sex
     */
    public static void putPreferences(String token, String userId, String nickName,
                                      String mobile, String avatar, String sex,
                                      String role, String roleText, String isAuth) {
        putPreferences(TOKEN_KEY, token);
        putPreferences(USER_ID, userId);
        putPreferences(NICK_NAME, nickName);
        putPreferences(MOBILE, mobile);
        putPreferences(AVATAR, avatar);
        putPreferences(SEX, sex);
        putPreferences(ROLE, role);
        putPreferences(ROLETEXT, roleText);
        putPreferences(ISAUTH, isAuth);
    }

    /**
     * 个人信息
     *
     * @param bean
     */
    public static void putPreferences(UserInfoBean bean) {
        putPreferences(NICK_NAME, bean.getNickName());
        putPreferences(MOBILE, bean.getMobile());
        putPreferences(AVATAR, bean.getAvatar());
        putPreferences(BIRTHDAY, bean.getBirthday());
        putPreferences(SEX, bean.getSex());
        putPreferences(REAL_NAME, bean.getRealName());
        putPreferences(ROLE, bean.getRole());
        putPreferences(ROLETEXT, bean.getRoleText());
        putPreferences(ADDRESS, bean.getProvinceName() + " " + bean.getCityName() + " " +
                bean.getDistrictName());
    }

    public static void putPreferencesCity(String cityName, String cityId) {
        putPreferences(CITY_NAME, cityName);
        putPreferences(CITY_ID, cityId);
    }

    /**
     * 清除用户信息
     */
    public static void clearPreferences() {
        putPreferences(TOKEN_KEY, "");
        putPreferences(USER_ID, "");
        putPreferences(REAL_NAME, "");
        putPreferences(NICK_NAME, "");
        putPreferences(MOBILE, "");
        putPreferences(AVATAR, "");
        putPreferences(PROVINCE_ID, "");
        putPreferences(PROVINCE_NAME, "");
        putPreferences(AUTH_ROLE, "");
        if (sp != null) {
            sp.edit().clear().commit();
        }
    }


    /**
     * false未登录，true登录
     *
     * @return
     */
    public static boolean isTokenNull() {
        if (StringUtil.isNotBlankAndEmpty(getPreferences(TOKEN_KEY, ""))) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * false不一致，true一致
     *
     * @return
     */
    public static boolean isOnlineKey(String onlineKey) {
        if (getPreferences(LOCAL_KEY, "").equals(onlineKey)) {
            return true;
        } else {
            return false;
        }
    }

    private static SharedPreferences sp;

    /**
     * 将选中的考试类型保存在本地
     *
     * @param mActivity
     * @param id
     * @param text
     * @param options
     */
    public static void putSPProductTypeBean(Activity mActivity, String id, String text, String options) {
        ProductTypeBean recondBean = new ProductTypeBean();
        recondBean.setValue(id);
        recondBean.setText(text);
        recondBean.setOptions(options);
        if (sp == null) {
            sp = mActivity.getSharedPreferences("config", MODE_PRIVATE);
        }
        SharedPreferences.Editor editor = sp.edit();
        Gson gson = new Gson();
        String json = gson.toJson(recondBean);
        editor.putString(PRODUCT_TYPE, json);
        editor.commit();
    }

    /**
     * 读取保存在本地选中的考试类型
     *
     * @return
     */
    public static ProductTypeBean getSPProductTypeBean(Activity mActivity) {
        if (sp == null) {
            sp = mActivity.getSharedPreferences("config", MODE_PRIVATE);
        }
        String json = sp.getString(PRODUCT_TYPE, null);
        Type type = new TypeToken<ProductTypeBean>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }

    /**
     * 读取保存在本地选中的考试类型
     *
     * @return
     */
    public static ProductTypeBean getSPProductTypeBean(Context mActivity) {
        if (sp == null) {
            sp = mActivity.getSharedPreferences("config", MODE_PRIVATE);
        }
        String json = sp.getString(PRODUCT_TYPE, null);
        Type type = new TypeToken<ProductTypeBean>() {
        }.getType();
        return new Gson().fromJson(json, type);
    }












    public static void clearArea() {
        if (sp != null) {
            SharedPreferences.Editor editor = sp.edit();
            editor.remove(TEST_AREA);
            editor.commit();
        }
    }

    public static String productType(Activity mActivity) {
        String productType = "";
        try {
            productType = PUtil.getSPProductTypeBean(mActivity).getValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productType;
    }


}