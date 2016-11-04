package com.mht.stock.model;

import android.content.Context;

/**
 * 投资类型
 * Created by mht on 2016/4/17.
 */
public class InvestTypeModel {

    public int id;
    public String name;     //名称

    public InvestTypeModel() {
    }

    public InvestTypeModel(int id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * 获取投资类型
     * 不能随意变换id和名称
     */
    private static InvestTypeModel[] data;

    static {
        data = new InvestTypeModel[] {
                new InvestTypeModel(1, "现金"),               //现金
                new InvestTypeModel(2, "股票"),               //股票
                new InvestTypeModel(3, "国债"),               //国债
                new InvestTypeModel(4, "国债逆回购"),        //国债逆回购
                new InvestTypeModel(5, "基金"),               //基金
        };
    }

    public static InvestTypeModel[] getInvestTypes(Context context) {
        return data;
    }
}
