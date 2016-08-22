package calendar.joybar.com.calendar_library.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import calendar.joybar.com.calendar_library.R;
import calendar.joybar.com.calendar_library.data.CalendarDate;

/**
 * Created by joybar on 2/24/16.
 */
public class CalendarGridViewAdapter extends BaseAdapter {

    private List<CalendarDate> mListData = new ArrayList<>();


    public CalendarGridViewAdapter(List<CalendarDate> mListData) {
        this.mListData = mListData;
    }

    public List<CalendarDate> getListData() {
        return mListData;
    }


    public int getCount() {
        return mListData.size();
    }


    public Object getItem(int position) {
        return position;
    }



    public long getItemId(int position) {
        return position;
    }


    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) parent.getContext()
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_calendar, parent, false);
            viewHolder = new ViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.tv_day.setText(mListData.get(position).getDay()+"");
        if(mListData.get(position).isInThisMOnth()){
            viewHolder.tv_day.setTextColor(Color.parseColor("#000000"));
        }else{
            viewHolder.tv_day.setTextColor(Color.parseColor("#D7D7D7"));

        }
        return convertView;
    }


    public static class ViewHolder {
        private TextView tv_day;
        public ViewHolder(View itemView) {
            tv_day = (TextView) itemView.findViewById(R.id.tv_day);
        }

    }



}

