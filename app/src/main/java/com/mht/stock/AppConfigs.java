package com.mht.stock;

import android.content.Context;

import com.mht.stock.storage.UserStorage;

public class AppConfigs {

	private static AppConfigs singleton;

	private String mHost;
	private String mWebHost;
	private String mChannel;
	private boolean mLogcat;
	private boolean mLogcatToFile;

	private AppConfigs() {
	}

	public static AppConfigs instance() {
		if(singleton == null) {
			synchronized (UserStorage.class) {
				if(singleton == null) {
					singleton = new AppConfigs();
				}
			}
		}
		return singleton;
	}

	public void init(Context context) {
		mChannel = context.getString(R.string.com_stack_channel);
		mHost = context.getString(R.string.com_stack_host);
		mWebHost = context.getString(R.string.com_stack_host_web);
		mLogcat = Boolean.parseBoolean(context.getString(R.string.logcat));
		mLogcatToFile = Boolean.parseBoolean(context.getString(R.string.logcat_to_file));
	}

	public String getHost() {
		return mHost;
	}

	public String getWebHost() {
		return mWebHost;
	}

	public String getUrl(String url) {
		return mHost + url;
	}

	public String getWebUrl(String url) {
		return mWebHost + url;
	}

	public String getChannel() {
		return mChannel;
	}

	public boolean isLogcat() {
		return mLogcat;
	}

	public boolean isLogcatToFile() {
		return mLogcatToFile;
	}
}
