package com.example.dell.baseproject.mvp.biz.mmmvp;

import android.os.Handler;
import android.os.Looper;

import com.example.dell.baseproject.mvp.biz.BizR;
import com.example.dell.baseproject.mvp.biz.BizRequest;
import com.example.dell.baseproject.mvp.biz.OnRequestListener;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/8/24.
 * 邮箱：123123@163.com
 */

public class MvpPresenter {

    private  MvpView mvpView;
    private BizRequest bizRequest;
    private Handler  mHandler;

    public  MvpPresenter (MvpView mView) {
        this.mvpView = mView;
         bizRequest = new BizR();
         mHandler = new Handler(Looper.getMainLooper());
    }

    public  void   onResume () {
          mvpView.showDialog();
          bizRequest.onRequestData(new OnRequestListener() {
              @Override
              public void onError() {
                  mvpView.hideDialog();
              }

              @Override
              public void onSuccess(final List<String> data) {
                 mHandler.post(new Runnable() {
                     @Override
                     public void run() {
                         mvpView.hideDialog();
                         mvpView.updateListview(data);
                     }
                 });


              }
          });


    }

    public  void  onItemClick (int position) {
          mvpView.showToast("点击了"+position);
    }

}
