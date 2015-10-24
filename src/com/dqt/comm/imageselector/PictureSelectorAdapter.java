package com.dqt.comm.imageselector;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.dqt.app.BaseActivity;
import com.dqt.app.R;
import com.dqt.ctrl.ImageCtrl;

import android.graphics.Color;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PictureSelectorAdapter extends CommonAdapter<String> {

	/**
	 * 用户选择的图片，存储为图片的完整路径
	 */
	public static List<String> mSelectedImage = new LinkedList<String>();
	private ArrayList<String> mAllImage;
	/**
	 * 文件夹路径
	 */
	private String mDirPath;

	public PictureSelectorAdapter(BaseActivity act, List<String> mDatas, int itemLayoutId,
			String dirPath) {
		super(act, mDatas, itemLayoutId);
		this.mDirPath = dirPath;
		/* 存储完整路径 */
		int size = mDatas.size();
		mAllImage = new ArrayList<String>(size);
		for (int i = 0;i < size; i++) {
			mAllImage.add(i, "file://" + mDirPath + "/" + mDatas.get(i));
		}
	}

	@Override
	public void convert(final com.dqt.comm.imageselector.ViewHolder helper, final String item) {
		//设置no_pic
		helper.setImageResource(R.id.id_item_image, R.drawable.pic_selector_no);
		//设置no_selected
				helper.setImageResource(R.id.id_item_select,
						R.drawable.pic_selector_unselected);
		//设置图片
		helper.setImageByUrl(R.id.id_item_image, mDirPath + "/" + item);
		
		final ImageView mImageView = helper.getView(R.id.id_item_image);
		final ImageView mSelect = helper.getView(R.id.id_item_select);
		mSelect.setOnClickListener(new OnClickListener() {
			// 点击选中图标
			// 选择，则将图片变暗，反之则反之
			@Override
			public void onClick(View v) {
				// 已经选择过该图片
				if (mSelectedImage.contains(mDirPath + "/" + item)) {
					mSelectedImage.remove(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.pic_selector_unselected);
					mImageView.setColorFilter(null);
				} else
				// 未选择该图片
				{
					mSelectedImage.add(mDirPath + "/" + item);
					mSelect.setImageResource(R.drawable.pic_selector_selected);
					mImageView.setColorFilter(Color.parseColor("#77000000"));
				}
			}
		});
		mImageView.setColorFilter(null);
		mImageView.setOnClickListener(new OnClickListener() {
			// 设置ImageView的点击事件
			@Override
			public void onClick(View v) {
//				ActivityCtrl.getInstance().gotoImageBrower(mContext, Uri.parse("file://" + mDirPath + "/" + item));
				ImageCtrl.getInstance().gotoImageBrowser(mActivity, mAllImage, helper.getPosition());
			}
		});
		
		/**
		 * 已经选择过的图片，显示出选择过的效果
		 */
		if (mSelectedImage.contains(mDirPath + "/" + item)) {
			mSelect.setImageResource(R.drawable.pic_selector_selected);
			mImageView.setColorFilter(Color.parseColor("#77000000"));
		}

	}
}
