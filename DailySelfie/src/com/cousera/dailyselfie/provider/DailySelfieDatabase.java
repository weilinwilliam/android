package com.cousera.dailyselfie.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DailySelfieDatabase extends SQLiteOpenHelper {
	
	private static final String DATABASE_NAME = "selfie_db";
	private static final int DATABASE_VERSION = 1; 
	
	private static final String CREATE_STATEMENT = 
			"CREATE TABLE "+DailySelfieContract.SELFIE_TABLE_NAME+ " ("+
			DailySelfieContract.SELFIE_COLUMN_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +
			DailySelfieContract.SELFIE_COLUMN_NAME+ " TEXT, "+
			DailySelfieContract.SELFIE_COLUMN_PATH+ " TEXT, "+
			DailySelfieContract.SELFIE_COLUMN_THUMB+ " TEXT" +
			")";
			
	
	
	public DailySelfieDatabase(Context context) {
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(CREATE_STATEMENT);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS "+DailySelfieContract.SELFIE_TABLE_NAME);
		onCreate(db);
	}

}
