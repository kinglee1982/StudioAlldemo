package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.app.alldemo.R;

public class DynLineayLayoutActivity extends Activity {
    private LinearLayout dyn_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dyn_layout);
        findUI();
        addView();
    }
    private void findUI(){
        dyn_layout=(LinearLayout)findViewById(R.id.dyn_layout);
    }
    private void addView(){
        for(int i=0;i<10;i++){
            View childView =getLayoutInflater().inflate(R.layout.dyn_lineay_item, null);
            dyn_layout.addView(childView);
        }
    }
}
