package com.example.androiddemo.base;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.example.androiddemo.R;
import com.example.androiddemo.utils.MsgUtil;
import com.example.androiddemo.utils.StackManager;
import com.example.androiddemo.utils.StatusBarUtil;
import com.example.androiddemo.utils.Util;

/**
 * 供其他activity继承
 * SwipeBackActivity支持手势滑动返回
 */
public class BaseActivity extends Activity implements OnClickListener{

    public Context ctx;
    public static Dialog loadingDialog;
//    public SwipeBackLayout mSwipeBackLayout;
    protected HeaderLayout headerLayout;
    protected MyDialog myDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ctx = this;
        StackManager.getStackManager().pushActivity(this);
//        mSwipeBackLayout = getSwipeBackLayout();
//        mSwipeBackLayout.setEdgeTrackingEnabled(SwipeBackLayout.EDGE_LEFT);

        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        }
        headerLayout = (HeaderLayout) findViewById(R.id.headerLayout);
        //沉浸式导航栏
        setStatusBar();
    }


    protected int getLayoutId() {
        return 0;
    }



    /**
     * 通过Class跳转界面 *
     */
    public void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面 *
     */
    public void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        this.overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }


    /**
     * 返回结果的跳转界面 *
     */
    public void startActivityForResult(Class<?> cls, int requestCode,
                                       Bundle bundle) {

        Intent intent = new Intent();
        intent.setClass(this, cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivityForResult(intent, requestCode);
        this.overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }

    /**
     * 返回结果的跳转界面 *
     */
    public void startActivityForResult(Class<?> cls, int requestCode) {
        startActivityForResult(cls, requestCode, null);
    }

    /**
     * 返回界面 *
     */
    public void setResult(int resultCode, Bundle bundle) {
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        setResult(resultCode, intent);
        finish();
    }

    /**
     * 没有参数返回界面 *
     */
    public void setResultNoData(int resultCode) {
        setResult(resultCode);
        finish();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.push_right_in, R.anim.push_right_out);
    }

    public void doBack(View view) {
        finish();
    }

    @Override
    public void onClick(View v) {
    }




    @Override
    protected void onDestroy() {
        StackManager.getStackManager().removeActivity(this);
        super.onDestroy();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return super.dispatchTouchEvent(ev);
    }

    protected void longToast(String msg) {
        MsgUtil.longToast(ctx, msg);
    }

    protected void longToast(int msgId) {
        MsgUtil.longToast(ctx, getString(msgId));
    }

    protected void Toast(String msg) {
        MsgUtil.toast(ctx, msg);
    }

    protected void Toast(int msgId) {
        MsgUtil.toast(ctx, getString(msgId));
    }



    protected void setStatusBar() {
        StatusBarUtil.setColor(this, getColor(this, R.color.app_color));
    }

    /**
     * getColor(int id)在API23时过时
     *
     * @param context
     * @param id
     * @return
     */
    public static int getColor(Context context, int id) {
        return context.getResources().getColor(id);
    }

    protected void showDialog(String message, OnClickListener yesListener, OnClickListener cancelListener) {
        showDialog("提示", message, "确定", "取消", yesListener, cancelListener, true);
    }

    protected void showDialog(String title, String message, String yesText, String cancelText, OnClickListener yesListener, OnClickListener cancelListener, boolean isCanCancel) {
        myDialog = new MyDialog(this, R.style.dialog_style);
        myDialog.setContentView(R.layout.dialog_loginout_layout);
        myDialog.setCanceledOnTouchOutside(isCanCancel);
        myDialog.setCancelable(isCanCancel);
        myDialog.show();
        TextView titleView = (TextView) myDialog.findViewById(R.id.dialog_title);
        TextView layout_btn_yes = (TextView) myDialog.findViewById(R.id.dialog_yes);
        TextView layout_btn_cancel = (TextView) myDialog.findViewById(R.id.dialog_cancel);
        TextView msg = (TextView) myDialog.findViewById(R.id.dialog_msg);
        titleView.setText(title);
        msg.setText(message);
        layout_btn_yes.setText(yesText);
        if (Util.isEmpty(cancelText)) {
            layout_btn_cancel.setVisibility(View.GONE);
        } else {
            layout_btn_cancel.setText(cancelText);
            layout_btn_cancel.setOnClickListener(cancelListener);
        }
        layout_btn_yes.setOnClickListener(yesListener);
        myDialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                // 对话框消失则关闭当前界面
            }
        });

    }

}
