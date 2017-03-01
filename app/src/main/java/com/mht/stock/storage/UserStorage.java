package com.mht.stock.storage;

import android.content.Context;

import com.mht.stock.MyApplication;
import com.mht.stock.storage.util.PreferencesUtils;

/**
 * 用户数据
 */
public class UserStorage {

    private static UserStorage singleton;
    private PreferencesUtils mPreferences;

    private UserStorage() {
    }

    public static UserStorage instance() {
        if(singleton == null) {
            synchronized (UserStorage.class) {
                if(singleton == null) {
                    singleton = new UserStorage();
                    singleton.init(MyApplication.getApplication());
                }
            }
        }
        return singleton;
    }

    private void init(Context context) {
        mPreferences = new PreferencesUtils(context, "user_preferences");
    }

    public void login() {
    }

    public void logout() {
    }
}
