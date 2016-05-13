package com.app.alldemo.courview.phto;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.media.ExifInterface;
import android.os.Environment;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.VelocityTracker;
import android.view.View;
import android.view.WindowInsets;
import android.widget.Scroller;

import com.app.alldemo.utils.MyLog;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Administrator on 2016/2/14.
 */
public class CutImageView extends View {
    final String TAG = "ShearImage";

    private ArrayList<Bitmap> bitmapList = new ArrayList<>();
    /**
     * 计算指滑速度
     */
    private VelocityTracker mTracker;
    /**
     * 不剪切
     */
    final int SHARE_TYPE_NONE = 0;
    /**
     * 圆形剪切
     */
    public final int SHARE_TYPE_CIRCLE = 1;
    /**
     * 五角星剪切️
     */
    public final int SHARE_TYPE_STAR = 2;
    /**
     * 方形剪切
     */
    public final int SHARE_TYPE_RECT = 3;
    /**
     * 心形剪切
     */
    public final int SHARE_TYPE_HEART = 4;

    /**
     * 正方形剪切
     */
    public final int SHARE_TYPE_SQUARE = 5;


    /**
     * 默认背景
     */
    private int backgroud = Color.parseColor("#343434");
    /**
     * 当前剪切方式
     */
    private int shearType = SHARE_TYPE_CIRCLE;

    /**
     * 是否为双击事件
     */
    private boolean isDoubleTap;

    /**
     * 图片原始路径
     */
    private String srcPath;
    private int canvasWidth;
    private int canvasHeight;
    private float maxCircleRadius;
    private float circleRadius;
    private float minCleRadius = 100;
    private Point canvasCenter = new Point();
    private RectF srcImageRect = new RectF();

    private Scroller mScroller;
    private Cut mScaler;

    private PointF scaleCenter = new PointF();


    /**
     * 星形路径
     */
    private Path starPath = new Path();
    /**
     * 心形路径
     */
    private Path heartPath = new Path();


    private RectF squareRect = new RectF();

    private float maxScale = 3.0f;
    private float largeScale = 2.0f;
    private float minScale = 0.3f;
    private float currentScale = 1.0f;
    private ScaleGestureDetector scaleGestureDetector;
    private GestureDetector gestureDetector;

    private OnOptionListener onOptionListener;

    /**
     * 图片的matrix
     */
    private Matrix mMatrix = new Matrix();
    /**
     * 图片的画笔
     */
    private Paint bitmapPaint = new Paint();
    /**
     * 图片上面剪切形状的画笔
     */
    private Paint shearPaint = new Paint();

    public CutImageView(Context context) {
        this(context, null);
    }

    public CutImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CutImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        shearPaint.setAntiAlias(true);
        shearPaint.setStyle(Paint.Style.FILL);
//        shearPaint.setDither(true);
        Xfermode xFermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
        shearPaint.setXfermode(xFermode);
        shearPaint.setColor(Color.BLACK);
        gestureDetector = new GestureDetector(context, new simpleGestureListener());
        scaleGestureDetector = new ScaleGestureDetector(context, new MyScaleListener());
        mScroller = new Scroller(context);
        mScroller.setFriction(0.05f);
        mScaler = new Cut(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        canvasWidth = MeasureSpec.getSize(widthMeasureSpec);
        canvasHeight = MeasureSpec.getSize(heightMeasureSpec);

        canvasCenter.x = canvasWidth / 2;
        canvasCenter.y = canvasHeight / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(backgroud);
        if (srcBitmap != null) {
            canvas.drawBitmap(srcBitmap, mMatrix, bitmapPaint);
            if (shearType != SHARE_TYPE_NONE) {
                canvas.saveLayerAlpha(0, 0, canvasWidth, canvasHeight, 150, Canvas.ALL_SAVE_FLAG);
                canvas.drawRGB(0, 0, 0);
                switch (shearType) {
                    case SHARE_TYPE_CIRCLE:
                        canvas.drawCircle(canvasCenter.x, canvasCenter.y, circleRadius, shearPaint);
                        break;
                    case SHARE_TYPE_STAR:
                        canvas.drawPath(starPath, shearPaint);
                        break;
                    case SHARE_TYPE_RECT:

                        break;
                    case SHARE_TYPE_HEART:
                        canvas.drawPath(heartPath, shearPaint);
                        break;
                    case SHARE_TYPE_SQUARE:
                        canvas.drawRoundRect(squareRect, 10, 10, shearPaint);
                        break;
                }
                canvas.restore();
            }
        }
    }

