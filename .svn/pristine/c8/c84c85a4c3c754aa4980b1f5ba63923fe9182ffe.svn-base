package com.sunnyday.view.diary;

import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.sunday.app.R;
import com.sunnyday.app.MainActivity;
import com.sunnyday.view.common.RefreshViewLayout;
import com.sunnyday.view.common.RefreshViewLayout.PullToRefreshListener;

public class DiaryView extends LinearLayout {
	DiaryListView contentView;
	RefreshViewLayout refreshLayout;
	public DiaryView(MainActivity main, AttributeSet attrs) {
		super(main, attrs);
		setOrientation(VERTICAL);
		contentView = new DiaryListView(main);
		View titleBar = View.inflate(main, R.layout.title_bar, null);
//		View titleBar = main.findViewById(R.layout.title_bar);
		refreshLayout = new RefreshViewLayout(main, attrs);
		refreshLayout.addView(contentView);
		addView(titleBar);
        addView(refreshLayout);
        refreshLayout.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
			}
		}, 0);
	}

}
