package com.mht.stock.util;

import android.content.Context;

import com.mht.stock.R;

public class Configs {

	protected static Configs sConfigs;

	private String mHost;
	private String mChannel;
	private boolean mLogcat;

	public static Configs instance() {
		return sConfigs;
	}

	public static void init(Context context){
		sConfigs = new Configs();
		sConfigs.inter_init(context);		
	}

	private void inter_init(Context context){
		mChannel = context.getString(R.string.com_stack_channel);
		mHost = context.getString(R.string.com_stack_host);
		mLogcat = Boolean.parseBoolean(context.getString(R.string.logcat));
	}

	public String getHost() {
		return mHost;
	}

	public String getChannel() {
		return mChannel;
	}

	public boolean isLogcat() {
		return mLogcat;
	}
}
