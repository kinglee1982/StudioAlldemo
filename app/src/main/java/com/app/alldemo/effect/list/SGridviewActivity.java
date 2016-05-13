package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.SlistItemAdapter;
import com.app.alldemo.courview.list.WrapperListView;
import com.app.alldemo.model.other.SName;
import com.app.alldemo.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

public class SGridviewActivity extends Activity {
    private WrapperListView listview;
    private SlistItemAdapter adpter;
    private List<SName> snames=new ArrayList<SName>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgridview_test);
        listview=(WrapperListView)findViewById(R.id.listview);
        getdatas();
        adpter=new SlistItemAdapter(this,snames);
        listview.setAdapter(adpter);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyLog.e("","点击事件");
            }
        });
    }
    private void getdatas(){
        String urlString="http://static.social.umeng.com/image_840dfb272f91ff9747577e0f5f226a95@360h_50Q.jpeg";
        String urlString2="http://static.social.umeng.com/image_f4f6eeed90afcd4df1fcbad39f8186ee@360w_50Q.jpeg";
        String urlString3="http://static.social.umeng.com/image_77706eac2771cdb3bc3aefa79dd49572@360w_50Q.jpeg";
        for(int i=0;i<20;i++){
            SName sName=new SName();
            sName.setName1("1名字"+i);
            sName.setName2("二名字"+i);
            List<String> strings=new ArrayList<String>();
            if(i==0){
                strings.add(urlString);
            }
            if(i==3){
                strings.add(urlString);
                strings.add(urlString2);
                strings.add(urlString3);
                strings.add(urlString3);
            }
            if(i==4){
                strings.add(urlString);
                strings.add(urlString2);
            }
            if(i==7){
                strings.add(urlString);
                strings.add(urlString2);
                strings.add(urlString3);
            }
            if(i==10){
                strings.add(urlString3);
            }
            sName.setImages(strings);
            snames.add(sName);
        }
    }
}
