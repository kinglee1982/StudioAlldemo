package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.TuneWheel;
import com.app.alldemo.courview.TuneWheel2;

public class TuneWheelActivity extends Activity {
    private TuneWheel tuneWheel;
    private TuneWheel2 tuneWheel2;
    private TextView textview,textview2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tunewheel);
        findUI();
    }
    private void findUI(){
        tuneWheel();
        tuneWheel2();
    }
    private void tuneWheel(){
        tuneWheel=(TuneWheel)findViewById(R.id.tuneWheel);
        tuneWheel.setmMaxValue(100);
        tuneWheel.setValue(50);
        textview=(TextView)findViewById(R.id.textview);
        textview.setText("50");
        tuneWheel.setValueChangeListener(new TuneWheel.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                textview.setText(String.valueOf(value));
            }
        });
    }
    private void tuneWheel2(){
        tuneWheel2=(TuneWheel2)findViewById(R.id.tuneWheel2);
        tuneWheel2.setmMaxValue(2000);
        tuneWheel2.setValue(500);
        textview2=(TextView)findViewById(R.id.textview2);
        textview2.setText("50");
        tuneWheel2.setValueChangeListener(new TuneWheel2.OnValueChangeListener() {
            @Override
            public void onValueChange(float value) {
                textview2.setText(String.valueOf(value/10));
            }
        });
    }
}
