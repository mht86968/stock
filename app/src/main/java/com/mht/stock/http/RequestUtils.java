package com.mht.stock.http;

import com.mht.stock.AppException;
import com.mht.stock.constant.Constants;
import com.mht.stock.util.JSONUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;

public class RequestUtils {
	private static final String TAG = "HttpUtils";

	public static Request httpGet(String url, Map<String, Object> params) throws AppException {
		try {
			if (params != null && params.size() > 0) {
				StringBuffer urlBuffer = new StringBuffer();
				urlBuffer.append(url);
				urlBuffer.append("?");
				for (Map.Entry<String, Object> e : params.entrySet()) {
					Object value = e.getValue();
					urlBuffer.append(e.getKey());
					urlBuffer.append("=");
					urlBuffer.append(URLEncoder.encode(value == null ? "" : value.toString(), Constants.UTF8));
					urlBuffer.append("&");
				}
				urlBuffer.setLength(urlBuffer.length() - 1);
				return new Request.Builder().get().url(urlBuffer.toString()).build();
			} else {
				return new Request.Builder().get().url(url).build();
			}
		} catch (UnsupportedEncodingException e) {
			throw new AppException(AppException.ERROR_HTTP_PARAMS);
		}
	}
	
	public static Request httpGet(String url, JSONObject params) throws AppException {
		StringBuffer urlBuffer = new StringBuffer(url);
		if(params!=null) {
			try {
				Iterator<String> iterator = params.keys();
				if(iterator!=null) {
					boolean hasParam = iterator.hasNext();
					if(hasParam) {
						urlBuffer.append("?");
						while(iterator.hasNext()) {
							String key = iterator.next();
							Object value = params.opt(key);
							urlBuffer.append(key).append("=").append(URLEncoder.encode(String.valueOf(value), Constants.UTF8)).append("&");
						}
						urlBuffer.setLength(urlBuffer.length() - 1);
					}
				}
			} catch (UnsupportedEncodingException e) {
				throw new AppException(AppException.ERROR_HTTP_PARAMS);
			}
		}
		return new Request.Builder().get().url(urlBuffer.toString()).build();
	}
	
	public static Request httpPost(String url, Map<String, Object> params) throws UnsupportedEncodingException {
		Request.Builder requestBuilder = new Request.Builder().url(url);
		if(params!=null) {
			RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), JSONUtils.toJSON(params));
			requestBuilder.post(requestBody);
		}
        return requestBuilder.build();
	}
	
	public static Request httpPost(String url, JSONObject params) {
		Request.Builder requestBuilder = new Request.Builder().url(url);
		if(params!=null) {
			RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"), params.toString());
			requestBuilder.post(requestBody);
		}
		return requestBuilder.build();
	}

	/**
	 * 模拟表单提交  上传文件
	 * @param url
	 * @param params
	 * @return
     */
	public static Request httpMultipartPost(String url, JSONObject params, File[] files) {
		MultipartBody.Builder multipartBodyBuilder = new MultipartBody.Builder();
		multipartBodyBuilder.setType(MultipartBody.FORM);
		if(params != null) {
			Iterator<String> keys = params.keys();
			while (keys.hasNext()) {
				try {
					String key = keys.next();
					multipartBodyBuilder.addFormDataPart(key, params.getString(key));
				} catch (JSONException e) {
					e.printStackTrace();
				}
			}
		}
		if(files != null) {
			for (int i=0; i<files.length; i++) {
				String name = String.format("file%d", i);
				multipartBodyBuilder.addFormDataPart(name, name, RequestBody.create(MediaType.parse("image/jpeg"), files[i]));
			}
		}
		return new Request.Builder()
				.url(url)
				.post(multipartBodyBuilder.build())
				.build();
	}
}
