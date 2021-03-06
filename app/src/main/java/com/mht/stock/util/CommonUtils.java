package com.mht.stock.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Resources;
import android.graphics.Rect;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.mht.stock.MyApplication;

import java.net.NetworkInterface;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by mht on 2016/3/22.
 */
public class CommonUtils {

    public static boolean isChinese(String str){
        Pattern p = Pattern.compile("^[\u4e00-\u9fa5]{2,6}$");
        Matcher m = p.matcher(str);
        if(m.find()) {
            return true;
        } else {
            return false;
        }
    }

    public static String getHttpUrl(String url) {
        if(url == null || url.startsWith("http")) {
            return url;
        } else {
            return "http://" + url;
        }
    }

    public static Toast showToast(Context context, String text) {
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    public static Toast showToast(Context context, int resId) {
        Toast toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        toast.show();
        return toast;
    }

    public static void cancelToast(Toast toast) {
        if(toast!=null) {
            toast.cancel();
        }
    }

    public static Snackbar showSnackbar(View view, String text) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(null, null);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar showSnackbar(View view, int resId) {
        Snackbar snackbar = Snackbar.make(view, resId, Snackbar.LENGTH_LONG).setAction(null, null);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar showSnackbar(View view, String text, String action, View.OnClickListener l) {
        Snackbar snackbar = Snackbar.make(view, text, Snackbar.LENGTH_LONG).setAction(action, l);
        snackbar.show();
        return snackbar;
    }

    public static Snackbar showSnackbar(View view, int resId, String action, View.OnClickListener l) {
        Snackbar snackbar = Snackbar.make(view, resId, Snackbar.LENGTH_LONG).setAction(action, l);
        snackbar.show();
        return snackbar;
    }

    public static void cancelToast(Snackbar snackbar) {
        if(snackbar!=null) {
            snackbar.dismiss();
        }
    }


    public static void openUrl(Context context, String url) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(getHttpUrl(url)));
        context.startActivity(intent);
    }

    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        context.startActivity(intent);
    }

    public static void playNotifyRing(Context context) {
        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        if(notification != null) {
            Ringtone r = RingtoneManager.getRingtone(context, notification);
            if(r != null) {
                r.play();
            }
        }
    }

    /**
     * 关闭软键盘
     */
    public static void closeSoftInput(Activity activity) {
        View currentFocus = activity.getCurrentFocus();
        if (currentFocus != null) {
            IBinder iBinder = currentFocus.getApplicationWindowToken();
            if (iBinder != null) {
                InputMethodManager im = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
                im.hideSoftInputFromWindow(iBinder, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    /**
     * 获取设备唯一编号
     *
     * @param context
     * @return
     */
    public static String getDeviceId(Context context) {
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        return tm.getDeviceId();
    }

}
