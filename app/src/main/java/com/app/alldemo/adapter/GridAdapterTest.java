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

import java.util.List;


@SuppressLint("NewApi")
public class GridAdapterTest extends BaseAdapter {
    private static final String TAG = "ListAdapterTest";
    private List<DataModel> datas;
    private Context ctx;

    public GridAdapterTest(List<DataModel> datas, Context ctx) {
        this.datas = datas;
        this.ctx = ctx;
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
                    R.layout.grid_item, null);
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.text);
            viewHolder.image = (ImageView) convertView
                    .findViewById(R.id.image);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DataModel dataModel=datas.get(position);
        viewHolder.text.setText(dataModel.getName());
        return convertView;
    }

    private class ViewHolder {
        ImageView image;
        TextView text;
    }
}
