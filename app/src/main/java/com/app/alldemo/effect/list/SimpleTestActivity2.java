package com.app.alldemo.effect.list;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.AdapterView;

import com.app.alldemo.R;
import com.app.alldemo.adapter.other.MessageAdapter;
import com.app.alldemo.model.other.DraftBoxBean;
import com.baoyz.swipemenulistview.SwipeMenu;
import com.baoyz.swipemenulistview.SwipeMenuCreator;
import com.baoyz.swipemenulistview.SwipeMenuItem;
import com.baoyz.swipemenulistview.SwipeMenuListView;

import java.util.ArrayList;

public class SimpleTestActivity2 extends Activity {
    private SwipeMenuListView mListView;
    private ArrayList<DraftBoxBean> commentBeans=new ArrayList<DraftBoxBean>();
    private MessageAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swiplist_list2);
        findUI();
    }
    private void findUI(){
        mListView = (SwipeMenuListView) findViewById(R.id.listView);
        data();
        test();
        adapter=new MessageAdapter(this, commentBeans);
        mListView.setAdapter(adapter);
    }
    private void data(){
        for(int i=0;i<10;i++){
            DraftBoxBean commentBean=new DraftBoxBean();
            commentBean.setName("名字"+i);
            commentBean.setContent("内容:"+i);
            commentBeans.add(commentBean);
        }
    }
    private void test(){
        SwipeMenuCreator creator = new SwipeMenuCreator() {
            @Override
            public void create(SwipeMenu menu) {
                // create "delete" item
                SwipeMenuItem deleteItem = new SwipeMenuItem(
                        getApplicationContext());
                // set item background
                deleteItem.setBackground(new ColorDrawable(Color.rgb(0xF9,
                        0x3F, 0x25)));
//				// set item width
                deleteItem.setWidth(dp2px(90));
                // set a icon
                deleteItem.setIcon(R.drawable.ic_delete);
                // add to menu
                menu.addMenuItem(deleteItem);
            }
        };
        mListView.setMenuCreator(creator);
        // step 2. listener item click event
        mListView.setOnMenuItemClickListener(new SwipeMenuListView.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(int position, SwipeMenu menu, int index) {
                DraftBoxBean item = commentBeans.get(position);
                switch (index) {
                    case 0:
                        // delete
//							delete(item);
                        commentBeans.remove(position);
                        adapter.notifyDataSetChanged();
                        break;
                }
                return false;
            }
        });
        // set SwipeListener
        mListView.setOnSwipeListener(new SwipeMenuListView.OnSwipeListener() {

            @Override
            public void onSwipeStart(int position) {
                // swipe start
            }

            @Override
            public void onSwipeEnd(int position) {
                // swipe end
            }
        });
    }

    private int dp2px(int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp,
                getResources().getDisplayMetrics());
    }
}
