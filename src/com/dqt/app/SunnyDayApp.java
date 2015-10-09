package com.dqt.app;

import com.dqt.util.AppColor;

import android.app.Application;

public class SunnyDayApp extends Application {
	
	private static SunnyDayApp app;
	
	public SunnyDayApp() {
		app = this;
	}
	
	public static SunnyDayApp getInstance() {
		if (null == app) 
			app = new SunnyDayApp();
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
