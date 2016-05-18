package com.app.alldemo.effect;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.bumptech.glide.Glide;

public class GildActivity extends Activity {
    private ImageView image;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gild);
        image=(ImageView)findViewById(R.id.image);
        String url="http://52.192.66.23/data/timages/39/39_0.jpg?1454086351";
        Glide.with(this).load(Uri.parse(url)).centerCrop().into(image);
//        Glide.with(this).load(Uri.parse(url)).placeholder(new ColorDrawable(Color.WHITE)).diskCacheStrategy(DiskCacheStrategy.ALL).into(image);
    }
    private void findUI(){
    }
}
