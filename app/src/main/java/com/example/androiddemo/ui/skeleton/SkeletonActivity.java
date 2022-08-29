
package com.example.androiddemo.ui.skeleton;

import android.app.LauncherActivity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.drag.DragActivity;
import com.example.androiddemo.ui.multilist.MultiListActivity;
import com.example.androiddemo.ui.permission.PermisstionActivity;
import com.example.androiddemo.ui.permission.util.permissionsetting.PermSetting;
import com.example.androiddemo.ui.zxing.QrCodeActivity;

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