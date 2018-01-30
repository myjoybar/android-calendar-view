# android-calendar-view
Easy to use, powerful, easy to expand the android calendar view library.<br />
使用方便，易扩展的andorid 日历控件库

 ![image](https://github.com/myjoybar/android-calendar-view/blob/master/screenshots/screenshot.gif) 
  
## Features
 - 日历左右滑动.
 - 显示阳历，农历，节假日和二十四节气
 - 实现对某月日期的单选或者多选.


# 使用步骤
# Gradle Dependency
####  Add the library to your project build.gradle
```gradle
  compile 'com.joybar.calendar:librarycalendar:1.0.6'
```
## Sample Usage
实现OnPageChangeListener和OnDateClickListener接口，如果实现多选，需要实现 OnDateCancelListener

```java
  public class MainActivity extends AppCompatActivity implements
        CalendarViewPagerFragment.OnPageChangeListener,
        CalendarViewFragment.OnDateClickListener,
        CalendarViewFragment.OnDateCancelListener {

    private TextView tv_date;
    private boolean isChoiceModelSingle = false;
    private List<CalendarDate> mListDate = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_date = (TextView) findViewById(R.id.tv_date);
        initFragment();
    }

   private void initFragment(){
       FragmentManager fm = getSupportFragmentManager();
       FragmentTransaction tx = fm.beginTransaction();
       // Fragment fragment = new CalendarViewPagerFragment();
       Fragment fragment = CalendarViewPagerFragment.newInstance(isChoiceModelSingle);
       tx.replace(R.id.fl_content, fragment);
       tx.commit();
   }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_im, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_single:
                isChoiceModelSingle = true;
                initFragment();
                break;
            case R.id.menu_multi:
                isChoiceModelSingle = false;
                initFragment();
                break;
            default:
                break;
        }
        return true;
    }
    @Override
    public void onDateClick(CalendarDate calendarDate) {

        int year = calendarDate.getSolar().solarYear;
        int month = calendarDate.getSolar().solarMonth;
        int day = calendarDate.getSolar().solarDay;
        if (isChoiceModelSingle) {
            tv_date.setText(year + "-" + month + "-" + day);
        } else {
            //System.out.println(calendarDate.getSolar().solarDay);
            mListDate.add(calendarDate);
            tv_date.setText(listToString(mListDate));
        }

    }

    @Override
    public void onDateCancel(CalendarDate calendarDate) {
        int count = mListDate.size();
        for (int i = 0; i < count; i++) {
            CalendarDate date = mListDate.get(i);
            if (date.getSolar().solarDay == calendarDate.getSolar().solarDay) {
                mListDate.remove(i);
                break;
            }
        }
        tv_date.setText(listToString(mListDate));
    }

    @Override
    public void onPageChange(int year, int month) {
        tv_date.setText(year + "-" + month);
        mListDate.clear();
    }

    private static String listToString(List<CalendarDate> list) {
        StringBuffer stringBuffer = new StringBuffer();
        for (CalendarDate date : list) {
            stringBuffer.append(date.getSolar().solarYear + "-" + date.getSolar().solarMonth + "-" + date.getSolar().solarDay).append(" ");
        }
        return stringBuffer.toString();
    }

}
```
## 单选或者多选的实现代码
```java
        if (isChoiceModelSingle) {
            mGridView.setChoiceMode(GridView.CHOICE_MODE_SINGLE);
        } else {
            mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE);
        }
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                CalendarDate calendarDate = ((CalendarGridViewAdapter) mGridView.getAdapter()).getListData().get(position);
                if (isChoiceModelSingle) {
                    //单选
                    if (finalMListDataCalendar.get(position).isInThisMonth()) {
                        onDateClickListener.onDateClick(calendarDate);
                    } else {
                        mGridView.setItemChecked(position, false);
                    }
                } else {
                    //多选
                    if (finalMListDataCalendar.get(position).isInThisMonth()) {
                       // mGridView.getCheckedItemIds()
                        if(!mGridView.isItemChecked(position)){
                            onDateCancelListener.onDateCancel(calendarDate);
                        } else {
                            onDateClickListener.onDateClick(calendarDate);
                        }

                    } else {
                        mGridView.setItemChecked(position, false);
                    }

                }
            }
        });

```


## License

    Copyright 2016 MyJoybar

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

