package com.dqt.c.blog.edit;

import com.dqt.app.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class AddImgMenuActivity extends Activity implements OnClickListener{
	public final static String MENU_SELECTED = "ITEM_INDEX";
	
	private Activity activity;
	private Button btn_take_photo, btn_pick_photo, btn_cancel;
	private LinearLayout layout;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.blog_edit_addimg_menu);
		activity = this;
		btn_take_photo = (Button) this.findViewById(R.id.btn_take_photo);
		btn_pick_photo = (Button) this.findViewById(R.id.btn_pick_photo);
		btn_cancel = (Button) this.findViewById(R.id.btn_cancel);
		
		layout = (LinearLayout)findViewById(R.id.pop_layout);
		
		//添加选择窗口范围监听可以优先获取触点，即不再执行onTouchEvent()函数，点击其他地方时执行onTouchEvent()函数销毁Activity
		layout.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
			}
		});
		//添加按钮监听
		btn_take_photo.setOnClickListener(this);
		btn_pick_photo.setOnClickListener(this);
		btn_cancel.setOnClickListener(this);
		
		btn_take_photo.setOnTouchListener(itemOnTouchListener);
		btn_pick_photo.setOnTouchListener(itemOnTouchListener);
		btn_cancel.setOnTouchListener(cancelOnTouchListener);
	}
	
	//实现onTouchEvent触屏函数但点击屏幕时销毁本Activity
	@Override
	public boolean onTouchEvent(MotionEvent event){
		finish();
		return true;
	}

	public void onClick(View v) {
		byte index = 0;
		switch (v.getId()) {
		case R.id.btn_take_photo:
			index = 0;
			break;
		case R.id.btn_pick_photo:
			index = 1;
			break;
		case R.id.btn_cancel:
			index = 2;
			break;
		default:
			break;
		}
		Intent in = new Intent();
		in.putExtra(MENU_SELECTED, index);
		setResult(RESULT_OK, in);
		finish();
	}
	
	View.OnTouchListener itemOnTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(activity.getResources().getColor(R.color.title_bar_bg_blue));
				break;
			case MotionEvent.ACTION_UP:
				v.setBackgroundColor(activity.getResources().getColor(R.color.white));
				break;
			}
			return false;
		}
	};
	View.OnTouchListener cancelOnTouchListener = new OnTouchListener() {
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
				v.setBackgroundColor(activity.getResources().getColor(R.color.title_bar_bg_blue));
				break;
			case MotionEvent.ACTION_UP:
				v.setBackgroundColor(activity.getResources().getColor(R.color.orange));
				break;
			}
			return false;
		}
	};
}
