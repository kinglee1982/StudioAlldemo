package com.app.alldemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.utils.ViewUtils;

import java.util.List;


public class Adapter extends BaseAdapter{
	private List<String> datasList;
	private Context ctx;
	public Adapter(Context ctx,List<String> datasList) {
		this.ctx = ctx;
		this.datasList=datasList;
	}

	@Override
	public int getCount() {
		return datasList.size();
	}

	@Override
	public Object getItem(int position) {
		return datasList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(ctx).inflate(R.layout.adapter_test,
					parent,false);
		} 
		TextView textView= ViewUtils.getInstance().get(convertView, R.id.text);
		String text=datasList.get(position);
		textView.setText(text);
		return convertView;
	}
}
