package com.example.dell.baseproject.base;

import android.app.Activity;
import android.support.multidex.MultiDexApplication;

import com.baidu.mapapi.SDKInitializer;
import com.example.dell.baseproject.BuildConfig;
import com.example.dell.baseproject.utils.LogTwoUtils;
import com.example.dell.baseproject.utils.LogUtilsThree;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SEELE on 2017/6/20.
 */

public class App extends MultiDexApplication {

    public static  App  app;
    private List<Activity> mActivitys  = new ArrayList<>();


    @Override
    public void onCreate() {
        super.onCreate();
        app = this;
        SDKInitializer.initialize(this);
        initLogUtilsTwo();
        initLoggerThree();
       // initOkHttpUtils();
        initRetrofit();
    }


    private void initLoggerThree() {
        // 初始化Looger工具
        LogUtilsThree.init(BaseConfig.LOG_DEBUG);
    }

    private void initRetrofit() {

    }

    /*private void initOkHttpUtils() {
          *//*保存cookie(包含Session)*//*
        CookieJarImpl cookieJar = new CookieJarImpl(new PersistentCookieStore(getApplicationContext()));
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar)
                .connectTimeout(3000L, TimeUnit.MILLISECONDS)
                .readTimeout(6000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        OkHttpUtils.initClient(okHttpClient);
    }*/

    private void initLogUtilsTwo() {
        LogTwoUtils.Builder builder = new LogTwoUtils.Builder()
                .setLogSwitch(BuildConfig.DEBUG)// 设置log总开关，包括输出到控制台和文件，默认开
                .setConsoleSwitch(BuildConfig.DEBUG)// 设置是否输出到控制台开关，默认开
                .setGlobalTag(null)// 设置log全局标签，默认为空
                // 当全局标签不为空时，我们输出的log全部为该tag，
                // 为空时，如果传入的tag为空那就显示类名，否则显示tag
                .setLogHeadSwitch(true)// 设置log头信息开关，默认为开
                .setLog2FileSwitch(false)// 打印log时是否存到文件的开关，默认关
                .setDir("")// 当自定义路径为空时，写入应用的/cache/log/目录中
                .setBorderSwitch(true)// 输出日志是否带边框开关，默认开
                .setConsoleFilter(LogTwoUtils.V)// log的控制台过滤器，和logcat过滤器同理，默认Verbose
                .setFileFilter(LogTwoUtils.V);// log文件过滤器，和logcat过滤器同理，默认Verbose
        LogTwoUtils.d(builder.toString());
    }

    public synchronized static App getInstance() {
        return app;
    }

    public List<Activity> getActivitys() {
        return mActivitys;
    }

    public void putActivity(Activity _Act) {
        mActivitys.add(_Act);
    }

    public void removeActivity(Activity _Act) {
        mActivitys.remove(_Act);
    }

    public void clearActivity() {
        mActivitys.clear();
    }

    public void exit(){
        for(Activity _activity:mActivitys){
            _activity.finish();
        }
    }


}
