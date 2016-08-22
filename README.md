# android-calendar-view
Easy to use, powerful, easy to expand the android calendar view library.使用方便，功能强大，易扩展的andorid 日历控件库
# 使用步骤
#### 1 添加编译依赖库
```
  compile project(':calendar_library')
```
#### 2 实现接口
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

 ![image](https://github.com/myjoybar/android-calendar-view/blob/master/img/demo.png)  