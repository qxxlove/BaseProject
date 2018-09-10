package com.example.dell.baseproject.bean;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/9/22.
 * 邮箱：123123@163.com
 */

public class StudentBean  {

    private  String name ;
    private  boolean isCheck;


    public StudentBean() {
    }

    public StudentBean(String name) {
        this.name = name;
    }

    public StudentBean(String name, boolean isCheck) {
        this.name = name;
        this.isCheck = isCheck;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
