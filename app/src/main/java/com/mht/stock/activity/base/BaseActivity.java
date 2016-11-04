package com.mht.stock.activity.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.Toast;

import com.mht.stock.MyApplication;
import com.mht.stock.util.CommonUtils;

/**
 * Created by mht on 2016/4/8.
 */
public class BaseActivity extends AppCompatActivity {

    protected MyApplication mApplication;
    protected Context mContext;

    private Toast mToast;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mApplication = (MyApplication) getApplication();
        mContext = this;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    protected void showToast(String text) {
        if(mToast != null) {
            mToast.cancel();
        }
        mToast = CommonUtils.showToast(mContext, text);
    }

    protected void showToast(int resId) {
        if(mToast != null) {
            mToast.cancel();
        }
        mToast = CommonUtils.showToast(mContext, resId);
    }


    protected void replaceFragment(int layoutId, Fragment fragment, boolean isToBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(layoutId, fragment);
        if(isToBackStack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    protected void replaceFragment(int layoutId, Fragment fragment) {
        replaceFragment(layoutId, fragment, false);
    }
}
