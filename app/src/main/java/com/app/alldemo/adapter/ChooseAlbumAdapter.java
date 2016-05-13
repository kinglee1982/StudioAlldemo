package com.app.alldemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.model.ImageBucket;
import com.app.alldemo.model.ImageItem;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class ChooseAlbumAdapter extends BaseAdapter {
	private Context context;
	private List<ImageBucket> mList;
	private LayoutInflater mInflater;
	public ChooseAlbumAdapter(Context context,List<ImageBucket> list){
		this.context = context;
		this.mList=list;
		mInflater = LayoutInflater.from(context);
	}
	
	public void setData(List<ImageBucket> list) {
		this.mList=list;
	}
	
	@Override
	public int getCount() {
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
			view = mInflater.inflate(R.layout.ablums_image_item, null);
			viewHolder = new ViewHolder();
			viewHolder.ablumsImag = (ImageView) view.findViewById(R.id.ablums_imag);
			viewHolder.ablumsTitles = (TextView) view.findViewById(R.id.ablums_titles);
			viewHolder.ablumsNum = (TextView) view.findViewById(R.id.ablums_num);
			view.setTag(viewHolder);
		}
		else{
			viewHolder = (ViewHolder) view.getTag();
		}
		ImageBucket imageBucket = mList.get(position);
		List<ImageItem> imageItems=imageBucket.imageList;
		if(imageItems!=null && imageItems.size()>0){
			ImageItem imageItem=imageItems.get(0);
			ImageLoader.getInstance().displayImage("file://" +imageItem.imagePath, 
					viewHolder.ablumsImag);
		}
		viewHolder.ablumsTitles.setText(imageBucket.bucketName);
		viewHolder.ablumsNum.setText(imageBucket.count+"");
		return view;
	}
	
	static class ViewHolder{
		ImageView ablumsImag;
		TextView ablumsTitles,ablumsNum;
	}

}
