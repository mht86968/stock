package com.mht.stock;

import android.app.Application;

import com.mht.stock.app.data.UserStorage;
import com.mht.stock.util.Configs;

/**
 * Created by mht on 2016/4/8.
 */
public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Configs.init(this);
        UserStorage.init(this);
    }
}
