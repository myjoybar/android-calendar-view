package com.joybar.librarycalendar.data;

/**
 * Created by joybar on 9/12/16.
 */
public class Lunar {
    public boolean isleap;
    public int lunarDay;
    public int lunarMonth;
    public int lunarYear;
    public boolean isLFestival;
    public String lunarFestivalName;//农历节日


    final static String chineseNumber[] =
            {"一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"};

    public static String getChinaDayString(int day) {
        String chineseTen[] =
                {"初", "十", "廿", "卅"};
        int n = day % 10 == 0 ? 9 : day % 10 - 1;
        if (day > 30)
            return "";
        if (day == 10)
            return "初十";
        else
            return chineseTen[day / 10] + chineseNumber[n];
    }

    @Override
    public String toString() {
        return "Lunar [isleap=" + isleap + ", lunarDay=" + lunarDay
                + ", lunarMonth=" + lunarMonth + ", lunarYear=" + lunarYear
                + ", isLFestival=" + isLFestival + ", lunarFestivalName="
                + lunarFestivalName + "]";
    }
}
