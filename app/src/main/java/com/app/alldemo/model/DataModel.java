package com.app.alldemo.model;

import java.io.Serializable;
import java.util.List;

public class DataModel implements Serializable{
	private int id;
	private String name;
	private String irco;
	private String path;
	private List<String> images;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getIrco() {
		return irco;
	}
	public void setIrco(String irco) {
		this.irco = irco;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}
}
