package calendar.joybar.com.calendar_library.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import calendar.joybar.com.calendar_library.fragment.CalendarViewFragment;
import calendar.joybar.com.calendar_library.utils.DateUtils;

/**
 * Created by joybar on 4/27/16.
 */
public class CalendarViewPagerAdapter extends FragmentStatePagerAdapter {

    public static final int NUM_ITEMS = 200;
    public static final int NUM_ITEMS_CURRENT = NUM_ITEMS/2;
    private int mThisMonthPosition = DateUtils.getYear()*12+DateUtils.getMonth()-1;//---100 -position
    private int number = mThisMonthPosition - NUM_ITEMS_CURRENT;
    public CalendarViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int year = getYearByPosition(position);
        int month = getMonthByPosition(position);
        Fragment fragment = CalendarViewFragment.newInstance(year,month);
        return  fragment;
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    public   int getYearByPosition(int position){
        int year = (position+number)/12;
        return year;
    }
    public  int getMonthByPosition(int position){
        int month = (position + number) % 12 + 1;
        return month;
    }
}
