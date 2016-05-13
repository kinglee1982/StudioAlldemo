package com.app.alldemo.effect;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.app.alldemo.R;
import com.app.alldemo.courview.DialogView;
import com.app.alldemo.effect.phto.ChooseImagesAct;
import com.app.alldemo.effect.phto.ChoosePhotosTestActivity;
import com.app.alldemo.effect.phto.CutCicleImagActivity;
import com.app.alldemo.effect.phto.CutCicleImagActivity2;
import com.app.alldemo.effect.phto.CutTangularImagActivity;
import com.app.alldemo.effect.phto.CutTangularImagActivity2;
import com.app.alldemo.effect.phto.CutTangularImagActivity3;
import com.app.alldemo.listenner.MyTagListenner;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.File;

public class PhtoActivity extends Activity {
    public static final int CHOOSECAMA = 1;// 拍照
    private static final int RESULT_LOAD_IMAGE = 2;// 选择照片
    private static final int CUTUPIMAG = 3;//裁剪照片
    private LinearLayout phto_layout;
    private ImageView phto_image,phto_image2,phto_cicle_image,phto_cicle_image2,phto_cicle_image3;
    private Button button1,button2;
    private String filePath = "";
    private static String path = Environment.getExternalStorageDirectory()
            .toString() + "/phto/";
    private static String suffix = ".jpg";
    private int i=0;//1:矩形；2：圆形;3：矩形缩小;4；圆形缩小
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phto);
        findUI();
    }
    private void findUI(){
        phto_layout=(LinearLayout)findViewById(R.id.phto_layout);
        phto_image=(ImageView)findViewById(R.id.phto_image);
        phto_image2=(ImageView)findViewById(R.id.phto_image2);
        phto_cicle_image=(ImageView)findViewById(R.id.phto_cicle_image);
        phto_cicle_image2=(ImageView)findViewById(R.id.phto_cicle_image2);
        phto_cicle_image3=(ImageView)findViewById(R.id.phto_cicle_image3);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        phto_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i=1;
                phtoImage();
            }
        });
        phto_cicle_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 2;
                phtoImage();
            }
        });
        phto_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 3;
                phtoImage();
            }
        });
        phto_cicle_image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 4;
                phtoImage();
            }
        });
        phto_cicle_image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i = 5;
                phtoImage();
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choosePhto();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                moreChoosePhto();
            }
        });
    }
    private void phtoImage(){
        DialogView.getInstance().avarClick(new MyTagListenner() {
            @Override
            public void onTagComplete(String values, Object object) {
                int value = (Integer) object;
                if (value == 1) {
                    selectPhto();
                } else if (value == 2) {
                    chooseInCamra();
                } else if (value == 3) {

                }
            }
        }, PhtoActivity.this, phto_layout);
    }
    /**
     * 选择照片
     */
    private void selectPhto() {
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, RESULT_LOAD_IMAGE);
    }
    /**
     * 拍摄图片
     *
     */
    public void chooseInCamra() {
        String fileName = System.currentTimeMillis() + suffix;
        filePath = path + fileName;
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File file = new File(path);
        if (!file.exists()) {
            file.mkdirs();
        }
        intent.putExtra(MediaStore.EXTRA_OUTPUT,
                Uri.fromFile(new File(path, fileName)));
        startActivityForResult(intent, CHOOSECAMA);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == Activity.RESULT_OK) {
            getSelectPhto(data);
        }
        if (requestCode == CHOOSECAMA && resultCode == Activity.RESULT_OK) {
            cutImag();
        }
        if (requestCode == CUTUPIMAG && resultCode == Activity.RESULT_OK) {
            viewImag(data);
        }
    }
    /**
     * 获取选择的照片
     */
    private void getSelectPhto(Intent data) {
        Uri selectedImage = data.getData();
        String[] filePathColumn = { MediaStore.Images.Media.DATA };
        Cursor cursor = getContentResolver().query(selectedImage,
                filePathColumn, null, null, null);
        cursor.moveToFirst();
        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        filePath = cursor.getString(columnIndex);
        cursor.close();
        cutImag();
    }
    private void cutImag() {
        Intent intent=null;
        if(i==1){
            intent=new Intent(this,CutTangularImagActivity.class);
        }else if(i==2){
            intent=new Intent(this,CutCicleImagActivity.class);
        }else if(i==3){
            intent=new Intent(this,CutTangularImagActivity2.class);
        }else if(i==4){
            intent=new Intent(this,CutCicleImagActivity2.class);
        }else if(i==5){
            intent=new Intent(this,CutTangularImagActivity3.class);
        }
        intent.putExtra("filePath", filePath);
        startActivityForResult(intent, CUTUPIMAG);
    }
    private void viewImag(Intent setdata){
        filePath=setdata.getStringExtra("filePath");
        if(i==1){
            ImageLoader.getInstance().displayImage("file://" + filePath, phto_image);
        }else if(i==2){
            ImageLoader.getInstance().displayImage("file://" + filePath, phto_cicle_image);
        }else if(i==3){
            ImageLoader.getInstance().displayImage("file://" + filePath, phto_image2);
        }else if(i==4){
            ImageLoader.getInstance().displayImage("file://" + filePath, phto_cicle_image2);
        }else if(i==5){
            ImageLoader.getInstance().displayImage("file://" + filePath, phto_cicle_image3);
        }
    }
    private void choosePhto(){
        Intent intent=new Intent(this, ChooseImagesAct.class);
        startActivity(intent);
    }
    private void moreChoosePhto(){
        Intent intent=new Intent(this, ChoosePhotosTestActivity.class);
        startActivity(intent);
    }
}
