package com.example.dell.baseproject.mvp.biz.mmmmvp.m;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/18.
 * 邮箱：123123@163.com
 */

public class ViewUserModel implements  IViewUserModel {
    @Override
    public void login(final String name, final String pass, final LoginUserListener loginUserListener) {
        //模拟子线程耗时操作
        new Thread()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(2000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                //模拟登录成功
                if ("123".equals(name) && "123".equals(pass))
                {
                    loginUserListener.loginSuccess(null);
                } else
                {
                    loginUserListener.loginFaile();
                }
            }
        }.start();
    }
}
