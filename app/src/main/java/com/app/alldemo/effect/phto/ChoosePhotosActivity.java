package com.app.alldemo.effect.phto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.GridView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.ChoosephotoAdapter;
import com.app.alldemo.model.ImageItem;
import com.app.alldemo.utils.MediaImagesTool;

import java.util.ArrayList;
/**
 * 选择照片
 * @author Administrator
 *
 */
public class ChoosePhotosActivity extends Activity {
	private static final String TAG="ChoosePhotosActivity";
	private GridView gridView;
	private Button button;
	private ChoosephotoAdapter adapter;
	private ArrayList<ImageItem> imagesDatas;
	private ArrayList<String> selectList=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_photos);
		initUI();
		initView();
	}
	
	private void initUI(){
		gridView = (GridView) findViewById(R.id.image_grid);
		button = (Button) findViewById(R.id.button);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
									int position, long id) {
				ImageItem imageItem = (ImageItem) parent.getItemAtPosition(position);
				if (selectList.contains(imageItem.imagePath)) {
					selectList.remove(imageItem.imagePath);
				} else {
					selectList.add(imageItem.imagePath);
				}
				flashView();
			}
		});
		button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				back();
			}
		});
	}
	private void flashView(){
		adapter.setData(imagesDatas);
		adapter.setSelectList(selectList);
		adapter.notifyDataSetChanged();
	}
	private void back(){
		Intent intent=getIntent();
		intent.putStringArrayListExtra("imags", selectList);
		setResult(RESULT_OK, intent);
		finish();
	}
	private void initView(){
		imagesDatas= MediaImagesTool.getInstance().getAllImages(ChoosePhotosActivity.this);
		adapter=new ChoosephotoAdapter(ChoosePhotosActivity.this,imagesDatas,selectList);
		gridView.setAdapter(adapter);
	}
}
