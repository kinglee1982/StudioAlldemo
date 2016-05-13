package com.app.alldemo.effect;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.alldemo.R;
import com.app.alldemo.effect.wheel.WheelActivity1;
import com.app.alldemo.effect.wheel.WheelActivity2;
import com.app.alldemo.effect.wheel.WheelActivity3;
import com.app.alldemo.effect.wheel.WheelActivity4;
import com.app.alldemo.effect.wheel.WheelActivity5;

public class WheelActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wheel);
        findUI();
    }
    private void findUI(){
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WheelActivity.this, WheelActivity1.class);
                startActivity(intent);
            }
        });
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WheelActivity.this, WheelActivity2.class);
                startActivity(intent);
            }
        });
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WheelActivity.this, WheelActivity3.class);
                startActivity(intent);
            }
        });
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WheelActivity.this, WheelActivity4.class);
                startActivity(intent);
            }
        });
        Button button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WheelActivity.this, WheelActivity5.class);
                startActivity(intent);
            }
        });
    }
}
