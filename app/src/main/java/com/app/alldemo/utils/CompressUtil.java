package com.app.alldemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by wangbs on 16/8/3.
 * 压缩图片
 */
public class CompressUtil {
    private static final String TAG="CompressUtil";
    private static final int maxNum = 100;//kb
    private static CompressUtil instance;
    public static CompressUtil getInstance(){
        if(instance == null){
            instance = new CompressUtil();
        }
        return instance;
    }

    /**
     *
     * @param srcPath
     * 说明:
     * Bitmap.Config ARGB_4444：每个像素占四位，即A=4，R=4，G=4，B=4，那么一个像素点占4+4+4+4=16位
     * Bitmap.Config ARGB_8888：每个像素占四位，即A=8，R=8，G=8，B=8，那么一个像素点占8+8+8+8=32位
     * Bitmap.Config RGB_565：每个像素占四位，即R=5，G=6，B=5，没有透明度，那么一个像素点占5+6+5=16位
     * Bitmap.Config ALPHA_8：每个像素占四位，只有透明度，没有颜色。
     * 一般情况下我们都是使用的ARGB_8888，由此可知它是最占内存的，因为一个像素占32位，8位=1字节，所以一个像素占4字节的内存。假设有一张480x800的图片，如果格式为ARGB_8888，那么将会占用1500KB的内存。
     * @return
     */
    public byte[] compressImageFromFile(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        newOpts.inJustDecodeBounds = true;//只读边,不读内容
        BitmapFactory.decodeFile(srcPath, newOpts);
        newOpts.inJustDecodeBounds = false;
        int newOptsWidth = newOpts.outWidth;
        int newOptsHeight = newOpts.outHeight;
        newOpts.inSampleSize = getSampleSize(newOptsWidth,newOptsHeight);//设置缩放比例
        newOpts.inPreferredConfig = Bitmap.Config.RGB_565;
        newOpts.inPurgeable = true;// 同时设置才会有效
        newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        int roteValue = roteValue(srcPath);
        if (roteValue != 0) {
            Matrix matrix = new Matrix();
            matrix.setRotate(roteValue);
            bitmap = Bitmap.createBitmap(bitmap, 0, 0,
                    bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        }
        return getBytes(bitmap);
    }
    private static int getSampleSize(int newOptsWidth,int newOptsHeight){
        double scale = Math.sqrt((newOptsWidth * newOptsHeight)/(800.0*480.0));
        int scaleValue= scale< 1 ? 1 : (int)scale;
        MyLog.e(TAG,"scaleValue:"+scaleValue);
        return scaleValue;
    }

    private int roteValue(String srcPath) {
        int roteValue = 0;
        try {
            ExifInterface exifInterface = new ExifInterface(srcPath);
            int orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            // 是否旋转图片
            if (orientation != ExifInterface.ORIENTATION_NORMAL
                    && orientation != ExifInterface.ORIENTATION_UNDEFINED) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        roteValue = 0;
                        break;

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        roteValue = 0;
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_90:
                        roteValue = 90;
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        roteValue = 180;
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        roteValue = 270;
                        break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return roteValue;
    }

    private byte[] getBytes(Bitmap bitmap) {
        int options = 100;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, options, baos);
        if (baos.toByteArray().length / 1024 > maxNum) {
            while (baos.toByteArray().length / 1024 > maxNum) {
                baos.reset();
                options -= 10;
                bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
            }
            return baos.toByteArray();
        } else {
            return baos.toByteArray();
        }
    }
}
