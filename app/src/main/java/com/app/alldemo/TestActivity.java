package com.app.alldemo;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

import com.app.alldemo.utils.HttpTool;

import org.apache.http.protocol.HTTP;

public class TestActivity extends Activity {
    private Button button,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        findUI();
    }
    private void findUI(){
        String url="www.baidu.com";
        HttpTool.getInstance().postByteEntityData(url,null, HTTP.UTF_8);
    }
}
