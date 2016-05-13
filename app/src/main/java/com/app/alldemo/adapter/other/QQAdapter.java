package com.app.alldemo.adapter.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.listenner.MyTagListenner;
import com.app.alldemo.model.other.SName;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class QQAdapter extends BaseAdapter {
    private LayoutInflater infalter;
    private List<SName> sNames;
    private Context context;
    private MyTagListenner myTagListenner;
    public QQAdapter(Context context,List<SName> sNames,MyTagListenner myTagListenner) {
        infalter = LayoutInflater.from(context);
        this.sNames=sNames;
        this.context=context;
        this.myTagListenner=myTagListenner;
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
            convertView = infalter.inflate(R.layout.qq_item, null);
            holder = new ViewHolder();
            holder.gridView = (GridView) convertView.findViewById(R.id.gridviewtest);
            holder.image_inco = (ImageView) convertView.findViewById(R.id.image_inco);
            holder.comment_inco = (ImageView) convertView.findViewById(R.id.comment_inco);
            holder.user_name = (TextView) convertView.findViewById(R.id.user_name);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        SName sName=sNames.get(position);
        holder.user_name.setText("我们"+position);
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
        final int pos=position;
        holder.comment_inco.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				myTagListenner.onTagComplete("comment", pos);
			}
		});
        return convertView;
    }

    static class ViewHolder {
    	GridView gridView;
    	ImageView image_inco,comment_inco;
    	TextView user_name;
    }
    private void shorGridView(GridView wGridView,List<String> mData, Context mContext){
    	GridviewTestAdapter adapter=new GridviewTestAdapter(mData,mContext);
    	wGridView.setAdapter(adapter);
    }
}
