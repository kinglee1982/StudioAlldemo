package com.app.alldemo.model;

import java.util.Comparator;

public class Student2 {
	private int num;
	private String name;

	public Student2(int num, String name) {
		this.num = num;
		this.name = name;
	}

	public static class studentComparator implements Comparator {
		public int compare(Object o1, Object o2) {
			Student2 s1 = (Student2) o1;
			Student2 s2 = (Student2) o2;
			int result = s1.num > s2.num ? 1 : (s1.num == s2.num ? 0 : -1);
			// 注意：此处在对比num相同时，再按照name的首字母比较。
			if (result == 0) {
				result = s1.name.compareTo(s2.name);
			}
			return result;
		}
	}
	
	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

