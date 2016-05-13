package com.app.alldemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.model.DataModel;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;


@SuppressLint("NewApi")
public class GridMoreAdapter extends BaseAdapter {
    private static final String TAG = "ListAdapterTest";
    private List<DataModel> datas;
    private Context ctx;
    private List<String> selectLists;

    public GridMoreAdapter(List<DataModel> datas, List<String> selectLists, Context ctx) {
        this.datas = datas;
        this.selectLists = selectLists;
        this.ctx = ctx;
    }

    public List<String> getSelectLists() {
        return selectLists;
    }

    public void setSelectLists(List<String> selectLists) {
        this.selectLists = selectLists;
    }

    public void setDatas(List<DataModel> datas) {
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(ctx).inflate(
                    R.layout.grid_more_item, null);
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.text);
            viewHolder.image = (ImageView) convertView
                    .findViewById(R.id.image);
            viewHolder.delete_inco = (ImageView) convertView
                    .findViewById(R.id.delete_inco);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DataModel dataModel = datas.get(position);
        if(!dataModel.getIrco().equals(viewHolder.image.getTag())){
            viewHolder.image.setTag(dataModel.getIrco());
            ImageLoader.getInstance().displayImage("file://" + dataModel.getIrco(),
                    viewHolder.image);
        }
        viewHolder.text.setText(dataModel.getName());
        if (selectLists.contains(dataModel.getName())) {
            viewHolder.delete_inco.setVisibility(View.VISIBLE);
        } else {
            viewHolder.delete_inco.setVisibility(View.GONE);
        }
        return convertView;
    }

    private class ViewHolder {
        ImageView image, delete_inco;
        TextView text;
    }
}
