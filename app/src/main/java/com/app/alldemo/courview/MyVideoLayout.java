package com.app.alldemo.courview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

public class MyVideoLayout extends RelativeLayout {
	public MyVideoLayout(Context context) {
		super(context);
	}
	
	public MyVideoLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public MyVideoLayout(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int mWidth = MeasureSpec.getSize(widthMeasureSpec);
		int hm = MeasureSpec.getMode(heightMeasureSpec);
		int mHeight = (int) (5.0f * mWidth / 3);
		heightMeasureSpec = MeasureSpec.makeMeasureSpec(mHeight, hm);
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
	}
}
