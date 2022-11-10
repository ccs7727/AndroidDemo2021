package com.example.androiddemo;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.androiddemo.ui.date.DateStyleActivity;
import com.example.androiddemo.ui.drag.DragActivity;
import com.example.androiddemo.ui.multilist.MultiListActivity;
import com.example.androiddemo.ui.permission.PermisstionActivity;
import com.example.androiddemo.ui.seekbar.SeekbarActivity;
import com.example.androiddemo.ui.skeleton.skeleton1.Skeleton1Activity;
import com.example.androiddemo.ui.skeleton.skeleton2.Skeleton2Activity;
import com.example.androiddemo.ui.skeleton.skeleton3.Skeleton3Activity;
import com.example.androiddemo.ui.skeleton.skeleton4.Skeleton4Activity;
import com.example.androiddemo.ui.slidelock.SlideLockActivity;
import com.example.androiddemo.ui.zxing.QrCodeActivity;

public class MainActivity extends LauncherActivity {

    private Class<?>[] clazz = {
            QrCodeActivity.class
            , DragActivity.class
            , MultiListActivity.class
            , PermisstionActivity.class
            , Skeleton1Activity.class
            , Skeleton2Activity.class
            , Skeleton3Activity.class
            , Skeleton4Activity.class
            , DateStyleActivity.class
            , SlideLockActivity.class
            , SeekbarActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String names[] = {
                "扫描二维码"
                , "拖动调整应用位置与排序"
                , "多级列表"
                , "权限设置"
                , "骨架加载方案一"
                , "骨架加载方案二"
                , "骨架加载方案三"
                , "骨架加载方案四"
                , "自定义日期样式"
                , "滑动解锁"
                , "垂直Seekbar"
        };

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(this, clazz[position]);
    }
}