package com.mht.stock.dao;

import android.content.Context;

import com.mht.stock.model.StockInvestModel;

import java.util.List;

/**
 * Created by mht on 2016/4/8.
 */
public class StockInvestDao {

    public StockInvestDao(Context context) {
    }

    public void saveOrUpdateStock(StockInvestModel model) {
//        mDbManager.saveOrUpdate(model);
    }

    public void saveOrUpdateStocks(List<StockInvestModel> list) {
        if(list != null) {
            for (StockInvestModel model : list) {
                saveOrUpdateStock(model);
            }
        }
    }

    public List<StockInvestModel> getStocks() {
//        return mDbManager.findAll(StockInvestModel.class);
        return null;
    }

    public StockInvestModel getStockById(long id) {
//        return mDbManager.findById(StockInvestModel.class, id);
        return null;
    }

    public void deleteStock(long id) {
//        mDbManager.deleteById(StockInvestModel.class, id);
    }
}
