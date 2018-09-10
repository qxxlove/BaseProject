package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * description:    butterknife 使用注意 gradle的版本 ，及时上github进行学习
 * autour: TMM
 * date: 2017/7/29 18:23
 * update: 2017/7/29
 * version:
*/

public class BUtterknifeActivity extends AppCompatActivity {

    @BindView(R.id.text_butterknife_one)
    TextView textButterknifeOne;
    @BindView(R.id.text_butterknife_two)
    TextView textButterknifeTwo;
    @BindView(R.id.text_butterknife_three)
    TextView textButterknifeThree;
    @BindView(R.id.text_butterknife_four)
    TextView textButterknifeFour;
    @BindView(R.id.activity_butterknife)
    LinearLayout activityButterknife;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_butterknife);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {



    }

    @OnClick({R.id.text_butterknife_one,R.id.text_butterknife_two})
    public void initClick(View view) {
        switch (view.getId()) {
            case R.id.text_butterknife_one :
                BaseUtils.toast(this,"one");
                break;
            case  R.id.text_butterknife_two :
                BaseUtils.toast(this,"two");
                break;
        }
    }
}
