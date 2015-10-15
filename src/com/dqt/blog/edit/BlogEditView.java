package com.dqt.blog.edit;

import com.dqt.app.R;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

public class BlogEditView extends LinearLayout {
	private View blogEditView;
	
	public BlogEditView(Context context) {
		super(context);
		setOrientation(VERTICAL);
		blogEditView = View.inflate(context, R.layout.test, null);
		addView(blogEditView);
	}

}
