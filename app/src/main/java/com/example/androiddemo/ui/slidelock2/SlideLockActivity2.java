package com.example.androiddemo.ui.slidelock2;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.slidelock2.wdiget.SlideLockView;

public class SlideLockActivity2 extends AppCompatActivity {
    private SlideLockView mCustomSlideToUnlockView;
    private TextView tv_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_slide_lock);

        mCustomSlideToUnlockView = findViewById(R.id.slide_to_unlock);
        tv_text = findViewById(R.id.tv_hint);

        SlideLockView.CallBack callBack = new SlideLockView.CallBack() {
            @Override
            public void onAlpha(float alpha) {
                tv_text.setAlpha(alpha);
            }

            @Override
            public void onUnlocked() {
                Log.e("", "已解锁");
            }
        };
        mCustomSlideToUnlockView.setmCallBack(callBack);
    }
}
