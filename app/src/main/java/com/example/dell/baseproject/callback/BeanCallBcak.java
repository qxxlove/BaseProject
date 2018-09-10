package com.example.dell.baseproject.callback;

import retrofit2.Callback;

/**
 * description:   网络请求回调处理
 * autour: TMM
 * date: 2017/7/17 10:10
 * update: 2017/7/17
 * version:
*/

public abstract class BeanCallBcak<T> implements Callback<T> {
    private static final String TAG = BeanCallBcak.class.getSimpleName();



   /*@Override
    public T parseNetworkResponse(Response response, int id) throws Exception {
        String str = response.body().string();
        Log.e(TAG, str);
        Type type = this.getClass().getGenericSuperclass();
        if (type instanceof ParameterizedType) {
            //如果用户写了泛型，就会进入这里，否者不会执行
            ParameterizedType parameterizedType = (ParameterizedType) type;
            Type beanType = parameterizedType.getActualTypeArguments()[0];
            if (beanType == String.class) {
                //如果是String类型，直接返回字符串
                return (T) str;
            } else {
                //如果是 Bean List Map ，则解析完后返回
                return new Gson().fromJson(str, beanType);
            }
        } else {
            //如果没有写泛型，直接返回Response对象
            return (T) str;
        }
    }

    @Override
    public void onError(Call call, Exception e, int id) {

    }

    @Override
    public void onResponse(T response, int id) {

    }*/
}
