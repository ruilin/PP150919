package com.sunnyday.util;

import android.util.Log;

/*
 * 调试模块
 * 对程序日志进行处理和监控
 */
public final class AppDebug {
	static final String FOUT_PATH = "/sdcard/SunnyDay/debug.txt";
	static final String LOGTAG = "NULL";
	static final String LOGTAG_TEST = "TEST";
	
	public static final void logi(String msg) {
		Log.i(LOGTAG, msg);
	}
	
	public static final void logd(String msg) {
		Log.d(LOGTAG, msg);
	}
	
	public static final void logw(String msg) {
		Log.w(LOGTAG, msg);
	}
	
	public static final void loge(String msg) {
		Log.e(LOGTAG, msg);
	}

	public static final void logt(String msg) {
		Log.e(LOGTAG_TEST, msg);
	}
	
	public static final void logflag() {
		Log.e(LOGTAG_TEST, "[APP Debuge] xxx !");
	}
}
