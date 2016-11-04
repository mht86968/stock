package com.mht.stock.util;

import android.annotation.TargetApi;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.widget.TextView;
import android.widget.ZoomButtonsController;

import java.lang.reflect.Field;

public class ViewUtils {
	private static final String TAG = "ViewUtil";

	private ViewUtils() {
	};

	/**
	 * 截屏
	 * @param view
	 * @return
	 */
	public static Bitmap viewToBitmap(View view) {
		Bitmap bitmap = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
		view.draw(new Canvas(bitmap));
		return bitmap;
	}

	// 去掉ZoomControl 放大缩小按钮
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	public static void setZoomControlGone(WebView view) {
		if (Utils.hasHoneycomb()) {
			view.getSettings().setDisplayZoomControls(false);
		} else {
			try {
				Class<WebView> classType = WebView.class;
				Field field = classType.getDeclaredField("mZoomButtonsController");
				field.setAccessible(true);
				ZoomButtonsController mZoomButtonsController = new ZoomButtonsController(view);
				mZoomButtonsController.getZoomControls().setVisibility(View.GONE);
				field.set(view, mZoomButtonsController);
			} catch (Exception e) {
				Log.e(TAG, e.getMessage(), e);
			}
		}
	}

	public static void setCompoundDrawables(TextView textView, int leftResid, int topResid, int rightResid, int bottomResid) {
		Resources resources = textView.getResources();
		Drawable leftDrawable = null;
		Drawable topDrawable = null;
		Drawable rightDrawable = null;
		Drawable bottomDrawable = null;
		if(leftResid > 0) {
			leftDrawable = resources.getDrawable(leftResid);
			leftDrawable.setBounds(0, 0, leftDrawable.getIntrinsicWidth(), leftDrawable.getIntrinsicHeight());
		}
		if(topResid > 0) {
			topDrawable = resources.getDrawable(topResid);
			topDrawable.setBounds(0, 0, topDrawable.getIntrinsicWidth(), topDrawable.getIntrinsicHeight());
		}
		if(rightResid > 0) {
			rightDrawable = resources.getDrawable(rightResid);
			rightDrawable.setBounds(0, 0, rightDrawable.getIntrinsicWidth(), rightDrawable.getIntrinsicHeight());
		}
		if(bottomResid > 0) {
			bottomDrawable = resources.getDrawable(bottomResid);
			bottomDrawable.setBounds(0, 0, bottomDrawable.getIntrinsicWidth(), bottomDrawable.getIntrinsicHeight());
		}
		textView.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, topDrawable, rightDrawable, bottomDrawable);
	}
}
