package com.app.alldemo;

import android.app.Application;
import android.content.Context;

import com.app.alldemo.utils.CrashHandler;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.utils.StorageUtils;
import com.uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by Administrator on 2015/10/30.
 */
public class AlldemoApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        initImageLoder(getApplicationContext());
        initFont();
        initCrash();
    }
    //捕获崩溃
    private void initCrash(){
        CrashHandler.getInstance().init(getApplicationContext());
    }
    private void initFont() {
        CalligraphyConfig.initDefault("fonts/FOT-RodinProN-M.ttf",
                R.attr.fontPath);
    }
    /**
     * 初始化ImageLoader
     * @param context
     */
    private Map<String, String> name = new HashMap<String, String>();
    private void initImageLoder(Context context) {
        ImageLoaderConfiguration.Builder builder = new ImageLoaderConfiguration.Builder(
                context);
        builder.diskCache(new UnlimitedDiscCache(StorageUtils.getCacheDirectory(context), null, new FileNameGenerator() {
            @Override
            public String generate(String s) {
                if (!name.containsKey(s)) {
                    name.put(s, UUID.randomUUID().toString());
                }
                return name.get(s);
            }
        }));
        builder.threadPriority(Thread.NORM_PRIORITY - 2);
        builder.tasksProcessingOrder(QueueProcessingType.LIFO);
        builder.discCacheFileNameGenerator(new Md5FileNameGenerator());
        builder.denyCacheImageMultipleSizesInMemory();

        DisplayImageOptions.Builder dopts = new DisplayImageOptions.Builder();
        dopts.showImageForEmptyUri(R.drawable.ic_launcher); // 设置图片Uri为空或是错误的时候显示的图片
        dopts.showImageOnFail(R.drawable.ic_launcher);
        dopts.showImageOnLoading(R.drawable.ic_launcher);// 设置图片下载期间显示的图片
        dopts.cacheInMemory(true); // 设置下载的图片是否缓存在内存中
        dopts.cacheOnDisk(true); // 设置下载的图片是否缓存在SD卡中
        builder.defaultDisplayImageOptions(dopts.build());
        ImageLoader.getInstance().init(builder.build());
        com.nostra13.universalimageloader.utils.L.writeLogs(false);//去掉日志打印
//      ImageLoader.getInstance().clearMemoryCache();
//		ImageLoader.getInstance().clearDiskCache();
    }
}
