package com.example.androiddemo;

import android.app.LauncherActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.example.androiddemo.glide.GlideActivity;
import com.example.androiddemo.ui.alarmmanager.AlarmManagerActivity;
import com.example.androiddemo.ui.calendar.CalenderDemoActivity;
import com.example.androiddemo.ui.coordinatorLayout.CoordinatorLayoutTestActivity;
import com.example.androiddemo.ui.date.DateStyleActivity;
import com.example.androiddemo.ui.drag.DragActivity;
import com.example.androiddemo.ui.list.RecyclerViewListActivity;
import com.example.androiddemo.ui.lottie.LottieAnimActivity;
import com.example.androiddemo.ui.multicolor.MultiColorSeekbarActivity;
import com.example.androiddemo.ui.multilist.MultiListActivity;
import com.example.androiddemo.ui.permission.PermisstionActivity;
import com.example.androiddemo.ui.seekbar.SeekbarActivity;
import com.example.androiddemo.ui.skeleton.skeleton1.Skeleton1Activity;
import com.example.androiddemo.ui.skeleton.skeleton2.Skeleton2Activity;
import com.example.androiddemo.ui.skeleton.skeleton3.Skeleton3Activity;
import com.example.androiddemo.ui.skeleton.skeleton4.Skeleton4Activity;
import com.example.androiddemo.ui.slidelock.SlideLockActivity;
import com.example.androiddemo.ui.slidelock2.SlideLockActivity2;
import com.example.androiddemo.ui.touchsign.TouchSingleActivity;
import com.example.androiddemo.ui.watermark.WaterMarkActivity;
import com.example.androiddemo.ui.zxing.QrCodeActivity;

public class MainActivity extends LauncherActivity {

    private Class<?>[] clazz = {
            QrCodeActivity.class
            , DragActivity.class
            , MultiListActivity.class
            , RecyclerViewListActivity.class
            , CoordinatorLayoutTestActivity.class
            , DateStyleActivity.class
            , SlideLockActivity2.class
            , MultiColorSeekbarActivity.class
            , TouchSingleActivity.class
            , WaterMarkActivity.class
            , LottieAnimActivity.class
            , CalenderDemoActivity.class
            , AlarmManagerActivity.class
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String names[] = {
                "扫描二维码"
                , "拖动调整应用位置与排序"
                , "多级列表"
                , "RecyclerView横向滚动"
                , "滑动悬停"
                , "自定义年月、年左右选择控件"
                , "滑动解锁"
                , "多色横向进度条"
                , "手写电子签名"
                , "图片添加水印"
                , "Lottie加载json动画"
                , "创建系统日历提醒"
                , "定时提醒"
        };

        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, names));
    }

    @Override
    protected Intent intentForPosition(int position) {
        return new Intent(this, clazz[position]);
    }
}