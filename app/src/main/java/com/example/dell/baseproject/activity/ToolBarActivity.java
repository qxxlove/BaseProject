package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.example.dell.baseproject.R;

public class ToolBarActivity extends FragmentActivity {


    private Toolbar toolbar_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toolbar);
        initView();
        initClick();
    }

    private void initClick() {
        // toolbar 自带的返回键
        toolbar_back.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // toolbar 自带的菜单键
        toolbar_back.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {

                }
                return false;
            }
        });
    }

    private void initView() {
        toolbar_back = ((Toolbar) findViewById(R.id.toolbar_system));

    }
}
