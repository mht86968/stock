package com.mht.stock.adapter.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by mht on 2016/6/20.
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseRecyclerAdapter.ViewHolder> {

    private AdapterView.OnItemClickListener mItemClickListener;
    protected List<T> mData;

    public BaseRecyclerAdapter() {
    }

    public BaseRecyclerAdapter(T[] data) {
        mData = new ArrayList<>();
        Collections.addAll(mData, data);
    }

    public BaseRecyclerAdapter(List<T> data) {
        mData = data;
    }

    @Override
    public int getItemCount() {
        return mData==null ? 0 : mData.size();
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

    public void addDates(List<T> data) {
        if(data != null) {
            checkData();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }

    public void addDates(T[] data) {
        if(data != null) {
            checkData();
            Collections.addAll(mData, data);
            notifyDataSetChanged();
        }
    }

    public void updateDatas(List<T> data) {
        mData = data;
        notifyDataSetChanged();
    }

    public void updateDatas(T[] data) {
        if(data!=null && data.length>0) {
            List<T> list = new ArrayList<>();
            Collections.addAll(list, data);
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

    public void remove(int position) {
        mData.remove(position);
        notifyDataSetChanged();
    }

    public void remove(T t) {
        mData.remove(t);
        notifyDataSetChanged();
    }

    public List<T> getData() {
        return mData;
    }

    public T getItemData(int position) {
        return mData.get(position);
    }

    private void checkData() {
        if(mData==null) {
            mData = new ArrayList<>();
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener l) {
        mItemClickListener = l;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ViewDataBinding binding;

        public ViewHolder(View view) {
            super(view);
            binding = DataBindingUtil.bind(view);
            binding.getRoot().setOnClickListener(this);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }

        @Override
        public void onClick(View v) {
            if(mItemClickListener != null) {
                int position = getAdapterPosition();
                mItemClickListener.onItemClick(null, v, position, -1);
            }
        }
    }
}
