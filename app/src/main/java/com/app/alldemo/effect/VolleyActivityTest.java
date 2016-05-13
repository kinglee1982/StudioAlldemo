package com.app.alldemo.effect;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.app.alldemo.R;
import com.app.alldemo.adapter.other.BitmapCache;
import com.app.alldemo.adapter.other.VolleyAdapter;
import com.app.alldemo.effect.volley.XMLRequest;
import com.app.alldemo.model.Info;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/1/12.
 */
public class VolleyActivityTest extends Activity {
    private ImageView image;
    private NetworkImageView net_image;
    private Button get,post,json,xml;
    private RequestQueue mQueue;
    private ListView mListView;
    private ArrayList<Info> infos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.volley_test);
        init();
        image=(ImageView)findViewById(R.id.image);
        net_image=(NetworkImageView)findViewById(R.id.net_image);
        get=(Button)findViewById(R.id.get);
        post=(Button)findViewById(R.id.post);
        json=(Button)findViewById(R.id.json);
        xml=(Button)findViewById(R.id.xml);
        get.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                get();
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                post();
            }
        });
        json.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonReque();
            }
        });
        xml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                xml();
            }
        });
        mListView  = (ListView) findViewById(R.id.listView);
        imageUrl();
        netImageUrl();
        list();
    }
    private void init(){
        mQueue = Volley.newRequestQueue(this);
    }
    private void imageUrl(){
        //图片的宽高最大值，0:不管图片有多大，都不会进行压缩；
        //ARGB_8888可以展示最好的颜色属性，每个图片像素占据4个字节的大小，而RGB_565则表示每个图片像素占据2个字节大小
        ImageRequest imageRequest = new ImageRequest(
                "http://imgstatic.baidu.com/img/image/shouye/fanbingbing.jpg",
                new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        image.setImageBitmap(response);
                    }
                }, 0, 0, Bitmap.Config.RGB_565, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                image.setImageResource(R.drawable.ic_launcher);
            }
        });
        mQueue.add(imageRequest);
    }
    private void netImageUrl(){
        net_image.setDefaultImageResId(R.drawable.image_thumb);
        net_image.setErrorImageResId(R.drawable.image_thumb);
        ImageLoader mImageLoader=  new ImageLoader(mQueue, new BitmapCache());
        net_image.setImageUrl("http://imgstatic.baidu.com/img/image/shouye/fanbingbing.jpg",
                mImageLoader);
    }
    private void list(){
        initData();
        VolleyAdapter adapter = new VolleyAdapter(this,infos);
        mListView.setAdapter(adapter);
    }
    private void initData() {
        infos = new ArrayList<Info>();
        Info info1 = new Info("http://imgstatic.baidu.com/img/image/shouye/fanbingbing.jpg");
        Info info2 = new Info("http://imgstatic.baidu.com/img/image/shouye/liuyifei.jpg");
        Info info3 = new Info("http://imgstatic.baidu.com/img/image/shouye/wanglihong.jpg");
        Info info4 = new Info("http://imgstatic.baidu.com/img/image/shouye/gaoyuanyuan.jpg");
        Info info5 = new Info("http://imgstatic.baidu.com/img/image/shouye/yaodi.jpg");
        Info info6 = new Info("http://imgstatic.baidu.com/img/image/shouye/zhonghanliang.jpg");
        Info info7 = new Info("http://imgstatic.baidu.com/img/image/shouye/xiezhen.jpg");
        Info info8 = new Info("http://imgstatic.baidu.com/img/image/shouye/yiping3.jpg");
        Info info9 = new Info("http://imgstatic.baidu.com/img/image/shouye/erping4.jpg");
        Info info10 = new Info("http://imgstatic.baidu.com/img/image/shouye/hangeng.jpg");
        Info info11 = new Info("http://imgstatic.baidu.com/img/image/shouye/liuyan1.jpg");
        Info info12 = new Info("http://imgstatic.baidu.com/img/image/shouye/liushishi1.jpg");
        Info info13 = new Info("http://imgstatic.baidu.com/img/image/shouye/sunli1.jpg");
        Info info14 = new Info("http://imgstatic.baidu.com/img/image/shouye/tangyan1.jpg");
        Info info15 = new Info("http://imgstatic.baidu.com/img/image/shouye/zhanggenshuo1.jpg");
        Info info16 = new Info("http://imgstatic.baidu.com/img/image/shouye/xiaohua0605.jpg");
        infos.add(info1);
        infos.add(info2);
        infos.add(info3);
        infos.add(info4);
        infos.add(info5);
        infos.add(info6);
        infos.add(info7);
        infos.add(info8);
        infos.add(info9);
        infos.add(info10);
        infos.add(info11);
        infos.add(info12);
        infos.add(info13);
        infos.add(info14);
        infos.add(info15);
        infos.add(info16);
    }
    private void get(){
        String url="http://weight.nichan-matome.info/small-app-api/index.php/api2/get/share-email?app_id=com.app.weighthealthdiet&app_name=レコダイエット&app_lang=ja";
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TAG", "get:"+response);
                    }
                }
                , new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("TAG", error.getMessage(), error);
                    }
                }
        );
        mQueue.add(stringRequest);
    }
    private void post(){
        String url="http://weight.nichan-matome.info/small-app-api/index.php/api2/get/share-email";
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.e("TAG", "post:"+response);
                    }
                }
                ,new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Log.e("TAG", error.getMessage(), error);
                }
            }
        )
        {
            //复写getParams传递参数，也可自己定义
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("app_id", "value1");
                map.put("name", "レコダイエット");
                map.put("app_lang", "ja");
                return map;
            }

        };
        mQueue.add(stringRequest);
    }
    private void jsonReque(){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("http://m.weather.com.cn/data/101010100.html", null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.e("TAG", "中国天气网:" + response.toString());
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(jsonObjectRequest);
    }
    private void xml(){
        XMLRequest xmlRequest = new XMLRequest(
                "http://flash.weather.com.cn/wmaps/xml/china.xml",
                new Response.Listener<XmlPullParser>() {
                    @Override
                    public void onResponse(XmlPullParser response) {
                        try {
                            int eventType = response.getEventType();
                            while (eventType != XmlPullParser.END_DOCUMENT) {
                                switch (eventType) {
                                    case XmlPullParser.START_TAG:
                                        String nodeName = response.getName();
                                        if ("city".equals(nodeName)) {
                                            String pName = response.getAttributeValue(0);
                                            Log.e("TAG", "pName is " + pName);
                                        }
                                        break;
                                }
                                eventType = response.next();
                            }
                        } catch (XmlPullParserException e) {
                            e.printStackTrace();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("TAG", error.getMessage(), error);
            }
        });
        mQueue.add(xmlRequest);
    }
}
