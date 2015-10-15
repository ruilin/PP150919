package com.dqt.app.login;

import com.dqt.app.BaseActivity;
import com.dqt.app.MainActivity;
import com.dqt.app.R;
import com.dqt.ctrl.ActivityCtrl;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity {
	private LoginActivity mActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login_main);
		mActivity = this;
		final EditText et_username = (EditText) findViewById(R.id.et_username);
		final EditText et_password = (EditText) findViewById(R.id.et_password);
		Button btn_login = (Button) findViewById(R.id.btn_login);
		Button btn_regist = (Button) findViewById(R.id.btn_regist);
		
		btn_login.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(mActivity, "登录成功："+ et_username.getText() + " ／ " + et_password.getText(), 
						Toast.LENGTH_SHORT).show();
				finish();
				ActivityCtrl.getInstance().gotoActivity(mActivity, MainActivity.class);
			}
		});
		
		btn_regist.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
	    		Toast.makeText(mActivity, "暂未开放！", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
