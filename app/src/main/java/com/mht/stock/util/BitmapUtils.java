package com.mht.stock.util;

import android.content.ContentResolver;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils {

	/**
	 * 保存图片
	 * @param bitmap
	 * @param file
	 * @throws IOException
     */
	public static void saveBitmap(Bitmap bitmap, File file) throws IOException {
		FileOutputStream out = new FileOutputStream(file);
		bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
		out.flush();
		out.close();
	}

	/**
	 * 创建圆形图片
	 * @param w
	 * @param h
	 * @param color
     * @return
     */
	public static Bitmap createOvalBitmap(int w, int h, int color) {
		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setColor(color);
		c.drawOval(new RectF(0, 0, w, h), p);
		return bm;
	}

	/**
	 * 创建方形图片
	 * @param w
	 * @param h
	 * @param color
     * @return
     */
	public static Bitmap createRectBitmap(int w, int h, int color) {
		Bitmap bm = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
		Canvas c = new Canvas(bm);
		Paint p = new Paint(Paint.ANTI_ALIAS_FLAG);
		p.setColor(color);
		c.drawRect(new RectF(0, 0, w, h), p);
		return bm;
	}

	public static String bitmapToBase64(Bitmap bitmap) throws IOException {
		String result = null;
		ByteArrayOutputStream baos = null;
		try {
			baos = new ByteArrayOutputStream();
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
			baos.flush();
			baos.close();
			byte[] bitmapBytes = baos.toByteArray();
			result = Base64.encodeToString(bitmapBytes, Base64.DEFAULT);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			baos.flush();
			baos.close();
		}
		return result;
	}

	/**
	 * 创建二维码
	 * @param large
	 * @param str
	 * @return
	 */
//	public static Bitmap createQRCodeBitmap(int large, String str)
//			throws WriterException {
//		BitMatrix matrix = new QRCodeWriter().encode(str,
//				BarcodeFormat.QR_CODE, large, large);
//		int width = matrix.getWidth();
//		int height = matrix.getHeight();
//		int[] pixels = new int[width * height];
//		for (int y = 0; y < width; ++y) {
//			for (int x = 0; x < height; ++x) {
//				if (matrix.get(x, y)) {
//					pixels[y * width + x] = 0xff000000; // black pixel
//				} else {
//					pixels[y * width + x] = 0xffffffff; // white pixel
//				}
//			}
//		}
//		Bitmap bmp = Bitmap
//				.createBitmap(width, height, Bitmap.Config.ARGB_8888);
//		bmp.setPixels(pixels, 0, width, 0, 0, width, height);
//		return bmp;
//	}

	/**
	 * 创建圆角图片
	 * @param bitmap
	 * @param roundPx
	 * @return
	 */
	public static Bitmap getRoundedCorner(Bitmap bitmap, float roundPx) {
		Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
				bitmap.getHeight(), Config.ARGB_8888);
		Canvas canvas = new Canvas(output);

		final int color = 0xff424242;
		final Paint paint = new Paint();
		final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
		final RectF rectF = new RectF(rect);

		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		paint.setColor(color);
		canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
		paint.setXfermode(new PorterDuffXfermode(Mode.SRC_IN));
		canvas.drawBitmap(bitmap, rect, rect, paint);
		return output;
	}

	/**
	 * 获取bitmap大小
	 * @param bitmap
	 * @return
	 */
	public static int getBitmapSize(Bitmap bitmap) {
		return bitmap.getRowBytes() * bitmap.getHeight();
	}

	/**
	 * 获取圆形图片
	 * @param bitmap
	 * @return
	 */
	public static Bitmap getRoundBitmap(Bitmap bitmap) {
		int imgW = bitmap.getWidth();
		int imgH = bitmap.getHeight();
		int radius = 0;
		float left = 0;
		float top = 0;
		if (imgW < imgH) {
			radius = imgW;
			top = (float) (imgW - imgH) / 2;
		} else {
			radius = imgH;
			left = (float) (imgH - imgW) / 2;
		}

		Bitmap roundBit = createOvalBitmap(radius, radius, 0xFF66AAFF);
		Paint paint = new Paint();
		paint.setFilterBitmap(false);
		paint.setStyle(Paint.Style.STROKE);
		paint.setShader(null);
		paint.setStyle(Paint.Style.FILL);

		Canvas c = new Canvas(roundBit);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		c.drawBitmap(bitmap, left, top, paint);
		paint.setXfermode(null);
		return roundBit;
	}

	public static Bitmap decodeSampledBitmapFromStream(ContentResolver cr, Uri uri, int reqWidth, int reqHeight) throws IOException {
		InputStream input1 = cr.openInputStream(uri);
		InputStream input2 = cr.openInputStream(uri);
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeStream(input1, null, options);
		input1.close();

		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		Bitmap bitmap = BitmapFactory.decodeStream(input2, null, options);
		input2.close();
		return bitmap;
	}

	public static Bitmap decodeSampledBitmapFromFile(String filename, int reqWidth, int reqHeight) {
		final BitmapFactory.Options options = new BitmapFactory.Options();
		options.inJustDecodeBounds = true;
		BitmapFactory.decodeFile(filename, options);

		options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
		options.inJustDecodeBounds = false;
		return BitmapFactory.decodeFile(filename, options);
	}

	private static int calculateInSampleSize(BitmapFactory.Options options,
			int reqWidth, int reqHeight) {
		final int height = options.outHeight;
		final int width = options.outWidth;
		int inSampleSize = 1;

		if (height > reqHeight || width > reqWidth) {
			if (width > height) {
				inSampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				inSampleSize = Math.round((float) width / (float) reqWidth);
			}
			final float totalPixels = width * height;
			final float totalReqPixelsCap = reqWidth * reqHeight * 2;

			while (totalPixels / (inSampleSize * inSampleSize) > totalReqPixelsCap) {
				inSampleSize++;
			}
		}
		return inSampleSize;
	}
}
