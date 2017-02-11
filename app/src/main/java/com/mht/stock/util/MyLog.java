package com.mht.stock.util;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class MyLog {
	
	private static final String LOG_HEADE = "Stock_";
    private static boolean DEBUG = false;
    private static boolean LOG_TO_FILE = false;
    private static String dir;

    private static Executor mExecutor;

    public static void init(Context context) {
        DEBUG = true;
        LOG_TO_FILE = true;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            dir = context.getExternalCacheDir().getPath() + File.separator + "log" + File.separator;
        } else {
            dir = context.getCacheDir().getPath() + File.separator + "log" + File.separator;
        }

        if(DEBUG && LOG_TO_FILE) {
            mExecutor = Executors.newSingleThreadExecutor();
        }
    }


	public static final void d(String tag, Throwable tr) {
        doLog('d', tag, Log.getStackTraceString(tr), null);
    }

    public static final void v(String tag, Throwable tr) {
        doLog('v', tag, Log.getStackTraceString(tr), null);
    }

    public static final void i(String tag, Throwable tr) {
        doLog('i', tag, Log.getStackTraceString(tr), null);
    }

    public static final void e(String tag, Throwable tr) {
        doLog('e', tag, Log.getStackTraceString(tr), null);
    }

    public static final void w(String tag, Throwable tr) {
        doLog('w', tag, Log.getStackTraceString(tr), null);
    }

    public static final void d(String tag, String msg) {
        doLog('d', tag, msg, null);
    }

    public static final void v(String tag, String msg) {
        doLog('v', tag, msg, null);
    }

    public static final void i(String tag, String msg) {
        doLog('i', tag, msg, null);
    }

    public static final void e(String tag, String msg) {
        doLog('e', tag, msg, null);
    }

    public static final void w(String tag, String msg) {
        doLog('w', tag, msg, null);
    }

    public static final void d(String tag, String msg, Throwable tr) {
        doLog('d', tag, msg, tr);
    }

    public static final void v(String tag, String msg, Throwable tr) {
        doLog('v', tag, msg, tr);
    }

    public static final void i(String tag, String msg, Throwable tr) {
        doLog('i', tag, msg, tr);
    }

    public static final void e(String tag, String msg, Throwable tr) {
        doLog('e', tag, msg, tr);
    }

    public static final void w(String tag, String msg, Throwable tr) {
        doLog('w', tag, msg, tr);
    }

    private static final void doLog(char level, String tag, String msg, Throwable tr) {
        if (DEBUG && msg!=null) {
            msg = msg + '\n' + Log.getStackTraceString(tr);

            final int maxLen = 4000;
            for (int i = 0, len = msg.length(); i * maxLen < len; ++i) {
                String subMsg = msg.substring(i * maxLen, (i + 1) * maxLen < len ? (i + 1) * maxLen : len);
                switch (level) {
                    case Log.VERBOSE:
                        Log.v(LOG_HEADE + tag, subMsg);
                        break;
                    case Log.DEBUG:
                        Log.d(LOG_HEADE + tag, subMsg);
                        break;
                    case Log.INFO:
                        Log.i(LOG_HEADE + tag, subMsg);
                        break;
                    case Log.WARN:
                        Log.w(LOG_HEADE + tag, subMsg);
                        break;
                    case Log.ERROR:
                        Log.e(LOG_HEADE + tag, subMsg);
                        break;
                    default:
                        break;
                }
            }

            if(LOG_TO_FILE) {
                log2File(level, tag, msg, tr);
            }
        }
    }


    /**
     * 打开日志文件并写入日志
     *
     * @param level 日志类型
     * @param tag   标签
     * @param msg   信息
     **/
    private synchronized static void log2File(final char level, final String tag, final String msg, Throwable e) {
        Date now = new Date();
        String date = new SimpleDateFormat("MM-dd", Locale.getDefault()).format(now);
        final String fullPath = dir + date + ".txt";
        if (!FileUtils.createOrExistsFile(fullPath)) return;
        String time = new SimpleDateFormat("MM-dd HH:mm:ss.SSS", Locale.getDefault()).format(now);
        final String dateLogContent = new StringBuffer(time).append(":")
                .append(level).append(":")
                .append(tag).append(":")
                .append(msg).append("\n")
                .append(Log.getStackTraceString(e)).append("\n").toString();

        mExecutor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    IO.write(dateLogContent, new File(fullPath), true);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
