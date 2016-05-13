package com.app.alldemo.effect.uiset;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.app.alldemo.R;
import com.app.alldemo.utils.LangUtils;
import com.app.alldemo.utils.MyLog;

import java.util.regex.Pattern;

public class EditSetActivity extends Activity {
    private EditText editext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editset);
        findUI();
        test2();
    }
    private void findUI(){
        editext=(EditText)findViewById(R.id.editext);
        editext.setText("");
    }
    private void test2(){
        //模拟编辑框不可编辑效果
//        editext.setKeyListener(null);
        //键盘监听
        editext.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                MyLog.e("", "回车" + event.getAction() + "keyCode" + keyCode);
                if (keyCode == 66 && event.getAction() == KeyEvent.ACTION_DOWN) { //回车键被按下
                }
                if (keyCode == 66 && event.getAction() == KeyEvent.KEYCODE_BACK) { //回车键被按下
                    MyLog.e("", "返回");
                }
                return false;
            }
        });
    }
    private void test(){
        //判断是否为中文
        LangUtils.isChinese("是否为");
    }
    /**
     * 有时候会发现输入框被键盘挡住一部分，不能完全显示出来
     */
    public void hideKeyboard(){
        //java代码:
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE |
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        //java代码:简单一点
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        //xml属性设置:android:windowSoftInputMode="stateHidden" android:screenOrientation="portrait"
        //软输入法模式选项：
        /*
        软输入区域是否可见。
        public static final int SOFT_INPUT_MASK_STATE = 0x0f;
        未指定状态。
        public static final int SOFT_INPUT_STATE_UNSPECIFIED = 0;
        不要修改软输入法区域的状态。
        public static final int SOFT_INPUT_STATE_UNCHANGED = 1;
        隐藏输入法区域（当用户进入窗口时）。
        public static final int SOFT_INPUT_STATE_HIDDEN = 2;
        当窗口获得焦点时，隐藏输入法区域。
        public static final int SOFT_INPUT_STATE_ALWAYS_HIDDEN = 3;
        显示输入法区域（当用户进入窗口时）。
        public static final int SOFT_INPUT_STATE_VISIBLE = 4;
        当窗口获得焦点时，显示输入法区域。
        public static final int SOFT_INPUT_STATE_ALWAYS_VISIBLE = 5;
        窗口应当主动调整，以适应软输入窗口。
        public static final int SOFT_INPUT_MASK_ADJUST = 0xf0;
        未指定状态，系统将根据窗口内容尝试选择一个输入法样式。
        public static final int SOFT_INPUT_ADJUST_UNSPECIFIED = 0x00;
        当输入法显示时，允许窗口重新计算尺寸，使内容不被输入法所覆盖。
        不可与SOFT_INPUT_ADJUSP_PAN混合使用；如果两个都没有设置，系统将根据窗口内容自动设置一个选项。
        public static final int SOFT_INPUT_ADJUST_RESIZE = 0x10;
        输入法显示时平移窗口。它不需要处理尺寸变化，框架能够移动窗口以确保输入焦点可见。
        不可与SOFT_INPUT_ADJUST_RESIZE混合使用；如果两个都没有设置，系统将根据窗口内容自动设置一个选项。
        public static final int SOFT_INPUT_ADJUST_PAN = 0x20;
        当用户转至此窗口时，由系统自动设置，所以你不要设置它。
        当窗口显示之后该标志自动清除。
        public static final int SOFT_INPUT_IS_FORWARD_NAVIGATION = 0x100;
        */
    }
    private void editRegext(){
        //正则匹配方式
        editext.addTextChangedListener(new TextWatcher() {
            public CharSequence temp;
            public int editStart;
            public int editEnd;
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                editStart = editext.getSelectionStart();
                editEnd = editext.getSelectionEnd();
                String regEx="^[1-9]{1,1}\\d{0,2}";
                if (!Pattern.matches(regEx, temp)) {
                    if (s.length() != 0) {
                        if (editStart > 0) {
                            ((Editable) s).delete(editStart - 1, editEnd);
                        }
                    }
                    int tempSelection = editEnd;
                    editext.setSelection(tempSelection);
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                temp=s;
            }

            @Override
            public void afterTextChanged(Editable s) {
                // TODO Auto-generated method stub

            }
        });
    }
}
