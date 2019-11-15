package com.android.tool.ui.main.adapter;

import android.app.Activity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.tool.R;
import com.android.tool.model.LocationCityListModel;

import java.util.List;

import me.yokeyword.indexablerv.IndexableHeaderAdapter;

/**
 * @author York(wuchunyuan)
 * @Created 2019/8/22.
 */
public class CityIndexableHeaderAdapter extends IndexableHeaderAdapter {
    private static final int TYPE = 1;
    private Activity mActivity;
    private List<LocationCityListModel.ProvinceRecordBean> locationRecentAccesList;
    private List<LocationCityListModel.HotProvinceBean> hotCityList;
    private String city;

    public CityIndexableHeaderAdapter(Activity mActivity, String index, String indexTitle, List datas, String city) {
        super(index, indexTitle, datas);
        this.mActivity = mActivity;
        this.city = city;
    }

    public void setIndexableHeaderList(List<LocationCityListModel.ProvinceRecordBean> locationRecentAccesList, List<LocationCityListModel.HotProvinceBean> hotCityList) {
        this.locationRecentAccesList = locationRecentAccesList;
        this.hotCityList = hotCityList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType() {
        return TYPE;
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.location_city_header_item, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, Object entity) {
        final viewHolder viewHolder = (viewHolder) holder;
        viewHolder.txtLocation.setText("您正在看：" + city);

        //定位、最近访问
        viewHolder.rvLocationRecentAcces.setLayoutManager(new GridLayoutManager(mActivity, 3));
        LocationRecentAccesAdapter mLocationRecentAccesAdapter = new LocationRecentAccesAdapter(mActivity, locationRecentAccesList);
        viewHolder.rvLocationRecentAcces.setAdapter(mLocationRecentAccesAdapter);
        viewHolder.rvLocationRecentAcces.setNestedScrollingEnabled(false);
        mLocationRecentAccesAdapter.setOnItemClickListener(new LocationRecentAccesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LocationCityListModel.ProvinceRecordBean item, int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemLocationRecentAccesClick(item, position);
                }
            }
        });

        //热门城市
        viewHolder.rvHotCity.setLayoutManager(new GridLayoutManager(mActivity, 3));
        HotCityAdapter mHotCityAdapter = new HotCityAdapter(mActivity, hotCityList);
        viewHolder.rvHotCity.setAdapter(mHotCityAdapter);
        viewHolder.rvHotCity.setNestedScrollingEnabled(false);
        mHotCityAdapter.setOnItemClickListener(new HotCityAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(LocationCityListModel.HotProvinceBean item, int position) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemHotCityClick(item, position);
                }
            }
        });
    }

    private class viewHolder extends RecyclerView.ViewHolder {
        RecyclerView rvLocationRecentAcces, rvHotCity;
        TextView txtLocation;

        public viewHolder(View itemView) {
            super(itemView);
            txtLocation = itemView.findViewById(R.id.txt_location);
            rvLocationRecentAcces = itemView.findViewById(R.id.rv_location_recent_acces);
            rvHotCity = itemView.findViewById(R.id.rv_hot_city);
        }
    }


    public interface OnItemClickListener {
        void onItemLocationRecentAccesClick(LocationCityListModel.ProvinceRecordBean bean, int position);

        void onItemHotCityClick(LocationCityListModel.HotProvinceBean bean, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }
}
