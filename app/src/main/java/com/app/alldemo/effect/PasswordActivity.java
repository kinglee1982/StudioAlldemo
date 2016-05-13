package com.app.alldemo.effect;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.courview.ImeDelBugFixedEditText;
import com.app.alldemo.utils.MyLog;
/**
 * 没有输入焦点的控件可以弹出键盘就要设置onCreateInputConnection
 * setFocusable(true)
 * setFocusableInTouchMode(true)
 * @return
 */
public class PasswordActivity extends Activity {
    private TextView set_cancel;
    private EditText editext;
    private TextView text1,text2,text3,text4;
    private int i=0;
    private String text1Value,text2Value,text3Value,text4Value;
    private String againText1Value,againText2Value,againText3Value,againText4Value;
    private ImeDelBugFixedEditText inputView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);
        findUI();
        getData();
        initView();
        findEdit();
    }
    private void findUI(){
        set_cancel = (TextView)findViewById(R.id.set_cancel);
        editext =(EditText)findViewById(R.id.editext);
        editext.requestFocus();
        editext.setEnabled(true);
        text1 =(TextView)findViewById(R.id.text1);
        text2 =(TextView)findViewById(R.id.text2);
        text3 =(TextView)findViewById(R.id.text3);
        text4 =(TextView)findViewById(R.id.text4);
        set_cancel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
            }
        });
        editListener();
    }
    private void getData() {
    }
    private void editListener(){
        editext.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    i = i + 1;
                    MyLog.e("", "第一个:" + i);
                    text(s.toString());
                    editext.setText("");
                }
            }
        });
        editext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == 67 && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (i > 0) {
                        MyLog.e("", "清除键:" + i);
                        clearText();
                    }
                }
                return false;
            }
        });
    }
    private void clearText(){
        if(i==1){
            text1Value="";
            text1.setText("");
            i=i-1;
        }else if(i==2){
            text2Value="";
            text2.setText("");
            i=i-1;
        }else if(i==3){
            text3Value="";
            text3.setText("");
            i=i-1;
        }else if(i==5){
            againText1Value="";
            text1.setText("");
            i=i-1;
        }else if(i==6){
            againText2Value="";
            text2.setText("");
            i=i-1;
        }else if(i==7){
            againText3Value="";
            text3.setText("");
            i=i-1;
        }
    }
    private void text(String s){
        if(i==1){
            text1Value=s;
            text1.setText(s);
        }else if(i==2){
            text2Value=s;
            text2.setText(s);
        }else if(i==3){
            text3Value=s;
            text3.setText(s);
        }else if(i==4){
            text4Value=s;
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
        }else if(i==5){
            againText1Value=s;
            text1.setText(s);
        }else if(i==6){
            againText2Value=s;
            text2.setText(s);
        }else if(i==7){
            againText3Value=s;
            text3.setText(s);
        }else if(i==8){
            againText4Value=s;
            set();
        }
    }
    private void set(){
        if(text1Value.equals(againText1Value) && text2Value.equals(againText2Value)
                && text3Value.equals(againText3Value) && text4Value.equals(againText4Value)){
            setPassword(1);
        }else {
            i=4;
            text1.setText("");
            text2.setText("");
            text3.setText("");
            text4.setText("");
        }
    }
    private void setPassword(int value){
        Intent data = new Intent();
        data.putExtra("value", value);
        setResult(RESULT_OK, data);
        finish();
    }
    private void initView() {

    }
    private void findEdit(){
        inputView = (ImeDelBugFixedEditText) findViewById(R.id.inputView);

        inputView.setMaxEms(6);
        inputView.addTextChangedListener(textWatcher);
        inputView.setDelKeyEventListener(onDelKeyEventListener);
        inputView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                forceInputViewGetFocus();
            }
        });
    }
    private TextWatcher textWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            Log.e("", "s:" + s.toString() + "count:" + count);
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            Log.e("","s:"+s.toString()+"count:"+count);
        }

        @Override
        public void afterTextChanged(Editable s) {
            Log.e("","s:"+s.toString());
        }
    };
    private ImeDelBugFixedEditText.OnDelKeyEventListener onDelKeyEventListener = new ImeDelBugFixedEditText.OnDelKeyEventListener() {

        @Override
        public void onDeleteClick() {
            Log.e("","删除");
        }
    };
    private void forceInputViewGetFocus() {
        inputView.setFocusable(true);
        inputView.setFocusableInTouchMode(true);
        inputView.requestFocus();
        InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(inputView, InputMethodManager.SHOW_IMPLICIT);
    }
}
