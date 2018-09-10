package com.example.dell.baseproject.mvp.biz.mmmmvp;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/17.
 * 邮箱：123123@163.com
 */

public class BasePresener<T> {


    protected Reference<T> mViewRef ;      // View 接口类型的弱引用

    /**
     * 建立关联
     * @param view
     */
    public  void  attachView (T view) {
        mViewRef  = new WeakReference<T>(view);

    }

    /**
     * 获取view
     * @return
     */
    public  T getView (){
           return mViewRef.get();
    }


    /**
     *  判断是否与View 建立关联
     * @return
     */
    public   boolean  isViewAttach (){
         return  mViewRef != null && mViewRef.get() != null;
    }


    /**
     * 解除关联
     */
    public  void   detechView (){
        if (mViewRef != null){
            mViewRef.clear();
            mViewRef = null;
        }
    }
}
