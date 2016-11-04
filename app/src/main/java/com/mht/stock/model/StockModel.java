package com.mht.stock.model;

import java.io.Serializable;

/**
 * Created by mht on 2016/2/4.
 * 股票信息
 */
public class StockModel implements Serializable {

    public static final String TYPE_SH = "sh";
    public static final String TYPE_SZ = "sz";

    public long id;

    public String stockCode;    //股票代码

    public String companyName;  //公司名称

    public String type;         //沪指  深指

    public StockModel() {
    }

    public static StockModel[] getSampleStockModel() {
        StockModel[] resList = new StockModel[5];

        StockModel model1 = new StockModel();
        model1.stockCode = "000001";
        model1.companyName = "平安银行";
        resList[0] = model1;

        StockModel model2 = new StockModel();
        model2.stockCode = "000002";
        model2.companyName = "万科A";
        resList[1] = model2;

        StockModel model3 = new StockModel();
        model3.stockCode = "000004";
        model3.companyName = "世纪星源";
        resList[2] = model3;

        StockModel model4 = new StockModel();
        model4.stockCode = "000005";
        model4.companyName = "世纪星源";
        resList[3] = model4;

        StockModel model5 = new StockModel();
        model5.stockCode = "000006";
        model5.companyName = "深振业A";
        resList[4] = model5;
        return resList;
    }
}
