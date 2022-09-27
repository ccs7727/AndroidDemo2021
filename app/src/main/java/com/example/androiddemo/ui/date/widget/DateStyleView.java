package com.example.androiddemo.ui.date.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.androiddemo.R;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by ccs 2022/9/26 17:36
 */
public class DateStyleView extends LinearLayout implements View.OnClickListener {
    LinearLayout llLeft;
    LinearLayout llRight;
    TextView tvContent;
    int centerYear;
    int centerMonth;

    public DateStyleView(Context context) {
        super(context);
        this.initVariables();
    }

    public DateStyleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.initVariables();
    }

    public DateStyleView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.initVariables();
    }

    private void initVariables() {
        LayoutInflater.from(getContext()).inflate(R.layout.view_date_style, this, true);
        llLeft = findViewById(R.id.llLeft);
        llRight = findViewById(R.id.llRight);
        tvContent = findViewById(R.id.tvContent);
        llLeft.setOnClickListener(this);
        llRight.setOnClickListener(this);
    }

    /**
     * 初始化日历
     */
    public void initCalendar() {
        Calendar c = Calendar.getInstance(); //首先要获取日历对象
        int mYear = c.get(Calendar.YEAR); // 获取当前年份
        int mMonth = c.get(Calendar.MONTH) + 1;// 获取当前月份
        setDate(mYear, mMonth); //设置年月
    }

    /**
     * 设置初始化年月日期
     *
     * @param year  ...
     * @param month ...
     */
    public void setDate(int year, int month) {
        if (month < 1) {
            month = 1;
        }
        if (month > 12) {
            month = 12;
        }
        centerYear = year;
        centerMonth = month;
        tvContent.setText(year + "-" + month);
        onMonthChangeListener.onMonthChangeListener(year, month);
    }

    private OnMonthChangeListener onMonthChangeListener;//点击上月下月监听器

    /**
     * 点击上月下月监听器
     */
    public void setOnLastClickListener(OnMonthChangeListener onMonthChangeListener) {
        this.onMonthChangeListener = onMonthChangeListener;
    }

    /**
     * 设置上一月
     */
    private void getLast() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(centerYear - 1900, centerMonth - 1, 1));
        c.add(Calendar.MONTH, -1);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        setDate(year, month);
    }

    /**
     * 设置下一月
     */
    private void getNext() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(centerYear - 1900, centerMonth - 1, 1));
        c.add(Calendar.MONTH, 1);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH) + 1;
        setDate(year, month);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.llLeft:
                getLast();
                break;
            case R.id.llRight:
                getNext();
                break;
        }
    }

    public interface OnMonthChangeListener {
        void onMonthChangeListener(int year, int month);
    }
}
