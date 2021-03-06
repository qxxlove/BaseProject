package com.example.dell.baseproject.utils;

import android.content.Context;
import android.view.LayoutInflater;

/**
 * description:单例模式的应用
 * autour: TMM
 * date: 2017/10/30 9:42
 * update: 2017/10/30
 * version:
 * 参考：http://mp.weixin.qq.com/s/ZfJPhEPp-NaY5ZFsBZzyYw
 *
 *     单例：  确保这个类在内存中只会存在一个对象,而且自行实例化并向整个系统提供这个实例。
 *            
 *
 */
public class SingleInstance {

    /**
     * 饿汉试  (那空间换时间，缺点一进来就实例化对象，不管你需不需要。因此,造成资源浪费)
     */
    public static final SingleInstance SINGLE_INSTANCE = new SingleInstance();

    private SingleInstance() {
    }

    public static final SingleInstance getSingleInstance (){
        return  SINGLE_INSTANCE ;
    }


    /**
     * 懒汉式  （那时间换空间:效率低，第一次加载需要实例化，反应稍慢。
     *         每次调用 getInstance 方法都会进行同步，消耗不必要的资源。
     *         因此我们对此作出优化：双重检查单例（DCL））
     */

    public static SingleInstance singleInstance ;
    public static synchronized  SingleInstance getSingleInstanceTwo () {
          if (singleInstance  == null) {
              singleInstance = new SingleInstance();
          }
        return  singleInstance;
    }


    /**
     * 双重检查单例 （ ①避免了不必要的同步，②只有singleInstance在null的情况下才创建）
     * @return   此方法在jdk 1.5 之前并不是白分百可靠，
     *        因为①线程间共享变量不可见性;②无序性(执行顺序无法保证);   不过此bug官方已修复，
     *       解决：  private Volatitle static Singleton instance;   (可能会影响性能，这点牺牲还是值得的)
     *
     *      例如：  常用的EventBus 源码 实例的获取就是通过此方法
     */
    public static SingleInstance getSingleInstanceThree(){
        //① 避免不必要的同步
        if(singleInstance == null){
            synchronized (SingleInstance.class){
                // ② 只有singleInstance在null的情况下才创建
                if(singleInstance == null){
                    singleInstance = new SingleInstance();
                }
            }
        }
        return singleInstance;
    }


    /**
     * 静态内部类实现单利
     * 开发中实际用到的，大牛也推荐这么写。
     * 这种方式不仅确保了线程的安全性,也能够保证对象的唯一性,同时也是延迟加载
     */
    private  static   SingleInstance   singleInstanceFour ;
    public   static class   Single  {
        public static   final SingleInstance SINGLE_INSTANCE = new SingleInstance();
    }

    public   static  SingleInstance getSingleInstanceFour  (){
        return Single.SINGLE_INSTANCE;
    }

    

    /**
     * 枚举实现单例
     * 优点:相对于其他单例来说枚举写法最简单,并且任何情况下都是单列的,JDK1.5之后才有的。
     * 
     * 如何使用呢？
     */
    public enum SingletonEnum {
        /**
         * 实例化对象
         */
        INSTANCE;
        
        public void doSomething() {

        }
    }

    /**
     * 学习源码分析 如：看下 LayoutInflater 的单例模式实现:
     *
     *       结论： 就是采用 静态内部类实现单利
     *
     *        主要学习作者分析源码的技巧，达到孰能生巧
     */
     public  void   getLayoutInflater (Context context){
         LayoutInflater mInflater = LayoutInflater.from(context);
     }


}
