package com.app.alldemo.function;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.alldemo.R;
import com.app.alldemo.download.DownloadInfo;
import com.app.alldemo.download.DownloadManager;
import com.app.alldemo.download.DownloadService;
import com.app.alldemo.function.adapter.DownloadAdapter;
import com.app.alldemo.function.adapter.DownloadFinishedAdapter;

import java.util.List;

@SuppressLint("NewApi")
public class DownloadListActivity extends Activity{
	private ListView downloadlist_list,downloadfinsh_list;
	private List<DownloadInfo> dataModels;
	private List<DownloadInfo> finished_list;
	private DownloadAdapter adapter;
	private DownloadFinishedAdapter downfinshAdapter;
	private DownloadManager downloadManager;
    @Override  
    public void onCreate(Bundle savedInstanceState)  
    {  
        super.onCreate(savedInstanceState);  
        setContentView(R.layout.downloading);
        initData();
        findView();
    }
    
    private void findView(){
    	downloadlist_list=(ListView)findViewById(R.id.downloadlist_list);
    	downloadfinsh_list=(ListView)findViewById(R.id.downloadfinsh_list);
    	adapter=new DownloadAdapter(this, dataModels);
    	downloadlist_list.setAdapter(adapter);
    	downfinshAdapter=new DownloadFinishedAdapter(this, finished_list);
    	downloadfinsh_list.setAdapter(downfinshAdapter);
    }
    
    private void initData(){
    	downloadManager = DownloadService.getDownloadManager(this);
    	dataModels = downloadManager.getDownloadingItems();
    	finished_list = downloadManager.getDownloadFinished();
    }
}
