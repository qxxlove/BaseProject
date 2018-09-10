package com.example.dell.baseproject.coordnitor;

import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.dell.baseproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * description: Materia Design  5.0 新特性
 * autour: TMM
 * date: 2017/11/3 10:37
 * update: 2017/11/3
 * version:
 *
 * 参考;https://mp.weixin.qq.com/s/-ldKAMGChfbvk8E8nzvuNw
 *
 */

public class CoordinatorLayoutActivity extends AppCompatActivity {

    @BindView(R.id.text_depend)
    TextView textDepend;
    @BindView(R.id.text_auto)
    TextView textAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        ButterKnife.bind(this);
       /* Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        textDepend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewCompat.offsetTopAndBottom(v, 5);
            }
        });

    }

}
