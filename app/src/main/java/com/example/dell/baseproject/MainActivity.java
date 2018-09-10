package com.example.dell.baseproject;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.example.dell.baseproject.activity.BaseActivity;
import com.example.dell.baseproject.bean.ActionBarStyle;
import com.example.dell.baseproject.bean.Tab;
import com.example.dell.baseproject.fragment.CarShopfragment;
import com.example.dell.baseproject.fragment.Homefragment;
import com.example.dell.baseproject.fragment.Minefragment;
import com.example.dell.baseproject.fragment.TypeFragment;
import com.example.dell.baseproject.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;


/*
*     学习一个新东西一定要学会看官网，是第一手资料。
*         ① fragmentTabHost 缺陷： 不会保存当前fragment的状态，当tab间进行切换，每次都重新加载，待完善
*
*
*
* */


public class MainActivity extends BaseActivity {


    private FragmentTabHost fragment_tab;
    private LayoutInflater layoutInflater;

    private List<Tab> list_tab = new ArrayList<>(4);


    @Override
    public int initContentID() {
        return 0;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        setActionBarStyle(ActionBarStyle.HideBoth);
        setTitle(BaseUtils.getString(R.string.home))   ;
        // initView();       //fragmentTabhost  的简单用法

        initTab();          // 实际开发中项目中所用到的
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initListener() {

    }

    @Override
    protected void execHttp() {

    }


    private void initView() {
        layoutInflater = LayoutInflater.from(this);
        fragment_tab = ((FragmentTabHost) findViewById(android.R.id.tabhost));
        fragment_tab.setup(this,getSupportFragmentManager(),R.id.realTabHost);   // 狸猫换太子

        TabHost.TabSpec tabSpec = fragment_tab.newTabSpec("home");
        View view = layoutInflater.inflate(R.layout.indicator_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);

         imageView.setBackgroundResource(R.mipmap.main_tabs_index);
         textView.setText("主页");

        tabSpec.setIndicator(view);
        fragment_tab.addTab(tabSpec, Homefragment.class, null);

        //fragment_tab.addTab(fragment_tab.newTabSpec("home").setIndicator(view));    链式编程简单写法

    }

    private void initTab() {
        Tab tab_home = new Tab(R.string.home,R.drawable.tab_selector,Homefragment.class);
        Tab tab_type = new Tab(R.string.type,R.drawable.tab_selector_type,TypeFragment.class);
        Tab tab_car = new Tab(R.string.shop,R.drawable.tab_selector_car,CarShopfragment.class);
        Tab tab_mine = new Tab(R.string.mine,R.drawable.tab_selector_mine,Minefragment.class);
        list_tab.add(tab_home);
        list_tab.add(tab_type);
        list_tab.add(tab_car);
        list_tab.add(tab_mine);


        layoutInflater = LayoutInflater.from(this);
        fragment_tab = ((FragmentTabHost) findViewById(android.R.id.tabhost));
        fragment_tab.setup(this, getSupportFragmentManager(), R.id.realTabHost);   // 狸猫换太子

        for (Tab tab :list_tab){
            TabHost.TabSpec tabSpec = fragment_tab.newTabSpec(getString(tab.getTitle()));
            tabSpec.setIndicator(buildView(tab));
            fragment_tab.addTab(tabSpec, tab.getFragment(), null);

        }

        fragment_tab.getTabWidget().setShowDividers(LinearLayout.SHOW_DIVIDER_NONE);   // 去掉自带的分割线
        fragment_tab.setCurrentTab(0);                                                   // 默认选择第一个
    }


    private  View    buildView(Tab tab) {
        View view = layoutInflater.inflate(R.layout.indicator_view, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageView);
        TextView textView = (TextView) view.findViewById(R.id.textView);
        imageView.setBackgroundResource(tab.getDrawable());
        textView.setText(getString(tab.getTitle()));
        return  view;
    }

}