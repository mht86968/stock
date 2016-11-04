package com.mht.stock.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;

import com.mht.stock.R;
import com.mht.stock.activity.base.BaseActivity;
import com.mht.stock.constant.Constants;
import com.mht.stock.databinding.ActivitySelectStockBinding;
import com.mht.stock.fragment.AllStockFragment;

/**
 * 选择股票
 */
public class SelectStockActivity extends BaseActivity {

    private ActivitySelectStockBinding mBinding;

    private AllStockFragment mAllStockFragment;

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, SelectStockActivity.class);
        context.startActivity(intent);
    }


    public static void toActivityForResult(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, SelectStockActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_select_stock);

        mBinding.toolbar.setTitle("选择股票");
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAllStockFragment = AllStockFragment.newFragment();
        replaceFragment(R.id.layoutMain, mAllStockFragment);
        mAllStockFragment.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent data = new Intent();
                data.putExtra(Constants.PARAM, mAllStockFragment.getItemData(position));
                setResult(RESULT_OK, data);
                onBackPressed();
            }
        });
    }
}
