package com.example.androiddemo.utils;

import android.content.Context;
import android.text.TextUtils;
import android.view.Gravity;
import android.widget.Toast;

/**
 * ToastUtil
 */
public class MsgUtil {

    /**
     * 正常提示
     *
     * @param ctx
     * @param msg
     */
    public static void toast(Context ctx, String msg) {
        if (!TextUtils.isEmpty(msg)) {
            Toast toast = Toast.makeText(ctx.getApplicationContext(), msg, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
        }
    }

    /**
     * 长时间提示
     *
     * @param ctx
     * @param msg
     */
    public static void longToast(Context ctx, String msg) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(ctx, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义时间提示
     *
     * @param ctx
     * @param msg
     * @param time
     */
    public static void selfToast(Context ctx, String msg, int time) {
        if (!TextUtils.isEmpty(msg))
            Toast.makeText(ctx, msg, time).show();
    }
}
