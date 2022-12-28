package com.example.androiddemo;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_splash);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                startApp();
            }
        }, 1500L);
    }

    private void startApp() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
