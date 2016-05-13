package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.ListViewAdapter;
import com.app.alldemo.courview.list.MyTofloawScrollView;

import java.util.ArrayList;

public class SrollListActivity extends Activity {
    private MyTofloawScrollView mScrollView;
    private ImageView mImageView;
    private LinearLayout mFlowLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scrolist_main);
        findUI();
    }
    private void findUI(){
        mScrollView = (MyTofloawScrollView) findViewById(R.id.scroll_view);
        mImageView = (ImageView) findViewById(R.id.image_view);
        mFlowLayout = (LinearLayout) findViewById(R.id.flow_llay);
        ListView listview = (ListView) findViewById(R.id.list_view);
        listview.setAdapter(new ListViewAdapter(getData(), this));
        listview.setFocusable(false);
        listview.setOnItemClickListener(onItemClickListener);

        setListViewHeightBasedOnChildren(listview);
        ListView listview2 = (ListView) findViewById(R.id.list_view2);
        listview2.setAdapter(new ListViewAdapter(getData(), this));
        listview2.setFocusable(false);
        listview2.setOnItemClickListener(onItemClickListener);

        setListViewHeightBasedOnChildren(listview2);
        mScrollView.listenerFlowViewScrollState(mImageView, mFlowLayout);
        mScrollView.scrollTo(0, 0);
    }

    OnItemClickListener onItemClickListener = new OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                long arg3) {
            Toast.makeText(SrollListActivity.this, "测试" + (arg2 + 1),
                    Toast.LENGTH_SHORT).show();
        }
    };

    private ArrayList<String> getData() {
        ArrayList<String> data = new ArrayList<String>();
        for (int i = 1; i < 30; i++) {
            data.add("测试" + i);
        }
        return data;
    }

    public void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0, len = listAdapter.getCount(); i < len; i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }
}
