package com.sunnyday.app;

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
