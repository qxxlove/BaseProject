package com.example.dell.baseproject.bean;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/19.
 * 邮箱：123123@163.com
 */

public class CouseBean {

    private  int   c_id;
    private  String  c_subject;
    private  String  c_time;

    public CouseBean() {
    }

    public CouseBean(int c_id, String c_subject, String c_time) {
        this.c_id = c_id;
        this.c_subject = c_subject;
        this.c_time = c_time;
    }

    public int getC_id() {
        return c_id;
    }

    public void setC_id(int c_id) {
        this.c_id = c_id;
    }

    public String getC_subject() {
        return c_subject;
    }

    public void setC_subject(String c_subject) {
        this.c_subject = c_subject;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }
}
