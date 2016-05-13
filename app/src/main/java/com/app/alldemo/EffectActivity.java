package com.app.alldemo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.app.alldemo.effect.AcActivity;
import com.app.alldemo.effect.AutoCompleteTextViewActivity;
import com.app.alldemo.effect.CStatueActivity;
import com.app.alldemo.effect.CircleBakActivity;
import com.app.alldemo.effect.CircularmenuActivity;
import com.app.alldemo.effect.DialogActivity;
import com.app.alldemo.effect.DrawTestActivity;
import com.app.alldemo.effect.DynLineayLayoutActivity;
import com.app.alldemo.effect.EditextIncoActivity;
import com.app.alldemo.effect.ImagColorMatrixActivity;
import com.app.alldemo.effect.ImagMatrixActivity;
import com.app.alldemo.effect.ImageUtilsActivity;
import com.app.alldemo.effect.LineayLineActivity;
import com.app.alldemo.effect.LineayLineActivity2;
import com.app.alldemo.effect.ListViewTestActivity;
import com.app.alldemo.effect.MatrixTestActivity;
import com.app.alldemo.effect.MyVideoActivity;
import com.app.alldemo.effect.PasswordActivity;
import com.app.alldemo.effect.PhtoActivity;
import com.app.alldemo.effect.PopuActivity;
import com.app.alldemo.effect.SearchBoxActivity;
import com.app.alldemo.effect.SearchFlyActivity;
import com.app.alldemo.effect.SeekBarActivity;
import com.app.alldemo.effect.StatueHeightActivity;
import com.app.alldemo.effect.SwitchActivity;
import com.app.alldemo.effect.SysSetActivity;
import com.app.alldemo.effect.TextViewIncoActivity;
import com.app.alldemo.effect.TimerActivity;
import com.app.alldemo.effect.TuneWheelActivity;
import com.app.alldemo.effect.ViewGridActivity;
import com.app.alldemo.effect.ViewTreeObserverActivity;
import com.app.alldemo.effect.WheelActivity;
import com.app.alldemo.effect.wheel.WheelActivity6;

public class EffectActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.effect);
        findUI();
    }
    private void findUI(){
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, ListViewTestActivity.class);
                startActivity(intent);
            }
        });
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, TimerActivity.class);
                startActivity(intent);
            }
        });
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, SysSetActivity.class);
                startActivity(intent);
            }
        });
        Button button4=(Button)findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, DrawTestActivity.class);
                startActivity(intent);
            }
        });
        Button button5=(Button)findViewById(R.id.button5);
        button5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, SearchFlyActivity.class);
                startActivity(intent);
            }
        });
        Button button6=(Button)findViewById(R.id.button6);
        button6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, CircularmenuActivity.class);
                startActivity(intent);
            }
        });
        Button button7=(Button)findViewById(R.id.button7);
        button7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, ViewTreeObserverActivity.class);
                startActivity(intent);
            }
        });
        Button button8=(Button)findViewById(R.id.button8);
        button8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, TuneWheelActivity.class);
                startActivity(intent);
            }
        });
        Button button9=(Button)findViewById(R.id.button9);
        button9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, WheelActivity.class);
                startActivity(intent);
            }
        });
        Button button10=(Button)findViewById(R.id.button10);
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, ViewGridActivity.class);
                startActivity(intent);
            }
        });
        Button button11=(Button)findViewById(R.id.button11);
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, ImageUtilsActivity.class);
                startActivity(intent);
            }
        });
        Button button12=(Button)findViewById(R.id.button12);
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, DialogActivity.class);
                startActivity(intent);
            }
        });
        Button button13=(Button)findViewById(R.id.button13);
        button13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, PhtoActivity.class);
                startActivity(intent);
            }
        });
        Button button14=(Button)findViewById(R.id.button14);
        button14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, PopuActivity.class);
                startActivity(intent);
            }
        });
        Button button15=(Button)findViewById(R.id.button15);
        button15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, SearchBoxActivity.class);
                startActivity(intent);
            }
        });
        Button button16=(Button)findViewById(R.id.button16);
        button16.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, MyVideoActivity.class);
                startActivity(intent);
            }
        });
        Button button17=(Button)findViewById(R.id.button17);
        button17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, SeekBarActivity.class);
                startActivity(intent);
            }
        });
        Button button18=(Button)findViewById(R.id.button18);
        button18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, CStatueActivity.class);
                startActivity(intent);
            }
        });
        Button button19=(Button)findViewById(R.id.button19);
        button19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, StatueHeightActivity.class);
                startActivity(intent);
            }
        });
        Button button20=(Button)findViewById(R.id.button20);
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, EditextIncoActivity.class);
                startActivity(intent);
            }
        });
        Button button21=(Button)findViewById(R.id.button21);
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, TextViewIncoActivity.class);
                startActivity(intent);
            }
        });
        Button button22=(Button)findViewById(R.id.button22);
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, LineayLineActivity.class);
                startActivity(intent);
            }
        });
        Button button23=(Button)findViewById(R.id.button23);
        button23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, LineayLineActivity2.class);
                startActivity(intent);
            }
        });
        Button button24=(Button)findViewById(R.id.button24);
        button24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, DynLineayLayoutActivity.class);
                startActivity(intent);
            }
        });
        Button button25=(Button)findViewById(R.id.button25);
        button25.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, CircleBakActivity.class);
                startActivity(intent);
            }
        });
        Button button26=(Button)findViewById(R.id.button26);
        button26.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, AcActivity.class);
                startActivity(intent);
            }
        });
        Button button27=(Button)findViewById(R.id.button27);
        button27.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, PasswordActivity.class);
                startActivity(intent);
            }
        });
        Button button28=(Button)findViewById(R.id.button28);
        button28.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, SwitchActivity.class);
                startActivity(intent);
            }
        });
        Button button29=(Button)findViewById(R.id.button29);
        button29.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, AutoCompleteTextViewActivity.class);
                startActivity(intent);
            }
        });
        Button button30=(Button)findViewById(R.id.button30);
        button30.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, WheelActivity6.class);
                startActivity(intent);
            }
        });
        Button button31=(Button)findViewById(R.id.button31);
        button31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, ImagColorMatrixActivity.class);
                startActivity(intent);
            }
        });
        Button button32=(Button)findViewById(R.id.button32);
        button32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, ImagMatrixActivity.class);
                startActivity(intent);
            }
        });
        Button button33=(Button)findViewById(R.id.button33);
        button33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EffectActivity.this, MatrixTestActivity.class);
                startActivity(intent);
            }
        });
    }
}
