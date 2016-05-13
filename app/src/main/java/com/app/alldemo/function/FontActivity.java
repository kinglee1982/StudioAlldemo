package com.app.alldemo.function;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import com.app.alldemo.R;
import com.loopj.android.http.AsyncHttpClient;
import com.uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class FontActivity extends Activity {
    private AsyncHttpClient client;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font);
        findUI();
    }
    //添加字体，个别字体设置的话，需要添加这个
    @Override
    protected void attachBaseContext(Context newBase) {
        // TODO Auto-generated method stub
        super.attachBaseContext(new CalligraphyContextWrapper(newBase));
    }
    private void findUI(){
    }

}
