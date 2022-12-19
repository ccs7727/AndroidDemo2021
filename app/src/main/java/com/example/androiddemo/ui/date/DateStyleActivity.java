package com.example.androiddemo.ui.date;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.date.widget.DateStyleView;

import java.util.ArrayList;
import java.util.List;

public class DateStyleActivity extends AppCompatActivity {

    DateStyleView datePicker;
    DateStyleView datePicker2;
    DateStyleView datePicker3;
    TextView tvDate;
    TextView tvDate2;
    TextView tvDate3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_style);
        datePicker = findViewById(R.id.datePicker);
        datePicker2 = findViewById(R.id.datePicker2);
        datePicker3 = findViewById(R.id.datePicker3);
        tvDate = findViewById(R.id.tvDate);
        tvDate2 = findViewById(R.id.tvDate2);
        tvDate3 = findViewById(R.id.tvDate3);

        //上一月下一月监听器
        datePicker.initCalendar();
        datePicker.setOnLastClickListener((year, month) -> tvDate.setText(year + "-" + month));
        datePicker.setOnNextClickListener((year, month) -> tvDate.setText(year + "-" + month));

        //上一年下一年监听器
        datePicker2.setType(1);//年
        datePicker2.initCalendar();
        datePicker2.setOnLastClickListener((year, month) -> tvDate2.setText(year));
        datePicker2.setOnNextClickListener((year, month) -> tvDate2.setText(year));

        //上一年下一年监听器
        datePicker3.setType(1);//年
        datePicker3.initCalendar();
        datePicker3.setOnLastClickListener((year, month) -> tvDate3.setText(year));
        datePicker3.setOnNextClickListener((year, month) -> tvDate3.setText(year));
        datePicker3.initYear(getData());
    }

    private List<String> getData() {
        List<String> list = new ArrayList<>();
        list.add("2018");
        list.add("2019");
        list.add("2020");
        list.add("2021");
        list.add("2022");
        return list;
    }
}
