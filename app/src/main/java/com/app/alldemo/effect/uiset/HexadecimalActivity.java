package com.app.alldemo.effect.uiset;

import android.app.Activity;
import android.os.Bundle;

public class HexadecimalActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		test();
	}
	private void test() {
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
}
