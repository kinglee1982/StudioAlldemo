package com.app.alldemo.adapter.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.list.WrapperGridView;
import com.app.alldemo.model.other.SName;

import java.util.List;


public class SlistItemAdapter extends BaseAdapter {
    private LayoutInflater infalter;
    private List<SName> sNames;
    private Context context;
    public SlistItemAdapter(Context context,List<SName> sNames) {
        infalter = LayoutInflater.from(context);
        this.sNames=sNames;
        this.context=context;
    }

    @Override
    public int getCount() {
        return sNames.size();
    }

    @Override
    public Object getItem(int position) {
        return sNames.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder holder;
        if (convertView == null) {
            convertView = infalter.inflate(R.layout.slist_item, null);
            holder = new ViewHolder();
            holder.name1 = (TextView) convertView.findViewById(R.id.name1);
            holder.name2 = (TextView) convertView.findViewById(R.id.name2);
            holder.gridView = (WrapperGridView) convertView.findViewById(R.id.gridviewtest);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SName sName=sNames.get(position);
        holder.name1.setText(sName.getName1());
        holder.name2.setText(sName.getName2());
        shorGridView(holder.gridView,sName.getImages(),context);
        return convertView;
    }

    static class ViewHolder {
    	TextView name1,name2;
    	WrapperGridView gridView;
    }
    private void shorGridView(WrapperGridView wGridView,List<String> mData, Context mContext){
    	GridviewTestAdapter adapter=new GridviewTestAdapter(mData,mContext);
    	wGridView.setAdapter(adapter);
    }
}
