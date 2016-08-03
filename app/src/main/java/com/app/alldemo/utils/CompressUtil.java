package com.app.alldemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

/**
 * Created by wangbs on 16/8/3.
 * 压缩
 */
public class CompressUtil {
    private static final int maxNum=100;
    public static byte[] compressImageFromFile(byte[] frontBytes,String srcPath) {
        if(frontBytes.length/1024>maxNum){
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = true;//只读边,不读内容
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            float hh = 800f;
            float ww = 480f;
            int be = 1;
            if (w > h && w > ww) {
                be = (int) (newOpts.outWidth / ww);
            } else if (w < h && h > hh) {
                be = (int) (newOpts.outHeight / hh);
            }
            if (be <= 0)
                be = 1;
            newOpts.inSampleSize = be;//设置采样率
            newOpts.inPreferredConfig = Bitmap.Config.ARGB_8888;//该模式是默认的,可不设
            newOpts.inPurgeable = true;// 同时设置才会有效
            newOpts.inInputShareable = true;//。当系统内存不够时候图片自动被回收
            bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
            int roteValue=roteValue(srcPath);
            if(roteValue!=0){
                Matrix matrix = new Matrix();
                matrix.setRotate(roteValue);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0,
                        bitmap.getWidth(), bitmap.getHeight(), matrix, true);
            }
            return getBytes(bitmap,frontBytes);
        }else{
            return frontBytes;
        }
    }
    private static int roteValue(String srcPath){
        int roteValue=0;
        int orientation = ExifInterface.ORIENTATION_UNDEFINED;
        try {
            ExifInterface exifInterface = new ExifInterface(srcPath);
            orientation = exifInterface.getAttributeInt(
                    ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
            // 是否旋转图片
            if(orientation != ExifInterface.ORIENTATION_NORMAL
                    && orientation != ExifInterface.ORIENTATION_UNDEFINED) {
                switch (orientation) {
                    case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                        roteValue=0;
                        break;

                    case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                        roteValue=0;
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_90:
                        roteValue=90;
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_180:
                        roteValue=180;
                        break;

                    case ExifInterface.ORIENTATION_ROTATE_270:
                        roteValue=270;
                        break;
                }
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
        return roteValue;
    }
    private static byte[] getBytes(Bitmap bitmap,byte[] frontBytes){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
        if(baos.toByteArray().length<frontBytes.length){
            if(baos.toByteArray().length/1024>maxNum){
                int options = 100;
                while (baos.toByteArray().length / 1024 > maxNum) {
                    baos.reset();
                    options -= 10;
                    bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                }
                MyLog.e("compressImageFromFile","11111压缩后的大小:"+baos.toByteArray().length+"压缩前:"+frontBytes.length);
                return baos.toByteArray();
            }else{
                MyLog.e("compressImageFromFile","2222压缩后的大小:"+baos.toByteArray().length+"压缩前:"+frontBytes.length);
                return baos.toByteArray();
            }
        }else{
            byte[] front2Bytes=compressBmpToFile(BitmapFactory.decodeByteArray(frontBytes, 0, frontBytes.length));
            MyLog.e("compressImageFromFile","33333压缩后的大小:"+front2Bytes.length+"压缩前:"+frontBytes.length);
            return front2Bytes;
        }
    }
    public static byte[] compressBmpToFile(Bitmap image){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while ( baos.toByteArray().length / 1024>maxNum) { //循环判断如果压缩后图片是否大于100kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 10;//每次都减少10
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        return baos.toByteArray();
    }
}
