package com.mht.stock;

import android.app.Application;

import com.mht.stock.storage.UserStorage;
import com.mht.stock.util.MyLog;

/**
 * Created by mht on 2016/4/8.
 */
public class MyApplication extends Application {

    private static MyApplication mApplication;

    @Override
    public void onCreate() {
        super.onCreate();

        mApplication = this;

        AppCrash.getInstance().init();

        MyLog.init(this);
        AppConfigs.init(this);

        UserStorage.init(this);
    }

    public static MyApplication getApplication() {
        return mApplication;
    }
}
