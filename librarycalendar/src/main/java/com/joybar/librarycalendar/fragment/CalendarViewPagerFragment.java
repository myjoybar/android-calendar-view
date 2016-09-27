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

    private static final String CHOICE_MODE_SINGLE="choice_mode_single";
    private boolean isChoiceModelSingle;
    private ViewPager viewPager;
    private OnPageChangeListener onPageChangeListener;

    public CalendarViewPagerFragment() {
    }

    public static CalendarViewPagerFragment newInstance(boolean isChoiceModelSingle) {
        CalendarViewPagerFragment fragment = new CalendarViewPagerFragment();
        Bundle args = new Bundle();
        args.putBoolean(CHOICE_MODE_SINGLE, isChoiceModelSingle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onPageChangeListener = (OnPageChangeListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnDateClickListener");
        }
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            isChoiceModelSingle = getArguments().getBoolean(CHOICE_MODE_SINGLE,false);
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
        viewPager.setOffscreenPageLimit(2);
        final CalendarViewPagerAdapter myAdapter = new CalendarViewPagerAdapter(getChildFragmentManager(),isChoiceModelSingle);
        viewPager.setAdapter(myAdapter);
        viewPager.setCurrentItem(CalendarViewPagerAdapter.NUM_ITEMS_CURRENT);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                int year = myAdapter.getYearByPosition(position);
                int month = myAdapter.getMonthByPosition(position);
               // tv_date.setText(year+"-"+month+"");
                onPageChangeListener.onPageChange(year,month);
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
         void onPageChange(int year, int month);
    }
}
