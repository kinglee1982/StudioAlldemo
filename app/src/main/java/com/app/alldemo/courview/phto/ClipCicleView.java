package com.app.alldemo.courview.phto;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.AttributeSet;
import android.view.View;
/**
 * 裁剪边框，圆形
 */
public class ClipCicleView extends View {
	/**
	 * 边框距左右边界距离，用于调整边框长度
	 */
	public static int BORDERDISTANCE = 50;
	private Point center;// 圆心点
	private static  float radius;//圆饼半径
	private Paint paint_circus = new Paint();
	public ClipCicleView(Context context) {
		this(context, null);
	}

	public ClipCicleView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ClipCicleView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		cicle(canvas);
	}
	
	private void cicle(Canvas canvas){
		int width = this.getWidth();
		int height = this.getHeight();
		center = new Point(width / 2, height / 2);
		paint_circus.setColor(Color.WHITE);
		paint_circus.setStyle(Paint.Style.STROKE);
		paint_circus.setStrokeWidth(4);
		paint_circus.setAntiAlias(true);
		radius=(width - BORDERDISTANCE *2)/2;
		canvas.drawCircle(center.x, center.y, radius, paint_circus);
	}
}
