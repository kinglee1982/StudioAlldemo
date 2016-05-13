package com.app.alldemo.courview.seekbar;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.media.AudioManager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.app.alldemo.R;
import com.app.alldemo.utils.Constant;
import com.app.alldemo.utils.MyLog;

public class VocieProgressBar extends View {
	private static final String TAG = "VocieProgressBar";
	private Context context;
	private int bakColor, progressColor;
	private Paint paint, bakPaint, progressPaint;
	private int screentWidth, screentHight;
	// margerButtom整个图片离底部的距离，margerRight右边，progressWidth进度条的整体宽度
	private int margerButtom = 260, margerRight = 10;
	private float progressWidth, progressHeight;
	private float cureentProgressHeight;
	private float screent_density;
	private float bakDrawLeft, bakDrawTop;
	private int bitmapWidth, bitmapHeight;
	private float bakLeft, bakTop, bakRight, bakBottom;
	private float progressLeft, progressTop, progressRight, progressBottom;
	private Bitmap bitmapBg;
	public int maxVolume;
	public int currentVolume;
	public AudioManager mAudioManager;
	private float progress;

	public VocieProgressBar(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public VocieProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	public VocieProgressBar(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		this.context = context;
		init();
	}

	private void init() {
		// 设置矩形背景色
		bakColor = Color.rgb(30, 35, 82);
		progressColor = Color.rgb(0, 255, 255);
		// 屏幕宽高
		screentWidth = Constant.SCREEN_WIDTH;
		screentHight = Constant.SCREEN_HIGHT;
		screent_density = Constant.SCREEN_DENSITY;
		MyLog.v(TAG, "screentWidth:" + screentWidth + "screentHight:"
				+ screentHight);
		// 背景图片
		paint = new Paint();
		paint.setAntiAlias(true);// 设置画笔为无锯齿
		paint.setStyle(Style.STROKE);// 空心效果

		// 背景矩形
		bakPaint = new Paint();
		bakPaint.setColor(bakColor);
		bakPaint.setStyle(Style.FILL);
		bakPaint.setStrokeWidth((float) 1.0);// 线宽

		progressPaint = new Paint();
		progressPaint.setColor(progressColor);
		progressPaint.setStyle(Style.FILL);
		progressPaint.setStrokeWidth((float) 0.5);// 线宽

		bitmapBg = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.voice_bar);
		bitmapWidth = bitmapBg.getWidth();
		bitmapHeight = bitmapBg.getHeight();
		MyLog.v(TAG, "bitmapWidth:" + bitmapWidth + "bitmapHeight:"
				+ bitmapHeight);
		initVolum();
		initBakDrawMargin();
		initBakMargin();
		getProgressWH();
		initProgressMargin();
	}

	private void initBakDrawMargin() {
		bakDrawLeft = screentWidth - margerRight * screent_density
				- bitmapWidth;
		bakDrawTop = screentHight - margerButtom * screent_density
				- bitmapHeight;
	}
	/**
	 * 重新绘制画布高度
	 */
	@Override
	protected void onAttachedToWindow() {
		// TODO Auto-generated method stub
		android.widget.RelativeLayout.LayoutParams layout = new android.widget.RelativeLayout.LayoutParams(bitmapWidth, bitmapHeight);
		layout.setMargins((int)bakDrawLeft, (int)bakDrawTop, 0, 0);
		setLayoutParams(layout);
		super.onAttachedToWindow();
	}

	private void initBakMargin() {
		bakLeft = 58;
		bakTop = 40;
		bakRight = 93;
		bakBottom = 310;
	}

	private void initProgressMargin() {
		progressLeft = 58;
		progressTop = 40;
		progressRight = 93;
		progressBottom = 310;
	}

	private void getProgressWH() {
		progressWidth = bakRight - bakLeft;
		progressHeight = bakBottom - bakTop;
		progress = (float) currentVolume / (float) maxVolume;
		cureentProgressHeight = progressHeight * progress;
		MyLog.v(TAG, "progressWidth:" + progressWidth + "ProgressHeight:"
				+ progressHeight);
	}
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		MyLog.v(TAG, "onDraw()");
		// 画出背景
		canvas.drawBitmap(bitmapBg, 0, 0, paint);
		MyLog.v(TAG, "bakDrawLeft:" + bakDrawLeft + "bakDrawTop:" + bakDrawTop);
		canvas.drawRect(bakLeft, bakTop, bakRight, bakBottom, bakPaint);
		canvas.drawRect(progressLeft, progressBottom - cureentProgressHeight,
				progressRight, progressBottom, progressPaint);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float x = event.getX();
		float y = event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			return true;
		case MotionEvent.ACTION_MOVE:
			if (x >= bakLeft && x <= bakRight && y >= bakTop && y <= bakBottom) {
				setValue(y);
			} else if (x >= bakLeft && x <= bakRight && y < bakTop) {
				setValue(bakTop);
			}
			break;
		case MotionEvent.ACTION_UP:
			if (x >= bakLeft && x <= bakRight && y >= bakTop && y <= bakBottom) {
				setValue(y);
			} else if (x >= bakLeft && x <= bakRight && y < bakTop) {
				setValue(bakTop);
			}
			break;
		default:
			break;
		}
		return true;
	}

	private void setValue(float y) {
		float marValue = bakBottom - y;
		progress = marValue / progressHeight;
		currentVolume = (int) (maxVolume * progress);
		setVolumValue();
		setProgress();
	}

	public void setProgress() {
		progress = (float) currentVolume / (float) maxVolume;
		MyLog.v(TAG, "progress:" + progress + "currentVolume:" + currentVolume
				+ "maxVolume:" + maxVolume);
		cureentProgressHeight = progressHeight * progress;
		postInvalidate();
	}

	private void initVolum() {
		mAudioManager = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);
		maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		currentVolume = mAudioManager
				.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	public void setCurrentVolume(int currentVolume) {
		this.currentVolume = currentVolume;
		setProgress();
	}

	private void setVolumValue() {
		mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, currentVolume,
				0);
	}
}
