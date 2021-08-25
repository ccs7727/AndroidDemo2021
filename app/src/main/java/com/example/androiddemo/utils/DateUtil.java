package com.example.androiddemo.utils;

import android.annotation.SuppressLint;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Hua on 2015/9/11 0011.
 */
public class DateUtil {
    private final static int SECONDS = 1;
    private final static int MINUTES = 60 * SECONDS;
    private final static int HOURS = 60 * MINUTES;
    private final static int DAYS = 24 * HOURS;
    private final static int WEEKS = 7 * DAYS;
    private final static int MONTHS = 4 * WEEKS;
    private final static int YEARS = 12 * MONTHS;

//    public static String getTimeAgo(Context context, Date date) {
//        int beforeSeconds = (int) (date.getTime() / 1000);
//        int nowSeconds = (int) (Calendar.getInstance().getTimeInMillis() / 1000);
//        int timeDifference = nowSeconds - beforeSeconds;
//
//        Resources res = context.getResources();
//
//        if (timeDifference < MINUTES) {
//            return res.getQuantityString(R.plurals.fuzzydatetime__seconds_ago, timeDifference, timeDifference);
//        } else if (timeDifference < HOURS) {
//            return res.getQuantityString(R.plurals.fuzzydatetime__minutes_ago, timeDifference / MINUTES, timeDifference / MINUTES);
//        } else if (timeDifference < DAYS) {
//            return res.getQuantityString(R.plurals.fuzzydatetime__hours_ago, timeDifference / HOURS, timeDifference / HOURS);
//        } else if (timeDifference < WEEKS) {
//            return res.getQuantityString(R.plurals.fuzzydatetime__days_ago, timeDifference / DAYS, timeDifference / DAYS);
//        } else if (timeDifference < MONTHS) {
//            return res.getQuantityString(R.plurals.fuzzydatetime__weeks_ago, timeDifference / WEEKS, timeDifference / WEEKS);
//        } else if (timeDifference < YEARS) {
//            return res.getQuantityString(R.plurals.fuzzydatetime__months_ago, timeDifference / MONTHS, timeDifference / MONTHS);
//        } else {
//            return res.getQuantityString(R.plurals.fuzzydatetime__years_ago, timeDifference / YEARS, timeDifference / YEARS);
//        }
//
//    }

    /**
     * 获取当前日期 相对日期字符串
     *
     * @param format
     * @param relativeDay
     * @return
     * @throws ParseException
     */
    public static String currentDate(String format, int relativeDay) {

        SimpleDateFormat df = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, relativeDay);//获取当前日期


