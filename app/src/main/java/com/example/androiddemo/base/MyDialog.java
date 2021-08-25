package com.example.androiddemo.base;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

/**
 * 
 * @Description dialog样式
 * @date 2015年11月15日 下午5:56:13
 * 
 */
public class MyDialog extends Dialog {

	Context context;

	public MyDialog(Context context) {
		super(context);
		this.context = context;
	}

	public MyDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

}
