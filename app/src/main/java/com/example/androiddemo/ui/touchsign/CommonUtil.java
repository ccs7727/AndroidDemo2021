package com.example.androiddemo.ui.touchsign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;

import java.io.File;

public class CommonUtil {
    /**
     * 检查是否有SDCARD
     *
     * @return true if exists
     */
    public static boolean checkSDCard() {
        try {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 创建文件目录,如果目录不存在
     *
     * @param filePath
     */
    public static void createFolder(String filePath) {
        File folder = new File(filePath);
        if (!folder.exists()) {
            try {
                folder.mkdirs();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String getDBAbsolutePath(Context context) {
        String path = "";
        try {
//            if (Build.VERSION.SDK_INT >= 29) {
            path = context.getExternalFilesDir("/sign/").getAbsolutePath();
//            } else {
//                path = android.os.Environment.getExternalStorageDirectory().getAbsolutePath() + "/tky/";
//            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path;
    }

}