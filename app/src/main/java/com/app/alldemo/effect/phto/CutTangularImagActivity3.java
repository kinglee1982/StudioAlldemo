package com.app.alldemo.effect.phto;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.phto.CutImageView;
import com.app.alldemo.utils.MyLog;

public class CutTangularImagActivity3 extends Activity {
    private CutImageView imageView;
    private TextView cutupImagConfrim,cutupImagCancel;
    private String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_tangular3);
        init();
        findUI();
        initView();
    }
    private void init(){
        Intent intent=getIntent();
        filePath=intent.getStringExtra("filePath");
    }
    private void findUI(){
        imageView=(CutImageView)findViewById(R.id.src_pic);
        cutupImagCancel=(TextView)findViewById(R.id.cutup_imag_cancel);
        cutupImagConfrim=(TextView)findViewById(R.id.cutup_imag_confrim);
        cutupImagCancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
            }
        });
        cutupImagConfrim.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                setdata();
            }
        });
    }
    private void initView(){
        loadPicture(filePath);
    }
    private void loadPicture(String imagePath){
        MyLog.e("","图片路径:"+imagePath);
        imageView.setImagePath(imagePath);
    }
    private void setdata(){
        Intent data=new Intent();
        String path = imageView.getShearResult();
        MyLog.e("","裁剪后的图片路径:"+path);
        data.putExtra("filePath", path);
        setResult(RESULT_OK, data);
        finish();
    }
}
