package com.app.alldemo.adapter.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.model.other.FicationBean;
import com.app.alldemo.model.other.FoodBean;
import com.app.alldemo.utils.MyLog;

import java.util.HashMap;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;


public class FoodAdapter extends BaseAdapter implements
		StickyListHeadersAdapter {
	private HashMap<String, FicationBean> hashMap;
	private List<FoodBean> datas;
	private Context ctx;

	public FoodAdapter(HashMap<String, FicationBean> hashMap,
			List<FoodBean> datas, Context ctx) {
		this.hashMap = hashMap;
		this.datas = datas;
		this.ctx = ctx;
	}
	public void setDatas(List<FoodBean> datas){
		this.datas = datas;
	}
	public void setHashMap(HashMap<String, FicationBean> hashMap){
		this.hashMap = hashMap;
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
			convertView = LayoutInflater.from(ctx).inflate(R.layout.listview_title_item,
					null);
			viewHolder.tv_name = (TextView) convertView
					.findViewById(R.id.tv_name);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		FoodBean foodBean=datas.get(position);
		if(foodBean!=null){
			viewHolder.tv_name.setText(foodBean.getFoodName());
		}
		return convertView;
	}

	@Override
	public View getHeaderView(int position, View convertView, ViewGroup parent) {
		HeaderViewHolder headerViewHolder = null;
		if (convertView == null) {
			headerViewHolder = new HeaderViewHolder();
			convertView = LayoutInflater.from(ctx).inflate(
					R.layout.listview_title_hearderitem, null);
			headerViewHolder.tv_header_name = (TextView) convertView
					.findViewById(R.id.tv_header_name);
			convertView.setTag(headerViewHolder);
		} else {
			headerViewHolder = (HeaderViewHolder) convertView.getTag();
		}
		MyLog.e("","分类position:"+position);
		FicationBean ficationBean=hashMap.get(datas.get(position).getFicationId());
		if(ficationBean!=null){
			headerViewHolder.tv_header_name.setText(ficationBean.getFication_name());
		}
		return convertView;
	}

	@Override
	public long getHeaderId(int position) {
		return datas.get(position).getHeard_id();
	}

	private class ViewHolder {
		public TextView tv_name;
	}

	private class HeaderViewHolder {
		public TextView tv_header_name;
	}
}
