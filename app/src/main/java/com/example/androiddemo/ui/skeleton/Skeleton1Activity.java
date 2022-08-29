package com.example.androiddemo.ui.skeleton;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class Skeleton1Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_skeleton1);
        findViewById(R.id.btn_list).setOnClickListener(view -> Skeleton1ListActivity.start(Skeleton1Activity.this, Skeleton1ListActivity.TYPE_LINEAR));
        findViewById(R.id.btn_view).setOnClickListener(view -> Skeleton1ViewActivity.start(Skeleton1Activity.this, Skeleton1ViewActivity.TYPE_VIEW));
    }
}
