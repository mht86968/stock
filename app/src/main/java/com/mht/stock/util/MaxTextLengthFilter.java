package com.mht.stock.util;

import android.content.Context;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.Toast;

/**
 * Created by mht on 2016/11/8.
 */

public class MaxTextLengthFilter implements InputFilter {

    private int mMaxLength;
    private Toast mToast;

    public MaxTextLengthFilter(Context context, int max) {
        mMaxLength = max;
        mToast = Toast.makeText(context,"已到输入上限", Toast.LENGTH_SHORT);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        int keep = mMaxLength - (dest.length() - (dend - dstart));
        if(keep < (end - start)){
            mToast.show();
        }
        if(keep <= 0){
            return "";
        }else if(keep >= end - start){
            return null;
        }else{
            return source.subSequence(start,start + keep);
        }
    }
}
