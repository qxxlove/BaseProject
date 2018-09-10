package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.adapter.ImageAdapter;

public class GridViewActivity extends AppCompatActivity {

    GridView gridView_radio;        //单选宫格
    GridView gridView_check;        //多选宫格

    ImageAdapter ia_radio;            //存储图片源的适配器(单选)
    ImageAdapter ia_check;            //存储图片源的适配器(多选)


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);
        // 单选的宫格
        gridView_radio = (GridView) findViewById(R.id.gridview_radio);
        ia_radio = new ImageAdapter(this, false);
        gridView_radio.setAdapter(ia_radio);
        // 设置点击监听
        gridView_radio.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                ia_radio.changeState(position);
            }
        });

        // 多选的宫格
        gridView_check = (GridView) findViewById(R.id.gridview_check);
        ia_check = new ImageAdapter(this, true);
        gridView_check.setAdapter(ia_check);
        // 设置点击监听
        gridView_check.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1,
                                    int position, long arg3) {
                // TODO Auto-generated method stub
                ia_check.changeState(position);
            }
        });
    }
}
