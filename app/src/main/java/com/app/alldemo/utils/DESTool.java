package com.app.alldemo.utils;

import android.util.Base64;

import org.apache.http.protocol.HTTP;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Random;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

/**
 * @author Administrator
 *
 */
public class DESTool {
	private static final String TAG = "DESTool";
	private static DESTool tool;

	private DESTool() {
	};

	public static DESTool getInstance() {
		if (tool == null) {
			tool = new DESTool();
		}
		return tool;
	}
	//模式有:DES/CBC/PKCS5Padding,DES/CFB/PKCS5Padding,DES/ECB/PKCS5Padding,DES/OFB/PKCS5Padding,
	//DES/CFB/NoPadding,DES/CBC/NoPadding,DES/ECB/NoPadding,DES/OFB/NoPadding
	//常用加密算法:DES、3DES、RC4、AES等;
	//加密模式:ECB、CBC、CFB、OFB等;
	//填充模式:NoPadding、PKCS1Padding、PKCS5Padding、PKCS7Padding
	//DES为加密算法，ECB为加密模式，NoPadding为填充模式.
	private static final String MODEL = "DES/CBC/PKCS5Padding";
	private static final String DES_KEY = "rF90.2gh";

	/**
	 * 加密
	 * 第一步：gzdeflate 9级别 压缩
     * 第二步：MCRYPT_DES, MCRYPT_MODE_CBC
     * 第三步：base64_encode
     * 第四步：rawurlencode 通信用
     * http://www.cnblogs.com/pengxl/p/3967040.html
	 */
	public String encryptUtil(String datasString){
		try {
			datasString=URLEncoder.encode(datasString, HTTP.UTF_8);
			byte[] deflaterByte = deflater(datasString.getBytes(HTTP.UTF_8));
			byte[] encryptByte = encrypt(deflaterByte);
			byte[] baseBytes=Base64.encode(encryptByte, Base64.DEFAULT);
			String string=new String(baseBytes);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public String encryptUtil2(String datasString){
		try {
			datasString= getEncode(datasString);
			byte[] encryptByte = encrypt(datasString.getBytes());
			byte[] deflaterByte = deflater(encryptByte);
			byte[] baseBytes=Base64.encode(deflaterByte, Base64.DEFAULT);
			String string=new String(baseBytes);
			string= getEncode(string);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public String getEncode(String string) {
		try {
			String s = URLEncoder.encode(string, HTTP.UTF_8);
			s = s.replaceAll("\\+", "%20");
			return s;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return "";
		}
	}
	public byte[] encrypt(byte[] datasByte) {
		try {
			byte[] bytes=generateMixString(datasByte);
			Cipher cipher = Cipher.getInstance(MODEL);
			DESKeySpec desKeySpec = new DESKeySpec(DES_KEY.getBytes(HTTP.UTF_8));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			IvParameterSpec iv = new IvParameterSpec(DES_KEY.getBytes(HTTP.UTF_8));
			cipher.init(Cipher.ENCRYPT_MODE, secretKey, iv);
			byte[] datas = cipher.doFinal(bytes);
			return datas;
		} catch (Exception e) {
			e.printStackTrace();
			MyLog.v(TAG, "DES加密失败:" + e.getMessage());
			return null;
		}
	}
	/**
	 * gzdeflate 9级别 压缩
	 * 
	 * @param bytes
	 * @return
	 */
	public byte[] deflater(byte[] bytes) {
		ByteArrayOutputStream out;
		try {
			out = new ByteArrayOutputStream();
			DeflaterOutputStream deflater = new DeflaterOutputStream(out,
					new Deflater(1, true));
			deflater.write(bytes);
			deflater.close();
			out.close();
			return out.toByteArray();
		} catch (IOException e) {
			MyLog.v(TAG, "压缩失败：" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	/**解密
	 * 第一步：base64_decode
     * 第二步：gzinflate 9级别 解压
     * 第三步：MCRYPT_DES, MCRYPT_MODE_CBC 
	 */
	public String decryptUtil(String datasString){
		try {
			datasString=URLDecoder.decode(datasString, HTTP.UTF_8);
			byte[] bytesrc = Base64.decode(datasString.getBytes(), Base64.DEFAULT);
			byte[] inflaterByte=inflater(bytesrc);
			byte[] decryptByte=decrypt(inflaterByte);
			String string = new String(decryptByte,HTTP.UTF_8);
			string=URLDecoder.decode(string, HTTP.UTF_8);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public String decryptUtil2(String datasString){
		try {
			byte[] bytesrc = Base64.decode(datasString.getBytes(), Base64.DEFAULT);
			byte[] decryptByte=decrypt(bytesrc);
			byte[] inflaterByte=inflater(decryptByte);
			String string = new String(inflaterByte,HTTP.UTF_8);
			string=URLDecoder.decode(string, HTTP.UTF_8);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public byte[] decrypt(byte[] bytesrc) {
		try {
			byte[] retByte = null;
			String ivKey = new String(bytesrc, HTTP.UTF_8);
			ivKey = ivKey.substring(0, 8);
			byte[] key = new byte[8];
			for (int i = 0; i < key.length; i++) {
				key[i] = bytesrc[i];
			}
			byte[] data = new byte[bytesrc.length - key.length];
			for (int i = 8; i < bytesrc.length; i++) {
				data[i - 8] = bytesrc[i];
			}
			Cipher cipher = Cipher.getInstance(MODEL);
			DESKeySpec desKeySpec = new DESKeySpec(DES_KEY.getBytes(HTTP.UTF_8));
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
			SecretKey secretKey = keyFactory.generateSecret(desKeySpec);
			// 前8位作为解密key
			IvParameterSpec iv = new IvParameterSpec(key);
			cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
			retByte = cipher.doFinal(data);
			return retByte;
		} catch (Exception e1) {
			e1.printStackTrace();
			return null;
		}
	}
	/**
	 * gzinflate解压
	 * 
	 * @param data
	 * @return
	 */
	public byte[] inflater(byte[] data) {
		ByteArrayOutputStream out;
		try {
			ByteArrayInputStream bins = new ByteArrayInputStream(data);
			InflaterInputStream inflater = new InflaterInputStream(bins,
					new Inflater(true));
			byte[] buf = new byte[1024];
			int len = 0;
			out = new ByteArrayOutputStream();
			while ((len = inflater.read(buf)) != -1) {
				out.write(buf, 0, len);
			}
			out.close();
			inflater.close();
			bins.close();
			return out.toByteArray();
		} catch (IOException e) {
			MyLog.v(TAG, "解压异常:" + e.getMessage());
			e.printStackTrace();
			return null;
		}
	}
	/**
     * 获取固定长度的字符串
     * @param length
     * @return
     */
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyz";
    public String generateMixString(int length){
    	StringBuffer sb = new StringBuffer();
    	Random random = new Random();
    	for (int i = 0; i < length; i++) {
    		sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
    	}
    	return sb.toString();
    }
    public byte[] generateMixString(byte[] bytes){
    	byte[] keyBytes=generateMixString(8).getBytes();
    	byte[] datas=new byte[keyBytes.length+bytes.length];
    	for (int i = 0; i < 8; i++) {
    		datas[0] = keyBytes[i];
		}
    	for (int i = 8; i < datas.length; i++) {
    		datas[i] = bytes[i-8];
		}
    	return datas;
    }
    public String inflaterTest(String datasString){
		try {
			byte[] bytesrc = Base64.decode(datasString, Base64.DEFAULT);
			byte[] inflaterByte=inflater(bytesrc);
			String string = new String(inflaterByte,HTTP.UTF_8);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public String decryptTest(String decryptString){
		try {
			byte[] bytesrc = Base64.decode(decryptString, Base64.DEFAULT);
			byte[] decryptByte=decrypt(bytesrc);
			String string = new String(decryptByte,HTTP.UTF_8);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
    public String deflaterTest(String datasString){
		try {
			byte[] deflaterByte = deflater(datasString.getBytes(HTTP.UTF_8));
			byte[] baseBytes=Base64.encode(deflaterByte, Base64.DEFAULT);
			String string=new String(baseBytes);
			return string;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}
	public String encryptTest(String datasString){
		byte[] encryptByte = encrypt(datasString.getBytes());
		byte[] baseBytes=Base64.encode(encryptByte, Base64.DEFAULT);
		String string=new String(baseBytes);
		return string;
	}
}
