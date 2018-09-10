package com.example.dell.baseproject.Retrofit;

/** 
 * description: 接口统一封装
 * autour: TMM
 * date: 2017/10/31 10:28 
 * update: 2017/10/31
 * version: 
*/

public enum  ApiFactory {
    INSTANCE ;
    
    
    private final API gitHubAPI;
    private final NewsApi anotherAPI;

    ApiFactory() {
        gitHubAPI = RetrofitUtils.INSTANCE.getRetrofit().create(API.class);
        anotherAPI = RetrofitUtils.INSTANCE.getRetrofit().create(NewsApi.class);
    }

    public API gitHubAPI() {
        return gitHubAPI;
    }

    public NewsApi getAnotherAPI() {
        return anotherAPI;
    }
}
