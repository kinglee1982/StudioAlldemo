package com.app.alldemo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Administrator on 2015/12/28.
 */
public class Test {
    public static void main(String[] args) {
        String st="sss12kk3kk33k3";
        ttt2(st);
    }

    private static String regEx4 = "\\d";
    private static void ttt2(String string) {
        String s2 = "";
        String s = "";
        Pattern pattern = Pattern.compile(regEx4);
        Matcher matcher = pattern.matcher(string);
        while (matcher.find()) {
            s = matcher.group(0);
            System.out.println(s);
        }
    }
}
