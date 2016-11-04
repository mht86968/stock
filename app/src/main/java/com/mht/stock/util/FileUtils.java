package com.mht.stock.util;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtils {

	/**
	 * 获取sdcard 根目录
	 * @return
	 */
	public static String getSDcardRootPath() {
		return Environment.getExternalStorageDirectory().getAbsolutePath();
	}

	/**
	 * 判断是否有SDcard
	 * @return
	 */
	public static boolean isAvailableSdcard() {
		return Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState());
	}

	/**
	 * 获取缓存路径
	 * @param context
	 * @return
	 */
	public static File getExternalCacheDir(Context context) {
		File file = context.getExternalCacheDir();
		if(file == null) {
			file = new File("/sdcard/Android/data/"+context.getPackageName()+"/cache");
		}
		return file;
	}

	/**
	 * 返回可用容量   单位byte
	 * @param path
	 * @return
	 */
	public static long getUsableSpace(File path) {
		if (Utils.hasGingerbread()) {
			return path.getUsableSpace();
		} else {
			final StatFs stats = new StatFs(path.getPath());
			return (long) stats.getBlockSize() * (long) stats.getAvailableBlocks();
		}
	}



	/**
	 * 获取App缓存路径
	 * @param context
	 * @return
     */
	public static File getCacheDir(Context context) {
		if (isAvailableSdcard()) {
			return getExternalCacheDir(context);
		} else {
			return context.getCacheDir();
		}
	}

	public static File getDownloadCacheDir(Context context) {
		File f = new File(getCacheDir(context), "download");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f;
	}

	public static File getImageCacheDir(Context context) {
		File f = new File(getCacheDir(context), "images");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f;
	}

	public static File getUpdateCacheDir(Context context) {
		File f = new File(getCacheDir(context), "update");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f;
	}

	public static boolean createFile(File file) throws IOException {
		File parentFile = file.getParentFile();
		if (!parentFile.exists()) {
			parentFile.mkdirs();
		}
		if (!file.exists()) {
			return file.createNewFile();
		} else {
			return true;
		}
	}

	public static long getFileSize(File file) throws IOException {
		return new FileInputStream(file).available();
	}
}