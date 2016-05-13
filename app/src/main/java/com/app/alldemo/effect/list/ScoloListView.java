package com.app.alldemo.effect.list;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.alldemo.R;

import java.lang.reflect.Field;

public class ScoloListView extends Activity {
    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scol_list);
        lv = (ListView) findViewById(R.id.listView1);
//        commonly();
        lv.setAdapter(new ListAdapter());
    }
    /**
     * ScrollBar很常用的一种效果
     */
    private void commonly(){
        lv.setScrollBarStyle(0);
    }
    //数量少了就不行
    private void scolooBar(){
        try {
            Field f = AbsListView.class.getDeclaredField("mFastScroller");
            f.setAccessible(true);
            Object o = f.get(lv);
            f = f.getType().getDeclaredField("mThumbDrawable");
            f.setAccessible(true);
            Drawable drawable = (Drawable) f.get(o);
            drawable = getResources().getDrawable(R.drawable.ic_launcher);
            f.set(o, drawable);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public class ListAdapter extends BaseAdapter {

        public int getCount() {
            return 200;
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(ScoloListView.this);
            tv.setTextSize(30);
            tv.setText("aaaaa" + position);
            return tv;
        }
    }
}
