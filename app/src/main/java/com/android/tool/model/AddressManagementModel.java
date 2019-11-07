package com.android.tool.model;

import java.io.Serializable;

/**
 * class ：---
 * author：York(wuchunyuan)
 * time  : 2018/7/2 15:13
 */
public class AddressManagementModel implements Serializable {


    /**
     * id : 49476223
     * userid : 49432251
     * realname : 吴春渊
     * mobile : 15201506339
     * provinceid : 110000
     * cityid : 110100
     * districtid : 110101
     * address : 大钟寺中坤广场
     * isdefault : 1
     * note :
     * postcode :
     * createtime : 1530523366
     * addressName : 北京市 市辖区 东城区
     * detailArea : 北京市 市辖区 东城区大钟寺中坤广场
     */

    private String id;
    private String userid;
    private String realname;
    private String mobile;
    private String provinceid;
    private String cityid;
    private String districtid;
    private String address;
    private String isdefault;
    private String note;
    private String postcode;
    private String createtime;
    private String addressName;
    private String detailArea;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getProvinceid() {
        return provinceid;
    }

    public void setProvinceid(String provinceid) {
        this.provinceid = provinceid;
    }

    public String getCityid() {
        return cityid;
    }

    public void setCityid(String cityid) {
        this.cityid = cityid;
    }

    public String getDistrictid() {
        return districtid;
    }

    public void setDistrictid(String districtid) {
        this.districtid = districtid;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getIsdefault() {
        return isdefault;
    }

    public void setIsdefault(String isdefault) {
        this.isdefault = isdefault;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getDetailArea() {
        return detailArea;
    }

    public void setDetailArea(String detailArea) {
        this.detailArea = detailArea;
    }
}
