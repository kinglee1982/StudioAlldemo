package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.ListSlistAdapter;
import com.app.alldemo.model.other.ListTestModel;
import com.app.alldemo.utils.ViewUtils;

import java.util.ArrayList;
import java.util.List;
public class SListViewActivity extends Activity {
    private ListView listview;
    List<ListTestModel> datas=new ArrayList<ListTestModel>();
    ListSlistAdapter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slist);
        listview=(ListView)findViewById(R.id.listview);
        getDatas();
        adpter=new ListSlistAdapter(datas,this);
        listview.setAdapter(adpter);
        ViewUtils.getInstance().setListViewHeightBasedOnChildren(listview);
    }
    private void getDatas(){
        for(int i=0;i<20;i++){
            ListTestModel listTestModel=new ListTestModel();
            listTestModel.setUserName("women" + i);
            datas.add(listTestModel);
        }
//        adpter.setDatas(datas);
//        adpter.notifyDataSetChanged();
//        ViewUtils.getInstance().setListViewHeightBasedOnChildren(listview);
    }
}
