package com.app.alldemo.effect.viewgrid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.GridMoreAdapter;
import com.app.alldemo.model.DataModel;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一张图片闪动
 */
public class GridMoreActivity extends Activity {
    private GridView grid_list;
    private TextView emplty_view;
    private GridMoreAdapter adapter;
    private List<DataModel> dataModels=new ArrayList<DataModel>();
    private List<String> selectLists=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_more);
        getDatas();
        findUI();
    }
    private void findUI(){
        grid_list=(GridView)findViewById(R.id.grid_list);
        grid_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                DataModel dataModel=(DataModel)parent.getItemAtPosition(position);
                if(selectLists.contains(dataModel.getName())){
                    selectLists.remove(dataModel.getName());
                }else{
                    selectLists.add(dataModel.getName());
                }
                flashView();
            }
        });
        emplty_view=(TextView)findViewById(R.id.emplty_view);
        grid_list.setEmptyView(emplty_view);
        adapter =new GridMoreAdapter(dataModels,selectLists,this);
        grid_list.setAdapter(adapter);
    }
    private void flashView(){
        adapter.setSelectLists(selectLists);
        adapter.setDatas(dataModels);
        adapter.notifyDataSetChanged();
    }
    private void getDatas(){
        String inco0="/storage/emulated/0/Download/42655195.jpg";
        String inco1="/storage/emulated/0/Pictures/Screenshots/Screenshot_2014-10-17-10-19-35.png";
        String inco2="/storage/emulated/0/57d39004c1ae703fc3af723b530caeb5.png";
        String inco3="/storage/emulated/0/43932003.jpg";
        String inco4="/storage/emulated/0/46196607.jpg";
        String inco5="/storage/emulated/0/Pictures/Screenshots/Screenshot_2014-10-17-10-19-07.png";
        for(int i=0;i<6;i++){
            DataModel dataModel=new DataModel();
            if(i==0){
                dataModel.setIrco(inco0);
            }
            if(i==1){
                dataModel.setIrco(inco1);
            }
            if(i==2){
                dataModel.setIrco(inco2);
            }
            if(i==3){
                dataModel.setIrco(inco3);
            }
            if(i==4){
                dataModel.setIrco(inco4);
            }
            if(i==5){
                dataModel.setIrco(inco5);
            }
            dataModel.setName("名字"+i);
            dataModels.add(dataModel);
        }
    }
}
