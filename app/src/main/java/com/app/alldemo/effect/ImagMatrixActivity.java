package com.app.alldemo.effect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.app.alldemo.utils.MyLog;

/**
 * * 坐标矩阵
 *  float[] src = new float[]{
 *      a, b, c,//X=a*x+b*y+c
 *      d, e, f,//Y=d*x+e*y+f
 *      g, h, i,
 *  };
 *  字面上理解，矩阵中的MSCALE用于处理缩放变换，MSKEW用于处理错切变换，MTRANS用于处理平移变换，MPERSP用于处理透视变换。
 *  float[] src = new float[]{
 *      MSCALE_X, MSKEW_X, MTRANS_X,
 *      MSKEW_Y, MSCALE_Y, MTRANS_Y,
 *      MPERSP_0, MPERSP_1, MPERSP_2,
 *  };
 */
public class ImagMatrixActivity extends Activity {
    private ImageView imag;
    private Button small_button,big_button;
    private Button small_button2,big_button2,tansant_button,shear_button,rotate_button;
    private ImageView poly_imag;
    private Button poly_buton;
    private Button origin_rote_button;
    private ImageView origin_rote_image;
    private ImageView matrix_test;
    private Button translate_test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matrix);
        findUI();
        imag();
        polyImag();
    }
    private void findUI(){
        imag=(ImageView)findViewById(R.id.imag);
        small_button=(Button)findViewById(R.id.small_button);
        big_button=(Button)findViewById(R.id.big_button);
        small_button2=(Button)findViewById(R.id.small_button2);
        big_button2=(Button)findViewById(R.id.big_button2);
        tansant_button=(Button)findViewById(R.id.tansant_button);
        shear_button=(Button)findViewById(R.id.shear_button);
        rotate_button=(Button)findViewById(R.id.rotate_button);
        poly_imag=(ImageView)findViewById(R.id.poly_imag);
        poly_buton=(Button)findViewById(R.id.poly_buton);
        origin_rote_button=(Button)findViewById(R.id.origin_rote_button);
        origin_rote_image=(ImageView)findViewById(R.id.origin_rote_image);
        matrix_test=(ImageView)findViewById(R.id.matrix_test);
        translate_test=(Button)findViewById(R.id.translate_test);
        small_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scale(0.5f);
            }
        });
        big_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scale(2f);
            }
        });
        small_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                small();
            }
        });
        big_button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                big();
            }
        });
        tansant_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tansant();
            }
        });
        shear_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shear();
            }
        });
        rotate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotate();
            }
        });
        poly_buton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                poly();
            }
        });
        origin_rote_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                originRote();
            }
        });
        translate_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                translateTest();
            }
        });
    }
    private void imag(){
        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(), R.drawable.face1);
        Bitmap creatBitmap=Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
        imag.setImageBitmap(creatBitmap);
    }
    private void scale(float scale){
        Matrix matrix=new Matrix();
//        matrix.setScale(scale,scale);//set设置；pre是先乘;post是后乘
        matrix.postScale(scale, scale);
        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(), R.drawable.face1);
        Bitmap resizeBmp=Bitmap.createBitmap(bmp, 0, 0, imag.getWidth(), imag.getHeight(), matrix, true);
        imag.setImageBitmap(resizeBmp);
    }
    private void small(){
        float[] array = new float[]{
                0.5f, 0, 0,
                0,0.5f,0,
                0,0,1,
        };
        matrix(array);
    }
    private void big(){
        float[] array = new float[]{
                2f, 0, 0,
                0, 2f, 0,
                0, 0, 1,
        };
        matrix(array);
    }
    private void tansant(){
        float[] array = new float[]{
                1, 0, 50,
                0, 1, 50,
                0, 0, 1,
        };
        matrix(array);
    }
    private void shear(){
        float[] array = new float[]{
                1, 0, 50,
                2, 1, 50,
                0, 0, 1,
        };
        matrix(array);
    }
    private void rotate(){
        float[] array = new float[]{
                0, -1, 50,
                1, 0, 50,
                0, 0, 1,
        };
        matrix(array);
    }
    private void matrix(float[] array){
        Matrix matrix=new Matrix();
        matrix.setValues(array);
        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(), R.drawable.face1);
        Bitmap resizeBmp=Bitmap.createBitmap(bmp, 0, 0, imag.getWidth(), imag.getHeight(), matrix, true);
        imag.setImageBitmap(resizeBmp);
    }
    private void polyImag(){
        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(), R.drawable.comm_like_pressed);
        Bitmap creatBitmap=Bitmap.createBitmap(bmp, 0, 0, bmp.getWidth(), bmp.getHeight());
        poly_imag.setImageBitmap(creatBitmap);
    }
    private void poly(){
        Bitmap bitmap= BitmapFactory.decodeResource(this.getResources(), R.drawable.comm_like_pressed);
        Matrix matrix = new Matrix();
        float[] src = new float[] { 0, 0, // 左上
                bitmap.getWidth(), 0,// 右上
                bitmap.getWidth(), bitmap.getHeight(),// 右下
                0, bitmap.getHeight() };// 左下
        float[] dst = new float[] { 0, 0,
                bitmap.getWidth()+30, 0,
                bitmap.getWidth()+30, bitmap.getHeight() + 30,
                0,bitmap.getHeight() };
        //自由变换，对应的点变化成对应的地方
        matrix.setPolyToPoly(src, 0, dst, 0, src.length/2);
        Bitmap bm = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(),
                bitmap.getHeight(), matrix, true);
        poly_imag.setImageBitmap(bm);
    }
    private void originRote(){
        Matrix matrix=new Matrix();
        float[] array = new float[]{
                (float)Math.cos(30), -(float)Math.sin(30), 0,
                (float)Math.sin(30), (float)Math.cos(30), 0,
                0, 0, 1,
        };
        matrix.setValues(array);
        Bitmap bmp= BitmapFactory.decodeResource(this.getResources(), R.drawable.bg2);
        Bitmap resizeBmp=Bitmap.createBitmap(bmp, 0, 0, 100, 130, matrix, true);
        origin_rote_image.setImageBitmap(resizeBmp);
    }
    private void translateTest(){
        Matrix matrix =matrix_test.getImageMatrix();
        matrix.setScale(0.8f, 0.8f);
//        matrix.postTranslate(matrix_test.getWidth(),matrix_test.getHeight());
        matrix_test.setImageMatrix(matrix);
//        matrix_test.postInvalidate();
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for(int i = 0; i < 3; ++i)
        {
            String temp = new String();
            for(int j = 0; j < 3; ++j)
            {
                temp += matrixValues[3 * i + j ] + "\t";
            }
            MyLog.e("","temp:"+temp);
        }
    }
}
