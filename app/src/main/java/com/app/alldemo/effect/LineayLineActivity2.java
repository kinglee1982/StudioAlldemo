package com.app.alldemo.effect;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.WordWrapView;

import java.util.ArrayList;
import java.util.List;
public class LineayLineActivity2 extends Activity {
    List<String> topsList=new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_line2);
        findUI();
        getdatas();
        test();
    }
    private void findUI(){
    }
    private void getdatas(){
        topsList.add("搜");
        topsList.add("搜索凤");
        topsList.add("搜索东方");
        topsList.add("搜索");
        topsList.add("搜索得得得凤飞飞");
        topsList.add("搜索得");
        topsList.add("搜索顶顶顶顶");
        topsList.add("搜索的");
        topsList.add("搜索得得得");
        topsList.add("搜索");
        topsList.add("搜");
        topsList.add("搜索东");
        topsList.add("搜索东方发送的");
        topsList.add("搜索东方送的");
        topsList.add("方发送的");
        topsList.add("搜索东送的");
        topsList.add("搜索送的");
        topsList.add("搜索东");
        topsList.add("搜索东方发送的");
    }
    private void test(){
        WordWrapView fixGridLayout = (WordWrapView) findViewById(R.id.top_lineay);
        for (String string:topsList) {
            final TextView textView = new TextView(this);
            textView.setTextSize(18);
            textView.setTextColor(Color.RED);
            textView.setBackgroundResource(R.drawable.light_blue_solid_arc);
            textView.setOnClickListener(new OnClickListener() {

                @Override
                public void onClick(View v) {
                    Log.v("MyActivity", "点击");
                    textView.setTextColor(Color.BLUE);
                    textView.setBackgroundResource(R.drawable.navy_blue_solid_arc);
                }
            });
            textView.setText(string);
            fixGridLayout.addView(textView);
        }
    }
}
