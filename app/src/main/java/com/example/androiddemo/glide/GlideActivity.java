package com.example.androiddemo.glide;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.androiddemo.R;
import com.example.androiddemo.glide.imageloader.FileUtil;
import com.example.androiddemo.glide.imageloader.ImageLoader;


public class GlideActivity extends AppCompatActivity {

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_glide);

        imageView = (ImageView) findViewById(R.id.imageview);


        int permission = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            explainDialog();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ImageLoader.getInstance().cancleThread();
    }

    public void showImage(View view) {
        ImageLoader imageLoader = ImageLoader.getInstance();
        imageLoader.setCachePath(FileUtil.getExternalStorageDirectory(this).getPath());
        imageLoader.display("https://lalzz.ahtelit.com/gw/api-global-oss/oss/preview/public/2022-12-10/1670660196128-20221210161620.jpg", imageView);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (grantResults != null && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

        } else {
            Toast.makeText(this, "用户拒绝了", Toast.LENGTH_SHORT).show();
        }
    }


    private void explainDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("应用需要获取您的读取文件权限,是否授权？")
                .setPositiveButton("确定", (dialog, which) -> {
                    //请求权限
                    ActivityCompat.requestPermissions(GlideActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                }).setNegativeButton("取消", null)
                .create().show();
    }

}
