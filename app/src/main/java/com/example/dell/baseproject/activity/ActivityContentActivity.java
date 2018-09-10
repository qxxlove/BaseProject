package com.example.dell.baseproject.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.dell.baseproject.R;
import com.example.dell.baseproject.utils.BaseUtils;
import com.example.dell.baseproject.utils.LogUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * description: Activity 基础
 * autour: TMM
 * date: 2018/1/8 16:54
 * update: 2018/1/8
 * version:
 * ①  onSaveInstanceState    用来界面销毁时保存重要的数据
 * ②  onRestoreInstanceState  界面恢复时取出保存的数据   --->  等同于onCreate , 因为onCreate() 携带了 Bundle 信息
 *    了解即可，实际项目开发不提倡，无法准确把控其发生的场景
 *    参考： http://blog.csdn.net/Tomasyb/article/details/73529970
 *           https://mp.weixin.qq.com/s/lrLlAmC5U4Q4HVhU34H6Qg
 *
 */

public class ActivityContentActivity extends AppCompatActivity {

    @BindView(R.id.edit_input_content)
    EditText editInputContent;
    @BindView(R.id.activity_content)
    LinearLayout activityContent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content);
        ButterKnife.bind(this);
        LogUtils.e(LogUtils.TAG, "---------------onCreate-------------------");

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogUtils.e(LogUtils.TAG, "---------------onSaveInstanceState-------------------");
        if (!BaseUtils.isEmpty(editInputContent.getText().toString().trim())) {
            outState.putString("outState",editInputContent.getText().toString().trim());
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        LogUtils.e(LogUtils.TAG, "---------------onRestoreInstanceState-------------------");
        if (savedInstanceState != null){
            String outState = savedInstanceState.getString("outState");
            editInputContent.setText(outState);
        }

    }
}
