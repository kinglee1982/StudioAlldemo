package com.app.alldemo.effect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.alldemo.R;
import com.app.alldemo.effect.seekbar.AnminBarActivity;
import com.app.alldemo.effect.seekbar.CircleBarBarTestActivity;
import com.app.alldemo.effect.seekbar.CircularProgressActivity;
import com.app.alldemo.effect.seekbar.DefaultSeekBarActivity;
import com.app.alldemo.effect.seekbar.DigitalBarActivity;
import com.app.alldemo.effect.seekbar.MyProgressActivity;
import com.app.alldemo.effect.seekbar.MyVerSeekBarActivity;
import com.app.alldemo.effect.seekbar.ProgressWheelActivity;
import com.app.alldemo.effect.seekbar.SeekBarTestActivity;
import com.app.alldemo.effect.seekbar.VerticalSeekBarActivity;

public class SeekBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);
        findUI();
    }
    private void findUI(){
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, DefaultSeekBarActivity.class);
                startActivity(intent);
            }
        });
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, VerticalSeekBarActivity.class);
                startActivity(intent);
            }
        });
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, CircleBarBarTestActivity.class);
                startActivity(intent);
            }
        });
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, ProgressWheelActivity.class);
                startActivity(intent);
            }
        });
        Button button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, MyProgressActivity.class);
                startActivity(intent);
            }
        });
        Button button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, MyVerSeekBarActivity.class);
                startActivity(intent);
            }
        });
        Button button7=(Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, SeekBarTestActivity.class);
                startActivity(intent);
            }
        });
        Button button8=(Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, DigitalBarActivity.class);
                startActivity(intent);
            }
        });
        Button button9=(Button)findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, AnminBarActivity.class);
                startActivity(intent);
            }
        });
        Button button10=(Button)findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SeekBarActivity.this, CircularProgressActivity.class);
                startActivity(intent);
            }
        });
    }
}
