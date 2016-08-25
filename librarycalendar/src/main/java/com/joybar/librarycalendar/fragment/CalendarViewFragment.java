package com.joybar.librarycalendar.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import com.joybar.librarycalendar.R;
import com.joybar.librarycalendar.adapter.CalendarGridViewAdapter;
import com.joybar.librarycalendar.controller.CalendarDateController;
import com.joybar.librarycalendar.data.CalendarDate;
import com.joybar.librarycalendar.utils.DateUtils;

import java.util.List;



/**
 * Created by joybar on 4/27/16.
 */
public class CalendarViewFragment extends Fragment {

    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private int mYear;
    private int mMonth;
    private GridView mGridView;
    private OnDateClickListener onDateClickListener;

    public CalendarViewFragment() {
    }

    public static CalendarViewFragment newInstance(int year, int month) {
        CalendarViewFragment fragment = new CalendarViewFragment();
        Bundle args = new Bundle();
        args.putInt(YEAR, year);
        args.putInt(MONTH, month);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            onDateClickListener = (OnDateClickListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement OnDateClickListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mYear = getArguments().getInt(YEAR);
            mMonth = getArguments().getInt(MONTH);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        mGridView = (GridView) view.findViewById(R.id.gv_calendar);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<CalendarDate> mListDataCalendar ;//日历数据
        mListDataCalendar = CalendarDateController.getCalendarDate(mYear, mMonth);
        mGridView.setAdapter( new CalendarGridViewAdapter(mListDataCalendar));
        final List<CalendarDate> finalMListDataCalendar = mListDataCalendar;

        mGridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CalendarDate calendarDate = ((CalendarGridViewAdapter) mGridView.getAdapter()).getListData().get(position);
                int year = calendarDate.getYear();
                int month = calendarDate.getMonth();
                int day = calendarDate.getDay();
                if (finalMListDataCalendar.get(position).isInThisMonth()) {
                    onDateClickListener.OnDateClick(year, month, day);
                }
            }
        });
        mGridView.post(new Runnable() {
            @Override
            public void run() {
                //需要默认选中当天
                List<CalendarDate> mListData = ((CalendarGridViewAdapter) mGridView.getAdapter()).getListData();
                int count = mListData.size();
                for (int i = 0; i < count; i++) {
                    if (mListData.get(i).getDay() == DateUtils.getDay()
                            && mListData.get(i).getMonth() == DateUtils.getMonth()
                            && mListData.get(i).getYear() == DateUtils.getYear()) {
                        if (null != mGridView.getChildAt(i) && mListData.get(i).isInThisMonth()) {
                            mGridView.setItemChecked(i, true);
                        }
                    }
                }

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();


    }

    public interface OnDateClickListener {
         void OnDateClick(int year, int month, int day);
    }
}
