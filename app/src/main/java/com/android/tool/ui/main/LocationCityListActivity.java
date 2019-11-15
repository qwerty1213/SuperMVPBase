package com.android.tool.ui.main;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.android.tool.R;
import com.android.tool.model.AddressRowBean;
import com.android.tool.model.LocationCityListModel;
import com.android.tool.presenter.LocationCityListPresenter;
import com.android.tool.presenter.impl.LocationCityListImpl;
import com.android.tool.ui.base.BaseActivitys;
import com.android.tool.ui.main.adapter.CityIndexableHeaderAdapter;
import com.android.tool.ui.main.adapter.CityListAdapter;
import com.android.tool.ui.view.LocationCityListView;
import com.android.tool.util.GetJsonDataUtil;
import com.android.tool.util.KeyUtil;
import com.android.tool.util.L;
import com.android.tool.util.ResultUtil;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.ethanhua.skeleton.SkeletonScreen;
import com.github.promeg.tinypinyin.lexicons.java.cncity.CnCityDict;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import me.yokeyword.indexablerv.IndexableAdapter;
import me.yokeyword.indexablerv.IndexableLayout;

/**
 * 城市定位    城市选择列表
 *
 * @author York(wuchunyuan)
 * @Created 2019/8/22.
 */
@SuppressLint("Registered")
public class LocationCityListActivity extends BaseActivitys implements LocationCityListView, CityIndexableHeaderAdapter.OnItemClickListener, IndexableAdapter.OnItemContentClickListener<LocationCityListModel.AreaListBean> {

    @BindView(R.id.indexableLayout)
    IndexableLayout indexableLayout;
    private CityListAdapter mCityListAdapter;
    private CityIndexableHeaderAdapter mCityIndexableHeaderAdapter;
    private LocationCityListPresenter mLocationCityListPresenter;
    private LocationClient client = null;
    private LocationClientOption mOption;
    private boolean isLocation = false;
    private String city;
    private List<LocationCityListModel.ProvinceRecordBean> locationRecentAccesList;

    @Override
    public void initParms(Bundle mBundle) {
        steepSetStatusBarTranslucent(true, true);
        city = mBundle.getString("city");
    }

    @Override
    public int bindLayout() {
        return R.layout.activity_location_city_list;
    }

    @Override
    public void initView() {
        mLocationCityListPresenter = new LocationCityListImpl(this);
        indexableLayout.setLayoutManager(new LinearLayoutManager(this));
        mCityListAdapter = new CityListAdapter(this);//字母和城市列表适配器
        indexableLayout.setAdapter(mCityListAdapter);
        indexableLayout.setOverlayStyle_MaterialDesign(color(R.color.c_zhuti));
        indexableLayout.showAllLetter(false);//设置是否显示所有字母

        // 多音字处理
        Pinyin.init(Pinyin.newConfig().with(CnCityDict.getInstance(this)));
        // 全字母排序。  排序规则设置为：每个字母都会进行比较排序；速度较慢
        indexableLayout.setCompareMode(IndexableLayout.MODE_FAST);
        // 这里BannerView只有一个Item, 添加一个长度为1的任意List作为第三个参数
        List<String> bannerList = new ArrayList<>();
        bannerList.add("");
        mCityIndexableHeaderAdapter = new CityIndexableHeaderAdapter(this, "定位", null, bannerList, city);
        indexableLayout.addHeaderAdapter(mCityIndexableHeaderAdapter);
        showViewSkeletonScreen(R.layout.ss_location_city_header, indexableLayout);
    }


