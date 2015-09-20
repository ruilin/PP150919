package com.sunnyday.blog;

import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;

import com.sunday.app.R;
import com.sunnyday.app.MainActivity;
import com.sunnyday.blog.edit.BlogEditActivity;
import com.sunnyday.common.view.RefreshViewLayout;
import com.sunnyday.common.view.RefreshViewLayout.PullToRefreshListener;

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
		        Intent intent = new Intent(main, BlogEditActivity.class);
		        main.startActivity(intent);
			}
		});
	}

}
