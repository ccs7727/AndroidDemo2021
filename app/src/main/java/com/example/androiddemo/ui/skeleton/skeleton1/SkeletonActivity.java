
package com.example.androiddemo.ui.skeleton.skeleton1;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

/**
 * 骨架加载
 */
public class SkeletonActivity extends LauncherActivity {

    private Class<?>[] clazz = {
            Skeleton1Activity.class
            ,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String names[] = {
                "方案一"
                ,
        };

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(this, clazz[position]);
    }
}