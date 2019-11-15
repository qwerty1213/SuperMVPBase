package com.android.tool.ui.main.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.tool.R;
import com.android.tool.model.LocationCityListModel;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 热门城市适配器
 *
 * @author York(wuchunyuan)
 * @Created 2019/8/22.
 */
public class HotCityAdapter extends RecyclerView.Adapter<HotCityAdapter.Holder> {

    private List<LocationCityListModel.HotProvinceBean> mList;

    private Activity mActivity;

    public HotCityAdapter(Activity mActivity, List<LocationCityListModel.HotProvinceBean> mList) {
        this.mActivity = mActivity;
        this.mList = mList;
    }

    @Override
    public int getItemCount() {
        return mList == null ? 0 : mList.size();
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mActivity).inflate(R.layout.hot_city_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        final LocationCityListModel.HotProvinceBean item = mList.get(position);
        holder.txtCity.setText(item.getProvincename());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(item, position);
                }
            }
        });
    }

    public interface OnItemClickListener {

        void onItemClick(LocationCityListModel.HotProvinceBean item, int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }


    class Holder extends RecyclerView.ViewHolder {
        @BindView(R.id.txt_city)
        TextView txtCity;

        public Holder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}