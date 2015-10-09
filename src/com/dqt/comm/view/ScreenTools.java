package com.dqt.comm.view;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

public class ScreenTools {
	private static ScreenTools instance;
	private Context ctx;
	
	public ScreenTools(Context ctx) {
	}
	
	public static ScreenTools instance(Context ctx) {
		if (null == instance)
			instance = new ScreenTools(ctx);
		return instance;
	}
	
	public static int getScreenWidth(Context ctx) {
		return ctx.getResources().getDisplayMetrics().widthPixels;
	}
	
	public static int getScreenHeight(Context ctx) {
		return ctx.getResources().getDisplayMetrics().heightPixels;
	}
	
	public static int dip2px(Context ctx, float dipValue) { 
		float scale = ctx.getResources().getDisplayMetrics().density; 
        return (int)(dipValue * scale + 0.5f); 
	} 
	
	public static int px2dip(Context ctx, float pxValue) {
		float scale = ctx.getResources().getDisplayMetrics().density; 
		return (int)(pxValue / scale + 0.5f); 
	} 
	
    /**
     * 設置View的寬度（像素），若設置爲自適應則應該傳入MarginLayoutParams.WRAP_CONTENT
     * @param view
     * @param width
     */
    public static void setLayoutSize(View view, int width, int height) {  
       /* MarginLayoutParams margin=new MarginLayoutParams(view.getLayoutParams());  
        margin.setMargins(margin.leftMargin,y, margin.rightMargin, y+margin.height);  
        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);  
        //view.setLayoutParams(layoutParams);
        ViewGroup.MarginLayoutParams  layoutParams =newLayParms(view, margin);
        //RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);       
        view.setLayoutParams(layoutParams); 
        view.requestLayout();*/
    	if (view.getParent() instanceof FrameLayout){
    		FrameLayout.LayoutParams lp=(FrameLayout.LayoutParams) view.getLayoutParams();
    		if (-1 != width) lp.width = width;
    		if (-1 != height) lp.height = height;
    		view.setLayoutParams(lp);
    		//view.setX(x);
    		view.requestLayout();    		
    	}
    	else if (view.getParent() instanceof RelativeLayout){
    		RelativeLayout.LayoutParams lp=(RelativeLayout.LayoutParams)view.getLayoutParams();
    		if (-1 != width) lp.width = width;
    		if (-1 != height) lp.height = height;
    		view.setLayoutParams(lp);
    		//view.setX(x);
    		view.requestLayout();    		
    	}
    	else if (view.getParent() instanceof LinearLayout){
    		LinearLayout.LayoutParams lp=(LinearLayout.LayoutParams)view.getLayoutParams();
    		if (-1 != width) lp.width = width;
    		if (-1 != height) lp.height = height;
    		view.setLayoutParams(lp);
    		//view.setX(x);
    		view.requestLayout();    		    		
    	}
    } 
    
	/**
	 * 根据Uri获取图片绝对路径，解决Android4.4以上版本Uri转换
	 * @param activity
	 * @param imageUri
	 * @author yaoxing
	 * @date 2014-10-12
	 */
	@TargetApi(19)
	public static String getImageAbsolutePath(Activity context, Uri imageUri) {
		if (context == null || imageUri == null)
			return null;
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && DocumentsContract.isDocumentUri(context, imageUri)) {
			if (isExternalStorageDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				if ("primary".equalsIgnoreCase(type)) {
					return Environment.getExternalStorageDirectory() + "/" + split[1];
				}
			} else if (isDownloadsDocument(imageUri)) {
				String id = DocumentsContract.getDocumentId(imageUri);
				Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
				return getDataColumn(context, contentUri, null, null);
			} else if (isMediaDocument(imageUri)) {
				String docId = DocumentsContract.getDocumentId(imageUri);
				String[] split = docId.split(":");
				String type = split[0];
				Uri contentUri = null;
				if ("image".equals(type)) {
					contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
				} else if ("video".equals(type)) {
					contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
				} else if ("audio".equals(type)) {
					contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
				}
				String selection = MediaStore.Images.Media._ID + "=?";
				String[] selectionArgs = new String[] { split[1] };
				return getDataColumn(context, contentUri, selection, selectionArgs);
			}
		} // MediaStore (and general)
		else if ("content".equalsIgnoreCase(imageUri.getScheme())) {
			// Return the remote address
			if (isGooglePhotosUri(imageUri))
				return imageUri.getLastPathSegment();
			return getDataColumn(context, imageUri, null, null);
		}
		// File
		else if ("file".equalsIgnoreCase(imageUri.getScheme())) {
			return imageUri.getPath();
		}
		return null;
	}

	public static String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
		Cursor cursor = null;
		String column = MediaStore.Images.Media.DATA;
		String[] projection = { column };
		try {
			cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
			if (cursor != null && cursor.moveToFirst()) {
				int index = cursor.getColumnIndexOrThrow(column);
				return cursor.getString(index);
			}
		} finally {
			if (cursor != null)
				cursor.close();
		}
		return null;
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is ExternalStorageProvider.
	 */
	public static boolean isExternalStorageDocument(Uri uri) {
		return "com.android.externalstorage.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is DownloadsProvider.
	 */
	public static boolean isDownloadsDocument(Uri uri) {
		return "com.android.providers.downloads.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is MediaProvider.
	 */
	public static boolean isMediaDocument(Uri uri) {
		return "com.android.providers.media.documents".equals(uri.getAuthority());
	}

	/**
	 * @param uri The Uri to check.
	 * @return Whether the Uri authority is Google Photos.
	 */
	public static boolean isGooglePhotosUri(Uri uri) {
		return "com.google.android.apps.photos.content".equals(uri.getAuthority());
	}
}
