package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.utils.LogTwoUtils;
import com.example.dell.baseproject.utils.OSHelperUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LogUtilsActivity extends BaseActivity {

    @BindView(R.id.button_click)
    Button buttonClick;
    @BindView(R.id.button_click_two)
    Button buttonClickTwo;
    @BindView(R.id.button_click_three)
    Button buttonClickThree;
    @BindView(R.id.button_click_four)
    Button buttonClickFour;
    @BindView(R.id.activity_log_utils)
    LinearLayout activityLogUtils;

    @Override
    public int initContentID() {
        return 0;
    }


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_log_utils);
        ButterKnife.bind(this);

    }

    @Override
    protected void initData() {
          if (OSHelperUtils.isEMUI()){
              BaseUtils.toast(this,"当前为华为设备");
          } else  if (OSHelperUtils.isFlyme()){
              BaseUtils.toast(this,"当前为魅族设备");
          }   else  if (OSHelperUtils.isMIUI()){
              BaseUtils.toast(this,"当前为小米设备");
          }   else {
              BaseUtils.toast(this,"无法获取当前设备");
          }
    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void execHttp() {

    }

    @OnClick({R.id.button_click})
    public  void  click (View view){
         switch (view.getId()){
             case R.id.button_click:
                 LogTwoUtils.d("debug");
                 break;
         }
    }
}
