package com.dqt.c.comm.imageselector;

import java.util.List;

import com.dqt.c.base.BaseActivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public abstract class CommonAdapter<T> extends BaseAdapter
{
	protected LayoutInflater mInflater;
	protected BaseActivity mActivity;
	protected List<T> mDatas;
	protected final int mItemLayoutId;

	public CommonAdapter(BaseActivity act, List<T> mDatas, int itemLayoutId)
	{
		this.mActivity = act;
		this.mInflater = LayoutInflater.from(mActivity);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}

	@Override
	public int getCount()
	{
		return mDatas.size();
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder viewHolder = getViewHolder(position, convertView,
				parent);
		convert(viewHolder, getItem(position));
		return viewHolder.getConvertView();

	}

	public abstract void convert(ViewHolder helper, T item);

	private ViewHolder getViewHolder(int position, View convertView,
			ViewGroup parent)
	{
		return ViewHolder.get(mActivity, convertView, parent, mItemLayoutId,
				position);
	}

}
