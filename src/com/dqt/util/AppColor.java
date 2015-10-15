package com.dqt.util;

import com.dqt.app.R;

import android.content.res.Resources;

public class AppColor {
	public static int title_bar_bg_blue;
	
	public static void init(Resources res) {
		title_bar_bg_blue = res.getColor(R.color.title_bar_bg_blue);
	}

}
