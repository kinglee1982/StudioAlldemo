package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.adapter.ListAdapterTest;
import com.app.alldemo.courview.list.PullToRefreshLayout;
import com.app.alldemo.courview.list.PullToRefreshLayout.OnRefreshListener;
import com.app.alldemo.courview.list.WrapperListView;

import java.util.ArrayList;
import java.util.List;

public class RefreshScrolloListViewActivity extends Activity implements OnRefreshListener {
    private WrapperListView list_test;
    private PullToRefreshLayout refresh_view;
    private List<String> datas=new ArrayList<String>();
    private ListAdapterTest adapterTest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.refresh_scroll_test);
        findUI();
        getDatas();
        initView();
    }

    private void findUI() {
        refresh_view = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        list_test = (WrapperListView) findViewById(R.id.list_test);
        refresh_view.setOnRefreshListener(this);
    }
    private void getDatas(){
        for(int i=0;i<20;i++){
            datas.add("测试哦"+i);
        }
    }
    private void initView(){
        adapterTest=new ListAdapterTest(datas,this);
        list_test.setAdapter(adapterTest);
    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        // TODO Auto-generated method stub
        pullToRefreshLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                Toast.makeText(RefreshScrolloListViewActivity.this,"上拉刷新成功",Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }

    @Override
    public void onLoadMore(final PullToRefreshLayout pullToRefreshLayout) {
        // TODO Auto-generated method stub
        pullToRefreshLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                pullToRefreshLayout.loadmoreFinish(PullToRefreshLayout.SUCCEED);
                Toast.makeText(RefreshScrolloListViewActivity.this,"下拉加载成功",Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
