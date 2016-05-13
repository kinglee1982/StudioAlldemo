package com.app.alldemo.effect.seekbar;

import android.app.Activity;
import android.os.Bundle;

import com.app.alldemo.R;
import com.app.alldemo.courview.seekbar.ProgressWheel;

public class ProgressWheelActivity extends Activity {
    private ProgressWheel progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_wheel);
        findUI();
    }
    private void findUI(){
        progressBar=(ProgressWheel)findViewById(R.id.progressBar);
        progressBar.setProgress(60);// progressBar的最大值是360
    }
}
