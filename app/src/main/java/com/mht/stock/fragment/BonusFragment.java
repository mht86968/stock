package com.mht.stock.fragment;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.EditText;

import com.mht.stock.BR;
import com.mht.stock.R;
import com.mht.stock.adapter.RecyclerAdapter;
import com.mht.stock.databinding.FragmentBonusBinding;
import com.mht.stock.fragment.base.BaseFragment;
import com.mht.stock.model.BonusModel;
import com.mht.stock.util.CommonUtils;
import com.mht.stock.util.DataUtils;
import com.mht.stock.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分红计算
 */
public class BonusFragment extends BaseFragment implements View.OnClickListener, AdapterView.OnItemClickListener {
    public static final String TAG = "BonusFragment";

    private String mRegistDate;                 //股权登记日  (暂忽略)
    private RecyclerAdapter<BonusModel> mAdapter;
    private FragmentBonusBinding mBinding;

    public static BonusFragment newFragment() {
        return new BonusFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = getLayoutInflater(savedInstanceState).inflate(R.layout.fragment_bonus, null);
        mBinding = DataBindingUtil.bind(view);
        mBinding.setClickListener(this);

        mAdapter = new RecyclerAdapter(mContext, R.layout.item_bonus, BR.model);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mBinding.recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.etDate:
            case R.id.etBuyDate:
            case R.id.etSellDate:
            case R.id.etRegistDate:
                dialogDate((EditText) v);
                break;
            case R.id.btnAdd:
                uiAddBonusModel();
                break;
            case R.id.btnProfit:
                uiProfit();
                break;
        }
    }

    /**
     * 收益
     */
    public void uiProfit() {
        String buyDate = mBinding.etBuyDate.getText().toString();
        List<BonusModel> data = mAdapter.getData();
        if(TextUtils.isEmpty(buyDate) || data==null || data.isEmpty()) {
            mBinding.tvProfit.setText("收益0%");
        } else {
            BonusModel tmpBonusModel = new BonusModel();
            tmpBonusModel.bonusDate = buyDate;
            int tmp = Collections.binarySearch(data, tmpBonusModel);
            int startBonusDay = tmp<0 ? -tmp-1 : tmp;               //第一次分红日期

            String sellDate = mBinding.etSellDate.getText().toString();
            int endBonusDay;
            if(!TextUtils.isEmpty(sellDate)) {
                tmpBonusModel.bonusDate = sellDate;
                tmp = Collections.binarySearch(data, tmpBonusModel);
                endBonusDay = tmp<0 ? -tmp-2 : tmp;                 //最后一次分红
            } else {
                endBonusDay = data.size()-1;
            }

            if(startBonusDay>endBonusDay || startBonusDay>=data.size()) {
                mBinding.tvProfit.setText("收益0%");
            } else {
                float buyPrice = Float.parseFloat(mBinding.etBuyPrice.getText().toString());
                int buyStockCount = 100;
                float bonus = 0;
                for (int i = startBonusDay; i <= endBonusDay; i++) {
                    BonusModel model = data.get(i);
                    buyStockCount += (int) (buyStockCount / 10 * model.give + buyStockCount / 10 * model.trans);
                    bonus += (buyStockCount / 10 * model.bonus);
                }

                StringBuffer buffer = new StringBuffer();
                buffer.append("原股:").append(100).append(" 总额:").append(100 * buyPrice);
                buffer.append("\n现股:").append(buyStockCount).append(" 红利:").append(bonus).append("元 总额:").append(buyStockCount * buyPrice + bonus);
                buffer.append("\n收益:").append(DataUtils.getFormatValue((buyStockCount * buyPrice + bonus)/(100 * buyPrice) - 1, DataUtils.FORMAT_PERCENTAGE_ONE));
                mBinding.tvProfit.setText(buffer.toString());
            }
        }
    }

    public void uiAddBonusModel() {
        BonusModel bonusModel = getBonusModel();
        if(bonusModel != null) {
            List<BonusModel> list = mAdapter.getData();
            if(list == null) {
                list = new ArrayList<>();
            }
            list.add(bonusModel);
            Collections.sort(list);
            mAdapter.updateDatas(list);
        }
    }

    public BonusModel getBonusModel() {
        String _bonusDate = mBinding.etDate.getText().toString();
        if(TextUtils.isEmpty(_bonusDate)) {
            CommonUtils.showToast(mContext, "请输入发放红利时间！");
            return null;
        }

        String _give = mBinding.etGiveStock.getText().toString();
        String _trans = mBinding.etTransStock.getText().toString();
        String _bonus = mBinding.etBonusStock.getText().toString();
        if(TextUtils.isEmpty(_give) && TextUtils.isEmpty(_trans) && TextUtils.isEmpty(_bonus)) {
            CommonUtils.showToast(mContext, "请输入红利！");
            return null;
        }

        BonusModel model = new BonusModel();
        model.bonusDate = _bonusDate;
        model.give = TextUtils.isEmpty(_give) ? 0 : Float.parseFloat(_give);
        model.trans = TextUtils.isEmpty(_trans) ? 0 : Float.parseFloat(_trans);
        model.bonus = TextUtils.isEmpty(_bonus) ? 0 : Float.parseFloat(_bonus);
        return model;
    }

    private Map<EditText, Calendar> dialogDate = new HashMap<>();
    private Calendar getCalendar(EditText editText) {
        if(dialogDate.containsKey(editText)) {
            return dialogDate.get(editText);
        } else {
            Calendar calendar = Calendar.getInstance();
            dialogDate.put(editText, calendar);
            return calendar;
        }
    }

    private void dialogDate(final EditText editText) {
        final Calendar calendar = getCalendar(editText);
        new DatePickerDialog(mContext, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(year, monthOfYear, dayOfMonth, 0, 0, 0);
                Date date = calendar.getTime();
                editText.setText(DateUtils.dateToString(date, DateUtils.FORMAT_YMD));
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH))
        .show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
        new AlertDialog.Builder(mContext)
                .setTitle("删除")
                .setMessage("确认删除此分红记录么？")
                .setNegativeButton(android.R.string.no, null)
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mAdapter.remove(position);
                    }
                })
                .show();
    }
}
