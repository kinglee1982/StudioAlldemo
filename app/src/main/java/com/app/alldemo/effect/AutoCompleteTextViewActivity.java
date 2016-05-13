package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.app.alldemo.R;

public class AutoCompleteTextViewActivity extends Activity {
    private AutoCompleteTextView actv;
    private static final String[] autoStrs = new String[]{"a","abc","abcd","abcde","ba","我们","我们会","他们"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autosearch);
        actv = (AutoCompleteTextView)findViewById(R.id.actv);
        //new ArrayAdapter对象并将autoStr字符串数组传入actv中
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,autoStrs);
        actv.setAdapter(adapter);
        findUI();
    }
    private void findUI(){
    }
}
