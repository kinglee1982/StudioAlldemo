package com.app.alldemo.effect;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.BulletSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.ScaleXSpan;
import android.text.style.StrikethroughSpan;
import android.text.style.StyleSpan;
import android.text.style.SubscriptSpan;
import android.text.style.SuperscriptSpan;
import android.text.style.TypefaceSpan;
import android.text.style.URLSpan;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.app.alldemo.R;
import com.app.alldemo.utils.ViewUtils;

public class TextViewIncoActivity extends Activity {
    private static final String TAG = "TextViewIncoActivity";
    private TextView spannable_text;
    private TextView text_click;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_inco);
        spannable_text = (TextView) findViewById(R.id.spannable_text);
        text_click = (TextView) findViewById(R.id.text_click);
        findUI();
        textClick();
        textStyle();
    }

    private void findUI() {
        TextView textView = (TextView) this.findViewById(R.id.textview);
        TextView textview2 = (TextView) this.findViewById(R.id.textview2);
        textView.setTextColor(Color.BLACK);
        textView.setBackgroundColor(Color.WHITE);
        textView.setTextSize(20);// 设置字体的大小
        String text2 = "<font color=\"#FF0000\">颜色2</font>";
        textview2.setText(Html.fromHtml(text2));
        String html = "图像1<img src='face1'/>图像2<img src='face2'/>图像3<img src='face3'/><p>"
                + "图像4<a href='http://www.baidu.com'><img src='face4'></a>图像5<img src='face5'/>"
                + "<b>text3:</b>"
                + "<p>超链接:<a href=\"http://www.google.com\">link</a></P>"
                + "<p><font color=\"#FF0000\">颜色2</p>"
                + "<font color=\"#FF0000\">颜色2</font><br/>"
                + "<h1>标题1</h1>"
                + "<html><head><title>TextView使用HTML</title></head><body>"
                + "<p><strong>强调</strong></p>"
                + "<p><em>斜体</em></p>"
                + "<p>大于>小于<</p>"
                + "<font color=\"#FF0000\">fawefaewfawefawefawefawefawefawaewfafaw</font><br/>"
                + "网络图片</p><img src=\"http://avatar.csdn.net/0/3/8/2_zhang957411207.jpg\"/>"
                + "</body></html>";
        ViewUtils.getInstance().viewHTmlText(this, textView, html);
    }
    private void textClick(){
//        CharSequence str=getText(R.string.textclick);
        text_click.setText(Html.fromHtml("sddddddddddfgffdssss"));
        SpannableString spannableString1 = new SpannableString(text_click.getText());
        spannableString1.setSpan(new TextViewURLSpan(text_click.getText().toString().substring(text_click.getText().length() - 3,text_click.getText().length())), text_click.getText().length() - 3, text_click.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableString1.setSpan(new ForegroundColorSpan(Color.RED), text_click.getText().length() - 3, text_click.getText().length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        text_click.setText(spannableString1);
        text_click.setMovementMethod(LinkMovementMethod.getInstance());
    }
    private void textClick2(){
//        text_click.setText(Html.fromHtml(actionText));
        StringBuilder actionText = new StringBuilder();
        actionText.append("<a style=\"text-decoration:none;\" href='username'>"
                + "username:" + " </a>");
        String textString="我摸<font  color=\"#4A90E2\">#password </font>他家";
        actionText.append(textString);
        actionText.append("隐形人"
                + "<a style=\"color:blue;text-decoration:none;\" href='singstar'> "
                + " love" + "</a>");
        actionText.append(" : \"" + "孙燕姿" + "\"");
        actionText.append("<font color=\"#FF0000\" href='women'>颜色2</font>");
        text_click.setText(Html.fromHtml(actionText.toString()));
        text_click.setMovementMethod(LinkMovementMethod.getInstance());
        CharSequence text = text_click.getText();
        int ends = text.length();
        Spannable spannable = (Spannable) text_click.getText();
        URLSpan[] urlspan = spannable.getSpans(0, ends, URLSpan.class);
        SpannableStringBuilder stylesBuilder = new SpannableStringBuilder(text);
        stylesBuilder.clearSpans(); // should clear old spans
        for (URLSpan url : urlspan) {
            Log.e("","内容:"+url.getURL());
            TextViewURLSpan myURLSpan = new TextViewURLSpan(url.getURL());
            stylesBuilder.setSpan(myURLSpan, spannable.getSpanStart(url),
                    spannable.getSpanEnd(url), spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        text_click.setText(stylesBuilder);
    }
    private class TextViewURLSpan extends ClickableSpan {
        private String clickString;

        public TextViewURLSpan(String clickString) {
            this.clickString = clickString;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
//            ds.setColor(TextViewIncoActivity.this.getResources().getColor(R.color.text_color));
            ds.setUnderlineText(false); //去掉下划线
        }

        @Override
        public void onClick(View widget) {
            Log.e("","点击:"+clickString);
            if (clickString.equals("颜色2")) {
                Toast.makeText(getApplication(), clickString, Toast.LENGTH_LONG)
                        .show();
            } else if (clickString.equals("singstar")) {
                Toast.makeText(getApplication(), clickString, Toast.LENGTH_LONG)
                        .show();
            }
             else if (clickString.equals("username")) {
                Toast.makeText(getApplication(), clickString, Toast.LENGTH_LONG)
                        .show();
            }
             else if (clickString.equals("women")) {
                Toast.makeText(getApplication(), clickString, Toast.LENGTH_LONG)
                        .show();
            }
        }
    }
    /**
     * 对textview每个样式的设置
     */
    private void textStyle() {
        SpannableString msp = new SpannableString("字体测试字体大小一半两倍前景色背景色正常粗体斜体粗斜体下划线删除线x1x2电话邮件网站短信彩信地图X轴综合");
        //设置字体(default,default-bold,monospace,serif,sans-serif)
        msp.setSpan(new TypefaceSpan("monospace"), 0, 2, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        msp.setSpan(new TypefaceSpan("serif"), 2, 4, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小（绝对值,单位：像素）;true:表示表示前面的字体大小单位为dip，否则为像素;6,8表示第6个开始到第8个
        msp.setSpan(new AbsoluteSizeSpan(20, true), 6, 8, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体大小（相对值,单位：像素） 参数表示为默认字体大小的多少倍;0.5f一半
        msp.setSpan(new RelativeSizeSpan(0.5f), 8, 10, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体前景色
        msp.setSpan(new ForegroundColorSpan(Color.MAGENTA), 12, 15, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体样式正常，粗体，斜体，粗斜体
        msp.setSpan(new StyleSpan(android.graphics.Typeface.NORMAL), 18, 20, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //正常
        msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 20, 22, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗体
        msp.setSpan(new StyleSpan(android.graphics.Typeface.ITALIC), 22, 24, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //斜体
        msp.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC), 24, 27, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);  //粗斜体
        //设置下划线
        msp.setSpan(new UnderlineSpan(), 27, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置删除线
        msp.setSpan(new StrikethroughSpan(), 30, 33, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置上下标
        msp.setSpan(new SubscriptSpan(), 34, 35, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //下标
        msp.setSpan(new SuperscriptSpan(), 36, 37, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);   //上标
        //超级链接（需要添加setMovementMethod方法附加响应）
        msp.setSpan(new URLSpan("tel:4155551212"), 37, 39, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //电话
        msp.setSpan(new URLSpan("mailto:webmaster@google.com"), 39, 41, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //邮件
        msp.setSpan(new URLSpan("http://www.baidu.com"), 41, 43, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //网络
        msp.setSpan(new URLSpan("sms:4155551212"), 43, 45, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //短信   使用sms:或者smsto:
        msp.setSpan(new URLSpan("mms:4155551212"), 45, 47, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //彩信   使用mms:或者mmsto:
        msp.setSpan(new URLSpan("geo:38.899533,-77.036476"), 47, 49, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);     //地图
        //设置字体大小（相对值,单位：像素） 参数表示为默认字体宽度的多少倍
        msp.setSpan(new ScaleXSpan(2.0f), 49, 51, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //2.0f表示默认字体宽度的两倍，即X轴方向放大为默认字体的两倍，而高度不变
        //设置项目符号
        msp.setSpan(new BulletSpan(android.text.style.BulletSpan.STANDARD_GAP_WIDTH, Color.GREEN), 0, 53, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE); //第一个参数表示项目符号占用的宽度，第二个参数为项目符号的颜色
        spannable_text.setText(msp);
        spannable_text.setMovementMethod(LinkMovementMethod.getInstance());
    }
}