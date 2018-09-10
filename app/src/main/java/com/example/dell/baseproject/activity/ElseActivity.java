package com.example.dell.baseproject.activity;

import android.Manifest;
import android.os.Bundle;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.ActionBarStyle;
import com.example.dell.baseproject.utils.BaseUtils;

public class ElseActivity extends BaseActivity {


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_else);
        setActionBarStyle(ActionBarStyle.HideRight);
        setTitle(BaseUtils.getString(R.string.mine))   ;
    }



    @Override
    protected void initData() {

    }

    @Override
    protected void execHttp() {

    }

    @Override
    protected void initListener() {

    }



    static final String[] PERMISSION = new String[]{
            Manifest.permission.WRITE_EXTERNAL_STORAGE,// 写入权限
            Manifest.permission.READ_EXTERNAL_STORAGE,  //读取权限
            Manifest.permission.READ_PHONE_STATE,        //读取设备信息
            Manifest.permission.ACCESS_COARSE_LOCATION, //百度定位
            Manifest.permission.ACCESS_FINE_LOCATION,
    };

    //布局id上送到BaseActivity
    @Override
    public int initContentID() {
        return  0;
    }


   @Override
    public void process(Bundle savedInstanceState) {
        super.process(savedInstanceState);
        //如果有什么需要初始化的，在这里写就好～
    }

    @Override
    public void getAllGrantedPermission() {
        //当获取到所需权限后，进行相关业务操作
        super.getAllGrantedPermission();
    }

    @Override
    public String[] getPermissions() {
        return PERMISSION;
    }
}
