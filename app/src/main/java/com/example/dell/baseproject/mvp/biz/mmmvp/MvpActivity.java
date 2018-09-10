package com.example.dell.baseproject.mvp.biz.mmmvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;

import java.util.List;

public class MvpActivity extends AppCompatActivity implements  MvpView {

    private ListView litview_mvp;
    private ProgressBar mprogressBar;
    private MvpPresenter mvpPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp);
        initView();
        initData();
        initClick();
    }

    private void initClick() {
        litview_mvp.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 mvpPresenter.onItemClick(position);
            }
        });

    }

    private void initData() {
         mvpPresenter = new MvpPresenter(this);
    }

    private void initView() {
        litview_mvp = ((ListView) findViewById(R.id.listview_mvp));
        mprogressBar = ((ProgressBar) findViewById(R.id.progress_mvp));

    }

    @Override
    protected void onResume() {
        super.onResume();
        mvpPresenter.onResume();
    }

    @Override
    public void showDialog() {
         mprogressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDialog() {
        mprogressBar.setVisibility(View.GONE);
    }

    @Override
    public void updateListview(List<String> list) {
        ArrayAdapter arrayAdapter = new ArrayAdapter(MvpActivity.this,android.R.layout.simple_list_item_activated_1,list);
        litview_mvp.setAdapter(arrayAdapter);
    }

    @Override
    public void showToast(String msg) {
        BaseUtils.toast(this,msg);
    }


}
