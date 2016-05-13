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
import com.app.alldemo.adapter.ChoosephotoTestAdapter;
import com.app.alldemo.utils.MyLog;

import java.util.ArrayList;
/**
 * 选择照片
 * @author Administrator
 *
 */
public class ChoosePhotosTestActivity extends Activity {
	private static final String TAG="ChoosePhotosTestActivity";
	private static final int chooseImag=100;
	private Button button;
	private GridView gridView;
	private ChoosephotoTestAdapter adapter;
	private ArrayList<String> datas=new ArrayList<String>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_photos_test);
		initUI();
		initView();
	}
	
	private void initUI(){
		button = (Button) findViewById(R.id.button);
		gridView = (GridView) findViewById(R.id.image_grid);
		gridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String path=(String)parent.getItemAtPosition(position);
				if("add_ficate".equals(path)){
					phtoActivity();
				}
			}
		});
		button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				phtoActivity();
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == RESULT_OK && requestCode == chooseImag) {
			ArrayList<String> imags = data.getStringArrayListExtra("imags");
			datas.addAll(imags);
			for(String string:datas){
				MyLog.v(TAG, "集合:"+string);
			}
			adapter.setSelectList(datas);
			adapter.notifyDataSetChanged();
		}
	}
	private void phtoActivity(){
		Intent intent=new Intent(ChoosePhotosTestActivity.this,ChoosePhotosActivity.class);
		startActivityForResult(intent, chooseImag);
	}
	private void initView(){
		datas.add("add_ficate");
		adapter=new ChoosephotoTestAdapter(ChoosePhotosTestActivity.this,datas);
		gridView.setAdapter(adapter);
	}
}
