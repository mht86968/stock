package com.mht.stock;

import android.app.Application;

import com.mht.stock.storage.UserStorage;

/**
 * Created by mht on 2016/4/8.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        AppConfigs.init(this);
        UserStorage.init(this);
    }
}
