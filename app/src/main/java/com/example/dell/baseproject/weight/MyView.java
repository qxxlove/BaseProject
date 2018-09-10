package com.example.dell.baseproject.weight;

import android.view.View;

import com.github.jdsjlzx.interfaces.IRefreshHeader;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/7/22.
 * 邮箱：123123@163.com
 */

public class MyView  implements IRefreshHeader {
    @Override
    public void onReset() {

    }

    @Override
    public void onPrepare() {

    }

    @Override
    public void onRefreshing() {

    }

    @Override
    public void onMove(float offSet, float sumOffSet) {

    }

    @Override
    public boolean onRelease() {
        return false;
    }

    @Override
    public void refreshComplete() {

    }

    @Override
    public View getHeaderView() {
        return null;
    }

    @Override
    public int getVisibleHeight() {
        return 0;
    }
}
