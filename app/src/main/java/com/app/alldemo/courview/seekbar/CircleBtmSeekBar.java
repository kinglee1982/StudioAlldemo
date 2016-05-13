package com.app.alldemo.courview.seekbar;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.app.alldemo.R;


public class CircleBtmSeekBar extends View {
	int viewWidth, viewHeight;
	// 进度头部位置图标
	private Bitmap dragIcon;
	// 中间图片
	private Bitmap centerImage;
	// 总进度
	private int maxProgress = 18000;
	// 当前进度
	private int currentProgress;
	private OnProgressChangedListener onProgressChangedListener;
	// 进度条颜色
	private int progressColor;
	// 进度条宽度
	private int progressWidth;
	// 进度条头部位置图标宽度
	private int dragIconWidth;
	// 进度条头部位置图标宽度和进度条宽度的差距
	private int dragIconWidthOffset;
	// 边框宽度
	private int borderWidth;
	// 整个seekbar半径
	private float radius;
	// 绘制进度条的半径
	private float progressRadius;
	// 中间图片的半径
	private float centerImageRadius;
	// 进度的初始开始位置角度
	float startAngle = 90;
	// 进度条扫为的弧度
	double sweepAngle = 0;
	// 进度头部位置图标本身大小
	Rect dragIconSrcRect = new Rect();
	// 进度头部位置图标触摸区域
	RectF dragIconTouchRect = new RectF();
	// 进度头部位置图标显示区域
	RectF dragIconDstRect = new RectF();
	// 进度条整个显示区域
	RectF progressRectF = new RectF();
	Paint paint;
	PointF progressPoint = new PointF();
	private float centerX, centerY;
	BitmapShader bitmapShader;

	private boolean isDraging;

	Matrix mShaderMatrix = new Matrix();
	Paint mBitmapPaint = new Paint();
	private boolean flag;

	public CircleBtmSeekBar(Context context) {
		this(context, null);
	}

