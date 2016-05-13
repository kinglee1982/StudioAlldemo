package com.app.alldemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * 图片压缩工具类
 * @author touch_ping
 * 2015-1-5 下午1:29:59
 */
public class BitmapCompressor {
    /**
     * 设置Bitmap采样率方式压缩
     * @param srcPath
     * @return
     */
    private static final int maxNum=100;
    public static byte[] compressImageFromFile(byte[] frontBytes,String srcPath) {
        if(frontBytes.length/1024>maxNum){
            BitmapFactory.Options newOpts = new BitmapFactory.Options();
            newOpts.inJustDecodeBounds = true;//只读边,不读内容
            Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
            newOpts.inJustDecodeBounds = false;
            int w = newOpts.outWidth;
            int h = newOpts.outHeight;
            float hh = 800f;//
            float ww = 480f;//
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
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            MyLog.e("","baos->"+baos.toByteArray().length/1024);
            if(baos.toByteArray().length<frontBytes.length){
                if(baos.toByteArray().length/1024>maxNum){
                    int options = 100;
                    while (baos.toByteArray().length / 1024 > maxNum) {
                        baos.reset();
                        options -= 10;
                        bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);
                    }
                    MyLog.e("","ba22os->"+baos.toByteArray().length/1024);
                    return baos.toByteArray();
                }else{
                    return baos.toByteArray();
                }
            }else{
                byte[] front2Bytes=compressBmpToFile(BitmapFactory.decodeByteArray(frontBytes, 0, frontBytes.length));
                MyLog.e("","frontBaos->"+front2Bytes.length/1024);
                return front2Bytes;
            }
        }else{
            return frontBytes;
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
