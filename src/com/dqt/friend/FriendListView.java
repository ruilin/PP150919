package com.dqt.friend;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.sunday.app.R;

public class FriendListView extends ListView {
	
	private final String KEY_ICON = "ICON";
	private final String KEY_TEXT = "TEXT";
	
	private final int[][] ITEMS = {
			{R.drawable.dqtwait, R.string.friend_circle},
			{R.drawable.friend, R.string.friend_my},
			{R.drawable.blog, R.string.friend_blog},
			{R.drawable.youji, R.string.friend_trave},
			{R.drawable.xianlu, R.string.friend_line},
	};
	
	public FriendListView(Context context) {
		super(context);
//		this.setAdapter(new SimpleAdapter(context, getData(), R.layout.friend_item, R.id.friend_item_icon, R.id.friend_item_text));
		this.setDivider(new ColorDrawable(Color.LTGRAY));
		this.setDividerHeight(30);
		SimpleAdapter sa = new SimpleAdapter(context, getData(), R.layout.friend_item, 
				new String[]{KEY_ICON, KEY_TEXT}, 
				new int[]{R.id.friend_item_icon, R.id.friend_item_text});
		this.setAdapter(sa);
	}
	
	private List<Map<String, Object>> getData() {
		List<Map<String, Object>> data = new ArrayList<Map<String, Object>>();
		for (int i = 0; i < ITEMS.length; i++) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put(KEY_ICON, ITEMS[i][0]);
			map.put(KEY_TEXT, getResources().getString(ITEMS[i][1]));
			data.add(map);
		}

		return data;
	}
	
}
