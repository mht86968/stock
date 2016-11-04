package com.mht.stock.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.mht.stock.R;
import com.mht.stock.activity.base.BaseActivity;
import com.mht.stock.constant.Constants;
import com.mht.stock.model.StockModel;

public class StockDetailActivity extends BaseActivity {

    private StockModel mData;

    public static void toActivity(Context context, Intent intent, StockModel model) {
        intent.setClass(context, StockDetailActivity.class);
        intent.putExtra(Constants.PARAM, model);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_detail);
        mData = (StockModel)getIntent().getSerializableExtra(Constants.PARAM);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(mData.companyName);
        toolbar.setSubtitle(mData.stockCode);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
