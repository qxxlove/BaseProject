package com.example.dell.baseproject.Retrofit;

import com.example.dell.baseproject.base.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** 
 * description: 比较推荐的写法（枚举单例的使用）
 * autour: TMM
 * date: 2017/10/31 10:24 
 * update: 2017/10/31
 * version: 
*/

public enum  RetrofitUtils {

    INSTANCE;

    private final Retrofit retrofit;

    RetrofitUtils() {
        retrofit = new Retrofit.Builder()
                //设置OKHttpClient
                .client(OkhttpFactory.INSTANCE.getOkHttpClient())

                //baseUrl
                .baseUrl(API.apk_url)

                //gson转化器
                .addConverterFactory(GsonConverterFactory.create())

                .build();
    }

    public Retrofit getRetrofit() {
        return retrofit;
    }


}
