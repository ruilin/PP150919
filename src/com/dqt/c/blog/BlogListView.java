package com.dqt.c.blog;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

import com.dqt.app.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.AbsListView;
import android.widget.ListView;

public class BlogListView extends ListView {
	Timer timer;
	 
	public BlogListView(final Context context) {
		super(context);
//		this.setDivider(new ColorDrawable(Color.TRANSPARENT));
//		this.setDividerHeight(10);
		this.setVerticalScrollBarEnabled(false);		/* 隐藏滚动条 */
		BlogAdapter adapter = new BlogAdapter(context, getData());
		this.setAdapter(adapter);
//		this.setAdapter(new ArrayAdapter<Map<String, Object>>(context, R.layout.plaza_item, R.id.textView1, getData()));
		
		this.setOnScrollListener(new AbsListView.OnScrollListener() {
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				/* firstVisibleItem   当前第一个可见的item */
				/* visibleItemCount   当前可见的item个数 */
			}
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
			}
		});
		
	}

	private List<BlogItem> getData() {
		Bitmap icon = BitmapFactory.decodeResource(getResources(),
				R.drawable.head);
		Bitmap img = BitmapFactory.decodeResource(getResources(),
				R.drawable.yjdefimg);
		List<BlogItem> data = new ArrayList<BlogItem>();
		for (int i = 0; i < 5; i++) {
			BlogItem item = new BlogItem();
			item.setName("测试数据" + i);
			item.setIcon(icon);
			item.setImg(img);
			data.add(item);
		}
		// data.add("测试数据2");
		// data.add("测试数据3");
		// data.add("测试数据4");

		return data;
	}
}
