package com.app.alldemo.model;

public class Student implements Comparable {
	private int num;
	private String name;

	public Student(int num, String name) {
		this.num = num;
		this.name = name;
	}
	public int compareTo(Object o) {
		Student s = (Student) o;
		return num > s.num ? 1 : (num == s.num ? 0 : -1);
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
	};
}
