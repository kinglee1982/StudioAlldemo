package com.app.alldemo.effect.viewgrid;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alldemo.R;

public class HscrollorActivity extends Activity {
    private LinearLayout galleryLineay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hscrollor);
        findUI();
        addChildView();
    }
    private void findUI(){
        galleryLineay=(LinearLayout)findViewById(R.id.gallery_lineay);
    }
    private void addChildView(){
        for(int i=0;i<10;i++){
            View view = LayoutInflater.from(this).inflate(R.layout.scrollor_item, galleryLineay, false);
            final TextView viewText=(TextView)view.findViewById(R.id.text);
            viewText.setText("测试"+i);
            viewText.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String textValue=viewText.getText().toString();
                    Toast.makeText(HscrollorActivity.this,textValue,Toast.LENGTH_SHORT).show();
                }
            });
            galleryLineay.addView(view);
        }
    }
}
