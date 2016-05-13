package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.BadgeKeywordsFlow;

import java.util.Random;

public class SearchFlyActivity extends Activity{
    public static final String[] keywords = { "QQ", "BaseAnimation", "APK",
            "GFW", "铅笔",//
            "短信", "桌面精灵", "MacBook Pro", "平板电脑", "雅诗兰黛",//
            "Base", "笔记本", "SPY Mouse", "Thinkpad E40", "捕鱼达人",//
            "内存清理", "地图", "导航", "闹钟", "主题",//
            "通讯录", "播放器", "CSDN leak", "安全", "Animation",//
            "美女", "天气", "4743G", "戴尔", "联想",//
            "欧朋", "浏览器", "愤怒的小鸟", "mmShow", "网易公开课",//
            "iciba", "油水关系", "网游App", "互联网", "365日历",//
            "脸部识别", "Chrome", "Safari", "中国版Siri", "苹果",//
            "iPhone5S", "摩托 ME525", "魅族 MX3", "小米" };
    private BadgeKeywordsFlow keywordsFlow;
    private Button btnIn, btnOut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchfly);
        findUI();
        initView();
    }
    private void findUI(){
        btnIn = (Button) findViewById(R.id.button1);
        btnOut = (Button) findViewById(R.id.button2);
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keywordsFlow.rubKeywords();
                // keywordsFlow.rubAllViews();
                feedKeywordsFlow(keywordsFlow, keywords);
                keywordsFlow.go2Show(BadgeKeywordsFlow.ANIMATION_IN);
            }
        });
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keywordsFlow.rubKeywords();
                // keywordsFlow.rubAllViews();
                feedKeywordsFlow(keywordsFlow, keywords);
                keywordsFlow.go2Show(BadgeKeywordsFlow.ANIMATION_OUT);
            }
        });
        keywordsFlow = (BadgeKeywordsFlow) findViewById(R.id.frameLayout1);
        keywordsFlow.setDuration(800l);
        keywordsFlow.setOnItemClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = ((TextView) v).getText().toString();
                System.out.println(keyword);
            }
        });
    }
    public void initView() {
        feedKeywordsFlow(keywordsFlow, keywords);
        keywordsFlow.go2Show(BadgeKeywordsFlow.ANIMATION_IN);

    }
    private static void feedKeywordsFlow(BadgeKeywordsFlow keywordsFlow, String[] arr) {
        Random random = new Random();
        for (int i = 0; i < BadgeKeywordsFlow.MAX; i++) {
            int ran = random.nextInt(arr.length);
            String tmp = arr[ran];
            keywordsFlow.feedKeyword(tmp);
        }
    }
}
