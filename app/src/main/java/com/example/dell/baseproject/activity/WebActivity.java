package com.example.dell.baseproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

import com.example.dell.baseproject.MainActivity;
import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.ActionBarStyle;
import com.example.dell.baseproject.bean.UserInfoBena;
import com.example.dell.baseproject.utils.BaseUtils;

/**
 * 广告详情页
 */
public class WebActivity extends BaseActivity {

    private  boolean isFormSplash ;
    private WebView webview;

    @Override
    public int initContentID() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_web);
        setActionBarStyle(ActionBarStyle.HideRight);
        setTitle(BaseUtils.getString(R.string.splash))   ;
        Intent intent = getIntent();
        isFormSplash = intent.getBooleanExtra("fromSplash",false);
        webview = ((WebView) findViewById(R.id.webView_activity));
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void execHttp() {

    }





    // mFromSplash 为启动页面传递过来的参数    .  监听actionBar 的返回箭头  （具体看实际开发中如何实现头部的布局（返回箭头））
    @Override
    protected void onLeftBtnClick(View v) {
        super.onLeftBtnClick(v);
        switch (v.getId()) {
            case R.id.leftBtn :
                if (isFormSplash) {
                    gotoLoginOrMainActivity();
                }
             break;
        }
    }

    @Override
    protected boolean onLeftBtnClick() {
        return true;
    }


    // 此方法为系统返回键的监听
    @Override
    public void onBackPressed() {
        if (webview.canGoBack()) {
            webview.goBack();
        } else if (isFormSplash) {
            gotoLoginOrMainActivity();
        } else {
            super.onBackPressed();
        }
    }

    // 下面是跳转逻辑
    private void gotoLoginOrMainActivity() {
        UserInfoBena userInfoBena = new UserInfoBena();
        if (userInfoBena.getToken() != null) {
            gotoLoginActivity();
        } else {
            gotoMainActivity();
        }
    }


    private void gotoMainActivity() {
        Intent intent = new Intent(WebActivity.this,MainActivity.class);
        startActivity(intent);
        WebActivity.this.finish();
    }

    private void gotoLoginActivity() {


    }

}
