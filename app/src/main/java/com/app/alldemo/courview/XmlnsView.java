package com.app.alldemo.courview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.View;

import com.app.alldemo.R;
import com.app.alldemo.utils.MyLog;

/**
 * Created by Administrator on 2015/11/16.
 */
public class XmlnsView extends View{
    private static final String TAG="XmlnsView";
    public XmlnsView(Context context) {
        super(context);
    }
    public XmlnsView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray xmlnsAttrs = getContext().obtainStyledAttributes(attrs,
                R.styleable.XmlnsAttrs, 0, 0);
        String string=xmlnsAttrs.getString(R.styleable.XmlnsAttrs_xmlns_name);
        boolean flag=xmlnsAttrs.getBoolean(R.styleable.XmlnsAttrs_xmlns_boolean,false);
        MyLog.v(TAG, "string:" + string + "flag:" + flag);
    }
    public XmlnsView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
}
