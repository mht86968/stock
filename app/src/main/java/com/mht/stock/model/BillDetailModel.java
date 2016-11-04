package com.mht.stock.model;

import android.content.Context;

import java.util.Date;

/**
 * 交易详情
 * 股票 买入T+1
 * 基金 买卖第二天成交
 * Created by mht on 2016/4/9.
 */
public class BillDetailModel {

    public long id;

    public long investId;           //投资具体id(平安银行、3天国债逆回购)

    public Date buyDate;
    public float buyMoney;          //买入资金（包括手续费）
    public int buyCount;            //买入份数
    public float buyUnitPrice;      //买入单价价格
    public float buyFee;            //买入手续费

    public Date sellDate;
    public float sellMoney;        //卖出资金（包括手续费）
    public int sellCount;          //卖出份数
    public float sellUnitPrice;    //卖出单价价格
    public float sellFee;          //卖出手续费

    public boolean isSell = false;  //是否卖出
    public float yield = 0;          //收益率

    public static String[] getSells(Context context) {
        return new String[]{
                "买",
                "卖"
        };
    }

    public static boolean isSell(String sell) {
        if("买".equals(sell)) {
            return false;
        } else {
            return true;
        }
    }

//    public BillDetailModel(DbModel model) {
//        id = model.getLong("id");
//        investId = model.getLong("investId");
//        isSell = model.getBoolean("isSell");
//        buyDate = model.getDate("buyDate");
//        buyMoney = model.getFloat("buyMoney");
//        buyCount = model.getInt("buyCount");
//        buyUnitPrice = model.getFloat("buyUnitPrice");
//        buyFee = model.getFloat("buyFee");
//
//        sellDate = model.getDate("sellDate");
//        sellMoney = model.getFloat("sellMoney");
//        sellCount = model.getInt("sellCount");
//        sellUnitPrice = model.getFloat("sellUnitPrice");
//        sellFee = model.getFloat("sellFee");
//
//        yield = model.getFloat("yield");
//    }

    public BillDetailModel() {
    }
}