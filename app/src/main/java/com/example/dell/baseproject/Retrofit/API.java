package com.example.dell.baseproject.Retrofit;

import com.example.dell.baseproject.bean.LoginBean;
import com.example.dell.baseproject.bean.MapDotInfoBean;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/19.
 * 邮箱：123123@163.com
 */

public interface API {

     @FormUrlEncoded
     @POST("carshare/user")
     Call<LoginBean>  getUserLogin  (@Field("uname") String uname,@Field("upwd") String pass);


     @POST("carshare/dot")
     Call<MapDotInfoBean> getDotInfo ();

    /**
     * 通过设置添加Headers来设置缓存，如果整个项目都需要缓存，则在Okhttp初始化的时候添加头部即可。
     * 以下只是单一的为某一个接口设置缓存
     * @return
     */
     @Headers("Cache-Control: public, max-age=30")
     @POST("carshare/dot")
     Observable<MapDotInfoBean> getDots() ;

     @FormUrlEncoded
     @POST("carshare/user")
     Observable<LoginBean>   login (@Field("uname") String name, @Field("upwd") String pass);


}
