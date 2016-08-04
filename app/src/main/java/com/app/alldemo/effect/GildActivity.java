package com.app.alldemo.effect;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.app.alldemo.courview.RotateTransformation;
import com.bumptech.glide.Glide;

import java.io.File;

public class GildActivity extends Activity {
    private ImageView image;
    String imagePath="/storage/emulated/0/DCIM/100ANDRO/DSC_0006.JPG";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gild);
        image=(ImageView)findViewById(R.id.image);
        fileImage();
    }
    private void netUrl(){
        String url="http://52.192.66.23/data/timages/39/39_0.jpg?1454086351";
        Glide.with(this).load(Uri.parse(url)).centerCrop().into(image);
//        Glide.with(this).load(Uri.parse(url)).placeholder(new ColorDrawable(Color.WHITE)).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
    }
    private void whtest(){
        String imagePath="";
        //设置宽高
        Glide.with(this).load(new File(imagePath)).override(100, 100).centerCrop().into(image);
    }
    private void fileImage(){
        Glide.with(this).load(new File(imagePath)).centerCrop().into(image);
    }

    /**
     * 旋转90
     */
    private void rateImage() {
        Glide.with(this).load(new File(imagePath)).override(100, 100).centerCrop().transform( new RotateTransformation( this, 90f )).into(image);
    }
}
