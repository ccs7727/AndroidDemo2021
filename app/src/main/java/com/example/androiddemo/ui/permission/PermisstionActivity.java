package com.example.androiddemo.ui.permission;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androiddemo.R;
import com.example.androiddemo.ui.permission.util.permissionsetting.PermSetting;


public class PermisstionActivity extends AppCompatActivity {

    private int REQUEST_CODE = 100;

    private int launchMode = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        findViewById(R.id.btn_go).setOnClickListener(v ->
                launchMode = PermSetting.gotoPermSetting(PermisstionActivity.this, REQUEST_CODE));
    }

    @Override
    protected void onResume() {
        if (launchMode == ActivityInfo.LAUNCH_SINGLE_TASK || launchMode == ActivityInfo.LAUNCH_SINGLE_INSTANCE) {
            //LAUNCH_SINGLE_TASK,LAUNCH_SINGLE_INSTANCE这两种情况下onResume才是真正的从设置页面返回
            // TODO: 再次判断权限
            Toast.makeText(PermisstionActivity.this, "从权限设置页面返回 launchMode = " + launchMode, Toast.LENGTH_SHORT).show();
            launchMode = -1;
        }
        super.onResume();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        //LAUNCH_SINGLE_TASK,LAUNCH_SINGLE_INSTANCE情况下还没有从设置页面回来就会回调onActivityResult，不适合做后续动作，如再次判断权限
        if (REQUEST_CODE == requestCode && launchMode != ActivityInfo.LAUNCH_SINGLE_TASK && launchMode != ActivityInfo.LAUNCH_SINGLE_INSTANCE) {
            Toast.makeText(PermisstionActivity.this, "从权限设置页面返回 launchMode = " + launchMode, Toast.LENGTH_SHORT).show();
            // TODO: 再次判断权限
            launchMode = -1;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
