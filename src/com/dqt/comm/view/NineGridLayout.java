package com.dqt.comm.view;

import java.util.List;

import com.dqt.app.MainActivity;
import com.dqt.app.SunnyDayApp;
import com.dqt.comm.viewimage.PictureViewActivity;
import com.dqt.ctrl.ActivityCtrl;
import com.dqt.util.AppDebug;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class NineGridLayout extends ViewGroup {

	private Context context;
    /**
     * 图片之间的间隔
     */
    private int gap = 5;
    private int columns;//
    private int rows;//
    private List listData;
    private int totalWidth;
    private int imgWidth;

    public NineGridLayout(Context context) {
        super(context);
        this.context = context;
    }

    public NineGridLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    
    @Override
    protected void onAttachedToWindow() {
    	super.onAttachedToWindow();
    }
    
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
    	// TODO Auto-generated method stub
    	super.onSizeChanged(w, h, oldw, oldh);
    }
    
    private void layoutChildrenView(int w, int h) {
    	totalWidth = w;
    	if (null == context) 
    		context = SunnyDayApp.getInstance().getApplicationContext();
    	imgWidth = totalWidth / 3 - gap;
    	
    	int childrenCount = listData.size();
//        int singleWidth = (totalWidth - gap * (3 - 1)) / 3;
//        int singleHeight = singleWidth;
        int singleWidth = imgWidth;
        int singleHeight = imgWidth;

        //根据子view数量确定高度
        ViewGroup.LayoutParams params = getLayoutParams();
        params.height = singleHeight * rows + gap * (rows - 1);
        setLayoutParams(params);
        for (int i = 0; i < childrenCount; i++) {
            GridImageView childrenView = (GridImageView) getChildAt(i);
//            childrenView.setImageUrl(((Image) listData.get(i)).getUrl());
            int[] position = findPosition(i);
            int left = (singleWidth + gap) * position[1];
            int top = (singleHeight + gap) * position[0];
            int right = left + singleWidth;
            int bottom = top + singleHeight;

            childrenView.layout(left, top, right, bottom);
        }
    }

    private int[] findPosition(int childNum) {
        int[] position = new int[2];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if ((i * columns + j) == childNum) {
                    position[0] = i;//行
                    position[1] = j;//列
                    break;
                }
            }
        }
        return position;
    }

    public int getGap() {
        return gap;
    }

    public void setGap(int gap) {
        this.gap = gap;
    }

    /*
     * TODO 数据类型有待优化
     */
    public void setImagesData(List<Bitmap> lists, int w, int h) {
        if (lists == null || lists.isEmpty()) {
            return;
        }
        //初始化布局
        generateChildrenLayout(lists.size());
        //这里做一个重用view的处理
        if (listData == null) {
            int i = 0;
            while (i < lists.size()) {
                GridImageView iv = generateImageView();
                iv.setImageBitmap(lists.get(i));
                addView(iv,generateDefaultLayoutParams());
                i++;
            }
        } else {
            int oldViewCount = listData.size();
            int newViewCount = lists.size();
            if (oldViewCount > newViewCount) {
                removeViews(newViewCount - 1, oldViewCount - newViewCount);
            } else if (oldViewCount < newViewCount) {
                for (int i = 0; i < newViewCount - oldViewCount; i++) {
                    GridImageView iv = generateImageView();
                    addView(iv,generateDefaultLayoutParams());
                }
            }
        }
        listData = lists;
        layoutChildrenView(w, h);
    }

    /**
     * 根据图片个数确定行列数量
     * 对应关系如下
     * num        row        column
     * 1           1        1
     * 2           1        2
     * 3           1        3
     * 4           2        2
     * 5           2        3
     * 6           2        3
     * 7           3        3
     * 8           3        3
     * 9           3        3
     *
     * @param length
     */
    private void generateChildrenLayout(int length) {
        if (length <= 3) {
            rows = 1;
            columns = length;
        } else if (length <= 6) {
            rows = 2;
            columns = 3;
            if (length == 4) {
                columns = 2;
            }
        } else {
            rows = 3;
            columns = 3;
        }
    }

    private GridImageView generateImageView() {
    	GridImageView iv = new GridImageView(getContext());
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	ActivityCtrl ctrl = ActivityCtrl.getInstance();
            	ctrl.gotoActivity(getContext(), PictureViewActivity.class);
            }
        });
        iv.setBackgroundColor(Color.parseColor("#f5f5f5"));
        return iv;
    }

	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		// TODO Auto-generated method stub
		
	}
}
