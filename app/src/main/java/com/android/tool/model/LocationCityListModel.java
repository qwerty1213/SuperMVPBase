package com.android.tool.model;

import java.util.List;

import me.yokeyword.indexablerv.IndexableEntity;

/**
 * @author York(wuchunyuan)
 * @Created 2019/8/22.
 */
public class LocationCityListModel {


    private List<ProvinceRecordBean> provinceRecord;
    private List<HotProvinceBean> hotProvince;
    private List<AreaListBean> areaList;


    public List<ProvinceRecordBean> getProvinceRecord() {
        return provinceRecord;
    }

    public void setProvinceRecord(List<ProvinceRecordBean> provinceRecord) {
        this.provinceRecord = provinceRecord;
    }

    public List<HotProvinceBean> getHotProvince() {
        return hotProvince;
    }

    public void setHotProvince(List<HotProvinceBean> hotProvince) {
        this.hotProvince = hotProvince;
    }

    public List<AreaListBean> getAreaList() {
        return areaList;
    }

    public void setAreaList(List<AreaListBean> areaList) {
        this.areaList = areaList;
    }

    public static class ProvinceRecordBean {
        /**
         * provinceid : 410000
         * provincename : 河南
         */

        private String provinceid;
        private String provincename;

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }
    }

    public static class HotProvinceBean {
        /**
         * provinceid : 110000
         * provincename : 北京
         */

        private String provinceid;
        private String provincename;

        public String getProvinceid() {
            return provinceid;
        }

        public void setProvinceid(String provinceid) {
            this.provinceid = provinceid;
        }

        public String getProvincename() {
            return provincename;
        }

        public void setProvincename(String provincename) {
            this.provincename = provincename;
        }
    }

    public static class AreaListBean implements IndexableEntity {
        /**
         * id : 110000
         * name : 北京市
         * pinyin : beijing
         * shortname : 北京
         */

        private String id;
        private String name;
        private String pinyin;
        private String shortname;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPinyin() {
            return pinyin;
        }

        public void setPinyin(String pinyin) {
            this.pinyin = pinyin;
        }

        public String getShortname() {
            return shortname;
        }

        public void setShortname(String shortname) {
            this.shortname = shortname;
        }

        @Override
        public String getFieldIndexBy() {
            return name;
        }

        @Override
        public void setFieldIndexBy(String indexField) {
            this.name = indexField;
        }

        @Override
        public void setFieldPinyinIndexBy(String pinyin) {
            // 需要用到拼音时(比如:搜索), 可增添pinyin字段 this.pinyin  = pinyin
            // 见 CityEntity
        }

    }
}
