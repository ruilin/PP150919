package com.dqt.m.blog;

import java.util.ArrayList;

public class Blog {
	public final static String KEY_ID = "id";
	public final static String KEY_TITLE = "title";
	public final static String KEY_CONTENT = "content";
	public final static String KEY_DATE = "date";
	public final static String KEY_SCENERY = "scenery";
	public final static String KEY_ZONE = "zone";
	public final static String KEY_GPS = "gps";
	public final static String KEY_ACCOUNT = "account";
	
	private int id;
	private String title;
	private String content;
	private String date;
	private String scenery;
	private String zone;
	private String gps;
	private String account;
	private ArrayList<String> imageUri;
	
	public Blog() {
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getScenery() {
		return scenery;
	}
	public void setScenery(String scenery) {
		this.scenery = scenery;
	}
	public String getZone() {
		return zone;
	}
	public void setZone(String zone) {
		this.zone = zone;
	}
	public String getGps() {
		return gps;
	}
	public void setGps(String gps) {
		this.gps = gps;
	}
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public ArrayList<String> getImageUri() {
		return imageUri;
	}
	public void setImageUri(ArrayList<String> imageUri) {
		this.imageUri = imageUri;
	}

}
