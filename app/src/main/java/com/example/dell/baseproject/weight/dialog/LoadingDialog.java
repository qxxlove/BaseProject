package com.example.dell.baseproject.weight.dialog;


import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;




public class LoadingDialog extends Dialog {

	private Context context;
	private FrameLayout dialogView;
	private TextView loadingMsgText;

	public LoadingDialog(Context context, int theme) {
		super(context, theme);
		this.context = context;
	}

	public LoadingDialog(Context context) {
		this(context, R.style.DialogTheme);
	}

	public LoadingDialog init(boolean   isCancelable) {
		View view = BaseUtils.inflate(context, R.layout.dialog_loading);// 得到加载view
		dialogView = (FrameLayout) view.findViewById(R.id.dialogView);
		this.setContentView(dialogView);// 设置布局

		if (isCancelable){

		}  else {
			this.setCancelable(false);
		}

		loadingMsgText = (TextView) view.findViewById(R.id.loadingMsg);
		return this;
	}

	public LoadingDialog setLoadingMsg(String loadingText) {
		loadingMsgText.setText(loadingText);
		return this;
	}


}
