package com.sunnyday.common.view;

import android.content.Context;

public class ScreenTools {
	private static ScreenTools instance;
	private Context ctx;
	
	public ScreenTools(Context ctx) {
	}
	
	public static ScreenTools instance(Context ctx) {
		if (null == instance)
			instance = new ScreenTools(ctx);
		return instance;
	}
	
	public int getScreenWidth(Context ctx) {
		return ctx.getResources().getDisplayMetrics().widthPixels;
	}
	
	public int getScreenHeight(Context ctx) {
		return ctx.getResources().getDisplayMetrics().heightPixels;
	}
	
	public static int dip2px(Context ctx, float dipValue) { 
		float scale = ctx.getResources().getDisplayMetrics().density; 
        return (int)(dipValue * scale + 0.5f); 
	} 
	
	public static int px2dip(Context ctx, float pxValue) {
		float scale = ctx.getResources().getDisplayMetrics().density; 
		return (int)(pxValue / scale + 0.5f); 
	} 
}
