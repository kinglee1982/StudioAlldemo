package com.app.alldemo.model;

import java.io.Serializable;

public class AlbumImages implements Serializable{
	private String imageThurmb;//图片
	private String imageTitles;//照片集
	private int imagesCount;//图片数
	public String getImageThurmb() {
		return imageThurmb;
	}
	public void setImageThurmb(String imageThurmb) {
		this.imageThurmb = imageThurmb;
	}
	public String getImageTitles() {
		return imageTitles;
	}
	public void setImageTitles(String imageTitles) {
		this.imageTitles = imageTitles;
	}
	public int getImagesCount() {
		return imagesCount;
	}
	public void setImagesCount(int imagesCount) {
		this.imagesCount = imagesCount;
	}
}
