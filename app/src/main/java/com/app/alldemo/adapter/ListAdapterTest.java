package com.app.alldemo.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.alldemo.R;

import java.util.List;


@SuppressLint("NewApi")
public class ListAdapterTest extends BaseAdapter {
    private static final String TAG = "ListAdapterTest";
    private List<String> datas;
    private Context ctx;

    public ListAdapterTest(List<String> datas, Context ctx) {
        this.datas = datas;
        this.ctx = ctx;
    }

    public void setDatas(List<String> datas) {
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
                    R.layout.adapter_item, null);
            viewHolder.text = (TextView) convertView
                    .findViewById(R.id.text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.text.setText(datas.get(position));
        return convertView;
    }

    private class ViewHolder {
        TextView text;
    }
}
