package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.SearchVideosAdapter;
import com.app.alldemo.courview.list.PullToRefreshView;
import com.app.alldemo.model.other.VideoDetailBean;

import java.util.ArrayList;

public class RefreshActivity extends Activity implements
        PullToRefreshView.OnHeaderRefreshListener,PullToRefreshView.OnFooterRefreshListener{
    private PullToRefreshView pullRefresh;
    //	GridView search_layout_gv_result;
    private ListView listview;
    private TextView nodata;
    SearchVideosAdapter adapter;
    ArrayList<VideoDetailBean> videoDetailBeans=new ArrayList<VideoDetailBean>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh);
        findUI();
    }
    private void findUI(){
        pullRefresh = (PullToRefreshView) findViewById(R.id.pull_refresh);
        nodata=(TextView)findViewById(R.id.nodata);
        pullRefresh.setOnHeaderRefreshListener(this);
        pullRefresh.setOnFooterRefreshListener(this);
//    	search_layout_gv_result = (GridView) findViewById(R.id.search_layout_gv_result);
        listview = (ListView) findViewById(R.id.listview);
        for(int i=0;i<2;i++){
            VideoDetailBean videoDetailBean=new VideoDetailBean();
            videoDetailBean.setmContent("我们"+1);
            videoDetailBeans.add(videoDetailBean);
        }
        listview.setEmptyView(nodata);
        adapter=new SearchVideosAdapter(this, videoDetailBeans);
//        heardView();
        listview.setAdapter(adapter);
//        nodata();
    }
    private void nodata(){
        if(videoDetailBeans.size()>0){
            nodata.setVisibility(View.GONE);
        }else{
            nodata.setVisibility(View.VISIBLE);
        }
    }
    private View heardView;
    private void heardView(){
        heardView= LayoutInflater.from(this).inflate(R.layout.refrsh_heard, null);
        listview.addHeaderView(heardView);
    }
    @Override
    public void onFooterRefresh(PullToRefreshView view) {
        // TODO Auto-generated method stub
        view.postDelayed(new Runnable() {

            @Override
            public void run() {
                pullRefresh.onFooterRefreshComplete();
                videoDetailBeans.clear();
                adapter.setDatas(videoDetailBeans);
                adapter.notifyDataSetChanged();
//                nodata();
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
