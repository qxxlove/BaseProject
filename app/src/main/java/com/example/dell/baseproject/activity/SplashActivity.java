package com.example.dell.baseproject.activity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.dell.baseproject.MainActivity;
import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.Contants;
import com.example.dell.baseproject.bean.UserInfoBena;
import com.example.dell.baseproject.bean.WebInfoBean;
import com.example.dell.baseproject.service.SplashDownLoadService;
import com.example.dell.baseproject.utils.LogUtils;
import com.example.dell.baseproject.utils.SerializableUtils;

import java.io.File;
import java.io.IOException;

import pub.devrel.easypermissions.EasyPermissions;

/**
 * 广告页
 */
public class SplashActivity extends AppCompatActivity {

    private ImageView image_bg;
    private Button btn_next;

    public static final int RC_PERMISSION = 123;
    private WebInfoBean webInfoBean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        initView();
        initData();
       // checkSDCardPermission();
        initClick();
    }

    /**
     * 检查权限
     */
    private void checkSDCardPermission() {
        if (EasyPermissions.hasPermissions(this, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            initSplashImage();
            startImageDownLoad();
        } else {
            EasyPermissions.requestPermissions(this, "需要您提供【**】App 读写内存卡权限来确保应用更好的运行", RC_PERMISSION, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
    }

    private void startImageDownLoad() {
        SplashDownLoadService.startDownLoadSplashImage(this, Contants.DOWNLOAD_SPLASH);
    }


    private void initSplashImage() {
        webInfoBean = getLocalSplash();
        //如果取出本地序列化的对象成功 则进行图片加载和倒计时
        if (webInfoBean != null && !TextUtils.isEmpty(webInfoBean.savePath)) {
            LogUtils.d("SplashActivity 获取本地序列化成功" + webInfoBean);
            Glide.with(this).load(webInfoBean.savePath).into(image_bg);
            startClock();//加载成功 开启倒计时
        } else {
            // 如果本地没有 直接跳转
            btn_next.setVisibility(View.INVISIBLE);
            btn_next.postDelayed(new Runnable() {
                @Override
                public void run() {
                    gotoLoginOrMainActivity();
                }
            }, 400);
        }
    }

    // 取出本地序列化的 Splash
    private WebInfoBean getLocalSplash() {
        WebInfoBean splash = null;
        try {
            File serializableFile = SerializableUtils.getSerializableFile(Contants.SPLASH_PATH, Contants.SPLASH_FILE_NAME);
            splash = (WebInfoBean) SerializableUtils.readObject(serializableFile);
        } catch (IOException e) {
            LogUtils.e("SplashActivity 获取本地序列化闪屏失败" + e.getMessage());
        }
        return splash;
    }




    private void initClick() {
        image_bg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                gotoWebActivity();
            }
        });
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                countDownTimer.cancel();
                gotoLoginOrMainActivity();
            }
        });
    }


    private void initData() {
        startClock();

    }

    private void initView() {
        image_bg = ((ImageView) findViewById(R.id.sp_bg));
        btn_next = ((Button) findViewById(R.id.sp_jump_btn));
    }



    private CountDownTimer countDownTimer = new CountDownTimer(3200, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            btn_next.setText("跳过(" + millisUntilFinished / 1000 + "s)");
        }

        @Override
        public void onFinish() {
            btn_next.setText("跳过(" + 0 + "s)");
            gotoLoginOrMainActivity();
        }
    };

    /**
     * 跳到登陆或者主界面
     */
    private void gotoLoginOrMainActivity() {
        UserInfoBena userInfoBena = new UserInfoBena();
        if (userInfoBena.getToken()  != null) {
            gotoLoginActivity();
        } else {
            gotoMainActivity();
        }
    }


    private void gotoMainActivity() {
        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }

    private void gotoLoginActivity() {

    }

    /**
     * 开启倒计时
     */
    private void startClock() {
        btn_next.setVisibility(View.VISIBLE);
        countDownTimer.start();
    }

    /**
     * 跳转到web界面
     */
    private void gotoWebActivity() {
        WebInfoBean webInfoBean = new WebInfoBean("","","http://www.baidu.com","");
        if (webInfoBean != null && webInfoBean.click_url != null) {
            Intent intent = new Intent(this, WebActivity.class);
            intent.putExtra("url", webInfoBean.click_url);
            intent.putExtra("title", webInfoBean.title);
            intent.putExtra("fromSplash", true);
            intent.putExtra("needShare", false);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null)
            countDownTimer.cancel();
    }
}
