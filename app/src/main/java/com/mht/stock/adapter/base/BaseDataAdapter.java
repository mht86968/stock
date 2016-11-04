package com.mht.stock.adapter.base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.BaseAdapter;

public abstract class BaseDataAdapter<T> extends BaseAdapter {

	protected Context mContext;
	protected List<T> mData;
	protected LayoutInflater mInflater;
	
	public BaseDataAdapter(Context context) {
		this(context, null);
	}
	
	public BaseDataAdapter(Context context, List<T> data) {
		mContext = context;
		mData = data;
		mInflater = LayoutInflater.from(context);
	}
	
	@Override
	public int getCount() {
		return mData==null ? 0 : mData.size();
	}

	@Override
	public Object getItem(int position) {
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	
	public void addDate(int pos, T data) {
		checkData();
		mData.add(pos, data);
		notifyDataSetChanged();
	}
	
	public void addDate(T data) {
		checkData();
		mData.add(data);
		notifyDataSetChanged();
	}
	
	public void addDates(List<T> datas) {
		if(datas!=null) {
			checkData();
			mData.addAll(datas);
			notifyDataSetChanged();
		}
	}
	
	public void addDates(T[] datas) {
		if(datas!=null) {
			checkData();
			Collections.addAll(mData, datas);
			notifyDataSetChanged();
		}
	}
	
	public void updateDatas(List<T> datas) {
		mData = datas;
		notifyDataSetChanged();
	}
	
	public void updateDatas(T[] datas) {
		if(datas!=null && datas.length>0) {
			List<T> list = new ArrayList<>();
			Collections.addAll(list, datas);
			mData = list;
		} else {
			mData = null;
		}
		notifyDataSetChanged();
	}
	
	public void clearData() {
		if(mData!=null) {
			mData.clear();
		}
		notifyDataSetChanged();
	}
	
	public List<T> getDatas() {
		return mData;
	}
	
	public T getItemData(int pos) {
		return mData.get(pos);
	}
	
	private void checkData() {
		if(mData==null) {
			mData = new ArrayList<T>();
		}
	}
}
