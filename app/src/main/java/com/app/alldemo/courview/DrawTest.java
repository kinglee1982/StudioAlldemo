package com.app.alldemo.courview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.graphics.Point;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.app.alldemo.utils.MyLog;

import java.util.ArrayList;

/**
 * 实线和虚线的绘制
 * 
 * @author Administrator
 *
 */
public class DrawTest extends View {
	private static final String TAG="DrawTest";
	public DrawTest(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public DrawTest(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
	}

	public DrawTest(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		acrTo(canvas);
	}
	private void xu(Canvas canvas){
		// 虚线的绘制
		Paint bak_x_line = new Paint();
		bak_x_line.setColor(Color.parseColor("#000000"));
		bak_x_line.setStyle(Style.STROKE);
		bak_x_line.setStrokeWidth(2 * 2);
		Path bak_x_path = new Path();
		bak_x_path.moveTo(10, 10);
		bak_x_path.lineTo(10 + 300, 10);
		PathEffect cal_effects = new DashPathEffect(new float[] { 5 * 2, 5 * 2,
				5 * 2, 5 * 2 }, 5 * 2);
		bak_x_line.setPathEffect(cal_effects);
		canvas.drawPath(bak_x_path, bak_x_line);
		Path bak_x_path2 = new Path();
		bak_x_path2.moveTo(20, 20);
		bak_x_path2.lineTo(20 + 300, 20);
		canvas.drawPath(bak_x_path2, bak_x_line);
	}
	/**
	 * 虚线
	 * @param canvas
	 */
	private void xuline(Canvas canvas) {
		// 虚线的绘制
		Paint bak_x_line = new Paint();
		bak_x_line.setColor(Color.parseColor("#000000"));
		bak_x_line.setStyle(Style.STROKE);
		bak_x_line.setStrokeWidth(2 * 2);
		Path bak_x_path = new Path();
		bak_x_path.moveTo(10, 10);
		bak_x_path.lineTo(10 + 300, 10);
		PathEffect cal_effects = new DashPathEffect(new float[] { 5 * 2, 5 * 2,
				5 * 2, 5 * 2 }, 5 * 2);
		bak_x_line.setPathEffect(cal_effects);
		canvas.drawPath(bak_x_path, bak_x_line);
		// 绘制连线
		float density = 2.0f;
		Paint mCircle = new Paint();
		mCircle.setAntiAlias(true);
		mCircle.setStrokeWidth(2.0F * density);
		mCircle.setColor(Color.parseColor("#00ff00"));
		mCircle.setStrokeWidth(12 * density);
		// 先绘制圆点
		ArrayList<Point> points = new ArrayList<Point>();
		for (int i = 0; i < 3; i++) {
			float cx = 12 + i * 50;
			float cy = 24 + i * 50;
			canvas.drawCircle(cx, cy, 3.0F * 2, mCircle);
			points.add(new Point((int) (cx), (int) (cy)));
		}
		// 绘制连线
		mCircle.setStrokeWidth(2.0F * density);
		for (int i = 0; i < points.size()-1; i++) {
			canvas.drawLine(points.get(i).x, points.get(i).y,
					points.get(i + 1).x, points.get(i + 1).y, mCircle);
		}
	}

	/**
	 * 多边形
	 * 
	 * @param canvas
	 */
	private void polygonTest(Canvas canvas) {
		Paint bodyLinePaint = new Paint();// 主题部分，实线
		bodyLinePaint.setColor(Color.parseColor("#ffe157"));
		bodyLinePaint.setAntiAlias(true);
		bodyLinePaint.setStyle(Style.STROKE);
		bodyLinePaint.setTextAlign(Align.CENTER);
		bodyLinePaint.setStrokeWidth(3f * 2);
		canvas.drawLine(30, 500, 50, 400, bodyLinePaint);
		canvas.drawLine(50, 400, 55, 420, bodyLinePaint);
		canvas.drawLine(55, 420, 60, 400, bodyLinePaint);
		canvas.drawLine(60, 400, 100, 550, bodyLinePaint);
		canvas.drawLine(100, 550, 400, 250, bodyLinePaint);
		canvas.drawLine(400, 250, 300, 600, bodyLinePaint);

		Paint paint = new Paint();
		paint.setColor(Color.parseColor("#6ac6b9"));
		paint.setAntiAlias(true);
		paint.setStyle(Style.FILL);
		Path path = new Path();
		path.moveTo(30, 600); // 不会进行绘制，只用于移动移动画笔,把画笔移动到哪开始
		path.lineTo(30, 500); // 用于进行直线绘制
		path.lineTo(50, 400);
		path.lineTo(55, 420);
		path.lineTo(60, 400);
		path.lineTo(100, 550);
		path.lineTo(400, 250);
		path.lineTo(300, 600);
		path.close();// 回到初始点形成封闭的曲线
		canvas.drawPath(path, paint);

		Paint bodyDotPaint = new Paint();
		bodyDotPaint.setColor(Color.parseColor("#ff7800"));
		bodyDotPaint.setAntiAlias(true);
		bodyDotPaint.setStyle(Style.FILL);
		bodyDotPaint.setTextAlign(Align.CENTER);
		canvas.drawCircle(30, 500, 3f * 2, bodyDotPaint);
	}

	/**
	 * 三角形-五角星
	 * 
	 * @param canvas
	 */
	private void triangleTest(Canvas canvas) {
		Paint trianglePaint = new Paint();
		trianglePaint.setColor(Color.parseColor("#EA0000"));
		trianglePaint.setAntiAlias(true);
		trianglePaint.setStyle(Style.FILL);
		Path trianglePath = new Path();
		trianglePath.moveTo(100, 300);
		trianglePath.lineTo(150, 300);
		trianglePath.lineTo(125, 325);
		trianglePath.close();// 回到初始点形成封闭的曲线
		canvas.drawPath(trianglePath, trianglePaint);
		//定义一个Path对象，封闭一个五角星   
        Path path2 = new Path();  
        path2.moveTo(27, 360);  
        path2.lineTo(54, 360);  
        path2.lineTo(70, 392);  
        path2.lineTo(40, 420);  
        path2.lineTo(10, 392);  
        path2.close();  
        //根据Path进行绘制，绘制五角星   
        canvas.drawPath(path2, trianglePaint); 
	}

	/**
	 * 圆角矩形
	 * 
	 * @param canvas
	 */
	private void back(Canvas canvas) {
		Paint p = new Paint();
		p.setStyle(Style.FILL);// 充满
		p.setColor(Color.parseColor("#8E8E8E"));
		p.setAntiAlias(true);
		float left1 = 180;
		float top1 = 260;
		float right1 = 300;
		float buttom1 = 300;
		float rx = 40;
		float ry = 40;
		RectF oval3 = new RectF(left1, top1, right1, buttom1);// 设置个新的长方形
		canvas.drawRoundRect(oval3, rx, ry, p);// 第二个参数是x半径，第三个参数是y半径
	}

	/**
	 * 绘制多条直线
	 * 
	 * @param canvas
	 */
	private void drawLines(Canvas canvas) {
		float[] pts = { 50, 50, 400, 50, // 第一条线
				300, 10, 400, 600, // 第二条线
				400, 600, 50, 600 // 第三条线
		};
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		// 设置画笔宽度
		paint.setStrokeWidth(3);
		// 消除锯齿
		paint.setAntiAlias(true);
		// 设置镂空（方便查看效果）
		paint.setStyle(Style.STROKE);
		canvas.drawLines(pts, paint);

		// 多条虚线
		float[] effectsPts = { 50, 50 + 10, 400, 50 + 10, 300 - 20, 10 + 10,
				400 - 20, 600 + 10, 400, 600 + 10, 50, 600 + 10 };
		Paint effectsPaint = new Paint();
		effectsPaint.setColor(Color.RED);
		// 设置画笔宽度
		effectsPaint.setStrokeWidth(3);
		// 消除锯齿
		effectsPaint.setAntiAlias(true);
		// 设置镂空（方便查看效果）
		effectsPaint.setStyle(Style.STROKE);
		PathEffect cal_effects = new DashPathEffect(new float[] { 5 * 2, 5 * 2,
				5 * 2, 5 * 2 }, 5 * 2);
		effectsPaint.setPathEffect(cal_effects);
		canvas.drawLines(effectsPts, effectsPaint);
	}

	/**
	 * 不规则的图形 贝塞尔曲线-quadTo
	 * 
	 * @param canvas
	 */
	private void irregular(Canvas canvas) {
		Path p = new Path();
		p.moveTo(40, 100);// 移动画笔
		p.lineTo(100,100);
		p.quadTo(200, 100, 300, 500);// 第一个控制点,第二个控制点
//		RectF oval = new RectF(200, 200, 100, 100);
//		p.arcTo(oval, 90, 180);//true:不连接,false:连接:默认值
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.STROKE);
		canvas.drawPath(p, paint);
	}

