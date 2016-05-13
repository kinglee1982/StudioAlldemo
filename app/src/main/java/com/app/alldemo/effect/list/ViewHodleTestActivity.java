package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.Adapter;

import java.util.ArrayList;
import java.util.List;

public class ViewHodleTestActivity extends Activity {
    private ListView listview;
    private Adapter adapter;
    private List<String> datasList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewhodle_test);
        listview=(ListView)findViewById(R.id.listview);
        datas();
        adapter=new Adapter(this, datasList);
        listview.setAdapter(adapter);
    }
    private void datas(){
        for(int i=0;i<30;i++){
            datasList.add("我们测试"+i);
        }
    }
}
