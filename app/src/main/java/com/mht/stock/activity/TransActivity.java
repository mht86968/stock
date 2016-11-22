package com.mht.stock.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.View.OnClickListener;

import com.mht.stock.R;
import com.mht.stock.activity.base.BaseActivity;
import com.mht.stock.constant.Constants;

public class TransActivity extends BaseActivity implements
        OnClickListener {

    public static final int TYPE_SYSTEM_ERROR = 1;

    private int mType;
    private Dialog mDialog;

    private OnDismissListener dismissListener = new OnDismissListener() {

        @Override
        public void onDismiss(DialogInterface dialog) {
            finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getIntent().getIntExtra(Constants.PARAM_TYPE, -1);
        switch (mType) {
            case TYPE_SYSTEM_ERROR:
                dialogSystemError();
                break;
            default:
                finish();
                break;
        }
    }

    public void dialogSystemError() {
        mDialog = new AlertDialog.Builder(this)
                .setTitle("提示")
                .setMessage("程序崩溃了.....")
                .setOnDismissListener(dismissListener)
                .setPositiveButton(R.string.btn_confirm, null)
                .create();
        mDialog.show();
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mDialog != null) {
            mDialog.dismiss();
        }
    }

    public static void showDialogSystemError(Context context) {
        Intent intent = new Intent(context, TransActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
}