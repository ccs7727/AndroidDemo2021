package com.example.androiddemo.ui.skeleton.gradientXml;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class Skeleton2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_skeleton1);
        findViewById(R.id.btn_list).setOnClickListener(view -> startActivity(new Intent(this, Skeleton2ListActivity.class)));
        findViewById(R.id.btn_view).setOnClickListener(view -> startActivity(new Intent(this, Skeleton2ViewActivity.class)));
    }
}
