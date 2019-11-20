package com.android.tool.model;

import java.io.Serializable;


/**
 * class ：通知json
 * author：York(wuchunyuan)
 * time  : 2017/3/24 16:05
 */
public class NotificationBean implements Serializable {

    /**
     * objValue : sxapp://getWebView?url=http://www.baidu.com
     * isControl : 0
     */

    private String objValue;
    private String isControl;
    private String objType;


    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public String getObjValue() {
        return objValue;
    }

    public void setObjValue(String objValue) {
        this.objValue = objValue;
    }

    public String getIsControl() {
        return isControl;
    }

    public void setIsControl(String isControl) {
        this.isControl = isControl;
    }
}
