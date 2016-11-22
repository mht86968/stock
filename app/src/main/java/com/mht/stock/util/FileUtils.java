package com.mht.stock.util;

import android.content.Context;
import android.os.Environment;
import android.os.StatFs;

import java.io.File;
import java.io.FileInputStream;
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
	 * 获取外置缓存路径
	 * @param context
	 * @return
	 */
	public static File getExternalCacheDir(Context context) {
		File file = context.getExternalCacheDir();
		if(file == null) {
			file = new File(String.format("/sdcard/Android/data/%s/cache", context.getPackageName()));
		}
		return file;
	}

    /**
     * 获取外置缓存路径
     * 一般放一些长时间保存的数据
     * @param context
     * @return
     */
    public static File getExternalFilesDir(Context context, String type) {
        File file = context.getExternalFilesDir(type);
        if(file == null) {
            file = new File(String.format("/sdcard/Android/data/%s/files", context.getPackageName()));
        }
        return file;
    }

    /**
     * 获取内置缓存路径
     * @param context
     * @return
     */
    public static File getCacheDir(Context context) {
        File file = context.getCacheDir();
        if(file == null) {
            file = new File(String.format("/data/data/%s/cache", context.getPackageName()));
        }
        return file;
    }

    /**
     * 获取内置缓存路径
     * @param context
     * @return
     */
    public static File getFilesDir(Context context) {
        File file = context.getFilesDir();
        if(file == null) {
            file = new File(String.format("/data/data/%s/files", context.getPackageName()));
        }
        return file;
    }





    
	/**
     * 获取App缓存根目录
     * @param context
     * @return
     */
    public static File getAppCacheDir(Context context) {
        if (isAvailableSdcard()) {
            return getExternalCacheDir(context);
        } else {
            return getCacheDir(context);
        }
    }

	public static File getDownloadCacheDir(Context context) {
		File f = new File(getAppCacheDir(context), "download");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f;
	}

	public static File getImageCacheDir(Context context) {
		File f = new File(getAppCacheDir(context), "images");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f;
	}

	public static File getUpdateCacheDir(Context context) {
		File f = new File(getAppCacheDir(context), "update");
		if (!f.exists()) {
			f.mkdirs();
		}
		return f;
	}




    /**
     * 创建目录
     * @param file
     * @return
     * @throws IOException
     */
    public static boolean createDir(File file) throws IOException {
        return file.mkdirs();
    }

	public static boolean createFile(File file) throws IOException {
        createDir(file.getParentFile());
		if (!file.exists()) {
			return file.createNewFile();
		} else {
			return true;
		}
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

	public static long getFileSize(File file) throws IOException {
		return new FileInputStream(file).available();
	}
}