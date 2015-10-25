package com.dqt.c.blog;

import java.util.ArrayList;
import java.util.List;

import com.dqt.app.R;
import com.dqt.v.comm.NineGridLayout;
import com.dqt.v.comm.ScreenTools;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BlogAdapter extends BaseAdapter {

	private LayoutInflater mInflater;
    private Context mContext = null;
    private List<BlogItem> mMarkerData = null;
	
	public BlogAdapter(Context ctx, List<BlogItem> markerItems) {
        mContext = ctx;
        mMarkerData = markerItems;
        mInflater = LayoutInflater.from(mContext);
	}

    public void setMarkerData(List<BlogItem> markerItems) {
        mMarkerData = markerItems;
    }
    
	@Override
	public int getCount() {
        int count = 0;
        if (null != mMarkerData) {
            count = mMarkerData.size();
        }
        return count;
	}

	@Override
	public BlogItem getItem(int position) {
        BlogItem item = null;
        if (null != mMarkerData) {
            item = mMarkerData.get(position);
        }
        return item;
	}

	@Override
	public long getItemId(int position) {
        return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.blog_item, null);
            viewHolder.name = (TextView) convertView.findViewById(R.id.diary_title);
            viewHolder.icon = (ImageView) convertView .findViewById(R.id.diary_icon);
            viewHolder.ninegrid = (NineGridLayout) convertView.findViewById(R.id.diary_ninegrid);
            
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // set item values to the viewHolder:
        BlogItem diaryItem = getItem(position);
        if (null != diaryItem) {
            viewHolder.name.setText(diaryItem.getName());
            viewHolder.icon.setImageBitmap(diaryItem.getIcon());
            
            ArrayList<Bitmap> list = new ArrayList<Bitmap>();
            list.add(diaryItem.getImg());
            list.add(diaryItem.getImg());
            list.add(diaryItem.getImg());
            list.add(diaryItem.getImg());
            list.add(diaryItem.getImg());
            list.add(diaryItem.getImg());
            list.add(diaryItem.getImg());
            list.add(diaryItem.getImg());
            list.add(diaryItem.getImg());
            
            /** TODO 尺寸为临时数据 */
            int screenGap = (int) convertView.getResources().getDimension(R.dimen.view_gap_horizontal);
            viewHolder.ninegrid.setImagesData(list, ScreenTools.getScreenWidth(mContext) - (screenGap << 1), ScreenTools.getScreenHeight(mContext));
        }
        return convertView;
	}

    private static class ViewHolder {
        TextView name;
        ImageView icon;
        NineGridLayout ninegrid;
    }
}
