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
import com.joybar.librarycalendar.data.ChoiceModel;
import com.joybar.librarycalendar.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by joybar on 4/27/16.
 */
public class CalendarViewFragment extends Fragment {

    private static final String YEAR = "year";
    private static final String MONTH = "month";
    private static final String CHOICE_MODE = "choice_mode";
    private int choiceModel;
    private int mYear;
    private int mMonth;
    private GridView mGridView;
    private OnDateClickListener onDateClickListener;
    private OnDateCancelListener onDateCancelListener;
    private OnDateDurationSelectedListener onDateDurationSelectedListener;
    private CalendarGridViewAdapter calendarGridViewAdapter;
    private boolean hasSelectStartData = false;
    private List<CalendarDate> calendarDateList = new ArrayList<>(0);
    private int startSelectPosition;
    private int endSelectPosition;

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

    public static CalendarViewFragment newInstance(int year, int month, int choiceModel) {
        CalendarViewFragment fragment = new CalendarViewFragment();
        Bundle args = new Bundle();
        args.putInt(YEAR, year);
        args.putInt(MONTH, month);
        args.putInt(CHOICE_MODE, choiceModel);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            if (choiceModel == ChoiceModel.CHOICE_MODE_SINGLE) {
                onDateClickListener = (OnDateClickListener) context;
            } else if (choiceModel == ChoiceModel.CHOICE_MODE_SINGLE) {
                onDateCancelListener = (OnDateCancelListener) context;
            } else if (choiceModel == ChoiceModel.CHOICE_MODE_SINGLE) {
                onDateDurationSelectedListener = (OnDateDurationSelectedListener) context;
            }

        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement " +
                    "OnDateClickListener" + " or OnDateCancelListener");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mYear = getArguments().getInt(YEAR);
            mMonth = getArguments().getInt(MONTH);
            choiceModel = getArguments().getInt(CHOICE_MODE, ChoiceModel.CHOICE_MODE_SINGLE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle
            savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        mGridView = (GridView) view.findViewById(R.id.gv_calendar);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        List<CalendarDate> mListDataCalendar;//日历数据
        mListDataCalendar = CalendarDateController.getCalendarDate(mYear, mMonth);
        mGridView.setAdapter(calendarGridViewAdapter = new CalendarGridViewAdapter
                (mListDataCalendar));
        final List<CalendarDate> finalMListDataCalendar = mListDataCalendar;
        if (isChoiceModelSingle()) {
            mGridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        } else {
            mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        }
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CalendarDate calendarDate = ((CalendarGridViewAdapter) mGridView.getAdapter())
                        .getListData().get(position);
                if (choiceModel == ChoiceModel.CHOICE_MODE_SINGLE) {
                    if (null != onDateClickListener) {
                        onDateClickListener.onDateClick(calendarDate);
                    }
                } else if (choiceModel == ChoiceModel.CHOICE_MODE_MULTI) {
                    if (!mGridView.isItemChecked(position)) {
                        if (null != onDateCancelListener) {
                            onDateCancelListener.onDateCancel(calendarDate);
                        }

                    } else {
                        if (null != onDateClickListener) {
                            onDateClickListener.onDateClick(calendarDate);
                        }

                    }
                } else if (choiceModel == ChoiceModel.CHOICE_MODE_DURATION) {
                    if (!hasSelectStartData) {
                        hasSelectStartData = true;
                        startSelectPosition = position;
                    } else {
                        calendarDateList.clear();
                        endSelectPosition = position;
                        for (int i = startSelectPosition; i < endSelectPosition; i++) {
                            mGridView.setItemChecked(i, true);
                            calendarDateList.add(calendarGridViewAdapter.getListData().get(i));
                        }

                    }


                }
            }
        });

        if (choiceModel != ChoiceModel.CHOICE_MODE_DURATION) {
            mGridView.post(new Runnable() {
                @Override
                public void run() {
                    //需要默认选中当天
                    List<CalendarDate> mListData = ((CalendarGridViewAdapter) mGridView
                            .getAdapter()).getListData();
                    int count = mListData.size();
                    for (int i = 0; i < count; i++) {
                        if (mListData.get(i).getSolar().solarDay == DateUtils.getDay() &&
                                mListData.get(i).getSolar().solarMonth == DateUtils.getMonth() &&
                                mListData.get(i).getSolar().solarYear == DateUtils.getYear()) {
                            if (null != mGridView.getChildAt(i) && mListData.get(i).isInThisMonth
                                    ()) {
                                // mListData.get(i).setIsSelect(true);
                                if (null != onDateClickListener) {
                                    onDateClickListener.onDateClick(mListData.get(i));
                                }
                                mGridView.setItemChecked(i, true);
                            }
                        }
                    }

                }
            });
        }
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (!isVisibleToUser) {
            if (null != mGridView) {
                // mGridView.setItemChecked(mCurrentPosition, false);
                mGridView.clearChoices();
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    public interface OnDateClickListener {
        void onDateClick(CalendarDate calendarDate);
    }

    public interface OnDateCancelListener {
        void onDateCancel(CalendarDate calendarDate);
    }


    public interface OnDateDurationSelectedListener {
        void OnDateDurationSelected(List<CalendarDate> calendarDateList);
    }

    boolean isChoiceModelSingle() {
        return choiceModel == ChoiceModel.CHOICE_MODE_SINGLE;
    }
}
