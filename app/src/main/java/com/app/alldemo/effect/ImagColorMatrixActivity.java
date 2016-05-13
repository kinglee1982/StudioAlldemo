package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.app.alldemo.utils.ViewUtils;

public class ImagColorMatrixActivity extends Activity {
    private Button rimag_button;
    private ImageView rimag,yimag,image2;
    private ImageView bright_image,dark_image,contrast_image,low_contrast_image,hight_saturated_image,low_saturated_image,red_hue_image,green_hue_image;
    private Button bright_button,dark_button,contrast_button,low_contrast_button,hight_saturated_button,low_saturated_button,red_hue_button,green_hue_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imag_rgb);
        findUI();
    }
    private void findUI(){
        rimag_button=(Button)findViewById(R.id.rimag_button);
        rimag=(ImageView)findViewById(R.id.rimag);
        yimag=(ImageView)findViewById(R.id.yimag);
        image2=(ImageView)findViewById(R.id.image2);
        bright_image=(ImageView)findViewById(R.id.bright_image);
        bright_button=(Button)findViewById(R.id.bright_button);
        dark_button=(Button)findViewById(R.id.dark_button);
        dark_image=(ImageView)findViewById(R.id.dark_image);
        contrast_button=(Button)findViewById(R.id.contrast_button);
        contrast_image=(ImageView)findViewById(R.id.contrast_image);
        low_contrast_button=(Button)findViewById(R.id.low_contrast_button);
        low_contrast_image=(ImageView)findViewById(R.id.low_contrast_image);
        hight_saturated_button=(Button)findViewById(R.id.hight_saturated_button);
        hight_saturated_image=(ImageView)findViewById(R.id.hight_saturated_image);
        low_saturated_button=(Button)findViewById(R.id.low_saturated_button);
        low_saturated_image=(ImageView)findViewById(R.id.low_saturated_image);
        red_hue_button=(Button)findViewById(R.id.red_hue_button);
        red_hue_image=(ImageView)findViewById(R.id.red_hue_image);
        green_hue_button=(Button)findViewById(R.id.green_hue_button);
        green_hue_image=(ImageView)findViewById(R.id.green_hue_image);
        rimag_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rImage();
            }
        });
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                yImage();
            }
        });
        bright_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bright();
            }
        });
        dark_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dark();
            }
        });
        contrast_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                contrast();
            }
        });
        low_contrast_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowcontrast();
            }
        });
        hight_saturated_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hightSaturated();
            }
        });
        low_saturated_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lowSaturated();
            }
        });
        red_hue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redHue();
            }
        });
        green_hue_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                greenHue();
            }
        });
    }
    private void rImage(){
        rimag.setImageBitmap(ViewUtils.getInstance().getRGBBitmap(this, R.drawable.face1));
    }
    private void yImage(){
        yimag.setImageBitmap(ViewUtils.getInstance().getYellowBitmap(this, R.drawable.face3));
    }
    private void bright(){
        float[] src = new float[]{
                1, 0, 0, 0, 100,
                0, 1, 0, 0, 100,
                0, 0, 1, 0, 100,
                0, 0, 0, 1, 0
        };
        bright_image.setImageBitmap(ViewUtils.getInstance().getColorBitmap(this,R.drawable.bg2,src));
    }
    private void dark(){
        float[] src = new float[]{
                1, 0, 0, 0, -100,
                0, 1, 0, 0, -100,
                0, 0, 1, 0, -100,
                0, 0, 0, 1, 0
        };
        dark_image.setImageBitmap(ViewUtils.getInstance().getColorBitmap(this,R.drawable.bg2,src));
    }
    private void contrast(){
        float[] src = new float[]{
                5, 0, 0, 0, -254,
                0, 5, 0, 0, -254,
                0, 0, 5, 0, -254,
                0, 0, 0, 1, 0
        };
        contrast_image.setImageBitmap(ViewUtils.getInstance().getColorBitmap(this,R.drawable.bg2,src));
    }
    private void lowcontrast(){
        float[] src = new float[]{
                0.2f, 0, 0, 0, 50.8f,
                0, 0.2f, 0, 0, 50.8f,
                0, 0, 0.2f, 0, 50.8f,
                0, 0, 0, 1, 0
        };
        low_contrast_image.setImageBitmap(ViewUtils.getInstance().getColorBitmap(this,R.drawable.bg2,src));
    }
    private void hightSaturated(){
        float[] src = new float[]{
                3.074f, -1.82f, -0.24f, 0, 50.8f,
                -0.92f, 2.171f, -0.24f, 0, 50.8f,
                -0.92f, -1.82f, 3.754f, 0, 50.8f,
                0, 0, 0, 1, 0
        };
        hight_saturated_image.setImageBitmap(ViewUtils.getInstance().getColorBitmap(this,R.drawable.bg2,src));
    }
    private void lowSaturated(){
        float[] src = new float[]{
                0.308f, 0.609f, 0.082f, 0, 0,
                0.308f, 0.609f, 0.082f, 0, 0,
                0.308f, 0.609f, 0.082f, 0, 0,
                0, 0, 0, 1, 0
        };
        low_saturated_image.setImageBitmap(ViewUtils.getInstance().getColorBitmap(this,R.drawable.bg2,src));
    }
    private void redHue(){
        float[] src = new float[]{
                -0.36f, 1.691f, -0.32f, 0, 0,
                0.325f, 0.398f, 0.275f, 0, 0,
                0.79f, 0.796f, -0.76f, 0, 0,
                0, 0, 0, 1, 0
        };
        red_hue_image.setImageBitmap(ViewUtils.getInstance().getColorBitmap(this,R.drawable.bg2,src));
    }
    private void greenHue(){
        float[] src = new float[]{
                -0.41f, 0.539f, 0.873f, 0, 0,
                0.452f, 0.666f, -0.11f, 0, 0,
                -0.30f, 0.796f, -0.76f, 0, 0,
                0, 0, 0, 1, 0
        };
        green_hue_image.setImageBitmap(ViewUtils.getInstance().getColorBitmap(this,R.drawable.bg2,src));
    }
}
