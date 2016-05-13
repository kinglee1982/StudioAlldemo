package com.app.alldemo.effect;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.View.OnClickListener;
import com.app.alldemo.R;

public class SearchBoxActivity extends Activity {
    private RelativeLayout rl_title;
    private LinearLayout ll_search_one,ll_search_two;
    private EditText et_search;
    private TextView btn_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchbox);
        findUI();
    }
    private void findUI(){
        rl_title=(RelativeLayout)findViewById(R.id.rl_title);
        ll_search_one=(LinearLayout)findViewById(R.id.ll_search_one);
        ll_search_two=(LinearLayout)findViewById(R.id.ll_search_two);
        et_search = (EditText) findViewById(R.id.et_search);
        btn_cancel = (TextView) findViewById(R.id.btn_cancel);
        ll_search_one.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                searchOneClick();
            }
        });
        btn_cancel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                searchCancelClick();
            }
        });
        et_search.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                // 输入完就去查询
                String words = et_search.getText().toString();
            }
        });
    }
    private void searchOneClick() {
        rl_title.setVisibility(View.GONE);
        ll_search_one.setVisibility(View.GONE);
        ll_search_two.setVisibility(View.VISIBLE);
    }
    private void searchCancelClick() {
        ll_search_one.setVisibility(View.VISIBLE);
        rl_title.setVisibility(View.VISIBLE);
        ll_search_two.setVisibility(View.GONE);
    }
}
