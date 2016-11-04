package com.mht.stock.adapter.base;

import android.support.v4.view.PagerAdapter;
import android.util.Log;

/**
 * 解决notifyDataSetChanged 不刷新页面的问题
 * Created by mht on 2016/4/22.
 */
public abstract class BasePagerAdapter extends PagerAdapter {

    private int mChildCount = 0;

    @Override
    public void notifyDataSetChanged() {
        mChildCount = getCount();
        super.notifyDataSetChanged();
    }

    @Override
    public int getItemPosition(Object object)   {
        if (mChildCount >= 0) {
            mChildCount--;
            return POSITION_NONE;
        }
        return super.getItemPosition(object);
    }
}
