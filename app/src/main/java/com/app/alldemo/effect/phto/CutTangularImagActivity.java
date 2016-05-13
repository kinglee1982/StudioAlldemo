package com.app.alldemo.effect.phto;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.phto.ClipTangImageView;
import com.app.alldemo.utils.FileUtils;
import com.app.alldemo.utils.ImageUtils;

import java.io.IOException;

public class CutTangularImagActivity extends Activity {
    private ClipTangImageView imageView;
    private TextView cutupImagConfrim,cutupImagCancel;
    private String filePath;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cut_tangular);
        init();
        findUI();
        initView();
    }
    private void init(){
        Intent intent=getIntent();
        filePath=intent.getStringExtra("filePath");
    }
    private void findUI(){
        imageView=(ClipTangImageView)findViewById(R.id.src_pic);
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
        try {
            BitmapRegionDecoder d = BitmapRegionDecoder.newInstance(imagePath, true);
            if(!d.isRecycled()){
                Bitmap bitmap = d.decodeRegion(new Rect(0, 0, d.getWidth(), d.getHeight()), null);
                imageView.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setdata(){
        Intent data=new Intent();
        String path=filePath.substring(0, filePath.lastIndexOf("/")+1);
        String fileName="cut"+filePath.substring(filePath.lastIndexOf("/")+1);
        FileUtils.getInstance().saveDataToFile(path, fileName, ImageUtils.getInstance().getBitmapBytes(imageView.clip()));
        filePath=path+fileName;
        data.putExtra("filePath", filePath);
        setResult(RESULT_OK, data);
        finish();
    }
}
