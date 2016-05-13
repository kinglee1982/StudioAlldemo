package com.app.alldemo.courview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.widget.ImageView;

import com.app.alldemo.R;

/**
 * Created by Administrator on 2016/1/21.
 */
public class MatrixTestView extends ImageView {
    private Bitmap bitmap;
    private Matrix matrix;

    public MatrixTestView(Context context) {
        super(context);
        init(context);
    }

    public void init(Context context) {
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.wechat_icon);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // 画出原图像
        canvas.drawBitmap(bitmap, 0, 0, null);
        // 画出变换后的图像
        canvas.drawBitmap(bitmap, matrix, null);
        super.onDraw(canvas);
    }

    @Override
    public void setImageMatrix(Matrix matrix) {
        this.matrix.set(matrix);
        super.setImageMatrix(matrix);
    }

    public Bitmap getImageBitmap() {
        return bitmap;
    }
}
