package com.app.alldemo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioManager;
import android.media.ExifInterface;
import android.media.SoundPool;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.ImageView;

import com.app.alldemo.R;
import com.app.alldemo.model.DataModel;
import com.app.alldemo.model.Student;
import com.app.alldemo.model.Student2;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 保留有效数字位数 return String
 * 保留有效数字位数 return double,
 * float等 float转换为double
 * 精確的加减乘除
 * 图片的缩放方法
 * 设置Drawable图片的大小
 * list集合转String数组
 * List<Float>转Float[]数组
 * List<Float>转float[]数组
 * map集合的遍历
 * 进度值互换
 * MD5字符串
 * bundle传递list
 * soundPool的用法
 * bundle传递list
 * 声音设置
 * 自然排序
 * 自定义比较规则排序
 * 矩阵显示图片
 * 加载大图片
 * 改变图片尺寸的方式
 * 大数据，超出long范围
 * 每三位添加逗号
 * 随机数
 * 从资源中获取Bitmap
 * Bitmap → byte[]
 * byte[] → Bitmap
 * 将Drawable转化为Bitmap
 * 将Bitmap转化为Drawable
 * bitmap存图片
 * Bitmap缩放
 * 判断sd卡中的图片是否被旋转
 * 读取本地媒体库
 * 获取所有图片的照片
 */
public class UtilsTool {
    private static final String TAG = "UtilsTool";
    private static UtilsTool instance;

    public static UtilsTool getInstance() {
        if (instance == null) {
            instance = new UtilsTool();
        }
        return instance;
    }

    /**
     * 保留有效数字位数
     *
     * @return
     */
    public String effectNumString(double doubleValue) {
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.00");
        String string = df.format(doubleValue);
        return string;
    }

    /**
     * 保留有效数字位数
     *
     * @param d
     * @param edigit 有效位数
     * @return
     */
    public double effectNum(double d, int edigit) {
        BigDecimal b = new BigDecimal(d);
        double doubleValue = b.setScale(edigit, BigDecimal.ROUND_HALF_UP)
                .doubleValue();
        return doubleValue;
    }

    /**
     * float转换为double
     */
    public double fDoble(float f) {
        BigDecimal b = new BigDecimal(String.valueOf(f));
        return b.doubleValue();
    }

