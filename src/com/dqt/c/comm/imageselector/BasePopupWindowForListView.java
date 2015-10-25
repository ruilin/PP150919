package com.dqt.c.comm.imageselector;

import java.util.List;

import com.dqt.c.base.BaseActivity;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.BitmapDrawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.PopupWindow;

public abstract class BasePopupWindowForListView<T> extends PopupWindow
{
	/**
	 * 布局文件的最外层View
	 */
	protected View mContentView;
	protected BaseActivity mActivity;
	/**
	 * ListView的数据集
	 */
	protected List<T> mDatas;

	public BasePopupWindowForListView(BaseActivity act, View contentView, int width, int height,
			boolean focusable)
	{
		this(act, contentView, width, height, focusable, null);
		mActivity = act;
	}

	public BasePopupWindowForListView(BaseActivity act, View contentView, int width, int height,
			boolean focusable, List<T> mDatas)
	{
		this(act, contentView, width, height, focusable, mDatas, new Object[0]);
		mActivity = act;
	}

	public BasePopupWindowForListView(BaseActivity act, View contentView, int width, int height,
			boolean focusable, List<T> mDatas, Object... params)
	{
		super(contentView, width, height, focusable);
		this.mContentView = contentView;
		this.mActivity = act;
		if (mDatas != null)
			this.mDatas = mDatas;

		if (params != null && params.length > 0)
		{
			beforeInitWeNeedSomeParams(params);
		}

		setBackgroundDrawable(new BitmapDrawable());
		setTouchable(true);
		setOutsideTouchable(true);
		setTouchInterceptor(new OnTouchListener()
		{
			@Override
			public boolean onTouch(View v, MotionEvent event)
			{
				if (event.getAction() == MotionEvent.ACTION_OUTSIDE)
				{
					dismiss();
					return true;
				}
				return false;
			}
		});
		initViews();
		initEvents();
		init();
	}

	protected abstract void beforeInitWeNeedSomeParams(Object... params);

	public abstract void initViews();

	public abstract void initEvents();

	public abstract void init();

	public View findViewById(int id)
	{
		return mContentView.findViewById(id);
	}

	protected static int dpToPx(Context context, int dp)
	{
		return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
	}

}
