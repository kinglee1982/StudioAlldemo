package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.adapter.ListAdapterTest;
import com.app.alldemo.courview.list.PullToRefreshLayout;

import java.util.ArrayList;
import java.util.List;

public class RefreshGridActivity extends Activity implements PullToRefreshLayout.OnRefreshListener {
    private GridView list_test;
    private PullToRefreshLayout refresh_view;
    private List<String> datas = new ArrayList<String>();
    private ListAdapterTest adapterTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_refresh_grid);
        findUI();
        getDatas();
        initView();
    }

    private void findUI() {
        refresh_view = (PullToRefreshLayout) findViewById(R.id.refresh_view);
        list_test = (GridView) findViewById(R.id.list_test);
        refresh_view.setOnRefreshListener(this);
    }

    private void getDatas() {
        for (int i = 0; i < 20; i++) {
            datas.add("测试哦" + i);
        }
    }

    private void initView() {
        adapterTest = new ListAdapterTest(datas, this);
        list_test.setAdapter(adapterTest);
    }

    @Override
    public void onRefresh(final PullToRefreshLayout pullToRefreshLayout) {
        // TODO Auto-generated method stub
        pullToRefreshLayout.postDelayed(new Runnable() {

            @Override
            public void run() {
                pullToRefreshLayout.refreshFinish(PullToRefreshLayout.SUCCEED);
                Toast.makeText(RefreshGridActivity.this, "上拉刷新成功", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(RefreshGridActivity.this, "下拉加载成功", Toast.LENGTH_SHORT).show();
            }
        }, 2000);
    }
}