    /**
     * 精確的加法運算
     *
     * @param v1
     * @param v2
     * @return
     */
    public static double add(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).doubleValue();
    }

    /**
     * 提供了精確的減法運算
     */
    public static double sub(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.subtract(b2).doubleValue();
    }

    /**
     * 提供了精確的乘法運算
     */
    public static double mul(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).doubleValue();
    }

    /**
     * 提供了(相對)精確的除法運算，當發生除不儘的情況時，精確到 小數點以後１10位
     *
     * @param edigit 有效位数
     */
    public static double div(double v1, double v2, int edigit) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, edigit, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /***
     * 图片的缩放方法
     *
     * @param bgimage   ：源图片资源
     * @param newWidth  ：缩放后宽度
     * @param newHeight ：缩放后高度
     * @return
     */
    public Bitmap zoomImage(Bitmap bgimage, double newWidth, double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    /**
     * 设置Drawable图片的大小
     *
     * @param drawable
     */
    public Drawable setDrawableSize(Drawable drawable) {
        drawable.setBounds(0, 0, drawable.getIntrinsicWidth() / 2,
                drawable.getIntrinsicHeight() / 2);
        return drawable;
    }

    /**
     * list集合转String数组
     */
    public void toArray() {
        List<String> list = new ArrayList<String>();
        String[] array2 = (String[]) list.toArray(new String[list.size()]);
    }

    /**
     * List<Float>转Float[]数组
     */
    public void toFloatArray() {
        List<Float> floats = new ArrayList<Float>();
        floats.add(35f);
        floats.add(25f);
        floats.add(65f);
        floats.add(67f);
        Float[] f = new Float[floats.size()];
        for (int i = 0; i < floats.size(); i++) {
            f[i] = floats.get(i);
        }
    }

    /**
     * List<Float>转float[]数组
     */
    public float[] tofloatArray(List<Float> floats) {
        float[] floatArray = new float[floats.size()];
        for (int j = 0; j < floats.size(); j++) {
            floatArray[j] = floats.get(j);
        }
        return floatArray;
    }

    /**
     * map集合的遍历
     */
    public void getMaps() {
        Map<Float, Float> hashMap = new HashMap<Float, Float>();
        hashMap.put(23f, 32f);
        hashMap.put(43f, 52f);
        for (Map.Entry<Float, Float> entry : hashMap.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
    }

    /**
     * 进度值互换
     */
    private void schedule() {
        // 十进制转成十六进制
        String string16 = Integer.toHexString(127);
        // 十进制转成八进制
        Integer.toOctalString(125);
        // 十进制转成二进制
        Integer.toBinaryString(20);
        // 十六进制转成十进制
        String string10 = Integer.valueOf("7f", 16).toString();
        // 八进制转成十进制
        // Integer.valueOf("876",8).toString();
        // 二进制转十进制
        Integer.valueOf("0101", 2).toString();
        System.out.println(string10);
        // 直接将2,8,16进制直接转换为10进制的
        Integer.parseInt("1100110", 2);
        Integer.parseInt("-FF", 16);
    }

    /**
     * MD5字符串
     *
     * @param strObj
     */
    public void md5(String strObj) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(strObj.getBytes());
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * bundle传递list
     */
    public void bundleList() {
        List<DataModel> modelTests = new ArrayList<DataModel>();
        Bundle bundle = new Bundle();
        ArrayList list = new ArrayList();
        list.add(modelTests);
        bundle.putParcelableArrayList("data", list);

        Intent intent = new Intent();
        Bundle getbundle = intent.getBundleExtra("bundle");
        ArrayList getList = getbundle.getParcelableArrayList("data");
        if (getList != null) {
            List<DataModel> getModels = (List<DataModel>) getList.get(0);
        }
    }

    /**
     * 读取本地媒体库
     */
    private void getMediaPaths(Context context) {
        Cursor cursor = context.getContentResolver().query(
                MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, null, null, null,
                MediaStore.Audio.Media.DEFAULT_SORT_ORDER);
        if (cursor.moveToFirst()) {
            while (cursor.moveToNext()) {
                // 歌曲编号
                int id = cursor.getInt(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media._ID));
                // 歌曲标题
                String tilte = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.TITLE));
                // 歌曲的专辑名：MediaStore.Audio.Media.ALBUM
                String album = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ALBUM));
                // 歌曲的歌手名： MediaStore.Audio.Media.ARTIST
                String artist = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.ARTIST));
                // 歌曲文件的路径 ：MediaStore.Audio.Media.DATA
                String url = cursor.getString(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DATA));
                // 歌曲的总播放时长 ：MediaStore.Audio.Media.DURATION
                int duration = cursor.getInt(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.DURATION));
                // 歌曲文件的大小 ：MediaStore.Audio.Media.SIZE
                Long size = cursor.getLong(cursor
                        .getColumnIndexOrThrow(MediaStore.Audio.Media.SIZE));
            }
        }
    }
    /**
     * 获取所有图片的照片
     *
     * @param context
     * @return
     */
    public ArrayList<String> getAllImages(Context context) {
        ArrayList<String> dataList = new ArrayList<String>();
        Cursor cursor = null;
        cursor = context.getContentResolver().
                query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                        new String[]{
                                MediaStore.Images.Media._ID,      //id
                                MediaStore.Images.Media.DATA,     //路径
                                MediaStore.Images.Media.DISPLAY_NAME,//文件名
                        },
                        null, null, null);
        if (cursor == null) {
            return dataList;
        }
        if (cursor.getCount() == 0) {
            cursor.close();
            return dataList;
        }
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
            dataList.add(path);
            cursor.moveToNext();
        }
        Collections.reverse(dataList);
        return dataList;
    }

    /**
     * soundPool的用法
     */
    private void soundpoolTest(Context context) {
        SoundPool soundPool = new SoundPool(10, AudioManager.STREAM_SYSTEM, 5);
        int loadresult = soundPool.load(context, R.raw.soundpool, 1);
        if (loadresult == 0) {
            Log.e("test", "加载失败");
        } else {
            Log.e("test", "加载成功");
        }
        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                int result = soundPool.play(1, 1, 1, 0, -1, 1f);
                if (result == 0) {
                    Log.e("test", "失败");
                } else {
                    Log.e("test", "成功");
                }
            }
        });
    }

    /**
     * 声音设置
     */
    private void volumUtils(Context context) {
        AudioManager mAudioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
        int volumeValue = 50;
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeValue, 0);
        int maxVolume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int currentVolume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        //监听音量键KeyEvent.KEYCODE_VOLUME_DOWN;KeyEvent.KEYCODE_VOLUME_UP
        //设置静音
        mAudioManager.setStreamMute(AudioManager.STREAM_MUSIC, true);
        /**
         * 打开声音
         * 第一个参数：第一个streamType是需要调整音量的类型,这里设的是媒体音量, 可以是:STREAM_ALARM
         * 警报；STREAM_MUSIC 音乐回放即媒体音量；STREAM_NOTIFICATION
         * 窗口顶部状态栏Notification,；STREAM_RING 铃声；STREAM_SYSTEM
         * 系统；STREAM_VOICE_CALL 通话；STREAM_DTMF 双音多频
         * 第三个flags是一些附加参数；FLAG_PLAY_SOUND 调整音量时播放声音；FLAG_SHOW_UI 调整时显示音量条；
         */
        mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, volumeValue,
                AudioManager.FLAG_PLAY_SOUND);
    }

    /**
     * 自然排序
     */
    private void naturalOrder() {
        ArrayList<Student> al = new ArrayList<Student>();
        al.add(new Student(8, "aora"));
        al.add(new Student(48, "longyu"));
        al.add(new Student(5, "goso"));
        Collections.sort(al);
        for (Student student : al) {
            System.out.println(student.getName() + student.getNum());
        }
    }

    /**
     * 自定义比较规则排序
     */
    private void order() {
        ArrayList<Student2> al = new ArrayList<Student2>();
        al.add(new Student2(2, "qingan"));
        al.add(new Student2(1, "longyu"));
        al.add(new Student2(3, "goso"));
        al.add(new Student2(2, "aora"));
        Collections.sort(al, new Student2.studentComparator());
        for (Student2 student : al) {
            System.out.println(student.getName() + student.getNum());
        }
    }

    /**
     * 矩阵显示图片
     */
    private void bakgroudImag() {
        ImageView sleep_fragment_bak = null;
        float screen_width = 12f;
        sleep_fragment_bak.setImageResource(R.drawable.bg1);
        Matrix matrix = sleep_fragment_bak.getImageMatrix();
        float[] f = new float[9];
        matrix.getValues(f);
        float width = screen_width;
        float imagWidth = sleep_fragment_bak.getDrawable().getIntrinsicWidth();
        float scale = imagWidth > width ? imagWidth / width : width / imagWidth;
        f[Matrix.MSCALE_X] *= scale;
        f[Matrix.MSCALE_Y] *= scale;
        matrix.setValues(f);
        sleep_fragment_bak.setImageMatrix(matrix);
    }

    /**
     * 加载大图片
     *
     * @param context
     */
    public void loadBigImage(Context context) {
        try {
            InputStream inputStream = context.getAssets().open("");
            BitmapRegionDecoder regionDecoder = BitmapRegionDecoder.newInstance(inputStream, false);
            BitmapFactory.Options opts = new BitmapFactory.Options();
            opts.inPreferredConfig = Bitmap.Config.RGB_565;
            opts.inJustDecodeBounds = false;
            Bitmap bitmap = regionDecoder.decodeRegion(new Rect(0, 0, regionDecoder.getWidth(), regionDecoder.getHeight()), opts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Bitmap readBitMap(Context context, int resId) {
        BitmapFactory.Options opt = new BitmapFactory.Options();
        opt.inPreferredConfig = Bitmap.Config.RGB_565;
        // 获取资源图片
        InputStream is = context.getResources().openRawResource(resId);
        return BitmapFactory.decodeStream(is, null, opt);
    }

    /**
     * 改变图片尺寸的方式
     *
     * @return
     */
    public Bitmap setBitmap() {
        String fileName = "/sdcard/DCIM/Camera/2010-05-14 16.01.44.jpg";
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 2;// 图片宽高都为原来的二分之一，即图片为原来的四分之一
        Bitmap b = BitmapFactory.decodeFile(fileName, options);
        return b;
    }

    /**
     * 大数据，超出long范围
     */
    public long lagLong() {
//		long i=1448008677343;
        BigDecimal bd = new BigDecimal("1448008677343");
        return bd.longValue();
    }

    /**
     * 每三位添加逗号
     *
     * @param value
     * @return
     */
    public String longFormat(long value) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(value);
    }

    /**
     * 随机数
     */
    public void randomTest() {
        Random rn = new Random();
        int i = rn.nextInt(10);
    }

    /**
     * 从资源中获取Bitmap
     *
     * @param context
     */
    public void test1(Context context) {
        Resources res = context.getResources();
        Bitmap bmp = BitmapFactory.decodeResource(res, R.drawable.waiting);
    }

    /**
     * Bitmap → byte[]
     *
     * @param bm
     * @return
     */
    public byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * byte[] → Bitmap
     *
     * @param b
     * @return
     */
    public Bitmap Bytes2Bimap(byte[] b) {
        if (b.length != 0) {
            return BitmapFactory.decodeByteArray(b, 0, b.length);
        } else {
            return null;
        }
    }

    /**
     * 将Drawable转化为Bitmap
     *
     * @param drawable
     * @return
     */
    public static Bitmap drawableToBitmap(Drawable drawable) {
        // 取 drawable 的长宽
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();

        // 取 drawable 的颜色格式
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888
                : Bitmap.Config.RGB_565;
        // 建立对应 bitmap
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        // 建立对应 bitmap 的画布
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        // 把 drawable 内容画到画布中
        drawable.draw(canvas);
        return bitmap;
    }

    /**
     * 将Bitmap转化为Drawable
     *
     * @return
     */
    public void getDrable(Context context) {
        Bitmap bm = null;
        BitmapDrawable bd = new BitmapDrawable(context.getResources(), bm);
    }

    /**
     * bitmap存图片
     * @param bitmap
     */
    public void getBitMapFile(Bitmap bitmap){
        try {
            String fileName="/mnt/sdcard/tamen2.jpg";
            FileOutputStream out = new FileOutputStream(fileName);
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, out);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Bitmap缩放
     *
     * @param bitmap
     * @param width
     * @param height
     * @return
     */
    public static Bitmap zoomBitmap(Bitmap bitmap, int width, int height) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) width / w);
        float scaleHeight = ((float) height / h);
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap newbmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        return newbmp;
    }

    /**
     * 判断sd卡中的图片是否被旋转
     * sd卡的图片，竖拍显示横拍的效果
     * @param path
     */
    public void sdImage(String path){
        try {
            ExifInterface exifInterface = new ExifInterface(path);
            int tag = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, -1);
            //旋转了90度
            if (tag == ExifInterface.ORIENTATION_ROTATE_90) {
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
