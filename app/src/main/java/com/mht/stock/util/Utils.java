/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.mht.stock.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.os.Build.VERSION_CODES;
import android.os.Bundle;

/**
 * Class containing some static utility methods.
 */
public class Utils {
	
    private Utils() {};

    //2.1以上
    public static boolean hasEclair() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.ECLAIR_MR1;
    }
    
    //2.2以上
    public static boolean hasFroyo() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.FROYO;
    }

    //2.3以上
    public static boolean hasGingerbread() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.GINGERBREAD;
    }
    
    //3.0以上
    public static boolean hasHoneycomb() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB;
    }
    
    //3.1以上
    public static boolean hasHoneycombMR1() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.HONEYCOMB_MR1;
    }

    //4.1以上
    public static boolean hasJellyBean() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN;
    }
    
    //4.2以上
    public static boolean hasJellyBeanMR1() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR1;
    }
    
    //4.3以上
    public static boolean hasJellyBeanMR2() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.JELLY_BEAN_MR2;
    }
    
    //4.4以上
    public static boolean hasKitKat() {
        return Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT;
    }
    
    // 获取ApiKey
    public static String getMetaValue(Context context, String meta) {
        if (context == null || meta == null) {
            return null;
        }
        Bundle metaData = null;
        String apiKey = null;
        try {
            ApplicationInfo ai = context.getPackageManager().getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);
            if (null != ai) {
                metaData = ai.metaData;
            }
            if (null != metaData) {
                apiKey = metaData.getString(meta);
            }
        } catch (NameNotFoundException e) {
        }
        return apiKey;
    }
}
