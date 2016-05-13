package com.app.alldemo.adapter.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;

import com.app.alldemo.R;
import com.app.alldemo.courview.list.MyWidthImageView;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.List;

public class TestGridviewAdapter extends BaseAdapter {
	private List<String> mData;
	private Context mContext;
	private int wh;
	public TestGridviewAdapter(List<String> mData, Context mContext,int wh) {
		this.mData = mData;
		this.mContext = mContext;
		this.wh=wh;
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
		return arg0;
	}

	Holder holder = null;
	@Override
	public View getView(int arg0, View convertView, ViewGroup arg2) {
		if (convertView == null) {
			LayoutInflater inflater = LayoutInflater.from(mContext);
			convertView = inflater
					.inflate(R.layout.test_grid_item, null);
			holder = new Holder();
			holder.image = (MyWidthImageView) convertView.findViewById(R.id.imageView);
			LayoutParams mImageViewLayoutParams = new LayoutParams(LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
			holder.image.setLayoutParams(mImageViewLayoutParams);
			convertView.setTag(holder);
		}else {
			holder = (Holder) convertView.getTag();
		}
		ImageLoader.getInstance().displayImage(mData.get(arg0), holder.image);
		return convertView;
	}

	class Holder {
		MyWidthImageView image;
	}
}
