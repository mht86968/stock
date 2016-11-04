package com.mht.stock.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.NetworkInfo.State;
import android.telephony.TelephonyManager;

/**
 * 网络判断
 * @author Administrator
 *
 */
public class NetworkUtils {

    public static final int NETWORK_TYPE_UNKNOWN = 0;
    public static final int NETWORK_TYPE_WIFI = 1;
    public static final int NETWORK_TYPE_2G = 2;
    public static final int NETWORK_TYPE_3G = 3;
    public static final int NETWORK_TYPE_4G = 4;
    
    /**
     * 判断网络是否连接上
     * @param context
     * @return
     */
    public static boolean isNetwork(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connManager.getActiveNetworkInfo();
        return (info != null && info.getState()==State.CONNECTED);
    }
    
    /**
     * 获取网络类型
     * @param context
     * @return
     */
    public static int getNetworkType(Context context) {
        ConnectivityManager connManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();
        if(networkInfo!=null) {
            int type = networkInfo.getType();
            if(type == ConnectivityManager.TYPE_WIFI) {
                return NETWORK_TYPE_WIFI;
            } else if(type == ConnectivityManager.TYPE_MOBILE) {
                switch (networkInfo.getSubtype()) {
                case TelephonyManager.NETWORK_TYPE_GPRS:
                case TelephonyManager.NETWORK_TYPE_EDGE:
                case TelephonyManager.NETWORK_TYPE_CDMA:
                case TelephonyManager.NETWORK_TYPE_1xRTT:
                case TelephonyManager.NETWORK_TYPE_IDEN:
                    return NETWORK_TYPE_2G;
                case TelephonyManager.NETWORK_TYPE_UMTS:
                case TelephonyManager.NETWORK_TYPE_EVDO_0:
                case TelephonyManager.NETWORK_TYPE_EVDO_A:
                case TelephonyManager.NETWORK_TYPE_HSDPA:
                case TelephonyManager.NETWORK_TYPE_HSUPA:
                case TelephonyManager.NETWORK_TYPE_HSPA:
                case TelephonyManager.NETWORK_TYPE_EVDO_B:
                case TelephonyManager.NETWORK_TYPE_EHRPD:
                case TelephonyManager.NETWORK_TYPE_HSPAP:
                    return NETWORK_TYPE_3G;
                case TelephonyManager.NETWORK_TYPE_LTE:
                    return NETWORK_TYPE_4G;
                }
            }
        }
        return NETWORK_TYPE_UNKNOWN;
    }
    
    /**
     * 获取网络名称
     * @param context
     * @return
     */
    public static String getNetworkTypeName(Context context) {
        int networkType = getNetworkType(context);
        if(networkType == NETWORK_TYPE_WIFI) {
            return "WIFI";
        } else if(networkType == NETWORK_TYPE_2G) {
            return "2G";
        } else if(networkType == NETWORK_TYPE_3G) {
            return "3G";
        } else if(networkType == NETWORK_TYPE_4G) {
            return "4G";
        }
        return "UNKNOWN";
    }
}