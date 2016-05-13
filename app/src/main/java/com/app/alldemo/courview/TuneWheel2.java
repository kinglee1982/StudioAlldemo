package com.app.alldemo.courview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.GradientDrawable;
import android.text.Layout;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Scroller;

/**
 * 卷尺控件类。<br>
 * 细节问题包括滑动过程中widget边缘的刻度显示问题等<br>
 */
@SuppressLint({ "ClickableViewAccessibility", "NewApi" })
public class TuneWheel2 extends View {
	private static final String TAG="TuneWheel";
	private static final int ITEM_MAX_HEIGHT = 50;//大刻度的长度
	private static final int ITEM_MIN_HEIGHT = 20;//小刻度的长度
	private static final int TEXT_SIZE = 18;//字体大小
	private float mDensity;
	private int mValue = 100;//当前的刻度值
	private final int mModType = 10;//大刻度里面几个小刻度
	private int mLineDivider = 10;//间距
	private int mMaxValue = 1000;
	private int mLastX, mMove;//移动的距离
	private int mWidth, mHeight;
	private final int num=4;//绘制几个宽度的尺寸，
	private int mMinVelocity;
	private Scroller mScroller;
	private VelocityTracker mVelocityTracker;//跟踪触摸屏事件（flinging事件和其他gestures手势事件）的速率。
	private OnValueChangeListener mListener;
	private String border_color = "#FFFFFF";// 边框颜色
	private String scal_color = "#000000";// 刻度颜色
	private int colors[] = { 0xFF999999, 0xFFFFFFFF, 0xFF999999 };//绘制的背景色，分别为开始颜色，中间夜色，结束颜色
	private Paint scalPaint,instructionsPain,ovalPaint;
	private TextPaint textPaint;
	private float textWidth;//字体需要的额宽度
	public TuneWheel2(Context context) {
		super(context);
		init();
	}
	public TuneWheel2(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}
	public TuneWheel2(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}
	public void setmMaxValue(int mMaxValue){
		this.mMaxValue=mMaxValue;
	}
	public interface OnValueChangeListener {
		void onValueChange(float value);
	}
	private void init(){
		mScroller = new Scroller(getContext());
		mDensity = getContext().getResources().getDisplayMetrics().density;
		mMinVelocity = ViewConfiguration.get(getContext())
				.getScaledMinimumFlingVelocity();
		setBackgroundDrawable(createBackground());
		initPain();
	}
	private GradientDrawable createBackground() {
		float strokeWidth = 0.5f * mDensity; // 边框宽度
		float roundRadius = 6 * mDensity; // 圆角半径
		int strokeColor = Color.parseColor(border_color);
		setPadding((int) strokeWidth, (int) strokeWidth, (int) strokeWidth, 0);
		GradientDrawable bgDrawable = new GradientDrawable(
				GradientDrawable.Orientation.LEFT_RIGHT, colors);// 创建drawable
		bgDrawable.setCornerRadius(roundRadius);
		bgDrawable.setStroke((int) strokeWidth, strokeColor);
		return bgDrawable;
	}

	/**
	 * 设置用于接收结果的监听器
	 * 
	 * @param listener
	 */
	public void setValueChangeListener(OnValueChangeListener listener) {
		mListener = listener;
	}

