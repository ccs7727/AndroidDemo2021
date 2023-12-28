package com.example.androiddemo.ui.calendar;

import android.Manifest;
import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.androiddemo.R;

import java.util.Calendar;

import static android.content.pm.PackageManager.PERMISSION_GRANTED;

public class CalenderDemoActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mDeleteEventButton;
    private Button mWriteEventButton;
    private Button mDeleteEventButton2;
    private Button mWriteEventButton2;
    private Button getEventId;
    private Button getEventId2;
    private long eventId = 0;
    private long eventId2 = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calender_demo);
        checkPermission(0, Manifest.permission.READ_CALENDAR, Manifest.permission.WRITE_CALENDAR, Manifest.permission.SET_ALARM);
        setupViews();
    }

    private void checkPermission(int callbackId, String... permissionsId) {
        boolean permissions = true;
        for (String p : permissionsId) {
            permissions = permissions && ContextCompat.checkSelfPermission(this, p) == PERMISSION_GRANTED;
        }

        if (!permissions)
            ActivityCompat.requestPermissions(this, permissionsId, callbackId);
    }

    private void setupViews() {
        mDeleteEventButton = (Button) findViewById(R.id.deleteEventButton);
        mWriteEventButton = (Button) findViewById(R.id.writeEventButton);
        mDeleteEventButton2 = (Button) findViewById(R.id.deleteEventButton2);
        mWriteEventButton2 = (Button) findViewById(R.id.writeEventButton2);
        getEventId = (Button) findViewById(R.id.getEventId);
        getEventId2 = (Button) findViewById(R.id.getEventId2);
        mDeleteEventButton.setOnClickListener(this);
        mWriteEventButton.setOnClickListener(this);
        mDeleteEventButton2.setOnClickListener(this);
        mWriteEventButton2.setOnClickListener(this);
        getEventId.setOnClickListener(this);
        getEventId2.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    @SuppressLint("Range")
    @Override
    public void onClick(View v) {
        if (v == mWriteEventButton) {
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(System.currentTimeMillis() + 10 * 60 * 1000);
            long start = mCalendar.getTime().getTime();
            mCalendar.setTimeInMillis(start + 1 * 60 * 1000);
            long end = mCalendar.getTime().getTime();
            CalendarReminderUtils.addCalendarEvent(this, eventId, "工作提醒", "该休息啦", start, end);
            Log.d("============eventId", "onClick: " + eventId);
        }
        if (v == mDeleteEventButton) {
            Log.d("============eventId", "onClick: " + eventId);
            CalendarReminderUtils.deleteCalendarEvent(this, eventId);
        }


        if (v == mWriteEventButton2) {
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(System.currentTimeMillis() + 1 * 60 * 1000);
            long start = mCalendar.getTime().getTime();
            mCalendar.setTimeInMillis(start + 1 * 60 * 1000);
            long end = mCalendar.getTime().getTime();
            CalendarReminderUtils2.addCalendarEvent(this, eventId2, "工作提醒2222", "该休息啦2222", start, end);
            Log.d("============eventId2", "onClick: " + eventId2);
        }
        if (v == mDeleteEventButton2) {
            Log.d("============eventId2", "onClick: " + eventId2);
            CalendarReminderUtils2.deleteCalendarEvent(this, eventId2);
        }
        if (v == getEventId) {
            eventId = Math.round(Math.random() * 9999) + 1;
            Log.d("============eventId", "onClick: " + eventId);
        }
        if (v == getEventId2) {
            eventId2 = Math.round(Math.random() * 9999) + 1;
            Log.d("============eventId2", "onClick: " + eventId2);
        }
    }
}