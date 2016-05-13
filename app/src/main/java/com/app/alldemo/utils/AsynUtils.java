package com.app.alldemo.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.BinaryHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.apache.http.Header;

import java.io.InputStream;

/**
 * Asyn请求的用法
 */
public class AsynUtils {
    private AsyncHttpClient client;
    private void get(){
        if(client==null){
            client = new AsyncHttpClient();
        }
        String url="http://api.decomovies.com//videos/1121";
        RequestParams params = new RequestParams();
        params.put("id", "2");
        params.put("token", "30ae638e881823c577fb287160ea8d83efb08fa0");
        client.get(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
            }
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                Log.e("test", "数据" + new String(arg2));
            }
        });
    }
    private void post(){
        if(client==null){
            client = new AsyncHttpClient();
        }
        String url="http://api.decomovies.com//videos/1121";
        RequestParams params = new RequestParams();
        params.put("id", "2");
        params.put("token", "30ae638e881823c577fb287160ea8d83efb08fa0");
        client.post(url, params, new AsyncHttpResponseHandler() {
            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
            }

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                Log.e("test", "数据" + new String(arg2));
            }
        });
    }
    /**
     * 上传图片
     */
    private void setVatar(){
        if(client==null){
            client = new AsyncHttpClient();
        }
        String url="http://api.decomovies.com//videos/1121";
        RequestParams params = new RequestParams();
        params.put("id", "2");
        params.put("token", "30ae638e881823c577fb287160ea8d83efb08fa0");
        //与服务器上传的头字段,传文件，inputStream都可
//		File file=new File("");
//		params.put("profile_image",file);
        InputStream inputStream=null;
        params.put("profile_image",inputStream);
        client.post(url, params, new AsyncHttpResponseHandler(){
            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2,
                                  Throwable arg3) {
            }
            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                // TODO Auto-generated method stub
                Log.e("test","数据"+new String(arg2));
            }
        });
    }
    /**
     * 下载网络上的图片保存到本地
     */
    public static String PATH = Environment.getExternalStorageDirectory().toString() + "/decoMove/"+"imag.jpg";
    private void saveNetPictrue() {
        if(client==null){
            client = new AsyncHttpClient();
        }
        String[] allowedContentTypes = new String[] { "image/png", "image/jpeg","image/jpg","image/pdf" };
        String imagUrl="http://graph.facebook.com/1480481595550964/picture?type=large";
        client.get(imagUrl, new BinaryHttpResponseHandler(allowedContentTypes) {

            @Override
            public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
                Bitmap bmp = BitmapFactory.decodeByteArray(arg2, 0, arg2.length);
            }

            @Override
            public void onFailure(int arg0, Header[] arg1, byte[] arg2, Throwable arg3) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgress(int bytesWritten, int totalSize) {
                // TODO Auto-generated method stub
                super.onProgress(bytesWritten, totalSize);
            }

            @Override
            public void onRetry(int retryNo) {
                // TODO Auto-generated method stub
                super.onRetry(retryNo);
                // 返回重试次数
            }
        });
    }
}
