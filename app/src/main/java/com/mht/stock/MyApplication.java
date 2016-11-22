package com.mht.stock;

import android.app.Application;

import com.mht.stock.storage.UserStorage;
import com.mht.stock.util.MyLog;

/**
 * Created by mht on 2016/4/8.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppCrashHandler handler = AppCrashHandler.getInstance();
        handler.init(this);

//        MyLog.DEBUG = AppConfigs.instance().isLogcat();
        MyLog.DEBUG = true;
        AppConfigs.init(this);

        UserStorage.init(this);
    }

    public void login() {
    }

    public void logout() {
    }

    public boolean isLogin() {
        return true;
    }

    public void toMainActivity() {
    }
}
