package com.example.dell.baseproject.mvp.biz.generic;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.baseproject.R;

/** 
 * description: 泛型的使用： 泛型即多种数据类型都可以，同时可以声明多个，默认Object
 * autour: TMM
 * date: 2017/10/18 14:19 
 * update: 2017/10/18
 * version: 
*/
public class TestActivity extends AppCompatActivity implements  ITeacher<Teacher<String,String>,String> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        // ①  实体类泛型
        Teacher<String,Integer>   teacher = new Teacher<>("",1);
    }


   // ③ 有的时候，类、接口或方法需要对类型变量加以约束。
  /*  public static <T> T get(T t1,T t2) {
        if(t1.compareTo(t2)>=0);//编译错误
        return t1;
    }*/

    public static <T extends  Comparable> T get(T t1,T t2) {  //添加类型限定
        if(t1.compareTo(t2)>=0);
        return t1;
    }

   /* 类型限定在泛型类、泛型接口和泛型方法中都可以使用，不过要注意下面几点：
            1、不管该限定是类还是接口，统一都使用关键字 extends
            2、可以使用&符号给出多个限定，比如
             public static <T extends Comparable&Serializable> T get(T t1, T t2);
            3、如果限定既有接口也有类，那么类必须只有一个，并且放在首位置
             public static <T extends Object&Comparable&Serializable> T get(T t1,T t2);*/






    // ② 接口泛型
    @Override
    public void getTeacherInfo(Teacher<String, String> stringStringTeacher, String s) {
           stringStringTeacher.getAge();
           stringStringTeacher.getName();
           
    }
}
