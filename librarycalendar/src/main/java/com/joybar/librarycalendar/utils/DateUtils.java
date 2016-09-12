package com.joybar.librarycalendar.utils;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by joybar on 2/16/16.
 */
public class DateUtils {

    static Calendar cal = Calendar.getInstance();

    public static int getYear() {
        return cal.get(Calendar.YEAR);//获取年份
    }

    public static int getMonth() {
        return cal.get(Calendar.MONTH) + 1;//获取月份
    }

    public static int getDay() {
        return cal.get(Calendar.DATE);//获取日
    }

    public static int getHour() {
        //  return  cal.get(Calendar.HOUR);//获取小时,12小时制
        return cal.get(Calendar.HOUR_OF_DAY); // 24小时制
    }

    public static int getMinute() {
        return cal.get(Calendar.MINUTE);//获取分
    }

    public static int getSecond() {
        return cal.get(Calendar.SECOND);//获取秒
    }

    public static String getWeek() {

        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }


    public static String getWeek(long date) {

        String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTimeInMillis(date);
        int week_index = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week_index < 0) {
            week_index = 0;
        }
        return weeks[week_index];
    }


    /**
     * 实现给定某日期，判断是星期几
     *
     * @param date 必须yyyy-MM-dd
     * @return
     */
    public static String getWeekday(String date) {
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdw = new SimpleDateFormat("E");
        Date d = null;
        try {
            d = sd.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sdw.format(d);
    }

    /**
     * @param sdf
     * @return
     */
    public static String getTime(SimpleDateFormat sdf) {
        //"yyyy-MM-dd HH:mm:ss aa",最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制
        return sdf.format(new Date());
    }

    /**
     * 获取当前时间戳
     *
     * @return
     */
    public static long getCurrentTimeMillis() {
        long time = System.currentTimeMillis();
        // return time / 1000;
        return time;
    }

    /**
     * 日期格式字符串转换成时间戳
     *
     * @param date_str   字符串日期 “2016-02-26 12:00:00”
     * @param format 如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static long date2TimeStamp(String date_str, String format) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(format);
            //  return sdf.parse(date_str).getTime() / 1000;
            return sdf.parse(date_str).getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 时间戳字符串转换成日期格式
     *
     * @param seconds 精确到毫秒
     * @param format  如：yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String timeStamp2Date(long seconds, String format) {

        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        // return sdf.format(new Date(Long.valueOf(seconds+"000")));
        return sdf.format(new Date(seconds));
    }

    /**
     * @param dateStr 2008-08-08 12:10:12
     * @param format  yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getFormatDate(String dateStr, String format) {

        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            return sdf.format(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 判断是否为今天或者今天以后
     *
     * @param year
     * @param month
     * @param day
     * @return
     */
    public static boolean isAfterToday(int year, int month, int day) {

        if (year > DateUtils.getYear()) {
            return true;
        } else if (year == DateUtils.getYear()) {

            if (month > DateUtils.getMonth()) {
                return true;
            } else if (month == DateUtils.getMonth()) {
                if (day >= DateUtils.getDay()) {
                    return true;
                } else {
                    return false;
                }

            } else {
                return false;
            }

        } else {
            return false;
        }


    }

    /**
     * 判断是否为当月或者当月以后
     *
     * @param year
     * @param month
     * @return
     */
    public static boolean isAfterThisMonth(int year, int month) {

        if (year > DateUtils.getYear()) {
            return true;
        } else if (year == DateUtils.getYear()) {

            if (month >= DateUtils.getMonth()) {
                return true;
            } else {
                return false;
            }

        } else {
            return false;
        }


    }

    public static String getConvertMonth(String numberStr) {

        String[] months = {"一月", "二月", "三月", "四月", "五月",
                "六月", "七月", "八月", "九月", "十月",
                "十一月", "十二月"};

        try {
            int month = Integer.valueOf(numberStr);
            return months[month-1];
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}
