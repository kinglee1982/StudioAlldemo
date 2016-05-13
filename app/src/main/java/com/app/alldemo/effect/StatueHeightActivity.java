package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.utils.ViewUtils;

public class StatueHeightActivity extends Activity {
    private TextView statue_height;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statue_height);
        statue_height=(TextView)findViewById(R.id.statue_height);
        int height=ViewUtils.getInstance().getStatusBarHeight(this);
        statue_height.setText("状态栏的高度"+height);
    }
    private void findUI(){
    }
}
