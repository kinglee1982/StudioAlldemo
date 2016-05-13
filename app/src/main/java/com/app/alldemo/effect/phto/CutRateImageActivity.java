package com.app.alldemo.effect.phto;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.phto.ClipRoteImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
/**
 * 图片剪切
 * @author Administrator
 *
 */
@SuppressLint("NewApi")
public class CutRateImageActivity extends Activity {
	private ClipRoteImageView imageView;
	private TextView cutupImagConfrim,cutupImagCancel,cutup_imag_pre;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cutup_roteimag_main);
		imageView = (ClipRoteImageView) findViewById(R.id.src_pic);
		cutupImagConfrim = (TextView) findViewById(R.id.cutup_imag_confrim);
		cutupImagCancel = (TextView) findViewById(R.id.cutup_imag_cancel);
		cutup_imag_pre = (TextView) findViewById(R.id.cutup_imag_pre);
		cutupImagConfrim.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				setdata();
			}
		});
		cutupImagCancel.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		cutup_imag_pre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				preView();
			}
		});
		// 设置需要裁剪的图片
		Intent intent=getIntent();
		String imagPath=intent.getStringExtra("imagPath");
		loadPicture(imagPath);
	}
	private void loadPicture(String imagePath){
		try {
			BitmapRegionDecoder d = BitmapRegionDecoder.newInstance(imagePath, true);
			Bitmap bitmap = d.decodeRegion(new Rect(0, 0, d.getWidth(), d.getHeight()), null);
			imageView.setImageBitmap(bitmap);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private byte[] getBitmapBytes(){
		// 此处获取剪裁后的bitmap
		Bitmap bitmap = imageView.clip();
		// 由于Intent传递bitmap不能超过40k,此处使用二进制数组传递
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] bitmapByte = baos.toByteArray();
		return bitmapByte;
	} 
	private void preView(){
		Intent intent = new Intent(this, PreviewActivity.class);
		intent.putExtra("bitmap", getBitmapBytes());
		startActivity(intent);
	}
	private void setdata(){
		Intent data=new Intent();
		data.putExtra("bitmap",getBitmapBytes());
		setResult(RESULT_OK, data);
		finish();
	}
}
