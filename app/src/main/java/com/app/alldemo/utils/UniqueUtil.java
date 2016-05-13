package com.app.alldemo.utils;

import android.content.Context;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.UUID;
/**
 * 随机字符串
 *
 */
public class UniqueUtil {
	public static UniqueUtil uniqueTest = null;
	public static final String letterChar = "abcdefghijklmnopqrstuvwxyz";
	public static final int Maxlength = 64;
	
	public static UniqueUtil getInstance(){
		synchronized(UniqueUtil.class){
			if(uniqueTest == null){
				uniqueTest = new UniqueUtil();
			}
		}
		return uniqueTest;
	}
	/**
	 * 随机字符串
	 * @return
	 */
	public String randomText(){
		return UUID.randomUUID().toString();
	}
	/**
	 * MD5生成唯一的字符串
	 * @return
	 */
	public String getresult(Context context){
		try {
			//手机的mac地址
			String str = AppInfoUtils.getInstance().getMac(context);
			MessageDigest md = MessageDigest.getInstance("MD5");
			return md5(str,md)+md5(getdata(), md);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	public String getdata(){
		long cunreentTime=System.currentTimeMillis();
		return Long.toString(cunreentTime);
	}
	/**
	 * 补齐位数
	 * @param result
	 * @return
	 */
	public String getString(String result){
		String string = "";
		if(result.length() > Maxlength){
			string = result.substring(0, Maxlength);
		}else if(result.length() < Maxlength){
			int remainder = Maxlength - result.length();
			string=result+generateMixString(remainder);
		}else {
			string=result;
		}
		return string;
	}
	/**
     * 获取固定长度的字符串
     * @param length
     * @return
     */
    public String generateMixString(int length){
    	StringBuffer sb = new StringBuffer();
    	Random random = new Random();
    	for (int i = 0; i < length; i++) {
    		sb.append(letterChar.charAt(random.nextInt(letterChar.length())));
    	}
    	return sb.toString();
    }
	
	public String md5(String strSrc,MessageDigest  md) {  
        byte[] bt = strSrc.getBytes();  
        md.update(bt);  
        String strDes = bytes2Hex(md.digest()); // to HexString
        return strDes;  
    }
	
    private String bytes2Hex(byte[] bts) {  
        StringBuffer des = new StringBuffer();  
        String tmp = null;  
        for (int i = 0; i < bts.length; i++) {  
            tmp = (Integer.toHexString(bts[i] & 0xFF));  
            if (tmp.length() == 1) {  
                des.append("0");  
            }  
            des.append(tmp);  
        }  
        return des.toString();  
    }
}
