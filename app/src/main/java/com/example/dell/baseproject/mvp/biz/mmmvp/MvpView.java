package com.example.dell.baseproject.mvp.biz.mmmvp;

import java.util.List;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/8/24.
 * 邮箱：123123@163.com
 */

public interface MvpView {
    void  showDialog ();
    void  hideDialog();
    void  updateListview(List<String> list);
    void  showToast(String msg);

}
