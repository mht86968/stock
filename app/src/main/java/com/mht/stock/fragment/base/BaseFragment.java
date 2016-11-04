package com.mht.stock.fragment.base;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.mht.stock.MyApplication;

/**
 * Created by mht on 2016/4/9.
 */
public class BaseFragment extends Fragment {

    protected MyApplication mApplication;
    protected Context mContext;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mApplication = (MyApplication) context.getApplicationContext();
        mContext = context;
    }
}
