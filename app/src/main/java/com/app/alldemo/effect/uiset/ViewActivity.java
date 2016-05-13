package com.app.alldemo.effect.uiset;

import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.app.alldemo.R;

public class ViewActivity extends Activity {
    private LinearLayout lineay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        findUI();
    }
    private void findUI(){
        lineay=(LinearLayout)findViewById(R.id.lineay);
        lineay.getBackground().setLevel(2);
    }
}
