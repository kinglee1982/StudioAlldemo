package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.NumberPicker;
import android.widget.TimePicker;

import com.app.alldemo.R;

public class TimerActivity extends Activity {
    private DatePicker datePicker;
    private TimePicker timePicker;
    private NumberPicker num_picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        findUI();
    }
    private void findUI(){
        Button button1=(Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewData();
            }
        });
        Button button2=(Button)findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timeData();
            }
        });
        Button button3=(Button)findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numData();
            }
        });
        datePicker=(DatePicker)findViewById(R.id.data_test);
        timePicker=(TimePicker)findViewById(R.id.timePicker);
        num_picker=(NumberPicker)findViewById(R.id.num_picker);
    }
    private void viewData(){
        datePicker.setVisibility(View.VISIBLE);
        timePicker.setVisibility(View.GONE);
        num_picker.setVisibility(View.GONE);
    }
    private void timeData(){
        datePicker.setVisibility(View.GONE);
        timePicker.setVisibility(View.VISIBLE);
        num_picker.setVisibility(View.GONE);
    }
    private void numData(){
        datePicker.setVisibility(View.GONE);
        timePicker.setVisibility(View.GONE);
        num_picker.setVisibility(View.VISIBLE);
        num_picker.setMaxValue(999);
        num_picker.setMinValue(0);
        num_picker.setValue(20);
    }
}
