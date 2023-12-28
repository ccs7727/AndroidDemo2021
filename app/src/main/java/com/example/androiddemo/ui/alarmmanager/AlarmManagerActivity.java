package com.example.androiddemo.ui.alarmmanager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.alarmmanager.util.AlarmManagerUtil;

import java.util.Calendar;

/**
 * 定时提醒
 */
public class AlarmManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView date_tv;
    private Button set_btn, reset_btn;
    private String time;
    private int ring = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        set_btn = (Button) findViewById(R.id.set_btn);
        set_btn.setOnClickListener(this);
        reset_btn = (Button) findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(this);
        date_tv = (TextView) findViewById(R.id.date_tv);
        initTime();
    }

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        time = calendar.get(Calendar.HOUR_OF_DAY) + ":" + (calendar.get(Calendar.MINUTE));
        date_tv.setText(time);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.set_btn:
                setClock();
                break;
            case R.id.reset_btn:
                initTime();
                setClock();
                break;
        }
    }

    private void setClock() {
        int id = (int) (Math.round(Math.random() * 9999) + 1);
        if (time != null && time.length() > 0) {
            String[] times = time.split(":");
            AlarmManagerUtil.setAlarm(this, Integer.parseInt(times[0]), Integer.parseInt
                    (times[1]), id, 0, "闹钟响了-demo", ring);
            Toast.makeText(this, "闹钟设置成功", Toast.LENGTH_LONG).show();
        }
    }

}
