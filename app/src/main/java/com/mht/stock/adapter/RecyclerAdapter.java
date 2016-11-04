package com.mht.stock.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.mht.stock.adapter.base.BaseRecyclerAdapter;

import java.util.List;

/**
 * 通用RecyclerAdapter
 * Created by mht on 2016/6/20.
 */
public class RecyclerAdapter<T> extends BaseRecyclerAdapter<T> {

    private Context mContext;
    private int mItemLayoutResid;
    private int mVariableId;

    public RecyclerAdapter(Context context, int itemLayoutResid, int variableId) {
        super();
        mContext = context;
        mItemLayoutResid = itemLayoutResid;
        mVariableId = variableId;
    }

    public RecyclerAdapter(Context context, int itemLayoutResid, int variableId, T[] data) {
        super(data);
        mContext = context;
        mItemLayoutResid = itemLayoutResid;
        mVariableId = variableId;
    }

    public RecyclerAdapter(Context context, int itemLayoutResid, int variableId, List<T> data) {
        super(data);
        mContext = context;
        mItemLayoutResid = itemLayoutResid;
        mVariableId = variableId;
    }

    @Override
    public BaseRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BaseRecyclerAdapter.ViewHolder(LayoutInflater.from(mContext).inflate(mItemLayoutResid, null));
    }

    @Override
    public void onBindViewHolder(BaseRecyclerAdapter.ViewHolder holder, int position) {
        holder.getBinding().setVariable(mVariableId, getItemData(position));
    }
}
