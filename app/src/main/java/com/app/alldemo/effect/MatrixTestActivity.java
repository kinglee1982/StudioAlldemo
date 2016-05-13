package com.app.alldemo.effect;

import android.app.Activity;
import android.graphics.Matrix;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.app.alldemo.courview.MatrixTestView;
import com.app.alldemo.utils.MyLog;

/**
 * Created by Administrator on 2016/1/21.
 */
public class MatrixTestActivity extends Activity {
    private MatrixTestView matrixTestView;
    private int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        matrixTestView = new MatrixTestView(this);
        matrixTestView.setScaleType(ImageView.ScaleType.MATRIX);
        matrixTestView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                symmetric2();
            }
        });
        setContentView(matrixTestView);
    }

    private void transtant() {
        Matrix matrix = new Matrix();
        matrix.setTranslate(100, 100);
        matrixTestView.setImageMatrix(matrix);
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for (int i = 0; i < 3; ++i) {
            String temp = new String();
            for (int j = 0; j < 3; ++j) {
                temp += matrixValues[3 * i + j] + "\t";
            }
            MyLog.e("", "temp:" + temp);
        }
    }

    private void transtant2() {
        Matrix matrix = new Matrix();
        float[] array = new float[]{
                1.0f, 0, 100f,
                0, 1.0f, 100f,
                0, 0, 1.0f,
        };
        matrix.setValues(array);
        matrixTestView.setImageMatrix(matrix);
    }

    // 2. 旋转(围绕图像的中心点)
    private void rote() {
        Matrix matrix = new Matrix();
        matrix.setRotate(45f, matrixTestView.getImageBitmap().getWidth(), matrixTestView.getImageBitmap().getHeight());
        matrix.postTranslate(matrixTestView.getImageBitmap().getWidth() * 1.5f, 0f);
        matrixTestView.setImageMatrix(matrix);
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for (int i = 0; i < 3; ++i) {
            String temp = new String();
            for (int j = 0; j < 3; ++j) {
                temp += matrixValues[3 * i + j] + "\t";
            }
            MyLog.e("", "temp:" + temp);
        }
    }

    private void symmetric() {
        // 8. 对称 (水平对称)
        Matrix matrix = new Matrix();
        float matrix_values[] = {1f, 0f, 0f, 0f, -1f, 0f, 0f, 0f, 1f};
        matrix.setValues(matrix_values);
        // 下面的代码是为了查看matrix中的元素
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for (int i = 0; i < 3; ++i) {
            String temp = new String();
            for (int j = 0; j < 3; ++j) {
                temp += matrixValues[3 * i + j] + "\t";
            }
            MyLog.e("TestTransformMatrixActivity", temp);
        }

        // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
        matrix.postTranslate(0f, matrixTestView.getImageBitmap().getHeight() * 2f);
        matrixTestView.setImageMatrix(matrix);

        // 下面的代码是为了查看matrix中的元素
        matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for (int i = 0; i < 3; ++i) {
            String temp = new String();
            for (int j = 0; j < 3; ++j) {
                temp += matrixValues[3 * i + j] + "\t";
            }
            MyLog.e("TestTransformMatrixActivity", temp);
        }
    }
    private void symmetric2() {
    // 9. 对称 - 垂直
        Matrix matrix = new Matrix();
        float matrix_values[] = {-1f, 0f, 0f, 0f, 1f, 0f, 0f, 0f, 1f};
        matrix.setValues(matrix_values);
        // 下面的代码是为了查看matrix中的元素
        float[] matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for (int i = 0; i < 3; ++i) {
            String temp = new String();
            for (int j = 0; j < 3; ++j) {
                temp += matrixValues[3 * i + j] + "\t";
            }
            MyLog.e("TestTransformMatrixActivity", temp);
        }

        // 做下面的平移变换，纯粹是为了让变换后的图像和原图像不重叠
        matrix.postTranslate(matrixTestView.getImageBitmap().getWidth() * 2f, 0f);
        matrixTestView.setImageMatrix(matrix);

        // 下面的代码是为了查看matrix中的元素
        matrixValues = new float[9];
        matrix.getValues(matrixValues);
        for (int i = 0; i < 3; ++i) {
            String temp = new String();
            for (int j = 0; j < 3; ++j) {
                temp += matrixValues[3 * i + j] + "\t";
            }
            MyLog.e("TestTransformMatrixActivity", temp);
        }
    }
}
