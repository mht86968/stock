package com.mht.stock;

import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Environment;

import com.mht.stock.util.FileUtils;
import com.mht.stock.util.IO;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * <pre>
 *     author: Blankj
 *     blog  : http://blankj.com
 *     time  : 2016/9/27
 *     desc  : 崩溃相关工具类
 * </pre>
 */
public class AppCrash implements Thread.UncaughtExceptionHandler {

    private volatile static AppCrash mInstance;

    private Thread.UncaughtExceptionHandler mHandler;

    private boolean mInitialized;
    private String  crashDir;
    private String  versionName;
    private int     versionCode;

    private AppCrash() {
    }

    /**
     * 获取单例
     * <p>在Application中初始化{@code CrashUtils.getInstance().init(this);}</p>
     * <p>需添加权限 {@code <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>}</p>
     *
     * @return 单例
     */
    public static AppCrash getInstance() {
        if (mInstance == null) {
            synchronized (AppCrash.class) {
                if (mInstance == null) {
                    mInstance = new AppCrash();
                }
            }
        }
        return mInstance;
    }

    /**
     * 初始化
     *
     * @return {@code true}: 成功<br>{@code false}: 失败
     */
    public boolean init() {
        if (mInitialized) return true;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            File baseCache = MyApplication.getApplication().getExternalCacheDir();
            if (baseCache == null) return false;
            crashDir = baseCache.getPath() + File.separator + "crash" + File.separator;
        } else {
            File baseCache = MyApplication.getApplication().getCacheDir();
            if (baseCache == null) return false;
            crashDir = baseCache.getPath() + File.separator + "crash" + File.separator;
        }
        try {
            PackageInfo pi = MyApplication.getApplication().getPackageManager().getPackageInfo(MyApplication.getApplication().getPackageName(), 0);
            versionName = pi.versionName;
            versionCode = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        mHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
        return mInitialized = true;
    }

    @Override
    public void uncaughtException(Thread thread, final Throwable throwable) {
        String now = new SimpleDateFormat("yy-MM-dd HH:mm:ss", Locale.getDefault()).format(new Date());
        final String fullPath = crashDir + now + ".txt";
        if (!FileUtils.createOrExistsFile(fullPath)) return;
        new Thread(new Runnable() {
            @Override
            public void run() {
                PrintWriter pw = null;
                try {
                    pw = new PrintWriter(new FileWriter(fullPath, false));
                    pw.write(getCrashHead());
                    throwable.printStackTrace(pw);
                    Throwable cause = throwable.getCause();
                    while (cause != null) {
                        cause.printStackTrace(pw);
                        cause = cause.getCause();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    IO.closeIO(pw);
                }
            }
        }).start();
        if (mHandler != null) {
            mHandler.uncaughtException(thread, throwable);
        }
    }

    /**
     * 获取崩溃头
     *
     * @return 崩溃头
     */
    private String getCrashHead() {
        return new StringBuffer("\n************* Crash Log Head ****************")
                .append("\nDevice Manufacturer: ").append(Build.MANUFACTURER)   //设备厂商
                .append("\nDevice Model       : ").append(Build.MODEL)          //设备型号
                .append("\nAndroid Version    : ").append(Build.VERSION.RELEASE)//系统版本
                .append("\nAndroid SDK        : ").append(Build.VERSION.SDK_INT)// SDK版本
                .append("\nApp VersionName    : ").append(versionName)
                .append("\nApp VersionCode    : ").append(versionCode)
                .append("\n************* Crash Log Head ****************\n\n")
                .toString();
    }
}