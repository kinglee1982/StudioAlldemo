package com.app.alldemo.effect.wheel;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.wheel.CalBaseDialog;
import com.app.alldemo.courview.wheel.LoveDialog;
import com.app.alldemo.courview.wheel.TemperatureDialog;
import com.app.alldemo.utils.MyLog;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class WheelActivity6 extends Activity {
    private Button button;
    private TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_testwheel);
        findUI();
        getPickers();
        getPickers2();
    }
    private void findUI(){
        button=(Button)findViewById(R.id.button);
        text=(TextView)findViewById(R.id.text);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loveTest();
            }
        });
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogTwo();
            }
        });
    }
    private int singlePosition;
    private void loveTest(){
        List<String> mPickerTexts=new ArrayList<>();
        mPickerTexts.add("我们");
        mPickerTexts.add("他们");
        mPickerTexts.add("你们");
        mPickerTexts.add("大家庭");
        new LoveDialog(this, singlePosition,mPickerTexts, new CalBaseDialog.Callback() {
            @Override
            public void onCallback(Object value) {
                singlePosition=(Integer)value;
                Log.e("", "结果:" + value);
            }
        }).show();
    }
    private int mPicker1Position;
    private List<String> mPicker1Values=new ArrayList<>();
    private void getPickers(){
        mPicker1Values.add("00");
        mPicker1Values.add("01");
        mPicker1Values.add("02");
        mPicker1Values.add("03");
        mPicker1Values.add("04");
        mPicker1Values.add("05");
        mPicker1Values.add("06");
    }
    private int mPicker2Position;
    private List<String> mPicker2Values=new ArrayList<>();
    private void getPickers2(){
        mPicker2Values.add("10");
        mPicker2Values.add("11");
        mPicker2Values.add("12");
        mPicker2Values.add("13");
        mPicker2Values.add("14");
        mPicker2Values.add("15");
        mPicker2Values.add("16");
    }
    private void dialogTwo(){
        TemperatureDialog dialog = new TemperatureDialog(this,mPicker1Values,mPicker1Position,mPicker2Values,mPicker2Position
                , new CalBaseDialog.Callback() {
            @Override
            public void onCallback(Object value) {
                Map<String,Integer> hashMap=(Map<String,Integer>)value;
                int picker1Position=hashMap.get("picker1");
                int picker2Position=hashMap.get("picker2");
                MyLog.e("","picker1Position:"+picker1Position+"picker2Position:"+picker2Position);
            }
        });
        dialog.show();
    }
}
