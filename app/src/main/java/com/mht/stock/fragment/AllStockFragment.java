package com.mht.stock.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.mht.stock.BR;
import com.mht.stock.R;
import com.mht.stock.activity.WebActivity;
import com.mht.stock.adapter.RecyclerAdapter;
import com.mht.stock.databinding.FragmentAllStockBinding;
import com.mht.stock.fragment.base.BaseFragment;
import com.mht.stock.model.StockModel;

public class AllStockFragment extends BaseFragment {
    public static final String TAG = "AllStockFragment";

    private FragmentAllStockBinding mBinding;
    private RecyclerAdapter<StockModel> mAdapter;
    private AdapterView.OnItemClickListener onStockItemClickListener;

    public static AllStockFragment newFragment() {
        return new AllStockFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_all_stock, null);
        mBinding = DataBindingUtil.bind(view);
        initStock();
        return view;
    }

    private void initStock() {
        mAdapter = new RecyclerAdapter(mContext, R.layout.item_stock, BR.stock, StockModel.getSampleStockModel());
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(mAdapter);

        if(onStockItemClickListener == null ) {
            mAdapter.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    StockModel model = mAdapter.getItemData(position);
                    WebActivity.startActivity(mContext, String.format(getString(R.string.url_stock_detail), model.stockCode), model.companyName);
                }
            });
        } else {
            mAdapter.setOnItemClickListener(onStockItemClickListener);
        }
    }

    public void setOnItemClickListener(AdapterView.OnItemClickListener l) {
        onStockItemClickListener = l;
    }

    public StockModel getItemData(int position) {
        return mAdapter.getItemData(position);
    }
}