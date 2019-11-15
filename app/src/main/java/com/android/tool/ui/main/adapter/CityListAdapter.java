package com.android.tool.ui.main.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.android.tool.R;
import com.android.tool.model.LocationCityListModel;

import me.yokeyword.indexablerv.IndexableAdapter;

public class CityListAdapter extends IndexableAdapter<LocationCityListModel.AreaListBean> {
    private LayoutInflater mInflater;

    public CityListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateTitleViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.city_list_letter_item, parent, false);
        return new IndexVH(view);
    }

    @Override
    public RecyclerView.ViewHolder onCreateContentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.city_list_city_item, parent, false);
        return new ContentVH(view);
    }

    @Override
    public void onBindTitleViewHolder(RecyclerView.ViewHolder holder, String indexTitle) {
        IndexVH vh = (IndexVH) holder;
        vh.tv.setText(indexTitle);
    }

    @Override
    public void onBindContentViewHolder(RecyclerView.ViewHolder holder, final LocationCityListModel.AreaListBean entity) {
        ContentVH vh = (ContentVH) holder;
        vh.tvName.setText(entity.getName());
    }

    private class IndexVH extends RecyclerView.ViewHolder {
        TextView tv;

        public IndexVH(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_index);
        }
    }

    private class ContentVH extends RecyclerView.ViewHolder {
        TextView tvName;

        public ContentVH(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

}
