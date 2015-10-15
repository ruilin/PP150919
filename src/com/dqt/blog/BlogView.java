package com.dqt.blog;

import com.dqt.app.MainActivity;
import com.dqt.app.R;
import com.dqt.blog.edit.BlogEditActivity;
import com.dqt.comm.view.RefreshViewLayout;
import com.dqt.comm.view.RefreshViewLayout.PullToRefreshListener;
import com.dqt.ctrl.ActivityCtrl;

import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

public class BlogView extends LinearLayout {
	private BlogListView contentView;
	private RefreshViewLayout refreshLayout;
	private View titleBar;
	private View layout;
	
	public BlogView(final MainActivity main, AttributeSet attrs) {
		super(main, attrs);
		setOrientation(VERTICAL);
		layout = View.inflate(main, R.layout.blog_main, null);
		addView(layout);
		contentView = new BlogListView(main);
		refreshLayout = (RefreshViewLayout) layout.findViewById(R.id.blog_refreshView);
		refreshLayout.addView(contentView);
        refreshLayout.setOnRefreshListener(new PullToRefreshListener() {
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
			}
		}, 0);

        titleBar = layout.findViewById(R.id.blog_title);
        View btn_right = titleBar.findViewById(R.id.title_right);
        btn_right.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/* press down the '+' button */
		        ActivityCtrl.getInstance().gotoActivity(main, BlogEditActivity.class);
			}
		});
	}

}
