package com.app.alldemo.function.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.download.DownloadInfo;
import com.app.alldemo.download.DownloadManager;
import com.app.alldemo.download.DownloadService;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.util.LogUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.List;

public class DownloadAdapter extends BaseAdapter{
	private Context context;
	private List<DownloadInfo> dataInfos;
	private LayoutInflater inflater;
	private DownloadManager downloadManager;
	public DownloadAdapter(Context context,List<DownloadInfo> dataInfos){
		this.context=context;
		this.dataInfos=dataInfos;
		this.inflater = LayoutInflater.from(context);
		downloadManager = DownloadService.getDownloadManager(context);
	}
	
	public void setDatas(List<DownloadInfo> dataInfos){
		this.dataInfos=dataInfos;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataInfos.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dataInfos.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		DownloadItemViewHolder holder = null;
		DownloadInfo downloadInfo = dataInfos.get(position);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.download_item, null);
			holder = new DownloadItemViewHolder(downloadInfo);
			ViewUtils.inject(holder, convertView);
			convertView.setTag(holder);
			holder.refresh();
		} else {
			holder = (DownloadItemViewHolder) convertView.getTag();
			holder.update(downloadInfo);
		}

		HttpHandler<File> handler = downloadInfo.getHandler();
		if (handler != null) {
			RequestCallBack callBack = handler.getRequestCallBack();
			if (callBack instanceof DownloadManager.ManagerCallBack) {
				DownloadManager.ManagerCallBack managerCallBack = (DownloadManager.ManagerCallBack) callBack;
				if (managerCallBack.getBaseCallBack() == null) {
					managerCallBack
							.setBaseCallBack(new DownloadRequestCallBack());
				}
			}
			callBack.setUserTag(new WeakReference<DownloadItemViewHolder>(
					holder));
		}
		return convertView;
	}
	
	public class DownloadItemViewHolder {
        @ViewInject(R.id.downing_statue)
        Button downing_statue;
        @ViewInject(R.id.downing_delete)
        Button downing_delete;
        @ViewInject(R.id.downing_name)
        TextView downing_name;
        @ViewInject(R.id.downing_bar)
        ProgressBar downing_bar;
        
        private DownloadInfo downloadInfo;

        public DownloadItemViewHolder(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
        }
        
        @OnClick(R.id.downing_statue)
        public void stop(View view) {
            HttpHandler.State state = downloadInfo.getState();
            switch (state) {
                case WAITING:
                case STARTED:
                case LOADING:
                    try {
                        downloadManager.stopDownload(downloadInfo);
                    } catch (DbException e) {
                        LogUtils.e(e.getMessage(), e);
                    }
                    break;
                case CANCELLED:
                case FAILURE:
                    try {
                        downloadManager.resumeDownload(downloadInfo, new DownloadRequestCallBack());
                    } catch (DbException e) {
                        LogUtils.e(e.getMessage(), e);
                    }
                    notifyDataSetChanged();
                    break;
                default:
                    break;
            }
        }
        
        @OnClick(R.id.downing_delete)
        public void remove(View view) {
            try {
                downloadManager.removeDownload(downloadInfo);
                setDatas(downloadManager.getDownloadingItems());
                notifyDataSetChanged();
            } catch (DbException e) {
                LogUtils.e(e.getMessage(), e);
            }
        }
        
        public void update(DownloadInfo downloadInfo) {
            this.downloadInfo = downloadInfo;
            refresh();
        }
        
        public void refresh() {
        	downing_name.setText(downloadInfo.getFileName()+"进度值"+downloadInfo.getProgress());
            downing_statue.setText(downloadInfo.getState().toString());
            if (downloadInfo.getFileLength() > 0) {
            	downing_bar.setProgress((int) (downloadInfo.getProgress() * 100 / downloadInfo.getFileLength()));
            } else {
            	downing_bar.setProgress(0);
            }
            HttpHandler.State state = downloadInfo.getState();
            switch (state) {
                case WAITING:
                	downing_statue.setText("等待");
                    break;
                case STARTED:
                	downing_statue.setText("下载中，暂停");
                    break;
                case LOADING:
                	downing_statue.setText("暂停");
                    break;
                case CANCELLED:
                	downing_statue.setText("继续");
                    break;
                case SUCCESS:
                	downing_statue.setText("成功");
                    break;
                case FAILURE:
                	downing_statue.setText("失败");
                    break;
                default:
                    break;
            }
            notifyDataSetChanged();
        }
	}
	
	private class DownloadRequestCallBack extends RequestCallBack<File> {

        @SuppressWarnings("unchecked")
        private void refreshListItem() {
            if (userTag == null) return;
            WeakReference<DownloadItemViewHolder> tag = (WeakReference<DownloadItemViewHolder>) userTag;
            DownloadItemViewHolder holder = tag.get();
            if (holder != null) {
                holder.refresh();
                System.out.println("回调刷新...");
            }
        }

        @Override
        public void onStart() {
        	System.out.println("onStart....");
            refreshListItem();
        }

        @Override
        public void onLoading(long total, long current, boolean isUploading) {
        	System.out.println("onLoading....");
            refreshListItem();
        }

        @Override
        public void onSuccess(ResponseInfo<File> responseInfo) {
        	System.out.println("onSuccess....");
            refreshListItem();
        }

        @Override
        public void onFailure(HttpException error, String msg) {
        	System.out.println("onFailure....");
            refreshListItem();
        }

        @Override
        public void onCancelled() {
        	System.out.println("onCancelled....");
            refreshListItem();
        }
    }

}
