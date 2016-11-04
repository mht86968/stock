package com.mht.stock.http;

import com.mht.stock.http.listener.HttpListener;

import java.util.ArrayList;

import okhttp3.Request;

/**
 * Created by mht on 2016/6/18.
 */
public class Http {

    private ArrayList<HttpListener> mHttpListeners;

    public Http setTimeout(long timeout) {
        return this;
    }

    public Http setHttps() {
        return this;
    }

    public Http addHttpListener(HttpListener httpListener) {
        if(mHttpListeners == null) {
            mHttpListeners = new ArrayList<>();
        }
        mHttpListeners.add(httpListener);
        return this;
    }

    public Http request(Request request) {
        return this;
    }

    public Http execute() {
        return this;
    }

    public void cancel() {
    }
}
