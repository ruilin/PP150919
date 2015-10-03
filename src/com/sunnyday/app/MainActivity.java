package com.sunnyday.app;

import com.sunday.app.R;
import com.sunnyday.blog.BlogView;
import com.sunnyday.friend.FriendView;
import com.sunnyday.util.AppColor;
import com.sunnyday.util.AppDebug;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends BaseActivity 
						implements View.OnClickListener, View.OnFocusChangeListener, View.OnTouchListener {

	private final int TAB_COUNT = 5;

	private RelativeLayout mTabBar;
	private LinearLayout contentContainer;
	private ImageView mBtnFriends;
	private ImageView mBtnNews;
	private ImageView mBtnBlog;
	private ImageView mBtnSetting;
	private ImageView mBtnSquare;
	private int curIndex = -1;
	private View mViewList[];
//	private ImageView mTab[];
	
	RelativeLayout[] mTab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mTab = new RelativeLayout[TAB_COUNT];
        mViewList = new View[TAB_COUNT];
//        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_bar);
        this.mTabBar = ((RelativeLayout)findViewById(R.id.main_tab_bar));
        this.contentContainer = ((LinearLayout)findViewById(R.id.main_container));
//        this.contentContainer = ((LinearLayout)findViewById(R.id.refreshView));
//        mTab[0] = ((ImageView)findViewById(R.id.main_btn_hot));
//        mTab[1] = ((ImageView)findViewById(R.id.main_btn_destination));
//        mTab[2] = ((ImageView)findViewById(R.id.main_btn_blog));
//        mTab[3] = ((ImageView)findViewById(R.id.main_btn_friends));
//        mTab[4] = ((ImageView)findViewById(R.id.main_btn_setting));
        mTab[0] = (RelativeLayout) findViewById(R.id.item_0);
        mTab[1] = (RelativeLayout) findViewById(R.id.item_1);
        mTab[2] = (RelativeLayout) findViewById(R.id.item_2);
        mTab[3] = (RelativeLayout) findViewById(R.id.item_3);
        mTab[4] = (RelativeLayout) findViewById(R.id.item_4);
        initItem(mTab[0], R.string.main_bar_item0, R.drawable.btn_tab_hot);
        initItem(mTab[1], R.string.main_bar_item1, R.drawable.btn_tab_destination);
        initItem(mTab[2], R.string.main_bar_item2, R.drawable.btn_tab_blog);
        initItem(mTab[3], R.string.main_bar_item3, R.drawable.btn_tab_friends);
        initItem(mTab[4], R.string.main_bar_item4, R.drawable.btn_tab_me);
//        mTab[5].setFocusable(true);
//        mTab[1].setOnTouchListener(new OnTouchListener() {
//			
//			@Override
//			public boolean onTouch(View v, MotionEvent event) {
//				switch (event.getAction()) {
//				case MotionEvent.ACTION_DOWN:
//					AppDebug.loge("xxxx 99");
//					break;
//				case MotionEvent.ACTION_UP:
//					AppDebug.loge("xxxx 00");
//					
//					break;
//				default: break;
//				}
//				return false;
//			}
//		});
//        mTab[1].setOnFocusChangeListener(new OnFocusChangeListener() {
//			
//			@Override
//			public void onFocusChange(View v, boolean hasFocus) {
//				AppDebug.loge("xxxx 88");
//				v.setBackgroundColor(AppColor.title_bar_bg_blue);
//			}
//		});
        for (int i = 0; i < TAB_COUNT; i++) {
        	mTab[i].setOnTouchListener(this);
        	mTab[i].setOnFocusChangeListener(this);
        }
        setTab(3);
    }
    
    private void initItem(RelativeLayout tab, int resIdText, int resIdIcon) {
    	TextView tv = (TextView) tab.findViewById(R.id.text);
    	tv.setText(resIdText);
    	ImageView iv = (ImageView) tab.findViewById(R.id.icon);
    	iv.setImageResource(resIdIcon);
    	ImageView dot = (ImageView) tab.findViewById(R.id.dot);
    	dot.setVisibility(View.INVISIBLE);
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
    
    public boolean setTab(int index) {
    	if (0 <= index && index < TAB_COUNT) {
    		 mTab[index].requestFocus();
    		 return true;
    	}
    	return false;
    }

	@Override
	public void onClick(View v) {
		AppDebug.logi("MainActivity onClick()");
//		switch (v.getId()) {
//		case R.id.main_btn_hot:
//			resetTab(0);
//			AppDebug.logt("MainActivity onClick() main_btn_square");
//			break;
//		case R.id.main_btn_destination:
//			resetTab(1);
//			AppDebug.logt("MainActivity onClick() main_btn_destination");
//			break;
//		case R.id.main_btn_blog:
//			resetTab(2);
//			AppDebug.logt("MainActivity onClick() main_btn_record");
//			break;
//		case R.id.main_btn_setting:
//			resetTab(3);
//			AppDebug.logt("MainActivity onClick() main_btn_setting");
//			break;
//		case R.id.main_btn_friends:
//			resetTab(4);
//			AppDebug.logt("MainActivity onClick() main_btn_friends");
//			break;
//		default:
//			break;
//		}
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void onFocusChange(View v, boolean hasFocus) {
		v.setBackgroundColor(hasFocus ? AppColor.title_bar_bg_blue : Color.TRANSPARENT);
		if (hasFocus) {
			switch (v.getId()) {
			case R.id.item_0:
				resetTab(0);
				AppDebug.logt("MainActivity onFocusChange() main_btn_square");
				break;
			case R.id.item_1:
				resetTab(1);
				AppDebug.logt("MainActivity onFocusChange() main_btn_destination");
				break;
			case R.id.item_2:
				resetTab(2);
				AppDebug.logt("MainActivity onFocusChange() main_btn_record");
				break;
			case R.id.item_3:
				resetTab(3);
				AppDebug.logt("MainActivity onFocusChange() main_btn_friends");
				break;
			case R.id.item_4:
				resetTab(4);
				AppDebug.logt("MainActivity onFocusChange() main_btn_setting");
				break;
			default: break;
			}
		}
		AppDebug.logt("MainActivity onFocusChange(): " + v.getId() + " " + hasFocus);
	}
    
	private void resetTab(int i) {
		int oldIndex = curIndex;
		curIndex = i;
		if (oldIndex != curIndex) {
			if (null == mViewList[curIndex]) {
				switch (curIndex) {
				case 0:
			        mViewList[curIndex] = new BlogView(this, null);
					break;
				case 1:
					mViewList[curIndex] = new BlogView(this, null);
					break;
				case 2:
					mViewList[curIndex] = new BlogView(this, null);
					break;
				case 3:
					  mViewList[curIndex] = new FriendView(this);
					break;
				case 4:
					mViewList[curIndex] = new BlogView(this, null);
					break;
				}
			}
			contentContainer.removeAllViews();
			contentContainer.addView(mViewList[curIndex]);
		}
	}

}
