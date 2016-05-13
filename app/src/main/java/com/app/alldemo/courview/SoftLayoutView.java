package com.app.alldemo.courview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.app.alldemo.utils.MyLog;

/**
 * Created by Administrator on 2016/2/15.
 */
public class SoftLayoutView extends LinearLayout{
    public SoftLayoutView(Context context) {
        super(context);
    }
    public SoftLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public SoftLayoutView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        MyLog.e("","w:"+w+"h:"+h+"oldw:"+oldw+"oldh:"+oldh);
        super.onSizeChanged(w, h, oldw, oldh);
    }
}
