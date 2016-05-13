package com.app.alldemo.effect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.alldemo.R;
import com.app.alldemo.effect.uiset.EditHintActivity;
import com.app.alldemo.effect.uiset.EditSetActivity;
import com.app.alldemo.effect.uiset.HexadecimalActivity;
import com.app.alldemo.effect.uiset.LeverActivity;
import com.app.alldemo.effect.uiset.OperatorActivity;
import com.app.alldemo.effect.uiset.RelaySetActivity;
import com.app.alldemo.effect.uiset.ShapActivity;
import com.app.alldemo.effect.uiset.SoftEditActivity;
import com.app.alldemo.effect.uiset.TextSetActivity;
import com.app.alldemo.effect.uiset.TransparencyActivity;
import com.app.alldemo.effect.uiset.ViewActivity;
import com.app.alldemo.effect.uiset.XmlnsActivity;

public class SysSetActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sysset);
        findUI();
    }
    private void findUI(){
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,EditSetActivity.class);
                startActivity(intent);
            }
        });
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,LeverActivity.class);
                startActivity(intent);
            }
        });
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,TransparencyActivity.class);
                startActivity(intent);
            }
        });
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,RelaySetActivity.class);
                startActivity(intent);
            }
        });
        Button button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,ShapActivity.class);
                startActivity(intent);
            }
        });
        Button button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,TextSetActivity.class);
                startActivity(intent);
            }
        });
        Button button7=(Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,XmlnsActivity.class);
                startActivity(intent);
            }
        });
        Button button8=(Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,ViewActivity.class);
                startActivity(intent);
            }
        });
        Button button9=(Button)findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SysSetActivity.this, OperatorActivity.class);
                startActivity(intent);
            }
        });
        Button button10=(Button)findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,HexadecimalActivity.class);
                startActivity(intent);
            }
        });
        Button button11=(Button)findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,EditHintActivity.class);
                startActivity(intent);
            }
        });
        Button button12=(Button)findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SysSetActivity.this,SoftEditActivity.class);
                startActivity(intent);
            }
        });
    }
}
