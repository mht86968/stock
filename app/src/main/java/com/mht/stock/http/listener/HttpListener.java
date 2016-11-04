package com.mht.stock.http.listener;


public interface HttpListener<T> {

	public void onPrepare();
	
	public void onResult(T result);

	public void onError(int code, String error);
}