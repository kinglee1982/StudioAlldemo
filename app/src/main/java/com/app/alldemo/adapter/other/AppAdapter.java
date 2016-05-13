package com.app.alldemo.adapter.other;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alldemo.R;

import java.util.List;


public class AppAdapter extends BaseAdapter{
	List<ApplicationInfo> mAppList;
	Context context;
	public AppAdapter(Context context,List<ApplicationInfo> mAppList){
		this.mAppList=mAppList;
		this.context=context;
	}
	@Override
	public int getCount() {
		return mAppList.size();
	}

	@Override
	public ApplicationInfo getItem(int position) {
		return mAppList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = View.inflate(context,
					R.layout.draftbox_item, null);
			new ViewHolder(convertView);
		}
		ViewHolder holder = (ViewHolder) convertView.getTag();
		ApplicationInfo item = getItem(position);
		holder.iv_icon.setImageDrawable(item.loadIcon(context.getPackageManager()));
		holder.tv_name.setText(item.loadLabel(context.getPackageManager()));
		return convertView;
	}

	class ViewHolder {
		ImageView iv_icon;
		TextView tv_name;

		public ViewHolder(View view) {
			iv_icon = (ImageView) view.findViewById(R.id.iv_icon);
			tv_name = (TextView) view.findViewById(R.id.tv_name);
			view.setTag(this);
		}
	}
}
