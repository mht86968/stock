package com.mht.stock.util;

import android.content.Context;
import android.util.DisplayMetrics;

public class Screen {

	private static Screen mInstance;
	
	private int mScreenWidth;
	private int mScreenHeight;
	private float mDensity;
	
	public static Screen getInstance(Context context) {
		if(mInstance==null) {
			mInstance = new Screen(context);
		}
		return mInstance;
	}
	
	public Screen(Context context) {
		mDensity = context.getResources().getDisplayMetrics().density;
		DisplayMetrics dm = context.getResources().getDisplayMetrics();
		mScreenWidth = dm.widthPixels;
		mScreenHeight = dm.heightPixels;
	}
	
	public int dipToPx(float dip) {
        return (int) (dip * mDensity + 0.5f * (dip >= 0 ? 1 : -1));
	}
	
	public int pxToDip(int px) {
		return (int) (px / mDensity + 0.5f * (px >= 0 ? 1 : -1));
	}

	public int getScreenWidth() {
		return mScreenWidth;
	}

	public int getScreenHeight() {
		return mScreenHeight;
	}
}
