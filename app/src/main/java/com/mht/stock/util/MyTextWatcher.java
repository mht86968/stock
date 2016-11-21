package com.mht.stock.util;

import android.content.Context;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by mht on 2016/11/7.
 */

public class MyTextWatcher implements TextWatcher {

    private Context mContext;
    private EditText mEditText;
    private int mMaxLen;
    private String mInputLenError;

    public MyTextWatcher(Context context, EditText editText, int maxLen) {
        mContext = context;
        mEditText = editText;
        mMaxLen = maxLen;
    }

    public MyTextWatcher(Context context, EditText editText, int maxLen, String inputLenError) {
        mContext = context;
        mEditText = editText;
        mMaxLen = maxLen;
        mInputLenError = inputLenError;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d("1111 beforeTextChanged", s + " " + start + " " + count + " " + after);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d("1111 onTextChanged", s + " " + start + " " + before + " " + count);
    }

    private Toast mToast;
    @Override
    public void afterTextChanged(Editable s) {
        if(s.length() > mMaxLen) {
            CharSequence sub = s.subSequence(0, mMaxLen);
            mEditText.setText(sub);
            mEditText.setSelection(sub.length());
            if(mToast != null) {
                mToast.cancel();
            }
            if(TextUtils.isEmpty(mInputLenError)) {
                mToast = CommonUtils.showToast(mContext, "上线");
            } else {
                mToast = CommonUtils.showToast(mContext, mInputLenError);
            }
        }
    }
}
