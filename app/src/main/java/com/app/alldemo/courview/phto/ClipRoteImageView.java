package com.app.alldemo.courview.phto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.ScaleGestureDetector.OnScaleGestureListener;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewTreeObserver;
import android.widget.ImageView;


/**
 * 用于缩放裁剪的自定义ImageView视图外加图片缩小到裁剪范围之内
 */
public class ClipRoteImageView extends ImageView implements View.OnTouchListener,
		ViewTreeObserver.OnGlobalLayoutListener {
	private static final String TAG = "ClipImageView";
	private static final int BORDERDISTANCE = ClipCicleView.BORDERDISTANCE;

	public static final float DEFAULT_MAX_SCALE = 4.0f;
	public static final float DEFAULT_MID_SCALE = 2.0f;
	public static final float DEFAULT_MIN_SCALE = 1.0f;

	private float minScale = DEFAULT_MIN_SCALE;
	private float midScale = DEFAULT_MID_SCALE;
	private float maxScale = DEFAULT_MAX_SCALE;

	private MultiGestureDetector multiGestureDetector;

	private int borderlength;// 裁剪正方形的边长

	private boolean isJusted;

	private final Matrix baseMatrix = new Matrix();
	private final Matrix drawMatrix = new Matrix();
	private final Matrix suppMatrix = new Matrix();
	private final RectF displayRect = new RectF();
	private final float[] matrixValues = new float[9];
	private boolean isScal;

	public ClipRoteImageView(Context context) {
		this(context, null);
	}

	public ClipRoteImageView(Context context, AttributeSet attr) {
		this(context, attr, 0);
	}

	public ClipRoteImageView(Context context, AttributeSet attr, int defStyle) {
		super(context, attr, defStyle);
		init(context);
	}

	private void init(Context context) {
		setOnTouchListener(this);
		multiGestureDetector = new MultiGestureDetector(context);
	}

	/**
	 * 依据图片宽高比例，设置图像初始缩放等级和位置
	 */
	private void configPosition() {
		super.setScaleType(ScaleType.MATRIX);
		Drawable d = getDrawable();
		if (d == null) {
			return;
		}
		final float viewWidth = getWidth();
		final float viewHeight = getHeight();
		final int drawableWidth = d.getIntrinsicWidth();
		final int drawableHeight = d.getIntrinsicHeight();
		borderlength = (int) (viewWidth - BORDERDISTANCE * 2);
		float scale = 1.0f;
		// 移动居中
		baseMatrix.postTranslate((viewWidth - drawableWidth * scale) / 2,
				(viewHeight - drawableHeight * scale) / 2);
		resetMatrix();
		isJusted = true;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		return multiGestureDetector.onTouchEvent(event);
	}

	private class MultiGestureDetector extends
			GestureDetector.SimpleOnGestureListener implements
			OnScaleGestureListener {
		// 这个类是专门用来检测两个手指在屏幕上做缩放的手势用的
		private final ScaleGestureDetector scaleGestureDetector;
		//移动的最小距离
		private final float scaledTouchSlop;

		private VelocityTracker velocityTracker;
		private boolean isDragging;//是否拖动
		float x = 0, y = 0;//触点的值
		float dx,dy;//触点的差值
		float lastTouchX;//上一次触点x值
		float lastTouchY;//上一次触点y值

		public MultiGestureDetector(Context context) {
			scaleGestureDetector = new ScaleGestureDetector(context, this);
			final ViewConfiguration configuration = ViewConfiguration
					.get(context);
			scaledTouchSlop = configuration.getScaledTouchSlop();
		}

		@Override
		public boolean onScale(ScaleGestureDetector detector) {
			float scaleFactor = detector.getScaleFactor();
			if (getDrawable() != null) {
				suppMatrix.postScale(scaleFactor, scaleFactor, getWidth() / 2,
						getHeight() / 2);
				setImageMatrix(getDisplayMatrix());
			}
			return true;
		}

		@Override
		public boolean onScaleBegin(ScaleGestureDetector detector) {
			isScal=false;
			return true;
		}

		@Override
		public void onScaleEnd(ScaleGestureDetector detector) {
			isScal = true;
		}

		public boolean onTouchEvent(MotionEvent event) {
			scaleGestureDetector.onTouchEvent(event);
            final int pointerCount = event.getPointerCount();
            if(pointerCount<2){
            	x=event.getX();
            	y=event.getY();
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				if (velocityTracker == null) {
                    velocityTracker = VelocityTracker.obtain();
                } else {
                    velocityTracker.clear();
                }
                velocityTracker.addMovement(event);
                lastTouchX = x;
                lastTouchY = y;
                isDragging = false;
				break;
			case MotionEvent.ACTION_MOVE:
				if(isScal){
					lastTouchX = x;
	                lastTouchY = y;
					isScal = false;
				}
				dx = x - lastTouchX;
				dy = y - lastTouchY;
				if (isDragging == false) {
                    isDragging = Math.sqrt((dx * dx) + (dy * dy)) >= scaledTouchSlop;
                }
				if (isDragging) {
                    if (getDrawable() != null) {
                        suppMatrix.postTranslate(dx, dy);
                        setImageMatrix(getDisplayMatrix());
                    }
                    lastTouchX = x;
                    lastTouchY = y;
                    if (velocityTracker != null) {
                        velocityTracker.addMovement(event);
                    }
                }
				break;
			case MotionEvent.ACTION_UP:
			case MotionEvent.ACTION_CANCEL:
				if (velocityTracker != null) {
                    velocityTracker.recycle();
                    velocityTracker = null;
                }
				break;
			}
            }
			return true;
		}

		@Override
		public boolean onDoubleTap(MotionEvent event) {
			return true;
		}
	}

	@Override
	public void onGlobalLayout() {
		if (isJusted) {
			return;
		}
		// 调整视图位置
		configPosition();
	}

	@Override
	protected void onAttachedToWindow() {
		super.onAttachedToWindow();
		getViewTreeObserver().addOnGlobalLayoutListener(this);
	}

	@SuppressWarnings("deprecation")
	@Override
	protected void onDetachedFromWindow() {
		super.onDetachedFromWindow();
		getViewTreeObserver().removeGlobalOnLayoutListener(this);
	}

	/**
	 * Resets the Matrix back to FIT_CENTER, and then displays it.s
	 */
	private void resetMatrix() {
		if (suppMatrix == null) {
			return;
		}
		suppMatrix.reset();
		setImageMatrix(getDisplayMatrix());
	}

	protected Matrix getDisplayMatrix() {
		drawMatrix.set(baseMatrix);
		drawMatrix.postConcat(suppMatrix);
		return drawMatrix;
	}

	/**
	 * 剪切图片，返回剪切后的bitmap对象
	 * 
	 * @return
	 */
	public Bitmap clip() {
		Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(),
				Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bitmap);
		draw(canvas);
		return Bitmap.createBitmap(bitmap, (getWidth() - borderlength) / 2,
				(getHeight() - borderlength) / 2, borderlength, borderlength);
	}
}
