package com.dqt.comm.viewimage;

import com.sunday.app.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Window;
import android.view.WindowManager;

public class PictureViewActivity extends FragmentActivity 
{
	
	// 屏幕宽度
	public static int screenWidth;
	// 屏幕高度
	public static int screenHeight;
	

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.picture_view_activity);
		  //初始化接口，应用启动的时候调用 
		initViews();
	}

	private void initViews() {

		screenWidth = getWindow().getWindowManager().getDefaultDisplay()
				.getWidth();
		screenHeight = getWindow().getWindowManager().getDefaultDisplay()
				.getHeight();

	}
	 
	
}