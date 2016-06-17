package com.app.alldemo.courview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.TextView;

import com.app.alldemo.R;

/**
 * Created by wangbs on 16/6/14.
 */
public class XmlTextView extends TextView{
    public XmlTextView(Context context) {
        super(context);
    }
    public XmlTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray xmlnsAttrs = getContext().obtainStyledAttributes(attrs,
                R.styleable.XmlnsTextAttrs, 0, 0);
        float size=xmlnsAttrs.getFloat(R.styleable.XmlnsTextAttrs_xml_size,10);
        Log.e("字体:","size:"+size);
        setTextSize(size);
    }
    public XmlTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray xmlnsAttrs = getContext().obtainStyledAttributes(attrs,
                R.styleable.XmlnsTextAttrs, 0, 0);
        float size=xmlnsAttrs.getFloat(R.styleable.XmlnsTextAttrs_xml_size,10);
        Log.e("字体:","size:"+size);
        setTextSize(size);
    }
}
