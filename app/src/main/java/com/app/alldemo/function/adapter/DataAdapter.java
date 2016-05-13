package com.app.alldemo.function.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.download.DownloadManager;
import com.app.alldemo.download.DownloadService;
import com.app.alldemo.function.DownloadActivity;
import com.app.alldemo.model.DataModel;
import com.app.alldemo.utils.MyLog;

import java.util.List;

public class DataAdapter extends BaseAdapter{
	private Context context;
	private List<DataModel> videoModels;
	private ViewHolder viewHolder =null;
	private LayoutInflater inflater;
	private DownloadManager downloadManager;
	public DataAdapter(Context context,List<DataModel> videoModels){
		this.context=context;
		this.videoModels=videoModels;
		this.inflater = LayoutInflater.from(context);
		downloadManager = DownloadService.getDownloadManager(context);
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return videoModels.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return videoModels.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(convertView == null){
			convertView = inflater.inflate(R.layout.layout_down_appitem, null);
			viewHolder = new ViewHolder();
			viewHolder.name=(TextView)convertView.findViewById(R.id.name);
			viewHolder.downbuton=(Button)convertView.findViewById(R.id.downbuton);
			convertView.setTag(viewHolder);
		}else {
			viewHolder = (ViewHolder)convertView.getTag();
		}
		final DataModel dataModel=videoModels.get(position);
		viewHolder.name.setText(dataModel.getName());
		viewHolder.downbuton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				try {
					downloadManager.addNewDownload(dataModel.getPath(),dataModel.getName()+".apk", DownloadActivity.Downfile+dataModel.getName()+".apk",true,
							false,null);
				} catch (Exception e) {
					MyLog.e("",e.getMessage());
				}
			}
		});
		return convertView;
	}
	
	class ViewHolder{
		TextView name;
		Button downbuton;
	}

}
