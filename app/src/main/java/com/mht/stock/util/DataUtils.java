package com.mht.stock.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.UUID;

public class DataUtils {

    public static final String FORMAT_DIGITS_ZERO    = "0";         //取整
    public static final String FORMAT_DIGITS_ONE     = "0.0";       //保留小数点后一位
    public static final String FORMAT_DIGITS_TWO     = "0.00";
    public static final String FORMAT_DIGITS_THREE   = "0.000";
    
    public static final String FORMAT_DIGITS_ONE2     = "#.#";      //保留小数点后一位
    public static final String FORMAT_DIGITS_TWO2     = "#.##";     //保留小数点后2位
    
    public static final String FORMAT_PERCENTAGE_ZERO    = "#%";    //取百分号
    public static final String FORMAT_PERCENTAGE_ONE     = "#.#%";
    public static final String FORMAT_PERCENTAGE_TWO     = "#.##%";
    public static final String FORMAT_PERCENTAGE_THREE   = "#.###%";

    /**
     * 保留小数点后几位,四舍五入
     * @param num
     * @param newScale
     * @return
     */
    public static double getScaleValue(double num, int newScale) {
    	return new BigDecimal(num).setScale(newScale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    public static float getScaleValue(float num, int newScale) {
        return new BigDecimal(num).setScale(newScale, BigDecimal.ROUND_HALF_UP).floatValue();
    }


    /**
     * double类型四舍五入,保留小数点后digit位
     * @param data
     * @return
     */
    public static String getFormatValue(double data, String format) {
        return new DecimalFormat(format).format(data);
    }

    public static String getFormatValue(float data, String format) {
        return new DecimalFormat(format).format(data);
    }
    
    public static String getFormatValue(int data, String format) {
        return new DecimalFormat(format).format(data);
    }
    
    /**
     * 获得一个UUID 唯一值
     * @return String UUID
     */
    public static String getUUID() {
        String s = UUID.randomUUID().toString();
        // 去掉“-”符号
        return s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23) + s.substring(24);
    }

    /**
     * 获得指定数目的UUID
     * @param number int 需要获得的UUID数量
     * @return String[] UUID数组
     */
    public static String[] getUUID(int number) {
        if (number < 1) {
            return null;
        }
        String[] ss = new String[number];
        for (int i = 0; i < number; i++) {
            ss[i] = getUUID();
        }
        return ss;
    }
}
