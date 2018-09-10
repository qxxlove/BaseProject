package com.example.dell.baseproject.Retrofit;

import android.text.TextUtils;

import com.example.dell.baseproject.base.App;
import com.example.dell.baseproject.base.BaseConfig;
import com.example.dell.baseproject.utils.JsonUtils;
import com.example.dell.baseproject.utils.LogUtilsThree;
import com.example.dell.baseproject.utils.NetworkUtils;

import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.Headers;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * description:  此处使用多个是OKhttp最带的Log日志拦截， 那OKhttp 还有什么其他的拦截方式
 * autour: TMM
 * date: 2017/10/23 14:37
 * update: 2017/10/23
 * version:
 * 参考： http://mp.weixin.qq.com/s/zuZbsZGbeBeMe-HeEg6Otw
*/

public class OKhttpUtils {

    private static   OkHttpClient mOkHttpClient;

    /**
     * 初始化okhttpclient.
     *
     * @return okhttpClient
     */
    public static OkHttpClient getOkhttpclient() {
        if (mOkHttpClient == null) {
            if (BaseConfig.LOG_DEBUG){
                HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new HttpLogger());
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);


                mOkHttpClient = new OkHttpClient.Builder()
                        //失败重连
                        .retryOnConnectionFailure(true)

                        //time out
                        .readTimeout(15, TimeUnit.SECONDS)
                        .connectTimeout(15, TimeUnit.SECONDS)

                        .addNetworkInterceptor(logInterceptor)
                        .cache(cache)
                        .addInterceptor(cacheInterceptor)
                        .addNetworkInterceptor(cacheInterceptor)
                       // .addInterceptor(interceptor)

                        .addInterceptor(addQueryParameterInterceptor)
                        .addInterceptor(headerInterceptor )
                        //添加UA
                        .addInterceptor(new UserAgentInterceptor("tmm"))
                        .build();
            }  else {
                mOkHttpClient = new OkHttpClient.Builder()
                        .connectTimeout(15, TimeUnit.SECONDS)
                        .build();
            }

        }
        return mOkHttpClient;
    }


    /**
     * 日志拦截 第一种方式
     */
    static  class HttpLogger implements HttpLoggingInterceptor.Logger {
        private StringBuilder mMessage = new StringBuilder();
        @Override
        public void log(String message) {
            // 请求或者响应开始
            if (message.startsWith("--> POST")) {
                mMessage.setLength(0);
            }
            // 以{}或者[]形式的说明是响应结果的json数据，需要进行格式化
            if ((message.startsWith("{") && message.endsWith("}"))
                    || (message.startsWith("[") && message.endsWith("]"))) {
                message = JsonUtils.formatJson(JsonUtils.decodeUnicode(message));
            }
            mMessage.append(message.concat("\n"));
            // 响应结束，打印整条日志
            if (message.startsWith("<-- END HTTP")) {
                LogUtilsThree.d(mMessage.toString());
            }
        }
    }


    /**
     *   日志拦截 第二种方式
     */
    public class LoggingInterceptor implements Interceptor {

        @Override public Response intercept(Interceptor.Chain chain) throws IOException {

            Request request = chain.request();
            LogUtilsThree.d(String.format("Sending request %s on %s%n%s", request.url(),  chain.connection(), request.headers()));

            long t1 = System.nanoTime();
            okhttp3.Response response = chain.proceed(chain.request());
            long t2 = System.nanoTime();
            LogUtilsThree.d(String.format(Locale.getDefault(), "Received response for %s in %.1fms%n%s",
                    response.request().url(), (t2 - t1) / 1e6d, response.headers()));

            okhttp3.MediaType mediaType = response.body().contentType();
            String content = response.body().string();
            LogUtilsThree.json(content);
            return response.newBuilder()
                    .body(okhttp3.ResponseBody.create(mediaType, content))
                    .build();
        }
    }





    /**
     * 缓存  原理：缓存的设置是通过设置 Http请求头和响应头中的 Cache-Control 的 max-age 属性达到的。
     *             在复写 Interceptor 时，通过设置响应头的 Cache-Control 来达到目的
     *               /**
     * 第二种类型 离线可以缓存，在线就获取最新数据
     * 这种方法和第一种方法的区别是在设置的拦截器上，这里同时需要配置使用NetworkInterceptor，和Interceptor。
     先讲一下步骤：
     * 1、 首先，给OkHttp设置拦截器
     * 2、然后，在拦截器内做Request拦截操作，在每个请求发出前，判断一下网络状况，如果没问题继续访问，如果有问题，则设置从本地缓存中读取
     * 3、接下来是设置Response，先判断网络，网络好的时候，移除header后添加cache失效时间为0小时，网络未连接的情况下设置缓存时间为4周
     *
     * 这里使用getCacheDir()来作为缓存文件的存放路径（/data/data/包名/cache） ，如果你想看到缓存文件可以临时使用 getExternalCacheDir()（/sdcard/Android/data/包名/cache）。



       思路是可行的，但是并没有实现理想的效果，也不知道问题出现在哪里
     */
    static File cacheFile = new File(App.getInstance().getExternalCacheDir(), "tmm");
    static Cache cache = new Cache(cacheFile, 1024 * 1024 * 50);    // 50Mb
    static Interceptor cacheInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            if (!NetworkUtils.isAvailable(App.getInstance())) {
                request = request.newBuilder()
                        .cacheControl(CacheControl.FORCE_CACHE)
                        .build();
                LogUtilsThree.i("no network");
            }

            Response response = chain.proceed(request);
            if (NetworkUtils.isAvailable(App.getInstance())) {
                int maxAge = 0;
                String cacheControl = response.cacheControl().toString();
                // 有网络时 设置缓存超时时间0个小时
                response.newBuilder()
                       // .header("Cache-Control", "public, max-age=" + maxAge)
                        .header("Cache-Control", cacheControl)
                        .removeHeader("Pragma")// 清除头信息，因为服务器如果不支持，会返回一些干扰信息，不清除下面无法生效 ？
                        .build();
            } else {
                // 无网络时，设置超时为4周
                int maxStale = 60 * 60 * 24 * 28;
                response.newBuilder()
                        .header("Cache-Control", "public, only-if-cached, max-age=30")
                        .removeHeader("Pragma")
                        .build();
            }
            return response;
        }
    };


    /**
     * 第一种类型有网没网都走缓存（60s 之内）      (这个方法相比上面的方法cacheInterceptor实际开发中比较推荐上面的那种)
     */
    static Interceptor interceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            LogUtilsThree.i("request="+request);
            Response response = chain.proceed(request);
            LogUtilsThree.i("response="+response);

            String cacheControl = request.cacheControl().toString();
            if (TextUtils.isEmpty(cacheControl)) {
                cacheControl = "public, max-age=60";
            }
            return response.newBuilder()
                    .header("Cache-Control", cacheControl)
                    .removeHeader("Pragma")
                    .build();
        }
    };









    /**
     *  添加公共参数 （是往接口后面添加的 如： https://www.zslzsl999.cn/carshare/carshare/dot?platform=android&version=1.0.0
     */
     static Interceptor addQueryParameterInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request request;
            String method = originalRequest.method();
            Headers headers = originalRequest.headers();
            HttpUrl modifiedUrl = originalRequest.url().newBuilder()
                    // Provide your custom parameter here
                    .addQueryParameter("platform", "android")
                    .addQueryParameter("version", "1.0.0")
                    .build();
            request = originalRequest.newBuilder().url(modifiedUrl).build();
            return chain.proceed(request);
        }
    };


    /**
     * 设置头
     * 有的接口可能对请求头要设置
      */
    static Interceptor headerInterceptor = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request originalRequest = chain.request();
            Request.Builder requestBuilder = originalRequest.newBuilder()
                    .header("AppType", "TPOS")
                    .header("Content-Type", "application/json")
                    .header("Accept", "application/json")
                    .method(originalRequest.method(), originalRequest.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
    };


    /**
     * 动态更改UA(User-Agent)
     */
    public static final class UserAgentInterceptor implements Interceptor {
        private static final String USER_AGENT_HEADER_NAME = "User-Agent";
        private final String userAgentHeaderValue;

        public UserAgentInterceptor(String userAgentHeaderValue) {
            this.userAgentHeaderValue = userAgentHeaderValue;
        }

        @Override public Response intercept(Chain chain) throws IOException {
            final Request originalRequest = chain.request();

            final Request requestWithUserAgent = originalRequest.newBuilder()

                    //移除先前默认的UA
                    .removeHeader(USER_AGENT_HEADER_NAME)

                    //设置UA
                    .addHeader(USER_AGENT_HEADER_NAME, userAgentHeaderValue)


                    .build();
            return chain.proceed(requestWithUserAgent);
        }
    }


}
