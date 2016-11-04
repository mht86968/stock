package com.mht.stock.fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mht.stock.R;
import com.mht.stock.databinding.FragmentMainBinding;
import com.mht.stock.fragment.base.BaseFragment;

public class MainFragment extends BaseFragment {
    public static final String TAG = "MainFragment";

    private FragmentMainBinding mBinding;

    public static MainFragment newFragment() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_main, null);
        mBinding = DataBindingUtil.bind(view);
        return view;
    }
}