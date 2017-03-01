package com.mht.stock;

import android.app.Application;

import com.mht.stock.util.MyLog;

/**
 * Created by mht on 2016/4/8.
 */
public class MyApplication extends Application {

    private static MyApplication singleton;

    public static MyApplication getApplication() {
        return singleton;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        singleton = this;

        AppConfigs.instance().init(this);
        AppCrash.instance().init(AppCache.instance());
        MyLog.init(AppCache.instance(), AppConfigs.instance());
    }
}
