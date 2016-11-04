/**
 * 
 */
package com.mht.stock.util;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils {

    public static final String FORMAT_YMDHMS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMAT_YMDHM = "yyyy-MM-dd HH:mm";
    public static final String FORMAT_MDHM = "MM-dd HH:mm";
    public static final String FORMAT_HMS = "HH:mm:ss";
    public static final String FORMAT_HM = "HH:mm";
    public static final String FORMAT_YMD = "yyyy-MM-dd";
    public static final String FORMAT_YMDE = "yyyy-MM-dd EEEE";    //EEEE星期  E周几

    /**
     * Date 转  String
     * @param date
     * @param format
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String dateToString(Date date, String format) {
        format = TextUtils.isEmpty(format) ? FORMAT_YMDHMS : format;
        return null!=date ? new SimpleDateFormat(format).format(date) : "";
    }

    /**
     * String 转  Date
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    @SuppressLint("SimpleDateFormat")
    public static Date stringToDate(String date, String format) throws ParseException {
        format = TextUtils.isEmpty(format) ? FORMAT_YMDHMS : format;
        return new SimpleDateFormat(format).parse(date);
    }

    @SuppressLint("SimpleDateFormat")
    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }
    
    /**
     * 获取当前时间
     * @param format
     * @return
     */
    @SuppressLint("SimpleDateFormat")
    public static String getNowDate(String format) {
        return new SimpleDateFormat(format).format(Calendar.getInstance().getTime());
    }
    
    /**
     * 两个时间之间的天数
     * @param startDate
     * @return
     * @throws ParseException
     */
    @SuppressLint("SimpleDateFormat")
    public static int getDifferDays(String startDate, String endDate, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Date dateStart = formatter.parse(startDate);
        Date dateEnd = formatter.parse(endDate);
        long total = (dateEnd.getTime() - dateStart.getTime());
        if (total > 0) {
            long day = total / (24 * 60 * 60 * 1000L);
            if (day > 0) {
                return (int) day;
            } else {
            	return 0;
            }
        } if(total==0) {
        	return 0;
        } else {
            return -1;
        }
    }

    public static String toMonth(int month) {
		switch (month) {
		case Calendar.JANUARY:
			return "一月";
		case Calendar.FEBRUARY:
			return "二月";
		case Calendar.MARCH:
			return "三月";
		case Calendar.APRIL:
			return "四月";
		case Calendar.MAY:
			return "五月";
		case Calendar.JUNE:
			return "六月";
		case Calendar.JULY:
			return "七月";
		case Calendar.AUGUST:
			return "八月";
		case Calendar.SEPTEMBER:
			return "九月";
		case Calendar.OCTOBER:
			return "十月";
		case Calendar.NOVEMBER:
			return "十一月";
		case Calendar.DECEMBER:
			return "十二月";
		default:
			return "";
		}
	}
    
    public static int toDateMonth(int month) {
		switch (month) {
		case Calendar.JANUARY:
			return 1;
		case Calendar.FEBRUARY:
			return 2;
		case Calendar.MARCH:
			return 3;
		case Calendar.APRIL:
			return 4;
		case Calendar.MAY:
			return 5;
		case Calendar.JUNE:
			return 6;
		case Calendar.JULY:
			return 7;
		case Calendar.AUGUST:
			return 8;
		case Calendar.SEPTEMBER:
			return 9;
		case Calendar.OCTOBER:
			return 10;
		case Calendar.NOVEMBER:
			return 11;
		case Calendar.DECEMBER:
			return 12;
		default:
			return -1;
		}
	}

    public static int getMinutes(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MINUTE);
    }
    
    public static int getMinutes() {
        return Calendar.getInstance().get(Calendar.MINUTE);
    }
    
    public static int getHour(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY);
    }
    
    public static int getHour() {
        return Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    }
    
    public static int getDay(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getDay() {
        return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
    }
    
    public static int getMonth(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.MONTH);
    }
    
    public static int getMonth() {
        return Calendar.getInstance().get(Calendar.MONTH);
    }
    
    public static int getYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.YEAR);
    }
    
    public static int getYear() {
        return Calendar.getInstance().get(Calendar.YEAR);
    }
    
    private DateUtils() {}
}
