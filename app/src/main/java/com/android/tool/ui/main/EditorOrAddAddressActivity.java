package com.android.tool.ui.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.tool.R;
import com.android.tool.model.AddressManagementDetailsBean;
import com.android.tool.model.AddressRowBean;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.util.GetJsonDataUtil;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.PathUtil;
import com.android.tool.util.ResultUtil;
import com.android.tool.util.StringUtil;
import com.android.tool.utility.AppConfig;
import com.android.tool.utility.ObjectCallback;
import com.android.tool.utility.StringDialogCallback;
import com.android.tool.utility.model.ObjectResponse;
import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * class ：添加编辑地址
 * author：York(wuchunyuan)
 * time  : 2018/7/2 16:17
 */
public class EditorOrAddAddressActivity extends BaseActivitys {
    @BindView(R.id.txt_title)
    TextView txtTitle;
    @BindView(R.id.txt_right)
    TextView txtRight;
    @BindView(R.id.et_consignee_name)
    EditText etConsigneeName;
    @BindView(R.id.et_contact_phone_number)
    EditText etContactPhoneNumber;
    @BindView(R.id.txt_in_the_area)
    TextView txtInTheArea;
    @BindView(R.id.et_detailed_address)
    EditText etDetailedAddress;
    private String addressId;
    private ArrayList<AddressRowBean> options1Items = new ArrayList<>();
    private ArrayList<ArrayList<String>> options2Items = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<String>>> options3Items = new ArrayList<>();
    private String proviceId, cityId = "", districtId = "";

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
        addressId = mBundle.getString(KeyUtil.ADDRESS_ID, "");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_editor_or_add_address;
    }

    @Override
    public void initView() {
        getAddressJsonData();
        txtRight.setText(R.string.text_save);
        if (StringUtil.isNotBlankAndEmpty(addressId)) {//编辑地址
            txtTitle.setText(R.string.editor_address);
            OkGo.<ObjectResponse<AddressManagementDetailsBean>>get(PathUtil.getAddressList())
                    .params(AppConfig.AddressList.ADDRESS_ID, addressId)
                    .execute(new ObjectCallback<ObjectResponse<AddressManagementDetailsBean>>(mActivity, "") {
                        @Override
                        public void onSuccess(Response<ObjectResponse<AddressManagementDetailsBean>> response) {
                            AddressManagementDetailsBean bean = response.body().data;
                            if (bean != null) {
                                etConsigneeName.setText(bean.getRealname());
                                etContactPhoneNumber.setText(bean.getMobile());
                                txtInTheArea.setText(bean.getAddressName());
                                etDetailedAddress.setText(bean.getAddress());
                                proviceId = bean.getProvinceid();
                                cityId = bean.getCityid();
                                districtId = bean.getDistrictid();
                            }
                        }
                    });
        } else {//添加地址
            txtTitle.setText(R.string.add_addresss);
            etConsigneeName.setText("");
            etContactPhoneNumber.setText("");
            txtInTheArea.setText("");
            etDetailedAddress.setText("");
        }
    }

    @Override
    public void initListener() {

    }

    @Override
    public void doBusiness() {

    }

    @OnClick({R.id.img_back, R.id.txt_right, R.id.txt_in_the_area})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.img_back:
                onBackPressed();
                break;
            case R.id.txt_right:
                String consigneeName = etConsigneeName.getText().toString().trim();
                String contactPhoneNumber = etContactPhoneNumber.getText().toString().trim();
                String detailedAddress = etDetailedAddress.getText().toString().trim();
                if (isNull(consigneeName, contactPhoneNumber, detailedAddress)) {
                    if (StringUtil.isNotBlankAndEmpty(addressId)) {//编辑地址
                        OkGo.<String>post(PathUtil.getAddressUpdate()).tag(this)
                                .params(AppConfig.AddressList.ID, addressId)
                                .params(AppConfig.AddressList.REAL_NAME, consigneeName)
                                .params(AppConfig.AddressList.MOBILE, contactPhoneNumber)
                                .params(AppConfig.AddressList.PROVINCE_ID, proviceId)
                                .params(AppConfig.AddressList.CITY_ID, cityId)
                                .params(AppConfig.AddressList.DISTRICT_ID, districtId)
                                .params(AppConfig.AddressList.ADDRESS, detailedAddress)
                                .execute(new StringDialogCallback(mActivity) {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        setResult(ResultUtil.ADDRESSMANAGEMENT_CODE);
                                        finish();
                                    }
                                });
                    } else {//添加地址
                        OkGo.<String>post(PathUtil.getAddressAdd()).tag(this)
                                .params(AppConfig.AddressList.REAL_NAME, consigneeName)
                                .params(AppConfig.AddressList.MOBILE, contactPhoneNumber)
                                .params(AppConfig.AddressList.PROVINCE_ID, proviceId)
                                .params(AppConfig.AddressList.CITY_ID, cityId)
                                .params(AppConfig.AddressList.DISTRICT_ID, districtId)
                                .params(AppConfig.AddressList.ADDRESS, detailedAddress)
                                .execute(new StringDialogCallback(mActivity) {
                                    @Override
                                    public void onSuccess(Response<String> response) {
                                        setResult(ResultUtil.ADDRESSMANAGEMENT_CODE);
                                        finish();
                                    }
                                });
                    }
                }
                break;
            case R.id.txt_in_the_area:
                OptionsPickerView pvOptions = new OptionsPickerBuilder(mActivity, new OnOptionsSelectListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onOptionsSelect(int options1, int options2, int options3, View v) {
                        try {
                            //返回的分别是三个级别的选中位置
                            txtInTheArea.setText(options1Items.get(options1).getPickerViewText() + " " +
                                    options2Items.get(options1).get(options2) + " " +
                                    options3Items.get(options1).get(options2).get(options3));
                            proviceId = options1Items.get(options1).getId();
                            cityId = cityBeanList2.get(options1).get(options2).getId();
                            districtId = districtBeanList3.get(options1).get(options2).get(options3).getId();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).setTitleText("省市区选择")
                        .setSubCalSize(AppConfig.Main.subCalSize)
                        .setContentTextSize(AppConfig.Main.contentTextSize)//设置滚轮文字大小
                        .setTitleSize(AppConfig.Main.titleSize)
                        .setTextColorCenter(ContextCompat.getColor(mActivity, R.color.c_zhuti))
                        .setTitleColor(ContextCompat.getColor(mActivity, R.color.c_66))
                        .setCancelColor(ContextCompat.getColor(mActivity, R.color.c_zhuti))
                        .setSubmitColor(ContextCompat.getColor(mActivity, R.color.c_zhuti))
                        .build();
                pvOptions.setPicker(options1Items, options2Items, options3Items);//三级选择器
                pvOptions.show();
                if (pvOptions.isShowing()) {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(EditorOrAddAddressActivity.this.getCurrentFocus().getWindowToken(), 0);
                }
                break;
        }
    }

    private boolean isNull(String consigneeName, String contactPhoneNumber, String detailedAddress) {
        if (!StringUtil.isNotBlankAndEmpty(consigneeName)) {
            showToast(R.string.please_enter_the_consignee_name);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(contactPhoneNumber)) {
            showToast(R.string.please_enter_the_contact_phone_number);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(proviceId) && !StringUtil.isNotBlankAndEmpty(cityId)
                && !StringUtil.isNotBlankAndEmpty(districtId)) {
            showToast(R.string.the_address_is_null);
            return false;
        }
        if (!StringUtil.isNotBlankAndEmpty(detailedAddress)) {
            showToast(R.string.please_enter_the_detailed_address);
            return false;
        }
        return true;
    }

    private ArrayList<ArrayList<AddressRowBean.CityBean>> cityBeanList2 = new ArrayList<>();
    private ArrayList<ArrayList<ArrayList<AddressRowBean.CityBean.DistrictBean>>> districtBeanList3 = new ArrayList<>();

    private void getAddressJsonData() {
        /**
         * 注意：assets 目录下的Json文件仅供参考，实际使用可自行替换文件
         * 关键逻辑在于循环体
         * */
        String JsonData = new GetJsonDataUtil().getJson(this, "address.json");//获取assets目录下的json文件数据
        ArrayList<AddressRowBean> jsonBean = GetJsonDataUtil.parseData(JsonData);//用Gson 转成实体
        /**
         * 添加省份数据
         * 注意：如果是添加的JavaBean实体，则实体类需要实现 IPickerViewData 接口，
         * PickerView会通过getPickerViewText方法获取字符串显示出来。
         */
        options1Items = jsonBean;
        for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
            ArrayList<String> cityList = new ArrayList<>();//该省的城市列表（第二级）
            ArrayList<ArrayList<String>> provinceAreaList = new ArrayList<>();//该省的所有地区列表（第三极）
            /***********************存id--start***********************/
            ArrayList<AddressRowBean.CityBean> cityDataList = new ArrayList<>();//存id
            ArrayList<ArrayList<AddressRowBean.CityBean.DistrictBean>> provinceAreaDataList = new ArrayList<>();//该省的所有地区列表（第三极）
            /***********************存id--end***********************/
            for (int c = 0; c < jsonBean.get(i).getCity().size(); c++) {//遍历该省份的所有城市
                cityList.add(jsonBean.get(i).getCity().get(c).getName());//添加城市
                ArrayList<String> cityAreaList = new ArrayList<>();//该城市的所有地区列表
                /***********************存id--start***********************/
                AddressRowBean.CityBean city = new AddressRowBean.CityBean();//
                city.setId(jsonBean.get(i).getCity().get(c).getId());
                cityDataList.add(city);//存id
                ArrayList<AddressRowBean.CityBean.DistrictBean> cityAreaDataList = new ArrayList<>();//该城市的所有地区列表
                /***********************存id--end***********************/
                //如果无地区数据，建议添加空数据，防止数据为null 导致三个选项长度不匹配造成崩溃
                if (jsonBean.get(i).getCity().get(c).getDistrict().size() == 0) {
                    cityAreaList.add("");
                }
                for (int d = 0; d < jsonBean.get(i).getCity().get(c).getDistrict().size(); d++) {//该城市对应地区所有数据
                    String AreaName = jsonBean.get(i).getCity().get(c).getDistrict().get(d).getName();
                    cityAreaList.add(AreaName);//添加该城市所有地区数据
                    /***********************存id--start***********************/
                    AddressRowBean.CityBean.DistrictBean districtBean = new AddressRowBean.CityBean.DistrictBean();//
                    districtBean.setId(jsonBean.get(i).getCity().get(c).getDistrict().get(d).getId());
                    cityAreaDataList.add(districtBean);//存id
                    /***********************存id--end***********************/

                }
                provinceAreaList.add(cityAreaList);//添加该省所有地区数据
                provinceAreaDataList.add(cityAreaDataList);//添加该省所有地区数据
            }
            //添加城市数据
            options2Items.add(cityList);
            cityBeanList2.add(cityDataList);
            //添加地区数据
            options3Items.add(provinceAreaList);
            districtBeanList3.add(provinceAreaDataList);
        }
    }
}
