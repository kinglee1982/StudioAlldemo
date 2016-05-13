package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.ListView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.QQAdapter;
import com.app.alldemo.listenner.MyTagListenner;
import com.app.alldemo.model.other.SName;
import com.app.alldemo.utils.MyLog;

import java.util.ArrayList;
import java.util.List;
public class ListViewSectionActivity extends Activity {
    private static final String TAG="ListViewSectionActivity";
    private Button button, button2, button3,button4;
    private ListView friends_list;
    private List<SName> snames = new ArrayList<SName>();
    private QQAdapter adpter;
    private Handler mhandler = new Handler() {
        // Handler处理消息，异步
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    friends_list.setSelection(3);
                    break;
            }
            super.handleMessage(msg);
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_selection);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        friends_list = (ListView) findViewById(R.id.friends_list);
        getdatas();
        adpter = new QQAdapter(this, snames, new MyTagListenner() {

            @Override
            public void onTagComplete(String values, Object object) {
            }
        });
        friends_list.setAdapter(adpter);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                flashView1();
            }
        });
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                flashView2();
            }
        });
        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                flashView3();
            }
        });
        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                topSrollo();
            }
        });
    }

    private void flashView1() {
        adpter = new QQAdapter(this, snames, new MyTagListenner() {

            @Override
            public void onTagComplete(String values, Object object) {
            }
        });
        friends_list.setAdapter(adpter);
        friends_list.setSelection(5);
    }

    /**
     * 跳转时主线程再做其他操作,放子线程刷新
     */
    private void flashView2() {
        new Thread() {
            @Override
            public void run() {
                Message msg = mhandler.obtainMessage();
                msg.what = 1;
                mhandler.sendMessage(msg);
            }
        }.start();
    }

    /**
     * 计算位置
     */
    private void flashView3() {
        friends_list.requestFocus();
        friends_list.setItemChecked(8, true);
        friends_list.setSelection(8);
        //每个item高度一致才行,跳转到一个绝对位置,以第一个item为准,这个有个平滑效果
        friends_list.smoothScrollToPosition(8);
    }
    /**
     * 平滑到底部
     */
    private void topSrollo(){
        friends_list.smoothScrollToPosition(0);
        friends_list.setOnScrollListener(new OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {
                MyLog.v(TAG, "firstVisibleItem:" + firstVisibleItem + "totalItemCount:" + totalItemCount);
//				if(firstVisibleItem == 0) {
//					friends_list.setOnScrollListener(null);
//                }
            }
        });
    }

    private void getdatas() {
        String urlString = "http://static.social.umeng.com/image_840dfb272f91ff9747577e0f5f226a95@360h_50Q.jpeg";
        String urlString2 = "http://static.social.umeng.com/image_f4f6eeed90afcd4df1fcbad39f8186ee@360w_50Q.jpeg";
        String urlString3 = "http://static.social.umeng.com/image_77706eac2771cdb3bc3aefa79dd49572@360w_50Q.jpeg";
        for (int i = 0; i < 20; i++) {
            SName sName = new SName();
            sName.setName1("1名字" + i);
            sName.setName2("二名字" + i);
            List<String> strings = new ArrayList<String>();
            if (i == 0) {
                strings.add(urlString);
            }
            if (i == 3) {
                strings.add(urlString);
                strings.add(urlString2);
                strings.add(urlString3);
            }
            if (i == 4) {
                strings.add(urlString);
                strings.add(urlString2);
            }
            if (i == 7) {
                strings.add(urlString);
                strings.add(urlString2);
                strings.add(urlString3);
            }
            if (i == 10) {
                strings.add(urlString3);
            }
            sName.setImages(strings);
            snames.add(sName);
        }
    }
}
