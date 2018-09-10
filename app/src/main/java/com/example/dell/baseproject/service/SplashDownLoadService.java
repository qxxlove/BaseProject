package com.example.dell.baseproject.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import com.example.dell.baseproject.bean.Contants;
import com.example.dell.baseproject.bean.WebInfoBean;
import com.example.dell.baseproject.utils.DownLoadUtils;
import com.example.dell.baseproject.utils.LogUtils;
import com.example.dell.baseproject.utils.SerializableUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.dell.baseproject.bean.Contants.SPLASH_FILE_NAME;

/**
 * Created by SEELE on 2017/6/21.
 */

public class SplashDownLoadService extends IntentService  implements DownLoadUtils.DownLoadInterFace{


    WebInfoBean splash = null;


    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public SplashDownLoadService(String name) {
        super(name);
    }


    public static void startDownLoadSplashImage(Context context, String action) {
        Intent intent = new Intent(context, SplashDownLoadService.class);
        intent.putExtra(Contants.EXTRA_DOWNLOAD, action);
        context.startService(intent);
    }

    /**
     *      SplashDownLoadService 内容，IntentService 在调用了 startService 后会执行 onHandleIntent 方法，
     *      在这方法中我们去请求服务器最新的数据即 loadSplashNetDate：
     */
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String action = intent.getStringExtra(Contants.EXTRA_DOWNLOAD);
            if (action.equals(Contants.DOWNLOAD_SPLASH)) {
                loadSplashNetDate();
            }
        }
    }


    /**
     * 网络请求
     */
    private void loadSplashNetDate() {
        // ①  网络请求
        // ② 下载之后的逻辑判断
        WebInfoBean splashLocal = getLocalSplash();
        if (splashLocal != null) {
            if (splashLocal == null) {
                LogUtils.d("splashLocal 为空导致下载");
                startDownLoadSplash(Contants.SPLASH_PATH, splashLocal.burl);
            } else if (isNeedDownLoad(splashLocal.savePath, splashLocal.burl)) {
                LogUtils.d("isNeedDownLoad 导致下载");
                startDownLoadSplash(Contants.SPLASH_PATH, splashLocal.burl);
            }
        } else {//由于活动是一段时间，等活动结束后我们并不需要在进入闪屏页面，这个时候我们就需要将本地文件删除，下次在进来，本地文件为空，就会直接 finish 掉 Splash 页面，进入主页面。
            if (splashLocal != null) {
                File splashFile = null;
                try {
                    splashFile = SerializableUtils.getSerializableFile(Contants.SPLASH_PATH, SPLASH_FILE_NAME);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (splashFile.exists()) {
                    splashFile.delete();
                    LogUtils.d("mScreen为空删除本地文件");
                }
            }
        }
    }

    //// ①  网络请求
    private void startDownLoadSplash(String splashPath, String burl) {


    }


    // 取出本地序列化的 Splash
    private WebInfoBean getLocalSplash() {

        try {
            File serializableFile = SerializableUtils.getSerializableFile(Contants.SPLASH_PATH, Contants.SPLASH_FILE_NAME);
            splash = (WebInfoBean) SerializableUtils.readObject(serializableFile);
        } catch (IOException e) {
            LogUtils.e("SplashActivity 获取本地序列化闪屏失败" + e.getMessage());
        }
        return splash;
    }





    /**
     * @param path 本地存储的图片绝对路径
     * @param url  网络获取url
     * @return 比较储存的 图片名称的哈希值与 网络获取的哈希值是否相同
     */
    private boolean isNeedDownLoad(String path, String url) {
        // 如果本地存储的内容为空则进行下载
        if (TextUtils.isEmpty(path)) {
            return true;
        }
        // 如果本地文件不存在则进行下载，这里主要防止用户误删操作
        File file = new File(path);
        if (!file.exists()) {
            return true;
        }
        // 如果两者都存在则判断图片名称的 hashCode 是否相同，不相同则下载
        if (getImageName(path).hashCode() != getImageName(url).hashCode()) {
            return true;
        }
        return false;
    }


    private String getImageName(String url) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        String[] split = url.split("/");
        String nameWith_ = split[split.length - 1];
        String[] split1 = nameWith_.split("\\.");
        return split1[0];
    }



    @Override
    public void afterDownLoad(ArrayList<String> savePaths) {
        if (savePaths.size() == 1) {
            LogUtils.d("闪屏页面下载完成" + savePaths);
            if (splash!= null) {
                splash.savePath = savePaths.get(0);
            }
            // 序列化 Splash 到本地
            SerializableUtils.writeObject(splash, Contants.SPLASH_PATH + "/" + SPLASH_FILE_NAME);
        } else {
            LogUtils.d("闪屏页面下载失败" + savePaths);
        }


    }
}
