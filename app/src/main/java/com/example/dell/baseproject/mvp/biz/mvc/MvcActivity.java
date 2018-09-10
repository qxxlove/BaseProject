package com.example.dell.baseproject.mvp.biz.mvc;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.mvp.biz.BizR;
import com.example.dell.baseproject.mvp.biz.BizRequest;
import com.example.dell.baseproject.mvp.biz.OnRequestListener;
import com.example.dell.baseproject.mvp.biz.mmmvp.MvpActivity;

import java.util.List;

public class MvcActivity extends AppCompatActivity {

    private ListView listView;
    private BizRequest bizRequest;
    private TextView text_click;
    private Handler  handler;
    private TextView text_intent_mvp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvc);
        listView = ((ListView) findViewById(R.id.list_mvc));
        text_click = ((TextView) findViewById(R.id.text_click));
        text_intent_mvp = ((TextView) findViewById(R.id.text_itent_mvp));
        bizRequest =  new BizR();
        handler   =   new Handler(Looper.getMainLooper());
        initClick();

    }

    private void initClick() {
        text_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HttpRequestData();
            }
        });

        text_intent_mvp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MvcActivity.this,MvpActivity.class);
                startActivity(intent2);
            }
        });
    }

    private void HttpRequestData() {
        bizRequest.onRequestData(new OnRequestListener() {
            @Override
            public void onError() {

            }

            @Override
            public void onSuccess(final List<String> data) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayAdapter    arrayAdapter = new ArrayAdapter(MvcActivity.this,android.R.layout.simple_list_item_activated_1,data);
                        listView.setAdapter(arrayAdapter);
                    }
                });
            }
        });

    }
}
