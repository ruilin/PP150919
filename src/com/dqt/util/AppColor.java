package com.dqt.util;

import android.content.res.Resources;

import com.sunday.app.R;

public class AppColor {
	public static int title_bar_bg_blue;
	
	public static void init(Resources res) {
		title_bar_bg_blue = res.getColor(R.color.title_bar_bg_blue);
	}

}
