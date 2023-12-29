package com.example.androiddemo.ui.alarmmanager;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Vibrator;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.alarmmanager.util.AlarmManagerUtil;
import com.example.androiddemo.ui.alarmmanager.util.ReceiverUtils;
import com.example.androiddemo.ui.alarmmanager.util.SimpleDialog;

import java.util.Calendar;

/**
 * 定时提醒
 */
public class AlarmManagerActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView date_tv, index_tv;
    private Button reset_btn, cancel_btn;
    private String time;
    private int ring = 2;
    private MediaPlayer mediaPlayer;
    private Vibrator vibrator;
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm_manager);
        reset_btn = findViewById(R.id.reset_btn);
        reset_btn.setOnClickListener(this);
        cancel_btn = findViewById(R.id.cancel_btn);
        cancel_btn.setOnClickListener(this);
        date_tv = findViewById(R.id.date_tv);
        index_tv = findViewById(R.id.index_tv);
        ReceiverUtils.registerReceiver(this, receiver, new IntentFilter(AlarmManagerUtil.ALARM_ACTION));
    }

    private final BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            runOnUiThread(() -> {
                String msg = intent.getStringExtra("msg");
                int flag = intent.getIntExtra("soundOrVibrator", 0);
                showDialogInBroadcastReceiver(msg, flag);
            });
        }
    };

    private void initTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = (calendar.get(Calendar.MINUTE) + index);
        time = (hour < 10 ? ("0" + hour) : hour) + ":" + (minute < 10 ? ("0" + minute) : minute);
        if (TextUtils.isEmpty(date_tv.getText().toString())) {
            date_tv.setText(time);
        } else {
            date_tv.setText(date_tv.getText().toString() + "," + time);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.reset_btn://新增
                index++;
                initTime();
                setClock();
                index_tv.setText("" + index);
                break;
            case R.id.cancel_btn://取消
                AlarmManagerUtil.cancelAlarm(this, index);
                if (index > 1) {
                    index--;
                    index_tv.setText("" + index);
                    date_tv.setText(date_tv.getText().toString().substring(0, date_tv.getText().toString().lastIndexOf(",")));
                }
                break;
        }
    }

    private void setClock() {
        if (time != null && time.length() > 0) {
            String[] times = time.split(":");
            Calendar calendar = Calendar.getInstance();
            AlarmManagerUtil.setAlarm(this,
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH),
                    Integer.parseInt(times[0]),
                    Integer.parseInt(times[1]),
                    index, "闹钟响了-demo-" + index, ring);
        }
    }

    private void showDialogInBroadcastReceiver(String message, final int flag) {
        if (flag == 1 || flag == 2) {
            if (mediaPlayer == null) {
                mediaPlayer = MediaPlayer.create(this, R.raw.in_call_alarm);
                mediaPlayer.setLooping(true);
            }
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
            }
            mediaPlayer.start();
        }
        //数组参数意义：第一个参数为等待指定时间后开始震动，震动时间为第二个参数。后边的参数依次为等待震动和震动的时间
        //第二个参数为重复次数，-1为不重复，0为一直震动
        if (flag == 0 || flag == 2) {
            if (vibrator != null && vibrator.hasVibrator()) {
                vibrator.cancel();
            }
            vibrator = (Vibrator) this.getSystemService(Service.VIBRATOR_SERVICE);
            vibrator.vibrate(new long[]{0, 1000, 1000, 1000}, 0);
        }

        final SimpleDialog dialog = new SimpleDialog(this, R.style.Theme_dialog);
        dialog.show();
        dialog.setTitle("闹钟提醒");
        dialog.setMessage(message);
        dialog.setClickListener(v -> {
            if (dialog.bt_confirm == v || dialog.bt_cancel == v) {
                if (flag == 1 || flag == 2) {
                    mediaPlayer.pause();
                }
                if (flag == 0 || flag == 2) {
                    vibrator.cancel();
                }
                dialog.dismiss();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ReceiverUtils.unregisterReceiver(this, receiver);
        if (null != mediaPlayer) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        if (vibrator != null) {
            if (vibrator.hasVibrator()) {
                vibrator.cancel();
            }
            vibrator = null;
        }
    }
}
