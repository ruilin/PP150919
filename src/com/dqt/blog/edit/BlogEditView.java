package com.dqt.blog.edit;

import com.sunday.app.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
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
