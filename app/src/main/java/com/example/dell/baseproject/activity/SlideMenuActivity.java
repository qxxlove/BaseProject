package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.example.dell.baseproject.R;

public class SlideMenuActivity extends AppCompatActivity {

    Button mCloseButton;
    Button mOpenButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_menu);
        initView();
        initCLick();
    }

    private void initView() {

        mCloseButton = (Button) findViewById(R.id.button_close);
        mOpenButton = (Button) findViewById(R.id.button_open);

    }

    private void initCLick() {

    }
}