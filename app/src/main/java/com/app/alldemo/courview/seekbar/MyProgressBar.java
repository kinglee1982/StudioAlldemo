package com.app.alldemo.courview.seekbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.view.View;

import com.app.alldemo.R;


public class MyProgressBar extends View {

	private float progress = 0;
	private float maxProgress = 100;
	private Bitmap backgroundBitmap;
	private Bitmap barBitmap;
	private int barWidth;
	private int barHeight;

	public MyProgressBar(Context context) {
		super(context);
		init(context, null);
	}

	public MyProgressBar(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context, attrs);
	}

	public MyProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context, attrs);
	}

	private void init(Context context, AttributeSet attrs) {
		if (isInEditMode()) {
			return;
		}
		if (attrs != null) {
			TypedArray typedArray = context.obtainStyledAttributes(attrs,
					R.styleable.MyPeogress);
			maxProgress = typedArray.getFloat(
					R.styleable.MyPeogress_maxProgress, maxProgress);
			progress = typedArray.getFloat(R.styleable.MyPeogress_progress,
					progress);
		}
		backgroundBitmap = BitmapFactory.decodeResource(getResources(),
				R.drawable.progress_bak);
		barBitmap = BitmapFactory
				.decodeResource(getResources(), R.drawable.progress_bar).copy(
						Bitmap.Config.ARGB_8888, true);
		barWidth = backgroundBitmap.getWidth();
		barHeight = backgroundBitmap.getHeight();
	}

	/**
	 * 参考http://blog.sina.com.cn/s/blog_4a0238270101ivfd.html<br>
	 * http://blog.csdn.net/edisonlg/article/details/7084977
	 */
	@Override
	protected void onDraw(Canvas canvas) {
		// 背景部分
		canvas.drawBitmap(backgroundBitmap, 0, 0, null);
		Paint paint = new Paint();
		paint.setFilterBitmap(false);

		int x = 0;
		int y = 0;
		int barWidthValue = barWidth;
		int barHeightValue = barHeight;

		int sc = canvas.saveLayer(x, y, x + barWidthValue, y + barHeightValue,
				null, Canvas.MATRIX_SAVE_FLAG | Canvas.CLIP_SAVE_FLAG
						| Canvas.HAS_ALPHA_LAYER_SAVE_FLAG
						| Canvas.FULL_COLOR_LAYER_SAVE_FLAG
						| Canvas.CLIP_TO_LAYER_SAVE_FLAG);
		canvas.drawRect(0, 0, progress / maxProgress * barWidthValue,
				barHeightValue, paint);
		// 在正常的情况下，在已有的图像上绘图将会在其上面添加一层新的形状
		// 。如果新的Paint是完全不透明的，那么它将完全遮挡住下面的Paint；
		// 如果它是部分透明的，那么它将会被染上下面的颜色。
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(barBitmap, 0f, 0f, paint);
		paint.setXfermode(null);
		canvas.restoreToCount(sc);
	}

	/**
	 * 参考http://blog.csdn.net/ethan_xue/article/details/7401163
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		setMeasuredDimension(measureWidth(widthMeasureSpec),
				measureHeight(heightMeasureSpec));
	}

	private int measureWidth(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);

		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = barWidth + getPaddingLeft() + getPaddingRight();
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	private int measureHeight(int measureSpec) {
		int result = 0;
		int specMode = MeasureSpec.getMode(measureSpec);
		int specSize = MeasureSpec.getSize(measureSpec);
		if (specMode == MeasureSpec.EXACTLY) {
			result = specSize;
		} else {
			result = barHeight + getPaddingTop() + getPaddingBottom();
			if (specMode == MeasureSpec.AT_MOST) {
				result = Math.min(result, specSize);
			}
		}
		return result;
	}

	public float getProgress() {
		return progress;
	}

	public void setProgress(float progress) {
		this.progress = progress;
		invalidate();
	}
}
