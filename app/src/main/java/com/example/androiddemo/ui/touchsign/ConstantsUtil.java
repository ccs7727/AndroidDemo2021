package com.example.androiddemo.ui.touchsign;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;

import java.io.File;

public class ConstantsUtil {

    // 图片存储的地址
    public static String IMG_FOLDER_PATH = FilePath.PATH_APP_STORAGE_BASE + "imgOnline/";

    /**
     * 各种文件路径
     */
    public static class FilePath {

        public static FilePath instance;
        /**
         * 应用的 文件根目录
         */
        public static String PATH_APP_STORAGE_BASE;


        public static FilePath getInstance(Context context) {
            if (instance == null) {
                instance = new FilePath();
                if (CommonUtil.checkSDCard()) {
                    PATH_APP_STORAGE_BASE = CommonUtil.getDBAbsolutePath(context) + "/signOnline/";
                } else {
                    String path = "/mnt/sdcard2/";
                    File file = new File(path);
                    if (!file.exists()) {
                        path = "/mnt/sdcard/";
                    }
                    PATH_APP_STORAGE_BASE = path + "/signOnline/";
                }
                CommonUtil.createFolder(ConstantsUtil.FilePath.PATH_APP_STORAGE_BASE);
            }
            return instance;
        }
    }

    //缩小图片
    public static Bitmap smallImage(Bitmap bitmapOrg, int newWidth) {
        //获取这个图片的宽和高
        int width = bitmapOrg.getWidth();
        int height = bitmapOrg.getHeight();

        //定义预转换成的图片的宽度和高度
//        int newHeight = 960;

        //计算缩放率，新尺寸除原始尺寸
        float scaleWidth = ((float) newWidth) / width;
//        float scaleHeight = ((float) newHeight) / height;

        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();

        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleWidth);

        // 创建新的图片
        return Bitmap.createBitmap(bitmapOrg, 0, 0,
                width, height, matrix, true);
    }

}