    /**
     * 变换后的图片位置、大小信息
     */
    RectF finalRectF = new RectF();

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        getTracker(event);
        scaleGestureDetector.onTouchEvent(event);
        gestureDetector.onTouchEvent(event);
        switch (event.getAction()) {
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mTracker.computeCurrentVelocity(1000);
                if (!isDoubleTap) {
                    startScroll(event.getX(), event.getY(), mTracker.getXVelocity(), mTracker.getYVelocity());
                }
                releaseTracker();
                if (onOptionListener != null) {
                    onOptionListener.onEnd();
                }
                i = 0;
                isDoubleTap = false;
                break;
        }
        return true;
    }

    void startScroll(float startX, float startY, float xSpeed, float ySpeed) {
        if (Math.abs(xSpeed) > 100 && Math.abs(ySpeed) > 100) {
            tempX = Math.round(startX);
            tempY = Math.round(startY);
            int yDistance = 0;
            int xDistance = 0;
            if (Math.abs(xSpeed) > Math.abs(ySpeed)) {
                xDistance = 400;
                yDistance = Math.round(400 * Math.abs(ySpeed) / Math.abs(xSpeed));
            } else {
                yDistance = 400;
                xDistance = Math.round(400 * Math.abs(xSpeed) / Math.abs(ySpeed));
            }
            mScroller.fling(tempX, tempY, Math.round(xSpeed), Math.round(ySpeed), tempX - xDistance, tempX + xDistance, tempY - yDistance, tempY + yDistance);
        }
    }

    float[] matrixValues = new float[9];

    @Override
    public void postInvalidate() {
        super.postInvalidate();
        mMatrix.mapRect(finalRectF, srcImageRect);
        mMatrix.getValues(matrixValues);
        currentScale = matrixValues[Matrix.MSCALE_X];
        MyLog.e("finalRectF", finalRectF.left + "  " + finalRectF.right + "  " + currentScale);

    }

    Bitmap srcBitmap;


    /**
     * 设置欲剪切图片路径
     *
     * @param path 图片路径
     */
    public void setImagePath(final String path) {
        if (TextUtils.isEmpty(path)) {
            throw new RuntimeException("path 为空");
        }
        if (!new File(path).exists()) {
            throw new RuntimeException("文件不存在：" + path);
        }
        if (canvasCenter.x == 0) {
            postDelayed(new Runnable() {
                @Override
                public void run() {
                    setImagePath(path);
                }
            }, 100);
            return;
        }
        this.srcPath = path;
        log("path: " + path);
        maxCircleRadius = canvasHeight > canvasWidth ? canvasWidth / 2 : canvasHeight / 2;
        circleRadius = maxCircleRadius * 0.8f;
        getHeartPath();
        getStarPath();
        squareRect.set(canvasCenter.x - circleRadius, canvasCenter.y - circleRadius, canvasCenter.x + circleRadius, canvasCenter.y + circleRadius);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(path, options);
        final int srcWidth = options.outWidth;
        final int srcHeight = options.outHeight;
        int rotateWidth = canvasWidth;
        int rotateHeight = canvasHeight;
        try {
            ExifInterface exif = new ExifInterface(path);
            int rotation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, 0);
            switch (rotation) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    mMatrix.postRotate(90, srcWidth / 2.0f, srcHeight / 2.0f);
                    rotateWidth = canvasHeight;
                    rotateHeight = canvasWidth;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    mMatrix.postRotate(180, srcWidth / 2.0f, srcHeight / 2.0f);
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    mMatrix.postRotate(270, srcWidth / 2.0f, srcHeight / 2.0f);
                    rotateWidth = canvasHeight;
                    rotateHeight = canvasWidth;
                    break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        log("srcWidth:" + srcWidth + "  srcHeight:" + srcHeight);
        int inSampleSize = 1;
        while (options.outWidth > rotateWidth || options.outHeight > rotateHeight) {
            inSampleSize++;
            options.inSampleSize = inSampleSize;
            BitmapFactory.decodeFile(path, options);
        }
        options.inJustDecodeBounds = false;
        Bitmap tempBitmap = BitmapFactory.decodeFile(path, options);
        bitmapList.add(tempBitmap);
        float tempScale = rotateWidth / (float) options.outWidth >= rotateHeight / (float) options.outHeight ? rotateHeight / (float) options.outHeight : rotateWidth / (float) options.outWidth;
        mMatrix.postScale(tempScale, tempScale);
        srcBitmap = Bitmap.createBitmap(tempBitmap, 0, 0, tempBitmap.getWidth(), tempBitmap.getHeight(), mMatrix, false);
        bitmapList.add(srcBitmap);
        srcImageRect.set(0, 0, srcBitmap.getWidth(), srcBitmap.getHeight());
        mMatrix.reset();
        mMatrix.preTranslate(canvasCenter.x - srcImageRect.centerX(), canvasCenter.y - srcImageRect.centerY());
        postInvalidate();
    }

    void getHeartPath() {
        int i, j;
        double x, y, r;
        for (i = 0; i <= 90; i++) {
            for (j = 0; j <= 90; j++) {
                r = Math.PI / 45 * i * (1 - Math.sin(Math.PI / 45 * j))
                        * 20;
                x = r * Math.cos(Math.PI / 45 * j)
                        * Math.sin(Math.PI / 45 * i) + 320 / 2;
                y = -r * Math.sin(Math.PI / 45 * j) + 400 / 4;
                heartPath.lineTo((float) x, (float) y);
            }
        }
    }

    public Path fivePointedStar(float cx, float cy, float radius, float delta) {
        final double min = 0.43701602444882104;
        final double[] startPos = {0.55, 0.65};

        Path path = new Path();

        for (int index = 0; index < 5; index++) {
            float r, o, x, y;

            r = radius;
            o = (float) (startPos[0] + index * 0.2f);

            x = (float) (r * Math.cos(o * 2 * Math.PI));
            y = (float) (r * Math.sin(o * 2 * Math.PI));

            if (path.isEmpty()) {
                path.moveTo(x + cx, y + cy);
            } else {
                path.lineTo(x + cx, y + cy);
            }

            r = (float) (radius * min * delta);
            o = (float) (startPos[1] + index * 0.2f);

            x = (float) (r * Math.cos(o * 2 * Math.PI));
            y = (float) (r * Math.sin(o * 2 * Math.PI));

            path.lineTo(x + cx, y + cy);
        }

        path.close();

        return path;
    }

    private void getStarPath() {
        starPath = fivePointedStar(circleRadius, circleRadius, circleRadius, 0.5f);
    }

    private void getTracker(MotionEvent event) {
        if (mTracker == null) {
            mTracker = VelocityTracker.obtain();
        }
        mTracker.addMovement(event);
    }

    private void releaseTracker() {
        if (mTracker != null) {
            mTracker.clear();
            mTracker.recycle();
            mTracker = null;
        }
    }

    void log(String message) {
        MyLog.e(TAG, message);
    }

    int i = 0;

    private class simpleGestureListener extends
            GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent e) {
            MyLog.e("MotionEvent", "onDown");
            if (onOptionListener != null) {
                onOptionListener.onStart();
            }
            return super.onDown(e);
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
            if (i != 0) {
                mMatrix.postTranslate(-distanceX, -distanceY);
                if (onOptionListener != null) {
                    onOptionListener.onOption();
                }
                postInvalidate();
            } else {
                i = 1;
            }
            return super.onScroll(e1, e2, distanceX, distanceY);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            return super.onFling(e1, e2, velocityX, velocityY);
        }

        @Override
        public boolean onDoubleTap(MotionEvent e) {
            isDoubleTap = true;
            final int bitmapX = Math.round(finalRectF.centerX());
            final int bitmapY = Math.round(finalRectF.centerY());
            tempX = bitmapX;
            tempY = bitmapY;
            mScroller.startScroll(bitmapX, bitmapY, canvasCenter.x - bitmapX, canvasCenter.y - bitmapY);
            if (currentScale > 0.99999f && currentScale < 1.00003) {//放大为2倍
                mScaler.startScale(1, largeScale);
                scaleCenter.set(e.getX(), e.getY());
                scaleCenterChange = false;
            } else {//调整为原始大小
                mScaler.startScale(currentScale, 1);
                scaleCenterChange = true;
            }
            postInvalidate();
            return super.onDoubleTap(e);
        }

        /**
         * @param e
         * @return
         */
        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            MyLog.e("onSingleTapConfirmed", "onSingleTapConfirmed: ");
            return super.onSingleTapConfirmed(e);
        }
    }

    private class MyScaleListener implements ScaleGestureDetector.OnScaleGestureListener {

        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            float scale = detector.getScaleFactor();
            if (onOptionListener != null) {
                onOptionListener.onOption();
            }
            mMatrix.postScale(scale, scale, detector.getFocusX(), detector.getFocusY());
            postInvalidate();
            MyLog.e("onScale", "onScale  " + detector.getFocusX() + " " + detector.getFocusY());
            return true;
        }

        @Override
        public boolean onScaleBegin(ScaleGestureDetector detector) {
            MyLog.e("onScaleBegin", "onScaleBegin");
            return !isDoubleTap;
        }

        @Override
        public void onScaleEnd(ScaleGestureDetector detector) {
            if (currentScale > maxScale) {
                mScaler.startScale(currentScale, maxScale);
                scaleCenterChange = false;
                scaleCenter.set(detector.getFocusX(), detector.getFocusY());
            } else if (currentScale < minScale) {
                mScaler.startScale(currentScale, minScale);
                scaleCenterChange = false;
                scaleCenter.set(detector.getFocusX(), detector.getFocusY());
            }
            postInvalidate();
        }
    }

    /**
     * 获取剪切后的图片路径
     *
     * @return
     */
    public String getShearResult() {
        Bitmap bitmap = Bitmap.createBitmap((int) circleRadius * 2, (int) circleRadius * 2, Bitmap.Config.ARGB_8888);
        bitmapList.add(bitmap);
        Canvas canvas = new Canvas(bitmap);
        canvas.drawColor(backgroud);
        canvas.translate(circleRadius - canvasCenter.x, -canvasCenter.y + circleRadius);
        canvas.drawBitmap(srcBitmap, mMatrix, bitmapPaint);
        Bitmap finalBitmap = bitmap;
        if (shearType != SHARE_TYPE_SQUARE) {
            finalBitmap = Bitmap.createBitmap((int) circleRadius * 2, (int) circleRadius * 2, Bitmap.Config.ARGB_8888);
            Canvas canvas1 = new Canvas(finalBitmap);
            Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            Paint circlePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
            circlePaint.setShader(shader);
            switch (shearType) {
                case SHARE_TYPE_CIRCLE:
                    canvas1.drawCircle(circleRadius, circleRadius, circleRadius, circlePaint);
                    break;
                case SHARE_TYPE_STAR:
                    canvas1.drawPath(starPath, circlePaint);
                    break;
                case SHARE_TYPE_RECT:

                    break;
                case SHARE_TYPE_HEART:
                    canvas1.drawPath(heartPath, circlePaint);
                    break;
            }
        }

        File file = new File(Environment.getExternalStorageDirectory(), "tempphoto.jpg");
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(file);
            finalBitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (out != null) {
                    out.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        bitmapList.add(finalBitmap);
        return file.getAbsolutePath();
    }

    /**
     * 监听用户对图片操作状态
     */
    public interface OnOptionListener {
        /**
         * 操作开始
         */
        void onStart();

        /**
         * 操作进行中
         */
        void onOption();

        /**
         * 操作结束
         */
        void onEnd();

        /**
         * 单击操作
         */
        void onSingleTap();

        /**
         * 连点两次
         */
        void onDoubleTap();
    }

    public void setOptionListener(OnOptionListener onOptionListener) {
        this.onOptionListener = onOptionListener;
    }

    /**
     * activity或者fragment Destroy的时候调用，回收bitmap
     */
    public void onDestroy() {
        recycleBitmap();
    }


    /**
     * 回收图片
     */
    public void recycleBitmap() {
        for (Bitmap b : bitmapList) {
            if (b != null) {
                b.recycle();
            }
        }
    }


    int tempX;
    int tempY;
    boolean scaleCenterChange = true;

    @Override
    public void computeScroll() {
        boolean needInvalidate = false;
        if (mScroller.computeScrollOffset()) {
            if (!mScroller.isFinished()) {
                MyLog.e("computeScroll mScroller", mScroller.getCurrX() + " " + mScroller.getCurrY());
                int scrollX = -tempX + mScroller.getCurrX();
                int scrollY = -tempY + mScroller.getCurrY();
                mMatrix.postTranslate(scrollX, scrollY);
                tempX = mScroller.getCurrX();
                tempY = mScroller.getCurrY();
                needInvalidate = true;
            }
        }
        if (mScaler.computeScrollOffset()) {
            if (scaleCenterChange) {
                mMatrix.mapRect(finalRectF, srcImageRect);
                scaleCenter.set(finalRectF.centerX(), finalRectF.centerY());
            }
            mMatrix.postScale(mScaler.getmDeltaScaleX(), mScaler.getmDeltaScaleY(), scaleCenter.x, scaleCenter.y);
            needInvalidate = true;
        }
        if (needInvalidate) {
            postInvalidate();
        }
        super.computeScroll();
    }

    @Override
    public WindowInsets computeSystemWindowInsets(WindowInsets in, Rect outLocalInsets) {
        return super.computeSystemWindowInsets(in, outLocalInsets);
    }


    /**
     * 设置最大缩放比
     *
     * @param maxScale
     */
    public void setMaxScale(float maxScale) {
        this.maxScale = maxScale;
    }

    /**
     * 设置最小缩放比
     *
     * @param minScale
     */
    public void setMinScale(float minScale) {
        this.minScale = minScale;
    }
}
