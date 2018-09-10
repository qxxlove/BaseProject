package com.example.dell.baseproject.bean;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/19.
 * 邮箱：123123@163.com
 */

public class Student {

    private  String name ;
    private  int   age;

    private List<CouseBean>   list_couse;


    public Student() {
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student(String name, int age, List<CouseBean> list_couse) {
        this.name = name;
        this.age = age;
        this.list_couse = list_couse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<CouseBean> getList_couse() {
        return list_couse;
    }

    public void setList_couse(List<CouseBean> list_couse) {
        this.list_couse = list_couse;
    }
}
