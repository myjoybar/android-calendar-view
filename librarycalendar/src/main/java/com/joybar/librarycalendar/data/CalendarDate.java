package com.joybar.librarycalendar.data;

/**
 * Created by joybar on 2/24/16.
 */
public class CalendarDate {

    private int year;
    private int month;
    private int day;
    private boolean isInThisMOnth; //是否在当月
    private boolean isSelect;//是否被选中

    public CalendarDate(int year, int month, int day, boolean isInThisMOnth, boolean isSelect) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.isInThisMOnth = isInThisMOnth;
        this.isSelect = isSelect;
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


    public boolean isInThisMOnth() {
        return isInThisMOnth;
    }

    public void setIsInThisMOnth(boolean isInThisMOnth) {
        this.isInThisMOnth = isInThisMOnth;
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
}
