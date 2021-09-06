package com.example.androiddemo.ui.zxing;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import com.example.androiddemo.base.BaseActivity;
import com.example.androiddemo.utils.Utils;

import cn.bertsir.zbar.Qr.ScanResult;
import cn.bertsir.zbar.QrConfig;
import cn.bertsir.zbar.QrManager;
import cn.bertsir.zbar.view.ScanLineView;

/**
 * 扫描二维码
 */
public class QrCodeActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        QrConfig qrConfig = Utils.initScan();
        QrManager.getInstance().init(qrConfig).startScan(this, new QrManager.OnScanResultCallback() {
            @Override
            public void onScanSuccess(ScanResult result) {
                Log.e("TAG", "onScanSuccess: " + result.getContent());
            }
        });
    }

}