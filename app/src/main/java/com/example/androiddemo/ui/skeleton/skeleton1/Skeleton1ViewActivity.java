package com.example.androiddemo.ui.skeleton.skeleton1;

import android.os.Bundle;
import android.os.Message;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.androiddemo.R;
import com.example.androiddemo.ui.skeleton.skeleton1.adapter.NewsAdapter;

import java.lang.ref.WeakReference;

public class Skeleton1ViewActivity extends AppCompatActivity {

    private SkeletonScreen skeletonScreen;

    public static class MyHandler extends android.os.Handler {
        private final WeakReference<Skeleton1ViewActivity> activityWeakReference;

        MyHandler(Skeleton1ViewActivity activity) {
            this.activityWeakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (activityWeakReference.get() != null) {
                activityWeakReference.get().skeletonScreen.hide();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        View rootView = findViewById(R.id.rootView);
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        NewsAdapter adapter = new NewsAdapter();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
        skeletonScreen = Skeleton.bind(rootView)
                .load(R.layout.activity_view_skeleton)
                .duration(1000)
                .color(R.color.shimmer_color)
                .angle(0)
                .show();
        MyHandler myHandler = new MyHandler(this);
        myHandler.sendEmptyMessageDelayed(1, 3000);
    }


}
