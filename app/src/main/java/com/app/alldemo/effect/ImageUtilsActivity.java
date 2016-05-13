package com.app.alldemo.effect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.app.alldemo.utils.ImageUtils;

public class ImageUtilsActivity extends Activity {
    private ImageView image_core,image_scale,image_inver,imag_grey,imag_rotate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imag_utils);
        findUI();
        initView();
    }
    private void findUI(){
        image_core=(ImageView)findViewById(R.id.image_core);
        image_scale=(ImageView)findViewById(R.id.image_scale);
        image_inver=(ImageView)findViewById(R.id.image_inver);
        imag_grey=(ImageView)findViewById(R.id.imag_grey);
        imag_rotate=(ImageView)findViewById(R.id.imag_rotate);
    }
    private void initView(){
        image_core();
        image_scale();
        image_inver();
        imag_grey();
        imag_rotate();
    }
    private void image_core(){
        Drawable drawable = image_core.getDrawable();
        Bitmap bitmap = ImageUtils.getInstance().getRoundCornerBitmap(
                ImageUtils.getInstance().drawable2Bitmap(drawable),20f);
        image_core.setImageBitmap(bitmap);
    }
    private void image_scale(){
        Drawable drawable_scale = image_scale.getDrawable();
        Bitmap bitmap_scale = ImageUtils.getInstance().zoomBitmap(
                ImageUtils.getInstance().drawable2Bitmap(drawable_scale), 50, 50);
        image_scale.setImageBitmap(bitmap_scale);
    }
    private void image_inver(){
        Drawable drawable_inver = image_inver.getDrawable();
        Bitmap bitmap_inver = ImageUtils.getInstance().createReflectedBitmap(
                ImageUtils.getInstance().drawable2Bitmap(drawable_inver));
        image_inver.setImageBitmap(bitmap_inver);
    }
    private void imag_grey() {
        Drawable drawable_grey = getResources().getDrawable(R.drawable.bg2);
        Bitmap bitmap_grey = ((BitmapDrawable) drawable_grey).getBitmap();
        Bitmap bitmap_grey2 = ImageUtils.getInstance().grey(bitmap_grey);
        imag_grey.setImageBitmap(bitmap_grey2);
    }
    private void imag_rotate(){
        Drawable drawable_grey = getResources().getDrawable(R.drawable.bg1);
        Bitmap tmpBitmap = ((BitmapDrawable) drawable_grey).getBitmap();
        Bitmap tmpBitmap2 = ImageUtils.getInstance().postRotateBitamp(tmpBitmap, 90);
        imag_rotate.setImageBitmap(tmpBitmap2);
    }
}
