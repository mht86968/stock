package com.mht.stock.dao;

import android.content.Context;

import com.mht.stock.model.StockModel;

import java.util.List;

/**
 * Created by mht on 2016/4/8.
 */
public class StockDao {

//    private DbManager mDbManager;

    public StockDao(Context context) {
//        mDbManager = org.xutils.x.getDb(((MyApplication)context.getApplicationContext()).getDaoConfig());
    }

    public void saveOrUpdateStock(StockModel model) {
//        mDbManager.saveOrUpdate(model);
    }

    public void saveOrUpdateStocks(List<StockModel> list) {
        if(list != null) {
            for (StockModel model : list) {
                saveOrUpdateStock(model);
            }
        }
    }

    public List<StockModel> getStocks() {
//        return mDbManager.findAll(StockModel.class);
        return null;
    }

    public StockModel getStockById(long id) {
//        return mDbManager.findById(StockModel.class, id);
        return null;
    }

    public void deleteStock(long id) {
//        mDbManager.deleteById(StockModel.class, id);
    }
}
