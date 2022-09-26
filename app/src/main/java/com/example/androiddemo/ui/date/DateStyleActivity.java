package com.example.androiddemo.ui.date;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.skeleton.skeleton4.Skeleton4ListActivity;
import com.example.androiddemo.ui.skeleton.skeleton4.Skeleton4ViewActivity2;

public class DateStyleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_style);
        findViewById(R.id.btn_list).setOnClickListener(view -> startActivity(new Intent(this, Skeleton4ListActivity.class)));
//        findViewById(R.id.btn_view).setOnClickListener(view -> startActivity(new Intent(this, Skeleton4ViewActivity.class)));
        findViewById(R.id.btn_view).setOnClickListener(view -> startActivity(new Intent(this, Skeleton4ViewActivity2.class)));
    }
}
