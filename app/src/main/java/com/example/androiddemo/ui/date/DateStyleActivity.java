package com.example.androiddemo.ui.date;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.date.widget.DateStyleView;

public class DateStyleActivity extends AppCompatActivity {

    DateStyleView datePicker2;
    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_style);
        datePicker2 = findViewById(R.id.datePicker2);
        tvDate = findViewById(R.id.tvDate);

        //点击上月下月监听器
        datePicker2.setOnLastClickListener((year, month) -> tvDate.setText(year + "-" + month));
        datePicker2.initCalendar();
    }

}
