package com.app.alldemo.utils;

import android.content.Context;
import android.text.TextUtils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class HttpTool {
	private static final String TAG="HttpTool";
	private static HttpTool instance;
	public static HttpTool getInstance() {
		if (instance == null) {
			instance = new HttpTool();
		}
		return instance;
	}
	private DefaultHttpClient httpClient = null;
	/**
	 * 使用get请求，往指定地址传输数据,并获得结果
	 * 
	 * @param url 指定地址
	 * 
	 * @param encoding 编码方式
	 * 
	 *
	 * */
	public void getData(String url,String encoding){
		HttpGet httpGet = null;
		HttpResponse httpResponse = null;
		String response = null;
		try {
			if(httpClient == null){
				httpClient = new DefaultHttpClient();
			}
			httpGet  = new HttpGet(url);
			//头信息
//			httpGet.setHeader("User-Agent","");
			httpResponse = httpClient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				response = EntityUtils.toString(httpResponse.getEntity(),encoding);
				MyLog.v(TAG, "getData请求结果：" + response);
			}else{
				MyLog.v(TAG, "getData请求失败：" + statusCode + " " + httpResponse.getStatusLine().getReasonPhrase());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpGet = null;
			httpResponse = null;
			response = null;
		}
	}
	/**
	 * 使用get请求，往指定地址传输数据,并获得结果
	 * 
	 * @param url 指定地址
	 * 
	 * @param encoding 编码方式
	 * 
	 *
	 * */
	public void getZipData(String url,String encoding){
		HttpGet httpGet = null;
		HttpResponse httpResponse = null;
		String response = null;
		try {
			if(httpClient == null){
				httpClient = new DefaultHttpClient();
			}
			httpGet  = new HttpGet(url);
			httpResponse = httpClient .execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				GZIPInputStream gis = new GZIPInputStream(httpResponse.getEntity().getContent());  
		        int l;  
		        byte[] tmp = new byte[4096];  
		        while ((l=gis.read(tmp))!=-1){  
		        	baos.write(tmp, 0, l);  
		        }  
		        response = new String(baos.toByteArray(),HTTP.UTF_8);   
    			MyLog.v(TAG, "请求结果：" + response);
    		}
			else{
				MyLog.v(TAG, "请求失败：" + statusCode + httpResponse.getStatusLine().getReasonPhrase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpGet = null;
			httpResponse = null;
			response = null;
		}
	}
	/**
	 * 使用post请求，往指定地址传输数据, 并获得结果（数据发送前需压缩）
	 * 
	 * @param url 指定地址
	 * 
	 * @param params 数据字符串
	 * 
	 * @param encoding 编码方式
	 * 
	 *
	 * */
	public void postGzipByteEntityData(String url,String params, String encoding){
		HttpPost httpPost = null;
		HttpResponse httpResponse = null;
		String response = null;
		try {
			if(httpClient == null){
				httpClient = new DefaultHttpClient();
			}
			httpPost  = new HttpPost(url);
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			GZIPOutputStream gos = new GZIPOutputStream(baos); 
			gos.write(params.getBytes(), 0, params.getBytes().length);
			gos.close();
			ByteArrayEntity entity = new ByteArrayEntity(baos.toByteArray());
			httpPost.setEntity(entity);
			httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
    			response = EntityUtils.toString(httpResponse.getEntity(),encoding);
    			MyLog.v(TAG, "请求结果：" + response);
    		}
			else{
				MyLog.v(TAG, "请求失败：" + statusCode + " " + httpResponse.getStatusLine().getReasonPhrase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpPost = null;
			httpResponse = null;
			response = null;
		}
	}
	/**
	 * 使用post请求，往指定地址传输数据, 并获得结果
	 * 
	 * @param url 指定地址
	 * 
	 * @param params 数据键值对
	 * 
	 * @param encoding 编码方式
	 * 
	 *
	 * */
	public void postFormEntityData(String url,List<NameValuePair> params,String encoding){
		HttpPost httpPost = null;
		HttpResponse httpResponse = null;
		String response = null;
		try {
			if(httpClient == null){
				httpClient = new DefaultHttpClient();
			}
			httpPost  = new HttpPost(url);
			httpPost.setEntity(new UrlEncodedFormEntity(params, encoding));
			httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
    			response = EntityUtils.toString(httpResponse.getEntity(),encoding);
    			MyLog.v(TAG, "请求结果：" + response);
    		}
			else{
				MyLog.v(TAG, "请求失败：" + statusCode + " " + httpResponse.getStatusLine().getReasonPhrase());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpPost = null;
			httpResponse = null;
			response = null;
		}
	}
	/**
	 * {
	  	"act": "user\/login", // 根据以下Action变化
	  	"deviceInfo": {
	    "uuid": "test",
	    "Device": "iPhone 4 (GSM)",
	    "Lang": "en",
	    "OS": "Version 4.2.1 (Build 8C148)"
	  	},
	  "appInfo": {
	    "appName": "testApp",
	    "boundId": "boundId-test",
	    "version": 10
	  },
	  "params": {
	    "somekey": "somevalue" // 非必须
	  }
	}
	 * @param url
	 * @param params
	 * @param encoding
	 */
	public void postByteEntityData(String url,byte[] params,String encoding){
		HttpPost httpPost = null;
		HttpResponse httpResponse = null;
		String response = null;
		try {
			if(httpClient == null){
				HttpParams httpParameters=new BasicHttpParams();
				//建立连接超时
				HttpConnectionParams.setConnectionTimeout(httpParameters, 3000);
				//等待数据超时
				HttpConnectionParams.setSoTimeout(httpParameters, 5000);
				httpClient = new DefaultHttpClient(httpParameters);
			}
			httpPost  = new HttpPost(url);
			httpPost.setHeader("Content-Type", "application/json");
			httpPost.setHeader("X-Forwarded-For", "");
			httpPost.setHeader("Client-IP", "");
			httpPost.setHeader("User-Agent", "");
			httpPost.setHeader("Referer", "");
			httpPost.setHeader("Cookie", "");
			if(params!=null){
				ByteArrayEntity entity = new ByteArrayEntity(params);
				httpPost.setEntity(entity);
			}
			httpResponse = httpClient.execute(httpPost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == 200) {
				response = EntityUtils.toString(httpResponse.getEntity(),encoding);
			}else{
				MyLog.v(TAG, "请求失败：" + statusCode + " " + httpResponse.getStatusLine().getReasonPhrase());
			}
			MyLog.e("","响应:"+response);
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			httpPost = null;
			httpResponse = null;
			response = null;
		}
	}
	public byte[] getRequestParams(Context context,String act,HashMap<String, Object> paramsHashMap){
		try {
			JSONObject paramsJson = new JSONObject();
			JSONObject deviceJson = new JSONObject();
			JSONObject appInfoJson = new JSONObject();
			JSONObject requestParamsJson = new JSONObject();
			//deviceInfo
			if(TextUtils.isEmpty("")){
				deviceJson.accumulate("uuid", "");
			}
			deviceJson.accumulate("Device", "");
			deviceJson.accumulate("Lang", "");
			deviceJson.accumulate("OS", "");
			//appInfo
			appInfoJson.accumulate("appName", "");
			appInfoJson.accumulate("version", "");
			appInfoJson.accumulate("boundId", "");
			//params
			if(paramsHashMap!=null && paramsHashMap.size() > 0){
				for (Map.Entry<String, Object> entry : paramsHashMap.entrySet()) {
					paramsJson.accumulate(entry.getKey(), entry.getValue());
				}
			}
			requestParamsJson.accumulate("act", act);
			requestParamsJson.accumulate("deviceInfo", deviceJson);
			requestParamsJson.accumulate("appInfo", appInfoJson);
			requestParamsJson.accumulate("params", paramsJson);
			String requestParams=requestParamsJson.toString();
			return requestParams.getBytes();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * 
	 * @param urlString
	 * @param data 主体数据
	 * @param encoding
	 */
		public void postHttp(String urlString,byte[] data,String encoding){
		try {
			// 根据地址创建URL对象
			URL url=new URL(urlString);
			// 根据URL对象打开链接
			HttpURLConnection connection =(HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setReadTimeout(5000);
			connection.setConnectTimeout(5000);
			// 发送POST请求必须设置允许输出
			connection.setDoInput(true);
			// 发送POST请求必须设置允许输入,默认值true
			connection.setDoOutput(true);
			// 设置请求的头
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("Charset", encoding);
			// 设置请求的头  
			connection.setRequestProperty("Content-Length",  
                    String.valueOf(data.length));
			//获取输出流
			OutputStream os = connection.getOutputStream();
			os.write(data);
			os.flush();
			os.close();
			MyLog.v(TAG, "返回码:"+connection.getResponseCode());
			if(connection.getResponseCode()==200){
				// 获取响应的输入流对象  
                InputStream is = connection.getInputStream();
                // 创建字节输出流对象  
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int len = 0;
                // 定义缓冲区  
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {  
                    baos.write(buffer, 0, len);  
                }
                // 释放资源  
                is.close();  
                baos.close();
                String result = new String(baos.toByteArray());
				MyLog.v(TAG, "获取的数据:"+result);
			}
		} catch (Exception e) {
			MyLog.v(TAG, "异常:"+e.getMessage());
			e.printStackTrace();
		}
	}
}
