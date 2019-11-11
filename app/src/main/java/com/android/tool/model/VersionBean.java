package com.android.tool.model;

/**
 * class ：版本更新
 * author：York(wuchunyuan)
 * time  : 2018/4/23 17:30
 */
public class VersionBean {


    /**
     * versionName : 1.0.0
     * versionCode : 10
     * result : 2
     * isForced : 1
     * contents : 山香教师
     * url : http://wanglei.api.yuanding.ea-crm.com/UpdateAppInfo/DownloadAPPAndroid
     */

    private String versionName;
    private String versionCode;
    private String result;
    private String isForced;
    private String contents;
    private String url;

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getIsForced() {
        return isForced;
    }

    public void setIsForced(String isForced) {
        this.isForced = isForced;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
