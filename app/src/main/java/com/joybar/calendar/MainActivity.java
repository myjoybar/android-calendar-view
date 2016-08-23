package com.joybar.calendar;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.joybar.librarycalendar.fragment.CalendarViewFragment;
import com.joybar.librarycalendar.fragment.CalendarViewPagerFragment;


public class MainActivity extends AppCompatActivity implements
        CalendarViewPagerFragment.OnPageChangeListener,
        CalendarViewFragment.OnDateClickListener {

    private TextView tv_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_date = (TextView) findViewById(R.id.tv_date);
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction tx = fm.beginTransaction();
        Fragment fragment = new CalendarViewPagerFragment();
        tx.add(R.id.fl_content, fragment);
        tx.commit();
    }

    @Override
    public void OnDateClick(int year, int month, int day) {
        tv_date.setText(year + "-" + month + "-" + day);
    }

    @Override
    public void OnPageChangeOrClick(int year, int month) {
        tv_date.setText(year + "-" + month);
    }
}
