package com.dqt.m.blog;

import java.util.ArrayList;

import com.dqt.util.AppDebug;

import android.content.Context;

public class BlogManager {
	private static BlogManager instance = new BlogManager();
	private Context mContext = null;
	private BlogDb db = null;
	
	private BlogManager() {
	}
	
	public static BlogManager getInstance(Context context) {
		if (null == context) {
			AppDebug.loge("BlogManager.getInstance() error: context == null!!!");
			return null;
		}
		instance.mContext = context;
		return instance;
	}
	
	public boolean saveBlog(String title, String content, String date, 
							String scenery, String zone, String gps, 
							String account, ArrayList<String> imageUri) {
		Blog b = new Blog();
		b.setTitle(title);
		b.setContent(content);
		b.setDate(date);
		b.setScenery(scenery);
		b.setZone(zone);
		b.setGps(gps);
		b.setAccount(account);
		b.setImageUri(imageUri);
		getDb().insert(b);
		return true;
	}
	
	private BlogDb getDb() {
		if (db == null) {
			db = new BlogDb(mContext);
		}
		return db;
	}
}
