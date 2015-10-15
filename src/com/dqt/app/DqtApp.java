package com.dqt.app;

import com.dqt.util.AppColor;

import android.app.Application;

public class DqtApp extends Application {
	
	private static DqtApp app;
	
	public DqtApp() {
		app = this;
	}
	
	public static DqtApp getInstance() {
		if (null == app) 
			app = new DqtApp();
		return app;
	}

	@Override
	public void onCreate() {
		AppColor.init(getResources());
		super.onCreate();
	}
	
	@Override
	public void onLowMemory() {
		super.onLowMemory();
	}
	
	@Override
	public void onTerminate() {
		super.onTerminate();
	}
}
