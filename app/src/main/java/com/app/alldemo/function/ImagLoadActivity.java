package com.app.alldemo.function;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.utils.ImageLoderTest;
import com.app.alldemo.utils.ImageUtils;
import com.app.alldemo.utils.MyLog;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;

public class ImagLoadActivity extends Activity {
    private ImageView imagview,imagview2,image_load;
    private Button load_button;
    private RelativeLayout progress_layout;
    private TextView progress_text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imagload);
        findUI();
        test();
        test2();
    }
    private void findUI(){
        imagview=(ImageView)findViewById(R.id.imagview);
        imagview2=(ImageView)findViewById(R.id.imagview2);
        image_load=(ImageView)findViewById(R.id.image_load);
        load_button=(Button)findViewById(R.id.load_button);
        progress_layout=(RelativeLayout)findViewById(R.id.progress_layout);
        progress_text=(TextView)findViewById(R.id.progress_text);
        load_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageLoadUrl();
            }
        });
    }
    private void test(){
        String url="http://img.xiami.net/images/album/img21/55621/20295839391429583961_5.jpg";
        ImageLoader.getInstance().displayImage(
                url, imagview);
    }
    private void test2(){
        Bitmap bitmap=ImageUtils.getInstance().getImageFromAssetsFile(this, "/assets/imageload/test.jpg");
        MyLog.e("", "bitmap:"+bitmap);
        if(bitmap!=null){
            imagview2.setImageBitmap(bitmap);
        }
    }

    /**
     * 显示asserts下的图片
     */
    private void test3(){
        ImageLoader.getInstance().displayImage("assets://welcome/welcome4.png",imagview2);
        ImageLoader.getInstance().displayImage("file://",imagview2);
    }
    private void imageLoadUrl(){
        progress_layout.setVisibility(View.GONE);
        String url="http://52.192.66.23/data/timages/199/199_1.jpg";
        ImageLoader.getInstance().displayImage(url,image_load,
                ImageLoderTest.getInstance().getoptions(),ImageLoderTest.getInstance().getanimateListener()
                , new ImageLoadingProgressListener() {
                    @Override
                    public void onProgressUpdate(String s, View view, int i, int i1) {
//                        MyLog.e("","s:"+s+"i:"+i+"i1:"+i1);
                        int precent=(int)(((float)i/(float)i1)*100);
                        if(precent==100){
                            progress_layout.setVisibility(View.GONE);
                        }else{
                            progress_layout.setVisibility(View.VISIBLE);
                            progress_text.setText(precent+"%");
                        }
                        MyLog.e("","precent:"+precent);
                    }
                });
    }
}
