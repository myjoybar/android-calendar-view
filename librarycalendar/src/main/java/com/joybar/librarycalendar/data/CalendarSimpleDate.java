package com.joybar.librarycalendar.data;

/**
 * Created by joybar on 8/23/16.
 */
public class CalendarSimpleDate {
    private int year;
    private int month;
    private int day;

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

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public CalendarSimpleDate(int year, int month, int day) {
        this.year = year;
        this.month = month;
        this.day = day;
    }
}