	public CircleBtmSeekBar(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public CircleBtmSeekBar(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init();
	}

	private void init() {
		progressWidth = 10;
		dragIconWidthOffset = 15;
		dragIconWidth = progressWidth + dragIconWidthOffset;
		borderWidth = 3;
		paint = new Paint();
		progressColor = Color.parseColor("#28b8e7");
		dragIcon = BitmapFactory.decodeResource(getResources(),
				R.drawable.icon_timeline);
		dragIconSrcRect.set(0, 0, dragIcon.getWidth(), dragIcon.getHeight());
	}

	public void setProgressColor(int progressColor) {
		this.progressColor = progressColor;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		if (!flag) {

			viewWidth = getLayoutParams().width; // Get View Width
			viewHeight = getLayoutParams().height;// Get View Height
			if (viewWidth == 0 || viewHeight == 0) {
				return;
			}
			init();
			int size = (viewWidth > viewHeight) ? viewHeight : viewWidth; // Choose

			centerX = viewWidth / 2.0f; // Center X for circle
			centerY = viewHeight / 2.0f; // Center Y for circle
			radius = size / 2.0f; // Radius of the outer circle
			int maxRadius = borderWidth < dragIconWidthOffset ? dragIconWidthOffset
					: borderWidth;
			radius -= maxRadius; // 不让拖拽图标显示在画布外
			progressRadius = radius - borderWidth - progressWidth / 2;
			centerImageRadius = progressRadius - progressWidth / 2;
			// 进度条上的起点
			progressPoint.set(centerX, centerY + progressRadius);
			// 这是画弧形的区域
			progressRectF.set(centerX - progressRadius, centerY
					- progressRadius, centerX + progressRadius, centerY
					+ progressRadius);
			updateNewPoint();
			flag = true;
		}
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		drawbg(canvas);
		drawProgress(canvas);
		drawCenterImage(canvas);
		drawDragIcon(canvas);
	}

	private void drawbg(Canvas canvas) {
		paint.reset();
		paint.setAntiAlias(true);
		paint.setColor(Color.WHITE);
		paint.setStyle(Paint.Style.FILL);
		paint.setStrokeWidth(0);
		canvas.drawCircle(centerX, centerY, radius, paint);
	}

	private void drawProgress(Canvas canvas) {
		paint.reset();
		paint.setAntiAlias(true);
		paint.setColor(progressColor);
		paint.setStyle(Paint.Style.STROKE);
		paint.setStrokeWidth(progressWidth);

		canvas.drawArc(progressRectF, startAngle, (float) sweepAngle, false,
				paint);
	}

	private void drawDragIcon(Canvas canvas) {
		if (dragIcon != null) {
			paint.reset();
			paint.setFilterBitmap(true);
			paint.setAntiAlias(true);
			canvas.drawBitmap(dragIcon, dragIconSrcRect, dragIconDstRect, paint);
		}
	}

	private void drawCenterImage(Canvas canvas) {
		if (centerImage != null) {
			int radius = (int) centerImageRadius;// src为我们要画上去的图，跟上一个示例中的bitmap一样。
			bitmapShader = new BitmapShader(centerImage, Shader.TileMode.CLAMP,
					Shader.TileMode.CLAMP);
			Bitmap dest1 = Bitmap.createBitmap(2 * (int) centerImageRadius,
					2 * (int) centerImageRadius, Bitmap.Config.ARGB_8888);
			Canvas c1 = new Canvas(dest1);
			updateShaderMatrix();
			mBitmapPaint.setAntiAlias(true);
			mBitmapPaint.setShader(bitmapShader);
			c1.drawCircle(radius, radius, radius, mBitmapPaint);
			canvas.drawBitmap(dest1, null, new RectF(centerX - radius, centerY
					- radius, centerX + radius, centerY + radius), null);
		}
	}

	/**
	 * 设置中间图片
	 * 
	 */
	public void setDragIcon(Bitmap dragIcon) {
		if (dragIcon == null) {
			return;
		}
		if (this.dragIcon != null) {
			this.dragIcon.recycle();
		}
		this.dragIcon = dragIcon;
	}

	public void setDragIcon(int dragIconResId) {
		setDragIcon(BitmapFactory.decodeResource(getResources(), dragIconResId));
	}

	public Bitmap getCenterImage() {
		return centerImage;
	}

	/**
	 * 设置中间图片
	 * 
	 * @param centerImage
	 */
	public void setCenterImage(Bitmap centerImage) {
		if (centerImage == null) {
			return;
		}
		if (this.centerImage != null) {
			this.centerImage.recycle();
		}
		this.centerImage = centerImage;
		postInvalidate();
	}

	/**
	 * 设置中间图片
	 * 
	 */
	public void setCenterImage(int centerImageResId) {
		setCenterImage(BitmapFactory.decodeResource(getResources(),
				centerImageResId));
	}

	public int getMaxProgress() {
		return maxProgress;
	}

	public void setMaxProgress(int maxProgress) {
		this.maxProgress = maxProgress;
	}

	public int getCurrentProgress() {
		return currentProgress;
	}

	/**
	 * 外部调用设置进度接口,内部拖动时,不响应外部进度事件
	 * 
	 * @param currentProgress
	 */
	public void setCurrentProgress(int currentProgress) {
		if (isDraging) { // 内部拖动,不响应外部进度事件
			Log.e("", "我正在拖 不要动");
			return;
		}
		if (currentProgress > maxProgress) {
			try {
				throw new Exception("当前进度超出了总进度");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Log.e("", "我没有拖 可以动");
		this.currentProgress = currentProgress;
		calculateNewPoint();
	}

	/**
	 * 拖动进度的时候内部调用
	 * 
	 * @param currentProgress
	 */
	private void setDragProgress(int currentProgress) {
		this.currentProgress = currentProgress;
		calculateNewPoint();
	}

	public void setOnProgressChangedListener(
			OnProgressChangedListener onProgressChangedListener) {
		this.onProgressChangedListener = onProgressChangedListener;
	}

	private void calculateNewPoint() {
		sweepAngle = 360 * (double) currentProgress / (double) maxProgress;
		float disX = (float) (Math.sin(Math.toRadians(sweepAngle)) * progressRadius);
		float disY = (float) (Math.cos(Math.toRadians(sweepAngle)) * progressRadius);
		progressPoint.set(centerX - disX, centerY + disY);
		updateNewPoint();
	}

	private void updateNewPoint() {
		dragIconDstRect.set(progressPoint.x - dragIconWidth, progressPoint.y
				- dragIconWidth, progressPoint.x + dragIconWidth,
				progressPoint.y + dragIconWidth);
		dragIconTouchRect.set(dragIconDstRect.left - 50,
				dragIconDstRect.top - 50, dragIconDstRect.right + 50,
				dragIconDstRect.bottom + 50);
		postInvalidate();
	}

	public interface OnProgressChangedListener {
		void onProgressChanged(CircleBtmSeekBar seekBar, int currentProgress,
							   int maxProgress);

		void onDragStart(CircleBtmSeekBar seekBar, int currentProgress,
						 int maxProgress);

		void onDraging(CircleBtmSeekBar seekBar, int currentProgress,
					   int maxProgress);

		void onDragStop(CircleBtmSeekBar seekBar, int currentProgress,
						int maxProgress);

	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			Log.e("onTouchEvent", "ACTION_DOWN");
			float downX = event.getX();
			float downY = event.getY();
			if (dragIconTouchRect.contains(downX, downY)) {
				getParent().requestDisallowInterceptTouchEvent(true);
				isDraging = true;
				notifyDragStart();
			}
			break;
		case MotionEvent.ACTION_MOVE:
			Log.e("onTouchEvent", "ACTION_MOVE");
			float moveX = event.getX();
			float moveY = event.getY();
			if (!dragIconTouchRect.contains(moveX, moveY)) {
				notifyDragStop();
				isDraging = false;
				return true;
			}
			double tan = Math.atan2((moveY - centerY), (moveX - centerX));
			double rd = Math.toDegrees(tan) - 90;
			double degrees = (rd > 0 ? rd : rd + 360);
			if (Math.abs(sweepAngle - degrees) > 200) { // 防止直接从起点逆时针滑动
				return true;
			}
			setDragProgress((int) (degrees / 360.0d * (double) maxProgress));
			notifyDraging();
			return true;
		case MotionEvent.ACTION_UP:
			isDraging = false;
			notifyDragStop();
			break;
		case MotionEvent.ACTION_CANCEL:
			isDraging = false;
			notifyDragStop();
			break;
		default:
			break;
		}
		return true;
	}

	private void notifyDragStop() {

		if (onProgressChangedListener != null) {
			onProgressChangedListener.onDragStop(CircleBtmSeekBar.this,
					currentProgress, maxProgress);
		}
	}

	private void notifyDragStart() {

		if (onProgressChangedListener != null) {
			onProgressChangedListener.onDragStart(CircleBtmSeekBar.this,
					currentProgress, maxProgress);
		}
	}

	private void notifyDraging() {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onDraging(CircleBtmSeekBar.this,
					currentProgress, maxProgress);
		}
	}

	private void notifyProgressChanged() {
		if (onProgressChangedListener != null) {
			onProgressChangedListener.onProgressChanged(CircleBtmSeekBar.this,
					currentProgress, maxProgress);
		}
	}

	private void updateShaderMatrix() {
		float scale;
		float dx = 0;
		float dy = 0;

		mShaderMatrix.set(null);

		if (centerImage.getWidth() * viewHeight > viewWidth
				* centerImage.getHeight()) {
			scale = viewHeight / (float) centerImage.getHeight();
			dx = (viewWidth - centerImage.getWidth() * scale) * 0.5f;
		} else {
			scale = viewWidth / (float) centerImage.getWidth();
			dy = (viewHeight - centerImage.getHeight() * scale) * 0.5f;
		}

		mShaderMatrix.setScale(scale, scale);
		mShaderMatrix.postTranslate((int) (dx + 0.5f), (int) (dy + 0.5f));

		bitmapShader.setLocalMatrix(mShaderMatrix);
	}

}
