package com.dqt.c.base;

import com.dqt.util.AppDebug;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Window;

public class BaseActivity extends Activity {
	public Dialog mDialog;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
	}
	
	public void setDialog(Dialog d) {
		mDialog = d;
	}
	
	@Override
	protected void onPause() {
		super.onPause();
		if (null != mDialog && !mDialog.isShowing()) {
			mDialog.dismiss();
		}
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (null != mDialog && !mDialog.isShowing())
			mDialog.dismiss();
	}
}
