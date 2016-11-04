package com.mht.stock.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.mht.stock.R;
import com.mht.stock.activity.base.BaseActivity;
import com.mht.stock.constant.Constants;
import com.mht.stock.databinding.ActivityAddInvestBinding;
import com.mht.stock.model.CashModel;
import com.mht.stock.model.StockModel;
import com.mht.stock.util.CommonUtils;
import com.mht.stock.util.DateUtils;

import java.text.ParseException;
import java.util.Date;

public class AddInvestActivity extends BaseActivity {

    private static final int REQUEST_SELECT_STOCK = 1;

    private ActivityAddInvestBinding mBinding;

    private CashModel cashModel;
    private StockModel stockModel;

    public static void toActivity(Context context) {
        Intent intent = new Intent(context, AddInvestActivity.class);
        context.startActivity(intent);
    }

    public static void toActivity(Activity activity, int requestCode) {
        Intent intent = new Intent(activity, AddInvestActivity.class);
        activity.startActivityForResult(intent, requestCode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_add_invest);

        mBinding.toolbar.setTitle("添加记录");
        setSupportActionBar(mBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        initView();
    }

    private void initView() {
        mBinding.spinnerType.setAdapter(new ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, android.R.id.text1, getResources().getStringArray(R.array.Type)));
        mBinding.etBuyDate.setText(DateUtils.dateToString(new Date(), DateUtils.FORMAT_YMD));
    }



    private float getMoney(EditText editText) {
        String money = editText.getText().toString();
        return TextUtils.isEmpty(money) ? 0 : Float.parseFloat(money);
    }

    private Date getDate(TextView textView) {
        String money = textView.getText().toString();
        try {
            return TextUtils.isEmpty(money) ? new Date() : DateUtils.stringToDate(money, DateUtils.FORMAT_YMD);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    private CashModel getCashModel() {
        CashModel cashModel = new CashModel();
        cashModel.money = getMoney(mBinding.etCashMoney);
        cashModel.date = getDate(mBinding.etBuyDate);
        return cashModel;
    }

    private void showContent(int position) {
        mBinding.layoutContent.getChildAt(position).setVisibility(View.VISIBLE);
        int size = mBinding.layoutContent.getChildCount();
        for (int i=0; i<size; i++) {
            if(position == i) {
                continue;
            } else {
                mBinding.layoutContent.getChildAt(i).setVisibility(View.GONE);
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_invest, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(R.id.menu_save == item.getItemId()) {
            CommonUtils.showToast(mContext, "nihai");
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK) {
            if(requestCode == REQUEST_SELECT_STOCK) {
                stockModel = (StockModel) data.getSerializableExtra(Constants.PARAM);
            }
        }
    }

    private void onStockClick(View view) {
        SelectStockActivity.toActivityForResult(this, REQUEST_SELECT_STOCK);
    }
}
