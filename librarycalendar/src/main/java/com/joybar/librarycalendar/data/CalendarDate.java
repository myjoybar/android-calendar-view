package com.joybar.librarycalendar.data;

import com.joybar.librarycalendar.utils.LunarSolarConverter;

/**
 * Created by joybar on 2/24/16.
 */
public class CalendarDate {

    private int year;
    private int month;
    private int day;
    private boolean isInThisMonth; //是否在当月
    private boolean isSelect;//是否被选中
    private LunarSolarConverter.Lunar lunar = new LunarSolarConverter.Lunar();//农历

    public CalendarDate(int year, int month, int day, boolean isInThisMonth, boolean isSelect) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.isInThisMonth = isInThisMonth;
        this.isSelect = isSelect;
    }

    public CalendarDate(int year, int month, int day, boolean isInThisMonth, boolean isSelect, LunarSolarConverter.Lunar lunar) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.isInThisMonth = isInThisMonth;
        this.isSelect = isSelect;
        this.lunar = lunar;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }


    public boolean isInThisMonth() {
        return isInThisMonth;
    }

    public void setIsInThisMonth(boolean isInThisMonth) {
        this.isInThisMonth = isInThisMonth;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setIsSelect(boolean isSelect) {
        this.isSelect = isSelect;
    }

    public int getDay() {
        return day;
    }

    public void setDate(int day) {
        this.day = day;
    }

    public LunarSolarConverter.Lunar getLunar() {
        return lunar;
    }

    public void setLunar(LunarSolarConverter.Lunar lunar) {
        this.lunar = lunar;
    }
}
