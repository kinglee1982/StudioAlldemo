package com.app.alldemo.effect.phto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.ChooseAlbumAdapter;
import com.app.alldemo.model.AlbumImages;
import com.app.alldemo.model.ImageBucket;
import com.app.alldemo.utils.MediaImagesTool;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择相册
 * @author Administrator
 *
 */
public class ChooseAlbumActivity extends Activity {
	private static final int SELECTPHOTO = 5;//选择照片
	private TextView chooseImagCancel;
	private ListView chooseList;
	private ChooseAlbumAdapter adapter;
	private List<AlbumImages> mList;
	private List<ImageBucket> imageBuckets=new ArrayList<ImageBucket>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_album);
		initUI();
		initView();
		refreshView();
	}
	private void initUI(){
		chooseImagCancel = (TextView) findViewById(R.id.choose_imag_cancel);
		chooseList = (ListView) findViewById(R.id.choose_list);
		chooseImagCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		chooseList.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				ImageBucket imageBucket=imageBuckets.get(position);
				chooseImage(imageBucket);
			}
		});
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == SELECTPHOTO && resultCode == Activity.RESULT_OK) {
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
	private void chooseImage(ImageBucket imageBucket){
		Intent intent=new Intent(ChooseAlbumActivity.this,ChooseImagesActivity.class);
		intent.putExtra("imageBucket",imageBucket);
		startActivityForResult(intent, SELECTPHOTO);
	}
	private void refreshView(){
		imageBuckets.clear();
		imageBuckets= MediaImagesTool.getInstance().getImagesBucketList(this);
		adapter.setData(imageBuckets);
		adapter.notifyDataSetChanged();
	}
	private void initView(){
		if(imageBuckets!=null){
			adapter=new ChooseAlbumAdapter(ChooseAlbumActivity.this, imageBuckets);
			chooseList.setAdapter(adapter);
		}
	}
}
