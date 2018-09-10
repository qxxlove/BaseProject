package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.baseproject.R;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * description: 线程池的使用
 * autour: TMM
 * date: 2018/6/27 17:52
 * update: 2018/6/27
 * version:
 * <p>
 *
 *    参考： Java多线程-线程池ThreadPoolExecutor构造方法和规则
 *           https://blog.csdn.net/qq_25806863/article/details/71126867
 *          线程池的使用（ThreadPoolExecutor详解）
 *          https://blog.csdn.net/lipc_/article/details/52025993
 *
 *          Android开发之线程池使用总结
 *          https://blog.csdn.net/u012702547/article/details/52259529
 *
 *  线程池原理： 先创建核心线程，然后根据配置的是哪种任务队列，
 *               是创建非核心线程，还是加入任务队列等待，此时又需要根据任务队列注意最大线程数
 *               （线程池内的线程数不得大于最大线程数）
 *
 *               
 *  Java线程池线程复用的秘密
 *  https://mp.weixin.qq.com/s/jISHo8-aKMPjjeCYGJILgg
 *
 *    android 提供了四种线城池：
 *           CacheThreadPool 大意是说当有任务提交进来，会优先使用线程池里可用的空闲线程来执行任务，
 *             但是如果没有可用的线程会直接创建线程。空闲的线程会保留 60s，之后才会被回收。
 *
 *           思考： 1. 如何让线程存活60,之后死亡？
 *                  2. 如何实现线程的复用？
 *                  
 *
 *
 */


public class ThreadPoolExecutorActivity extends AppCompatActivity {

    // private ThreadManager.ThreadPool threadPool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thread_pool_executor);

