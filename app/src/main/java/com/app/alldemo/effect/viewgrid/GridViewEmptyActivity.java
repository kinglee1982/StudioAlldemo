package com.app.alldemo.effect.viewgrid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.GridAdapterTest;
import com.app.alldemo.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class GridViewEmptyActivity extends Activity {
    private Button flash_button;
    private GridView grid_list;
    private TextView emplty_view;
    private GridAdapterTest adapterTest;
    private List<DataModel> dataModels=new ArrayList<DataModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_empty);
        getDatas();
        findUI();
    }
    private void findUI(){
        flash_button=(Button)findViewById(R.id.flash_button);
        grid_list=(GridView)findViewById(R.id.grid_list);
        emplty_view=(TextView)findViewById(R.id.emplty_view);
        flash_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flashView();
            }
        });
        grid_list.setEmptyView(emplty_view);
        adapterTest=new GridAdapterTest(dataModels,this);
        grid_list.setAdapter(adapterTest);
    }
    private void flashView(){
        dataModels.clear();
        adapterTest.setDatas(dataModels);
        adapterTest.notifyDataSetChanged();
    }
    private void getDatas(){
        for(int i=0;i<10;i++){
            DataModel dataModel=new DataModel();
            dataModel.setName("名字"+i);
            dataModels.add(dataModel);
        }
    }
}
