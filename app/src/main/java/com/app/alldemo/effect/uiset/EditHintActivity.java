package com.app.alldemo.effect.uiset;

import android.app.Activity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.style.AbsoluteSizeSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.app.alldemo.R;

/**
 * 分析过程:1.EditText继承TextView，分析setHint(CharSequence hint)源码
 * 2.mHint = TextUtils.stringOrSpannedString(hint);此方法就是对hint文本的转换，hint转换为另一个hint，hint是CharSequence类型的,说明我们可以增加一些自定义属性
 * 3.分析stringOrSpannedString()；我们只要传入的hint是SpannedString或者Spanned类型就返回本身，可以保持原本的属性；
 * 4.除了可以改变Hint的大小,其它属性都可更改
 */
public class EditHintActivity extends Activity {
    private EditText editext_test;
    private Button button1,button2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edithint);
        editext_test=(EditText)findViewById(R.id.editext_test);
        button1=(Button)findViewById(R.id.button1);
        button2=(Button)findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hint();
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                text();
            }
        });
    }
    private void hint(){
        // 新建一个可以添加属性的文本对象
        SpannableString ss = new SpannableString("喝酒就要喝一斤!");
        // 新建一个属性对象,设置文字的大小
        AbsoluteSizeSpan ass = new AbsoluteSizeSpan(8, true);
        // 附加属性到文本
        ss.setSpan(ass, 0, ss.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 一定要进行转换,类型不对会被转换为String对象,否则属性会消失
        SpannedString spannedString=new SpannedString(ss);
        // 设置hint
        editext_test.setHint(spannedString);
    }
    private void text(){
        editext_test.setTextSize(16);
        editext_test.setText("喝酒就要喝一斤!");
    }
}
