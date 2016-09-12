package com.joybar.librarycalendar.data;

/**
 * Created by joybar on 2/24/16.
 */
public class CalendarDate {

    private Lunar lunar = new Lunar();//农历
    private Solar solar = new Solar();//公历
    private boolean isInThisMonth; //是否在当月
    private boolean isSelect;//是否被选中

    public CalendarDate(int year, int month, int day, boolean isInThisMonth, boolean isSelect, Lunar lunar) {
        this.isInThisMonth = isInThisMonth;
        this.isSelect = isSelect;
        this.lunar = lunar;
    }


    public CalendarDate(boolean isInThisMonth, boolean isSelect, Solar solar, Lunar lunar) {
        this.isInThisMonth = isInThisMonth;
        this.isSelect = isSelect;
        this.solar = solar;
        this.lunar = lunar;
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

    public Solar getSolar() {
        return solar;
    }

    public void setSolar(Solar solar) {
        this.solar = solar;
    }

    public void setInThisMonth(boolean inThisMonth) {
        isInThisMonth = inThisMonth;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }

    public Lunar getLunar() {
        return lunar;
    }

    public void setLunar(Lunar lunar) {
        this.lunar = lunar;
    }
}
