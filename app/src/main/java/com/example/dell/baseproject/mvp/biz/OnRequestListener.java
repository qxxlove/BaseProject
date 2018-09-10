package com.example.dell.baseproject.mvp.biz;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/8/24.
 * 邮箱：123123@163.com
 */

public interface OnRequestListener {

    public  void  onError ();
    public void onSuccess(List<String> data);

}
