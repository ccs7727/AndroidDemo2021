package com.example.androiddemo.ui.multicolor;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class MultiColorSeekbarActivity extends AppCompatActivity {

    ProgressBar progressBar;
    ImageView mSeekBarImg;
    Chronometer chronometer;
    int n = 1;//100
    int max = 72;//7200
    long downTime = 0;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_color_seekbar);
        progressBar = findViewById(R.id.progressBar);
        mSeekBarImg = findViewById(R.id.mSeekBarImg);
        chronometer = findViewById(R.id.chronometer);
        chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                setPrograss(downTime);
                downTime++;
            }
        });
    }

    private void setPrograss(Long base) {
        if (base <= 25) {
            progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_bule));
            progressBar.setProgress(base.intValue());
            mSeekBarImg.setImageResource(R.drawable.svg_seekbar1);
            chronometer.setTextColor(getColor(R.color.multi_color_blue));
        } else if (base <= 50) {
            progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_yellow));
            progressBar.setProgress(base.intValue());
            mSeekBarImg.setImageResource(R.drawable.svg_seekbar2);
            chronometer.setTextColor(getColor(R.color.multi_color_yellow));
        } else if (base <= 75) {
            progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_origin));
            progressBar.setProgress(base.intValue());
            mSeekBarImg.setImageResource(R.drawable.svg_seekbar3);
            chronometer.setTextColor(getColor(R.color.multi_color_origin));
        } else if (base <= 100) {
            progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_red));
            progressBar.setProgress(base.intValue());
            mSeekBarImg.setImageResource(R.drawable.svg_seekbar4);
            chronometer.setTextColor(getColor(R.color.multi_color_red));
        }
    }


    /**
     * 开始计时
     */
    public void doStart(View view) {
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    /**
     * 结束计时
     */
    public void doEnd(View view) {
        chronometer.stop();
        downTime = 0;
        progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_bule));
        progressBar.setProgress(0);
        mSeekBarImg.setImageResource(R.drawable.svg_seekbar1);
        chronometer.setTextColor(getColor(R.color.multi_color_blue));
    }

    /**
     * 0~1200
     */
    public void doBlue(View view) {
        progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_bule));
        progressBar.setProgress(0);
        mSeekBarImg.setImageResource(R.drawable.svg_seekbar1);
        chronometer.setTextColor(getColor(R.color.multi_color_blue));
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime());
        chronometer.start();
    }

    /**
     * 1200~1800
     */
    public void doYellow(View view) {
        progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_yellow));
        progressBar.setProgress(1600);
        mSeekBarImg.setImageResource(R.drawable.svg_seekbar2);
        chronometer.setTextColor(getColor(R.color.multi_color_yellow));
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime() - 1200000);
        chronometer.start();
    }

    /**
     * 1800~3600
     */
    public void doOrigin(View view) {
        progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_origin));
        progressBar.setProgress(2000);
        mSeekBarImg.setImageResource(R.drawable.svg_seekbar3);
        chronometer.setTextColor(getColor(R.color.multi_color_origin));
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime() - 1800000);
        chronometer.start();
    }

    /**
     * 3600~7200
     */
    public void doRed(View view) {
        progressBar.setProgressDrawable(getDrawable(R.drawable.multi_color_seekbar_red));
        progressBar.setProgress(5000);
        mSeekBarImg.setImageResource(R.drawable.svg_seekbar4);
        chronometer.setTextColor(getColor(R.color.multi_color_red));
        chronometer.stop();
        chronometer.setBase(SystemClock.elapsedRealtime() - 3600000);
        chronometer.start();
    }
}
