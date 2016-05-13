package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.SlistItemAdapter2;
import com.app.alldemo.courview.list.PullToRefreshView;
import com.app.alldemo.model.other.SName;

import java.util.ArrayList;
import java.util.List;

public class SGridviewActivity3 extends Activity
        implements PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener{
//    private WrapperListView friends_list;
    private ListView friends_list;
    private List<SName> snames=new ArrayList<SName>();
    private SlistItemAdapter2 adpter;
    private PullToRefreshView pullRefresh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sgridview3);
//        friends_list=(WrapperListView)findViewById(R.id.friends_list);
        friends_list=(ListView)findViewById(R.id.friends_list);
        pullRefresh = (PullToRefreshView) findViewById(R.id.pull_refresh);
        pullRefresh.setOnHeaderRefreshListener(this);
        pullRefresh.setOnFooterRefreshListener(this);
        getdatas();
        adpter=new SlistItemAdapter2(this,snames);
        friends_list.setAdapter(adpter);
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
            if(i==18){
                strings.add(urlString);
                strings.add(urlString2);
                strings.add(urlString3);
            }
            sName.setImages(strings);
            snames.add(sName);
        }
    }
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        // TODO Auto-generated method stub
        view.postDelayed(new Runnable() {

            @Override
            public void run() {
                pullRefresh.onFooterRefreshComplete();
            }
        }, 200);
    }

    @Override
    public void onHeaderRefresh(PullToRefreshView view) {
        view.postDelayed(new Runnable() {

            @Override
            public void run() {
                pullRefresh.onHeaderRefreshComplete();
            }
        }, 200);
    }
}
