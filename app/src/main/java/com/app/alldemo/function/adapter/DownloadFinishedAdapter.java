package com.app.alldemo.function.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.download.DownloadInfo;

import java.util.List;

public class DownloadFinishedAdapter extends BaseAdapter {

	private Context ctx;
	private List<DownloadInfo> downloadInfoList;
	private LayoutInflater inflater;

	public DownloadFinishedAdapter(Context ctx,
			List<DownloadInfo> downloadInfoList) {
		this.ctx = ctx;
		this.downloadInfoList = downloadInfoList;
		inflater = LayoutInflater.from(ctx);
	}

	
	public void setDownloadInfoList(List<DownloadInfo> downloadInfoList) {
		this.downloadInfoList = downloadInfoList;
	}

	public List<DownloadInfo> getDownloadInfoList(){
		
		return downloadInfoList;
	}

	@Override
	public int getCount() {
		return downloadInfoList.size();
	}

	@Override
	public Object getItem(int position) {
		return downloadInfoList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final DownloadInfo downloadInfo = downloadInfoList.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.downloadfinsh_item,
					null);
			holder = new ViewHolder();
			holder.downfinsh_name = (TextView) convertView
					.findViewById(R.id.downfinsh_name);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.downfinsh_name.setText(downloadInfo.getFileName());
		return convertView;
	}

	private class ViewHolder {
		public TextView downfinsh_name;
	}
}
