package com.app.alldemo.effect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.app.alldemo.R;
import com.app.alldemo.utils.MyLog;
import com.app.alldemo.utils.UtilsTool;
public class EditextIncoActivity extends Activity {
    private static final String TAG = "EditextIncoActivity";
    EditText editext;
    TextView textview;
    private Button button, button2, button3, button4, button5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_inco);
        findUI();
    }
    private void findUI(){
        editext = (EditText) findViewById(R.id.editext);
        textview = (TextView) findViewById(R.id.textview);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button5 = (Button) findViewById(R.id.button5);
        button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                textView();
            }
        });
        button2.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                addEditext();
            }
        });
        button3.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                highlight();
            }
        });
        button4.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                htmladd();
            }
        });
        button5.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                underline();
            }
        });
    }
    /**
     * 插入表情
     */
    private void addEditext() {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_launcher);
        Bitmap zBitmap = UtilsTool.getInstance().zoomImage(bitmap, 60, 60);
        ImageSpan imageSpan = new ImageSpan(this, zBitmap);
        SpannableString spannableString = new SpannableString("ic_launcher");
        spannableString.setSpan(imageSpan, 0, spannableString.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 光标处插入
        int index = editext.getSelectionStart();
        Editable editable = editext.getText();
        editable.insert(index, spannableString);
    }

    /**
     * 高亮显示
     */
    public void highlight() {
        String string = "我们嗯呢";
        SpannableStringBuilder spannable = new SpannableStringBuilder(string);// 用于可变字符串
        ForegroundColorSpan span = new ForegroundColorSpan(getResources().getColor(R.color.blue));
        spannable.setSpan(span, 0, string.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        // 光标处插入
        int index = editext.getSelectionStart();
        Editable editable = editext.getText();
        editable.insert(index, spannable);
    }

    /**
     * html标签
     */
    public void htmladd() {
        String string = "<font color=\"#FF0000\">颜色2";
        Spanned spanned= Html.fromHtml(string);
        // 光标处插入
        int index = editext.getSelectionStart();
        Editable editable = editext.getText();
        editable.insert(index, spanned);
    }

    /**
     * 下划线,测试错误，没需求要做没改bug
     */
    public void underline() {
        String string = "好吗";
        SpannableStringBuilder spannable = new SpannableStringBuilder(string);
        CharacterStyle span = new UnderlineSpan();
        String value = editext.getText().toString();
        int start = value.indexOf(string);
        int end = start + string.length() - 1;
        MyLog.v(TAG, "start:" + start + "end:" + end);
        spannable.setSpan(span, 0, string.length(),
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        editext.append(spannable, start, end);
    }

    private void textView() {
        String content = editext.getText().toString();
        MyLog.v(TAG, "content:"+content);
        textview.setText(content);
    }
}
