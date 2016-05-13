package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.ToggleButton;

import com.app.alldemo.R;
import com.app.alldemo.courview.switchbuton.MySwitchButton;
import com.app.alldemo.courview.switchbuton.SwitchButton;
import com.app.alldemo.utils.MyLog;

public class SwitchActivity extends Activity{
    private SwitchButton switchButton_test;
    private ToggleButton toggle_test;
    private Switch switch_test;
    private MySwitchButton mDefaultSb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);
        switchButtonTest();
        toggleButtonTest();
        switchTest();
        mySwitch();
    }
    private void switchButtonTest(){
        switchButton_test = (SwitchButton)findViewById(R.id.switchButton_test);
        switchButton_test.setChecked(true);
        switchButton_test.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyLog.e("","switchButtonTest-isChecked:"+isChecked);
            }
        });
    }
    private void toggleButtonTest(){
        toggle_test = (ToggleButton)findViewById(R.id.toggle_test);
        toggle_test.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyLog.e("","toggleButtonTest-isChecked:"+isChecked);
                if (isChecked) {
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                }
            }
        });
    }
    private void switchTest(){
        switch_test=(Switch)findViewById(R.id.switch_test);
        switch_test.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                MyLog.e("","switchTest-isChecked:"+isChecked);
            }
        });
    }
    private void mySwitch(){
        mDefaultSb = (MySwitchButton) findViewById(R.id.sb_default);
        mDefaultSb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Log.e("", "mySwitch-isChecked:" + isChecked);
            }
        });
    }
}
