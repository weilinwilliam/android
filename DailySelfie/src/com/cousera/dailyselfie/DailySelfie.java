package com.cousera.dailyselfie;

import com.cousera.dailyselfie.provider.DailySelfieContract;

import android.database.Cursor;
import android.graphics.Bitmap;

public class DailySelfie {
	private int id;
	private String name;
	private String path;
	private String thumbPath;
	private Bitmap bmp;
	
	public static DailySelfie fromCursor(Cursor cursor) {
		DailySelfie selfie = new DailySelfie();
		
		selfie.setId(cursor.getInt(cursor.getColumnIndex(DailySelfieContract.SELFIE_COLUMN_ID)));
		selfie.setPath(cursor.getString(cursor.getColumnIndex(DailySelfieContract.SELFIE_COLUMN_PATH)));
		selfie.setName(cursor.getString(cursor.getColumnIndex(DailySelfieContract.SELFIE_COLUMN_NAME)));
		return selfie;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public Bitmap getBmp() {
		return bmp;
	}
	public void setBmp(Bitmap bmp) {
		this.bmp = bmp;
	}

	public String getThumbPath() {
		return thumbPath;
	}

	public void setThumbPath(String thumbPath) {
		this.thumbPath = thumbPath;
	}
	
}
