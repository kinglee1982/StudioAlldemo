package com.app.alldemo.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ChoosephotoTestAdapter extends BaseAdapter {
	private Context context;
	private LayoutInflater mInflater;
	private List<String> datas;
	public ChoosephotoTestAdapter(Context context,List<String> selectList){
		this.context = context;
		this.datas=selectList;
		mInflater = LayoutInflater.from(context);
	}
	public List<String> getSelectList() {
		return datas;
	}
	public void setSelectList(List<String> selectList) {
		this.datas = selectList;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return datas.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View view, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		if (view == null) {
			view = mInflater.inflate(R.layout.phto_image_test_item, null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.image);
			view.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) view.getTag();
		}
		String imagePath=datas.get(position);
		if("add_ficate".equals(imagePath)){
			viewHolder.imageView.setBackgroundResource(R.drawable.add_ficate);
		}else {
			if(!TextUtils.isEmpty(imagePath)){
				ImageLoader.getInstance().displayImage("file://" +imagePath, 
						viewHolder.imageView);
			}
		}
		return view;
	}
	
	static class ViewHolder{
		ImageView imageView;
	}

}