	/**
	 * 获取当前刻度值
	 * 
	 * @return
	 */
	public int getValue() {
		return mValue;
	}
	public void setValue(int mValue){
		this.mValue=mValue;
	}

	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		mWidth = getWidth();
		mHeight = getHeight();
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawScaleLine(canvas);
		drawMiddleLine(canvas);
	}
	private void initPain(){
		scalePain();
		textPain();
		instructionsPain();
		ovalPain();
	}
	/**
	 * 刻度的paint
	 */
	private void scalePain(){
		scalPaint = new Paint();
		scalPaint.setStrokeWidth(2);
		scalPaint.setColor(Color.parseColor(scal_color));
	}
	private void textPain(){
		textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
		textPaint.setTextSize(TEXT_SIZE * mDensity);
	}
	/**
	 * 指示线
	 */
	private void instructionsPain(){
		instructionsPain = new Paint();
		instructionsPain.setStrokeWidth(8);
		instructionsPain.setColor(Color.RED);
	}
	/**
	 * 指示线上下两个的pain
	 */
	private void ovalPain(){
		ovalPaint = new Paint();
		ovalPaint.setColor(Color.RED);
		ovalPaint.setStrokeWidth(24);
	}
	/**
	 * 从中间往两边开始画刻度线，绘制好几个刻度尺寸，滑动不停的绘制
	 * @param canvas
	 */
	private void drawScaleLine(Canvas canvas) {
		canvas.save();
		int drawCount = 0;
		textWidth= Layout.getDesiredWidth("0", textPaint);
		float xPosition = 0;//x轴起始位置
		for (int i = 0; drawCount <= num * mWidth; i++) {
			int numSize = String.valueOf(mValue + i).length();
			xPosition = (mWidth / 2 - mMove) + i * mLineDivider * mDensity;
			//右半边
			if (xPosition + getPaddingRight() < mWidth) {
				if ((mValue + i) % mModType == 0) {
					//大刻度
					canvas.drawLine(xPosition, getPaddingTop(), xPosition,
							mDensity * ITEM_MAX_HEIGHT, scalPaint);
					if (mValue + i <= mMaxValue) {
						canvas.drawText(String.valueOf((mValue + i)/10),
								xPosition - (textWidth * numSize / 2),
								getHeight() - textWidth, textPaint);
					}
				} else {
					//小刻度
					canvas.drawLine(xPosition, getPaddingTop(), xPosition,
							mDensity * ITEM_MIN_HEIGHT, scalPaint);
				}
			}
			xPosition = (mWidth / 2 - mMove) - i * mLineDivider * mDensity;
			//左半天
			if (xPosition > getPaddingLeft()) {
				if ((mValue - i) % mModType == 0) {
					//大刻度
					canvas.drawLine(xPosition, getPaddingTop(), xPosition,
							mDensity * ITEM_MAX_HEIGHT, scalPaint);
					if (mValue - i >= 0) {
						//数字
						canvas.drawText(String.valueOf((mValue - i)/10),
								xPosition - (textWidth * numSize / 2),
								getHeight() - textWidth, textPaint);
					}
				} else {
					//小刻度
					canvas.drawLine(xPosition, getPaddingTop(), xPosition,
							mDensity * ITEM_MIN_HEIGHT, scalPaint);
				}
			}
			drawCount += 2 * mLineDivider * mDensity;
		}
		canvas.restore();
	}
	/**
	 * 画中间的红色指示线,指示线两端简单的用了两个矩形代替
	 * @param canvas
	 */
	private void drawMiddleLine(Canvas canvas) {
		canvas.save();
		canvas.drawLine(mWidth / 2, 0, mWidth / 2, mHeight, instructionsPain);
		//指示线上头的矩形
		canvas.drawLine(mWidth / 2, 0, mWidth / 2, 10, ovalPaint);
		//指示线下头的矩形
		canvas.drawLine(mWidth / 2, mHeight - 10, mWidth / 2,
				mHeight, ovalPaint);
		canvas.restore();
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int action = event.getAction();
		int xPosition = (int) event.getX();
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		//函数将Motion event加入到VelocityTracker类实例中
		mVelocityTracker.addMovement(event);
		switch (action) {
		case MotionEvent.ACTION_DOWN:
			mScroller.forceFinished(true);
			mLastX = xPosition;
			mMove = 0;
			break;
		case MotionEvent.ACTION_MOVE:
			mMove += (mLastX - xPosition);
			changeMoveAndValue();
			break;
		case MotionEvent.ACTION_UP:
		case MotionEvent.ACTION_CANCEL://当用户保持按下操作，并从你的控件转移到外层控件时
			countMoveEnd();
			countVelocityTracker(event);
			return false;
		default:
			break;
		}
		mLastX = xPosition;
		return true;
	}

	private void countVelocityTracker(MotionEvent event) {
		mVelocityTracker.computeCurrentVelocity(1000);
		//获得横向和竖向的速率到速率时，但是使用它们之前请先调用computeCurrentVelocity(int)来初始化速率的单位 。
		float xVelocity = mVelocityTracker.getXVelocity();
		if (Math.abs(xVelocity) > mMinVelocity) {
			mScroller.fling(0, 0, (int) xVelocity, 0, Integer.MIN_VALUE,
					Integer.MAX_VALUE, 0, 0);
		}
	}

	private void changeMoveAndValue() {
		int tValue = (int) (mMove / (mLineDivider * mDensity));
		if (Math.abs(tValue) > 0) {
			mValue += tValue;
			mMove -= tValue * mLineDivider * mDensity;
			if (mValue <= 0 || mValue > mMaxValue) {
				mValue = mValue <= 0 ? 0 : mMaxValue;
				mMove = 0;
				mScroller.forceFinished(true);
			}
			notifyValueChange();
		}
		postInvalidate();
	}

	private void countMoveEnd() {
		int roundMove = Math.round(mMove / (mLineDivider * mDensity));
		mValue = mValue + roundMove;
		mValue = mValue <= 0 ? 0 : mValue;
		mValue = mValue > mMaxValue ? mMaxValue : mValue;
		mLastX = 0;
		mMove = 0;
		notifyValueChange();
		postInvalidate();
	}

	private void notifyValueChange() {
		if (null != mListener) {
			mListener.onValueChange(mValue);
		}
	}

	@Override
	public void computeScroll() {
		super.computeScroll();
		if (mScroller.computeScrollOffset()) {
			if (mScroller.getCurrX() == mScroller.getFinalX()) { // over
				countMoveEnd();
			} else {
				int xPosition = mScroller.getCurrX();
				mMove += (mLastX - xPosition);
				changeMoveAndValue();
				mLastX = xPosition;
			}
		}
	}
}
