package com.app.alldemo.effect.list;

import android.app.Activity;
import android.content.Context;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.MyAdapter;
import com.app.alldemo.courview.list.listLetterSideBar;
import com.app.alldemo.effect.list.utils.GB2Alpha;
import com.app.alldemo.effect.list.utils.PinyinComparator;
import com.app.alldemo.model.other.SortModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class ListLetterActivity extends Activity {
    private ListView mListView;
    private listLetterSideBar indexBar;
    private WindowManager mWindowManager;
    private TextView mDialogText;
    private View head;
    List<SortModel> list = new ArrayList<SortModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //启动activity时不自动弹出软键盘
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        setContentView(R.layout.activity_letter);
        findUI();
    }
    private void findUI(){
        mListView = (ListView) this.findViewById(R.id.list);
        indexBar = (listLetterSideBar) findViewById(R.id.sideBar);
        mDialogText = (TextView) LayoutInflater.from(this).inflate(R.layout.list_letter_position, null);
        head = LayoutInflater.from(this).inflate(R.layout.list_letter_head, null);
        mListView.addHeaderView(head);
        mDialogText.setVisibility(View.INVISIBLE);
        mWindowManager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams(
                LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT,
                WindowManager.LayoutParams.TYPE_APPLICATION,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                        | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
                PixelFormat.TRANSLUCENT);
        mWindowManager.addView(mDialogText, lp);
        indexBar.setTextView(mDialogText);
        data();
        //根据a-z进行排序
        Collections.sort(list, new PinyinComparator());
        // 实例化自定义内容适配类
        MyAdapter adapter = new MyAdapter(this, list);
        // 为listView设置适配
        mListView.setAdapter(adapter);
        //设置SideBar的ListView内容实现点击a-z中任意一个进行定位
        indexBar.setListView(mListView);
    }
    private void data(){
        GB2Alpha gb2Alpha = new GB2Alpha();
        SortModel content = new SortModel();
        content.setName("为借口所见即所得");
        content.setSortLetters(gb2Alpha.String2Alpha("为借口所见即所得"));
        list.add(content);
        SortModel content2 = new SortModel();
        content2.setName("很好合适的");
        content2.setSortLetters(gb2Alpha.String2Alpha("很好合适的"));
        list.add(content2);
        SortModel content3 = new SortModel();
        content3.setName("喝口水狙击手");
        content3.setSortLetters(gb2Alpha.String2Alpha("喝口水狙击手"));
        list.add(content3);
        SortModel content4 = new SortModel();
        content4.setName("啊事实上");
        content4.setSortLetters(gb2Alpha.String2Alpha("啊事实上"));
        list.add(content4);
        SortModel content5 = new SortModel();
        content5.setName("被搜索");
        content5.setSortLetters(gb2Alpha.String2Alpha("被搜索"));
        list.add(content5);
    }
}
