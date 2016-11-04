package com.mht.stock.storage;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * 用户数据
 */
public class UserStorage {

    private static UserStorage sUserStorage;
    private SharedPreferences mPreferences;

    private UserStorage() {
    }

    public static void init(Context context) {
        sUserStorage = new UserStorage();
        sUserStorage._init(context);
    }

    private void _init(Context context) {
        mPreferences = context.getSharedPreferences("user_preferences", Context.MODE_PRIVATE);
    }

    public void login() {
    }

    public void logout() {
    }
}
