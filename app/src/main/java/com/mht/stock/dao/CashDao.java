package com.mht.stock.dao;

import android.content.Context;

import com.mht.stock.model.CashModel;

import java.util.List;

/**
 * Created by mht on 2016/4/8.
 */
public class CashDao {


    public CashDao(Context context) {
    }

    public void saveOrUpdateStock(CashModel model) {
//        mDbManager.saveOrUpdate(model);
    }

    public void saveOrUpdateStocks(List<CashModel> list) {
        if(list != null) {
            for (CashModel model : list) {
                saveOrUpdateStock(model);
            }
        }
    }

    public List<CashModel> getStocks() {
//        return mDbManager.findAll(CashModel.class);
        return null;
    }

    public CashModel getStockById(long id) {
//        return mDbManager.findById(CashModel.class, id);
        return null;
    }

    public void deleteStock(long id) {
//        mDbManager.deleteById(CashModel.class, id);
    }
}
