package com.example.dell.baseproject.utils;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/11/10.
 * 邮箱：123123@163.com
 */

public class NonScrollableViewPager extends ViewPager {

    public NonScrollableViewPager(Context context) {
        super(context);
    }



    public NonScrollableViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return false;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return false;
    }
}
