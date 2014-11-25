package com.cousera.dailyselfie;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cousera.dailyselfie.provider.DailySelfieContract;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * The adapter for the DailySelfie class
 * Refer to ContentProvider lab
 * @author WilliamWei
 *
 */
public class DailySelfieAdapter extends CursorAdapter {
	private static final String TAG = "SelfieAdapter";
	
	private static LayoutInflater sLayoutInflater = null;
	private List<DailySelfie> mSelfies = new ArrayList<DailySelfie>();
	private Context mContext;
	
	//Cache for bitmaps
	private Map<String,Bitmap> mBitmaps = new HashMap<String,Bitmap>();
	
	static class ViewHolder {
		ImageView image;
		TextView name;
	}
	
	public DailySelfieAdapter(Context context) {
		super(context,null,0);
		mContext = context;
		sLayoutInflater = LayoutInflater.from(mContext);

		BitmapUtil.initStoragePath(mContext);
	}
	
	@Override
	public Object getItem(int position) {
		Log.d(TAG,"requesting selfie at position "+position);
		return mSelfies.get(position);
	}
	
	@Override
	public Cursor swapCursor(Cursor newCursor) {
		Cursor oldCursor = super.swapCursor(newCursor);
		mSelfies.clear();
		if (newCursor !=null) {
			newCursor.moveToFirst();
			while(!newCursor.isAfterLast()) {
				DailySelfie selfie = DailySelfie.fromCursor(newCursor);
				mSelfies.add(selfie);
				newCursor.moveToNext();
			}
		}
		
		return oldCursor;
	}
	
	public void addSelfie(DailySelfie selfie) {
	
		mSelfies.add(selfie);
		
		ContentValues values = new ContentValues();
		
		values.put(DailySelfieContract.SELFIE_COLUMN_NAME, selfie.getName());
		values.put(DailySelfieContract.SELFIE_COLUMN_PATH, selfie.getPath());
		values.put(DailySelfieContract.SELFIE_COLUMN_THUMB, selfie.getThumbPath());
		Log.i(TAG,"added selfie "+selfie.getName()+" at position"+(mSelfies.size()-1));
		Log.d(TAG,"selfie at "+mSelfies.get(mSelfies.size()-1)+" is "+mSelfies.get(mSelfies.size()-1).getName());
		
		mContext.getContentResolver().insert(DailySelfieContract.SELFIE_URI,values);
	
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder)view.getTag();
		Bitmap bitmap = null;
		String path = cursor.getString(cursor.getColumnIndex(DailySelfieContract.SELFIE_COLUMN_THUMB));
		if (mBitmaps.containsKey(path)) {
			bitmap = mBitmaps.get(path);
		} else {
			bitmap = BitmapUtil.getBitmapFromFile(path);
			mBitmaps.put(path, bitmap);
		}
		holder.image.setImageBitmap(bitmap);
		holder.name.setText(
				cursor.getString(cursor.getColumnIndex(DailySelfieContract.SELFIE_COLUMN_NAME)));
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		View newView;
		ViewHolder holder = new ViewHolder();

		newView = sLayoutInflater.inflate(R.layout.selfie_item_view, parent,
				false);
		holder.image = (ImageView) newView.findViewById(R.id.selfie_bitmap);
		holder.name = (TextView) newView.findViewById(R.id.selfie_name);
		
		newView.setTag(holder);

		return newView;
	}
	
	
	

}
