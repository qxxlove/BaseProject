package com.example.dell.baseproject.bean;

/**
 * Created by DELL on 2017/6/10.
 */
public class Tab {

    private  int title;
    private  int drawable;
    private  Class fragment;

    public Tab(int title, int drawable, Class fragment) {
        this.title = title;
        this.drawable = drawable;
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
