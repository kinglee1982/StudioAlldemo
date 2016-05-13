package com.app.alldemo.adapter;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.courview.Configure;
import com.app.alldemo.courview.ScrollLayout;

public class SlidScrollActivity extends Activity {
    private ScrollLayout views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slid_scroll);
        init();
        findUI();
    }
    private void init(){
        Configure.init(this);
    }
    private void findUI(){
        views=(ScrollLayout)findViewById(R.id.views);
        views.setPageListener(new ScrollLayout.PageListener() {
            @Override
            public void page(int page) {
                Toast.makeText(SlidScrollActivity.this,"page:"+page,Toast.LENGTH_SHORT).show();
            }
        });
        addChiledView();
    }
    private void addChiledView(){
        LayoutInflater inflater = getLayoutInflater();
        View slid_item1 = inflater.inflate(R.layout.slid_item1, null);
        View slid_item2 = inflater.inflate(R.layout.slid_item2, null);
        View slid_item3 = inflater.inflate(R.layout.slid_item3, null);
        views.addView(slid_item1);
        views.addView(slid_item2);
        views.addView(slid_item3);
    }
}
