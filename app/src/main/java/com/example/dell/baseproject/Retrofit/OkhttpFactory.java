package com.example.dell.baseproject.Retrofit;

import com.example.dell.baseproject.base.App;

import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/** 
 * description: 比较推荐的写法
 * autour: TMM
 * date: 2017/10/31 10:25 
 * update: 2017/10/31
 * version: 
*/

public enum  OkhttpFactory {

    INSTANCE;

    private final OkHttpClient okHttpClient;

    private static final int TIMEOUT_READ = 25;
    private static final int TIMEOUT_CONNECTION = 25;

    OkhttpFactory() {
        //打印请求Log
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        //缓存目录
        Cache cache = new Cache(App.getInstance().getCacheDir(), 10 * 1024 * 1024);

        okHttpClient = new OkHttpClient.Builder()
                //打印请求log
                .addInterceptor(interceptor)

                //stetho,可以在chrome中查看请求
               // .addNetworkInterceptor(new StethoInterceptor())

                //添加UA
                .addInterceptor(new OKhttpUtils.UserAgentInterceptor("tmm"))

                //必须是设置Cache目录
                .cache(cache)

                //走缓存，两个都要设置
            //    .addInterceptor(new OnOffLineCachedInterceptor())
            //    .addNetworkInterceptor(new OnOffLineCachedInterceptor())

                //失败重连
                .retryOnConnectionFailure(true)

                //time out
                .readTimeout(TIMEOUT_READ, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT_CONNECTION, TimeUnit.SECONDS)

                .build();
    }

    public OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }



}
