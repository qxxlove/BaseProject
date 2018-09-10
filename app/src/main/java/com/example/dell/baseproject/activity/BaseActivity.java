package com.example.dell.baseproject.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.base.App;
import com.example.dell.baseproject.bean.ActionBarStyle;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.utils.LogUtils;
import com.example.dell.baseproject.utils.SystemBarTintManager;
import com.example.dell.baseproject.weight.dialog.LoadingDialog;

import java.lang.reflect.Field;
import java.util.List;

/**
 * @ClassName BaseActivity
 * @Description TODO(Activity基类)
 * @author txb
 * @Date 2016年12月8日 下午1:52:29
 * @version 1.0.0
 */
public abstract class BaseActivity extends FragmentActivity {
	private FrameLayout baseLayout;
	protected App mApplication;
	protected Context mContext;
	private Button leftBtn, rightBtn;
	private TextView middleText, rightText;
	private ProgressBar progressBar;
	protected static Handler mHandler = new Handler(Looper.getMainLooper());
	private RelativeLayout trp_relative;
	private  AlertDialog.Builder builder;


	public static final int PERMISSION_DENIEG = 1;//权限不足，权限被拒绝的时候
	public static final int PERMISSION_REQUEST_CODE = 0;//系统授权管理页面时的结果参数
	public static final String PACKAGE_URL_SCHEME = "package:";//权限方案
	private boolean isrequestCheck = true;//判断是否需要系统权限检测。防止和系统提示框重叠

