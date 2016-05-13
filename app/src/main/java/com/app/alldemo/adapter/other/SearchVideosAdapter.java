package com.app.alldemo.adapter.other;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.model.other.VideoDetailBean;

import java.util.ArrayList;


public class SearchVideosAdapter extends BaseAdapter {
	private ArrayList<VideoDetailBean> mDatas;
	private int mScrollState;
	private LayoutInflater inflater;
	private Context context;
	public SearchVideosAdapter(Context context,ArrayList<VideoDetailBean> mDatas) {
		this.context=context;
		this.mDatas=mDatas;
		this.inflater = LayoutInflater.from(context);
	}
	public void setDatas(ArrayList<VideoDetailBean> mDatas){
		this.mDatas=mDatas;
	}
	@Override
	public int getCount() {
		if(mDatas != null) {
			return mDatas.size();
		}

		return 0;
	}

	@Override
	public Object getItem(int position) {
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder;
		if(convertView == null) {
			holder = new ViewHolder();
			convertView = inflater.inflate(R.layout.refresh_item,null);
			holder.user_name = (TextView) convertView
					.findViewById(R.id.user_name);
			convertView.setTag(holder);
		}
		else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.user_name.setText(mDatas.get(position).getmContent());
		Log.e("test", "test");
		return convertView;
	}

	static class ViewHolder {
		TextView user_name;
	}

	public void update(int scrollState) {
		mScrollState = scrollState;
		if(mScrollState == 0) {
			notifyDataSetChanged();
		}
	}
}
