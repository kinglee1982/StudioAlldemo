package com.app.alldemo.model.other;

import java.io.Serializable;
import java.util.List;

public class SName implements Serializable{
	private String name1;
	private String name2;
	private List<String> images;
	public String getName1() {
		return name1;
	}
	public void setName1(String name1) {
		this.name1 = name1;
	}
	public String getName2() {
		return name2;
	}
	public void setName2(String name2) {
		this.name2 = name2;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
}