        // threadPool = ThreadManager.getInstance(6, 10);

        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        // 2.ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        // 3.ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
        // 4. ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(1));
         // ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());

        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        System.out.println("---先开三个---");
        System.out.println("核心线程数" + threadPool.getCorePoolSize());
        System.out.println("当前线程池数" + threadPool.getPoolSize());
        System.out.println("队列任务数" + threadPool.getQueue().size());

        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        System.out.println("---又开三个---");
        System.out.println("核心线程数" + threadPool.getCorePoolSize());
        System.out.println("当前线程池数" + threadPool.getPoolSize());
        System.out.println("队列任务数" + threadPool.getQueue().size());
     /*   threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        threadPool.execute(myRunnable);
        System.out.println("--当前线程数大于核心线程数---");
        System.out.println("核心线程数" + threadPool.getCorePoolSize());
        System.out.println("当前线程池数" + threadPool.getPoolSize());
        System.out.println("队列任务数" + threadPool.getQueue().size());*/
        try {
            // 8s 过后
            Thread.sleep(8000);
            System.out.println("核心线程数" + threadPool.getCorePoolSize());
            System.out.println("当前线程池数" + threadPool.getPoolSize());
            System.out.println("队列任务数" + threadPool.getQueue().size());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


     /*
       1.  当 ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS,
                                                  new SynchronousQueue<Runnable>());
          执行上面代码块，输出如下结果：
                          得出结论 ： 每次都是直接启动一个线程来执行任务，不会放入任务队列中

        I/System.out: ---先开三个---
                I/System.out: 核心线程数6
        I/System.out: 当前线程池数3
        I/System.out: 队列任务数0
        I/System.out: ---又开三个---
                I/System.out: 核心线程数6
        I/System.out: 当前线程池数6
        I/System.out: 队列任务数0
        I/System.out: --当前线程数大于核心线程数---
                I/System.out: 核心线程数6
        I/System.out: 当前线程池数9
        I/System.out: 队列任务数0

        I/System.out: 名称pool-2-thread-1 run
        I/System.out: 名称pool-2-thread-3 run
        I/System.out: 名称pool-2-thread-2 run
        I/System.out: 名称pool-2-thread-6 run
        I/System.out: 名称pool-2-thread-4 run
        I/System.out: 名称pool-2-thread-5 run
        I/System.out: 名称pool-2-thread-7 run
        I/System.out: 名称pool-2-thread-8 run
        I/System.out: 名称pool-2-thread-9 run

       2. 当   ThreadPoolExecutor threadPool = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>());
        结果：
        结论： 当线程池内的线程大于核心线程数，会将超出的线程放入任务队列中，等待执行
        I/System.out: ---先开三个---
        I/System.out: 核心线程数6
        I/System.out: 当前线程池数3
        I/System.out: 队列任务数0
        I/System.out: ---又开三个---
        I/System.out: 核心线程数6
        I/System.out: 当前线程池数6
        I/System.out: 队列任务数0
        I/System.out: --当前线程数大于核心线程数---
        I/System.out: 核心线程数6
        I/System.out: 当前线程池数6
        I/System.out: 队列任务数3

        I/System.out: 名称pool-2-thread-1 run
        I/System.out: 名称pool-2-thread-2 run
        I/System.out: 名称pool-2-thread-3 run
        I/System.out: 名称pool-2-thread-4 run
        I/System.out: 名称pool-2-thread-5 run
        I/System.out: 名称pool-2-thread-6 run
        I/System.out: 名称pool-2-thread-1 run
        I/System.out: 名称pool-2-thread-2 run
        I/System.out: 名称pool-2-thread-3 run

        3. 当 ： ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
         结果如下：
         结论： 队列为SynchronousQueue ，核心线程数3， 最大线程数6 ，
                           当我们执行的线程超过核心线程数时，他会创建非核心线程数，
                            当线程池内的线程数大于最大线程数时，会报错，因为 SynchronousQueue 不会保存线程
       I/System.out: ---先开三个---
       I/System.out: 核心线程数3
       I/System.out: 当前线程池数3
       I/System.out: 队列任务数0
       I/System.out: ---又开三个---
       I/System.out: 核心线程数3
       I/System.out: 当前线程池数6
       I/System.out: 队列任务数0
       I/System.out: 名称pool-2-thread-1 run
       I/System.out: 名称pool-2-thread-5 run
       I/System.out: 名称pool-2-thread-2 run
       I/System.out: 名称pool-2-thread-3 run
       I/System.out: 名称pool-2-thread-4 run
       I/System.out: 名称pool-2-thread-6 run
        睡8s之后
       I/System.out: 核心线程数3
       I/System.out: 当前线程池数3
       I/System.out: 队列任务数0



    4.  当：ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(2));
      结果如下：
      结论： 首先为前三个任务开启了三个核心线程1，2，3，然后第四个任务和第五个任务加入到队列中，
             第六个任务因为队列满了，就直接创建一个（非核心）新线程4，这是一共有四个线程，没有超过最大线程数。
             8秒后，非核心线程收超时时间影响回收了，因此线程池只剩3个线程了。

             如果把new LinkedBlockingDeque<Runnable>(2) 的大小设置成 1,直接报异常，遵照上面的分析即可得出结论。

             当队列为LinkedBlockingDeque 并且为他设置了固定大小，此时最大线程数才会受影响
             否则 最大线程数就没有作用（看上述 2）

      I/System.out: ---先开三个---
      I/System.out: 核心线程数3
      I/System.out: 当前线程池数3
      I/System.out: 队列任务数0
      I/System.out: ---又开三个---
      I/System.out: 核心线程数3
      I /System.out: 当前线程池数4
      I/System.out: 队列任务数2
      I/System.out: 名称pool-2-thread-1 run
      I/System.out: 名称pool-2-thread-2 run
      I/System.out: 名称pool-2-thread-3 run
      I/System.out: 名称pool-2-thread-4 run
      I/System.out: 名称pool-2-thread-1 run
      I/System.out: 名称pool-2-thread-2 run
      8s之后
      I/System.out: 核心线程数3
      I/System.out: 当前线程池数3
      I/System.out: 队列任务数0


      5. 当 ThreadPoolExecutor threadPool = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new SynchronousQueue<Runnable>());
         结果： 直接抛异常
         结论： SynchronousQueue 不会保存线程，原理： 拿到任务就去创建线程，当线程数大于4是就报错了
*/

    }


    Runnable myRunnable = new Runnable() {
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                System.out.println("名称" + Thread.currentThread().getName() + " run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    };


}
