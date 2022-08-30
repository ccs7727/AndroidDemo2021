package com.example.androiddemo.ui.skeleton.skeleton2;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;

import io.rmiri.skeleton.SkeletonViewGroup;

public class Skeleton2ViewActivity extends AppCompatActivity {

    private SkeletonViewGroup skeletonViewGroup;
    private TextView textTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_xml);
        skeletonViewGroup = findViewById(R.id.skeletonGroup);
        textTv = findViewById(R.id.textTv);
        skeletonViewGroup.setSkeletonListener(new SkeletonViewGroup.SkeletonListener() {
            @Override
            public void onStartAnimation() {

            }

            @Override
            public void onFinishAnimation() {
                textTv.setText("The Android O release ultimately became Android 8.0 Oreo, as predicted by pretty much everyone the first time they thought of a sweet");
            }
        });
        skeletonViewGroup.postDelayed(() -> skeletonViewGroup.finishAnimation(), 3000);
    }

}
