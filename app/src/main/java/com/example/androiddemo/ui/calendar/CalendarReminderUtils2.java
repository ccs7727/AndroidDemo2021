package com.example.androiddemo.ui.calendar;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarReminderUtils2 {

    private static String CALENDARS_NAME = "TaskList";
    private static String CALENDARS_ACCOUNT_NAME = "MyTaskList";
    private static String CALENDARS_ACCOUNT_TYPE = "MyTaskList";
    /**
     * 这里创建账户的展示名称，系统日历为我们提供了创建账户的入口，那我们就不使用系统自带的账户，创建一个自己app的账户
     */
    private static String CALENDARS_DISPLAY_NAME = "靠谱的任务清单";

    /**
     * 检查是否已经添加了日历账户，如果没有添加先添加一个日历账户再查询
     * 获取账户成功返回账户id，否则返回-1
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    private static int checkAndAddCalendarAccount(Context context) {
        int oldId = checkCalendarAccount(context);
        if (oldId >= 0) {
            return oldId;
        } else {
            long addId = addCalendarAccount(context);
            if (addId >= 0) {
                return checkCalendarAccount(context);
            } else {
                return -1;
            }
        }
    }

    /**
     * 检查是否存在现有账户，存在则返回账户id，否则返回-1
     */
    @SuppressLint("Range")
    private static int checkCalendarAccount(Context context) {
        Cursor userCursor = context.getContentResolver().query(CalendarContract.Calendars.CONTENT_URI, null, null, null, null);
        try {
            if (userCursor == null) { //查询返回空值
                return -1;
            }
            int count = userCursor.getCount();
            if (count > 0) { //存在现有账户，取第一个账户的id返回
                for (int i = 0; i <= count - 1; i++) {
                    if (i == 0) {
                        userCursor.moveToFirst();
                    } else {
                        userCursor.moveToNext();
                    }
                    String type = userCursor.getString(userCursor.getColumnIndex(CalendarContract.Calendars.ACCOUNT_TYPE));
                    if (type.equals(CALENDARS_ACCOUNT_TYPE)) {
                        return userCursor.getInt(userCursor.getColumnIndex(CalendarContract.Calendars._ID));
                    }
                }
            }
            return -1;
        } finally {
            if (userCursor != null) {
                userCursor.close();
            }
        }
    }

    /**
     * 添加日历账户，账户创建成功则返回账户id，否则返回-1
     */
    private static long addCalendarAccount(Context context) {
        TimeZone timeZone = TimeZone.getDefault();
        ContentValues value = new ContentValues();

        value.put(CalendarContract.Calendars.NAME, CALENDARS_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE);
        value.put(CalendarContract.Calendars.CALENDAR_DISPLAY_NAME, CALENDARS_DISPLAY_NAME);
//        可见度
        value.put(CalendarContract.Calendars.VISIBLE, 1);
//        日历颜色
        value.put(CalendarContract.Calendars.CALENDAR_COLOR, Color.BLUE);
//        权限
        value.put(CalendarContract.Calendars.CALENDAR_ACCESS_LEVEL, CalendarContract.Calendars.CAL_ACCESS_OWNER);
        value.put(CalendarContract.Calendars.SYNC_EVENTS, 1);
//        时区
        value.put(CalendarContract.Calendars.CALENDAR_TIME_ZONE, timeZone.getID());
        value.put(CalendarContract.Calendars.OWNER_ACCOUNT, CALENDARS_ACCOUNT_NAME);
        value.put(CalendarContract.Calendars.CAN_ORGANIZER_RESPOND, 0);

        Uri calendarUri = CalendarContract.Calendars.CONTENT_URI;
        calendarUri = calendarUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE)
                .build();

        Uri result = context.getContentResolver().insert(calendarUri, value);
        long id = result == null ? -1 : ContentUris.parseId(result);
        return id;
    }

    /**
     * 这个是关键方法，调用插入日程提醒
     *
     * @param context
     * @param eventId      事件id
     * @param title        提醒事件标题
     * @param description  事件描述
     * @param startTime 任务开始时间，这里参数名不太合适，后面会加提醒时间，
     * @param endTime      任务结束时间
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("Range")
    public static void addCalendarEvent(Context context, long eventId, String title, String description, long startTime, long endTime) {
        if (context == null) {
            return;
        }
        int calId = checkAndAddCalendarAccount(context); //获取日历账户的id
        if (calId < 0) { //获取账户id失败直接返回，添加日历事件失败
            return;
        }

        //添加日历事件
        Calendar mCalendar = Calendar.getInstance();
        mCalendar.setTimeInMillis(startTime);//设置开始时间
        long start = mCalendar.getTime().getTime();
        mCalendar.setTimeInMillis(endTime);//设置终止时间
        long end = mCalendar.getTime().getTime();
        ContentValues event = new ContentValues();
        event.put("title", title);
        event.put("description", description);
        event.put("calendar_id", calId); //插入账户的id
        event.put("eventStatus", 1);
        event.put(CalendarContract.Events._ID, eventId);
        event.put(CalendarContract.Events.HAS_EXTENDED_PROPERTIES, true);
        event.put(CalendarContract.Events.DTSTART, start);
        event.put(CalendarContract.Events.DTEND, end);
        event.put(CalendarContract.Events.HAS_ALARM, 1);//设置有闹钟提醒，但是经测试，此方案无效
        event.put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().getDisplayName());//这个是时区，必须有
        Uri newEvent = context.getContentResolver().insert(CalendarContract.Events.CONTENT_URI, event); //添加事件
        if (newEvent == null) { //添加日历事件失败直接返回
            return;
        }
        //扩展属性 用于高版本安卓系统设置闹钟提醒
        Uri extendedPropUri = CalendarContract.ExtendedProperties.CONTENT_URI;
        extendedPropUri = extendedPropUri.buildUpon()
                .appendQueryParameter(CalendarContract.CALLER_IS_SYNCADAPTER, "true")
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_NAME, CALENDARS_ACCOUNT_NAME)
                .appendQueryParameter(CalendarContract.Calendars.ACCOUNT_TYPE, CALENDARS_ACCOUNT_TYPE).build();
        ContentValues extendedProperties = new ContentValues();
        extendedProperties.put(CalendarContract.ExtendedProperties.EVENT_ID, ContentUris.parseId(newEvent));
        extendedProperties.put(CalendarContract.ExtendedProperties.VALUE, "{\"need_alarm\":true}");
        extendedProperties.put(CalendarContract.ExtendedProperties.NAME, "agenda_info");
        Uri uriExtended = context.getContentResolver().insert(extendedPropUri, extendedProperties);
        if (uriExtended == null) { //添加事件提醒失败直接返回
            return;
        }
        //事件提醒的设定
        ContentValues values = new ContentValues();
        values.put(CalendarContract.Reminders.EVENT_ID, ContentUris.parseId(newEvent));
        values.put(CalendarContract.Reminders.MINUTES, 0);// 提前previousDate天有提醒
        values.put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT);
        Uri uri = context.getContentResolver().insert(CalendarContract.Reminders.CONTENT_URI, values);
        if (uri == null) { //添加事件提醒失败直接返回
            return;
        }
        Toast.makeText(context, "设置日程成功!!!", Toast.LENGTH_LONG).show();
    }

    /**
     * 删除日历事件
     */
    public static void deleteCalendarEvent(Context context, Long delEventID) {
        if (context == null) {
            return;
        }
        Uri deleteUri = ContentUris.withAppendedId(CalendarContract.Events.CONTENT_URI, delEventID);
        int rows = context.getContentResolver().delete(deleteUri, null, null);
        if (rows == -1) { //事件删除失败
            return;
        }
        Toast.makeText(context, "删除日程成功!!!", Toast.LENGTH_LONG).show();
    }

    /**
     * 查询日历日程相关的数据库表(查询其他表也适用)，方法将查询表的所有列的所有行都展示出来了，就是这么人性化
     *
     * @param uri 用来区分查询的表的类型，查询不同的表，使用不同的URI即可，其他的都一样
     */
    @SuppressLint("Range")
    public void queryCalendarData(Uri uri, Activity context) {
        Cursor cursor = context.getContentResolver().query(uri, null,
                null, null, null);
        while (cursor.moveToNext()) {
            int columnCount = cursor.getColumnCount();
            Log.e("TAG", "columnCount :" + columnCount);//多少个属性
            for (int i = 0; i < columnCount; i++) {
                //获取到属性的名称
                String columnName = cursor.getColumnName(i);
                //获取到属性对应的值
                String message = cursor.getString(cursor.getColumnIndex(columnName));
                //打印属性和对应的值
                Log.e("TAG", columnName + " : " + message);
            }
        }
    }
}

