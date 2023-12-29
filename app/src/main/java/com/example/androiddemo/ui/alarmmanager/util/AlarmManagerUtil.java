package com.example.androiddemo.ui.alarmmanager.util;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.Calendar;

/**
 * 定时提醒
 */
public class AlarmManagerUtil {
    public static final String ALARM_ACTION = "com.loonggg.alarm.clock";

    public static void cancelAlarm(Context context, int id) {
        Intent intent = new Intent(ALARM_ACTION);
        PendingIntent pi = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        am.cancel(pi);
    }

    /**
     * @param hour            时
     * @param minute          分
     * @param id              闹钟的id
     * @param tips            闹钟提示信息
     * @param soundOrVibrator 2表示声音和震动都执行，1表示只有铃声提醒，0表示只有震动提醒
     */
    public static void setAlarm(Context context, int year, int month, int day, int hour, int minute, int id, String tips, int soundOrVibrator) {
        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Calendar calendar = Calendar.getInstance();
//        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH),
//        hour, minute, calendar.get(Calendar.SECOND) + 1);
        calendar.set(year, month, day, hour, minute, 0);
//        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), hour, minute, 0);
        Intent intent = new Intent(ALARM_ACTION);
//        intent.setPackage("com.example.androiddemo");
        intent.putExtra("msg", tips);
        intent.putExtra("id", id);
        intent.putExtra("soundOrVibrator", soundOrVibrator);
        PendingIntent sender = PendingIntent.getBroadcast(context, id, intent, PendingIntent.FLAG_CANCEL_CURRENT);
        long time = calendar.getTimeInMillis();
        am.setWindow(AlarmManager.RTC_WAKEUP, time, 0, sender);
        Log.e("闹钟提醒时间", "" + time);
    }

}
