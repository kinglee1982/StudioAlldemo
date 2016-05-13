package com.app.alldemo.adapter.other;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.model.other.ListTestModel;
import com.app.alldemo.utils.MyLog;

import java.util.List;

@SuppressLint("NewApi")
public class ListSlistAdapter extends BaseAdapter {
	private static final String TAG="ListAdapterTest";
	private List<ListTestModel> datas;
	private Context ctx;

	public ListSlistAdapter(List<ListTestModel> datas, Context ctx) {
		this.datas = datas;
		this.ctx = ctx;
	}
	public void setDatas(List<ListTestModel> datas){
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
					R.layout.list_item_test, null);
			viewHolder.user_name = (TextView) convertView
					.findViewById(R.id.user_name);
			viewHolder.dyn_layout = (LinearLayout) convertView
					.findViewById(R.id.dyn_layout);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		MyLog.v(TAG, "小大:"+datas.size());
		ListTestModel bean = datas.get(position);
		viewHolder.user_name.setText(bean.getUserName());
		addChildView(bean.getComments(),viewHolder.dyn_layout);
		return convertView;
	}
	private void addChildView(List<String> comments ,LinearLayout layout){
		layout.removeAllViews();
		if(comments!=null && comments.size() > 0){
			for(int i=0;i<comments.size();i++){
				TextView textView=new TextView(ctx);
				textView.setText(comments.get(i));
				layout.addView(textView);
			}
		}
	}

	private class ViewHolder {
		TextView user_name;
		LinearLayout dyn_layout;
	}
}
