package com.example.androiddemo.ui.slidelock;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

public class SlideLockActivity extends AppCompatActivity {

    private CustomSlideToUnlockView mCustomSlideToUnlockView;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_lock);
        mCustomSlideToUnlockView = findViewById(R.id.slide_to_unlock);
        tv_text = findViewById(R.id.tv_text);

        CustomSlideToUnlockView.CallBack callBack = new CustomSlideToUnlockView.CallBack() {
            @Override
            public void onSlide(int distance) {
                tv_text.setText("slide distance:" + distance);
            }

            @Override
            public void onUnlocked() {
                tv_text.setText("onUnlocked");
            }
        };
        mCustomSlideToUnlockView.setmCallBack(callBack);
        findViewById(R.id.button).setOnClickListener(view -> mCustomSlideToUnlockView.resetView());
    }
}
