package com.dqt.m.blog;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

public class BlogDb extends SQLiteOpenHelper {
	private final static String DB_NAME = "dqt_blog.db";
	private final static int DB_VERSION = 1;
	private final static String TABLE_NAME = "blog";
	
	public BlogDb(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
	}

	public BlogDb(Context context, String name, CursorFactory factory, int version,
			DatabaseErrorHandler errorHandler) {
		super(context, name, factory, version, errorHandler);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		String sql;
		sql = "CREATE TABLE " + TABLE_NAME + " (" 
				+ Blog.KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ Blog.KEY_TITLE + " VARCHAR);";
		db.execSQL(sql);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		String sql;
		sql = "DROP TABLE IF EXISTS" + TABLE_NAME;
		db.execSQL(sql);
		onCreate(db);
	}
	
	public Cursor select() {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
		return cursor;
	}
	
	public long insert(Blog blog) {
		SQLiteDatabase db = this.getWritableDatabase();
		long row = db.insert(TABLE_NAME, null, blog.getContentValues());
		return row;
	}
	
	public void delete(int id) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = Blog.KEY_ID + "= ?";
		String[] whereValue = { Integer.toString(id) };
		db.delete(TABLE_NAME, where, whereValue);
	}
	
	public void update(Blog blog) {
		SQLiteDatabase db = this.getWritableDatabase();
		String where = Blog.KEY_ID + " = ?";
		String[] whereValue = { Integer.toString(blog.getId()) };
		
		db.update(TABLE_NAME, blog.getContentValues(), where, whereValue);
	}
}
