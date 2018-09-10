package com.example.dell.baseproject.mvp.biz.mmmmvp.p;

import android.os.Handler;

import com.example.dell.baseproject.mvp.biz.mmmmvp.BasePresener;
import com.example.dell.baseproject.mvp.biz.mmmmvp.m.IViewUserModel;
import com.example.dell.baseproject.mvp.biz.mmmmvp.m.LoginBean;
import com.example.dell.baseproject.mvp.biz.mmmmvp.m.LoginUserListener;
import com.example.dell.baseproject.mvp.biz.mmmmvp.m.ViewUserModel;
import com.example.dell.baseproject.mvp.biz.mmmmvp.v.IViewUserLogin;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/10/18.
 * 邮箱：123123@163.com
 *
 *    BasePresener<IViewUserLogin>   为什么也要使用泛型？
 *    ①  因为BasePresener 的数据类型可以有好多，此处只是以登录 （IViewUserLogin）为例 ，除此之外还会有 注册，等等好多
 */



public class IViewUserLoginPresener  extends BasePresener<IViewUserLogin>{

    private IViewUserModel iViewUserModel ;
    private IViewUserLogin iViewUserLogin;

    private Handler mhandler = new Handler() ;


    public     IViewUserLoginPresener (IViewUserLogin iViewUserLogin) {
        this.iViewUserLogin = iViewUserLogin;
        iViewUserModel = new ViewUserModel();
    }

    public  void    login () {
         iViewUserLogin.shouDialog();
         iViewUserModel.login(iViewUserLogin.getUserName(), iViewUserLogin.getUserPass(), new LoginUserListener() {
             @Override
             public void loginSuccess(final LoginBean loginBean) {
                  mhandler.post(new Runnable() {
                      @Override
                      public void run() {
                          iViewUserLogin.hideDialog();
                          iViewUserLogin.toMainActivity(loginBean);
                      }
                  });

             }

             @Override
             public void loginFaile() {
                 mhandler.post(new Runnable() {
                     @Override
                     public void run() {
                         iViewUserLogin.hideDialog();
                         iViewUserLogin.tofaile();
                     }
                 });

             }
         });


    }

}
