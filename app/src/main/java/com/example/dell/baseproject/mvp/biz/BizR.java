package com.example.dell.baseproject.mvp.biz;

import java.util.ArrayList;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/8/24.
 * 邮箱：123123@163.com
 */

public class BizR implements  BizRequest {
    @Override
    public void onRequestData(final OnRequestListener onRequestListener) {
        new Thread(new Runnable() {
          @Override
          public void run() {
              try {
                  Thread.sleep(2000);
                  ArrayList<String>  arrayList = new ArrayList<String>();
                  for (int i = 0; i <8 ; i++) {
                         arrayList.add(i+"哈哈哈");
                  }
                  if ( null != onRequestListener){
                      onRequestListener.onSuccess(arrayList);
                  }
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }

          }
        }).start();
    }
}
