package com.dqt.friend;

import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

import com.dqt.app.MainActivity;
import com.sunday.app.R;

public class FriendView extends LinearLayout {
	private View layout;
//	private ListView listView;
	
	public FriendView(MainActivity main) {
		super(main, null);
		setOrientation(VERTICAL);
		setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));
		layout = View.inflate(main, R.layout.friend_main, null);
		addView(layout);
		FriendListView listView = new FriendListView(main);
		addView(listView);
	}
	
}
