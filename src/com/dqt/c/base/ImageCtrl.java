package com.dqt.c.base;

import java.util.ArrayList;
import java.util.List;

import com.dqt.c.comm.imagebrowser.ImageBrowserActivtiy;
import com.dqt.m.image.UILImagePagerAdapter;
import com.simple.imagebrowser.ImageBrowser;
import com.simple.imagebrowser.adapter.ImageAdapter;

import android.content.Intent;

public class ImageCtrl {
	private static ImageCtrl instance = new ImageCtrl();
	ImageBrowser browser;
	
	private ImageCtrl() {
	}
	
	public static ImageCtrl getInstance() {
		return instance;
	}
	
	public void gotoImageBrowser(BaseActivity act, List<String> list, int position) {
//		ImageAdapter adapter = new GlideImagePagerAdapter(ctx, list);
		ImageAdapter adapter = new UILImagePagerAdapter(act, list);
		if (null == browser)
			browser = new ImageBrowser(act);
		act.setDialog(browser);
		if (!act.isFinishing() && !browser.isShowing()) {
			// 设置显示图片的Adapter
			browser.setImageAdapter(adapter);
			browser.setPagePosition(position);
			browser.setTapDismiss(true);
			browser.show();
		}
	}
	
	public void gotoImageBrowser(BaseActivity act, ArrayList<String> list, int position) {
//		ImageAdapter adapter = new GlideImagePagerAdapter(ctx, list);

		ImageAdapter adapter = new UILImagePagerAdapter(act, list);
		if (null == browser)
			browser = new ImageBrowser(act);
		act.setDialog(browser);
		if (!act.isFinishing() && !browser.isShowing()) {
			// 设置显示图片的Adapter
			browser.setImageAdapter(adapter);
			browser.setPagePosition(position);
			browser.setTapDismiss(true);
			browser.show();
		}
		/* change image browser frome dialog to activity */
//        Intent intent = new Intent(act, ImageBrowserActivtiy.class);
//        intent.putStringArrayListExtra("URL_LIST", list);
//        intent.putExtra("POSITION", position);
//        act.startActivity(intent);
	}
	
	/* test load image by http */
	private List<String> mockImages() {
		List<String> imagesUrls = new ArrayList<String>();
		imagesUrls
				.add("http://imgsrc.baidu.com/forum/pic/item/09be3f094b36acaf0ad6eb717cd98d1000e99cde.jpg");
		imagesUrls.add("http://tupian.enterdesk.com/2014/mxy/05/15/1/7.jpg");
		imagesUrls
				.add("http://b.hiphotos.baidu.com/image/pic/item/14ce36d3d539b600ab94fdc3ea50352ac65cb768.jpg");
		imagesUrls.add("http://img6.faloo.com/Picture/0x0/0/747/747488.jpg");
		imagesUrls
				.add("http://61.144.56.195/forum/201302/19/144727b4b4zbfbyhbmfv4a.jpg");
		return imagesUrls;
	}
}
