package com.mht.stock;

import com.mht.stock.storage.UserStorage;
import com.mht.stock.util.SDCardUtils;

import java.io.File;

/**
 * Created by mht on 2017/3/1.
 */

public class AppCache {

    private static AppCache singleton;
    private File mAppCacheDir;

    private AppCache() {
    }

    public static AppCache instance() {
        if(singleton == null) {
            synchronized (UserStorage.class) {
                if(singleton == null) {
                    singleton = new AppCache();
                    singleton.init();
                }
            }
        }
        return singleton;
    }

    private void init() {
        if(SDCardUtils.isSDCardEnable()) {
            mAppCacheDir = MyApplication.getApplication().getExternalCacheDir();
        } else {
            mAppCacheDir = MyApplication.getApplication().getCacheDir();
        }
    }


    public File getAppCache() {
        return mAppCacheDir;
    }

    public File getAppCache(String path) {
        return new File(getAppCache(), path);
    }


    public File getImageCache() {
        return getAppCache("image");
    }

    public File getFileCache() {
        return getAppCache("file");
    }

    public File getLogCache() {
        return getAppCache("log");
    }

    public File getCasheCache() {
        return getAppCache("crash");
    }
}
