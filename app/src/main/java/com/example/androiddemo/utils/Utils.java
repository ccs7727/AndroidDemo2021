package com.example.androiddemo.utils;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.io.File;

import cn.bertsir.zbar.Qr.ScanResult;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bertsir.zbar.view.ScanLineView;

/**
 * Created by mafei on 16/1/19.
 */
public class Utils {
    public static String getImgName(String imgUrl) {
        if (imgUrl == null || imgUrl.equals("")) {
            return "";
        }
        String[] strs = imgUrl.split("/");
        return strs[strs.length - 1];
    }

    public static void deleteFile(String path) {
        deleteFile(new File(path));
    }

    public static void deleteFile(File oldPath) {
        try {
            if (oldPath.isDirectory()) {
                File[] files = oldPath.listFiles();
                for (File file : files) {
                    deleteFile(file);
                    file.delete();
                }
            } else {
                oldPath.delete();
            }
        } catch (Exception e) {

        }
    }

    public static QrConfig initScan() {
        QrConfig qrConfig = new QrConfig.Builder()
                .setDesText("扫描登录智慧林业平台电脑端\n将二维码放入扫描框内即可自动扫描")//扫描框下文字
                .setShowDes(true)//是否显示扫描框下面文字
                .setShowLight(false)//显示手电筒按钮
                .setShowTitle(true)//显示Title
                .setShowAlbum(false)//显示从相册选择按钮
                .setCornerColor(Color.parseColor("#1FAB64"))//设置扫描框颜色
                .setLineColor(Color.parseColor("#1FAB64"))//设置扫描线颜色
                .setLineSpeed(QrConfig.LINE_MEDIUM)//设置扫描线速度
                .setScanType(QrConfig.TYPE_QRCODE)//设置扫码类型（二维码，条形码，全部，自定义，默认为二维码）
                .setScanViewType(QrConfig.SCANVIEW_TYPE_QRCODE)//设置扫描框类型（二维码还是条形码，默认为二维码）
                .setCustombarcodeformat(QrConfig.BARCODE_I25)//此项只有在扫码类型为TYPE_CUSTOM时才有效
                .setPlaySound(true)//是否扫描成功后bi~的声音
                .setNeedCrop(false)//从相册选择二维码之后再次截取二维码
//                .setDingPath(R.raw.test)//设置提示音(不设置为默认的Ding~)
                .setIsOnlyCenter(true)//是否只识别框中内容(默认为全屏识别)
                .setTitleText("扫一扫")//设置Tilte文字
                .setTitleBackgroudColor(Color.TRANSPARENT)//设置状态栏颜色
                .setTitleTextColor(Color.WHITE)//设置Title文字颜色
                .setShowZoom(false)//是否手动调整焦距
                .setAutoZoom(false)//是否自动调整焦距
                .setFingerZoom(false)//是否开始双指缩放
                .setScreenOrientation(QrConfig.SCREEN_PORTRAIT)//设置屏幕方向
                .setDoubleEngine(false)//是否开启双引擎识别(仅对识别二维码有效，并且开启后只识别框内功能将失效)
                .setOpenAlbumText("选择要识别的图片")//打开相册的文字
                .setLooperScan(false)//是否连续扫描二维码
                .setLooperWaitTime(5 * 1000)//连续扫描间隔时间
                .setScanLineStyle(ScanLineView.style_radar)//扫描动画样式
                .setAutoLight(false)//自动灯光
                .setShowVibrator(false)//是否震动提醒
                .create();

        return  qrConfig;

    }
}
