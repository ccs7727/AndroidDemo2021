package com.example.androiddemo.ui.skeleton;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ethanhua.skeleton.Skeleton;
import com.ethanhua.skeleton.SkeletonScreen;
import com.example.androiddemo.R;
import com.example.androiddemo.ui.skeleton.adapter.NewsAdapter;

/**
 * Created by ethanhua on 2017/7/27.
 */

public class Skeleton1ListActivity extends AppCompatActivity {


    private static final String PARAMS_TYPE = "params_type";
    public static final String TYPE_LINEAR = "type_linear";
    public static final String TYPE_GRID = "type_grid";
    private String mType;

    public static void start(Context context, String type) {
        Intent intent = new Intent(context, Skeleton1ListActivity.class);
        intent.putExtra(PARAMS_TYPE, type);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);
        mType = getIntent().getStringExtra(PARAMS_TYPE);
        init();
    }


    private void init() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        if (TYPE_LINEAR.equals(mType)) {
            recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            NewsAdapter adapter = new NewsAdapter();
            final SkeletonScreen skeletonScreen = Skeleton.bind(recyclerView)
                    .adapter(adapter)
                    .shimmer(true)
                    .angle(20)
                    .frozen(false)
                    .duration(1200)
                    .count(10)
                    .load(R.layout.item_skeleton_news)
                    .show();
            recyclerView.postDelayed(() -> skeletonScreen.hide(), 3000);
            return;
        }
    }

}