        return df.format(c.getTime());
    }

    /**
     * 将时间字符串转化为毫秒数
     */
    public static long strData2MS(String data) throws ParseException {
        data = data.replaceAll("年", "").replaceAll("月", "").replaceAll("日", "").replaceAll(" ", "").replaceAll(":", "").replaceAll("-", "").replaceAll("  ", "");

        Calendar c = Calendar.getInstance();
        c.setTime(new SimpleDateFormat("yyyyMMddHHmm").parse(data));
        //  LogUtil.printLog("转换的时间" + data + "转换后" + c.getTimeInMillis());
        return c.getTimeInMillis();
    }


    /**
     * 根据已知开始时间月间隔小时数获取结束时间
     */
    public static long getEndTime(long startTime, int MTTS) throws ParseException {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date(startTime));
        c.add(Calendar.HOUR, MTTS);
        return c.getTimeInMillis();
    }


    /**
     * 将日期字符串  转换成date日期型
     *
     * @param strdate
     * @return
     */
    public static Date stringToDate(String strdate) {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = null; //初始化date
        try {
            return sdf.parse(strdate); //Mon Jan 14 00:00:00 CST 2013
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将10 or 13 位时间戳转为时间字符串 convert the number 1407449951 1407499055617 to
     * date/time format timestamp
     */
    @SuppressLint("SimpleDateFormat")
    public static String MS2strData(String str_num) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (str_num.length() == 13) {
            String date = sdf.format(new Date(toLong(str_num)));
            return date;
        } else if (str_num.length() == 10) {
            String date = sdf.format(new Date(toInt(str_num) * 1000L));
            return date;
        } else {
            String date = str_num;
            return date;
        }

    }

    /**
     * 将10 or 13 位时间戳转为时间字符串 convert the number 1407449951 1407499055617 to
     * date/time format timestamp
     */
    @SuppressLint("SimpleDateFormat")
    public static String MS2strData(String str_num, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        if (str_num.length() == 13) {
            String date = sdf.format(new Date(toLong(str_num)));
            return date;
        } else if (str_num.length() == 10) {
            String date = sdf.format(new Date(toInt(str_num) * 1000L));
            return date;
        } else if (str_num.length() == 9) {
            String date = sdf.format(new Date(toInt(str_num)));
            return date;
        } else {
            String date = str_num;
            return date;
        }


    }

    /**
     * 根据时间戳格式化成规定时间格式
     *
     * @param time        时间戳
     * @param formatStyle 时间格式
     * @return
     */
    public static String getDateByLong(String time, String formatStyle) {
        SimpleDateFormat sdf = new SimpleDateFormat(formatStyle);
        String date = "";
        if (time.contains("-")) {
            date = sdf.format(new Date(-8 * 3600 * 1000));
        } else {
            if (time.length() == 13) {
                date = sdf.format(new Date(toLong(time)));
            } else if (time.length() == 10) {
                date = sdf.format(new Date(toInt(time) * 1000L));
            } else {
                date = sdf.format(new Date(toLong(time) - 8 * 3600 * 1000));
            }
        }
        return date;
    }

    /**
     * String转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static long toLong(String obj) {
        try {
            return Long.parseLong(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * Int转long
     *
     * @param obj
     * @return 转换异常返回 0
     */
    public static int toInt(String obj) {
        try {
            return Integer.valueOf(obj);
        } catch (Exception e) {
        }
        return 0;
    }

    /**
     * 将时间字符串转化为毫秒数
     */
    public static long getTimeInMillis() {

        Calendar c = Calendar.getInstance();

        return c.getTimeInMillis();
    }

    //转换中文对应的时段
    public static String convertNowHour2CN(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("HH");
        String hourString = sdf.format(date);
        int hour = Integer.parseInt(hourString);
        if (hour < 6) {
            return "凌晨";
        } else if (hour >= 6 && hour < 12) {
            return "早上";
        } else if (hour == 12) {
            return "中午";
        } else if (hour > 12 && hour <= 18) {
            return "下午";
        } else if (hour >= 19) {
            return "晚上";
        }
        return "";
    }

    public static String convertSecond2Day(long time) {
        if (time <= 0) {
            return "已结束";
        }
        long day = time / (1 * 60 * 60 * 24);
        long hour = time / (1 * 60 * 60) % 24;
        long minute = time / (1 * 60) % 60;
        long second = time / (1) % 60;
        return day + "天" + hour + "小时" + minute + "分" + second + "秒";
    }


    public static String formatDateTime(String ymd) {
        //格式化当前时间
        SimpleDateFormat isNow = new SimpleDateFormat(ymd);
        String now = isNow.format(new Date());
        return now;
    }

    public static String formatDateTime(String ymd, String datetime) {
        //格式化当前时间
        SimpleDateFormat isNow = new SimpleDateFormat(ymd);
        String now = "";
        try {
            isNow.format(datetime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return now;
    }


    /**
     * 将秒转换成 成小时
     * @param second
     * @return
     */
    public  static String secondToHour(int second){
        String hour = null;
       int m = second/60;
        int h = m/60;

        if(m < 60){
            hour = m+"分钟";
        }else {
            hour = h+"小时";
        }

        return hour;
    }


}
