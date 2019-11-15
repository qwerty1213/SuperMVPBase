package com.android.tool.model;

import com.contrarywind.interfaces.IPickerViewData;

import java.util.List;

/**
 * class ：地址選擇jsonBean
 * author：York(wuchunyuan)
 * time  : 2017/3/23 14:21
 */
public class AddressRowBean implements IPickerViewData {


    /**
     * id : 110000
     * name : 北京市
     * city : [{"id":"110100","name":"市辖区","district":[{"id":"110101","name":"东城区"},{"id":"110102","name":"西城区"},{"id":"110103","name":"崇文区"},{"id":"110104","name":"宣武区"},{"id":"110105","name":"朝阳区"},{"id":"110106","name":"丰台区"},{"id":"110107","name":"石景山区"},{"id":"110108","name":"海淀区"},{"id":"110109","name":"门头沟区"},{"id":"110111","name":"房山区"},{"id":"110112","name":"通州区"},{"id":"110113","name":"顺义区"},{"id":"110114","name":"昌平区"},{"id":"110115","name":"大兴区"},{"id":"110116","name":"怀柔区"},{"id":"110117","name":"平谷区"}]},{"id":"110200","name":"县","district":[{"id":"110228","name":"密云县"},{"id":"110229","name":"延庆县"}]}]
     */

    private String id;
    private String name;
    private List<CityBean> city;

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

    public List<CityBean> getCity() {
        return city;
    }

    public void setCity(List<CityBean> city) {
        this.city = city;
    }

    @Override
    public String getPickerViewText() {
        return this.name;
    }

    public static class CityBean {
        /**
         * id : 110100
         * name : 市辖区
         * district : [{"id":"110101","name":"东城区"},{"id":"110102","name":"西城区"},{"id":"110103","name":"崇文区"},{"id":"110104","name":"宣武区"},{"id":"110105","name":"朝阳区"},{"id":"110106","name":"丰台区"},{"id":"110107","name":"石景山区"},{"id":"110108","name":"海淀区"},{"id":"110109","name":"门头沟区"},{"id":"110111","name":"房山区"},{"id":"110112","name":"通州区"},{"id":"110113","name":"顺义区"},{"id":"110114","name":"昌平区"},{"id":"110115","name":"大兴区"},{"id":"110116","name":"怀柔区"},{"id":"110117","name":"平谷区"}]
         */

        private String id;
        private String name;
        private List<DistrictBean> district;

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

        public List<DistrictBean> getDistrict() {
            return district;
        }

        public void setDistrict(List<DistrictBean> district) {
            this.district = district;
        }

        public static class DistrictBean {
            /**
             * id : 110101
             * name : 东城区
             */

            private String id;
            private String name;

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
        }
    }
}
