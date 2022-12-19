package com.example.androiddemo.ui.date.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.androiddemo.R;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by ccs 2022/9/26 17:36
 */
public class DateStyleView extends LinearLayout implements View.OnClickListener {
    LinearLayout llLeft;
    LinearLayout llRight;
    TextView tvContent;
    ImageView ivLeft;
    ImageView ivRight;
    int centerYear;
    int centerMonth;

    int type;//0-年月 1年

    List<String> yearList;
    int yearIndex = 0;

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
        ivLeft = findViewById(R.id.ivLeft);
        ivRight = findViewById(R.id.ivRight);
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
     * 初始化年列表
     *
     * @param list
     */
    public void initYear(List<String> list) {
        yearList = list;
        yearIndex = yearList.size() - 1;
        setDate(Integer.parseInt(yearList.get(yearIndex)), 1); //设置年月
        if (yearIndex == 0) {
            llLeft.setEnabled(false);
            ivLeft.setImageResource(R.drawable.svg_icon_date_left_enable);
            llRight.setEnabled(false);
            ivRight.setImageResource(R.drawable.svg_icon_date_right_enable);
        } else {
            llLeft.setEnabled(true);
            ivLeft.setImageResource(R.drawable.svg_icon_date_left);
            if (yearIndex == yearList.size() - 1) {
                llRight.setEnabled(false);
                ivRight.setImageResource(R.drawable.svg_icon_date_right_enable);
            }
        }
    }

    public void setType(int t) {
        type = t;
    }

    /**
     * 设置初始化年月日期
     *
     * @param year  ...
     * @param month ...
     */
    public void setDate(int year, int month) {
        if (type == 0) {
            if (month < 1) {
                month = 1;
            }
            if (month > 12) {
                month = 12;
            }
            centerYear = year;
            centerMonth = month;
            tvContent.setText(year + "年" + month + "月");
        } else {
            centerYear = year;
            centerMonth = month;
            tvContent.setText(year + "年");
        }
    }


    /**
     * 设置上一月
     */
    private void getLast() {
        if (type == 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(centerYear - 1900, centerMonth - 1, 1));
            c.add(Calendar.MONTH, -1);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            setDate(year, month);
            if (onLastMonthChangeListener != null) {
                if (month < 10) {
                    onLastMonthChangeListener.onMonthChangeListener("" + year, "0" + month);
                } else {
                    onLastMonthChangeListener.onMonthChangeListener("" + year, "" + month);
                }
            }
        } else {
            if (yearList != null) {
                //设置年月
                if (yearIndex == 0) {
                    yearIndex = yearList.size() - 1;
                } else {
                    yearIndex -= 1;
                    llRight.setEnabled(true);
                    ivRight.setImageResource(R.drawable.svg_icon_date_right);
                    if (yearIndex == 0) {
                        llLeft.setEnabled(false);
                        ivLeft.setImageResource(R.drawable.svg_icon_date_left_enable);
                    }
                }
                setDate(Integer.parseInt(yearList.get(yearIndex)), 1); //设置年月
                if (onLastMonthChangeListener != null) {
                    onLastMonthChangeListener.onMonthChangeListener(yearList.get(yearIndex), "0" + 1);
                }
            } else {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date(centerYear - 1900, centerMonth - 1, 1));
                c.add(Calendar.YEAR, -1);
                int year = c.get(Calendar.YEAR);
                int month = 1;
                setDate(year, month);
                if (onLastMonthChangeListener != null) {
                    onLastMonthChangeListener.onMonthChangeListener("" + year, "0" + month);
                }
            }
        }
    }

    /**
     * 设置下一月
     */
    private void getNext() {
        if (type == 0) {
            Calendar c = Calendar.getInstance();
            c.setTime(new Date(centerYear - 1900, centerMonth - 1, 1));
            c.add(Calendar.MONTH, 1);
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH) + 1;
            setDate(year, month);
            if (onNextMonthChangeListener != null) {
                if (month < 10) {
                    onNextMonthChangeListener.onMonthChangeListener("" + year, "0" + month);
                } else {
                    onNextMonthChangeListener.onMonthChangeListener("" + year, "" + month);
                }
            }
        } else {
            if (yearList != null) {
                //设置年月
                if (yearIndex == yearList.size() - 1) {
                    yearIndex = 0;
                } else {
                    yearIndex += 1;
                    llLeft.setEnabled(true);
                    ivLeft.setImageResource(R.drawable.svg_icon_date_left);
                    if (yearIndex == yearList.size() - 1) {
                        llRight.setEnabled(false);
                        ivRight.setImageResource(R.drawable.svg_icon_date_right_enable);
                    }
                }
                setDate(Integer.parseInt(yearList.get(yearIndex)), 1); //设置年月
                if (onLastMonthChangeListener != null) {
                    onLastMonthChangeListener.onMonthChangeListener(yearList.get(yearIndex), "0" + 1);
                }
            } else {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date(centerYear - 1900, centerMonth - 1, 1));
                c.add(Calendar.YEAR, 1);
                int year = c.get(Calendar.YEAR);
                int month = 1;
                setDate(year, month);
                if (onNextMonthChangeListener != null) {
                    onNextMonthChangeListener.onMonthChangeListener("" + year, "0" + month);
                }
            }
        }
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.llLeft) {
            getLast();
        } else if (id == R.id.llRight) {
            getNext();
        }
    }

    private OnLastMonthChangeListener onLastMonthChangeListener;//点击上月下月监听器

    /**
     * 点击上月监听器
     */
    public void setOnLastClickListener(OnLastMonthChangeListener onMonthChangeListener) {
        this.onLastMonthChangeListener = onMonthChangeListener;
    }

    public interface OnLastMonthChangeListener {
        void onMonthChangeListener(String year, String month);
    }


    private OnNextMonthChangeListener onNextMonthChangeListener;//点击上月下月监听器

    /**
     * 点击上月监听器
     */
    public void setOnNextClickListener(OnNextMonthChangeListener onMonthChangeListener) {
        this.onNextMonthChangeListener = onMonthChangeListener;
    }

    public interface OnNextMonthChangeListener {
        void onMonthChangeListener(String year, String month);
    }
}
