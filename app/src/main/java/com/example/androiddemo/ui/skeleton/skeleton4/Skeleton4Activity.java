package com.example.androiddemo.ui.skeleton.skeleton4;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class Skeleton4Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_skeleton1);
        findViewById(R.id.btn_list).setOnClickListener(view -> startActivity(new Intent(this, Skeleton4ListActivity.class)));
//        findViewById(R.id.btn_view).setOnClickListener(view -> startActivity(new Intent(this, Skeleton4ViewActivity.class)));
        findViewById(R.id.btn_view).setOnClickListener(view -> startActivity(new Intent(this, Skeleton4ViewActivity2.class)));
    }
}
