package com.sunnyday.blog;

import android.graphics.Bitmap;

public class BlogItem {
	private String title;
	private Bitmap icon;
	private Bitmap img;
	
	public String getName() {
		return title;
	}
	public void setName(String name) {
		this.title = name;
	}
	public Bitmap getIcon() {
		return icon;
	}
	public void setIcon(Bitmap icon) {
		this.icon = icon;
	}
	public void setImg(Bitmap img) {
		this.img = img;
	}
	public Bitmap getImg() {
		return img;
	}
}
