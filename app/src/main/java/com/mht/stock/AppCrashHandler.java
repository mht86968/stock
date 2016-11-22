package com.mht.stock;

import android.content.Context;
import android.util.Log;

import com.mht.stock.activity.TransActivity;
import com.mht.stock.util.CommonUtils;
import com.mht.stock.util.DateUtils;
import com.mht.stock.util.FileUtils;
import com.mht.stock.util.IO;

import java.io.File;
import java.io.IOException;

/**
 * Created by JuQiu
 * on 2016/9/13.
 */

public class AppCrashHandler implements Thread.UncaughtExceptionHandler {
    public static final String TAG = "CrashHandler";

    private Thread.UncaughtExceptionHandler mDefaultHandler;
    private static AppCrashHandler INSTANCE = new AppCrashHandler();
    private Context mContext;

    private AppCrashHandler() {
    }

    public static AppCrashHandler getInstance() {
        return INSTANCE;
    }

    public void init(Context context) {
        mContext = context;
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            mDefaultHandler.uncaughtException(thread, ex);
        } else {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
    }

    private boolean handleException(final Throwable ex) {
        if (ex == null) {
            return false;
        }
        ex.printStackTrace();
        TransActivity.showDialogSystemError(mContext);

        File errorDir = FileUtils.getErrorCacheDir(mContext);
        if(errorDir != null) {
            try {
                final String errorInfo = String.format("手机型号：%s\nAndroid系统：Android %s\nApp版本号：%s\n\n%s",
                        android.os.Build.MODEL,
                        android.os.Build.VERSION.RELEASE,
                        CommonUtils.getVersionName(mContext),
                        Log.getStackTraceString(ex));

                IO.write(errorInfo, new File(errorDir, String.format("%s.log", DateUtils.getNowDate(DateUtils.FORMAT_YMDHMS))));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
