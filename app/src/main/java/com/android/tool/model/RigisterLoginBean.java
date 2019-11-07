package com.android.tool.model;

/**
 * class ：注册/登录
 * author：York(wuchunyuan)
 * time  : 2018/3/27 16:57
 */
public class RigisterLoginBean {


    /**
     * userId : 10097400
     * nickName : York
     * mobile : 15011484815
     * sex : 0
     * avatar : http://wanglei.new.images.sx1211.ea-crm.com/01360ec6ca92556a84e0d1e60b56b008.100-100.png
     * token : 3bcd2228f7_MTAwOTc0MD_15271259532928
     */

    private String userId;
    private String nickName;
    private String mobile;
    private String sex;
    private String avatar;
    private String token;
    private String role;
    private String roleText;
    private String isAuth;

    public String getIsAuth() {
        return isAuth;
    }

    public void setIsAuth(String isAuth) {
        this.isAuth = isAuth;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRoleText() {
        return roleText;
    }

    public void setRoleText(String roleText) {
        this.roleText = roleText;
    }
}
