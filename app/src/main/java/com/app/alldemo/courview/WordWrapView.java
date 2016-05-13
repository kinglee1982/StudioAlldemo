package com.app.alldemo.courview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import com.app.alldemo.utils.MyLog;

public class WordWrapView extends ViewGroup {
	private static final String TAG="WordWrapView";
	private static final int PADDING_HOR = 30;// 水平方向padding  
    private static final int PADDING_VERTICAL = 15;// 垂直方向padding  
    private static final int SIDE_MARGIN = 10;// 左右间距  
    private static final int lineMargins = 10;//垂直间距
  
    public WordWrapView(Context context) {  
        super(context);  
    }  
  
    public WordWrapView(Context context, AttributeSet attrs) {  
        super(context, attrs);  
    }  
  
    public WordWrapView(Context context, AttributeSet attrs, int defStyle) {  
        super(context, attrs, defStyle);  
    }  
  
    @Override  
    protected void onLayout(boolean changed, int l, int t, int r, int b) {  
        int childCount = getChildCount();  
        int autualWidth = r - l;
        int startX=0;
        int endX=0;
        int x = 0;
        int y = 0;
        int rows = 1;  
        for (int i = 0; i < childCount; i++) {  
            View view = getChildAt(i);  
            int width = view.getMeasuredWidth();  
            int height = view.getMeasuredHeight();
            startX=x;
        	x += width + SIDE_MARGIN;
        	if (x > autualWidth) {
        		//每行最后一个，减去间距
        		if((x-SIDE_MARGIN) > autualWidth){
        			//换行
        			x=width + SIDE_MARGIN;
        			startX=0;
        			endX=width;
            		rows++;
        		}else {
					x=x-SIDE_MARGIN;
					endX=x;
				}
        	}else {
				endX=x-SIDE_MARGIN;
			}  
            y = rows * (height + lineMargins);
            MyLog.v(TAG, "i:"+i+"start-x:"+startX+"start-y:"+(y - height)+"end-x:"+endX+"end-y:"+y);
            view.layout(startX, y - height, endX, y);
        }  
    }  
  
    @Override  
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
    	int startX=0;
        int endX=0;
        int x = 0;// 横坐标  
        int y = 0;// 纵坐标  
        int rows = 1;// 总行数  
        int specWidth = MeasureSpec.getSize(widthMeasureSpec);  
        int autualWidth = specWidth - SIDE_MARGIN * 2;// 实际宽度  
        int childCount = getChildCount();  
        for (int index = 0; index < childCount; index++) {  
            View child = getChildAt(index);  
            child.setPadding(PADDING_HOR, PADDING_VERTICAL, PADDING_HOR,  
                    PADDING_VERTICAL);  
            child.measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED);  
            int width = child.getMeasuredWidth();  
            int height = child.getMeasuredHeight();  
            startX=x;
        	x += width + SIDE_MARGIN;
        	if (x > autualWidth) {
        		//每行最后一个，减去间距
        		if((x-SIDE_MARGIN) > autualWidth){
        			//换行
        			x=width + SIDE_MARGIN;
        			startX=0;
        			endX=width;
            		rows++;
        		}else {
					x=x-SIDE_MARGIN;
					endX=x;
				}
        	}else {
				endX=x-SIDE_MARGIN;
			}
            y = rows * (height + lineMargins);  
        }  
        setMeasuredDimension(autualWidth, y);  
    }
}
