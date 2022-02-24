package com.example.androiddemo;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.androiddemo.ui.drag.DragActivity;
import com.example.androiddemo.ui.multilist.MultiListActivity;
import com.example.androiddemo.ui.zxing.QrCodeActivity;

public class MainActivity extends LauncherActivity {

    private Class<?>[] clazz = {
            QrCodeActivity.class
            , DragActivity.class
            , MultiListActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String names[] = {
                "扫描二维码"
                , "拖动调整应用位置与排序"
                , "多级列表"
        };

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(this, clazz[position]);
    }
}