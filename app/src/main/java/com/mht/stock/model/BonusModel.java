package com.mht.stock.model;

/**
 * Created by mht on 2016/7/10.
 */
public class BonusModel implements Comparable<BonusModel> {

    public String bonusDate;  //红利时间
    public float give;  //送股
    public float trans; //转增
    public float bonus; //红利

    public String getInfo() {
        StringBuffer buffer = new StringBuffer("每十股");
        if(give > 0) {
            buffer.append(" 送").append(give).append("股");
        }
        if(trans > 0) {
            buffer.append(" 转增").append(trans).append("股");
        }
        if(bonus > 0) {
            buffer.append(" 红利").append(bonus).append("元");
        }
        return buffer.toString();
    }

    //获取税收
    public float getTax(String buyDate) {
        return 0.05f;
    }

    @Override
    public int compareTo(BonusModel another) {
        return bonusDate.compareTo(another.bonusDate);
    }
}
