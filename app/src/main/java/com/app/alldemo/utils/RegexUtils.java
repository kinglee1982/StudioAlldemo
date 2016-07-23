package com.app.alldemo.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 获取正则
 * 判断邮箱
 * 是否匹配
 * 0-9的数字
 */
public class RegexUtils {
    private static RegexUtils instance;
    public static RegexUtils getInstance() {
        if (instance == null) {
            instance = new RegexUtils();
        }
        return instance;
    }
    //邮箱的正则
    private String strPattern = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    // 最多三位整数和两位有效小数
    private String regEx = "^[1-9]{1,1}\\d{0,2}\\.\\d{1,2}|[1-9]{1,1}\\d{0,2}\\.?$";
    // 最多两位整数和两位有效小数
    private String regEx2 = "^[1-9]{1,1}\\d{0,1}\\.\\d{1,2}|[1-9]{1,1}\\d{0,1}\\.?$";
    // 最多两位整数和一位有效小数
    public final static String regEx6 = "^[1-9]{1,1}\\d{0,2}\\.\\d{1,1}|[1-9]{1,1}\\d{0,2}\\.?$";
    // 最多三位整数
    private String regEx3 = "^[1-9]{1,1}\\d{0,2}";
    // 0-9的数字
    private String regEx4 = "\\d";
    // 纯数字
    private String regEx5 = "[0-9]*";
    //网址
    public static final String check2 = "((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.){4})(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";//http://www.baidu.com
    public static final String check = "([a-zA-Z0-9\\._-]+\\.)(([a-zA-Z]{2,6})|([0-9]{1,3}\\.){4})(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?";//www.baidu.com

    public static final String test4 = "(((http|ftp|https)://)(([a-zA-Z0-9\\._-]+\\.[a-zA-Z]{2,6})|([0-9]{1,3}\\.){4})(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?)|(([a-zA-Z0-9\\._-]+\\.)(([a-zA-Z]{2,6})|([0-9]{1,3}\\.){4})(:[0-9]{1,4})*(/[a-zA-Z0-9\\&%_\\./-~-]*)?)";//baidu.com
    //电话号码
    String telRegex = "^(\\d{3,4}-)?\\d{6,8}$";
    //验证输入密码条件(字符与数据同时出现)
    String pasRegex = "[A-Za-z]+[0-9]";
    //验证输入密码长度 (6-18位)
    String pasLenghregex = "^\\d{6,18}$";
    /**
     * 获取正则
     */
    String s = "";
    String s2 = "";
    public static final String regErx4 = "<font id=\"food\"[^>]*>.*?</font><br.*/>";

    private void ttt2(String string) {
        Pattern pattern = Pattern.compile(regErx4);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            s = matcher.group(0);
        }
        s2 = string.replaceAll(regEx4, "");
        System.out.println(s);
        System.out.println(s2);
    }


    public boolean isEmail(String strEmail) {
        Pattern p = Pattern.compile(strPattern);
        Matcher m = p.matcher(strEmail);
        return m.matches();
    }
    /**
     * 是否匹配
     * @return
     */
    public void regext(CharSequence temp) {
        String regexString="";
        boolean flag = regexString.matches(regErx4);
        if (!Pattern.matches(regEx, temp)) {

        }
    }

}
