package com.sunnyday.app;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.sunday.app.R;
import com.sunnyday.blog.BlogView;
import com.sunnyday.util.AppColor;
import com.sunnyday.util.AppDebug;

public class MainActivity extends BaseActivity implements View.OnClickListener, View.OnFocusChangeListener {

	private RelativeLayout mTab;
	private LinearLayout contentContainer;
	private ImageView mBtnFriends;
	private ImageView mBtnNews;
	private ImageView mBtnBlog;
	private ImageView mBtnSetting;
	private ImageView mBtnSquare;
	
	private BlogView mViewBlog;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
        this.mTab = ((RelativeLayout)findViewById(R.id.main_tab_bar));
        this.contentContainer = ((LinearLayout)findViewById(R.id.main_container));
//        this.contentContainer = ((LinearLayout)findViewById(R.id.refreshView));
        this.mBtnSquare = ((ImageView)findViewById(R.id.main_btn_hot));
        this.mBtnNews = ((ImageView)findViewById(R.id.main_btn_destination));
        this.mBtnBlog = ((ImageView)findViewById(R.id.main_btn_blog));
        this.mBtnSetting = ((ImageView)findViewById(R.id.main_btn_setting));
        this.mBtnFriends = ((ImageView)findViewById(R.id.main_btn_friends));
        
        mBtnSquare.setOnClickListener(this);
        mBtnNews.setOnClickListener(this);
        mBtnBlog.setOnClickListener(this);
        mBtnSetting.setOnClickListener(this);
        mBtnFriends.setOnClickListener(this);
        
        mBtnSquare.setOnFocusChangeListener(this);
        mBtnNews.setOnFocusChangeListener(this);
        mBtnBlog.setOnFocusChangeListener(this);
        mBtnSetting.setOnFocusChangeListener(this);
        mBtnFriends.setOnFocusChangeListener(this);
        
        mViewBlog = new BlogView(this, null);
        contentContainer.addView(mViewBlog);
        
        mBtnBlog.requestFocus();
    }

    @Override
    protected void onResume() {
    	super.onResume();
    }
    
    @Override
    protected void onPause() {
    	super.onPause();
    }
    
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


	@Override
	public void onClick(View v) {
		AppDebug.logi("MainActivity onClick()");
		switch (v.getId()) {
		case R.id.main_btn_hot:
			AppDebug.logt("MainActivity onClick() main_btn_square");
			break;
		case R.id.main_btn_destination:
			AppDebug.logt("MainActivity onClick() main_btn_destination");
			break;
		case R.id.main_btn_blog:
			AppDebug.logt("MainActivity onClick() main_btn_record");
			break;
		case R.id.main_btn_setting:
			AppDebug.logt("MainActivity onClick() main_btn_setting");
			break;
		case R.id.main_btn_friends:
			AppDebug.logt("MainActivity onClick() main_btn_friends");
			break;
		default:
			break;
		}
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		v.setBackgroundColor(hasFocus ? AppColor.title_bar_bg_blue : Color.TRANSPARENT);
		AppDebug.logt("MainActivity onFocusChange(): " + v.getId() + " " + hasFocus);
	}
    
}