	private void acrTo(Canvas canvas){
		Path p = new Path();
//		p.moveTo(40, 100);// 移动画笔
		RectF oval = new RectF(100, 100, 200, 200);
		p.arcTo(oval, 180, -90);//true:不连接,false:连接:默认值

		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.STROKE);
		canvas.drawPath(p, paint);
	}

	/**
	 * 不规则的图形 贝塞尔曲线-quadTo,多一个控制点
	 * 
	 * @param canvas
	 */
	private void cubicTo(Canvas canvas) {
		Path p = new Path();
		p.moveTo(100, 500);// 移动画笔
		p.cubicTo(100, 500, 200, 100, 300, 500);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.STROKE);
		canvas.drawPath(p, paint);
	}

	/**
	 * 绘制弧线（实际是截取圆或椭圆的一部分）。
	 * 
	 * @param canvas
	 */
	private void arcTo(Canvas canvas) {
		Path p = new Path();
		// ovalRectF为椭圆的矩形，startAngle 为开始角度，sweepAngle为结束角度
		RectF mRectF = new RectF(30, 80, 100, 140);
		p.arcTo(mRectF, 0, 180);
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.STROKE);
		canvas.drawPath(p, paint);
		canvas.drawRect(mRectF, paint);
	}

	private void drawRect(Canvas canvas) {
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.FILL);
		// 圆
		canvas.drawCircle(40, 40, 30, paint);
		// 绘制正方形
		canvas.drawRect(10, 80, 70, 140, paint);
		// 绘制矩形
		canvas.drawRect(10, 150, 70, 190, paint);
		RectF rel = new RectF(10, 240, 70, 270);
		// 绘制椭圆
		canvas.drawOval(rel, paint);
	}

	/**
	 * 绘制文字
	 * @param canvas
	 */
	private void drawText(Canvas canvas){
		Paint paint = new Paint();
		paint.setColor(Color.RED);
		paint.setStrokeWidth(1);
		paint.setStyle(Style.FILL);
		String text="我们的好";
		canvas.drawText(text, 200, 200, paint);
		float width=paint.measureText(text);
		MyLog.v(TAG, "字的宽度:"+width);
	}
}
