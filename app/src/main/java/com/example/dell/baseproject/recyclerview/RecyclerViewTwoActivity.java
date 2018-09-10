package com.example.dell.baseproject.recyclerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.BaseRecyclerAdapter;
import com.example.dell.baseproject.bean.RecyclerData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description: 学习RecyclerView从最开始到一步步如何封装使用
 * autour: TMM
 * date: 2017/11/8 16:20
 * update: 2017/11/8
 * version:
 */
public class RecyclerViewTwoActivity extends AppCompatActivity {

    @BindView(R.id.recycler_activity_two)
    RecyclerView recyclerActivityTwo;
    @BindView(R.id.activity_recycler_view_two)
    LinearLayout activityRecyclerViewTwo;

    private List<RecyclerData> list;
    private BaseRecyclerAdapter mBaseRecyAdapter ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view_two);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        list = new ArrayList<>();
        list.add(new RecyclerData("1","卡欧邪恶","速度飞快的法国进口的风扇接口"));
        list.add(new RecyclerData("2","房贷首付","地方烦烦烦方法"));
        list.add(new RecyclerData("3","的撒法发","发vv嘎嘎嘎嘎嘎"));
        list.add(new RecyclerData("4","反对法","程序卸载方法"));
        list.add(new RecyclerData("5","的","大师傅士大夫"));
        list.add(new RecyclerData("6","吊死扶伤","士大夫士大夫撒旦地方撒"));
        list.add(new RecyclerData("7","撒旦","士大夫士大夫是打发士大夫撒旦"));
        list.add(new RecyclerData("8","的","的幅度萨芬撒范德萨范德萨范德萨"));

        mBaseRecyAdapter = new BaseRecyclerAdapter(list,this);
        recyclerActivityTwo.setLayoutManager(new LinearLayoutManager(this));
        recyclerActivityTwo.setAdapter(mBaseRecyAdapter);

    }
}
