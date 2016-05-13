package com.app.alldemo.effect.seekbar;

import android.app.Activity;
import android.os.Bundle;

import com.app.alldemo.R;

public class DefaultSeekBarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_default_seekbar);
        findUI();
    }
    private void findUI(){
    }
}
