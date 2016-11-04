package com.mht.stock.util;

import android.text.TextUtils;

import com.alibaba.fastjson.TypeReference;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Collections;
import java.util.List;

public class JSONUtils {
	private static final String TAG = "JSONUtils";

	public static <T> T fromJSON(String json, Class<T> classOfT) {
    	try {
			return TextUtils.isEmpty(json) ? null : com.alibaba.fastjson.JSONObject.parseObject(json, classOfT);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public static <T> T fromJSON(String json, TypeReference<T> reference) {
    	try {
			return TextUtils.isEmpty(json) ? null : com.alibaba.fastjson.JSONObject.parseObject(json, reference);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
    }
	
	public static <T> T fromJSON(JSONObject json, Class<T> classOfT) {
    	return json==null ? null : fromJSON(json.toString(), classOfT);
    }



	public static <T> List<T> toList(String json, Class<T> classOfT) {
		try {
			return TextUtils.isEmpty(json) ? null : com.alibaba.fastjson.JSONObject.parseArray(json, classOfT);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static String toJSON(Object object) {
		return com.alibaba.fastjson.JSONObject.toJSONString(object);
	}
	
	public static JSONArray toJSONArray(Object object) throws JSONException {
		return new JSONArray(toJSON(object));
	}
	
	public static JSONObject toJSONObj(Object object) throws JSONException {
		return new JSONObject(toJSON(object));
	}
}