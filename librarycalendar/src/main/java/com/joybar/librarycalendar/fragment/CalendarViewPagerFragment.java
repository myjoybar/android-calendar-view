package com.joybar.librarycalendar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.joybar.librarycalendar.R;
import com.joybar.librarycalendar.adapter.CalendarViewPagerAdapter;


/**
 * Created by joybar on 4/28/16.
 */
public class CalendarViewPagerFragment extends Fragment {

    private ViewPager viewPager;
    private OnPageChangeListener onPageChangeListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onPageChangeListener = (OnPageChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnDateClickListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar_viewpager, container, false);
        initViewPager(view);
        return view;
    }
    private void initViewPager(View view){
        viewPager = (ViewPager) view.findViewById(R.id.viewpager);


        final CalendarViewPagerAdapter myAdapter = new CalendarViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(CalendarViewPagerAdapter.NUM_ITEMS_CURRENT);


        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int year = myAdapter.getYearByPosition(position);
                int month = myAdapter.getMonthByPosition(position);
               // tv_date.setText(year+"-"+month+"");
                onPageChangeListener.OnPageChangeOrClick(year,month);
            }
            @Override
            public void onPageScrolled(int position, float positionOffset,
                                       int positionOffsetPixels) {
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }



    public interface OnPageChangeListener {
        public void OnPageChangeOrClick(int year, int month);
    }
}
