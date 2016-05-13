package com.app.alldemo.model.other;

import java.io.Serializable;

public class FicationBean implements Serializable{
	private String fication_id;
	private String fication_name;
	public String getFication_id() {
		return fication_id;
	}
	public void setFication_id(String fication_id) {
		this.fication_id = fication_id;
	}
	public String getFication_name() {
		return fication_name;
	}
	public void setFication_name(String fication_name) {
		this.fication_name = fication_name;
	}
}
