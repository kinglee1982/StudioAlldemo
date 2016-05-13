package com.app.alldemo.courview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.util.AttributeSet;
import android.view.View;

import com.app.alldemo.utils.MyLog;

public class CaloriesGraph extends View {
	private static final String TAG="CaloriesGraph";
	public CaloriesGraph(Context context) {
		super(context);
		init(context);
	}

	public CaloriesGraph(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public CaloriesGraph(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}
	@Override  
    protected void onDraw(Canvas canvas) {  
        super.onDraw(canvas); 
        graphDraw(canvas);
	}
	private Paint textPaint=new Paint();
	private Paint yTextPaint=new Paint();//y轴的字
	private Paint xLinePaint=new Paint();//x轴的线
	private Paint tLinePaint=new Paint();//目标线
	private Paint tTextPaint=new Paint();//目标字体
	private Paint xtextPaint=new Paint();//x轴的字
	
	private float density;// 屏幕密度
	private static final float textDistanceX=20;//消费离左边的距离
	private static final float textDistanceY=20;//消费离上边的距离
	
	private static final String textColor1="#000000";//消费字体颜色
	private String text1="消费力(8月14日 —— 9月17日)";
	private float text1DistanceX;
	private float text1DistanceY;
	private float text1Size=14f;//消费字体大小
	private static final String textColor2="#BEBEBE";//1340字体颜色
	private float text2Size=34f;
	private String text2="1340";
	private float text2DistanceX;
	private float text2DistanceY;
	private float addText1DistanceY=35;
	private static final String textColor3="#d0d0d0";//kcal字体颜色
	private float text3Size=14f;
	private String text3="kcal";
	private float text3DistanceX;
	private float addtext2DistanceX=90;//1340的宽度
	private float text3DistanceY;
	private int maxYScale=720;//最大y轴的刻度值,6的倍数
	private static final float viewRightWidth=10;//离右边的距离
	private float scaleHeight;//刻度的整个高度
	private float scaleWidth;//刻度的整个宽度
	private float scaleTopBlank=30;//刻度顶部空的距离
	private float scaleTop;//刻度顶部开始坐标y轴值,刻度值离顶部的距离，500kcal以上的值
	private float scaleButomBlank=10;//刻度底部空的距离,刻度与8/3-9之间的距离
	private float scaleButom;//刻度底部的距离
	private float eachScaleHeight;//每个刻度的高度
	private float eachScaleWidth;//每个刻度的宽度
	private static final String scaleColor="#6C6C6C";//刻度的字体颜色
	private float scaleBlankX=10;//y轴刻度左边空的距离
	private String shortLine="-";
	private String scaleText6="720";
	private float scale6DistanceY;
	private float scale6DistanceX;
	private String scaleText5="600";
	private float scale5DistanceY;
	private String scaleText4="480";
	private float scale4DistanceY;
	private String scaleText3="360";
	private float scale3DistanceY;
	private String scaleText2="240";
	private float scale2DistanceY;
	private String scaleText1="120";
	private float scale1DistanceY;
	private String scaleText0="0";
	private float scale0DistanceY;
	private float centerDistanceY;//左下角(0,0)y的坐标
	private float centerDistanceX;//左下角(0,0)x的坐标
	private final float scaleValueWidth=8;//刻度值的宽度
	private final float scaleValueHeight=9;//刻度值的高度
	private final float shLineWidth=8;//短横线的宽度
	private final float shLineBlankWidth=4;//短横线的宽度右边的一点空白
	//x的刻度线
	private static final String xLineColor="#000000";//刻度的字体颜色
	private static final float xlineHeight=30;//x轴上的线条
	private float xline0XStart;//x轴上的第一条线
	private float xline0YStart;//
	private float xline1Start,xline2Start,xline3Start,xline4Start,xline5Start;
	//目标kcal
	private static final String tLineColor="#FF2D2D";
	private float tlineXStart;
	private float tlineYStart;
	private final float tlineYBlank=10f;//字与线之间的距离
	private float tlineXEnd;
	private float tlineYEnd;
	private static final String tTextColor="#FF2D2D";
	private String tTextString="目标600kcal";
	private float tTextX;
	private float tTextY;
	
	//x轴的月份字体
	private static final String xColor="#000000";//8/3-9字体颜色
	private String xText1="8/3-9";
	private static final float xTextButom=10;//离底部的距离,8/3-9离底部的距离
	private static final float xTextHeight=10;//8/3-9的高度,
	private float xText1X;
	private float xText1Y;
	private void init(Context context){
		density = context.getResources().getDisplayMetrics().density;
		MyLog.v(TAG, "density:"+density);
		textPaint.setStyle(Paint.Style.STROKE);
		textPaint.setTextAlign(Align.LEFT);
		textPaint.setAntiAlias(true);
		
		yTextPaint.setStyle(Paint.Style.STROKE);
		yTextPaint.setTextAlign(Align.RIGHT);
		yTextPaint.setAntiAlias(true);
		yTextPaint.setTextSize(14 * density);
		yTextPaint.setColor(Color.parseColor(scaleColor));
		
		xtextPaint.setStyle(Paint.Style.STROKE);
		xtextPaint.setTextAlign(Align.LEFT);
		xtextPaint.setAntiAlias(true);
		xtextPaint.setColor(Color.parseColor(xColor));
		xtextPaint.setTextSize(14*density);
		
		xLinePaint.setStrokeWidth(1*density);
		xLinePaint.setColor(Color.parseColor(xLineColor));
		xLinePaint.setAntiAlias(true);
		
		tLinePaint.setStrokeWidth(2*density);
		tLinePaint.setColor(Color.parseColor(tLineColor));
		tLinePaint.setAntiAlias(true);
		
		tTextPaint.setStyle(Paint.Style.STROKE);
		tTextPaint.setTextAlign(Align.RIGHT);
		tTextPaint.setAntiAlias(true);
		tTextPaint.setColor(Color.parseColor(tTextColor));
		tTextPaint.setTextSize(20*density);
	}
	private void graphDraw(Canvas canvas){
		int width = getWidth();
		int height = getHeight();
		MyLog.v(TAG, "width:"+width+"height:"+height);
		//消费
		textPaint.setTextSize(text1Size * density);
		textPaint.setColor(Color.parseColor(textColor1));
		text1DistanceX=textDistanceX*density;
		text1DistanceY=textDistanceY*density;
		canvas.drawText(text1,text1DistanceX,text1DistanceY,textPaint);
		//1340
		textPaint.setTextSize(text2Size * density);
		textPaint.setColor(Color.parseColor(textColor2));
		text2DistanceY=text1DistanceY+addText1DistanceY*density;
		text2DistanceX=text1DistanceX;
		canvas.drawText(text2,text2DistanceX,text2DistanceY,textPaint);
		//kcal
		textPaint.setTextSize(text3Size * density);
		textPaint.setColor(Color.parseColor(textColor3));
		text3DistanceX=text2DistanceX+addtext2DistanceX*density;
		text3DistanceY=text2DistanceY;
		canvas.drawText(text3,text3DistanceX,text3DistanceY,textPaint);
		//计算坐标原点(0,0)
		centerDistanceX=(scaleBlankX+scaleValueWidth*scaleText6.length()
				+shLineWidth+shLineBlankWidth)*density;
		centerDistanceY=height-(xTextButom+xTextHeight+scaleButomBlank+scaleValueHeight/2)*density;
		//8/3-9
		xText1X=centerDistanceX;
		xText1Y=height-xTextButom*density;
		canvas.drawText(xText1,xText1X,xText1Y,xtextPaint);
		//计算
		scaleTop=text2DistanceY+scaleTopBlank*density;
		scaleButom=(scaleButomBlank+xTextButom+xTextHeight)*density;
		scaleHeight=height-scaleTop-scaleButom;
		eachScaleHeight=scaleHeight/6;
		scaleWidth=width-centerDistanceX-viewRightWidth*density;
		eachScaleWidth=scaleWidth/5;
		MyLog.v(TAG, "scaleHeight:"+scaleHeight+"eachScaleHeight:"+eachScaleHeight
				+"scaleTop:"+scaleTop+"scaleButom:"+scaleButom);
		//y轴kcal
		scale6DistanceY=scaleTop;
		scale6DistanceX=centerDistanceX;
		canvas.drawText(scaleText6+shortLine,scale6DistanceX,scale6DistanceY,yTextPaint);
		scale5DistanceY=scale6DistanceY+eachScaleHeight;
		canvas.drawText(scaleText5+shortLine,scale6DistanceX,scale5DistanceY,yTextPaint);
		scale4DistanceY=scale5DistanceY+eachScaleHeight;
		canvas.drawText(scaleText4+shortLine,scale6DistanceX,scale4DistanceY,yTextPaint);
		scale3DistanceY=scale4DistanceY+eachScaleHeight;
		canvas.drawText(scaleText3+shortLine,scale6DistanceX,scale3DistanceY,yTextPaint);
		scale2DistanceY=scale3DistanceY+eachScaleHeight;
		canvas.drawText(scaleText2+shortLine,scale6DistanceX,scale2DistanceY,yTextPaint);
		scale1DistanceY=scale2DistanceY+eachScaleHeight;
		canvas.drawText(scaleText1+shortLine,scale6DistanceX,scale1DistanceY,yTextPaint);
		scale0DistanceY=scale1DistanceY+eachScaleHeight;
		canvas.drawText(scaleText0+shortLine,scale6DistanceX,scale0DistanceY,yTextPaint);
		//x轴上的线
		xline0XStart=centerDistanceX;
		xline0YStart=centerDistanceY;
		canvas.drawLine(xline0XStart, xline0YStart, xline0XStart, xline0YStart-xlineHeight*density, xLinePaint);
		xline1Start=xline0XStart+eachScaleWidth;
		canvas.drawLine(xline1Start, xline0YStart, xline1Start, xline0YStart-xlineHeight*density, xLinePaint);
		xline2Start=xline1Start+eachScaleWidth;
		canvas.drawLine(xline2Start, xline0YStart, xline2Start, xline0YStart-xlineHeight*density, xLinePaint);
		xline3Start=xline2Start+eachScaleWidth;
		canvas.drawLine(xline3Start, xline0YStart, xline3Start, xline0YStart-xlineHeight*density, xLinePaint);
		xline4Start=xline3Start+eachScaleWidth;
		canvas.drawLine(xline4Start, xline0YStart, xline4Start, xline0YStart-xlineHeight*density, xLinePaint);
		xline5Start=xline4Start+eachScaleWidth;
		canvas.drawLine(xline5Start, xline0YStart, xline5Start, xline0YStart-xlineHeight*density, xLinePaint);
		//目标
		tlineXStart=xline0XStart;
		tlineYStart=scale5DistanceY-scaleValueHeight*density/2;
		tlineXEnd=xline5Start;
		tlineYEnd=tlineYStart;
		canvas.drawLine(tlineXStart, tlineYStart, tlineXEnd, tlineYEnd, tLinePaint);
		tTextX=tlineXEnd;
		tTextY=tlineYStart-tlineYBlank*density;
		canvas.drawText(tTextString,tTextX,tTextY,tTextPaint);
		//主体部分
		
	}
}
