package com.mht.stock.util;

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
import android.os.IBinder;
import android.support.design.widget.Snackbar;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

/**
 * Created by mht on 2016/3/22.
 */
public class CommonUtils {

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

    public static int getStatusBarHight(Activity activity) {
        Rect frame = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(frame);
        return frame.top;
    }

    public static int getStatusBarHeight(Context context) {
        Resources resources = context.getResources();
        int resourceId = resources .getIdentifier("status_bar_height", "dimen", "android");
        return resources.getDimensionPixelSize(resourceId);
    }

    public static void installApk(Context context, String path) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.parse("file://" + path),
                "application/vnd.android.package-archive");
        context.startActivity(intent);
    }

    public static void openUrl(Context context, String url) {
        if (url != null && !url.startsWith("http://")) {
            url = "http://" + url;
        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
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

    public static String getVersionName(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static int getVersionCode(Context context) {
        PackageManager packageManager = context.getPackageManager();
        try {
            PackageInfo packInfo = packageManager.getPackageInfo(context.getPackageName(), 0);
            return packInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return -1;
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

    public static void callPhone(Context context, String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNum));
        context.startActivity(intent);
    }
}
