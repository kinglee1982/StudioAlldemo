package com.app.alldemo.model;

import java.io.Serializable;
import java.util.List;

public class ImageBucket implements Serializable{
	public int count = 0;
	public String bucketName;//图片集名字
	public List<ImageItem> imageList;
}
