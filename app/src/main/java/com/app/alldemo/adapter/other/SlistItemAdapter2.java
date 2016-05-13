package com.app.alldemo.adapter.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.model.other.SName;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class SlistItemAdapter2 extends BaseAdapter {
    private LayoutInflater infalter;
    private List<SName> sNames;
    private Context context;
    public SlistItemAdapter2(Context context,List<SName> sNames) {
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
            convertView = infalter.inflate(R.layout.slist_item2, null);
            holder = new ViewHolder();
            holder.name1 = (TextView) convertView.findViewById(R.id.name1);
            holder.name2 = (TextView) convertView.findViewById(R.id.name2);
            holder.gridView = (GridView) convertView.findViewById(R.id.gridviewtest);
            holder.image_inco = (ImageView) convertView.findViewById(R.id.image_inco);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SName sName=sNames.get(position);
        holder.name1.setText(sName.getName1());
        holder.name2.setText(sName.getName2());
        List<String> images=sName.getImages();
        if(images!=null && images.size()>0){
        	if(images.size() == 1){
        		holder.gridView.setVisibility(View.GONE);
        		holder.image_inco.setVisibility(View.VISIBLE);
        		ImageLoader.getInstance().displayImage(images.get(0), holder.image_inco);
        	}else {
        		holder.gridView.setVisibility(View.VISIBLE);
        		holder.image_inco.setVisibility(View.GONE);
        		shorGridView(holder.gridView,sName.getImages(),context);
			}
        }else {
        	holder.gridView.setVisibility(View.GONE);
    		holder.image_inco.setVisibility(View.GONE);
		}
        return convertView;
    }

    static class ViewHolder {
    	TextView name1,name2;
    	GridView gridView;
    	ImageView image_inco;
    }
    private void shorGridView(GridView wGridView,List<String> mData, Context mContext){
    	GridviewTestAdapter adapter=new GridviewTestAdapter(mData,mContext);
    	wGridView.setAdapter(adapter);
    }
}
