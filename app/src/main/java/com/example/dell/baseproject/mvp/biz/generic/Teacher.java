package com.example.dell.baseproject.mvp.biz.generic;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/18.
 * 邮箱：123123@163.com
 */

public class Teacher<T,U> {

    private  T    name;
    private  U    age ;

    public Teacher(T name, U age) {
        this.name = name;
        this.age = age;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public U getAge() {
        return age;
    }

    public void setAge(U age) {
        this.age = age;
    }
}