	public abstract int initContentID();


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		try {
			mApplication = App.getInstance();
			mContext = this;
			initActionBar();
			initView(savedInstanceState);
			initTint();
			initData();
			initListener();
			execHttp();
			mApplication.putActivity(this);

			initContentID();
			//process(savedInstanceState);

		} catch (Exception e) {
			BaseUtils.toast(this,e.getMessage());
			LogUtils.e(this.getClass().getSimpleName() + "初始化失败！");
			e.printStackTrace();
		}
	}

	public void process(Bundle savedInstanceState) {
		if (getPermissions() != null) {
			if (BaseUtils.permissionSet(getPermissions())) {
				requestPermissions(getPermissions());     //去请求权限
			} else {
				getAllGrantedPermission();
			}
		}

	}

	/**
	 * 请求权限去兼容版本
	 */
	public void requestPermissions(String... permission) {
		ActivityCompat.requestPermissions(this, permission, PERMISSION_REQUEST_CODE);
	}

	/**
	 * 用于权限管理
	 * 如果全部授权的话，则直接通过进入
	 * 如果权限拒绝，缺失权限时，则使用dialog提示
	 *
	 * @param requestCode  请求代码
	 * @param permissions  权限参数
	 * @param grantResults 结果
	 */
	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (PERMISSION_REQUEST_CODE == requestCode && hasAllPermissionGranted(grantResults)) //判断请求码与请求结果是否一致
		{
			isrequestCheck = true;//需要检测权限，直接进入，否则提示对话框进行设置
			getAllGrantedPermission();
		} else {
			//提示对话框设置
			isrequestCheck = false;
			showMissingPermissionDialog();//dialog
		}

	}

	/**
	 *  当获取到所需权限后，进行相关业务操作
	 */
	public void getAllGrantedPermission() {
		  builder.setCancelable(true);
	      builder.setOnDismissListener(new DialogInterface.OnDismissListener() {
			  @Override
			  public void onDismiss(DialogInterface dialog) {
			      dialog.dismiss();
			  }
		  });
	}


	//获取全部权限
	public boolean hasAllPermissionGranted(int[] grantResults) {
		for (int grantResult : grantResults) {
			if (grantResult == PackageManager.PERMISSION_DENIED) {
				return false;
			}
		}
		return true;
	}


	//显示对话框提示用户缺少权限
	public void showMissingPermissionDialog() {
		builder = new AlertDialog.Builder(this);
		builder.setTitle("获取帮助");//提示帮助
		builder.setMessage("请你授权。。。。");

		//如果是拒绝授权，则退出应用
		//退出
		builder.setNegativeButton("确定", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				finish();
			}
		});
		//打开设置，让用户选择打开权限
		builder.setPositiveButton("设置", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				startAppSettings();//打开设置
			}
		});
		builder.setCancelable(false);
		builder.show();
	}

	//打开设置        //打开系统应用设置(ACTION_APPLICATION_DETAILS_SETTINGS:系统设置权限)
	private void startAppSettings() {
		Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
		intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
		startActivity(intent);
	}


	public String[] getPermissions() {
		return null;
	}


	@Override
	protected void onResume() {
		super.onResume();
		//根据activity生命周期，onRestart()->onResume()
		//此处表示从系统设置页面返回后，检查用户是否将所需的权限打开
		if (!isrequestCheck) {
			if (getPermissions() != null) {
				if (BaseUtils.permissionSet(getPermissions())) {
					showMissingPermissionDialog();//dialog
				} else {
					//获取全部权限,走正常业务
					getAllGrantedPermission();
				}
			}
		} else {
			isrequestCheck = true;
		}

	}

	/**
	 * @Description (设置沉浸式状态栏)
	 */
	private void initTint() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			setTranslucentStatus(true);
		}
		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		tintManager.setStatusBarTintResource(R.color.base_action_bar_color);// 通知栏所需颜色
	}

	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		if (on) {
			winParams.flags |= bits;
		} else {
			winParams.flags &= ~bits;
		}
		win.setAttributes(winParams);
	}

	/**
	 * @Description (初始化标题栏)
	 */
	private void initActionBar() {
		super.setContentView(R.layout.activity_base_actionbar);
		leftBtn = (Button) findViewById(R.id.leftBtn);
		middleText = (TextView) findViewById(R.id.middleText);
		rightText = (TextView) findViewById(R.id.rightText);
		progressBar = (ProgressBar) findViewById(R.id.progressBar);
		rightBtn = (Button) findViewById(R.id.rightBtn);
		baseLayout = (FrameLayout) findViewById(R.id.baseLayout);
		trp_relative = ((RelativeLayout) findViewById(R.id.topBar));
		leftBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if (onLeftBtnClick()) {
					onLeftBtnClick(v);
				} else {
					finish();
				}

			}
		});
		rightText.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onRightTextClick(v);
			}
		});
		rightBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onRightBtnClick(v);
			}
		});
		setActionBarStyle(ActionBarStyle.HideBoth);
	}

	@Override
	public void setContentView(int layoutResID) {
		baseLayout.removeAllViews();
		View.inflate(this, layoutResID, baseLayout);
		onContentChanged();
	}

	@Override
	public void setContentView(View view) {
		baseLayout.removeAllViews();
		baseLayout.addView(view);
		onContentChanged();
	}

	@Override
	public void setContentView(View view, LayoutParams params) {
		baseLayout.removeAllViews();
		baseLayout.addView(view, params);
		onContentChanged();
	}

	protected void initActionBar(ActionBarStyle style, String title) {
		middleText.setText(title);
		setActionBarStyle(style);
	}

	protected void initActionBar(ActionBarStyle style, int titleRes) {
		setTitleRes(titleRes);
		setActionBarStyle(style);
	}

	protected void setRightBtnRes(int rightBtnRes) {
		rightBtn.setBackgroundResource(rightBtnRes);
	}

	protected void setTitle(String title) {
		middleText.setText(title);
	}

	protected void setTitleRes(int titleRes) {
		setTitle(BaseUtils.getString(titleRes));
	}

	/**
	 * @Description (根据样式设定对应的显示方式)
	 * @param style
	 */
	protected void setActionBarStyle(ActionBarStyle style) {
		switch (style) {
		case ShowAll:
			leftBtn.setVisibility(View.VISIBLE);
			rightBtn.setVisibility(View.VISIBLE);
			break;
		case HideAll:
			leftBtn.setVisibility(View.GONE);
			rightBtn.setVisibility(View.GONE);
			break;
		case HideLeft:
			leftBtn.setVisibility(View.GONE);
			rightBtn.setVisibility(View.VISIBLE);
			break;
		case HideRight:
			leftBtn.setVisibility(View.VISIBLE);
			rightBtn.setVisibility(View.GONE);
			break;
		case HideBoth:
			leftBtn.setVisibility(View.GONE);
			rightBtn.setVisibility(View.GONE);
			break;


			case HideLayout :
				trp_relative.setVisibility(View.GONE);
			    break;
		}
	}

	/** 初始化布局 */
	protected abstract void initView(Bundle savedInstanceState);

	/** 初始化数据 */
	protected abstract void initData();

	/** 设置监听器 */
	protected abstract void initListener();

	/** 网络请求 */
	protected abstract void execHttp();

	@Override
	protected void onDestroy() {
		super.onDestroy();
		mApplication.removeActivity(this);
	}

	protected void startActivity(Class cls) {
		Intent mIntent = new Intent(mContext, cls);
		startActivity(mIntent);
	}

	/**
	 * @Description (TODO这里用一句话描述这个方法的作用)
	 * @return
	 */
	protected boolean onLeftBtnClick() {
		return false;
	}

	protected void onLeftBtnClick(View v) {
	}

	protected void onRightBtnClick(View v) {
	}

	protected void onRightTextClick(View v) {
	}



	protected void showProgress() {
		progressBar.setVisibility(View.VISIBLE);
	}

	protected void hideProgress() {
		progressBar.setVisibility(View.GONE);
	}

	protected void setRightText(String rText) {
		rightText.setVisibility(View.VISIBLE);
		rightText.setText(rText);
	}

	protected void setRightText(int rTextRes) {
		rightText.setVisibility(View.VISIBLE);
		rightText.setText(BaseUtils.getString(rTextRes));
	}

	@Override
	protected void onActivityResult(int arg0, int arg1, Intent arg2) {


	}

	protected void onImageSelector(List<String> imageList) {
	}

	/** 获取标题栏高度 **/
	protected int getTopBarHeight() {
		View topBar = findViewById(R.id.topBar);
		int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
		topBar.measure(width, height);
		height = topBar.getMeasuredHeight();
		return 0;
	}

	/** 获取状态栏高度 **/
	protected int getStatusHeight() {
		Class<?> c = null;
		Object obj = null;
		Field field = null;
		int x = 0, sbar = 0;
		try {
			c = Class.forName("com.android.internal.R$dimen");
			obj = c.newInstance();
			field = c.getField("status_bar_height");
			x = Integer.parseInt(field.get(obj).toString());
			sbar = getResources().getDimensionPixelSize(x);
		} catch (Exception e1) {
			LogUtils.e("get status bar height failure!");
			e1.printStackTrace();
		}
		return sbar;
	}

	protected int getTopHeight() {
		return getTopBarHeight() + getStatusHeight();
	}

	protected int getScreenWidth() {
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getWidth();
	}

	protected int getScreenHeight() {
		WindowManager wm = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		return wm.getDefaultDisplay().getHeight();
	}

	private LoadingDialog loadingDialog = null;

	public  void   showProgressDialog (String loadingMsg,boolean isCancel){
		if (loadingDialog == null){
			loadingDialog = new LoadingDialog(this).init(isCancel).setLoadingMsg(loadingMsg);
		}else {
			loadingDialog = new LoadingDialog(this).init(isCancel).setLoadingMsg(loadingMsg);
		}
		loadingDialog.show();
	}

	public  void hideProgressDialog () {
		if (loadingDialog != null){
			loadingDialog.dismiss();
			loadingDialog = null;
		}
	}


}
