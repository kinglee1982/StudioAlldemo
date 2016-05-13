package com.app.alldemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.app.alldemo.model.ImageItem;
import com.app.alldemo.utils.MyLog;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class ChoosephotoAdapter extends BaseAdapter {
	private static final String TAG="ChoosephotoAdapter";
	private Context context;
	private List<ImageItem> mList;
	private LayoutInflater mInflater;
	private List<String> selectList;
	public ChoosephotoAdapter(Context context,List<ImageItem> list,List<String> selectList){
		this.context = context;
		this.mList =list;
		this.selectList=selectList;
		mInflater = LayoutInflater.from(context);
	}
	public List<String> getSelectList() {
		return selectList;
	}
	public void setSelectList(List<String> selectList) {
		this.selectList = selectList;
	}
	public void setData(ArrayList<ImageItem> list) {
		this.mList =list;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
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
			view = mInflater.inflate(R.layout.phto_image_item, null);
			viewHolder = new ViewHolder();
			viewHolder.imageView = (ImageView) view.findViewById(R.id.image);
			viewHolder.select_inco = (ImageView) view.findViewById(R.id.select_inco);
			view.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) view.getTag();
		}
		ImageItem imageItem=mList.get(position);
		ImageLoader.getInstance().displayImage("file://" +imageItem.imagePath, 
				viewHolder.imageView);
		MyLog.v(TAG,"imageItem.imagePath:"+imageItem.imagePath);
		if(selectList.contains(imageItem.imagePath)){
			viewHolder.select_inco.setVisibility(View.VISIBLE);
		}else {
			viewHolder.select_inco.setVisibility(View.GONE);
		}
		return view;
	}
	
	static class ViewHolder{
		ImageView imageView,select_inco;
	}

}
