package com.example.androiddemo.ui.seekbar;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class SeekbarActivity extends AppCompatActivity {

    private static final String TAG = "LXP_MainActivity";

    VerticalSeekBar mSeekBar;
    ImageView img;
    TextView gracess, gracess2, gracess3, gracess0;

    int max = 16384000;
    int min = 2000;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seekbar);

        mSeekBar = findViewById(R.id.mSeekBar);
        img = findViewById(R.id.img);
        gracess = findViewById(R.id.gracess);
        gracess2 = findViewById(R.id.gracess2);
        gracess3 = findViewById(R.id.gracess3);
        gracess0 = findViewById(R.id.gracess0);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                gracess.setText("进度条：" + progress);
                gracess0.setText("进度条2：" + (max + min - progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        mSeekBar.setProgress(8192000);

        img.setOnTouchListener((v, event) -> {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    img.setVisibility(View.VISIBLE);
                    mSeekBar.setVisibility(View.VISIBLE);
                    break;
                case MotionEvent.ACTION_MOVE:
                    gracess2.setText("getY：" + event.getY());
                    int progress2 = (int) ((event.getY() / mSeekBar.getHeight()) * (min - max)+ min);
                    gracess3.setText("高度：" +  mSeekBar.getHeight());

//                    float y = event.getY();
//                    int h = mSeekBar.getHeight();
//                    gracess2.setText("getY：" + y);
//                    int progress2 = (int) ((y / h) * (min - max) + min);
//                    gracess3.setText("高度：" + h);
                    mSeekBar.setProgress(progress2);
                    break;
                case MotionEvent.ACTION_UP:
//                    img.setVisibility(View.VISIBLE);
//                    mSeekBar.setVisibility(View.VISIBLE);
                    break;
                default:
                    return super.onTouchEvent(event);
            }
            return true;
        });

    }
}
