package com.app.alldemo.courview;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
/**
 * 执行顺序:onMeasure-onLayout-onDraw
 * @author Administrator
 *
 */
public class ViewTest extends View{
	private static final String TAG="ImageTestView";
	public ViewTest(Context context) {
		super(context);
		init();
	}
	public ViewTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
	private void init(){
	}
	@Override
    protected void onDraw(Canvas canvas) {
		Log.v(TAG, "onDraw");
	}
	/**
	 * 负责设置View的大小和位置
	 */
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		Log.v(TAG, "onLayout");
		super.onLayout(changed, left, top, right, bottom);
	}
	/** 负责测量这个ViewGroup和子View的大小
	 * ①获得ViewGroup和子View的宽和高②设置子ViewGroup的宽和高
     * 比onDraw先执行 
     *  
     * 一个MeasureSpec封装了父布局传递给子布局的布局要求，每个MeasureSpec代表了一组宽度和高度的要求。 
     * 一个MeasureSpec由大小和模式组成 
     * 它有三种模式：UNSPECIFIED(未指定),父元素不对子元素施加任何束缚，子元素可以得到任意想要的大小; 
     *              EXACTLY(完全)，父元素决定子元素的确切大小，子元素将被限定在给定的边界里而忽略它本身大小； 
     *              AT_MOST(至多)，子元素至多达到指定大小的值。 
     *  
     * 　　它常用的三个函数： 　　 
     * 1.static int getMode(int measureSpec):根据提供的测量值(格式)提取模式(上述三个模式之一) 
     * 2.static int getSize(int measureSpec):根据提供的测量值(格式)提取大小值(这个大小也就是我们通常所说的大小),确定值 
     * 3.static int makeMeasureSpec(int size,int mode):根据提供的大小值和模式创建一个测量值(格式),不确定，根据自己尺寸来匹配
     */
	@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		Log.v(TAG, "onMeasure");
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));//设置ViewGroup的宽高
    }
	private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);//获取ViewGroup宽度
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
        	Log.v("test", "EXACTLY模式-300dp和match_parent");
            result = specSize;
        } else {
            result =  getPaddingLeft() + getPaddingRight();
            if (specMode == MeasureSpec.AT_MOST) {
            	Log.v("test", "AT_MOST模式-wrap_content");
                result = Math.min(result, specSize);
            }else {
            	Log.v("test", "UNSPECIFIED模式");
			}
        }
        Log.v("test", "result:"+result);
        return result;
    }
	private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result =  getPaddingTop() + getPaddingBottom();
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }
}
