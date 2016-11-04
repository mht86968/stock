package com.mht.stock.util;

import android.util.Log;

public class MyLog {
	
	private static final String LOG_HEADE = "Stock_";
	public static boolean DEBUG = false;

	public static final void d(String tag, Throwable e) {
        doLog(Log.DEBUG, tag, e.getMessage(), e);
    }

    public static final void v(String tag, Throwable e) {
        doLog(Log.VERBOSE, tag, e.getMessage(), e);
    }

    public static final void i(String tag, Throwable e) {
        doLog(Log.INFO, tag, e.getMessage(), e);
    }

    public static final void e(String tag, Throwable e) {
        doLog(Log.ERROR, tag, e.getMessage(), e);
    }

    public static final void w(String tag, Throwable e) {
        doLog(Log.WARN, tag, e.getMessage(), e);
    }

    public static final void d(String tag, String msg) {
        doLog(Log.DEBUG, tag, msg, null);
    }

    public static final void v(String tag, String msg) {
        doLog(Log.VERBOSE, tag, msg, null);
    }

    public static final void i(String tag, String msg) {
        doLog(Log.INFO, tag, msg, null);
    }

    public static final void e(String tag, String msg) {
        doLog(Log.ERROR, tag, msg, null);
    }

    public static final void w(String tag, String msg) {
        doLog(Log.WARN, tag, msg, null);
    }

    public static final void d(String tag, String msg, Throwable ex) {
        doLog(Log.DEBUG, tag, msg, ex);
    }

    public static final void v(String tag, String msg, Throwable ex) {
        doLog(Log.VERBOSE, tag, msg, ex);
    }

    public static final void i(String tag, String msg, Throwable ex) {
        doLog(Log.INFO, tag, msg, ex);
    }

    public static final void e(String tag, String msg, Throwable ex) {
        doLog(Log.ERROR, tag, msg, ex);
    }

    public static final void w(String tag, String msg, Throwable ex) {
        doLog(Log.WARN, tag, msg, ex);
    }

    private static final void doLog(int level, String tag, String msg, Throwable e) {
        if (DEBUG) {
            tag = LOG_HEADE + tag;
            switch (level) {
            case Log.VERBOSE:
                Log.v(tag, msg, e);
                break;
            case Log.DEBUG:
                Log.d(tag, msg, e);
                break;
            case Log.INFO:
                Log.i(tag, msg, e);
                break;
            case Log.WARN:
                Log.w(tag, msg, e);
                break;
            case Log.ERROR:
                Log.e(tag, msg, e);
                break;
            default:
                break;
            }
        }
    }
}
