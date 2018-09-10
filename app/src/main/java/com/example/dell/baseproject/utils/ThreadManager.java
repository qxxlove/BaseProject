package com.example.dell.baseproject.utils;

import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
/**
 * description:
 * autour: TMM
 * date: 2017/10/27 9:48
 * update: 2017/10/27
 * version:
 * 参考：http://blog.csdn.net/myloveyaqiong/article/details/53142521
*/

public class ThreadManager {

    private static ThreadPool Instance;

    /**
     *    单例模式，获取线程池的实例
     */
    public  static ThreadPool getInstance(int corePoolSize,int maximumPoolSize){
        if(Instance == null){
            synchronized (ThreadPool.class){
                if(Instance == null){
                    Instance = new ThreadPool(corePoolSize,maximumPoolSize,5);
                }
            }
        }
        return Instance;
    }

    public static class ThreadPool{

        private ThreadPoolExecutor executor;
        private  int corePoolSize;
        private  int maximumPoolSize;
        private  long keepAliveTime;
        private  SynchronousQueue<Runnable> synchronousQueue;

        public ThreadPool(int corePoolSize,int maximumPoolSize,long keepAliveTime) {
            this.corePoolSize = corePoolSize;
            this.maximumPoolSize = maximumPoolSize;
            this.keepAliveTime = keepAliveTime;

        }

        /** 1. 如何获取当前线程数*/
        public  void execute(Runnable r){
            if(executor == null){
                //线程池执行者。
                 //参1:核心线程数： 一般核心线程不受存活时间的影响（除非你设置），当前线程数小于核心线程数时，他会继续创建线程，不管有没有空闲线程
                // 参2:最大线程数;  当设置的线程 队列满了，当前线程数小于最大线程时，他会继续创建线程，如果使用了无界(没有大小 LinkedBlockingDeque)的任务队列，这个参数就没意义了
                // 参3:线程休眠时间;  线程执行完任务，存活的时间
                // 参4:时间单位;
                // 参5:线程队列;    用于保存等待执行的任务的阻塞队列
                                 // ArrayBlockingQueue：是一个基于数组结构的有界阻塞队列，
                                //       此队列按 FIFO（先进先出）原则对元素进行排序。
                                 //LinkedBlockingQueue：一个基于链表结构的阻塞队列，此队列按FIFO （先进先出） 排序元素，吞吐量通常要高于ArrayBlockingQueue。静态工厂方法Executors.newFixedThreadPool()使用了这个队列。
                                 //SynchronousQueue：一个不存储元素的阻塞队列。每个插入操作必须等上一个元素被移除之后，否则插入操作一直处于阻塞状态，吞吐量通常要高于LinkedBlockingQueue，静态工厂方法Executors.newCachedThreadPool使用了这个队列。
                                //PriorityBlockingQueue：一个具有优先级的无限阻塞队列。
                                //不同的runnableTaskQueue对线程池运行逻辑有很大影响
                // 参6:生产线程的工厂:   通过线程工厂给创建的每个线程设置名称，实际开发中，便于处理
                // 参7:线程异常处理策略 :
                       // RejectedExecutionHandler 线程池队列饱和之后的执行策略，默认是采用AbortPolicy。
                       //  JDK提供四种实现方式：
                       // AbortPolicy：直接抛出异常
                      // CallerRunsPolicy ：只用调用者所在线程来运行任务
                     // DiscardOldestPolicy 丢弃队列里最近的一个任务，并执行当前任务
                    // DiscardPolicy ： 不处理，丢弃掉
                synchronousQueue = new SynchronousQueue<Runnable>();
                executor = new ThreadPoolExecutor(
                        corePoolSize,maximumPoolSize,keepAliveTime, TimeUnit.SECONDS,
                        synchronousQueue, Executors.defaultThreadFactory(),
                        new ThreadPoolExecutor.AbortPolicy());
            }
            if(executor != null){

                executor.execute(r);
            }
        }
        //取消线程
        public void cancel(Runnable r){
            if(executor != null){
                executor.getQueue().remove(r);
            }
        }


        public int getCorePoolSize() {
            return corePoolSize;
        }

        public void setCorePoolSize(int corePoolSize) {
            this.corePoolSize = corePoolSize;
        }

        public int getMaximumPoolSize() {
            return maximumPoolSize;
        }

        public void setMaximumPoolSize(int maximumPoolSize) {
            this.maximumPoolSize = maximumPoolSize;
        }

        public SynchronousQueue<Runnable> getSynchronousQueue() {
            return synchronousQueue;
        }

        public void setSynchronousQueue(SynchronousQueue<Runnable> synchronousQueue) {
            this.synchronousQueue = synchronousQueue;
        }
    }
}
