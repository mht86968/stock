package com.mht.stock.app;

import android.content.Context;

import com.mht.stock.storage.dao.StockDao;


/**
 * Created by mht on 2016/4/10.
 */
public class HttpMethods {
    public static final String TAG = "HttpMethods";

    private static HttpMethods mInstance;

    public static HttpMethods getInstance(Context context) {
        if(mInstance == null) {
            mInstance = new HttpMethods(context);
        }
        return mInstance;
    }

    private Context mContext;
    private StockDao mStockDao;

    private HttpMethods(Context context) {
        mContext = context;
        mStockDao = new StockDao(context);
    }
}
