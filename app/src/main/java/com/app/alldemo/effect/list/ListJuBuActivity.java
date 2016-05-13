package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.JuUpdataAdapter;
import com.app.alldemo.utils.MyLog;

import java.util.ArrayList;
import java.util.List;

public class ListJuBuActivity extends Activity {
    private ListView mListView;
    private JuUpdataAdapter adapter;
    private List<String> datas=new ArrayList<>();
    private Button flash_button1,flash_button2,flash_button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_jubu_test);
        mListView = (ListView) findViewById(R.id.list_test);
        flash_button1 = (Button) findViewById(R.id.flash_button1);
        flash_button2 = (Button) findViewById(R.id.flash_button2);
        flash_button3 = (Button) findViewById(R.id.flash_button3);
        flash_button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataDatas(4);
                updateItemView(4);
            }
        });
        flash_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataDatas(14);
                updateItemView(14);
            }
        });
        flash_button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updataDatas(24);
                updateItemView(24);
            }
        });
        getLists();
        adapter=new JuUpdataAdapter(datas,this);
        mListView.setAdapter(adapter);
    }
    private void getLists(){
        for(int i=0;i<30;i++){
            datas.add("我们测试"+i);
        }
    }
    private void updataDatas(int position){
        List<String> datas2=new ArrayList<>();
        datas2.addAll(datas);
        for(int i=0;i<datas2.size();i++){
            if(i==position){
                datas.remove(i);
                datas.add(i,"新的数据"+position);
            }
        }
        adapter.setDatas(datas);
    }
    private void updateItemView(int position){
        int fristPosition = mListView.getFirstVisiblePosition();
        int lastPosition=mListView.getLastVisiblePosition();
        MyLog.e("","fristPosition:"+fristPosition+"lastPosition:"+lastPosition);
        if(position>fristPosition && position<lastPosition){
            int index = position - fristPosition;
            //得到要更新的item的view
            View view = mListView.getChildAt(index);
            //从view中取得holder
            JuUpdataAdapter.ViewHolder holder = (JuUpdataAdapter.ViewHolder) view.getTag();
            adapter.viewItem(holder,position);
        }
    }
}
