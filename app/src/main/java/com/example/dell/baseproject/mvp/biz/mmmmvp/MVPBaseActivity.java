package com.example.dell.baseproject.mvp.biz.mmmmvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.dell.baseproject.weight.dialog.LoadingDialog;

/**
 * description:
 * autour: TMM
 * date: 2017/10/18 15:28
 * update: 2017/10/18
 * version:
 *
 * 为什么要在 MVPBaseActivity 使用泛型，因为这是基类，
 *     ① 泛型 V 可以使任何数据类型，
 *     ②  T  extend BasePresener<V> 来限制此数据类型必须是BasePresener的实现类
 *     这个可以看 MVPBaseActivity 的实现类   LoginActiviy
 *
*/

public abstract class MVPBaseActivity<V,T extends  BasePresener<V>> extends AppCompatActivity {


    protected  T mPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_mvpbase);

        mPresenter = createPresenter();

        mPresenter.attachView((V)this);


    }


    protected  abstract  T createPresenter ();



    private LoadingDialog loadingDialog = null;

    public  void   showProgressDialog (String loadingMsg,boolean isCancel){
        if (loadingDialog == null){
             loadingDialog = new LoadingDialog(this).init(isCancel).setLoadingMsg(loadingMsg);
        }else {
        loadingDialog = new LoadingDialog(this).init(isCancel).setLoadingMsg(loadingMsg);
        }
        loadingDialog.show();
    }

     public  void hideProgressDialog () {
         if (loadingDialog != null){
               loadingDialog.dismiss();
               loadingDialog = null;
         }
     }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detechView();

    }
}
