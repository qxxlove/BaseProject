package com.example.dell.baseproject.rxjava;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.bean.CouseBean;
import com.example.dell.baseproject.bean.Student;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.utils.LogUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;


/**
 * description:
 * autour: TMM
 * date: 2017/10/19 16:20
 * update: 2017/10/19
 * version:
 *
 *   ① ---  ⑤   注意： RxJava在不指定线程的情况下，发起时间和消费时间默认使用当前线程 （主线程）
 *    subscribeOn()：指定subscribe() 所发生的线程，即 Observable.OnSubscribe 被激活时所处的线程。或者叫做事件产生的线程。   （可理解为耗时操作（子线程））
      observeOn()：指定Subscriber 所运行在的线程。或者叫做事件消费的线程。                                                    （更新UI   主线程）

      Schedulers.immediate()：直接在当前线程运行，相当于不指定线程。这是默认的 Scheduler。
      Schedulers.newThread()：总是启用新线程，并在新线程执行操作。
      Schedulers.io()： I/O 操作（读写文件、读写数据库、网络信息交互等）所使用的 Scheduler。行为模式和 newThread() 差不多，区别在于 io() 的内部实现是是用一个无数量上限的线程池，可以重用空闲的线程，因此多数情况下 io() 比 newThread() 更有效率。不要把计算工作放在 io() 中，可以避免创建不必要的线程。
      Schedulers.computation()：计算所使用的 Scheduler。这个计算指的是 CPU 密集型计算，即不会被 I/O 等操作限制性能的操作，例如图形的计算。这个 Scheduler 使用的固定的线程池，大小为 CPU 核数。不要把 I/O 操作放在 computation() 中，否则 I/O 操作的等待时间会浪费 CPU。
      AndroidSchedulers.mainThread()：它指定的操作将在 Android 主线程运行。



 */



public class RxjavaActivity extends AppCompatActivity {

    @BindView(R.id.text_rx_one)
    TextView textRxOne;
    @BindView(R.id.activity_rxjava)
    LinearLayout activityRxjava;

    private List<String>   list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);
        initView();
        initData();
        initClick();
    }

    private void initClick() {

    }

    private void initData() {
        // ①
        Observable<String> observable = getObservable();
        Observer<String> observe = getObserve();
        observable.subscribe(observe);

        // ②  为什么 就是点不出来 Action1（有一个参数无返回值的）  Action0（无参无返回值的）   (是废弃了还是自己版本问题)
        // ③   Func1 同 Action 相似，但是 Func1 是有返回值

        // ④ map （类型转换 ）    例如  将 Stutdent 转换为String类型的
         getStuMapToString ();

        // ⑤ flatMap              例如：打印出学生的所有课程
        getStuToCouse();

        // ⑥ Scheduler
        Observable.just("Hello", "Word")
                .subscribeOn(Schedulers.newThread())//指定 subscribe() 发生在新的线程       开发中推荐使用Schedulers.io()效率比newThread效率更高
                //  此处可执行耗时操作
                .observeOn(AndroidSchedulers.mainThread())// 指定 Subscriber 的回调发生在主线程 （observeOn() 指定的是它之后的操作所在的线程）
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                         Log.e("TAG",s);
                    }
                });



    }

    /**
     * 将 Student  获取到所有课程
     */
    private void getStuToCouse() {
         List<CouseBean> list = new ArrayList<>();
         for (int i = 0; i <5 ; i++) {
             list.add(new CouseBean(i,"语文","2017/10/19 14:12:02"));
         }
         Student student = new Student("小雪",25,list);

         Observable.fromArray(student).flatMap(new Function<Student, Observable<List<CouseBean>>>() {
             @Override
             public Observable<List<CouseBean>> apply(Student student) throws Exception {
                 List<CouseBean> list_couse = student.getList_couse();
                 return Observable.fromArray(list_couse);
             }
         }).subscribe(new Consumer<List<CouseBean>>() {
             @Override
             public void accept(List<CouseBean> couseBeen) throws Exception {
                 Log.i("TAG", couseBeen.get(1).getC_subject());
             }
         });
    }

    /**
     * 将Student 对象 转换为String
     */
    private void getStuMapToString() {
        Student   student = new Student("小乔",1);
        Student   student1 = new Student("大乔",1);
        //使用map进行转换，   (Student, String ) 参数1：转换前的类型，参数2：转换后的类型
        Observable.just(student,student1).map(new Function<Student, String>() {
            @Override
            public String apply(Student student) throws Exception {
                String s_name  =  student.getName();
                return s_name;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Exception {
                 list.add(s);
                 BaseUtils.toast(RxjavaActivity.this,list.toString());
            }
        });
    }

    /**
     * 得到 Observe 观察者
     */
    private Observer<String> getObserve() {
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String value) {
                 textRxOne.setText(value);
            }

            @Override
            public void onError(Throwable e) {
                LogUtils.e("TAG", e.getMessage());
            }

            @Override
            public void onComplete() {
                LogUtils.e("TAG", "完成");
            }
        };
        return observer;
    }

    /**
     * 得到Observable 对象（被观察者）
     */
    private Observable<String> getObservable() {
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("我是最简单的");
                e.onComplete();
            }
        });
        return observable;
    }

    private void initView() {

    }



}
