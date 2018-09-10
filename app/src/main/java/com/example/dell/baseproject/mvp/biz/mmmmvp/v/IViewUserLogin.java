package com.example.dell.baseproject.mvp.biz.mmmmvp.v;

import com.example.dell.baseproject.mvp.biz.mmmmvp.m.LoginBean;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/18.
 * 邮箱：123123@163.com
 */

public interface IViewUserLogin {

    public  String  getUserName();
    public  String  getUserPass();
    public  void    clearUserName();
    public  void    clearUserPass();
    public  void    hideDialog();
    public  void    shouDialog();
    public  void    toMainActivity(LoginBean loginBean);
    public  void    tofaile();
}
