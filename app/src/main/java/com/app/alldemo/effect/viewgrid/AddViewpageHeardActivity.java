package com.app.alldemo.effect.viewgrid;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.adapter.GridAdapterTest;
import com.app.alldemo.courview.GridViewWithHeaderAndFooter;
import com.app.alldemo.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class AddViewpageHeardActivity extends Activity {
    private GridViewWithHeaderAndFooter gridview;
    private View heardView;
    private GridAdapterTest adapterTest;
    private List<DataModel> datas=new ArrayList<DataModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpage_addheard);
        findUI();
        getDatas();
        initView();
    }
    private void findUI(){
        gridview=(GridViewWithHeaderAndFooter)findViewById(R.id.gridview);
        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position>3){
                    String string = datas.get(position - 3).getName();
                    Toast.makeText(AddViewpageHeardActivity.this, string, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void getDatas(){
        for(int i=0;i<10;i++){
            DataModel dataModel=new DataModel();
            dataModel.setName("名字:"+i);
            datas.add(dataModel);
        }
    }
    private void initView(){
        addHeardView();
        adapterTest=new GridAdapterTest(datas,this);
        gridview.setAdapter(adapterTest);
    }
    private void addHeardView(){
        heardView=getLayoutInflater().inflate(R.layout.gridview_heard, null);
        final TextView one_name=(TextView)heardView.findViewById(R.id.one_name);
        one_name.setText("hear名字");
        one_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddViewpageHeardActivity.this, one_name.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        gridview.addHeaderView(heardView);
    }
}
