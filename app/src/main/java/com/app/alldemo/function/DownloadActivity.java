package com.app.alldemo.function;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.app.alldemo.R;
import com.app.alldemo.function.adapter.DataAdapter;
import com.app.alldemo.model.DataModel;

import java.util.ArrayList;
public class DownloadActivity extends Activity{
    public static final String Downfile= Environment.getExternalStorageDirectory().getAbsolutePath()+"/"+"Download/";
    private ListView download_list;
    private ArrayList<DataModel> dataModels=new ArrayList<DataModel>();
    private DataAdapter adapter;
    private Button down_loading;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);
        initData();
        findView();
    }

    private void findView(){
        download_list=(ListView)findViewById(R.id.downloadlist_list);
        down_loading=(Button)findViewById(R.id.down_loading);
        down_loading.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downActivity();
            }
        });
        adapter=new DataAdapter(this, dataModels);
        download_list.setAdapter(adapter);
    }

    private void downActivity(){
        Intent intent=new Intent(DownloadActivity.this,DownloadListActivity.class);
        startActivity(intent);
    }

    private void initData(){
        DataModel dataModel=new DataModel();
        dataModel.setId(1001);
        dataModel.setName("QQ");
        String QQ="http://img03.taobaocdn.com/imgextra/i3/206246648/TB2oEitbXXXXXaCXpXXXXXXXXXX_!!206246648.png";
        String qqPath="http://www.apk.anzhi.com/data2/apk/201409/29/net.crimoon.pm.anzhi_32624700.apk";
        dataModel.setIrco(QQ);
        dataModel.setPath(qqPath);
        dataModels.add(dataModel);
        DataModel dataModel2=new DataModel();
        dataModel2.setId(1002);
        dataModel2.setName("WeiXin");
        String WeiXin="http://img04.taobaocdn.com/imgextra/i4/206246648/TB2_UGwbXXXXXXjXpXXXXXXXXXX_!!206246648.png";
        String WeiXinPath="http://gdown.baidu.com/data/wisegame/fab4bcd5101053a8/weixin_501.apk";
        dataModel2.setIrco(WeiXin);
        dataModel2.setPath(WeiXinPath);
        dataModels.add(dataModel2);
        DataModel dataModel3=new DataModel();
        dataModel3.setId(1006);
        dataModel3.setName("BeautyApp");
        String BeautyApp="http://img01.taobaocdn.com/imgextra/i1/206246648/TB2_s1zbXXXXXb3XXXXXXXXXXXX_!!206246648.png";
        String BeautyAppPath="http://bcs.91.com/rbreszy/android/soft/2014/11/5/f4d8f0e0500b422ab6b51ad2704b461a/com.brixd.niceapp_20208_2.2.8_635508096957824587.apk";
        dataModel3.setIrco(WeiXin);
        dataModel3.setPath(WeiXinPath);
        dataModels.add(dataModel3);
    }

}
