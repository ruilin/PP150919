package com.dqt.ctrl;

import java.io.File;

import com.dqt.app.DqtApp;
import com.dqt.util.AppDebug;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

public class ActivityCtrl {
	private static ActivityCtrl instance = new ActivityCtrl();
	
	private ActivityCtrl() {
		
	}
	
	public static ActivityCtrl getInstance() {
		return instance;
	}
	
	public void gotoActivity(Context ctx, Class<?> cls) {
        Intent intent = new Intent(ctx, cls);
        ctx.startActivity(intent);
	}
	
	public void gotoActivityForResult(Activity act, Class<?> cls, int requireCode) {
          Intent intent = new Intent(act, cls);  
          act.startActivityForResult(intent, requireCode);
	}
	
	public String getRunningActivityName() {
        ActivityManager activityManager = (ActivityManager) DqtApp.getInstance().getSystemService(Context.ACTIVITY_SERVICE);  
        String runningActivity = activityManager.getRunningTasks(1).get(0).topActivity.getClassName();  
        return runningActivity; 
	}
	
	public void gotoImageBrower(Context ctx, Uri uri) {
		AppDebug.logd(uri.getPath());
		Intent it =new Intent(Intent.ACTION_VIEW);
		it.setDataAndType(uri, "image/*");
		ctx.startActivity(it);
	}
}
