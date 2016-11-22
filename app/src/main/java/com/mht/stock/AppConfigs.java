package com.mht.stock;

import android.content.Context;
import android.os.Environment;

import java.io.File;

public class AppConfigs {

	protected static AppConfigs sConfigs;

	private String mHost;
	private String mChannel;
	private boolean mLogcat;

	public static AppConfigs instance() {
		return sConfigs;
	}

	public static void init(Context context){
		sConfigs = new AppConfigs();
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



    public String getSaveImagePath() {
        return Environment
                .getExternalStorageDirectory()
                + File.separator
                + "stock"
                + File.separator + "images" + File.separator;
    }

    public String getSaveFilePath() {
        return Environment
                .getExternalStorageDirectory()
                + File.separator
                + "stock"
                + File.separator + "file" + File.separator;
    }
}
