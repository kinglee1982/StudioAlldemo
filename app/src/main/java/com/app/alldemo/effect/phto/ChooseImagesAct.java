package com.app.alldemo.effect.phto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.ChooseImageAdapter;
import com.app.alldemo.model.ImageItem;
import com.app.alldemo.utils.MediaImagesTool;

import java.util.ArrayList;

/**
 * 选择照片
 * @author Administrator
 *
 */
public class ChooseImagesAct extends Activity {
	private GridView gridView;
	private ChooseImageAdapter adapter;
	private TextView chooseImagPhtos,chooseImagCancel;
	private ArrayList<ImageItem> imagesDatas;
	private static final int CUTUPIMAG = 3;//显示照片
	private static final int CHOOSEALBUM = 4;//显示照片
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_chooseimages);
		initUI();
		initView();
	}
	
	private void initUI(){
		gridView = (GridView) findViewById(R.id.image_grid);
		chooseImagPhtos = (TextView) findViewById(R.id.choose_imag_phtos);
		chooseImagCancel = (TextView) findViewById(R.id.choose_imag_cancel);
		chooseImagPhtos.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				choosePhotos();
			}
		});
		chooseImagCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String filePath=imagesDatas.get(position).imagePath;
				cutoutImageActivity(filePath);
			}
		});
	}
	private void cutoutImageActivity(String filePath){
    	if(!TextUtils.isEmpty(filePath)){
    		Intent intent=new Intent(ChooseImagesAct.this,CutRateImageActivity.class);
    		intent.putExtra("imagPath", filePath);
    		startActivityForResult(intent, CUTUPIMAG);
    	}
    }
	private void choosePhotos(){
		Intent intent=new Intent(ChooseImagesAct.this,ChooseAlbumActivity.class);
		startActivityForResult(intent, CHOOSEALBUM);
	}
	private void initView(){
		imagesDatas= MediaImagesTool.getInstance().getAllImages(ChooseImagesAct.this);
		adapter=new ChooseImageAdapter(imagesDatas,this);
		gridView.setAdapter(adapter);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == CUTUPIMAG && resultCode == Activity.RESULT_OK) {
			setdata(data);
		}else if(requestCode == CHOOSEALBUM && resultCode == Activity.RESULT_OK){
			setdata(data);
		}
	}
	private void setdata(Intent setdata){
		Intent data=new Intent();
		byte[] bis = setdata.getByteArrayExtra("bitmap");
		data.putExtra("bitmap",bis);
		setResult(RESULT_OK, data);
		finish();
	}
}
