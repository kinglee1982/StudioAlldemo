package com.app.alldemo.effect;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.app.alldemo.R;
public class CStatueActivity extends Activity {
    private LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cstatue);
        layout = (LinearLayout) findViewById(R.id.layout);
        statueStranst();
    }

    /**
     * 4.4以上即API19以上
     */
    @TargetApi(19)
    private void statueStranst() {
        //透明状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //透明导航栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
    }
    private void findUI() {
    }
}
