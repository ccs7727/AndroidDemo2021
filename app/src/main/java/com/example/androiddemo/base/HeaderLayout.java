package com.example.androiddemo.base;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.androiddemo.R;


/**
 * Created by lzw on 14-9-17.
 */
public class HeaderLayout extends LinearLayout {
    LayoutInflater mInflater;
    RelativeLayout header;
    TextView titleView;
    LinearLayout leftContainer, rightContainer;
    TextView backBtn;
    TextView rightButton;

    public HeaderLayout(Context context) {
        super(context);
        init();
    }

    public HeaderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        mInflater = LayoutInflater.from(getContext());
        header = (RelativeLayout) mInflater.inflate(R.layout.chat_common_base_header, null, false);
        titleView = (TextView) header.findViewById(R.id.titleView);
        leftContainer = (LinearLayout) header.findViewById(R.id.leftContainer);
        rightContainer = (LinearLayout) header.findViewById(R.id.rightContainer);
        backBtn = (TextView) header.findViewById(R.id.backBtn);
        addView(header);
    }

    public void showTitle(int titleId) {
        showTitle(getResources().getString(titleId));
    }

    public void showTitle(String s) {
        titleView.setText(s);
    }

    public void showLeftBackButton() {
        showLeftBackButton(R.string.back_text, null);
    }

    public void showLeftBackButton(int backTextId, OnClickListener listener) {
        if (backTextId != 0) {
            backBtn.setVisibility(View.VISIBLE);
        } else {
            backBtn.setCompoundDrawables(null, null, null, null);
        }
        backBtn.setText(getResources().getString(backTextId));
        if (listener == null) {
            listener = new OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((Activity) getContext()).finish();
                }
            };
        }
        backBtn.setOnClickListener(listener);
    }

    /**
     * header布局右上角显示的一个按钮
     *
     * @param rightResId
     * @param listener
     */
    public void showRightImageButton(int rightResId, OnClickListener listener) {
        rightContainer.removeAllViews();//防止多次add view
        View imageViewLayout = mInflater.inflate(R.layout.chat_common_base_header_right_image_btn, null, false);
        ImageView rightButton = (ImageView) imageViewLayout.findViewById(R.id.imageBtn);
        rightButton.setImageResource(rightResId);
        rightButton.setOnClickListener(listener);
        rightContainer.addView(imageViewLayout);
    }

    public void showRightImageButton(boolean isShow) {
        //清除布局
        rightContainer.removeAllViews();
    }

    /**
     * header布局右上角显示两个按钮
     *
     * @param rightResId
     * @param listener
     */
    public void showRightImageButton2(int rightResId, int rightResId2, OnClickListener listener, OnClickListener listener2) {
        rightContainer.removeAllViews();//防止多次add view
        View imageViewLayout = mInflater.inflate(R.layout.chat_common_base_header_right_image_btn, null, false);
        ImageView imageBtn = (ImageView) imageViewLayout.findViewById(R.id.imageBtn);
        imageBtn.setImageResource(rightResId);
        imageBtn.setOnClickListener(listener);
        imageBtn.setVisibility(View.VISIBLE);
        ImageView imageBtn2 = (ImageView) imageViewLayout.findViewById(R.id.imageBtn2);
        imageBtn2.setImageResource(rightResId2);
        imageBtn2.setOnClickListener(listener2);
        imageBtn2.setVisibility(View.VISIBLE);
        rightContainer.addView(imageViewLayout);
    }

    public void showRightTextButton(String rightResId, OnClickListener listener) {
        rightContainer.removeAllViews();//防止多次add view
        View imageViewLayout = mInflater.inflate(R.layout.chat_common_base_header_right_btn, null, false);
        rightButton = (TextView) imageViewLayout.findViewById(R.id.textBtn);
        rightButton.setText(rightResId);
        rightButton.setOnClickListener(listener);
        rightContainer.addView(imageViewLayout);
    }

    public void showRightTextButtonEnable(boolean isEnable) {
        rightButton.setEnabled(isEnable);
    }
}
