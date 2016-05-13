package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.ListSlistAdapter;
import com.app.alldemo.courview.list.WrapperListView;
import com.app.alldemo.model.other.ListTestModel;

import java.util.ArrayList;
import java.util.List;

public class SListViewTestActivity extends Activity {
    private WrapperListView listview;
    List<ListTestModel> datas=new ArrayList<ListTestModel>();
    ListSlistAdapter adpter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slist_test);
        listview=(WrapperListView)findViewById(R.id.listview);
        adpter=new ListSlistAdapter(datas,this);
        listview.setAdapter(adpter);
        getDatas();
    }
    private void getDatas(){
        List<String> comment0=new ArrayList<String>();
        for(int i=0;i<5;i++){
            comment0.add("0的评论"+i);
        }
        List<String> comment3=new ArrayList<String>();
        for(int i=0;i<5;i++){
            comment3.add("3的评论"+i);
        }
        for(int i=0;i<20;i++){
            ListTestModel listTestModel=new ListTestModel();
            listTestModel.setUserName("women"+i);
            if(i==0){
                listTestModel.setComments(comment0);
            }
            if(i==3){
                listTestModel.setComments(comment3);
            }
            datas.add(listTestModel);
        }
        adpter.setDatas(datas);
        adpter.notifyDataSetChanged();
    }
}
