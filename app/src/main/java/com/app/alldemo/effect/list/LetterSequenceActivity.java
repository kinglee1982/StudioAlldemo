package com.app.alldemo.effect.list;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.list.ListViewLetterIndicator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LetterSequenceActivity extends Activity {
    private ListView lvContent;
    private BaseAdapter mAdapter;
    private ListViewLetterIndicator liIndicator;
    private TextView tvAlert;

    private List<String> mData = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_letter_sequence);

        initData();

        lvContent = (ListView) findViewById(R.id.lvContent);
        liIndicator = (ListViewLetterIndicator) findViewById(R.id.liIndicator);
        tvAlert = (TextView)findViewById(R.id.tvAlert);

        mAdapter = new LetterAdapter();
        lvContent.setAdapter(mAdapter);

        liIndicator.setData(lvContent, mData, tvAlert);
    }

    private void initData() {

        String data = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < data.length(); i++) {
            mData.add(String.valueOf(data.charAt(i)));
            mData.add(String.valueOf(data.charAt(i)));
        }

        for (int i = 0; i < 8; i++) {
            mData.add("深深深Diary of a Wimpy Kid 6: Cabin Fever");
            mData.add("深深深Steve Jobs");
            mData.add("到底Inheritance (The Inheritance Cycle)");
            mData.add("上色11/22/63: A Novel");
            mData.add("地方The Hunger Games");
            mData.add("请求The LEGO Ideas Book");
            mData.add("额额Explosive Eighteen: A Stephanie Plum Novel");
            mData.add("wwwCatching Fire (The Second Book of the Hunger Games)");
            mData.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
            mData.add("Death Comes to Pemberley");
            mData.add("Diary of a Wimpy Kid 6: Cabin Fever");
            mData.add("Steve Jobs");
            mData.add("Inheritance (The Inheritance Cycle)");
            mData.add("11/22/63: A Novel");
            mData.add("The Hunger Games");
            mData.add("The LEGO Ideas Book");
            mData.add("Explosive Eighteen: A Stephanie Plum Novel");
            mData.add("Catching Fire (The Second Book of the Hunger Games)");
            mData.add("Elder Scrolls V: Skyrim: Prima Official Game Guide");
            mData.add("Death Comes to Pemberley");
        }
        Collections.sort(mData);
    }

    private class LetterAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mData.size();
        }

        @Override
        public String getItem(int position) {
            return mData.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tvItem = new TextView(getApplicationContext());
            tvItem.setText(getItem(position));
            tvItem.setTextSize(16);
            tvItem.setPadding(8, 8, 8, 8);
            return tvItem;
        }

    }
}
