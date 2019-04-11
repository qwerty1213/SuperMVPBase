package com.android.tool.util;


import android.content.SharedPreferences;

import com.android.tool.BaseApplication;


/**
 * @类说明： 小数据存储工具类
 */
public class P {

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
    public static final String ADDRESS = "address";//考试考试类型名稱

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

//    /**
//     * 用户信息登錄 注冊保存本地
//     *
//     * @param token
//     * @param userId
//     * @param nickName
//     * @param mobile
//     * @param avatar
//     * @param sex
//     */
//    public static void putPreferences(String token, String userId, String nickName,
//                                      String mobile, String avatar, String sex,
//                                      String role, String roleText, String isAuth) {
//        putPreferences(TOKEN_KEY, token);
//        putPreferences(USER_ID, userId);
//        putPreferences(NICK_NAME, nickName);
//        putPreferences(MOBILE, mobile);
//        putPreferences(AVATAR, avatar);
//        putPreferences(SEX, sex);
//        putPreferences(ROLE, role);
//        putPreferences(ROLETEXT, roleText);
//        putPreferences(ISAUTH, isAuth);
//    }

//    /**
//     * 个人信息
//     *
//     * @param bean
//     */
//    public static void putPreferences(UserInfoBean bean) {
//        putPreferences(NICK_NAME, bean.getNickName());
//        putPreferences(MOBILE, bean.getMobile());
//        putPreferences(AVATAR, bean.getAvatar());
//        putPreferences(BIRTHDAY, bean.getBirthday());
//        putPreferences(SEX, bean.getSex());
//        putPreferences(REAL_NAME, bean.getRealName());
//        putPreferences(ROLE, bean.getRole());
//        putPreferences(ROLETEXT, bean.getRoleText());
//        putPreferences(ADDRESS, bean.getProvinceName() + " " + bean.getCityName() + " " +
//                bean.getDistrictName());
//    }
//
//    /**
//     * 清除用户信息
//     */
//    public static void clearPreferences() {
//        putPreferences(TOKEN_KEY, "");
//        putPreferences(USER_ID, "");
//        putPreferences(REAL_NAME, "");
//        putPreferences(NICK_NAME, "");
//        putPreferences(MOBILE, "");
//        putPreferences(AVATAR, "");
//        putPreferences(PROVINCE_ID, "");
//        putPreferences(PROVINCE_NAME, "");
//        putPreferences(AUTH_ROLE, "");
//    }
//

//    /**
//     * false未登录，true登录
//     *
//     * @return
//     */
//    public static boolean isTokenNull() {
//        if (StringUtil.isNotBlankAndEmpty(getPreferences(TOKEN_KEY, ""))) {
//            return true;
//        } else {
//            return false;
//        }
//    }

//    /**
//     * false不一致，true一致
//     *
//     * @return
//     */
//    public static boolean isOnlineKey(String onlineKey) {
//        if (getPreferences(LOCAL_KEY, "").equals(onlineKey)) {
//            return true;
//        } else {
//            return false;
//        }
//    }

}