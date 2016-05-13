package com.app.alldemo.adapter.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alldemo.R;

import java.util.ArrayList;

public class dragAdapter extends BaseAdapter {

	private ArrayList<String> mData = new ArrayList<String>();
	private Context mContext;

	public dragAdapter(ArrayList<String> mData, Context mContext) {
		super();
		this.mData = mData;
		this.mContext = mContext;
	}
	public void setDatas(ArrayList<String> mData){
		this.mData=mData;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mData.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	Holder holder = null;

	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		// TODO Auto-generated method stub

		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater
					.inflate(R.layout.drag_list_item, null);
			holder = new Holder();
			holder.name_tv=(TextView)convertView.findViewById(R.id.text);
			convertView.setTag(holder);
		}else {
			holder = (Holder) convertView.getTag();
		}
		holder.name_tv.setText(mData.get(arg0));
		return convertView;
	}

	class Holder {
		ImageView avator_iv;
		TextView name_tv;
	}
}
