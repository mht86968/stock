package com.mht.stock.util;

import android.content.ContentResolver;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuff.Mode;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.ExifInterface;
import android.net.Uri;
import android.support.annotation.IntRange;
import android.util.Base64;
import android.view.View;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class BitmapUtils {

	/**
	 * bitmap转byteArr
	 *
	 * @param bitmap bitmap对象
	 * @param format 格式
	 * @return 字节数组
	 */
	public static byte[] bitmap2Bytes(Bitmap bitmap, Bitmap.CompressFormat format) {
		if (bitmap == null) return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		bitmap.compress(format, 100, baos);
		return baos.toByteArray();
	}

	/**
	 * byteArr转bitmap
	 *
	 * @param bytes 字节数组
	 * @return bitmap
	 */
	public static Bitmap bytes2Bitmap(byte[] bytes) {
		return (bytes == null || bytes.length == 0) ? null : BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * drawable转bitmap
	 *
	 * @param drawable drawable对象
	 * @return bitmap
	 */
	public static Bitmap drawable2Bitmap(Drawable drawable) {
		return drawable == null ? null : ((BitmapDrawable) drawable).getBitmap();
	}

	/**
	 * bitmap转drawable
	 *
	 * @param res    resources对象
	 * @param bitmap bitmap对象
	 * @return drawable
	 */
	public static Drawable bitmap2Drawable(Resources res, Bitmap bitmap) {
		return bitmap == null ? null : new BitmapDrawable(res, bitmap);
	}

	/**
	 * drawable转byteArr
	 *
	 * @param drawable drawable对象
	 * @param format   格式
	 * @return 字节数组
	 */
	public static byte[] drawable2Bytes(Drawable drawable, Bitmap.CompressFormat format) {
		return drawable == null ? null : bitmap2Bytes(drawable2Bitmap(drawable), format);
	}

	/**
	 * byteArr转drawable
	 *
	 * @param res   resources对象
	 * @param bytes 字节数组
	 * @return drawable
	 */
	public static Drawable bytes2Drawable(Resources res, byte[] bytes) {
		return res == null ? null : bitmap2Drawable(res, bytes2Bitmap(bytes));
	}

	public static String bitmap2Base64(Bitmap bitmap) throws IOException {
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
			IO.closeIO(baos);
		}
		return result;
	}


	/**
	 * view转Bitmap
	 *
	 * @param view 视图
	 * @return bitmap
	 */
	public static Bitmap view2Bitmap(View view) {
		if (view == null) return null;
		Bitmap ret = Bitmap.createBitmap(view.getWidth(), view.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(ret);
		Drawable bgDrawable = view.getBackground();
		if (bgDrawable != null) {
			bgDrawable.draw(canvas);
		} else {
			canvas.drawColor(Color.WHITE);
		}
		view.draw(canvas);
		return ret;
	}


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
		IO.closeIO(out);
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
	 * 转为圆角图片
	 *
	 * @param src    源图片
	 * @param radius 圆角的度数
	 * @return 圆角图片
	 */
	public static Bitmap toRoundCorner(Bitmap src, float radius) {
		return toRoundCorner(src, radius, false);
	}

	/**
	 * 转为圆角图片
	 *
	 * @param src     源图片
	 * @param radius  圆角的度数
	 * @param recycle 是否回收
	 * @return 圆角图片
	 */
	public static Bitmap toRoundCorner(Bitmap src, float radius, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		int width = src.getWidth();
		int height = src.getHeight();
		Bitmap ret = Bitmap.createBitmap(width, height, src.getConfig());
		Paint paint = new Paint();
		Canvas canvas = new Canvas(ret);
		Rect rect = new Rect(0, 0, width, height);
		paint.setAntiAlias(true);
		canvas.drawRoundRect(new RectF(rect), radius, radius, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(src, rect, rect, paint);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
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
	 * 转为圆形图片
	 *
	 * @param src 源图片
	 * @return 圆形图片
	 */
	public static Bitmap toRound(Bitmap src) {
		return toRound(src, false);
	}

	/**
	 * 转为圆形图片
	 *
	 * @param src     源图片
	 * @param recycle 是否回收
	 * @return 圆形图片
	 */
	public static Bitmap toRound(Bitmap src, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		int width = src.getWidth();
		int height = src.getHeight();
		int radius = Math.min(width, height) >> 1;
		Bitmap ret = Bitmap.createBitmap(width, height, src.getConfig());
		Paint paint = new Paint();
		Canvas canvas = new Canvas(ret);
		Rect rect = new Rect(0, 0, width, height);
		paint.setAntiAlias(true);
		canvas.drawARGB(0, 0, 0, 0);
		canvas.drawCircle(width >> 1, height >> 1, radius, paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(src, rect, rect, paint);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}




	/**
	 * 缩放图片
	 *
	 * @param src       源图片
	 * @param newWidth  新宽度
	 * @param newHeight 新高度
	 * @return 缩放后的图片
	 */
	public static Bitmap scale(Bitmap src, int newWidth, int newHeight) {
		return scale(src, newWidth, newHeight, false);
	}

	/**
	 * 缩放图片
	 *
	 * @param src       源图片
	 * @param newWidth  新宽度
	 * @param newHeight 新高度
	 * @param recycle   是否回收
	 * @return 缩放后的图片
	 */
	public static Bitmap scale(Bitmap src, int newWidth, int newHeight, boolean recycle) {
		if (src == null) return null;
		Bitmap ret = Bitmap.createScaledBitmap(src, newWidth, newHeight, true);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}

	/**
	 * 缩放图片
	 *
	 * @param src         源图片
	 * @param scaleWidth  缩放宽度倍数
	 * @param scaleHeight 缩放高度倍数
	 * @return 缩放后的图片
	 */
	public static Bitmap scale(Bitmap src, float scaleWidth, float scaleHeight) {
		return scale(src, scaleWidth, scaleHeight, false);
	}

	/**
	 * 缩放图片
	 *
	 * @param src         源图片
	 * @param scaleWidth  缩放宽度倍数
	 * @param scaleHeight 缩放高度倍数
	 * @param recycle     是否回收
	 * @return 缩放后的图片
	 */
	public static Bitmap scale(Bitmap src, float scaleWidth, float scaleHeight, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		Matrix matrix = new Matrix();
		matrix.setScale(scaleWidth, scaleHeight);
		Bitmap ret = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}

	/**
	 * 裁剪图片
	 *
	 * @param src    源图片
	 * @param x      开始坐标x
	 * @param y      开始坐标y
	 * @param width  裁剪宽度
	 * @param height 裁剪高度
	 * @return 裁剪后的图片
	 */
	public static Bitmap clip(Bitmap src, int x, int y, int width, int height) {
		return clip(src, x, y, width, height, false);
	}

	/**
	 * 裁剪图片
	 *
	 * @param src     源图片
	 * @param x       开始坐标x
	 * @param y       开始坐标y
	 * @param width   裁剪宽度
	 * @param height  裁剪高度
	 * @param recycle 是否回收
	 * @return 裁剪后的图片
	 */
	public static Bitmap clip(Bitmap src, int x, int y, int width, int height, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		Bitmap ret = Bitmap.createBitmap(src, x, y, width, height);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}

	/**
	 * 倾斜图片
	 *
	 * @param src 源图片
	 * @param kx  倾斜因子x
	 * @param ky  倾斜因子y
	 * @return 倾斜后的图片
	 */
	public static Bitmap skew(Bitmap src, float kx, float ky) {
		return skew(src, kx, ky, 0, 0, false);
	}

	/**
	 * 倾斜图片
	 *
	 * @param src     源图片
	 * @param kx      倾斜因子x
	 * @param ky      倾斜因子y
	 * @param recycle 是否回收
	 * @return 倾斜后的图片
	 */
	public static Bitmap skew(Bitmap src, float kx, float ky, boolean recycle) {
		return skew(src, kx, ky, 0, 0, recycle);
	}

	/**
	 * 倾斜图片
	 *
	 * @param src 源图片
	 * @param kx  倾斜因子x
	 * @param ky  倾斜因子y
	 * @param px  平移因子x
	 * @param py  平移因子y
	 * @return 倾斜后的图片
	 */
	public static Bitmap skew(Bitmap src, float kx, float ky, float px, float py) {
		return skew(src, kx, ky, px, py, false);
	}

	/**
	 * 倾斜图片
	 *
	 * @param src     源图片
	 * @param kx      倾斜因子x
	 * @param ky      倾斜因子y
	 * @param px      平移因子x
	 * @param py      平移因子y
	 * @param recycle 是否回收
	 * @return 倾斜后的图片
	 */
	public static Bitmap skew(Bitmap src, float kx, float ky, float px, float py, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		Matrix matrix = new Matrix();
		matrix.setSkew(kx, ky, px, py);
		Bitmap ret = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}

	/**
	 * 旋转图片
	 *
	 * @param src     源图片
	 * @param degrees 旋转角度
	 * @param px      旋转点横坐标
	 * @param py      旋转点纵坐标
	 * @return 旋转后的图片
	 */
	public static Bitmap rotate(Bitmap src, int degrees, float px, float py) {
		return rotate(src, degrees, px, py, false);
	}

	/**
	 * 旋转图片
	 *
	 * @param src     源图片
	 * @param degrees 旋转角度
	 * @param px      旋转点横坐标
	 * @param py      旋转点纵坐标
	 * @param recycle 是否回收
	 * @return 旋转后的图片
	 */
	public static Bitmap rotate(Bitmap src, int degrees, float px, float py, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		if (degrees == 0) return src;
		Matrix matrix = new Matrix();
		matrix.setRotate(degrees, px, py);
		Bitmap ret = Bitmap.createBitmap(src, 0, 0, src.getWidth(), src.getHeight(), matrix, true);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}

	/**
	 * 获取图片旋转角度
	 *
	 * @param filePath 文件路径
	 * @return 旋转角度
	 */
	public static int getRotateDegree(String filePath) {
		int degree = 0;
		try {
			ExifInterface exifInterface = new ExifInterface(filePath);
			int orientation = exifInterface.getAttributeInt(
					ExifInterface.TAG_ORIENTATION,
					ExifInterface.ORIENTATION_NORMAL);
			switch (orientation) {
				default:
				case ExifInterface.ORIENTATION_ROTATE_90:
					degree = 90;
					break;
				case ExifInterface.ORIENTATION_ROTATE_180:
					degree = 180;
					break;
				case ExifInterface.ORIENTATION_ROTATE_270:
					degree = 270;
					break;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return degree;
	}


	/**
	 * 添加颜色边框
	 *
	 * @param src         源图片
	 * @param borderWidth 边框宽度
	 * @param color       边框的颜色值
	 * @return 带颜色边框图
	 */
	public static Bitmap addFrame(Bitmap src, int borderWidth, int color) {
		return addFrame(src, borderWidth, color, false);
	}

	/**
	 * 添加颜色边框
	 *
	 * @param src         源图片
	 * @param borderWidth 边框宽度
	 * @param color       边框的颜色值
	 * @param recycle     是否回收
	 * @return 带颜色边框图
	 */
	public static Bitmap addFrame(Bitmap src, int borderWidth, int color, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		int doubleBorder = borderWidth << 1;
		int newWidth = src.getWidth() + doubleBorder;
		int newHeight = src.getHeight() + doubleBorder;
		Bitmap ret = Bitmap.createBitmap(newWidth, newHeight, src.getConfig());
		Canvas canvas = new Canvas(ret);
		Rect rect = new Rect(0, 0, newWidth, newHeight);
		Paint paint = new Paint();
		paint.setColor(color);
		paint.setStyle(Paint.Style.STROKE);
		// setStrokeWidth是居中画的，所以要两倍的宽度才能画，否则有一半的宽度是空的
		paint.setStrokeWidth(doubleBorder);
		canvas.drawRect(rect, paint);
		canvas.drawBitmap(src, borderWidth, borderWidth, null);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}

	/**
	 * 添加倒影
	 *
	 * @param src              源图片的
	 * @param reflectionHeight 倒影高度
	 * @return 带倒影图片
	 */
	public static Bitmap addReflection(Bitmap src, int reflectionHeight) {
		return addReflection(src, reflectionHeight, false);
	}

	/**
	 * 添加倒影
	 *
	 * @param src              源图片的
	 * @param reflectionHeight 倒影高度
	 * @param recycle          是否回收
	 * @return 带倒影图片
	 */
	public static Bitmap addReflection(Bitmap src, int reflectionHeight, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		// 原图与倒影之间的间距
		final int REFLECTION_GAP = 0;
		int srcWidth = src.getWidth();
		int srcHeight = src.getHeight();
		Matrix matrix = new Matrix();
		matrix.preScale(1, -1);
		Bitmap reflectionBitmap = Bitmap.createBitmap(src, 0, srcHeight - reflectionHeight,
				srcWidth, reflectionHeight, matrix, false);
		Bitmap ret = Bitmap.createBitmap(srcWidth, srcHeight + reflectionHeight, src.getConfig());
		Canvas canvas = new Canvas(ret);
		canvas.drawBitmap(src, 0, 0, null);
		canvas.drawBitmap(reflectionBitmap, 0, srcHeight + REFLECTION_GAP, null);
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		LinearGradient shader = new LinearGradient(0, srcHeight,
				0, ret.getHeight() + REFLECTION_GAP,
				0x70FFFFFF, 0x00FFFFFF, Shader.TileMode.MIRROR);
		paint.setShader(shader);
		paint.setXfermode(new PorterDuffXfermode(android.graphics.PorterDuff.Mode.DST_IN));
		canvas.drawRect(0, srcHeight + REFLECTION_GAP,
				srcWidth, ret.getHeight(), paint);
		if (!reflectionBitmap.isRecycled()) reflectionBitmap.recycle();
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}


	/**
	 * 添加文字水印
	 *
	 * @param src      源图片
	 * @param content  水印文本
	 * @param textSize 水印字体大小
	 * @param color    水印字体颜色
	 * @param x        起始坐标x
	 * @param y        起始坐标y
	 * @return 带有文字水印的图片
	 */
	public static Bitmap addTextWatermark(Bitmap src, String content, int textSize, int color, float x,
										  float y) {
		return addTextWatermark(src, content, textSize, color, x, y, false);
	}

	/**
	 * 添加文字水印
	 *
	 * @param src      源图片
	 * @param content  水印文本
	 * @param textSize 水印字体大小
	 * @param color    水印字体颜色
	 * @param x        起始坐标x
	 * @param y        起始坐标y
	 * @param recycle  是否回收
	 * @return 带有文字水印的图片
	 */
	public static Bitmap addTextWatermark(Bitmap src, String content, float textSize, int color, float x,
										  float y, boolean recycle) {
		if (isEmptyBitmap(src) || content == null) return null;
		Bitmap ret = src.copy(src.getConfig(), true);
		Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
		Canvas canvas = new Canvas(ret);
		paint.setColor(color);
		paint.setTextSize(textSize);
		Rect bounds = new Rect();
		paint.getTextBounds(content, 0, content.length(), bounds);
		canvas.drawText(content, x, y + textSize, paint);
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}

	/**
	 * 添加图片水印
	 *
	 * @param src       源图片
	 * @param watermark 图片水印
	 * @param x         起始坐标x
	 * @param y         起始坐标y
	 * @param alpha     透明度
	 * @return 带有图片水印的图片
	 */
	public static Bitmap addImageWatermark(Bitmap src, Bitmap watermark, int x, int y, int alpha) {
		return addImageWatermark(src, watermark, x, y, alpha, false);
	}

	/**
	 * 添加图片水印
	 *
	 * @param src       源图片
	 * @param watermark 图片水印
	 * @param x         起始坐标x
	 * @param y         起始坐标y
	 * @param alpha     透明度
	 * @param recycle   是否回收
	 * @return 带有图片水印的图片
	 */
	public static Bitmap addImageWatermark(Bitmap src, Bitmap watermark, int x, int y, int alpha, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		Bitmap ret = src.copy(src.getConfig(), true);
		if (!isEmptyBitmap(watermark)) {
			Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
			Canvas canvas = new Canvas(ret);
			paint.setAlpha(alpha);
			canvas.drawBitmap(watermark, x, y, paint);
		}
		if (recycle && !src.isRecycled()) src.recycle();
		return ret;
	}

	/**
	 * 根据文件名判断文件是否为图片
	 *
	 * @param file 　文件
	 * @return {@code true}: 是<br>{@code false}: 否
	 */
	public static boolean isImage(File file) {
		return file != null && isImage(file.getPath());
	}

	/**
	 * 根据文件名判断文件是否为图片
	 *
	 * @param filePath 　文件路径
	 * @return {@code true}: 是<br>{@code false}: 否
	 */
	public static boolean isImage(String filePath) {
		String path = filePath.toUpperCase();
		return path.endsWith(".PNG") || path.endsWith(".JPG")
				|| path.endsWith(".JPEG") || path.endsWith(".BMP")
				|| path.endsWith(".GIF");
	}

	/**
	 * 获取图片类型
	 *
	 * @param filePath 文件路径
	 * @return 图片类型
	 */
	public static String getImageType(String filePath) {
		return getImageType(FileUtils.getFileByPath(filePath));
	}

	/**
	 * 获取图片类型
	 *
	 * @param file 文件
	 * @return 图片类型
	 */
	public static String getImageType(File file) {
		if (file == null) return null;
		InputStream is = null;
		try {
			is = new FileInputStream(file);
			return getImageType(is);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} finally {
			IO.closeIO(is);
		}
	}

	/**
	 * 流获取图片类型
	 *
	 * @param is 图片输入流
	 * @return 图片类型
	 */
	public static String getImageType(InputStream is) {
		if (is == null) return null;
		try {
			byte[] bytes = new byte[8];
			return is.read(bytes, 0, 8) != -1 ? getImageType(bytes) : null;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 获取图片类型
	 *
	 * @param bytes bitmap的前8字节
	 * @return 图片类型
	 */
	public static String getImageType(byte[] bytes) {
		if (isJPEG(bytes)) return "JPEG";
		if (isGIF(bytes)) return "GIF";
		if (isPNG(bytes)) return "PNG";
		if (isBMP(bytes)) return "BMP";
		return null;
	}

	private static boolean isJPEG(byte[] b) {
		return b.length >= 2
				&& (b[0] == (byte) 0xFF) && (b[1] == (byte) 0xD8);
	}

	private static boolean isGIF(byte[] b) {
		return b.length >= 6
				&& b[0] == 'G' && b[1] == 'I'
				&& b[2] == 'F' && b[3] == '8'
				&& (b[4] == '7' || b[4] == '9') && b[5] == 'a';
	}

	private static boolean isPNG(byte[] b) {
		return b.length >= 8
				&& (b[0] == (byte) 137 && b[1] == (byte) 80
				&& b[2] == (byte) 78 && b[3] == (byte) 71
				&& b[4] == (byte) 13 && b[5] == (byte) 10
				&& b[6] == (byte) 26 && b[7] == (byte) 10);
	}

	private static boolean isBMP(byte[] b) {
		return b.length >= 2
				&& (b[0] == 0x42) && (b[1] == 0x4d);
	}



	/******************************~~~~~~~~~ 下方和压缩有关 ~~~~~~~~~******************************/

	/**
	 * 按缩放压缩
	 *
	 * @param src       源图片
	 * @param newWidth  新宽度
	 * @param newHeight 新高度
	 * @return 缩放压缩后的图片
	 */
	public static Bitmap compressByScale(Bitmap src, int newWidth, int newHeight) {
		return scale(src, newWidth, newHeight, false);
	}

	/**
	 * 按缩放压缩
	 *
	 * @param src       源图片
	 * @param newWidth  新宽度
	 * @param newHeight 新高度
	 * @param recycle   是否回收
	 * @return 缩放压缩后的图片
	 */
	public static Bitmap compressByScale(Bitmap src, int newWidth, int newHeight, boolean recycle) {
		return scale(src, newWidth, newHeight, recycle);
	}

	/**
	 * 按缩放压缩
	 *
	 * @param src         源图片
	 * @param scaleWidth  缩放宽度倍数
	 * @param scaleHeight 缩放高度倍数
	 * @return 缩放压缩后的图片
	 */
	public static Bitmap compressByScale(Bitmap src, float scaleWidth, float scaleHeight) {
		return scale(src, scaleWidth, scaleHeight, false);
	}

	/**
	 * 按缩放压缩
	 *
	 * @param src         源图片
	 * @param scaleWidth  缩放宽度倍数
	 * @param scaleHeight 缩放高度倍数
	 * @param recycle     是否回收
	 * @return 缩放压缩后的图片
	 */
	public static Bitmap compressByScale(Bitmap src, float scaleWidth, float scaleHeight, boolean recycle) {
		return scale(src, scaleWidth, scaleHeight, recycle);
	}

	/**
	 * 按质量压缩
	 *
	 * @param src     源图片
	 * @param quality 质量
	 * @return 质量压缩后的图片
	 */
	public static Bitmap compressByQuality(Bitmap src, @IntRange(from = 0, to = 100) int quality) {
		return compressByQuality(src, quality, false);
	}

	/**
	 * 按质量压缩
	 *
	 * @param src     源图片
	 * @param quality 质量
	 * @param recycle 是否回收
	 * @return 质量压缩后的图片
	 */
	public static Bitmap compressByQuality(Bitmap src, @IntRange(from = 0, to = 100) int quality, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		src.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		byte[] bytes = baos.toByteArray();
		if (recycle && !src.isRecycled()) src.recycle();
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * 按质量压缩
	 *
	 * @param src         源图片
	 * @param maxByteSize 允许最大值字节数
	 * @return 质量压缩压缩过的图片
	 */
	public static Bitmap compressByQuality(Bitmap src, long maxByteSize) {
		return compressByQuality(src, maxByteSize, false);
	}

	/**
	 * 按质量压缩
	 *
	 * @param src         源图片
	 * @param maxByteSize 允许最大值字节数
	 * @param recycle     是否回收
	 * @return 质量压缩压缩过的图片
	 */
	public static Bitmap compressByQuality(Bitmap src, long maxByteSize, boolean recycle) {
		if (isEmptyBitmap(src) || maxByteSize <= 0) return null;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int quality = 100;
		src.compress(Bitmap.CompressFormat.JPEG, quality, baos);
		while (baos.toByteArray().length > maxByteSize && quality > 0) {
			baos.reset();
			src.compress(Bitmap.CompressFormat.JPEG, quality -= 5, baos);
		}
		if (quality < 0) return null;
		byte[] bytes = baos.toByteArray();
		if (recycle && !src.isRecycled()) src.recycle();
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
	}

	/**
	 * 按采样大小压缩
	 *
	 * @param src        源图片
	 * @param sampleSize 采样率大小
	 * @return 按采样率压缩后的图片
	 */
	public static Bitmap compressBySampleSize(Bitmap src, int sampleSize) {
		return compressBySampleSize(src, sampleSize, false);
	}

	/**
	 * 按采样大小压缩
	 *
	 * @param src        源图片
	 * @param sampleSize 采样率大小
	 * @param recycle    是否回收
	 * @return 按采样率压缩后的图片
	 */
	public static Bitmap compressBySampleSize(Bitmap src, int sampleSize, boolean recycle) {
		if (isEmptyBitmap(src)) return null;
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = sampleSize;
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		src.compress(Bitmap.CompressFormat.JPEG, 100, baos);
		byte[] bytes = baos.toByteArray();
		if (recycle && !src.isRecycled()) src.recycle();
		return BitmapFactory.decodeByteArray(bytes, 0, bytes.length, options);
	}

	/**
	 * 判断bitmap对象是否为空
	 *
	 * @param src 源图片
	 * @return {@code true}: 是<br>{@code false}: 否
	 */
	private static boolean isEmptyBitmap(Bitmap src) {
		return src == null || src.getWidth() == 0 || src.getHeight() == 0;
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
