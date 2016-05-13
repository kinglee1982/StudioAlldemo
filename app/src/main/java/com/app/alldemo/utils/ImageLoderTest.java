package com.app.alldemo.utils;

import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ImageLoderTest {
	public static ImageLoderTest imageLoderTest = null;
	DisplayImageOptions options;
	ImageLoadingListener animateFirstListener = null;
	public static ImageLoderTest getInstance(){
		synchronized(ImageLoderTest.class){
			if(imageLoderTest == null){
				imageLoderTest = new ImageLoderTest();
			}
		}
		return imageLoderTest;
	}
	
	public ImageLoadingListener getanimateListener(){
		if(animateFirstListener == null){
			animateFirstListener = new AnimateFirstDisplayListener();
		}
		return animateFirstListener;
	}
	
	public DisplayImageOptions getoptions(){
		options = new DisplayImageOptions.Builder()
		.showStubImage(R.drawable.ic_launcher)			// 设置图片下载期间显示的图片
		.showImageForEmptyUri(R.drawable.ic_launcher)	// 设置图片Uri为空或是错误的时候显示的图片
		.showImageOnFail(R.drawable.ic_launcher)
		.cacheInMemory(true)						// 设置下载的图片是否缓存在内存中
		.cacheOnDisc(true)							// 设置下载的图片是否缓存在SD卡中
		.build();
		return options;
	}
	
	class AnimateFirstDisplayListener extends SimpleImageLoadingListener {
		 final List<String> displayedImages = Collections.synchronizedList(new LinkedList<String>());
		@Override
		public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
			if (loadedImage != null) {
				ImageView imageView = (ImageView) view;
				// 是否第一次显示
				boolean firstDisplay = !displayedImages.contains(imageUri);
				if (firstDisplay) {
					// 图片淡入效果
					FadeInBitmapDisplayer.animate(imageView, 500);
					displayedImages.add(imageUri);
				}
			}
		}
	}
}
