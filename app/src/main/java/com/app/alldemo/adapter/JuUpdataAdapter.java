package com.app.alldemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.alldemo.R;

import java.util.List;


public class JuUpdataAdapter extends BaseAdapter {
    private List<String> datas;
    private Context ctx;

    public JuUpdataAdapter(List<String> datas, Context ctx) {
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
                    R.layout.ju_item, null);
            viewHolder.ju_text = (TextView) convertView
                    .findViewById(R.id.ju_text);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewItem(viewHolder,position);
        return convertView;
    }

    public class ViewHolder {
        public TextView ju_text;
    }
    public void viewItem(ViewHolder viewHolder,int position){
        viewHolder.ju_text.setText(datas.get(position));
    }
}
