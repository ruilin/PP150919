package com.dqt.c.login;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import com.dqt.app.R;
import com.dqt.c.base.ActivityCtrl;
import com.dqt.c.base.BaseActivity;
import com.dqt.c.main.MainActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

@EActivity(R.layout.login_main)
public class LoginActivity extends BaseActivity {
	private LoginActivity mActivity;
	
	@ViewById(R.id.et_username)
	EditText et_username;
	
	@ViewById(R.id.et_password)
	EditText et_password;
	
//	@ViewById(R.id.btn_login)
//	Button b_login;
//	
//	@ViewById(R.id.btn_regist)
//	Button btn_regist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mActivity = this;
	}
	
	@Click
	void btn_login() {
		Toast.makeText(mActivity, "登录成功："+ et_username.getText() + " ／ " + et_password.getText(), 
		Toast.LENGTH_SHORT).show();
		finish();
		ActivityCtrl.getInstance().gotoActivity(mActivity, MainActivity.class);
	}
	
	@Click
	void btn_regist() {
		Toast.makeText(mActivity, "暂未开放！", Toast.LENGTH_SHORT).show();
	}
}
