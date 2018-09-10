package com.example.dell.baseproject.ui.nestedscroll;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.dell.baseproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NestedScrollOneActivity extends AppCompatActivity {

    @BindView(R.id.image_nestedScroll_click)
    ImageView imageNestedScrollClick;
    @BindView(R.id.activity_nested_scroll_one)
    RelativeLayout activityNestedScrollOne;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nested_scroll_one);
        ButterKnife.bind(this);
    }


    @OnClick({R.id.image_nestedScroll_click})
    public    void    initClick (View view) {
        Intent intent ;
       switch (view.getId()){
           case  R.id.image_nestedScroll_click :
               intent = new Intent(this, NestedScrollTwoActivity.class);
               startActivity(intent);
               break;
           default:
       }
    }

}
