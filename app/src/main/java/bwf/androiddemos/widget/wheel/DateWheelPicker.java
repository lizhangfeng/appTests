package bwf.androiddemos.widget.wheel;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;

import java.util.Calendar;

import bwf.androiddemos.widget.wheel.adapter.DateWheelAdapter;

/**
 * Created by lizhangfeng on 16/4/11.
 * description: 日期选择器
 */
public class DateWheelPicker extends LinearLayout {

    private Context context;

    //日历类
    private Calendar calendar;

    //Wheel picker
    private WheelView years, months, days;

    private DateWheelAdapter yearWheelAdapter, monthWheelAdapter, dayWheelAdapter;

    //onChangeListener
    private OnChangeListener onChangeListener;

    public DateWheelPicker(Context context) {
        super(context);
        init(context);
    }

    public DateWheelPicker(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        setWeightSum(5);
        setGravity(Gravity.CENTER);

        this.context = context;
        calendar = Calendar.getInstance();

        years = new WheelView(context);
        LayoutParams yearParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        yearParams.weight = 1;
        years.setLayoutParams(yearParams);

        months = new WheelView(context);
        LayoutParams monthParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        monthParams.weight = 1;
        months.setLayoutParams(monthParams);

        days = new WheelView(context);
        LayoutParams dayParams = new LayoutParams(android.view.ViewGroup.LayoutParams.WRAP_CONTENT,
                android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        dayParams.weight = 1;
        days.setLayoutParams(dayParams);

        yearWheelAdapter = new DateWheelAdapter(context, 1900, 9999, "年");
        years.setViewAdapter(yearWheelAdapter);

        monthWheelAdapter = new DateWheelAdapter(context, 1, 12, "月");
        months.setViewAdapter(monthWheelAdapter);
        months.setCyclic(true);
        int maxday_of_month = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        dayWheelAdapter = new DateWheelAdapter(context, 1, maxday_of_month, "日");
        days.setViewAdapter(dayWheelAdapter);
        days.setCyclic(true);

        years.addChangingListener(onYearsChangedListener);
        months.addChangingListener(onMonthsChangedListener);
        days.addChangingListener(onDaysChangedListener);

        addView(years);
        addView(months);
        addView(days);
    }


    /**
     * 年滚动监听
     */
    private OnWheelChangedListener onYearsChangedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView hours, int oldValue, int newValue) {
            calendar.set(Calendar.YEAR, 1900 + newValue);
            yearWheelAdapter.setSelectPosition(newValue);
            years.setViewAdapter(yearWheelAdapter);
            onChangeListener.onChange(getYear(), getMonth(), getDay(), getDayOfWeek());
            setMonth(getMonth());
            setDay(getDay());
            dayWheelAdapter.setMinAndMaxValue(1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            days.setViewAdapter(dayWheelAdapter);
        }
    };
    /**
     * 月滚动监听
     */
    private OnWheelChangedListener onMonthsChangedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView mins, int oldValue, int newValue) {
            calendar.set(Calendar.MONTH, 1 + newValue - 1);
            onChangeListener.onChange(getYear(), getMonth(), getDay(), getDayOfWeek());
            monthWheelAdapter.setSelectPosition(newValue);
            months.setViewAdapter(monthWheelAdapter);
            setMonth(getMonth());
            setDay(getDay());
            dayWheelAdapter.setMinAndMaxValue(1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            days.setViewAdapter(dayWheelAdapter);
        }
    };
    /**
     * 日滚动监听
     */
    private OnWheelChangedListener onDaysChangedListener = new OnWheelChangedListener() {
        @Override
        public void onChanged(WheelView mins, int oldValue, int newValue) {
            calendar.set(Calendar.DAY_OF_MONTH, newValue + 1);
            onChangeListener.onChange(getYear(), getMonth(), getDay(), getDayOfWeek());
            dayWheelAdapter.setSelectPosition(newValue);
            dayWheelAdapter.setMinAndMaxValue(1, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
            days.setViewAdapter(dayWheelAdapter);
        }
    };


    /**
     * 设置默认选中日期
     *
     * @param year
     * @param month
     * @param day
     */
    public void setDefaultDate(int year, int month, int day) {
        setYear(year);
        setMonth(month);
        setDay(day);
    }

    /**
     * 定义了监听时间改变的监听器借口
     */
    public interface OnChangeListener {
        void onChange(int year, int month, int day, int day_of_week);
    }

    /**
     * 设置监听器的方法
     *
     * @param onChangeListener
     */
    public void setOnChangeListener(OnChangeListener onChangeListener) {
        this.onChangeListener = onChangeListener;
    }

    /**
     * 设置年
     *
     * @param year
     */
    public void setYear(int year) {
        years.setCurrentItem(year - 1900);
    }

    /**
     * 获得年
     *
     * @return
     */
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 设置月
     */
    public void setMonth(int month) {
        months.setCurrentItem(month - 1);
    }

    /**
     * 获得月
     *
     * @return
     */
    public int getMonth() {
        return calendar.get(Calendar.MONTH) + 1;
    }

    /**
     * 设置日
     *
     * @param day
     */
    public void setDay(int day) {
        days.setCurrentItem(day - 1);
    }

    /**
     * 获得日
     *
     * @return
     */
    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获得星期
     *
     * @return
     */
    public int getDayOfWeek() {
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    /**
     * 获得星期
     *
     * @return
     */
    public static String getDayOfWeekCN(int day_of_week) {
        String result = null;
        switch (day_of_week) {
            case 1:
                result = "日";
                break;
            case 2:
                result = "一";
                break;
            case 3:
                result = "二";
                break;
            case 4:
                result = "三";
                break;
            case 5:
                result = "四";
                break;
            case 6:
                result = "五";
                break;
            case 7:
                result = "六";
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //默认设置为系统时间
        setYear(getYear());
        setMonth(getMonth());
        setDay(getDay());
    }
}
