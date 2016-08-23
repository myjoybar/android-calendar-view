# android-calendar-view
Easy to use, powerful, easy to expand the android calendar view library.<br />
使用方便，易扩展的andorid 日历控件库

 ![image](https://github.com/myjoybar/android-calendar-view/blob/master/img/demo.gif) 
  
## Features
 - 左右滑动.
 - 显示每月日期.
 - 选择某一天.

# 使用步骤
# Gradle Dependency
####  Add the library to your project build.gradle
```
  compile 'com.joybar.calendar:librarycalendar:1.0.0'
```
## Sample Usage
实现OnPageChangeListener和OnDateClickListener接口

```
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
        tv_date.setText(year+"-"+month+"-"+day);
    }

    @Override
    public void OnPageChangeOrClick(int year, int month) {
        tv_date.setText(year+"-"+month);
    }
}
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

