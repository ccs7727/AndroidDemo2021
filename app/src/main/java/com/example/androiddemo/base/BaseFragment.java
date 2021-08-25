package com.example.androiddemo.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.androiddemo.R;
import com.example.androiddemo.utils.MsgUtil;
import com.example.androiddemo.utils.Util;


/**
 * Fragment基础继承类
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected HeaderLayout headerLayout;
    protected Context ctx;
    protected MyDialog myDialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ctx = getActivity();
        headerLayout = (HeaderLayout) getView().findViewById(R.id.headerLayout);
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


    public void onClick(View v) {
    }


    /**
     * 通过Class跳转界面 *
     */
    protected void startActivity(Class<?> cls) {
        startActivity(cls, null);
    }

    /**
     * 含有Bundle通过Class跳转界面 *
     */
    protected void startActivity(Class<?> cls, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(getActivity(), cls);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().overridePendingTransition(R.anim.push_left_in,
                R.anim.push_left_out);
    }

    protected void showDialog(String message, View.OnClickListener yesListener, View.OnClickListener cancelListener) {
        showDialog("提示", message, "确定", "取消", yesListener, cancelListener, true);
    }

    protected void showDialog(String title, String message, String yesText, String cancelText, View.OnClickListener yesListener, View.OnClickListener cancelListener, boolean isCanCancel) {
        myDialog = new MyDialog(getActivity(), R.style.dialog_style);
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

    /**
     * Fragment当前状态是否可见
     */
    protected boolean isVisible;


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }


    /**
     * 可见
     */
    protected void onVisible() {
        lazyLoad();
    }


    /**
     * 不可见
     */
    protected void onInvisible() {

    }


    /**
     * 延迟加载
     * 子类必须重写此方法
     */
    protected abstract void lazyLoad();
}
