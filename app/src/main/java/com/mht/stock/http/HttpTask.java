package com.mht.stock.http;

import com.mht.stock.http.listener.HttpListener;

import java.util.ArrayList;

import okhttp3.Request;

/**
 * Created by mht on 2016/6/18.
 */
public class HttpTask {

    private ArrayList<HttpListener> mHttpListeners;

    public HttpTask setTimeout(long timeout) {
        return this;
    }

    public HttpTask setHttps() {
        return this;
    }

    public HttpTask addHttpListener(HttpListener httpListener) {
        if(mHttpListeners == null) {
            mHttpListeners = new ArrayList<>();
        }
        mHttpListeners.add(httpListener);
        return this;
    }

    public HttpTask request(Request request) {
        return this;
    }

    public HttpTask execute() {
        return this;
    }

    public void cancel() {
    }
}