    @Override
    public void initListener() {
        mCityListAdapter.setOnItemContentClickListener(this);
        mCityIndexableHeaderAdapter.setOnItemClickListener(this);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            ArrayList<String> permissions = new ArrayList<>();
            // 定位精确位置
            if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_FINE_LOCATION);
            }
            if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                permissions.add(Manifest.permission.ACCESS_COARSE_LOCATION);
            }
            if (permissions.size() > 0) {
                requestPermissions(permissions.toArray(new String[permissions.size()]), 127);
            }
        }
    }

    @Override
    public void doBusiness() {
        mLocationCityListPresenter.getRequested(mActivity);

    }

    /**
     * 接口返回数据
     *
     * @param model
     */
    @Override
    public void doLocationCityListResponse(LocationCityListModel model) {
        mCityListAdapter.setDatas(model.getAreaList());

        locationRecentAccesList = model.getProvinceRecord();
        LocationCityListModel.ProvinceRecordBean provinceRecordBean = new LocationCityListModel.ProvinceRecordBean();
        provinceRecordBean.setProvinceid("0");
        provinceRecordBean.setProvincename("定位中...");
        locationRecentAccesList.add(0, provinceRecordBean);

        mCityIndexableHeaderAdapter.setIndexableHeaderList(locationRecentAccesList, model.getHotProvince());
        //开始定位
        client = new LocationClient(getApplicationContext());
        client.setLocOption(getDefaultLocationClientOption());
        if (mListener != null && mOption != null) {
            client.registerLocationListener(mListener);
            client.setLocOption(mOption);
            if (client != null && !client.isStarted()) {
                client.start();
            }
        }
    }


    /**
     * 城市列表点击事件
     *
     * @param v
     * @param originalPosition
     * @param currentPosition
     * @param entity
     */
    @Override
    public void onItemClick(View v, int originalPosition, int currentPosition, LocationCityListModel.AreaListBean entity) {
        setResultCity(entity.getName(), entity.getId());
    }

    /**
     * 定位、最近访问点击事件
     *
     * @param bean
     * @param position
     */
    @Override
    public void onItemLocationRecentAccesClick(LocationCityListModel.ProvinceRecordBean bean, int position) {
        if (position == 0) {//定位点击
            if (isLocation) {
                String JsonData = new GetJsonDataUtil().getJson(this, "address.json");//获取assets目录下的json文件数据
                ArrayList<AddressRowBean> jsonBean = GetJsonDataUtil.parseData(JsonData);//用Gson 转成实体
                for (int i = 0; i < jsonBean.size(); i++) {//遍历省份
                    if (jsonBean.get(i).getName().equals(bean.getProvincename())) {
                        setResultCity(bean.getProvincename(), jsonBean.get(i).getId());
                        L.i(bean.getProvincename() + jsonBean.get(i).getId() + "\nName:" + jsonBean.get(i).getName());
                    }
                }
            } else {
                if (client != null && !client.isStarted()) {//重新定位
                    client.start();
                }
            }
        } else {
            setResultCity(bean.getProvincename(), bean.getProvinceid());
        }
    }

    /**
     * 热门城市点击事件
     *
     * @param bean
     * @param position
     */
    @Override
    public void onItemHotCityClick(LocationCityListModel.HotProvinceBean bean, int position) {
        setResultCity(bean.getProvincename(), bean.getProvinceid());
    }

    private void setResultCity(String cityName, String cityCode) {
        Intent intent = new Intent();
        intent.putExtra(KeyUtil.CITY_NAME, cityName);
        intent.putExtra(KeyUtil.CITY_CODE, cityCode);
        setResult(ResultUtil.HOME_PAGE_LOCATION, intent);
        finish();
    }

    @Override
    public void onHide() {
    }

    @Override
    public SkeletonScreen skeletonScreen() {
        return skeletonScreen;
    }

    /**
     * 定位结果回调，重写onReceiveLocation方法，可以直接拷贝如下代码到自己工程中修改
     */
    private BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // TODO Auto-generated method stub
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
//                L.i("当前定位城市----->" + location.getCity() + "\n" + location.get);
                isLocation = true;
                if (locationRecentAccesList != null) {
                    LocationCityListModel.ProvinceRecordBean provinceRecordBean = new LocationCityListModel.ProvinceRecordBean();
                    provinceRecordBean.setProvinceid(location.getCityCode());
                    provinceRecordBean.setProvincename(location.getProvince());
                    locationRecentAccesList.set(0, provinceRecordBean);
                    mCityIndexableHeaderAdapter.notifyDataSetChanged();
                }
            } else {
                isLocation = false;
                L.i("定位失败----->");
                mCityIndexableHeaderAdapter.notifyDataSetChanged();
            }
        }
    };

    /***
     *
     * @return DefaultLocationClientOption  默认O设置
     */
    public LocationClientOption getDefaultLocationClientOption() {
        if (mOption == null) {
            mOption = new LocationClientOption();
            mOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
            mOption.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系，如果配合百度地图使用，建议设置为bd09ll;
            mOption.setScanSpan(5000);//可选，默认0，即仅定位一次，设置发起连续定位请求的间隔需要大于等于1000ms才是有效的
            mOption.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
            mOption.setIsNeedLocationDescribe(true);//可选，设置是否需要地址描述
            mOption.setNeedDeviceDirect(false);//可选，设置是否需要设备方向结果
            mOption.setLocationNotify(false);//可选，默认false，设置是否当gps有效时按照1S1次频率输出GPS结果
            mOption.setIgnoreKillProcess(true);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
            mOption.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
            mOption.setIsNeedLocationPoiList(true);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
            mOption.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
            mOption.setOpenGps(true);//可选，默认false，设置是否开启Gps定位
            mOption.setIsNeedAltitude(false);//可选，默认false，设置定位时是否需要海拔信息，默认不需要，除基础定位版本都可用
        }
        return mOption;
    }

    /***
     * Stop location service
     */
    @Override
    protected void onStop() {
        // TODO Auto-generated method stub
        client.unRegisterLocationListener(mListener);
        if (client != null && client.isStarted()) {
            client.stop();
        }
        super.onStop();
    }

    @OnClick(R.id.iv_back)
    public void onViewClicked() {
        onBackPressed();
    }


}
