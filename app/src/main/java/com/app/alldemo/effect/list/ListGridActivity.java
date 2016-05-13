package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.adapter.ListGridAdapterTest;
import com.app.alldemo.model.DataModel;

import java.util.ArrayList;
import java.util.List;

public class ListGridActivity extends Activity {
    private ListView listview;
    private List<DataModel> dataModels = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listgrid);
        findUI();
        getDatas();
        initView();
    }
    private void findUI(){
        listview=(ListView)findViewById(R.id.listview);
    }
    private void initView(){
        ListGridAdapterTest adapterTest=new ListGridAdapterTest(dataModels,this);
        listview.setAdapter(adapterTest);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Toast.makeText(ListGridActivity.this, "list点击了第" + (arg2 + 1) + "项", Toast.LENGTH_LONG).show();
            }
        });
    }
    private void getDatas(){
        String imag1="http://static.social.umeng.com/image_840dfb272f91ff9747577e0f5f226a95@360h_50Q.jpeg";
        String imag2="http://static.social.umeng.com/image_f4f6eeed90afcd4df1fcbad39f8186ee@360w_50Q.jpeg";
        String imag3="http://static.social.umeng.com/image_77706eac2771cdb3bc3aefa79dd49572@360w_50Q.jpeg";
        for(int i=0;i<10;i++){
            DataModel dataModel=new DataModel();
            if(i==0){
                dataModel.setName("我是刘德华");
                dataModel.setIrco("欢迎光临：http://blog.csdn.net/baiyuliang2013");
                dataModel.setPath(imag1);
                List<String> images=new ArrayList<>();
                images.add(imag1);
                dataModel.setImages(images);
            }else if(i==1){
                dataModel.setName("我是张学友");
                dataModel.setIrco("哈哈哈哈哈");
                dataModel.setPath(imag2);
            }else if(i==2){
                dataModel.setName("我是郭富城");
                dataModel.setIrco("嗯嗯嗯恩");
                dataModel.setPath(imag2);
                List<String> images=new ArrayList<>();
                images.add(imag1);
                images.add(imag2);
                dataModel.setImages(images);
            }else if(i==3){
                dataModel.setName("我是黎明");
                dataModel.setIrco("嗯嗯嗯恩");
                dataModel.setPath(imag1);
                List<String> images=new ArrayList<>();
                images.add(imag1);
                images.add(imag2);
                images.add(imag3);
                dataModel.setImages(images);
            }else if(i==4){
                dataModel.setName("我是杨颖");
                dataModel.setIrco("水水水水");
                dataModel.setPath(imag2);
                List<String> images=new ArrayList<>();
                images.add(imag1);
                images.add(imag2);
                images.add(imag3);
                images.add(imag3);
                images.add(imag2);
                images.add(imag1);
                dataModel.setImages(images);
            }else if(i==5){
                dataModel.setName("我是范冰冰");
                dataModel.setIrco("水水水水");
                dataModel.setPath(imag3);
                List<String> images=new ArrayList<>();
                images.add(imag1);
                images.add(imag2);
                images.add(imag3);
                images.add(imag3);
                images.add(imag2);
                dataModel.setImages(images);
            }else if(i==6){
                dataModel.setName("我是李冰冰");
                dataModel.setIrco("水水水水");
                dataModel.setPath(imag3);
                List<String> images=new ArrayList<>();
                images.add(imag1);
                images.add(imag2);
                images.add(imag3);
                images.add(imag3);
                dataModel.setImages(images);
            }else if(i==7){
                dataModel.setName("我是赵薇");
                dataModel.setIrco("水水水水");
                dataModel.setPath(imag1);
                List<String> images=new ArrayList<>();
                images.add(imag1);
                dataModel.setImages(images);
            }else if(i==8){
                dataModel.setName("古天乐");
                dataModel.setIrco("水水水水");
                dataModel.setPath(imag2);
            }else if(i==9){
                dataModel.setName("胡歌");
                dataModel.setIrco("水水水水");
                dataModel.setPath(imag3);
                List<String> images=new ArrayList<>();
                images.add(imag1);
                images.add(imag3);
                dataModel.setImages(images);
            }
            dataModels.add(dataModel);
        }
    }
}
