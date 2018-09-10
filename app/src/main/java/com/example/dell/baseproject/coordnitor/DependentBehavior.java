package com.example.dell.baseproject.coordnitor;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * 描述 ：
 * 作者：Created by SEELE on 2017/11/3.
 * 邮箱：123123@163.com
 */

public class DependentBehavior extends CoordinatorLayout.Behavior<View> {


    public DependentBehavior(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean layoutDependsOn(CoordinatorLayout parent, View child, View dependency) {
        return dependency instanceof TextView;
    }


    @Override
    public boolean onDependentViewChanged(CoordinatorLayout parent, View child, View dependency) {

        int offset = dependency.getTop() - child.getTop();
        ViewCompat.offsetTopAndBottom(child, offset);

        return true;
    }
}
