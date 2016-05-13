package com.app.alldemo.courview.wheel;

import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class NumberAdapter extends BaseAdapter {
	int mHeight = 50;
	String[] mData = null;
	private Context context;
	public NumberAdapter(Context context,String[] data) {
		mHeight = (int) WheelUtils.dipToPx(context, mHeight);
		this.mData = data;
		this.context=context;
	}

	@Override
	public int getCount() {
		return (null != mData) ? mData.length : 0;
	}

	@Override
	public View getItem(int arg0) {
		return getView(arg0, null, null);
	}

	@Override
	public long getItemId(int arg0) {
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		WheelTextView textView = null;
		if (null == convertView) {
			convertView = new WheelTextView(context);
			convertView.setLayoutParams(new TosGallery.LayoutParams(-1,
					mHeight));
			textView = (WheelTextView) convertView;
			textView.setTextSize(25);
			textView.setGravity(Gravity.CENTER);
		}
		String text = mData[position];
		if (null == textView) {
			textView = (WheelTextView) convertView;
		}
		textView.setText(text);
		return convertView;
	}
}
