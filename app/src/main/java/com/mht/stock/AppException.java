package com.mht.stock;

import android.content.Context;

/**
 * Created by mht on 2016/5/17.
 */
public class AppException extends Exception {

    public static final int ERROR_NETWORK = 1;          //网络出错
    public static final int ERROR_HTTP_PARAMS = 2;      //http 参数错误

    private int errorCode;

    public AppException(int errorCode) {
        this.errorCode = errorCode;
    }

    public AppException(int errorCode, String detailMessage) {
        super(detailMessage);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public static String getErrorMessage(Context context, int errorCode) {
        if(ERROR_NETWORK == errorCode) {
            return "网络出错，请检测当前网络的状态！";
        } else if(ERROR_HTTP_PARAMS == errorCode) {
            return "网络请求出错，请重新再试！";
        } else {
            return "网络请求出错，请重新再试！";
        }
    }
}
